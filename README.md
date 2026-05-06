# Tunisia Heritage Quest

## 📌 Repository Contents

This GitHub repository contains:
- The full project report (including the use of AI section)
- Screenshots of the application
- A video demo showing the app functionality

## 📌 Project Overview

Tunisia Heritage Quest is an Android quiz application developed using Kotlin and Jetpack Compose.

The goal of the app is to help users learn about Tunisian historical monuments by identifying places from images and answering multiple-choice questions.

Main features:
- Image-based quiz system
- Multiple choice questions (4 options)
- 3 difficulty levels (easy, medium, hard)
- Score system (+10 per correct answer)
- Progress tracking (mastery)
- Streak system (number of completed games)
- Final results with percentage and feedback

Currently, only the Roman Heritage category is implemented.


---

## 🏗️ Architecture Overview

The application is built using modern Android tools:

- **Jetpack Compose** → for building UI screens  
- **Navigation Component** → to move between screens  
- **ViewModel** → to manage data such as score and questions  
- **State management (mutableStateOf)** → to update UI dynamically  

Main screens:
- Splash Screen
- Main Menu
- Category Screen
- Difficulty Screen
- Quiz Screen
- Result Screen
---

## ⚠️ Known Issues / Limitations

The project has some limitations and incomplete features:

- The **timer feature was not implemented**
- **Sound effects were not implemented**
- Only **one category (Roman Heritage)** was completed

- Testing was implemented (Unit Testing, ViewModel Testing, Navigation Testing),  
  but when running the tests, the **PC froze**, so screenshots of test results could not be taken
(Android Studio was **heavy on the system**)

---

