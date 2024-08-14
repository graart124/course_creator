package com.example.coursecreator.data

import com.example.coursecreator.domain.CourseRepository
import com.example.coursecreator.model.Course

class CachingCourseRepository(private val courseRepository: CourseRepository) : CourseRepository {
    private var cachedCourses: List<Course>? = null

    override fun fetchCourses(): List<Course> {
        if (cachedCourses == null) {
            cachedCourses = courseRepository.fetchCourses()
        }
        return cachedCourses ?: emptyList()
    }
}
