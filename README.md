# R3BL IntelliJ IDEA Plugins

A collection of IntelliJ IDEA plugins for improved developer productivity.

## Plugins

### R3BL Copy Selection Path and Range

Copy file paths with selected line ranges in formats compatible with Claude Code and IDEs.

**Keybinding:** `Alt+O`

**Output formats:**
- No selection: `path/to/file.kt:42` (includes cursor line number)
- Single line: `path/to/file.kt:42` (IDE-compatible format)
- Multi-line: `@path/to/file.kt#L42-45` (Claude Code format with @ prefix)

Get [R3BL Theme from JB Marketplace](https://plugins.jetbrains.com/plugin/28943-r3bl-theme/)

### R3BL Theme

A vibrant dark theme with carefully chosen colors for visual clarity and reduced eye strain.

**Installation:** Go to `Settings → Appearance → Theme` and select "R3BL"

**Features:**
- Vibrant color palette optimized for visual clarity
- Comprehensive syntax highlighting for 30+ languages
- Customized UI elements (caret, selection, gutter)
- Reduced eye strain with dark background and balanced colors

**Color Highlights:**
- Pink/Magenta: Functions and emphasis
- Blue: Keywords and operators
- Cyan: Types and classes
- Green: Strings and values
- Yellow: Numbers and parameters

Get [R3BL Copy Selection Path and Range from JB Marketplace](https://plugins.jetbrains.com/plugin/28944-r3bl-copy-selection-path-and-range/)

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

### Verify Compatibility (Recommended Before Publishing)

```bash
# Verify all plugins against multiple IntelliJ versions
./gradlew :plugins:r3bl-theme:runPluginVerifier :plugins:r3bl-copy-selection-path:runPluginVerifier
```

See the [Verifying Plugin Compatibility](#verifying-plugin-compatibility) section for details.

### Install Locally

```bash
./install.sh
```

Or manually:
1. Build the plugins (see above)
2. In IntelliJ IDEA: `Settings → Plugins → ⚙️ → Install Plugin from Disk`
3. Select the `.zip` file from `plugins/[plugin-name]/build/distributions/`

## Development

### Complete Development Workflow

Here's the recommended workflow when making changes to a plugin:

1. **Make your code changes**

2. **Test locally**:
   ```bash
   ./gradlew :plugins:[plugin-name]:runIde
   ```

3. **Verify compatibility**:
   ```bash
   ./gradlew :plugins:[plugin-name]:runPluginVerifier
   ```
   Ensure all versions show "Compatible" status.

4. **Bump version** in `plugins/[plugin-name]/build.gradle.kts`:
   ```kotlin
   version = "1.0.X"  // Increment from previous
   ```

5. **Build the plugin**:
   ```bash
   ./gradlew clean :plugins:[plugin-name]:buildPlugin
   ```

6. **Publish to marketplace**:
   ```bash
   ./gradlew :plugins:[plugin-name]:publishPlugin
   ```

7. **Wait for JetBrains review** (1-2 business days)

### Project Structure

```
r3bl-intellij-plugins/
├── build.gradle.kts              # Root build configuration
├── settings.gradle.kts           # Multi-project setup
├── gradle.properties             # Global properties (includes publish token & stdlib config)
└── plugins/                      # Plugin modules
    ├── r3bl-copy-selection-path/
    │   ├── build.gradle.kts      # Plugin-specific build config
    │   └── src/main/
    │       ├── kotlin/           # Plugin source code
    │       └── resources/
    │           ├── META-INF/plugin.xml  # Plugin descriptor
    │           └── theme/        # Theme resources (for theme plugin)
    └── r3bl-theme/
        └── (same structure)
```

### Adding a New Plugin

1. Create directory under `plugins/`
2. Add to `settings.gradle.kts`
3. Create `build.gradle.kts` for the plugin
4. Create `src/main/resources/META-INF/plugin.xml`
5. Implement plugin code in `src/main/kotlin/`
6. Add `runPluginVerifier` configuration to `build.gradle.kts`

### Testing

```bash
./gradlew test
```

### Running in Development

```bash
./gradlew runIde
```

This launches IntelliJ IDEA with the plugin loaded for testing.

### Verifying Plugin Compatibility

Before publishing, verify compatibility with multiple IntelliJ versions:

```bash
# Verify a specific plugin
./gradlew :plugins:r3bl-theme:runPluginVerifier
./gradlew :plugins:r3bl-copy-selection-path:runPluginVerifier

# Verify all plugins
./gradlew :plugins:r3bl-theme:runPluginVerifier :plugins:r3bl-copy-selection-path:runPluginVerifier
```

The verifier checks compatibility against IntelliJ versions 2024.1 through 2025.2 (configured in each plugin's `build.gradle.kts`).

**Important**: All plugins should pass verification with "Compatible" status before publishing to the marketplace.

### Publishing to JetBrains Marketplace

1. **Bump the version** in the plugin's `build.gradle.kts`:
   ```kotlin
   version = "1.0.2"  // Increment from previous version
   ```

2. **Rebuild the plugin**:
   ```bash
   ./gradlew clean :plugins:r3bl-theme:buildPlugin
   ```

3. **Verify compatibility** (see section above)

4. **Publish to marketplace**:
   ```bash
   ./gradlew :plugins:r3bl-theme:publishPlugin
   ```

   The `intellijPublishToken` is configured in `gradle.properties`.

5. **Wait for marketplace review**: JetBrains will review and approve within 1-2 business days.

### Important Configuration Notes

**Kotlin Standard Library**: Do NOT bundle the Kotlin stdlib with plugins. The IntelliJ Platform already provides it. This is configured in `gradle.properties`:

```properties
kotlin.stdlib.default.dependency = false
```

Without this setting, the bundled stdlib will trigger compatibility warnings for deprecated APIs. Always use the IDE-provided stdlib instead.

## Troubleshooting

### Compatibility Warnings from JetBrains Marketplace

If you receive compatibility warnings about deprecated APIs or scheduled-for-removal APIs:

1. **Check if Kotlin stdlib is bundled**:
   ```bash
   unzip -l plugins/[plugin-name]/build/distributions/[plugin-name]-*.zip | grep kotlin-stdlib
   ```

2. **If kotlin-stdlib appears**, verify `gradle.properties` has:
   ```properties
   kotlin.stdlib.default.dependency = false
   ```

3. **Clean and rebuild**:
   ```bash
   ./gradlew clean :plugins:[plugin-name]:buildPlugin
   ```

4. **Verify the fix worked** (stdlib should NOT appear):
   ```bash
   unzip -l plugins/[plugin-name]/build/distributions/[plugin-name]-*.zip | grep "\.jar"
   ```

5. **Run the plugin verifier** to confirm compatibility:
   ```bash
   ./gradlew :plugins:[plugin-name]:runPluginVerifier
   ```

### Updating Target IDE Versions

To test against different IntelliJ versions, update the `runPluginVerifier` configuration in `plugins/[plugin-name]/build.gradle.kts`:

```kotlin
runPluginVerifier {
    ideVersions.set(listOf(
        "2024.1.7",
        "2024.2.6",
        "2024.3.7",
        "2025.1.7",
        "2025.2.4"
    ))
}
```

## License

MIT License - See LICENSE file for details

## Testing & Verification

### Test Results (Completed)

All three output formats have been thoroughly tested and verified:

- ✅ **No Selection**: Outputs `path/to/file.kt:lineNumber` with cursor position
- ✅ **Single-Line Selection**: Outputs `path/to/file.kt:lineNumber` in IDE-compatible format
- ✅ **Multi-Line Selection**: Outputs `@path/to/file.kt#L21-30` in Claude Code format
- ✅ **Notifications**: Display with auto-dismiss after action
- ✅ **Keybinding**: `Alt+O` registered and functional (no critical conflicts)

### Implementation Notes

The plugin has been successfully implemented with the following updates:
- Fixed `ActionUpdateThread.OLD_EDT` deprecation warning by overriding `getActionUpdateThread()`
- Corrected output format for multi-line selections to include `@` prefix at the beginning
- Added line number support for no-selection case (cursor position)
- Updated documentation and specifications to reflect actual behavior

## Links

- [IntelliJ Platform SDK](https://plugins.jetbrains.com/docs/intellij/)
- [R3BL Website](https://r3bl.com)
