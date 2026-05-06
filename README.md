# Tunisia Heritage Quest

## 📌 Project Overview

Tunisia Heritage Quest is an Android quiz application developed using Kotlin and Jetpack Compose.

The app allows users to identify Tunisian historical monuments from images and answer multiple-choice questions.

Main features:
- Image-based quiz
- 3 difficulty levels (easy, medium, hard)
- Score system (+10 points per correct answer)
- Timer per question
- Progress tracking (mastery percentage)
- Streak system (number of completed games)
- Final results with percentage and performance message

The goal of the app is to make learning Tunisian heritage more interactive and engaging.


---

## 🏗️ Architecture Explanation

The application is built using modern Android development components:

- **Jetpack Compose**
  - Used to build all UI screens
  - Makes the interface dynamic and responsive

- **Navigation Component**
  - Handles movement between screens:
    - Splash → Main Menu → Category → Difficulty → Quiz → Result

- **ViewModel**
  - Manages app data and state
  - Keeps track of:
    - Score
    - Current question index
    - Questions list
    - User answers

- **State Management**
  - Uses `mutableStateOf`
  - Automatically updates UI when data changes

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
