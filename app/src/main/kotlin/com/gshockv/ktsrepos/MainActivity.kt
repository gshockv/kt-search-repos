package com.gshockv.ktsrepos

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

public class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == R.id.action_settings) {
            return true
        }
        return super<Activity>.onOptionsItemSelected(item)
    }
}
