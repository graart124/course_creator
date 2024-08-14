package com.example.coursecreator.model.decorator

import com.example.coursecreator.model.Lesson

abstract class LessonDecorator(name: String) : Lesson(name) {
    abstract val decoratedLesson: Lesson
}
