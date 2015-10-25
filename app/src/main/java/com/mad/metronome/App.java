package com.mad.metronome;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by vinodkumar on 10/25/2015.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "T0zNIvsroRy6LRyCO3mT90txR5ADqz6nprz2rcs1", "EuNsGGr1rFccqfUeKOH7yX5lIsudzuSreL9PVmAb");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
