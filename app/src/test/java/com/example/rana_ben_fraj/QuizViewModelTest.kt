package com.example.rana_ben_fraj

import org.junit.Assert.assertEquals
import org.junit.Test

class QuizViewModelTest {

    @Test
    fun quizViewModel_initialState_isCorrect() {
        val viewModel = QuizViewModel()
        assertEquals(0, viewModel.score.value)
        assertEquals(0, viewModel.currentQuestionIndex.value)
        assertEquals("", viewModel.selectedAnswer.value)
        assertEquals(false, viewModel.showResult.value)
    }

    @Test
    fun quizViewModel_loadQuestions_filtersCorrectly() {
        val viewModel = QuizViewModel()
        viewModel.loadQuestions("easy")
        assertEquals(5, viewModel.questions.size)
        viewModel.questions.forEach {
            assertEquals("easy", it.difficulty)
        }
    }

    @Test
    fun quizViewModel_submitCorrectAnswer_updatesScore() {
        val viewModel = QuizViewModel()
        viewModel.loadQuestions("easy")
        val currentQuestion = viewModel.questions[0]
        
        viewModel.onAnswerSelected(currentQuestion.correctAnswer)
        viewModel.submitAnswer()
        
        assertEquals(10, viewModel.score.value)
        assertEquals(true, viewModel.isCorrect.value)
        assertEquals(true, viewModel.showResult.value)
    }

    @Test
    fun quizViewModel_nextQuestion_resetsState() {
        val viewModel = QuizViewModel()
        viewModel.loadQuestions("easy")
        
        viewModel.onAnswerSelected(viewModel.questions[0].correctAnswer)
        viewModel.submitAnswer()
        viewModel.nextQuestion { _, _ -> }
        
        assertEquals(1, viewModel.currentQuestionIndex.value)
        assertEquals("", viewModel.selectedAnswer.value)
        assertEquals(false, viewModel.showResult.value)
    }
}
