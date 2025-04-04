plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("maven-publish")
}

android {
    namespace = "me.austinng.austinsegmentedcontrol"
    compileSdk = 35

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.animation.core.android)
    implementation(libs.androidx.foundation.android)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.austinng17"
            artifactId = "austinsegmentedcontrol"
            version = "0.0.6"

            afterEvaluate {
                from(components["release"])
            }
        }
    }

    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

