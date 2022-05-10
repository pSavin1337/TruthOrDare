package com.lospollos.truthordare.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lospollos.truthordare.R

class PlayersRecyclerViewAdapter(var playersCount: Int) :
    RecyclerView.Adapter<PlayersRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: EditText? = null

        init {
            nameTextView = itemView.findViewById(R.id.players_name_text_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.players_recycler_view_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.nameTextView?.text = namesList[position]
    }

    override fun getItemCount(): Int = playersCount

}