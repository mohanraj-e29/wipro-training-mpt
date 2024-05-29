package com.example.kotlindemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val inputList = mutableListOf<String>()  // List to store the inputs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val inputText = binding.editTextText2.text.toString()
            if (inputText.isNotEmpty()) {
                inputList.add(inputText)
                binding.editTextText2.text.clear()  // Clear input field after adding
                navigateToOutputActivity()
            }
        }
    }

    private fun navigateToOutputActivity() {
        val intent = Intent(this, OutputActivity::class.java)
        intent.putStringArrayListExtra("INPUT_LIST", ArrayList(inputList))
        startActivity(intent)
    }
}
