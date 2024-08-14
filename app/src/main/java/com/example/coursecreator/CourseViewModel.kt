package com.example.coursecreator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursecreator.data.CachingCourseRepository
import com.example.coursecreator.data.CourseRepositoryImpl
import com.example.coursecreator.domain.AudioResource
import com.example.coursecreator.domain.AudioResourceFlyWeightFactory
import com.example.coursecreator.domain.CourseRepository
import com.example.coursecreator.model.AudioLesson
import com.example.coursecreator.model.Course
import com.example.coursecreator.model.Lesson
import com.example.coursecreator.model.decorator.QuizDecorator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CourseAppUIState(
    val courses: List<Course> = emptyList(),
)

class CourseViewModel(
    private val courseRepository: CourseRepository = CachingCourseRepository(CourseRepositoryImpl()),
    private val audioResourceFlyWeightFactory: AudioResourceFlyWeightFactory = AudioResourceFlyWeightFactory()
) : ViewModel() {
    private val _uiState = MutableStateFlow(CourseAppUIState())
    val uiState: StateFlow<CourseAppUIState> = _uiState

    init {
        viewModelScope.launch {
            val fetchedCourses = courseRepository.fetchCourses()
            _uiState.value = CourseAppUIState(courses = fetchedCourses)
        }
    }

    fun addQuizLesson(lesson: Lesson, quizQuestion: String) {
        val quizLesson = QuizDecorator(decoratedLesson = lesson, quizQuestion = quizQuestion)
        val quizQuestionFromDecorator = quizLesson.getQuizQuestion()
    }

    fun getAudioResource(audioLesson: AudioLesson): AudioResource {
        return  audioResourceFlyWeightFactory.getAudioResource(audioLesson.audioRes)
    }
}
