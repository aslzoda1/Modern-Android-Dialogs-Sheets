package com.example.a04_02_2026

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a04_02_2026.data.ResultManager
import com.google.android.material.button.MaterialButton

class ResultFragment : Fragment(R.layout.fragment_result) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvScore = view.findViewById<TextView>(R.id.tvScore)
        val progress = view.findViewById<ProgressBar>(R.id.progressScore)
        val tvMotivation = view.findViewById<TextView>(R.id.tvMotivation)
        val tvDate = view.findViewById<TextView>(R.id.tvDate)
        val btnRetry = view.findViewById<MaterialButton>(R.id.btnRetry)

        val manager = ResultManager(requireContext())

        val score = manager.getLastScore()
        val date = manager.getLastDate()

        if (score == -1) {
            tvScore.text = "Natija yo‘q"
            progress.progress = 0
            tvDate.text = "Hali quiz ishlanmagan"
            tvMotivation.text = "Boshlash uchun quizni ishlang 💪"
        } else {
            tvScore.text = "$score / 10"
            progress.progress = score.coerceIn(0, 10)
            tvDate.text = "Last attempt: $date"

            tvMotivation.text = when {
                score >= 9 -> "Ajoyib! Siz professional darajadasiz! 🔥"
                score >= 7 -> "Juda yaxshi! Ozgina mashq yana! 💪"
                score >= 5 -> "Yaxshi! Yana practice qiling! 🙂"
                else -> "Boshlanish uchun yaxshi! O‘rganishda davom eting! 📘"
            }
        }

        btnRetry.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host, QuizFragment())
                .commit()
        }
    }
}
