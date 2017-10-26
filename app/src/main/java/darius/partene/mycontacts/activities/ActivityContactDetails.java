package darius.partene.mycontacts.activities;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import darius.partene.mycontacts.R;
import darius.partene.mycontacts.models.Contact;
import darius.partene.mycontacts.utils.CircleTransform;

public class ActivityContactDetails extends ToolbarActivity {

    //ui variables
    private TextView firstName, lastName, age, nationality, email;
    private ImageView image;

    //model which populates screen
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        initViews();
        contact = getIntent().getParcelableExtra("contact");
        setData();

    }

    private void initViews() {
        image = findViewById(R.id.image);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        age = findViewById(R.id.age);
        nationality = findViewById(R.id.nationality);
        email = findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", contact.getEmail(), null));
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    //update the screen with the information from the model
    private void setData() {

        if (contact == null) {
            return;
        }

        setTitle(contact.getFullName());
        Picasso.with(this).load(contact.getPicture()).transform(new CircleTransform()).placeholder(R.drawable.ic_account_placeholder).into(image);
        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());
        age.setText(contact.getAge());
        nationality.setText(contact.getNationality());
        email.setText(contact.getEmail());

    }
}
