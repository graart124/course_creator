package com.example.coursecreator.data

import com.example.coursecreator.R
import com.example.coursecreator.model.AudioLesson
import com.example.coursecreator.model.Course
import com.example.coursecreator.model.CourseComponent
import com.example.coursecreator.model.TextLesson
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class CourseDataSource : CourseAdapter {

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(CourseComponent::class.java, JsonCourseAdapter())
        .create()
    private val serializedCourses = mutableListOf<String>()

    init {
        saveCourses(generateCourses())
    }

    override fun saveCourses(courses: List<Course>) {
        courses.forEach { courseComponent ->
            saveCourseComponent(courseComponent)
        }
    }

    private fun saveCourseComponent(courseComponent: CourseComponent) {
        val json = gson.toJson(courseComponent)
        serializedCourses.add(json)
    }

    override fun fetchCourses(): List<Course> {
        return serializedCourses.mapNotNull { json ->
            gson.fromJson(json, Course::class.java)
        }
    }
}

private fun generateCourses(): List<Course> {
    val lesson1 = TextLesson(name = "Introduction", content = "Some content")
    val lesson2 = AudioLesson(name = "Chapter 1", audioRes = R.raw.mixkit_light_rain)
    val lesson3 = TextLesson(name = "Chapter 2", content = "Chapter 2 content")

    val subCourse = Course(name = "Advanced Topics", components = listOf(lesson3))

    val mainCourse1 =
        Course(name = "Main Course 1", components = listOf(lesson1, lesson2, subCourse))
    val mainCourse2 = Course(name = "Main Course 2", components = listOf(lesson1, lesson2))

    val courses = listOf(
        mainCourse1,
        mainCourse2,
        Course(
            name = "Introduction to Programming",
            components = listOf(
                AudioLesson(name = "Welcome to Programming", audioRes = R.raw.mixkit_rain),
                TextLesson(
                    name = "Getting Started with Python",
                    content = "In this lesson, you will learn the basics of Python programming."
                )
            )
        ),
        Course(
            name = "Advanced Java",
            components = listOf(
                TextLesson(
                    name = "Concurrency in Java",
                    content = "This lesson covers advanced topics in Java concurrency."
                )
            )
        ),
        Course(
            name = "Web Development Basics",
            components = listOf(
                AudioLesson(name = "HTML & CSS", audioRes = R.raw.mixkit_wolves),
                TextLesson(
                    name = "Introduction to JavaScript",
                    content = "Learn the basics of JavaScript for interactive web development."
                )
            )
        ),
        Course(
            name = "Data Science with Python",
            components = listOf(
                TextLesson(
                    name = "Data Analysis with Pandas",
                    content = "This lesson introduces data analysis using the Pandas library in Python."
                )
            )
        ),
        Course(
            name = "Machine Learning",
            components = listOf(
                AudioLesson(
                    name = "Supervised Learning",
                    audioRes = R.raw.mixkit_calm_thunderstorm
                ),
                TextLesson(
                    name = "Unsupervised Learning",
                    content = "Learn about unsupervised learning algorithms in this lesson."
                )
            )
        ),
        Course(
            name = "Mobile App Development",
            components = listOf(
                TextLesson(
                    name = "Building Your First App",
                    content = "This lesson guides you through building your first Android application."
                )
            )
        )
    )
    return courses
}
