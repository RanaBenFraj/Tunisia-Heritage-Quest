# Tunisia Heritage Quest

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

## ⚙️ Setup Instructions

To run the project:

1. Open Android Studio  
2. Click "Open Project"  
3. Select the project folder  
4. Wait for Gradle sync  
5. Run the app on an emulator or device  

Requirements:
- Android Studio installed  
- Kotlin support  


---

## ⚠️ Known Issues / Limitations

The project has some limitations and incomplete features:

- The **timer feature was not implemented**
- **Sound effects were not implemented**
- Only **one category (Roman Heritage)** was completed

- Testing was implemented (Unit Testing, ViewModel Testing, Navigation Testing),  
  but when running the tests, the **PC froze**, so screenshots of test results could not be taken

- Android Studio was **heavy on the system**, which affected performance and made it difficult to fully debug or finalize some parts

- Some minor bugs or UI issues may still appear depending on the device

---

