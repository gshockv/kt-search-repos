package com.gshockv.ktsrepos

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import com.gshockv.ktsrepos.io.GithubApiService
import com.gshockv.ktsrepos.model.ResponseEnvelope
import retrofit.Callback
import retrofit.client.Response
import retrofit.RetrofitError
import android.widget.Toast
import android.text.TextUtils
import kotlin.properties.Delegates
import android.view.Window

public class MainActivity : Activity() {

    var textLog: TextView by Delegates.notNull()
    var editText: EditText by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    private fun initUi() {
        editText = findViewById(R.id.edit_repo) as EditText
        textLog = findViewById(R.id.txt_log) as TextView

        val btnSearch = findViewById(R.id.btn_search)
        btnSearch?.setOnClickListener {
            searchRepos()
        }
    }

    private fun searchRepos() {
        val q = editText?.value()
        val query = if (q.isEmpty()) "kotlin" else q
        GithubApiService.searchRepos(query, callback = ResponseCallback())
    }

    inner class ResponseCallback : Callback<ResponseEnvelope> {
        override fun success(t: ResponseEnvelope?, response: Response?) {
            if (t == null) return
            writeLogMessage("Found: ${t?.items?.size()} repositories\n")
            for ((name) in t.items) {
                writeLogMessage("$name")
            }
        }

        override fun failure(error: RetrofitError?) {
        }
    }

    private fun writeLogMessage(msg: String) {
        textLog.setText(textLog.value() + "\n" + msg)
    }
}

fun TextView.clear() = this.setText("")
fun TextView.value() = this.getText().toString()
