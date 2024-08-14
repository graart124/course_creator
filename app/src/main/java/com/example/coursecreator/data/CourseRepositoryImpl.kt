package com.example.coursecreator.data

import com.example.coursecreator.domain.CourseRepository
import com.example.coursecreator.model.Course

class CourseRepositoryImpl(private val dataSource: CourseDataSource = CourseDataSource()) :
    CourseRepository {
    override fun fetchCourses(): List<Course> {
        return dataSource.fetchCourses()
    }
}
