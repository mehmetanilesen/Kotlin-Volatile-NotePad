package com.example.notpad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R

class ViewAdapter (val context : Context,val notelist : ArrayList<String>,var itemClick : (position : Int,v : View)->Unit) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v  = LayoutInflater.from(context).inflate(R.layout.itemview,parent,false)
        return ViewHolder(v, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(notelist[position])
    }

    override fun getItemCount(): Int {
        return notelist.size
    }
}