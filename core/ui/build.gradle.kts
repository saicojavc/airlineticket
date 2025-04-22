plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.com.dagger.hilt)
    alias(libs.plugins.kotlin.compose)
//    id("com.google.devtools.ksp")
}

android {
    namespace = "com.saico.airlineticket.ui"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    implementation(libs.transportation.consumer)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.foundation.layout.android)

    //Core
    coreLibraryDesugaring(libs.com.android.tools.desugar)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    //Icons -> https://fonts.google.com/icons
    api(libs.androidx.icons.extended)

    //Navigation jetpack compose
    api(libs.androidx.navigation.compose.ktx)
    api(libs.androidx.hilt.navigation.compose)

    //hilt
    implementation(libs.com.google.dagger.hilt.android)
//    ksp(libs.com.google.dagger.hilt.compiler)

    implementation(libs.androidx.constraintlayout.compose)

    api(libs.io.coil.kt)
}