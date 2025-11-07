#!/bin/bash

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}Building R3BL IntelliJ Plugins...${NC}"

# Check if Gradle wrapper exists
if [ ! -f "./gradlew" ]; then
    echo -e "${RED}Gradle wrapper not found. Run: gradle wrapper${NC}"
    exit 1
fi

# Clean previous builds
echo -e "${BLUE}Cleaning previous builds...${NC}"
./gradlew clean

# Build all plugins
echo -e "${BLUE}Building all plugins...${NC}"
./gradlew buildPlugin

# List generated plugin files
echo -e "${GREEN}Build complete!${NC}"
echo -e "${BLUE}Generated plugin files:${NC}"
find plugins -name "*.zip" -type f

echo -e "${GREEN}All plugins built successfully!${NC}"
