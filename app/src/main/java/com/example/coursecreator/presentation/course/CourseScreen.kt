package com.example.coursecreator.presentation.course

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.coursecreator.model.AudioLesson
import com.example.coursecreator.model.Course
import com.example.coursecreator.model.Lesson
import com.example.coursecreator.model.TextLesson
import com.example.coursecreator.ui.components.BackButton
import com.example.coursecreator.ui.components.CourseItem

@Composable
fun CourseScreen(
    course: Course,
    onEditClick: () -> Unit,
    onBack: () -> Unit,
    onCourseClick: (Course) -> Unit,
    onAudioLessonClick: (AudioLesson) -> Unit,
    onTextLessonClick: (TextLesson) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding()
            .systemBarsPadding()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = onEditClick) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit Course")
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(top = it.calculateTopPadding())
        ) {
            Row {
                BackButton {
                    onBack()
                }
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = course.name,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(course.components) { courseComponent ->
                    if (courseComponent is Course) {
                        CourseItem(courseComponent, onClick = { onCourseClick(courseComponent) })
                    } else {
                        LessonItem(lesson = courseComponent as Lesson, onClick = {
                            if (courseComponent is AudioLesson) {
                                onAudioLessonClick(courseComponent)
                            } else if (courseComponent is TextLesson) {
                                onTextLessonClick(courseComponent)
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun LessonItem(lesson: Lesson, onClick: () -> Unit = {}) {
    val isAudioLesson = lesson is AudioLesson

    Row(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(16.dp),
                color = if (isAudioLesson) Color(0xFF74C8D3) else
                    Color(0xFF74C8D3)
            )
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = lesson.name,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = if (isAudioLesson) "Audio" else "Text",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
