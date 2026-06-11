package com.example.sit305_3_1c

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class QuizActivity : Activity() {
    private val questions = QuizRepository.questions
    private lateinit var playerName: String
    private lateinit var answerButtons: List<Button>
    private lateinit var progressText: TextView
    private lateinit var quizProgressBar: ProgressBar
    private lateinit var welcomeText: TextView
    private lateinit var questionTitleText: TextView
    private lateinit var questionDetailText: TextView
    private lateinit var submitButton: Button
    private lateinit var nextButton: Button

    private var currentQuestionIndex = 0
    private var selectedAnswerIndex = -1
    private var score = 0
    private var hasSubmittedCurrentQuestion = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        playerName = intent.getStringExtra(MainActivity.EXTRA_PLAYER_NAME).orEmpty().ifBlank { "Player" }

        bindViews()
        setupAnswerButtons()
        setupActionButtons()
        loadQuestion()
    }

    private fun bindViews() {
        progressText = findViewById(R.id.progressText)
        quizProgressBar = findViewById(R.id.quizProgressBar)
        welcomeText = findViewById(R.id.welcomeText)
        questionTitleText = findViewById(R.id.questionTitleText)
        questionDetailText = findViewById(R.id.questionDetailText)
        submitButton = findViewById(R.id.submitButton)
        nextButton = findViewById(R.id.nextButton)
        answerButtons = listOf(
            findViewById(R.id.answerOneButton),
            findViewById(R.id.answerTwoButton),
            findViewById(R.id.answerThreeButton)
        )
    }

    private fun setupAnswerButtons() {
        answerButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (!hasSubmittedCurrentQuestion) {
                    selectedAnswerIndex = index
                    updateAnswerButtonBackgrounds()
                }
            }
        }
    }

    private fun setupActionButtons() {
        submitButton.setOnClickListener { submitCurrentAnswer() }
        nextButton.setOnClickListener { moveToNextQuestionOrResults() }
    }

    private fun loadQuestion() {
        val question = questions[currentQuestionIndex]
        selectedAnswerIndex = -1
        hasSubmittedCurrentQuestion = false

        welcomeText.text = "Welcome $playerName!"
        progressText.text = "${currentQuestionIndex + 1}/${questions.size}"
        quizProgressBar.max = questions.size
        quizProgressBar.progress = currentQuestionIndex + 1
        questionTitleText.text = question.title
        questionDetailText.text = question.questionText

        question.answerOptions.forEachIndexed { index, answer ->
            answerButtons[index].text = answer
            answerButtons[index].isEnabled = true
        }

        submitButton.visibility = View.VISIBLE
        submitButton.isEnabled = true
        nextButton.visibility = View.GONE
        nextButton.text = if (isFinalQuestion()) "View Results" else "Next"
        updateAnswerButtonBackgrounds()
    }

    private fun submitCurrentAnswer() {
        if (selectedAnswerIndex == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        hasSubmittedCurrentQuestion = true
        val question = questions[currentQuestionIndex]
        val isCorrect = selectedAnswerIndex == question.correctAnswerIndex
        if (isCorrect) {
            score += 1
        }

        submitButton.visibility = View.GONE
        nextButton.visibility = View.VISIBLE
        answerButtons.forEach { it.isEnabled = false }
        updateAnswerButtonBackgrounds()
    }

    private fun moveToNextQuestionOrResults() {
        if (isFinalQuestion()) {
            val intent = Intent(this, ResultsActivity::class.java).apply {
                putExtra(MainActivity.EXTRA_PLAYER_NAME, playerName)
                putExtra(EXTRA_SCORE, score)
                putExtra(EXTRA_TOTAL_QUESTIONS, questions.size)
            }
            startActivity(intent)
            finish()
        } else {
            currentQuestionIndex += 1
            loadQuestion()
        }
    }

    private fun updateAnswerButtonBackgrounds() {
        val correctAnswerIndex = questions[currentQuestionIndex].correctAnswerIndex
        answerButtons.forEachIndexed { index, button ->
            val background = when {
                hasSubmittedCurrentQuestion && index == correctAnswerIndex -> R.drawable.bg_answer_correct
                hasSubmittedCurrentQuestion && index == selectedAnswerIndex -> R.drawable.bg_answer_incorrect
                index == selectedAnswerIndex -> R.drawable.bg_answer_selected
                else -> R.drawable.bg_answer_neutral
            }
            val textColor = if (hasSubmittedCurrentQuestion && (index == correctAnswerIndex || index == selectedAnswerIndex)) {
                android.graphics.Color.WHITE
            } else {
                android.graphics.Color.rgb(31, 41, 55)
            }
            button.setBackgroundResource(background)
            button.setTextColor(textColor)
        }
    }

    private fun isFinalQuestion(): Boolean = currentQuestionIndex == questions.lastIndex

    companion object {
        const val EXTRA_SCORE = "com.example.sit305_3_1c.SCORE"
        const val EXTRA_TOTAL_QUESTIONS = "com.example.sit305_3_1c.TOTAL_QUESTIONS"
    }
}
