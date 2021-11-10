package com.yohannes.dev.app.anarchistcookbook.ui

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.yohannes.dev.app.anarchistcookbook.DetailActivity
import com.yohannes.dev.app.anarchistcookbook.R
import com.yohannes.dev.app.anarchistcookbook.data.Anarchy

class ItemAdapter (private val mList: List<Anarchy>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val anarchy = mList[position];
        holder.textView.text = anarchy.title
        holder.itemView.setOnClickListener {
            val context = holder.textView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("index", anarchy.title.take(3))
            intent.putExtra("data", anarchy.data)
            Log.e("data", anarchy.data)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.item_title)
    }
}