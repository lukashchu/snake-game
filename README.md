# 🐍 Snake Game in Java

A classic Snake game implemented using Java Swing. This lightweight, standalone application supports keyboard controls, persistent high scores, and a clean, responsive UI. 

## 🎮 Features

- Intuitive arrow key / WASD controls
- Real-time gameplay using Java's `javax.swing.Timer`
- Auto-increasing snake length and score upon collecting apples
- Game-over conditions:
  - Collision with wall
  - Self-collision
- Persistent **high score** saved to disk across game sessions
- Clean UI with current score, high score, and game timer display
- Easy to launch and run — no external dependencies

## 🧠 Controls

| Key        | Action        |
|------------|---------------|
| Arrow Keys | Move Snake    |
| WASD       | Move Snake    |
| Reset      | Restart Game  |

## 💾 High Score Storage

High score is saved to: `snake_local_temp/files/highscore.txt`

Make sure this `snake_local_temp/files/` directory exists and is writable. The game will create the file if it doesn't already exist.

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher

### Option 1: Run from Source
```bash
javac -d bin src/main/java/org/aluk/*.java src/main/java/org/aluk/snake/*.java
java -cp bin org.aluk.Game
```

### Option 2: Run Executable JAR
If you've downloaded the pre-built JAR:
```bash
java -jar snake-game.jar
```
> Tip: Double-clicking the JAR on most systems will launch the game.

## 🗂 Project Structure
```cpp
snake-game/
├── snake_local_temp/
│   ├── files/
│   │   └── highscore.txt
│   └── src/
│       ├── main/
│       │   └── java/
│       │       └── org/
│       │           └── aluk/
│       │               ├── Game.java
│       │               └── snake/
│       │                   ├── GameBoard.java
│       │                   ├── RunSnake.java
│       │                   ├── Snake.java
│       │                   └── SnakeBody.java
│       └── test/
│           └── java/
│               └── org/
│                   └── aluk/
│                       └── snake/
│                           └── GameTest.java
└── snake-game.jar
```

## 🛠 Customization
You can modify:
 - `INTERVAL` in `GameBoard.java` to adjust game speed
 - `BOARD_WIDTH` / `BOARD_HEIGHT` to change grid size
 - Add sound, skins, or levels with ease

Created with ❤️ by lukashchu
