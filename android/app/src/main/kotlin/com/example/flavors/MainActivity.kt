package com.example.flavors 

import android.os.Bundle
import com.google.firebase.appdistribution.FirebaseAppDistribution
import com.google.firebase.appdistribution.InterruptionLevel
import io.flutter.embedding.android.FlutterActivity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build

class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // إنشاء قناة الإشعار بالصوت المخصص
        createCustomNotificationChannel()
        FirebaseAppDistribution.getInstance()
                .showFeedbackNotification(
                        "شاركنا رأيك لتحسين التطبيق",
                        InterruptionLevel.HIGH
                )
    }
    override fun onDestroy() {
        super.onDestroy()
        FirebaseAppDistribution.getInstance().cancelFeedbackNotification()
    }

    fun createCustomNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "feedback_channel"
            val channelName = "Feedback Notifications"
            val soundUri = Uri.parse("android.resource://com.example.flavors/raw/feedback_sound")

            val attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
                setSound(soundUri, attributes)
                enableVibration(true)
                description = "Channel for custom feedback notification sound"
            }

            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

}