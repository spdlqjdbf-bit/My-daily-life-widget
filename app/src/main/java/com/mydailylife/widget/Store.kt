package com.mydailylife.widget

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Store {
    fun today(): String = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Date())
    fun list(context: Context): JSONArray = JSONArray(context.getSharedPreferences("records", Context.MODE_PRIVATE).getString("items", "[]"))
    fun add(context: Context, kind: String, content: String) {
        val a = list(context)
        a.put(JSONObject().put("date", today()).put("kind", kind).put("content", content).put("created", System.currentTimeMillis()))
        context.getSharedPreferences("records", Context.MODE_PRIVATE).edit().putString("items", a.toString()).apply()
        DailyWidget.refresh(context)
    }
    fun summary(context: Context): String {
        val a = list(context)
        val lines = mutableListOf<String>()
        for (i in a.length()-1 downTo 0) {
            val r = a.getJSONObject(i)
            if (r.optString("date") == today()) lines.add("${r.optString("kind")}: ${r.optString("content")}")
            if (lines.size >= 7) break
        }
        return if (lines.isEmpty()) "아직 기록이 없어요. 아래 버튼으로 시작해 봐요." else lines.joinToString("\n")
    }
}
