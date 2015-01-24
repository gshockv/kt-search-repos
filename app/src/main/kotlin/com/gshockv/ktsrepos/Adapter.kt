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
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * repositories list adapter
 */

class RepositoriesAdapter(val ctx: Context, val items: ArrayList<Repo>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup?, viewType: Int): ViewHolder? {
        val root = LayoutInflater.from(container?.getContext())
                .inflate(R.layout.list_item_repo, container, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(vh: ViewHolder?, position: Int) {
        val r = items[position]
        vh?.textName!!.setText(r.name)
        vh?.textUpdated!!.setText(r.updated_at)
        vh?.textHtmlUrl!!.setText(r.html_url)
        Picasso.with(ctx).load(r.owner.avatar_url).resize(80, 80).centerCrop().into(vh?.imageAvatar)
    }

    override fun getItemCount() = items.size()
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView: View) {
    val textName = itemView.findViewById(R.id.txt_name) as TextView
    val textUpdated = itemView.findViewById(R.id.txt_updated) as TextView
    val textHtmlUrl = itemView.findViewById(R.id.txt_html_url) as TextView
    val imageAvatar = itemView.findViewById(R.id.img_avatar) as ImageView
}
