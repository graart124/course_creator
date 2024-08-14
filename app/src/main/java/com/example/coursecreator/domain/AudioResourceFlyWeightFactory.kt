package com.example.coursecreator.domain

class AudioResourceFlyWeightFactory {
    private val audioResources = mutableMapOf<Int, AudioResource>()

    fun getAudioResource(audioResId: Int): AudioResource {
        return audioResources.getOrPut(audioResId) {
            AudioResource(audioResId)
        }
    }
}

data class AudioResource(val audioResId: Int)
