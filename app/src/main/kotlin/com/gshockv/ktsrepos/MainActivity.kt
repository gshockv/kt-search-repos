package com.gshockv.ktsrepos

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

public class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    fun initUi() {
        val editText: EditText? = findViewById(R.id.edit_repo) as EditText
        val textLog: TextView? = findViewById(R.id.txt_log) as TextView

        val btnSearch: Button? = findViewById(R.id.btn_search) as Button
        btnSearch?.setOnClickListener {
            textLog?.clear()
            textLog?.setText("Coming soon...")
        }
    }
}

fun TextView.clear() = this.setText("")
