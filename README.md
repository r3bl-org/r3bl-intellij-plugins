# R3BL IntelliJ IDEA Plugins

A collection of IntelliJ IDEA plugins for improved developer productivity.

## Plugins

### R3BL Copy Selection Path and Range

Copy file paths with selected line ranges in formats compatible with Claude Code and IDEs.

**Keybinding:** `Alt+O`

**Output formats:**
- No selection: `path/to/file.kt`
- Single line: `path/to/file.kt:42`
- Multi-line: `path/to/file.kt#L42-45`

## Building

### Prerequisites

- JDK 17 or later
- Gradle 8.7+ (or use included wrapper)

### Build All Plugins

```bash
./build.sh
```

Or manually with Gradle:

```bash
./gradlew buildPlugin
```

### Install Locally

```bash
./install.sh
```

Or manually:
1. Build the plugins (see above)
2. In IntelliJ IDEA: `Settings → Plugins → ⚙️ → Install Plugin from Disk`
3. Select the `.zip` file from `plugins/[plugin-name]/build/distributions/`

## Development

### Project Structure

```
r3bl-intellij-plugins/
├── build.gradle.kts              # Root build configuration
├── settings.gradle.kts           # Multi-project setup
├── gradle.properties             # Version properties
└── plugins/                      # Plugin modules
    └── r3bl-copy-selection-path/
```

### Adding a New Plugin

1. Create directory under `plugins/`
2. Add to `settings.gradle.kts`
3. Create `build.gradle.kts` for the plugin
4. Create `src/main/resources/META-INF/plugin.xml`
5. Implement plugin code in `src/main/kotlin/`

### Testing

```bash
./gradlew test
```

### Running in Development

```bash
./gradlew runIde
```

This launches IntelliJ IDEA with the plugin loaded for testing.

## License

MIT License - See LICENSE file for details

## Links

- [IntelliJ Platform SDK](https://plugins.jetbrains.com/docs/intellij/)
- [R3BL Website](https://r3bl.com)
