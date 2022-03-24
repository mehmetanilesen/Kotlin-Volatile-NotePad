package com.example.notpad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notepad.R
import com.example.notepad.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var notelist : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        notelist = arrayListOf()

        val linearlayoutmanager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        binding.rcycview.layoutManager = linearlayoutmanager

        binding.rcycview.adapter = ViewAdapter(this,notelist, ::itemclick)

        binding.includedtopbar.buttonthreedot.setOnClickListener {
            dotwindow()
        }
        binding.includedbottombar.addbutton.setOnClickListener {
            val xintent : Intent = Intent(this, MainActivity2::class.java)
            startActivityForResult(xintent,0)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                if (data?.getStringExtra("note") != null) {
                    notelist.add(data.getStringExtra("note").toString())
                    binding.rcycview.adapter!!.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "THERE IS NO DATA", Toast.LENGTH_SHORT).show()
                }
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show()
            }
        }
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val position = data!!.getIntExtra("position",-1)
                if(position != -1){
                    notelist.set(position, data!!.getStringExtra("note").toString());
                    binding.rcycview.adapter!!.notifyItemChanged(position)
                }else {
                    Toast.makeText(this,"POSITION ERROR",Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

    fun dotwindow(){
        val popw = PopupMenu(this,binding.includedtopbar.buttonthreedot)
        val v = menuInflater.inflate(R.menu.dotmenu,popw.menu)

        popw.show()

        popw.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.aboutusbutton  ->{ Toast.makeText(this,"ABOUT US CLICKED",Toast.LENGTH_SHORT).show() }
                R.id.settingsbutton ->{ Toast.makeText(this,"SETTINGS CLICKED",Toast.LENGTH_SHORT).show() }
                R.id.closebutton    ->{ this.finish() }
            }
            true
        }

    }
    fun itemwindow( position : Int,v : View){

        val popw =PopupMenu(this,v)
        val v = menuInflater.inflate(R.menu.itemclickmenu,popw.menu)

        popw.show()

        popw.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.regulatebutton  ->{
                    val xintent = Intent(this, MainActivity2::class.java)
                    xintent.putExtra("note",notelist.get(position))
                    xintent.putExtra("position",position)
                    startActivityForResult(xintent,1)
                }
                R.id.deletebutton  ->{
                    notelist.removeAt(position)
                    Toast.makeText(this,"DELETED",Toast.LENGTH_SHORT).show()
                    binding.rcycview.adapter!!.notifyItemRemoved(position);
                }
            }
            true
        }
    }

    fun itemclick(position : Int,v : View){
        itemwindow(position,v)
    }

}