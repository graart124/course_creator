package com.example.coursecreator.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BackButton(onBack: () -> Unit) {
    IconButton(onClick = onBack) {
        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
    }
}