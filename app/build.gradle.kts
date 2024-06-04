plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // adding hilt dependencies from https://developer.android.com/training/dependency-injection/hilt-android?hl=it
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    // add the Google services Gradle plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "com.curiosity"
    // update compileSdk to 34 to have the dependencies up to date
    compileSdk = 34

    defaultConfig {
        applicationId = "com.curiosity"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        // hilt needs functionality of Java18
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("com.google.guava:guava:31.1-android")
    implementation("androidx.hilt:hilt-common:1.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // adding hilt dependencies from https://developer.android.com/training/dependency-injection/hilt-android?hl=it
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("com.google.dagger:hilt-android:2.49")
    implementation("androidx.hilt:hilt-work:1.2.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // adding compose navigation dependencies from https://developer.android.com/develop/ui/compose/navigation?hl=it
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    // Firebase Auth
    implementation("com.google.android.gms:play-services-auth:21.1.1")
    implementation("com.google.firebase:firebase-auth")
    // Firebase Firestore
    implementation("com.google.firebase:firebase-firestore")
    // Firebase Cloud Storage library
    implementation("com.google.firebase:firebase-storage")

    // Extended Icons
    implementation("androidx.compose.material:material-icons-extended:1.6.7")

    // CSV Reader
    implementation("com.opencsv:opencsv:5.5.2")
}

// adding hilt dependencies from https://developer.android.com/training/dependency-injection/hilt-android?hl=it
kapt {
    correctErrorTypes = true
}