package com.example.coursecreator.presentation.text_lesson

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coursecreator.model.TextLesson
import com.example.coursecreator.ui.components.BackButton

@Composable
fun TextLessonScreen(
    textLesson: TextLesson,
    onBack: () -> Unit
) {

    Column(modifier = Modifier
        .systemBarsPadding()
        .navigationBarsPadding()
        .padding(16.dp)) {
        Row {
            BackButton {
                onBack()
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Lesson Name",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = textLesson.name,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Content",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = textLesson.content,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
