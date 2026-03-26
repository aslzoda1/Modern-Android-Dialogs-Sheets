package com.example.a04_02_2026

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class DialogFragment : Fragment(R.layout.fragment_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnPractice = view.findViewById<MaterialButton>(R.id.btnPracticeDialog)

        btnPractice.setOnClickListener {
            showCustomDialog()
        }
    }

    private fun showCustomDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom, null)

        val etName = dialogView.findViewById<EditText>(R.id.etName)
        val btnOk = dialogView.findViewById<View>(R.id.btnOk)
        val btnCancel = dialogView.findViewById<View>(R.id.btnCancel)

        val dialog = AlertDialog.Builder(requireContext(), R.style.IOSDialogTheme)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialog.show()

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnOk.setOnClickListener {
            val name = etName.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = "Ismni kiriting"
            } else {
                Toast.makeText(
                    requireContext(),
                    "Salom $name 👋",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }
        }
    }

}
