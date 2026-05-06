package com.example.rana_ben_fraj

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    private val _questions = mutableStateListOf<QuizQuestion>()
    val questions: List<QuizQuestion> = _questions

    private val _currentQuestionIndex = mutableStateOf(0)
    val currentQuestionIndex: State<Int> = _currentQuestionIndex

    private val _score = mutableStateOf(0)
    val score: State<Int> = _score

    private val _selectedAnswer = mutableStateOf("")
    val selectedAnswer: State<String> = _selectedAnswer

    private val _showResult = mutableStateOf(false)
    val showResult: State<Boolean> = _showResult

    private val _isCorrect = mutableStateOf(false)
    val isCorrect: State<Boolean> = _isCorrect

    fun loadQuestions(difficulty: String) {
        if (_questions.isEmpty()) {
            _questions.clear()
            _questions.addAll(romanHeritageQuestions.filter { it.difficulty == difficulty })
        }
    }

    fun onAnswerSelected(answer: String) {
        if (!_showResult.value) {
            _selectedAnswer.value = answer
        }
    }

    fun submitAnswer() {
        val currentQuestion = _questions.getOrNull(_currentQuestionIndex.value)
        if (currentQuestion != null && _selectedAnswer.value.isNotEmpty() && !_showResult.value) {
            _isCorrect.value = _selectedAnswer.value == currentQuestion.correctAnswer
            if (_isCorrect.value) {
                _score.value += 10
            }
            _showResult.value = true
        }
    }

    fun nextQuestion(onQuizFinished: (Int, Int) -> Unit) {
        if (_currentQuestionIndex.value < _questions.size - 1) {
            _currentQuestionIndex.value++
            _selectedAnswer.value = ""
            _showResult.value = false
        } else {
            onQuizFinished(_score.value, _questions.size)
        }
    }
}

class UserViewModel : ViewModel() {
    private val _totalCorrect = mutableStateOf(0)
    private val _totalAttempted = mutableStateOf(0)
    private val _streak = mutableStateOf(0)

    val streak: State<Int> = _streak

    fun getMastery(): Int {
        val attempted = _totalAttempted.value
        return if (attempted > 0) {
            ((_totalCorrect.value.toFloat() / attempted) * 100).toInt()
        } else 0
    }

    fun updateStats(sessionCorrect: Int, sessionTotal: Int) {
        _totalCorrect.value += sessionCorrect
        _totalAttempted.value += sessionTotal
        _streak.value += 1
    }
}
