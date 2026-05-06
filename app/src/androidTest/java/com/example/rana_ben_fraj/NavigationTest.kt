package com.example.rana_ben_fraj

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rana_ben_fraj.ui.theme.Rana_Ben_FrajTheme
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigationGraph_startsAtSplashScreen() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Splash.route) {
                    composable(Screen.Splash.route) { SplashScreen(navController) }
g                    composable(Screen.MainMenu.route) { MainMenuScreen(navController, UserViewModel()) }
                    composable(Screen.Category.route) { CategoryScreen(navController) }
                    composable(Screen.Difficulty.route) { DifficultyScreen(navController) }
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

        // Verify SplashScreen is displayed
        composeTestRule.onNodeWithText("Tunisia Heritage Quest").assertIsDisplayed()
    }

    @Test
    fun navigation_mainMenu_isCategoryScreen() {
        var currentScreen = Screen.MainMenu.route
        
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.MainMenu.route) {
                    composable(Screen.MainMenu.route) { 
                        MainMenuScreen(navController, UserViewModel())
                        currentScreen = Screen.MainMenu.route
                    }
                    composable(Screen.Category.route) { 
                        CategoryScreen(navController)
                        currentScreen = Screen.Category.route
                    }
                    composable(Screen.Difficulty.route) { DifficultyScreen(navController) }
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

        // Verify MainMenuScreen is displayed
        composeTestRule.onNodeWithText("Tunisia Heritage Quest").assertIsDisplayed()
    }

    @Test
    fun navigation_categoryScreen_displaysContent() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Category.route) {
                    composable(Screen.Category.route) { CategoryScreen(navController) }
                    composable(Screen.Difficulty.route) { DifficultyScreen(navController) }
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

        // Verify CategoryScreen is displayed
        composeTestRule.onNodeWithText("Choose Category").assertIsDisplayed()
    }

    @Test
    fun navigation_difficultyScreen_displaysContent() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Difficulty.route) {
                    composable(Screen.Difficulty.route) { DifficultyScreen(navController) }
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

        // Verify DifficultyScreen is displayed
        composeTestRule.onNodeWithText("Select Difficulty").assertIsDisplayed()
    }

    @Test
    fun navigation_quizScreen_displaysContent() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "quiz/easy") {
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

        // Verify QuizScreen is displayed
        composeTestRule.onNodeWithText("Identify this iconic Roman amphitheater located in central Tunisia.")
            .assertIsDisplayed()
    }

    @Test
    fun navigation_resultScreen_displaysContent() {
        composeTestRule.setContent {
            Rana_Ben_FrajTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "result/0/0") {
                    composable("result/{score}/{total}") { backStackEntry ->
                        val score = backStackEntry.arguments?.getInt("score") ?: 0
                        val total = backStackEntry.arguments?.getInt("total") ?: 0
                        ResultScreen(navController, score, total)
                    }
                }
            }
        }

        // Verify ResultScreen is displayed
        composeTestRule.onNodeWithText("Quiz Results").assertIsDisplayed()
    }

    @Test
    fun navigation_allScreensExist() {
        val screens = listOf(
            Screen.Splash,
            Screen.MainMenu,
            Screen.Category,
            Screen.Difficulty,
            Screen.Quiz,
            Screen.Result
        )

        for (screen in screens) {
            assert(screen.route.isNotEmpty()) { "Screen route cannot be empty for ${screen::class.simpleName}" }
        }
    }

    @Test
    fun navigation_splashScreenRoute_isCorrect() {
        assert(Screen.Splash.route == "splash")
    }

    @Test
    fun navigation_mainMenuRoute_isCorrect() {
        assert(Screen.MainMenu.route == "main_menu")
    }

    @Test
    fun navigation_categoryRoute_isCorrect() {
        assert(Screen.Category.route == "category")
    }

    @Test
    fun navigation_difficultyRoute_isCorrect() {
        assert(Screen.Difficulty.route == "difficulty")
    }

    @Test
    fun navigation_quizRoute_isCorrect() {
        assert(Screen.Quiz.route == "quiz/{difficulty}")
    }

    @Test
    fun navigation_resultRoute_isCorrect() {
        assert(Screen.Result.route == "result/{score}/{total}")
    }
}
