package darius.partene.mycontacts.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewStub;

import darius.partene.mycontacts.R;

public class BaseActivity extends AppCompatActivity {

    /**
     * base activity to make global changes to activities (ex: change orientation or change transition animation)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
    }

    //same name as super for convenience
    public void setContentView(int layoutToInflate) {
        ViewStub stub = findViewById(R.id.view_stub);
        stub.setLayoutResource(layoutToInflate);
        stub.inflate();
    }
}
