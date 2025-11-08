plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.intellij")
}

group = "com.r3bl"
version = "1.0.2"

repositories {
    mavenCentral()
}

intellij {
    version.set("2024.1")
    type.set("IC")
    plugins.set(listOf())
}

tasks {
    patchPluginXml {
        sinceBuild.set("241")
        untilBuild.set("")
    }

    buildSearchableOptions {
        enabled = false
    }

    runPluginVerifier {
        ideVersions.set(listOf(
            "2024.1.7",
            "2024.2.6",
            "2024.3.7",
            "2025.1.7",
            "2025.2.4"
        ))
    }

    // JetBrains Marketplace publication
    publishPlugin {
        val intellijPublishToken: String? by project
        token.set(intellijPublishToken)
    }
}
