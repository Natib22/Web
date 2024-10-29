

---

# Dictionary Bookmark Manager

A JavaFX application for searching, managing bookmarks, and browsing search history in a dictionary-style interface. Users can search for words, bookmark favorite entries, and access recent searches.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Functionality](#functionality)
- [Dependencies](#dependencies)
- [Installation and Setup](#installation-and-setup)
- [Running the Application](#running-the-application)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Overview

This JavaFX application provides a user-friendly interface for dictionary lookups with bookmarking capabilities. It supports search functionality with dynamic suggestions, bookmark management, and recent search history tracking, all backed by an SQLite database.

## Features

- **Search Functionality**: Users can type in words to get real-time suggestions.
- **Bookmark Management**: Easily bookmark and remove entries for quick reference.
- **User Interface Components**: Utilizes JavaFX for interactive UI, including text fields, list views, and buttons.
- **Database Integration**: Employs SQLite for storing bookmarks and search history.

## Functionality

- **Search Page**: Allows users to search for words and dynamically displays suggestions.
- **Bookmarking**: Users can bookmark words directly from the search results or recent search history.
- **History Management**: Provides access to recent searches for convenience.
- **Database Operations**: Supports CRUD operations for bookmarks and history, allowing for add, remove, and clear functionalities.

## Dependencies

- **JavaFX**: GUI components and framework.
- **SQLite JDBC Driver** (`org.xerial:sqlite-jdbc`): Enables database connectivity.
- **FXML**: XML-based layout definitions (part of JavaFX).
- **Apache Maven** (optional): Dependency management and build automation tool.

## Installation and Setup

1. **Install JavaFX SDK**: Download and install JavaFX SDK if not already installed.
2. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd dictionary-bookmark-manager
   ```

3. **Add Dependencies**:
   - If using Maven, the `pom.xml` file should include dependencies for JavaFX and SQLite JDBC.
   - Otherwise, download and include the `sqlite-jdbc` JAR file in your project’s classpath.

4. **Configure JavaFX Path**: If running from the command line, make sure to set the path for the JavaFX library.

### Example `pom.xml` Dependencies (if using Maven)

```xml
<dependencies>
    <!-- JavaFX -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>17</version> <!-- Adjust version as needed -->
    </dependency>

    <!-- SQLite JDBC -->
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.34.0</version>
    </dependency>
</dependencies>
```

## Running the Application

### Using an IDE (IntelliJ IDEA, Eclipse, etc.)

1. **Open the Project** in your IDE.
2. **Build the Project** to resolve all dependencies.
3. **Run the Application** by launching the main Java class file.

### Using the Command Line

If JavaFX is set up correctly, you can run the application with the following:

```bash
mvn clean compile exec:java -Dexec.mainClass="com.yourpackage.Main"
```

Replace `com.yourpackage.Main` with the actual main class path of your project.

### Without Maven

If Maven isn’t used, compile the code with:

```bash
javac -cp ".;path/to/javafx-sdk/lib/*;path/to/sqlite-jdbc.jar" Main.java
```

Run it with:

```bash
java -cp ".;path/to/javafx-sdk/lib/*;path/to/sqlite-jdbc.jar" Main
```

## Usage

1. **Search Words**: Type a word in the search field to get suggestions.
2. **Bookmark Entries**: Click to bookmark a word for easy access later.
3. **View History**: Access recent searches and select entries to bookmark.
4. **Database Management**: Add, remove, or clear history and bookmarks, all stored persistently in the SQLite database.

## Contributing

Feel free to fork the repository and submit pull requests to contribute to this project.

## License

This project is open source and available under the [MIT License](LICENSE).

---

This README provides a comprehensive overview and step-by-step guide to set up, run, and use your application.
