plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.24" apply false
    id("org.jetbrains.intellij") version "1.17.3" apply false
}

group = "com.r3bl"
version = "1.0.0"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.intellij")

    configure<org.jetbrains.intellij.IntelliJPluginExtension> {
        version.set("2024.1")
        type.set("IC") // IntelliJ IDEA Community Edition
        updateSinceUntilBuild.set(false)
    }

    dependencies {
        // Common dependencies can be added here
    }

    tasks {
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = "17"
        }

        withType<JavaCompile> {
            sourceCompatibility = "17"
            targetCompatibility = "17"
        }
    }
}
