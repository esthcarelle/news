plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mine.news"
    compileSdk = 34

    buildFeatures { buildConfig = true }

    defaultConfig {
        applicationId = "com.mine.news"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "API_KEY", project.property("API_KEY").toString())
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
    flavorDimensions += listOf("news")
    productFlavors {
        create("bbc") {
            dimension = "news"
            buildConfigField(type = "String", name = "SOURCE", value = "\"bbc-news\"")
            buildConfigField(type = "String", name = "SOURCE_NAME", value = "\"BBC News\"")
        }
        create("cnn") {
            dimension = "news"
            buildConfigField(type = "String", name = "SOURCE", value = "\"cnn\"")
            buildConfigField(type = "String", name = "SOURCE_NAME", value = "\"CNN\"")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.squareup.retrofit2:converter-gson:2.6.0")
    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.benchmark:benchmark-common:1.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // compose navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    // multidex
    implementation("androidx.multidex:multidex:2.0.1")

    // hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // timber log
    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    // pagination
    implementation("androidx.paging:paging-compose:3.2.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    // coil with animation
    implementation("com.github.skydoves:landscapist-coil:2.2.11")
    implementation("com.github.skydoves:landscapist-placeholder:2.2.11")
    implementation("com.github.skydoves:landscapist-animation:2.2.11")
    implementation("com.github.skydoves:landscapist-transformation:2.2.11")

    implementation("com.google.code.gson:gson:2.10.1")
    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.biometric:biometric:1.2.0-alpha05")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:1.4.3")
    // https://mvnrepository.com/artifact/org.mockito/mockito-core
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
    testImplementation("app.cash.turbine:turbine:0.11.0")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("com.google.dagger:hilt-android-testing:2.44")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")

    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    androidTestImplementation("com.squareup.okhttp3:mockwebserver:3.14.1")

}
