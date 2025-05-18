package com.example.flavors 

import android.os.Bundle
import com.google.firebase.appdistribution.FirebaseAppDistribution
import com.google.firebase.appdistribution.InterruptionLevel
import io.flutter.embedding.android.FlutterActivity

class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

}