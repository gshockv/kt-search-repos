package com.gshockv.ktsrepos

import android.content.Context
import com.gshockv.ktsrepos.model.Repo
import android.view.View
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import kotlin.properties.Delegates
import java.util.ArrayList

/**
 * repositories list adapter
 */

class RepositoriesAdapter(val items: ArrayList<Repo>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup?, viewType: Int): ViewHolder? {
        val root = LayoutInflater.from(container?.getContext())
                .inflate(R.layout.list_item_repo, container, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(vh: ViewHolder?, position: Int) {
        val r = items[position]
        vh?.textName!!.setText(r.name)
    }

    override fun getItemCount() = items.size()
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView: View) {
    val textName = itemView.findViewById(R.id.txt_name) as TextView
}
