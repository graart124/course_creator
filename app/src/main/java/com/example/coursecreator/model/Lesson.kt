package com.example.coursecreator.model

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
open class Lesson(
    @SerializedName("lesson_name")
    override val name: String,
    @IgnoredOnParcel val type: LessonType = listOf(
        IntroLesson(),
        PremiumLesson(),
        DefaultLesson(),
        AdvancedLesson(),
        ExpertLesson()
    ).random()
) : CourseComponent()

@Parcelize
data class AudioLesson(
    @SerializedName("audio_lesson_name")
    override val name: String,
    val audioRes: Int
) : Lesson(name)

@Parcelize
data class TextLesson(
    @SerializedName("text_lesson_name")
    override val name: String,
    val content: String
) : Lesson(name)
