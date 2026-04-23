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

group = "com.rdunndev"
version = "1.0.14"

publishing {
    publications {
        create<MavenPublication>("ReleaseAar") {
            groupId = group as String
            artifactId = "timetracker"
            version = findProperty("lib.version") as String?
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