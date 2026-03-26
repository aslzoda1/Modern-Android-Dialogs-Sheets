package com.example.a04_02_2026

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.a04_02_2026.data.QuizRepository
import com.example.a04_02_2026.data.ResultManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private lateinit var txtQuestion: TextView
    private lateinit var btnNext: MaterialButton
    private lateinit var progressBar: LinearProgressIndicator
    private lateinit var options: List<MaterialButton>

    private val questions = QuizRepository.getQuestions()
    private var currentIndex = 0
    private var score = 0
    private var selectedIndex = -1

    private lateinit var resultManager: ResultManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtQuestion = view.findViewById(R.id.txtQuestion)
        btnNext = view.findViewById(R.id.btnNext)
        progressBar = view.findViewById(R.id.progressBar)

        options = listOf(
            view.findViewById(R.id.btnOption1),
            view.findViewById(R.id.btnOption2),
            view.findViewById(R.id.btnOption3)
        )

        resultManager = ResultManager(requireContext())

        loadQuestion()

        options.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (selectedIndex == -1) {
                    selectedIndex = index
                    showFeedback()
                }
            }
        }

        btnNext.setOnClickListener {
            if (selectedIndex == -1) return@setOnClickListener

            if (selectedIndex == questions[currentIndex].correctIndex) score++

            currentIndex++

            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                finishQuiz()
            }
        }
    }

    private fun loadQuestion() {
        selectedIndex = -1
        val question = questions[currentIndex]
        txtQuestion.text = question.question

        options.forEachIndexed { index, button ->
            button.text = question.variants[index]
            button.backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.ios_card_option_bg)
            button.strokeColor =
                ContextCompat.getColorStateList(requireContext(), R.color.ios_gray)
            button.strokeWidth = 2
            button.isEnabled = true
        }

        // Progress update
        progressBar.progress = ((currentIndex.toFloat() / questions.size) * 100).toInt()
    }

    private fun showFeedback() {
        val correctIndex = questions[currentIndex].correctIndex
        options.forEachIndexed { index, button ->
            when (index) {
                correctIndex -> button.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.ios_green)
                selectedIndex -> if (selectedIndex != correctIndex)
                    button.backgroundTintList =
                        ContextCompat.getColorStateList(requireContext(), R.color.ios_red)
            }
            button.isEnabled = false
        }
    }

    private fun finishQuiz() {
        val date =
            SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date())
        resultManager.saveResult(score, date)

        txtQuestion.text = "Quiz tugadi! Siz $score/${questions.size} to‘g‘ri javob berdingiz ✅"
        btnNext.isEnabled = false
        options.forEach { it.isEnabled = false }
    }
}
