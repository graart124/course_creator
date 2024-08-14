package com.example.coursecreator.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    override val name: String,
    val components: List<CourseComponent> = emptyList()
) : CourseComponent()
