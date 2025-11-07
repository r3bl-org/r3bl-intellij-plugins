# Contributing to R3BL IntelliJ Plugins

## Development Setup

1. Clone the repository:
   ```bash
   git clone git@github.com:r3bl-org/r3bl-intellij-plugins.git
   cd r3bl-intellij-plugins
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run in development mode:
   ```bash
   ./gradlew runIde
   ```

## Plugin Development

### Creating a New Plugin

1. Create plugin directory:
   ```bash
   mkdir -p plugins/my-plugin/src/main/{kotlin,resources/META-INF}
   ```

2. Add to `settings.gradle.kts`:
   ```kotlin
   include(":plugins:my-plugin")
   ```

3. Create `build.gradle.kts` in plugin directory (see existing plugins for template)

4. Create `plugin.xml` manifest

5. Implement plugin code

### Testing Changes

- Run tests: `./gradlew test`
- Run in IDE: `./gradlew runIde`
- Build plugin: `./gradlew :plugins:my-plugin:buildPlugin`

### Code Style

- Follow Kotlin coding conventions
- Use 4 spaces for indentation
- Maximum line length: 120 characters

## Submitting Changes

1. Create a feature branch
2. Make your changes
3. Test thoroughly
4. Submit a pull request

## Resources

- [IntelliJ Platform SDK Documentation](https://plugins.jetbrains.com/docs/intellij/)
- [Kotlin Style Guide](https://kotlinlang.org/docs/coding-conventions.html)
