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
    ├── r3bl-copy-selection-path/
    └── r3bl-theme/
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
