package com.example.sit305_3_1c

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val playerName = intent.getStringExtra(MainActivity.EXTRA_PLAYER_NAME).orEmpty().ifBlank { "Player" }
        val score = intent.getIntExtra(QuizActivity.EXTRA_SCORE, 0)
        val totalQuestions = intent.getIntExtra(QuizActivity.EXTRA_TOTAL_QUESTIONS, QuizRepository.questions.size)

        findViewById<TextView>(R.id.congratulationsText).text = "Congratulations $playerName!"
        findViewById<TextView>(R.id.scoreValueText).text = "$score/$totalQuestions"

        findViewById<Button>(R.id.takeNewQuizButton).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(MainActivity.EXTRA_PLAYER_NAME, playerName)
            }
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.finishButton).setOnClickListener {
            finishAffinity()
        }
    }
}
