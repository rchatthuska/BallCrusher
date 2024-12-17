# BallCrusher 🎮

**BallCrusher** is an interactive **JavaFX game** where the player must click on all the randomly generated circles (balls) within a specified time limit to win. The user can choose the number of balls and whether they appear in **random sizes**. This game tests your speed and reflexes!

---

## 🚀 Features
- User-friendly graphical interface using **JavaFX**.
- Enter the number of balls and time limit for a personalized challenge.
- Option to play with **default-sized** or **random-sized circles**.
- Animated "You Win!" or "You Lose!" message with dynamic colors.
- Timer that tracks your performance.

---

## 📂 Project Structure
BallCrusher/ │-- src/ │ └── main/ │ └── resources/ │ └── com/ │ └── example/ │ └── BallCrusher.java # Main game file with all logic │ │-- README.md # Project documentation └── build/ # (Optional) Place compiled .class or .jar files here



---

## 🛠️ Technologies Used
- **JavaFX**: For graphical user interface and animations.
- **Java**: Programming language to implement game logic.
- **OOP Concepts**: Organized and modular design.

---

## 🧩 Setup Instructions
### Prerequisites:
1. Install **Java Development Kit (JDK)** version **8 or above**.
2. Ensure **JavaFX libraries** are configured in your development environment (IDE or CLI).

---

### Steps to Run the Game

#### Option 1: Using an IDE (IntelliJ, Eclipse, NetBeans)
1. Clone the repository:
   ```bash
   git clone https://github.com/rchatthuska/BallCrusher.git
   cd BallCrusher
Open the project in your preferred IDE.
Add JavaFX support:
In IntelliJ: Go to File > Project Structure > Libraries, and add JavaFX SDK.
Run the BallCrusher.java file from the path:
css

src/main/resources/com/example/BallCrusher.java
Option 2: Using the Command Line
Clone the repository:


git clone https://github.com/rchatthuska/BallCrusher.git
cd BallCrusher/src/main/resources/com/example
Compile the JavaFX program:


javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls BallCrusher.java
Replace /path/to/javafx-sdk/lib with the path to your JavaFX SDK folder.
Run the game:


java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls com.example.ballcrusher.BallCrusher
🎮 How to Play
Enter the number of balls and the time limit to beat in seconds.
Choose whether the circles should have random sizes.
Click Begin! to start the game.
Objective: Click on all the circles as fast as you can before time runs out.
If you succeed within the time limit, you’ll see a "You Win!" message. Otherwise, you'll get a "You Lose!" message.
🧑‍💻 Author
Rumesh Chathuska

GitHub: rchatthuska
📌 Future Improvements
Add sound effects for a better gaming experience.
Implement score tracking and a high-score leaderboard.
Introduce power-ups or obstacles for advanced gameplay.
📜 License
This project is licensed under the MIT License.

Enjoy the game and test your reflexes! 🚀🎉



---

### Key Updates:
1. **JavaFX Setup Instructions**: Added both IDE and command-line instructions for running a JavaFX project.
2. **Project Structure**: Reflects your file's actual location (`src/main/resources/com/example/BallCrusher.java`).
3. **Running the Game**: Clarified how to configure JavaFX SDK and run the program.

---

