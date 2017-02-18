package com.codepath.simpletodo;

/**
 * Created by Saranu on 2/17/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LogoPageActivity extends Activity {
    protected int splashTime = 2000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                Intent i = new Intent(LogoPageActivity.this, MainActivity.class);
                startActivity(i);
            }
        }, splashTime);
    }
}