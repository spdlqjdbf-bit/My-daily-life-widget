package com.mydailylife.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class DailyWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) { ids.forEach { update(context, manager, it) } }
    companion object {
        fun refresh(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            manager.getAppWidgetIds(ComponentName(context, DailyWidget::class.java)).forEach { update(context, manager, it) }
        }
        private fun update(context: Context, manager: AppWidgetManager, id: Int) {
            val views = RemoteViews(context.packageName, R.layout.widget_daily)
            views.setTextViewText(R.id.date, Store.today())
            views.setTextViewText(R.id.summary, Store.summary(context))
            val actions = listOf(R.id.activity to "활동", R.id.food to "음식", R.id.health to "건강", R.id.routine to "루틴", R.id.diary to "일기", R.id.tag to "태그")
            actions.forEachIndexed { index, pair ->
                val intent = Intent(context, QuickEntryActivity::class.java).putExtra("kind", pair.second)
                val pending = PendingIntent.getActivity(context, 100 + index, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                views.setOnClickPendingIntent(pair.first, pending)
            }
            views.setOnClickPendingIntent(R.id.title, PendingIntent.getActivity(context, 200, Intent(context, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE))
            manager.updateAppWidget(id, views)
        }
    }
}
