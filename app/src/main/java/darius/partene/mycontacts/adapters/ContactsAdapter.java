package darius.partene.mycontacts.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import darius.partene.mycontacts.R;
import darius.partene.mycontacts.activities.ActivityContactDetails;
import darius.partene.mycontacts.models.Contact;
import darius.partene.mycontacts.utils.CircleTransform;

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private Listener listener;

    public void addContacts(ArrayList<Contact> contacts) {
        this.contacts.addAll(contacts);
        notifyDataSetChanged();
    }

    public ContactsAdapter(Listener listener) {
        this.listener = listener;
    }

    private class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView name, age, nationality;
        ImageView thumbnail;
        View container;

        ContactViewHolder(View view) {
            super(view);
            container = view.findViewById(R.id.container);
            name = view.findViewById(R.id.name);
            age = view.findViewById(R.id.age);
            nationality = view.findViewById(R.id.nationality);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_contacts_row_item, parent, false);

        return new ContactViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ContactViewHolder contactViewHolder = (ContactViewHolder) holder;
        final Contact contact = contacts.get(position);
        final Context context = contactViewHolder.container.getContext();

        contactViewHolder.name.setText(context.getString(R.string.name_prefix) + " " + contact.getFullName());
        contactViewHolder.age.setText(context.getString(R.string.age_prefix) + " " + contact.getAge());
        contactViewHolder.nationality.setText(context.getString(R.string.nationality_prefix) + " " + contact.getNationality());


        contactViewHolder.container.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,
                        ActivityContactDetails.class);
                intent.putExtra("contact", contact);
                context.startActivity(intent);
            }
        });

        if (!TextUtils.isEmpty(contact.getThumbnail())) {
            Picasso.with(context).load(contact.getThumbnail()).transform(new CircleTransform())
                    .placeholder(R.drawable.ic_account_placeholder)
                    .into(contactViewHolder.thumbnail);
        }

        //reached the end of the list
        if (position == contacts.size() - 1) {
            listener.reachedBottom();
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public interface Listener {
        void reachedBottom();
    }

}