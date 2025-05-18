package com.example.flavors

import android.os.Bundle
import com.google.firebase.appdistribution.FirebaseAppDistribution
import com.google.firebase.appdistribution.InterruptionLevel
import io.flutter.embedding.android.FlutterActivity

class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseAppDistribution.getInstance().showFeedbackNotification(
            "331918924936", // استبدل هذا برقم مشروع Firebase الخاص بك
            InterruptionLevel.HIGH
        )
    }
}