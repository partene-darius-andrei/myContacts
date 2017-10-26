package darius.partene.mycontacts;

import android.app.Application;

import darius.partene.mycontacts.utils.RequestManager;

/**
 * Created by partened on 26.10.2017.
 */

public class MyContacts extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        RequestManager.init(this);
    }
}
