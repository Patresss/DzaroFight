package com.patres.dzarofight.model

import com.patres.dzarofight.MainSketch
import processing.core.PVector

class PolandBall(
        val board: Board,

        val position: PVector = PVector(MainSketch.CAMERA_RESOLUTION_WIDTH / 2f, MainSketch.CAMERA_RESOLUTION_HEIGHT / 2f)
) {
    val baseRadius = 30f
    var currentRadius = baseRadius
    var hp = 100f
        set(value) {
            field = value
            currentRadius = baseRadius * hp / 100f
        }
    val pApplet = board.pApplet
    val image = board.imageKeeper.polandBallImage


    fun draw() {
        val scaledX = (position.x - currentRadius) * MainSketch.SCALE_X
        val scaledY = (position.y - currentRadius) * MainSketch.SCALE_Y
        val scaledRadius = currentRadius * MainSketch.SCALE_X
        pApplet.image(image, scaledX, scaledY , scaledRadius * 2, scaledRadius * 2)
    }
}