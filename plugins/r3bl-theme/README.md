# R3BL Theme

A vibrant dark IntelliJ IDEA theme designed for developer productivity and visual clarity.

## Features

- **Vibrant Color Palette**: Carefully chosen colors for excellent contrast
- **Comprehensive Language Support**: 30+ programming languages
- **Custom UI Elements**: Tailored colors for caret, selection, gutter, and more
- **Reduced Eye Strain**: Dark background with balanced color highlights

## Installation

### Option 1: Install from Disk (Recommended)

1. Build the plugin:
   ```bash
   ./build.sh
   ```
   or
   ```bash
   ./gradlew buildPlugin
   ```

2. In IntelliJ IDEA:
   - Go to `Settings → Plugins → ⚙️ → Install Plugin from Disk`
   - Select the `.zip` file from `plugins/r3bl-theme/build/distributions/`
   - Restart IntelliJ IDEA

### Option 2: Use Install Script

```bash
./install.sh
```

Then restart IntelliJ IDEA.

## Activation

1. Go to `Settings → Appearance & Behavior → Appearance`
2. Select "R3BL" from the Theme dropdown
3. Click "Apply" and "OK"

The theme is immediately applied without restarting the IDE.

## Color Reference

### Syntax Highlighting

| Element | Color | Hex |
|---------|-------|-----|
| Keywords | Blue | `#B1BAF4` |
| Strings | Green | `#B1F2A7` |
| Numbers | Yellow | `#EBDE76` |
| Functions | Pink | `#E965A5` |
| Variables | Purple | `#E192EF` |
| Types | Cyan | `#B3F4F3` |
| Comments | Gray | `#8B81A7` |
| Errors | Red | `#FF6261` |

### UI Elements

| Element | Color | Hex |
|---------|-------|-----|
| Background | Very Dark | `#0D1117` |
| Caret | Magenta | `#D51AA3` |
| Selection | Magenta | `#981C72` |
| Line Numbers | Gray | `#8B81A7` |
| Gutter | Dark Gray | `#211E2A` |
| Console | Dark Gray | `#211E2A` |

## Development

### Build the Plugin

```bash
# Build just the theme plugin
./gradlew :plugins:r3bl-theme:buildPlugin

# Build all plugins
./build.sh
```

### Run in Development IDE

```bash
# Run theme plugin in development IDE
./gradlew :plugins:r3bl-theme:runIde

# Run all plugins in development IDE
./gradlew runIde
```

This launches a development instance of IntelliJ IDEA with the theme plugin loaded.

### File Structure

```
src/main/
├── kotlin/              # (Empty for theme plugins)
└── resources/
    └── META-INF/
        ├── plugin.xml
        ├── pluginIcon.png
        └── theme/
            ├── r3bl.theme.json    # Theme descriptor
            └── r3bl.icls          # Color scheme definition
```

## Theme Details

- **Based on**: Hard Hacker Darker color scheme with user customizations
- **Parent Scheme**: Darcula
- **Version**: 1.0.0
- **Target**: IntelliJ IDEA 2024.1+

## Customization

The theme is defined in two files:

1. **r3bl.theme.json**: Theme metadata and UI properties
2. **r3bl.icls**: XML-based color scheme with syntax highlighting rules

To customize colors, edit the `.icls` file and rebuild the plugin.

## Known Limitations

- Requires IntelliJ IDEA 2024.1 or later
- Some plugins may have their own color overrides that might conflict

## License

MIT License - See LICENSE file for details

## Author

R3BL - [r3bl.com](https://r3bl.com)

## Support

For issues or suggestions:
- Report at GitHub Issues
- Visit [r3bl.com](https://r3bl.com) for more information
