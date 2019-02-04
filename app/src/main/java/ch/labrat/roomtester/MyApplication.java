package ch.labrat.roomtester;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import java.text.DateFormat;

public class MyApplication extends Application {


    public static String databaseName = "anima_database";
    private static DateFormat dateFormat;

    public static MyApplication singleton;
    public static MyApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

        JodaTimeAndroid.init(this);
        singleton = this;
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

}
