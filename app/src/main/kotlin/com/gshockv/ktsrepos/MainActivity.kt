package com.gshockv.ktsrepos

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
import android.view.inputmethod.EditorInfo
import android.os.IBinder
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.gshockv.ktsrepos.model.Repo
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.app.ActionBarActivity
import java.util.ArrayList
import android.app.ProgressDialog
import android.app.AlertDialog

public class MainActivity : ActionBarActivity() {
    var progressDialog: ProgressDialog by Delegates.notNull()
    var editText: EditText by Delegates.notNull()
    var recyclerRepos: RecyclerView by Delegates.notNull()

    val repositories: ArrayList<Repo> by Delegates.observable(arrayListOf<Repo>(), {meta, oldItems, newItems ->
        adapter.notifyDataSetChanged()
    })
    val adapter = RepositoriesAdapter(items = repositories)

    override fun onCreate(savedInstanceState: Bundle?) {
        super<ActionBarActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    private fun initUi() {
        editText = findViewById(R.id.edit_repo) as EditText
        editText.setOnEditorActionListener { (textView, actionId, keyEvent) ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchRepos()
            }
            hideSoftKeyboard(editText.getWindowToken())
            true
        }
        val btnSearch = findViewById(R.id.btn_search)
        btnSearch?.setOnClickListener {
            searchRepos()
        }
        initRecyclerView()
        initProgressDialog()
    }

    private fun initRecyclerView() {
        recyclerRepos = findViewById(R.id.rv_repos) as RecyclerView
        val lm = LinearLayoutManager(getApplicationContext())
        lm.setOrientation(LinearLayoutManager.VERTICAL)
        recyclerRepos.setLayoutManager(lm)
        recyclerRepos.setAdapter(adapter)
    }

    private fun searchRepos() {
        showProgressDialog()

        repositories.clear()

        val q = editText?.value()
        val query = if (q.isEmpty()) "kotlin" else q

        val callback = object : Callback<ResponseEnvelope> {
            override fun success(t: ResponseEnvelope?, response: Response?) {
                dismissProgressDialog()

                if (t?.items!!.isEmpty()) {
                    showEmptyResultDialog()
                    return
                }

                repositories.addAll(t?.items)
                adapter.notifyDataSetChanged()
            }

            override fun failure(error: RetrofitError?) {
                dismissProgressDialog()
            }
        }
        GithubApiService.searchRepos(query, callback)
    }

    private fun showProgressDialog() {
        progressDialog.show()
    }

    private fun dismissProgressDialog() {
        progressDialog.dismiss()
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
        with (progressDialog) {
            setTitle("")
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            setMessage("Loading...")
            setCancelable(false)
            setIndeterminate(true)
            setCanceledOnTouchOutside(false)
        }
    }

    private fun showEmptyResultDialog() {
        val alertBuilder = AlertDialog.Builder(this)
        with (alertBuilder) {
            setTitle("No results")
            setMessage("Repositories with given criteria not found")
            setPositiveButton("OK") {di, which -> }
        }
        alertBuilder.create().show()
    }
}

// extensions
fun TextView.clear() = this.setText("")
fun TextView.value() = this.getText().toString()

fun ActionBarActivity.hideSoftKeyboard(windowToken: IBinder) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
