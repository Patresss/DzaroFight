package com.patres.dzarofight.model.popup

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.helper.ColorKeeper
import com.patres.dzarofight.helper.fill
import com.patres.dzarofight.model.Board
import processing.core.PApplet
import processing.core.PImage
import processing.core.PVector

abstract class OptionPopup(
        val board: Board,
        val buttonSize: PVector,
        val image: PImage
) {

    val pApplet = board.pApplet
    private val buttonPosition = PVector(pApplet.width / 2f - buttonSize.x / 2f, MainSketch.SIZE_Y * 0.57f)

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
        pApplet.fill(ColorKeeper.GRAY, alpha)
        pApplet.noStroke()
    }

    private fun display() {
        pApplet.image(image, pApplet.width / 2f - image.width / 2f, pApplet.height / 2f - image.height / 2f)
        pApplet.rect(buttonPosition.x, buttonPosition.y, buttonSize.x, buttonSize.y)
    }

    fun isInButton() = pApplet.mouseX in (buttonPosition.x..buttonSize.x + buttonPosition.x) && pApplet.mouseY in (buttonPosition.y..buttonSize.y + buttonPosition.y)
}


