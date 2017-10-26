package darius.partene.mycontacts.loaders;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import darius.partene.mycontacts.models.Contact;
import darius.partene.mycontacts.utils.JsonRequest;
import darius.partene.mycontacts.utils.RequestManager;

/**
 * Created by partened on 26.10.2017.
 */

public class ContactsLoader {

    private String TAG = "ContactsLoader";
    private String url = "https://randomuser.me/api?results=20&page=";
    private Listener listener;

    public ContactsLoader(Listener listener) {
        this.listener = listener;
    }

    public void loadContacts(int page) {

        JsonRequest request = new JsonRequest(url + page, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        // Access the RequestQueue through your singleton class.
        RequestManager.addRequest(request, TAG);

    }

    private void parseResponse(JSONObject item) {
        try {
            ArrayList<Contact> contacts = new ArrayList<>();
            JSONArray jsonArray = item.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                contacts.add(new Contact(jsonArray.getJSONObject(i)));
            }

            listener.onComplete(contacts);
        } catch (JSONException e) {
            listener.onFail("json exception");
            e.printStackTrace();
        }
    }

    public interface Listener {
        void onComplete(ArrayList<Contact> contacts);

        void onFail(String message);
    }
}
