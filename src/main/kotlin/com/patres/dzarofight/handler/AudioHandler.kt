package com.patres.dzarofight.handler

import ddf.minim.AudioInput
import ddf.minim.Minim

class AudioHandler(
        private val minim: Minim,
        private val audioInput: AudioInput = minim.getLineIn(Minim.STEREO, 512)
) {

    private val volumeLevel = 0.5

    fun getVolume(): Float = audioInput.mix.level()

    fun isLoud() = audioInput.mix.level() > volumeLevel

}