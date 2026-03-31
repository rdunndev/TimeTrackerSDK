import org.gradle.kotlin.dsl.publishing

plugins {
    alias(libs.plugins.android.library)
    `maven-publish`
}

android {
    namespace = "com.rdunndev.timetracker"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

group = "com.github.rdunndev"
version = "1.0.0"

publishing {
    publications {
        create<MavenPublication>("ReleaseAar") {
            groupId = "com.rdunndev"
            artifactId = "timetracker"
            version = "1.0.0"
            afterEvaluate {
                artifact(tasks.getByName("bundleReleaseAar"))
            }
        }
    }


    repositories {
        maven {
            name = "timetracker"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

dependencies {
    implementation(libs.appcompat.v7)
    testImplementation(libs.junit)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.espresso.core)
}