package com.mydailylife.widget

import android.app.Activity
import android.os.Bundle
import android.widget.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(24,24,24,24) }
        root.addView(TextView(this).apply { text = "MY DAILY LIFE · 위젯 베타"; textSize = 22f })
        root.addView(TextView(this).apply { text = "홈 화면을 길게 누른 뒤 위젯 → MY DAILY LIFE를 추가해 줘.\n\n현재는 위젯의 기록을 기기에 따로 저장하는 첫 단계야. Netlify 웹앱과 자동 동기화되지는 않아." })
        val items = TextView(this).apply { text = Store.summary(this@MainActivity); textSize = 16f; setPadding(0,20,0,20) }
        root.addView(items)
        val scroll = ScrollView(this).apply { addView(root) }
        setContentView(scroll)
    }
}
