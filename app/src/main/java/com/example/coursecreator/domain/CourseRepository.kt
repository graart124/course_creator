package com.example.coursecreator.domain

import com.example.coursecreator.model.Course

interface CourseRepository {
    fun fetchCourses(): List<Course>
}
