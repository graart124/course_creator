package com.example.coursecreator.model.decorator

import com.example.coursecreator.model.Lesson

class QuizDecorator(override val decoratedLesson: Lesson, private val quizQuestion: String) :
    LessonDecorator(decoratedLesson.name) {

    override val name: String
        get() = decoratedLesson.name

    fun getQuizQuestion(): String {
        return quizQuestion
    }
}
