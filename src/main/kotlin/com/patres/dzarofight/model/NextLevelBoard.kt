package com.patres.dzarofight.model

import com.patres.dzarofight.helper.ColorKeeper
import com.patres.dzarofight.helper.fill
import com.patres.dzarofight.helper.stroke
import processing.core.PApplet
import processing.core.PVector

class NextLevelBoard(
        val board: Board
) {

    companion object {
        val BUTTON_SIZE = PVector(300f, 80f)
    }

    val pApplet = board.pApplet
    val image = board.imageKeeper.nextLevel
    val buttonPosiviotn = PVector(pApplet.width / 2f - BUTTON_SIZE.x / 2f, 615f)


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
            pApplet.cursor(PApplet.ARROW)
        }
        pApplet.fill(ColorKeeper.BLOOD, alpha)
        pApplet.stroke(ColorKeeper.BLOOD, 50f)
    }

    private fun display() {
        pApplet.image(image, pApplet.width / 2f - image.width / 2f, pApplet.height / 2f - image.height / 2f)
        pApplet.rect(buttonPosiviotn.x, buttonPosiviotn.y, BUTTON_SIZE.x, BUTTON_SIZE.y)
    }

    fun isInButton() = pApplet.mouseX in (buttonPosiviotn.x..BUTTON_SIZE.x + buttonPosiviotn.x) && pApplet.mouseY in (buttonPosiviotn.y..BUTTON_SIZE.y + buttonPosiviotn.y)
}


