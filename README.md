# Battleships Game

A Java implementation of the classic Battleships game featuring both a **Swing-based GUI** and a **command-line interface (CLI)**. Built using the Model-View-Controller (MVC) architecture for clean separation of concerns.

---

## Features ✨
- **Two Interfaces**: Play via a graphical UI (GUI) or text-based CLI.
- **Random Ship Placement**: Automatically generate ship positions.
- **Custom Ship Configuration**: Load ship layouts from a file.
- **Hit/Miss Tracking**: Visual feedback for attacks (GUI: colored buttons, CLI: `H`/`M` markers).
- **Win Detection**: Game ends when all ships are sunk.
- **Unit Tests**: JUnit tests for core logic (e.g., attack handling, ship placement).
- **MVC Architecture**: Decoupled components for scalability and maintainability.

---

## Installation 🛠️
### Prerequisites
- **Java Development Kit (JDK) 8+**

### Steps
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/battleships.git
   cd battleships