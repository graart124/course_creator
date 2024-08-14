package com.example.coursecreator.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coursecreator.model.Course
import com.example.coursecreator.ui.components.CourseItem

@Composable
fun MainScreen(courses: List<Course>, onAddClick: () -> Unit, onCourseClick: (Course) -> Unit) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding()
            .systemBarsPadding()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Filled.Add, contentDescription = "Add Course")
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(courses) { course ->
                CourseItem(course){
                    onCourseClick(course)
                }
            }
        }
    }
}
