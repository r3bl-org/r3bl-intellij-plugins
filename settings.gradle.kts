pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "r3bl-intellij-plugins"

include(
    ":plugins:r3bl-copy-selection-path"
    // Future plugins will be added here
)
