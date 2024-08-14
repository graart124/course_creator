package com.example.coursecreator.data

import com.example.coursecreator.model.Course

interface CourseAdapter {
    fun saveCourses(courses: List<Course>)
    fun fetchCourses(): List<Course>
}
