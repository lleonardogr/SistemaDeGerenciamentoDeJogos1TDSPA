# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

A Metacritic-style content management system for games, series, and films. Users can add content, rate it, and view ratings. Built as a console application in Java 25 using Maven.

## Project Structure

Standard Maven directory layout:
```
src/
├── main/
│   ├── java/           # Java source files
│   │   ├── Main.java
│   │   ├── entities/
│   │   ├── enums/
│   │   └── services/
│   └── resources/      # log4j2.xml configuration
└── test/
    ├── java/           # Test files (currently empty)
    └── resources/      # Test resources (currently empty)
```

## Building and Running

**Important:** This project requires Java 25. Set JAVA_HOME before building:

```bash
# Set JAVA_HOME to Java 25
export JAVA_HOME=/Users/leonardo.romao/Library/Java/JavaVirtualMachines/openjdk-25.0.2/Contents/Home

# Compile the project
mvn compile

# Clean and rebuild
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="Main"

# Package the project
mvn package
```

## Architecture

### Entity Hierarchy

The project uses an inheritance-based entity model:

- **Conteudo** (abstract base class): Common fields for all content types
  - `nome`: String
  - `dataLancamento`: LocalDate
  - `avaliacoes`: ArrayList<Avaliacao>

- **Jogo extends Conteudo**: Fully implemented with ratings system
  - `plataforma`: PLATAFORMA_JOGO enum
  - `mediaAvaliacoes`: Calculated field (private with getter)
  - Must call `recalcularMediaAvaliacoes()` after adding reviews

- **Serie extends Conteudo**: Minimal implementation (stub)
  - `sinopse`: String

- **Filme extends Conteudo**: Minimal implementation (stub)
  - `sinopse`: String

- **Avaliacao**: User reviews with validation
  - `nomeUsuario`: String (public)
  - `nota`: int (0-10, private with validated setter)
  - `comentario`: String (public)
  - `dataLancamento`: LocalDate (public)

### Service Layer Pattern

Services handle all business logic and user interaction. Each content type has its own service:

- **JogoService**: Fully implemented with CRUD operations and rating functionality
  - Uses static IO utility methods (`IO.readln()`, `IO.println()`) from Java 25
  - Operates on shared `ArrayList<Conteudo>` collection
  - Menu navigation via MENU_JOGO enum

- **SerieService**: Stub implementation (only has menu definition)

### Field Visibility Convention

This codebase follows a specific pattern for field visibility:

- **Public fields**: Simple data that's directly assigned (e.g., `nome`, `plataforma`)
- **Private fields**: Calculated or validated data requiring getters/setters (e.g., `mediaAvaliacoes`, `nota` in Avaliacao)

### Navigation Flow

Main.java orchestrates the application flow:
1. Main menu uses MENU_INICIAL enum (0=Sair, 1=Jogo, 2=Serie, 3=Filme)
2. Each content type has submenu navigation (only JogoService fully implemented)
3. Single shared `ArrayList<Conteudo>` collection for all content types
4. Services receive this collection and cast to specific types as needed

### Java 25 Features

- Uses `IO.readln()` and `IO.println()` static utility methods for console I/O
- Text blocks (""") for multi-line strings

### Logging

The project uses Log4j2 (version 2.25.3) for logging:

- **Configuration**: `src/main/resources/log4j2.xml`
  - Console appender with simple timestamp format
  - Rolling file appender (logs to `logs/app.log`)
  - Rolls daily or when file exceeds 10MB
  - Keeps up to 10 archived log files
  - Root logger at INFO level
  - Service and entity loggers at DEBUG level

- **Log Levels Used**:
  - `logger.info()`: Application lifecycle events (startup, menu navigation, major operations)
  - `logger.debug()`: Detailed information (calculated values, collection sizes, selected options)
  - `logger.warn()`: Invalid user input or unexpected conditions

- **Where Logging is Implemented**:
  - `Main.java`: Application lifecycle and menu navigation
  - `JogoService.java`: All CRUD operations with detailed context
  - `Jogo.java`: Rating calculations and recalculations

- **Log Files**:
  - Logs are written to `logs/app.log` (excluded from git via .gitignore)
  - Use `tail -f logs/app.log` to monitor application in real-time

## Current State

- **Jogo (Games)**: Fully functional with add, remove, list, rate, and view ratings
- **Serie (Series)**: Menu exists but operations not implemented
- **Filme (Films)**: No implementation yet (not even menu handling in Main.java)

## When Adding Features

1. For new content types: Extend Conteudo abstract class
2. For calculated fields: Make them private and provide getters
3. For validation: Use setters (see Avaliacao.setNota())
4. For new services: Follow JogoService pattern - methods receive List<Conteudo> and handle casting
5. For enums: Match enum order to menu display order (0-indexed)
6. For logging: Add logger field and log at appropriate levels (info for operations, debug for details, warn for issues)
