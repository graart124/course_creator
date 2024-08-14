package com.example.coursecreator.ui.util

import android.os.Parcelable
import androidx.navigation.NamedNavArgument
import com.example.coursecreator.data.JsonCourseAdapter
import com.example.coursecreator.model.CourseComponent
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.parcelize.Parcelize

private val gson: Gson = GsonBuilder()
    .registerTypeAdapter(CourseComponent::class.java, JsonCourseAdapter())
    .create()

sealed class Screen(val route: String, val navArguments: List<NamedNavArgument> = emptyList()) {
    data object Main : Screen("main")
    data object Course : Screen("course/{courseArgs}") {
        @Parcelize
        data class CourseArgs(
            val course: com.example.coursecreator.model.Course
        ) : Parcelable {
            fun toJson(): String {
                val gson: Gson = GsonBuilder()
                    .registerTypeAdapter(CourseComponent::class.java, JsonCourseAdapter())
                    .create()
                return gson.toJson(this)
            }
        }

        const val Args = "courseArgs"

        fun createRoute(course: com.example.coursecreator.model.Course): String {
            val courseArgs = CourseArgs(course)
            return "course/${courseArgs.toJson()}"
        }
    }

    data object TextLesson : Screen("textLesson/{textLessonArgs}") {
        @Parcelize
        data class TextLessonArgs(
            val textLesson: com.example.coursecreator.model.TextLesson
        ) : Parcelable {
            fun toJson(): String {
                return gson.toJson(this)
            }
        }

        const val Args = "textLessonArgs"

        fun createRoute(textLesson: com.example.coursecreator.model.TextLesson): String {
            val courseArgs = TextLessonArgs(textLesson)
            return "textLesson/${courseArgs.toJson()}"
        }
    }

    data object AudioLesson : Screen("audioLesson/{audioLessonArgs}") {
        @Parcelize
        data class AudioLessonArgs(
            val audioLesson: com.example.coursecreator.model.AudioLesson
        ) : Parcelable {
            fun toJson(): String {
                return gson.toJson(this)
            }
        }

        const val Args = "audioLessonArgs"

        fun createRoute(audioLesson: com.example.coursecreator.model.AudioLesson): String {
            val courseArgs = AudioLessonArgs(audioLesson)
            return "audioLesson/${courseArgs.toJson()}"
        }
    }
}

inline fun <reified T> String.fromJson(): T {
    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(CourseComponent::class.java, JsonCourseAdapter())
        .create()

    return gson.fromJson(this, T::class.java)
}
