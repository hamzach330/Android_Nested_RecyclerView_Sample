package com.sample.nestedrecyclerview.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kayak.internexercise.R
import com.sample.nestedrecyclerview.data.AirlineInfo
import kotlinx.android.synthetic.main.main_headerlist_adapter.view.*


class MainListAdapter(private val airlineHeaderList: HashMap<String, List<AirlineInfo>> ):
    RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_headerlist_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainListAdapter.ViewHolder, position: Int) {
        val key = airlineHeaderList.keys.elementAt(position) // Get key by index.
        holder.bindItems(key,airlineHeaderList.values.elementAt(position).sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name }))
    }

    override fun getItemCount(): Int {
        return airlineHeaderList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(key: String,childList : List<AirlineInfo>) {
            itemView.tv_header.text = key
            val adapter = AirLineListAdapter(childList)
            itemView.rv_child.adapter = adapter
        }

    }

}