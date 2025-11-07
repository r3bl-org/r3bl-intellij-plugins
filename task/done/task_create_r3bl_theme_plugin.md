# IntelliJ IDEA Plugin: R3BL Theme

## Overview

Create an IntelliJ IDEA theme plugin featuring a high-contrast dark theme optimized for
visual clarity and developer productivity. This will be the second plugin in the
`r3bl-intellij-extensions` monorepo.

This is an excellent theme to use as an example for how to write themes:
[Hard Hacker for Jetbrains theme](https://github.com/hardhackerlabs/theme-jetbrains)

## Purpose

Provide a complete IntelliJ theme plugin with:

1. **Custom UI Theme**: Visual customization of IDE elements
2. **Color Scheme**: Comprehensive syntax highlighting and editor colors
3. **Easy Installation**: Packaged as a `.zip` plugin for installation via IntelliJ

## Theme Specifications

### Color Palette

The R3BL theme uses a vibrant dark color palette designed for visual clarity and reduced
eye strain:

#### Primary Colors

| Color       | Hex       | Usage                                       |
| ----------- | --------- | ------------------------------------------- |
| **Pink**    | `#E965A5` | Functions, methods, scrollbar               |
| **Magenta** | `#D51AA3` | Caret, refactoring UI                       |
| **Purple**  | `#E192EF` | Variables, constants, doc comments          |
| **Violet**  | `#C552AC` | UI hover states                             |
| **Blue**    | `#B1BAF4` | Keywords, operators                         |
| **Cyan**    | `#B3F4F3` | Types, classes, external commands           |
| **Green**   | `#B1F2A7` | Strings, values                             |
| **Yellow**  | `#EBDE76` | Numbers, section names, file unknown status |
| **Red**     | `#FF6261` | Errors, bad characters                      |

#### Background Colors

| Element                | Hex       | Purpose                                |
| ---------------------- | --------- | -------------------------------------- |
| **Main Background**    | `#0D1117` | Editor and window background           |
| **Console**            | `#211E2A` | Console and gutter background          |
| **Caret Row**          | `#4C1550` | Highlighted line with caret            |
| **Editor Selection**   | `#981C72` | Selected text in editor                |
| **UI Selection**       | `#6B1550` | Selected items in lists/menus/UI       |
| **Lookup**             | `#1A1821` | Code completion popup                  |
| **Secondary BG**       | `#2A2636` | Borders, separators, hover backgrounds |

#### Additional Colors

| Element              | Hex       | Purpose                      |
| -------------------- | --------- | ---------------------------- |
| **Comments**         | `#8B81A7` | Code comments                |
| **Line Numbers**     | `#8B81A7` | Gutter line numbers          |
| **Default Text**     | `#EEE9FC` | Punctuation and regular text |
| **Annotations**      | `#DAD5E7` | IDE annotations              |
| **Disabled Text**    | `#666373` | Inactive/disabled UI items   |
| **File Unknown**     | `#FFED16` | Unknown file status (VCS)    |

### UI Customization

The theme provides comprehensive UI customization for 40+ IDE elements including:

**Editor Elements:**
- Caret and selection colors
- Line numbers and gutter
- Console and lookup backgrounds
- Breakpoint and error visuals
- VCS annotations and git log

**UI Components:**
- Buttons (default, hover, pressed states)
- Menus, popups, and dropdown lists
- Tabs (editor tabs, tool window tabs)
- Tables, trees, and side panels
- Toolbars and status bar
- Scrollbars with transparency
- Progress bars and notifications
- Search fields and completion popups
- Welcome screen and plugin UI

## Plugin Structure

### Actual File Structure

```
plugins/r3bl-theme/
├── build.gradle.kts
├── LICENSE
├── README.md
└── src/main/resources/
    ├── META-INF/
    │   ├── plugin.xml
    │   └── pluginIcon.png
    └── theme/
        ├── r3bl.theme.json
        └── r3bl.xml
```

### Key Files

- **plugin.xml**: Registers the theme provider with IntelliJ
  - Contains `<themeProvider>` extension pointing to `/theme/r3bl.theme.json`

- **r3bl.theme.json**: Main theme descriptor
  - Defines UI colors for IDE elements (buttons, menus, toolbars, etc.)
  - References the color scheme at `/theme/r3bl.xml`
  - Contains comprehensive UI customizations (340+ lines)

- **r3bl.xml**: Color scheme for editor syntax highlighting
  - IntelliJ color scheme XML format (not `.icls`)
  - Defines editor colors (caret, selection, gutter, etc.)
  - Contains syntax highlighting for 30+ languages

## Implementation Notes

- Theme plugin requires only configuration files (no Kotlin code)
- Color scheme uses `.xml` extension, located in `theme/` directory
- Theme descriptor extensively customizes UI elements beyond basic colors
- Plugin built automatically via existing monorepo build scripts
- Theme extends Darcula as parent scheme
