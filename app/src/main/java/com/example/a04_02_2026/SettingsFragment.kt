package com.example.a04_02_2026

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnClear = view.findViewById<Button>(R.id.btnClearAll)

        // -------------------------------
        // BACK BUTTON
        // -------------------------------
        try {
            val activity = requireActivity()
            activity.findViewById<View>(R.id.backRight)?.setOnClickListener {
                safeAction { activity.onBackPressedDispatcher.onBackPressed() }
            }
        } catch (_: Exception) {}

        // -------------------------------
        // CLEAR ALL TASKS
        // -------------------------------
        btnClear.setOnClickListener {
            safeAction {
                AlertDialog.Builder(requireContext())
                    .setTitle("Clear all tasks?")
                    .setMessage("This will permanently delete all saved tasks.")
                    .setPositiveButton("Clear") { _, _ -> clearTasks() }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        }
    }

    private fun clearTasks() {
        safeAction {
            requireContext()
                .getSharedPreferences("tasks_pref", Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply()

            Toast.makeText(requireContext(), "All tasks cleared", Toast.LENGTH_SHORT).show()
        }
    }

    // -------------------------------
    // HELPER: Safe try-catch wrapper
    // -------------------------------
    private fun safeAction(action: () -> Unit) {
        try {
            action()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
        }
    }
}
