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
import android.view.inputmethod.EditorInfo
import android.os.IBinder
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.gshockv.ktsrepos.model.Repo
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView

public class MainActivity : Activity() {
    var editText: EditText by Delegates.notNull()
    var recyclerRepos: RecyclerView by Delegates.notNull()

    var repositories: List<Repo> by Delegates.observable(arrayListOf<Repo>(), {meta, oldItems, newItems ->
        // TODO: adapter.notifyDataSetChanged()
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    private fun initUi() {
        editText = findViewById(R.id.edit_repo) as EditText
        editText.setOnEditorActionListener { (textView, actionId, keyEvent) ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    searchRepos()
                }
            }
            hideSoftKeyboard(editText.getWindowToken())
            true
        }

        val btnSearch = findViewById(R.id.btn_search)
        btnSearch?.setOnClickListener {
            searchRepos()
        }

        recyclerRepos = findViewById(R.id.rv_repos) as RecyclerView

    }

    private fun searchRepos() {
        val q = editText?.value()
        val query = if (q.isEmpty()) "kotlin" else q

        val callback = object : Callback<ResponseEnvelope> {
            override fun success(t: ResponseEnvelope?, response: Response?) {
                if (t == null) return

            }

            override fun failure(error: RetrofitError?) {
            }
        }
        GithubApiService.searchRepos(query, callback)
    }

    /**
     * repositories list adapter
     */
    inner class ReposAdapter: RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): RepoViewHolder? {
            throw UnsupportedOperationException()
        }

        override fun onBindViewHolder(p0: RepoViewHolder?, p1: Int) {
            throw UnsupportedOperationException()
        }

        override fun getItemCount() = repositories.size()

        /**
         * repo viewholder
         */
        inner class RepoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        }
    }
}

fun TextView.clear() = this.setText("")
fun TextView.value() = this.getText().toString()

fun Activity.hideSoftKeyboard(windowToken: IBinder) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromInputMethod(windowToken, 0)
}
