# Tunisia Heritage Quest

## 📌 Project Overview

Tunisia Heritage Quest is an Android quiz application developed using Kotlin and Jetpack Compose.

The application allows users to identify Tunisian historical monuments from images and answer multiple-choice questions.

Main features:
- Image-based quiz
- 3 difficulty levels (easy, medium, hard)
- Score system (+10 points per correct answer)
- Progress tracking (mastery percentage)
- Streak system (number of completed games)
- Final results with percentage and performance message

Currently, only one category (Roman Heritage) is implemented.


---

## 🏗️ Architecture Explanation

The application follows a simple modern Android architecture:

- **Jetpack Compose**
  - Used to build all UI screens

- **Navigation Component**
  - Handles navigation between screens:
    - Splash → Main Menu → Category → Difficulty → Quiz → Result

- **ViewModel**
  - Used to manage and store application state:
    - Score
    - Current question index
    - Questions list
    - User answers

- **State Management**
  - Uses `mutableStateOf` to update the UI automatically

- **Data Model**
```kotlin
data class QuizQuestion(
    val imageRes: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val difficulty: String,
    val explanation: String
)
