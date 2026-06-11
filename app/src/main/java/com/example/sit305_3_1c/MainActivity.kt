package com.example.sit305_3_1c

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val playerName = nameEditText.text.toString().trim()
            if (playerName.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuizActivity::class.java).apply {
                    putExtra(EXTRA_PLAYER_NAME, playerName)
                }
                startActivity(intent)
            }
        }
    }

    companion object {
        const val EXTRA_PLAYER_NAME = "com.example.sit305_3_1c.PLAYER_NAME"
    }
}
