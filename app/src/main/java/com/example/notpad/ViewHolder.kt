package com.example.notpad

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R

class ViewHolder (itemView : View, var itemClick : (position : Int,v : View) -> Unit) :  RecyclerView.ViewHolder(itemView) {

    val notetxt : TextView

    init {
        notetxt = itemView.findViewById(R.id.notetxtview)

        itemView.setOnClickListener {
            itemClick(adapterPosition,it)
        }
    }

    fun bindData(str : String){
        notetxt.text = str
    }
}