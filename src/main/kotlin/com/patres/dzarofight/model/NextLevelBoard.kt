package com.patres.dzarofight.model

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.helper.ColorKeeper
import com.patres.dzarofight.helper.fill
import com.patres.dzarofight.helper.stroke
import processing.core.PApplet
import processing.core.PVector

class NextLevelBoard(
        val board: Board
) {

    companion object {
        val BUTTON_SIZE = PVector(MainSketch.SIZE_X * 0.15f, MainSketch.SIZE_Y * 0.07f)
    }

    val pApplet = board.pApplet
    val image = board.imageKeeper.nextLevel
    private val buttonPosition = PVector(pApplet.width / 2f - BUTTON_SIZE.x / 2f, MainSketch.SIZE_Y * 0.57f)


    fun draw() {
        setup()
        display()
    }

    private fun setup() {
        val alpha: Float
        if (isInButton()) {
            alpha = 100f
            pApplet.cursor(PApplet.HAND)
        } else {
            alpha = 50f
        }
        pApplet.fill(ColorKeeper.BLOOD, alpha)
        pApplet.stroke(ColorKeeper.BLOOD, 50f)
    }

    private fun display() {
        pApplet.image(image, pApplet.width / 2f - image.width / 2f, pApplet.height / 2f - image.height / 2f)
        pApplet.rect(buttonPosition.x, buttonPosition.y, BUTTON_SIZE.x, BUTTON_SIZE.y)
    }

    fun isInButton() = pApplet.mouseX in (buttonPosition.x..BUTTON_SIZE.x + buttonPosition.x) && pApplet.mouseY in (buttonPosition.y..BUTTON_SIZE.y + buttonPosition.y)
}


