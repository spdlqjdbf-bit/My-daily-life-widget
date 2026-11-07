package com.mydailylife.widget

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.*

class QuickEntryActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val kind = intent.getStringExtra("kind") ?: "활동"
        val layout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(32,32,32,24) }
        layout.addView(TextView(this).apply { text = "$kind 기록"; textSize = 22f; gravity = Gravity.CENTER_VERTICAL })
        val hint = when(kind) {
            "활동" -> "예: 09:00~10:30 영어 (시작·종료 시간 직접 입력)"
            "음식" -> "예: 12:30 김치찌개"
            "건강" -> "예: 컨디션 좋음 / 체중 51kg / 생리 O"
            "루틴" -> "예: 독서 20분 완료"
            "일기" -> "오늘의 일기"
            else -> "예: #야구 #독서"
        }
        val input = EditText(this).apply { this.hint = hint; minLines = 3; gravity = Gravity.TOP; inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE }
        layout.addView(input)
        layout.addView(Button(this).apply { text = "저장"; setOnClickListener {
            val content = input.text.toString().trim()
            if(content.isBlank()) { input.error = "내용을 입력해 줘"; return@setOnClickListener }
            Store.add(this@QuickEntryActivity, kind, content)
            Toast.makeText(this@QuickEntryActivity, "기록했어", Toast.LENGTH_SHORT).show(); finish()
        } })
        setContentView(layout)
    }
}
