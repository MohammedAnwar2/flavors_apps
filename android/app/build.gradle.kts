plugins {
    id("com.android.application")
    // START: FlutterFire Configuration
    id("com.google.gms.google-services")
    // END: FlutterFire Configuration
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
   id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.example.flavors"
    compileSdk = 34
    ndkVersion = "27.0.12077973"
    // buildToolsVersion = "34.0.0"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.example.flavors"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = 21
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    buildTypes {
        release {
            // TODO: Add your own signing config for the release build.
            // Signing with the debug keys for now, so `flutter run --release` works.
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    flavorDimensions += "default"
    productFlavors {
        create("development") {
            dimension = "default"
            resValue(
                type = "string",
                name = "app_name",
                value = "Flavors development")
            applicationIdSuffix = ".development"
        }
        create("production") {
            dimension = "default"
            resValue(
                type = "string",
                name = "app_name",
                value = "Flavors production")
        }
    }
}

flutter {
    source = "../.."
}
dependencies {
    // Add the Firebase App Distribution API library to all flavors (safe for Google Play compliance)
    implementation("com.google.firebase:firebase-appdistribution-api-ktx:16.0.0-beta15")

    // Add the full Firebase App Distribution library to the 'production' flavor only (for pre-release testing)
    "productionImplementation"("com.google.firebase:firebase-appdistribution:16.0.0-beta15")
    
    // Note: To add the dependency to another specific flavor (e.g., 'development'), use:
    // "developmentImplementation"("com.google.firebase:firebase-appdistribution:16.0.0-beta15")
}