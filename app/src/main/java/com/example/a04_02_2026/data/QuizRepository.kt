package com.example.a04_02_2026.data

import com.example.a04_02_2026.model.QuizQuestion

object QuizRepository {

    fun getQuestions(): List<QuizQuestion> {

        return listOf(

            QuizQuestion(
                "Custom Dialog nimani anglatadi?",
                listOf(
                    "Oddiy Toast",
                    "Custom layout bilan yaratilgan dialog",
                    "Fragment turi",
                    "Navigation turi"
                ),
                1,
                "Dialog"
            ),

            QuizQuestion(
                "Custom Dialog qaysi class orqali yaratiladi?",
                listOf(
                    "AlertDialog.Builder",
                    "Intent",
                    "Service",
                    "BroadcastReceiver"
                ),
                0,
                "Dialog"
            ),

            QuizQuestion(
                "Dialog qachon ishlatiladi?",
                listOf(
                    "Muhim ma'lumot ko‘rsatish",
                    "Telefonni o‘chirish",
                    "Internet yoqish",
                    "App uninstall qilish"
                ),
                0,
                "Dialog"
            ),

            // ===== SNACKBAR =====
            QuizQuestion(
                "Snackbar Toastdan nimasi bilan farq qiladi?",
                listOf(
                    "Action button bor",
                    "Hech farqi yo‘q",
                    "Faqat qora rang",
                    "Faqat Settings da ishlaydi"
                ),
                0,
                "Snackbar"
            ),

            QuizQuestion(
                "Snackbar qayerda chiqadi?",
                listOf(
                    "Ekran pastida",
                    "Ekran tepasida",
                    "Status bar ichida",
                    "Drawer ichida"
                ),
                0,
                "Snackbar"
            ),

            QuizQuestion(
                "Snackbar action nima?",
                listOf(
                    "Bosilganda amal bajariladi",
                    "Rang o‘zgartiradi",
                    "Telefonni o‘chiradi",
                    "Internetni yoqadi"
                ),
                0,
                "Snackbar"
            ),

             QuizQuestion(
                "BottomSheet nima?",
                listOf(
                    "Pastdan chiqadigan panel",
                    "Dialog turi",
                    "Toast turi",
                    "Fragment container"
                ),
                0,
                "BottomSheet"
            ),

            QuizQuestion(
                "BottomSheet qaysi class orqali ishlaydi?",
                listOf(
                    "BottomSheetDialogFragment",
                    "Intent",
                    "Activity",
                    "Service"
                ),
                0,
                "BottomSheet"
            ),

            QuizQuestion(
                "BottomSheet qayerdan chiqadi?",
                listOf(
                    "Pastdan",
                    "Tepadadan",
                    "O‘rtadan",
                    "Drawer ichidan"
                ),
                0,
                "BottomSheet"
            ),

            QuizQuestion(
                "BottomSheet qachon ishlatiladi?",
                listOf(
                    "Qo‘shimcha actionlar uchun",
                    "Telefon restart",
                    "Internet o‘chirish",
                    "App uninstall"
                ),
                0,
                "BottomSheet"
            )
        )
    }
}
