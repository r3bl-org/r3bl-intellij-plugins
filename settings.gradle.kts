pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "r3bl-intellij-plugins"

include(
    ":plugins:r3bl-copy-selection-path",
    ":plugins:r3bl-theme"
    // Future plugins will be added here
)
