package com.example.coursecreator.model

import androidx.compose.ui.graphics.Color


interface LessonType {
    val name: String
    val color: Color
}

class IntroLesson : LessonType {
    override val name: String
        get() = "Intro"
    override val color: Color
        get() = Color.Red
}

class PremiumLesson : LessonType {
    override val name: String
        get() = "Premium"
    override val color: Color
        get() = Color.Yellow
}

class DefaultLesson : LessonType {
    override val name: String
        get() = "Default"
    override val color: Color
        get() = Color.Gray
}

class AdvancedLesson : LessonType {
    override val name: String
        get() = "Advanced"
    override val color: Color
        get() = Color.Green
}

class ExpertLesson : LessonType {
    override val name: String
        get() = "Expert"
    override val color: Color
        get() = Color.Magenta
}
