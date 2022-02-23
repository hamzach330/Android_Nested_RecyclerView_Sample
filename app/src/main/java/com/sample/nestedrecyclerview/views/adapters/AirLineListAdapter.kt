package com.sample.nestedrecyclerview.views.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.nestedrecyclerview.R
import com.sample.nestedrecyclerview.data.AirlineInfo
import kotlinx.android.synthetic.main.airline_list_adapter.view.*


class AirLineListAdapter(private val airlineList: List<AirlineInfo>) :
    RecyclerView.Adapter<AirLineListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.airline_list_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(airlineList[position])
    }


    override fun getItemCount(): Int {
        return airlineList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(airlineInfo: AirlineInfo) {
            itemView.tv_airline_title.text = airlineInfo.name
            itemView.iv_airline_logo.setImageResource(airlineInfo.logoId)
        }

        init {
            itemView.setOnClickListener {
                val cntxt = itemView.context
                openBrowser(cntxt)

            }
        }

        fun openBrowser(context:Context)
        {
            val defaultBrowser =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data = Uri.parse(airlineList[adapterPosition].site)
            context.startActivity(defaultBrowser)
        }
    }

}