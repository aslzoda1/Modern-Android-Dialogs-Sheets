package com.example.a04_02_2026.data

import android.content.Context
import android.util.Log

class ResultManager(context: Context) {

    private val prefs = context.getSharedPreferences("quiz_result", Context.MODE_PRIVATE)

    fun saveResult(score: Int, date: String) {
        try {
            prefs.edit()
                .putInt("last_score", score)
                .putString("last_date", date)
                .apply()
        } catch (e: Exception) {
            Log.e("ResultManager", "saveResult xatosi: ${e.message}")
        }
    }

    fun getLastScore(): Int {
        return try {
            prefs.getInt("last_score", -1)
        } catch (e: Exception) {
            Log.e("ResultManager", "getLastScore xatosi: ${e.message}")
            -1
        }
    }

    fun getLastDate(): String {
        return try {
            prefs.getString("last_date", "-") ?: "-"
        } catch (e: Exception) {
            Log.e("ResultManager", "getLastDate xatosi: ${e.message}")
            "-"
        }
    }

    fun clear() {
        try {
            prefs.edit().clear().apply()
        } catch (e: Exception) {
            Log.e("ResultManager", "clear xatosi: ${e.message}")
        }
    }
}
