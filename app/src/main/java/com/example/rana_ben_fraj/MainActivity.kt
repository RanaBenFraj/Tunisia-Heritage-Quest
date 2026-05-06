package com.example.rana_ben_fraj

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rana_ben_fraj.ui.theme.Rana_Ben_FrajTheme
import kotlinx.coroutines.delay

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object MainMenu : Screen("main_menu")
    object Category : Screen("category")
    object Difficulty : Screen("difficulty")
    object Quiz : Screen("quiz/{difficulty}")
    object Result : Screen("result/{score}/{total}")
}

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity created")
        enableEdgeToEdge()
        setContent {
            Rana_Ben_FrajTheme {
                val userViewModel: UserViewModel = viewModel()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Splash.route) {
                    composable(Screen.Splash.route) { SplashScreen(navController) }
                    composable(Screen.MainMenu.route) { MainMenuScreen(navController, userViewModel) }
                    composable(Screen.Category.route) { CategoryScreen(navController) }
                    composable(Screen.Difficulty.route) { DifficultyScreen(navController) }
                    composable(
                        route = Screen.Quiz.route,
                        arguments = listOf(navArgument("difficulty") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "easy"
                        QuizScreen(navController, difficulty)
                    }
                    composable(
                        route = Screen.Result.route,
                        arguments = listOf(
                            navArgument("score") { type = NavType.IntType },
                            navArgument("total") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val score = backStackEntry.arguments?.getInt("score") ?: 0
                        val total = backStackEntry.arguments?.getInt("total") ?: 0
                        
                        // Update stats only once when entering ResultScreen
                        LaunchedEffect(Unit) {
                            userViewModel.updateStats(score / 10, total)
                        }
                        
                        ResultScreen(navController, score, total)
                    }
                }
            }
        }
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screen.MainMenu.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF003366)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = Icons.Filled.Home, contentDescription = null, tint = Color.White, modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Tunisia Heritage Quest", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MainMenuScreen(navController: NavController, userViewModel: UserViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Tunisia Heritage Quest", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            StatCard(icon = Icons.Filled.LocationOn, value = "10", label = "Sites")
            StatCard(icon = Icons.Filled.Star, value = "${userViewModel.getMastery()}%", label = "Mastery")
            StatCard(icon = Icons.Filled.Settings, value = "${userViewModel.streak.value}", label = "Streak")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { navController.navigate(Screen.Category.route) }, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Text("START QUIZ")
        }
    }
}

@Composable
fun StatCard(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String, label: String) {
    Card(modifier = Modifier.padding(4.dp)) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = icon, contentDescription = null)
            Text(text = value, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = label, fontSize = 14.sp)
        }
    }
}

@Composable
fun CategoryScreen(navController: NavController) {
    val categories = listOf(
        CategoryItem("Roman Heritage", Icons.Filled.Home, "10 questions"),
        CategoryItem("Islamic Heritage", Icons.Filled.Info, "10 questions"),
        CategoryItem("Punic & Pre-Roman", Icons.Filled.Info, "10 questions"),
        CategoryItem("Modern Heritage", Icons.Filled.Home, "10 questions"),
        CategoryItem("Natural & Mixed Sites", Icons.Filled.LocationOn, "10 questions"),
        CategoryItem("Cities", Icons.Filled.Home, "10 questions")
    )
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) { Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") }
            Text(text = "Choose Category", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(8.dp)) {
            items(categories.size) { index ->
                val category = categories[index]
                CategoryItemCard(name = category.name, icon = category.icon, questionsText = category.questionsText, onClick = { navController.navigate(Screen.Difficulty.route) })
            }
        }
    }
}

data class CategoryItem(val name: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val questionsText: String)

@Composable
fun CategoryItemCard(name: String, icon: androidx.compose.ui.graphics.vector.ImageVector, questionsText: String, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().height(150.dp).clickable(onClick = onClick), shape = RoundedCornerShape(12.dp)) {
        Column(modifier = Modifier.fillMaxSize().padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = questionsText, fontSize = 12.sp, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun DifficultyScreen(navController: NavController) {
    val timerEnabled = remember { mutableStateOf(true) }
    val soundEnabled = remember { mutableStateOf(true) }
    val hapticEnabled = remember { mutableStateOf(true) }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { Text(text = "Select Difficulty", fontWeight = FontWeight.Bold, fontSize = 24.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)) }
        item { DifficultyCard(title = "EASY", description = "Famous landmarks", time = "15 seconds", onClick = { navController.navigate("quiz/easy") }) }
        item { Spacer(modifier = Modifier.height(12.dp)); DifficultyCard(title = "MEDIUM", description = "Historical sites", time = "20 seconds", onClick = { navController.navigate("quiz/medium") }) }
        item { Spacer(modifier = Modifier.height(12.dp)); DifficultyCard(title = "HARD", description = "Archaeological details", time = "15 seconds", onClick = { navController.navigate("quiz/hard") }) }
        item { Spacer(modifier = Modifier.height(32.dp)); Text(text = "Game Options", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(bottom = 16.dp)) }
        item { GameOptionSwitch(label = "Timer Enabled", checked = timerEnabled.value, onCheckedChange = { timerEnabled.value = it }) }
        item { Spacer(modifier = Modifier.height(12.dp)); GameOptionSwitch(label = "Sound Effects", checked = soundEnabled.value, onCheckedChange = { soundEnabled.value = it }) }
        item { Spacer(modifier = Modifier.height(12.dp)); GameOptionSwitch(label = "Haptic Feedback", checked = hapticEnabled.value, onCheckedChange = { hapticEnabled.value = it }) }
    }
}

@Composable
fun DifficultyCard(title: String, description: String, time: String, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp), shape = RoundedCornerShape(12.dp)) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp)); Text(text = description, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp)); Text(text = time, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(12.dp)); Button(onClick = onClick, modifier = Modifier.align(Alignment.End)) { Text("PLAY") }
        }
    }
}

@Composable
fun GameOptionSwitch(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label, fontSize = 14.sp); Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun QuizScreen(navController: NavController, selectedDifficulty: String, viewModel: QuizViewModel = viewModel()) {
    LaunchedEffect(selectedDifficulty) { viewModel.loadQuestions(selectedDifficulty) }
    val questions = viewModel.questions
    if (questions.isEmpty()) { Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("No questions found.") }; return }
    val currentQuestion = questions[viewModel.currentQuestionIndex.value]

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) { Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Question ${viewModel.currentQuestionIndex.value + 1} / ${questions.size}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
        BoxWithConstraints(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
            val isWideScreen = maxWidth > 600.dp
            val scrollState = rememberScrollState()
            if (isWideScreen) {
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    Image(painter = painterResource(id = currentQuestion.imageRes), contentDescription = null, modifier = Modifier.weight(1f).height(400.dp), contentScale = ContentScale.Crop)
                    Column(modifier = Modifier.weight(1f).verticalScroll(scrollState)) { QuizContent(currentQuestion, viewModel, navController, questions.size) }
                }
            } else {
                Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
                    Image(painter = painterResource(id = currentQuestion.imageRes), contentDescription = null, modifier = Modifier.fillMaxWidth().height(200.dp), contentScale = ContentScale.Crop)
                    Spacer(modifier = Modifier.height(16.dp))
                    QuizContent(currentQuestion, viewModel, navController, questions.size)
                }
            }
        }
    }
}

@Composable
fun QuizContent(currentQuestion: QuizQuestion, viewModel: QuizViewModel, navController: NavController, total: Int) {
    Text(text = currentQuestion.question, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(24.dp))
    currentQuestion.options.forEach { option ->
        val isSelected = viewModel.selectedAnswer.value == option
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable(enabled = !viewModel.showResult.value) { viewModel.onAnswerSelected(option) },
            colors = CardDefaults.cardColors(containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant),
            border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null
        ) {
            Text(text = option, modifier = Modifier.padding(16.dp).fillMaxWidth(), textAlign = TextAlign.Center)
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
    if (!viewModel.showResult.value) {
        Button(onClick = { viewModel.submitAnswer() }, modifier = Modifier.fillMaxWidth(), enabled = viewModel.selectedAnswer.value.isNotEmpty()) { Text("SUBMIT ANSWER") }
    } else {
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = if (viewModel.isCorrect.value) Color(0xFFE8F5E9) else Color(0xFFFFEBEE))) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = if (viewModel.isCorrect.value) "✅ Correct! +10 points" else "❌ Incorrect!", color = if (viewModel.isCorrect.value) Color(0xFF2E7D32) else Color(0xFFC62828), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                if (!viewModel.isCorrect.value) Text(text = "Correct answer: ${currentQuestion.correctAnswer}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp)); Text(text = currentQuestion.explanation, style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.nextQuestion { score, totalSize -> navController.navigate("result/$score/$totalSize") } }, modifier = Modifier.fillMaxWidth()) {
            Text(if (viewModel.currentQuestionIndex.value < total - 1) "NEXT QUESTION" else "FINISH QUIZ")
        }
    }
}

@Composable
fun ResultScreen(navController: NavController, score: Int, total: Int) {
    val totalPoints = total * 10
    val percentage = if (totalPoints > 0) (score.toFloat() / totalPoints) * 100 else 0f
    val message = when { percentage >= 80 -> "Excellent! 🎉"; percentage >= 50 -> "Good Job 👍"; else -> "Try Again 💪" }
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "Quiz Results", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp)); Text(text = "Score: $score / $totalPoints", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Percentage: ${percentage.toInt()}%", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(24.dp)); Text(text = message, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = { navController.navigate(Screen.MainMenu.route) { popUpTo(Screen.MainMenu.route) { inclusive = true } } }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) { Text("Play Again", fontSize = 18.sp, modifier = Modifier.padding(8.dp)) }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Screen.MainMenu.route) { popUpTo(0) } }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) { Text("Exit", fontSize = 18.sp, modifier = Modifier.padding(8.dp)) }
    }
}
