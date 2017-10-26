package darius.partene.mycontacts.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by partened on 26.10.2017.
 */

public class Contact implements Parcelable {
    private String firstName;
    private String lastName;
    private String age;
    private String thumbnail;
    private String picture;
    private String nationality;
    private String email;

    public Contact(JSONObject item) {
        if (item.has("name")) {
            try {
                JSONObject nameJson = item.getJSONObject("name");
                firstName = nameJson.optString("first");
                lastName = nameJson.optString("last");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        age = getAge(item.optString("dob"));

        if (item.has("picture")) {
            try {
                JSONObject nameJson = item.getJSONObject("picture");
                thumbnail = nameJson.optString("thumbnail");
                picture = nameJson.optString("large");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        nationality = item.optString("nat");

        email = item.optString("email");

    }

    //region Parcel implementation

    protected Contact(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        age = in.readString();
        thumbnail = in.readString();
        picture = in.readString();
        nationality = in.readString();
        email = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(age);
        parcel.writeString(thumbnail);
        parcel.writeString(picture);
        parcel.writeString(nationality);
        parcel.writeString(email);
    }

    //endregion

    //region Getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPicture() {
        return picture;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    //endregion

    private String getAge(String dateOfBirth) {
        if (dateOfBirth != null) {
            LocalDate date = LocalDate.parse(dateOfBirth, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDate now = new LocalDate();
            Years age = Years.yearsBetween(date, now);
            return String.valueOf(age.getYears());
        }
        return "";
    }



}
