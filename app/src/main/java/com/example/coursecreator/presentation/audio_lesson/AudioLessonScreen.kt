package com.example.coursecreator.presentation.audio_lesson

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursecreator.CourseViewModel
import com.example.coursecreator.R
import com.example.coursecreator.domain.AudioResource
import com.example.coursecreator.model.AudioLesson
import com.example.coursecreator.ui.components.BackButton
import com.example.coursecreator.ui.util.AudioPlayer

@Composable
fun AudioLessonScreen(
    viewModel: CourseViewModel,
    audioLesson: AudioLesson,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val audioPlayer by remember {
        mutableStateOf(
            AudioPlayer(
                context,
                viewModel.getAudioResource(audioLesson).audioResId
            )
        )
    }

    val isPlaying = audioPlayer.isPlaying.collectAsState().value

    DisposableEffect(Unit) {
        onDispose {
            audioPlayer.destroy()
        }
    }

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp)
    ) {
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
            text = audioLesson.name,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.ic_audio_lesson),
                contentDescription = "Audio Lesson Image",
            )
            Spacer(modifier = Modifier.height(16.dp))
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        if (isPlaying) {
                            audioPlayer.pause()
                        } else {
                            audioPlayer.start()
                        }
                    },
                painter = painterResource(id = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                contentDescription = "play button"
            )
        }
    }
}
