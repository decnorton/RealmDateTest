package com.decnorton.realmdatetest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRealm = Realm.getInstance(this);

        testDate(1, 1900, 1, 1);
        testDate(2, 1901, 1, 1);
        testDate(3, 1902, 1, 1);
        testDate(4, 1903, 1, 1);
        testDate(5, 1904, 1, 1);
        testDate(6, 1905, 1, 1);
        testDate(7, 1910, 1, 1);
    }

    private void testDate(long id, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        // Create the Date object
        Date date = cal.getTime();

        Log.i(TAG, "[testDate:" + id +"] Testing date: " + date);

        mRealm.beginTransaction();

        // Create the object
        Test object = mRealm.createObject(Test.class);
        object.setId(id);
        object.setDate(date);

        Log.i(TAG, "[testDate:" + id + "] Before commit: " + object.getDate());

        mRealm.commitTransaction();

        object = mRealm.where(Test.class).equalTo("id", id).findFirst();

        Log.i(TAG, "[testDate:" + id + "] After commit: " + object.getDate());

        mRealm.beginTransaction();
        // Clear the table ready for the next test
        mRealm.clear(Test.class);
        mRealm.commitTransaction();

        Log.i(TAG, "------------");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
