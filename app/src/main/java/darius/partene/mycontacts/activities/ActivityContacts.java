package darius.partene.mycontacts.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import darius.partene.mycontacts.R;
import darius.partene.mycontacts.adapters.ContactsAdapter;
import darius.partene.mycontacts.loaders.ContactsLoader;
import darius.partene.mycontacts.models.Contact;

public class ActivityContacts extends AppCompatActivity {

    private ContactsAdapter adapter;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        initRecyclerView();
        loadData();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactsAdapter(new ContactsAdapter.Listener() {
            @Override
            public void getMoreData() {
                loadData();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        page++;
        ContactsLoader loader = new ContactsLoader(new ContactsLoader.Listener() {
            @Override
            public void onComplete(ArrayList<Contact> contacts) {
                adapter.addContacts(contacts);
            }

            @Override
            public void onFail(String message) {
                Toast.makeText(ActivityContacts.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        loader.loadContacts(page);
    }
}
