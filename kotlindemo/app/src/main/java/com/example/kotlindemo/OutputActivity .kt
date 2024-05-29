package com.example.kotlindemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.databinding.ActivityOutputBinding

class OutputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputList = intent.getStringArrayListExtra("INPUT_LIST")
        inputList?.let {
            val displayText = it.joinToString(separator = "\n") { text -> "- $text" }
            binding.editTextTextMultiLine2.setText(displayText)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
