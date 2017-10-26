package darius.partene.mycontacts.activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import darius.partene.mycontacts.R;
import darius.partene.mycontacts.models.Contact;
import darius.partene.mycontacts.utils.CircleTransform;

public class ActivityContactDetails extends ToolbarActivity {

    private Contact contact;

    private TextView firstName, lastName, age, nationality;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        initViews();
        setData();

    }

    private void initViews() {
        image = findViewById(R.id.image);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        age = findViewById(R.id.age);
        nationality = findViewById(R.id.nationality);
    }

    private void setData() {
        contact = getIntent().getParcelableExtra("contact");
        if (contact == null) {
            return;
        }

        Picasso.with(this).load(contact.getPicture()).transform(new CircleTransform()).placeholder(R.drawable.ic_account_placeholder).into(image);
        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());
        age.setText(contact.getAge());
        nationality.setText(contact.getNationality());

    }
}
