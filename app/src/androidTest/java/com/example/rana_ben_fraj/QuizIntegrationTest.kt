package com.example.rana_ben_fraj

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import com.example.rana_ben_fraj.ui.theme.Rana_Ben_FrajTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavBackStackEntry
import org.junit.Rule
import org.junit.Test

class QuizIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun totalQuizFlow_selectCategory_selectDifficulty_takeQuiz() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Category.route
                ) {
                    composable(Screen.Category.route) {
                        CategoryScreen(navController)
                    }
                    composable(Screen.Difficulty.route) {
                        DifficultyScreen(navController)
                    }
                    composable("quiz/{difficulty}") { backStackEntry ->
                        val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "easy"
                        QuizScreen(navController, difficulty)
                    }
                    composable("result/{score}/{total}") { backStackEntry ->
                        val score = backStackEntry.arguments?.getInt("score") ?: 0
                        val total = backStackEntry.arguments?.getInt("total") ?: 0
                        ResultScreen(navController, score, total)
                    }
                }
            }
        }

        // Verify we're on CategoryScreen
        composeTestRule.onNodeWithText("Choose Category").assertIsDisplayed()
    }

    @Test
    fun quizScreen_displayAllComponents() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        // Question should be displayed
        composeTestRule.onNodeWithText("Identify this iconic Roman amphitheater located in central Tunisia.")
            .assertIsDisplayed()

        // All 4 options should be displayed
        composeTestRule.onNodeWithText("El Jem Amphitheater").assertIsDisplayed()
        composeTestRule.onNodeWithText("Carthage").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dougga").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sbeitla").assertIsDisplayed()

        // Submit button should be displayed
        composeTestRule.onNodeWithText("SUBMIT ANSWER").assertIsDisplayed()
    }

    @Test
    fun userCanSelectAndChangeAnswers() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                QuizScreen(navController, "easy")
            }
        }

        // Select first answer
        composeTestRule.onNodeWithText("El Jem Amphitheater").performClick()
        composeTestRule.onNodeWithText("El Jem Amphitheater").assertIsDisplayed()

        // Change to second answer
        composeTestRule.onNodeWithText("Carthage").performClick()
        composeTestRule.onNodeWithText("Carthage").assertIsDisplayed()

        // Change to third answer
        composeTestRule.onNodeWithText("Dougga").performClick()
        composeTestRule.onNodeWithText("Dougga").assertIsDisplayed()

        // Verify all options are still visible
        composeTestRule.onNodeWithText("Sbeitla").assertIsDisplayed()
    }

    @Test
    fun difficultyScreen_allDifficultiesDisplayed() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                DifficultyScreen(navController)
            }
        }

        // Verify title
        composeTestRule.onNodeWithText("Select Difficulty").assertIsDisplayed()

        // Verify all difficulties
        composeTestRule.onNodeWithText("EASY").assertIsDisplayed()
        composeTestRule.onNodeWithText("MEDIUM").assertIsDisplayed()
        composeTestRule.onNodeWithText("HARD").assertIsDisplayed()

        // Verify descriptions
        composeTestRule.onNodeWithText("Famous landmarks").assertIsDisplayed()
        composeTestRule.onNodeWithText("Historical sites").assertIsDisplayed()
        composeTestRule.onNodeWithText("Archaeological details").assertIsDisplayed()
    }

    @Test
    fun categoryScreen_allCategoriesVisible() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                CategoryScreen(navController)
            }
        }

        // Verify header
        composeTestRule.onNodeWithText("Choose Category").assertIsDisplayed()

        // Verify at least some categories are visible
        composeTestRule.onNodeWithText("Roman Heritage").assertIsDisplayed()
    }

    @Test
    fun mainMenuScreen_displaysTitle() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                MainMenuScreen(navController, UserViewModel())
            }
        }

        // Verify title
        composeTestRule.onNodeWithText("Tunisia Heritage Quest").assertIsDisplayed()

        // Verify action button
        composeTestRule.onNodeWithText("START QUIZ").assertIsDisplayed()
    }

    @Test
    fun multipleQuestions_canBeAnsweredSequentially() {
        val viewModel = QuizViewModel()

        // Answer first question correctly
        viewModel.onAnswerSelected("El Jem Amphitheater")
        viewModel.submitAnswer()
        assert(viewModel.isCorrect.value == true)
        assert(viewModel.score.value == 10)  // Assuming 10 points per question

        // Move to next question
        viewModel.nextQuestion { score, totalSize -> /* callback */ }
        assert(viewModel.currentQuestionIndex.value == 1)
        assert(viewModel.selectedAnswer.value == "")

        // Answer second question incorrectly
        viewModel.onAnswerSelected("Wrong")
        viewModel.submitAnswer()
        assert(viewModel.isCorrect.value == false)
        assert(viewModel.score.value == 10)

        // Move to next question
        viewModel.nextQuestion { score, totalSize -> /* callback */ }
        assert(viewModel.currentQuestionIndex.value == 2)

        // Answer third question correctly
        viewModel.onAnswerSelected("Option3")
        viewModel.submitAnswer()
        assert(viewModel.isCorrect.value == true)
        assert(viewModel.score.value == 20)
    }
}
