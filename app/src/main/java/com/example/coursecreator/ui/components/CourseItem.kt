package com.example.coursecreator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.coursecreator.model.Course

@Composable
fun CourseItem(course: Course, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFE0F7FA)
            )
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = course.name,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "${course.components.size} components",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}
