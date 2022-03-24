package com.example.notpad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notepad.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var str = intent.getStringExtra("note")
        var position = intent.getIntExtra("position",-1)
        if(str !=null){
            binding.notetxtviewnew.setText(str)
        }


        binding.includedbottombarnew.addbutton.setOnClickListener {
            val xintent : Intent = Intent(this, MainActivity::class.java)
            xintent.putExtra("note",binding.notetxtviewnew.text.toString())
            if(position != -1){
                xintent.putExtra("position",position)
            }
            setResult(RESULT_OK,xintent)
            finish()
        }
    }
}