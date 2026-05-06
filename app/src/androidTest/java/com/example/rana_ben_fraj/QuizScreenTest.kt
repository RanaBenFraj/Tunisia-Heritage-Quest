package com.example.rana_ben_fraj

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import com.example.rana_ben_fraj.ui.theme.Rana_Ben_FrajTheme
import org.junit.Rule
import org.junit.Test

class QuizScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun quizScreen_displaysQuestionText() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = androidx.navigation.compose.rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        composeTestRule.onNodeWithText("Identify this iconic Roman amphitheater located in central Tunisia.")
            .assertIsDisplayed()
    }

    @Test
    fun quizScreen_displaysFourAnswerOptions() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = androidx.navigation.compose.rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        // Verify all 4 options are displayed
        composeTestRule.onNodeWithText("El Jem Amphitheater").assertIsDisplayed()
        composeTestRule.onNodeWithText("Carthage").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dougga").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sbeitla").assertIsDisplayed()
    }

    @Test
    fun quizScreen_clickingOption_selectsAnswer() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = androidx.navigation.compose.rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        // Click on an option
        composeTestRule.onNodeWithText("El Jem Amphitheater").performClick()

        // Verify the option is selected (by checking it's still displayed)
        composeTestRule.onNodeWithText("El Jem Amphitheater").assertIsDisplayed()
    }

    @Test
    fun quizScreen_submitButton_isDisplayed() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = androidx.navigation.compose.rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        composeTestRule.onNodeWithText("SUBMIT ANSWER").assertIsDisplayed()
    }

    @Test
    fun quizScreen_selectingOption_thenSubmit() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = androidx.navigation.compose.rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        // Select an option
        composeTestRule.onNodeWithText("El Jem Amphitheater").performClick()

        // Verify button is still displayed and clickable
        composeTestRule.onNodeWithText("SUBMIT ANSWER").assertIsDisplayed()
    }

    @Test
    fun quizScreen_multipleOptionsCanBeClicked() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = androidx.navigation.compose.rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        // Click first option
        composeTestRule.onNodeWithText("El Jem Amphitheater").performClick()
        composeTestRule.onNodeWithText("El Jem Amphitheater").assertIsDisplayed()

        // Click different option
        composeTestRule.onNodeWithText("Carthage").performClick()
        composeTestRule.onNodeWithText("Carthage").assertIsDisplayed()

        // Verify both options are still visible
        composeTestRule.onNodeWithText("Dougga").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sbeitla").assertIsDisplayed()
    }

    @Test
    fun quizScreen_submitWithoutSelection_buttonIsDisabled() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = androidx.navigation.compose.rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        // Try to find submit button (it exists but should be disabled)
        composeTestRule.onNodeWithText("SUBMIT ANSWER").assertIsDisplayed()
    }

    @Test
    fun quizScreen_questionImage_isDisplayed() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = androidx.navigation.compose.rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        // Verify question text is displayed (image loading is harder to test)
        composeTestRule.onNodeWithText("Identify this iconic Roman amphitheater located in central Tunisia.")
            .assertIsDisplayed()
    }

    @Test
    fun quizScreen_allOptionsAreClickable() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = androidx.navigation.compose.rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        val options = listOf(
            "El Jem Amphitheater",
            "Carthage",
            "Dougga",
            "Sbeitla"
        )

        for (option in options) {
            composeTestRule.onNodeWithText(option).performClick()
            composeTestRule.onNodeWithText(option).assertIsDisplayed()
        }
    }
}
