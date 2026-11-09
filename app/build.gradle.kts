plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.mydailylife.widget"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mydailylife.widget"
        minSdk = 26
        targetSdk = 35
        versionCode = 2
        versionName = "1.1"
    }

    signingConfigs {
        create("release") {
            val keystorePath =
                System.getenv("KEYSTORE_PATH")
            val keystorePassword =
                System.getenv("KEYSTORE_PASSWORD")

            if (
                !keystorePath.isNullOrBlank() &&
                !keystorePassword.isNullOrBlank()
            ) {
                storeFile = file(keystorePath)
                storePassword = keystorePassword
                keyAlias = "mydailylife"
                keyPassword = keystorePassword
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig =
                signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility =
            JavaVersion.VERSION_17
        targetCompatibility =
            JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}
