import org.gradle.kotlin.dsl.publishing

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    `maven-publish`
}

android {
    namespace = "com.rdunndev.composetest"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10" // Replace with your Compose compiler version
    }
}

group = "com.rdunndev"

publishing {
    publications {
        create<MavenPublication>("ReleaseAar") {
            groupId = group as String?
            artifactId = "composetest"
            version = findProperty("lib.version") as String?
            afterEvaluate {
                artifact(tasks.getByName("bundleReleaseAar"))
            }
        }
    }

    repositories {
        maven {
            name = "composetest"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

dependencies {
//    implementation(libs.org.jetbrains.kotlin.plugin.compose.gradle.plugin)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.runtime)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.androidx.material3)

    val composeBom = platform("androidx.compose:compose-bom:2026.03.00")
    implementation(composeBom)
}