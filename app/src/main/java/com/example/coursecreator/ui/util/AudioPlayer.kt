package com.example.coursecreator.ui.util

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AudioPlayer(
    private val context: Context,
    private var audioId: Int
) {
    private val mediaPlayer: MediaPlayer = MediaPlayer.create(context, audioId)
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    init {
        setupMediaPlayer()
    }

    private fun setupMediaPlayer() {
        mediaPlayer.setOnCompletionListener {
            _isPlaying.value = false
        }
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
            _isPlaying.value = true
        }
    }

    fun start() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            _isPlaying.value = true
        }
    }

    fun start(audioId: Int) {
        if (this.audioId != audioId) {
            stop()
            this.audioId = audioId
            mediaPlayer.reset()
            mediaPlayer.setDataSource(
                context,
                Uri.parse("android.resource://com.example.coursecreator/$audioId")
            )
            mediaPlayer.prepareAsync()
        } else {
            start()
        }
    }

    fun stop() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare() // Prepare the MediaPlayer for the next play
            _isPlaying.value = false
        }
    }

    fun pause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            _isPlaying.value = false
        }
    }

    fun destroy() {
        mediaPlayer.release()
    }

    fun setOnCompletionListener(callback: MediaPlayer.OnCompletionListener?) {
        mediaPlayer.setOnCompletionListener(callback)
    }
}
