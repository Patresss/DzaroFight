package com.patres.dzarofight.handler

import ddf.minim.AudioInput
import ddf.minim.Minim

class AudioHandler(
       val minim: Minim,
       val audioInput: AudioInput = minim.getLineIn(Minim.STEREO, 512)
) {

    companion object {
        val volumeLevel = 0.5
    }

    fun getVolume(): Float = audioInput.mix.level()

    fun isLoud() = audioInput.mix.level() > volumeLevel


}