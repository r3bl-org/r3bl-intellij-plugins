#!/bin/bash

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}Installing R3BL IntelliJ Plugins...${NC}"

# Find IntelliJ installation
if command -v idea &> /dev/null; then
    IDEA_CMD="idea"
elif [ -d "/Applications/IntelliJ IDEA.app" ]; then
    IDEA_CMD="/Applications/IntelliJ IDEA.app/Contents/MacOS/idea"
elif [ -f "$HOME/.local/share/JetBrains/Toolbox/scripts/idea" ]; then
    IDEA_CMD="$HOME/.local/share/JetBrains/Toolbox/scripts/idea"
else
    echo -e "${RED}IntelliJ IDEA command not found${NC}"
    echo "Please install plugins manually from:"
    find plugins -name "*.zip" -type f
    exit 1
fi

# Install each plugin
for plugin in plugins/*/build/distributions/*.zip; do
    if [ -f "$plugin" ]; then
        echo -e "${BLUE}Installing $(basename $plugin)...${NC}"
        "$IDEA_CMD" installPlugins "$plugin"
    fi
done

echo -e "${GREEN}Installation complete! Please restart IntelliJ IDEA.${NC}"
