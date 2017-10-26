package darius.partene.mycontacts.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import darius.partene.mycontacts.R;
import darius.partene.mycontacts.adapters.ContactsAdapter;
import darius.partene.mycontacts.loaders.ContactsLoader;
import darius.partene.mycontacts.models.Contact;

public class ActivityContacts extends ToolbarActivity implements ContactsAdapter.Listener, ContactsLoader.Listener {

    private ContactsAdapter adapter;
    private ContactsLoader loader;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setTitle(R.string.app_name);
        hideBackIcon();
        initRecyclerView();
        initLoader();
        getData();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactsAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initLoader() {
        loader = new ContactsLoader(this);
    }

    private void getData() {
        showLoading();
        loader.loadContacts(page++);
    }

    //callback from adapter when the user scrolled to the bottom
    @Override
    public void reachedBottom() {
        getData();
    }

    //finished loading contacts
    @Override
    public void onComplete(ArrayList<Contact> contacts) {
        hideLoading();
        adapter.addContacts(contacts);
    }

    //somethng went wrong
    @Override
    public void onFail(String message) {
        hideLoading();
        Toast.makeText(ActivityContacts.this, message, Toast.LENGTH_SHORT).show();
    }
}
