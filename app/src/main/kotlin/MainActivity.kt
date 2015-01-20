package com.gshockv.ktsrepos;

import android.app.Activity

public class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(com.gshockv.ktsrepos.R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        getMenuInflater().inflate(com.gshockv.ktsrepos.R.menu.main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        val id = item.getItemId()

        if (id == com.gshockv.ktsrepos.R.id.action_settings) {
            return true
        }
        return super<Activity>.onOptionsItemSelected(item)
    }
}
