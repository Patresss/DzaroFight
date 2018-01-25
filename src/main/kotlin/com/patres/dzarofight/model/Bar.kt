package com.patres.dzarofight.model

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.helper.ColorKeeper
import com.patres.dzarofight.helper.fill
import com.patres.dzarofight.helper.format
import processing.core.PFont
import processing.core.PVector
import java.awt.Color
import java.lang.Float.min

class Bar(
        var board: Board
) {

    companion object {
        val fontSize = min((0.032 * MainSketch.SIZE_Y).toFloat(), (0.0182 * MainSketch.SIZE_X).toFloat())
        val imageSize = min((0.032 * MainSketch.SIZE_Y).toFloat(), (0.0182 * MainSketch.SIZE_X).toFloat())

        const val pointsColumn = (0.4 * MainSketch.SIZE_X).toFloat()
        const val killsColumn1Image = (0.69 * MainSketch.SIZE_X).toFloat()
        const val killsColumn1Text = (0.72 * MainSketch.SIZE_X).toFloat()
        const val killsColumn2Image = (0.76 * MainSketch.SIZE_X).toFloat()
        const val killsColumn2Text = (0.79 * MainSketch.SIZE_X).toFloat()

        const val row1 = (0.037 * MainSketch.SIZE_Y).toFloat()
        val row1Img = row1 - imageSize * 0.8f
        const val row2 = (0.074 * MainSketch.SIZE_Y).toFloat()
        val row2Img = row2 - imageSize * 0.8f
        const val row3 = (0.111 * MainSketch.SIZE_Y).toFloat()
        val row3Img = row3 - imageSize * 0.8f

    }

    private var position = PVector(0f, 0f)
    private val pApplet = board.pApplet
    val image = board.imageKeeper.barImage
    private var font: PFont = pApplet.loadFont("fonts/OCRAExtended-30.vlw")

    fun draw() {
        pApplet.image(image, position.x, position.y)
        drawStatistic()
    }

    private fun drawStatistic() {
        pApplet.textFont(font, fontSize)
        drawPointsStatistic()
        drawKillsStatistic()
    }

    private fun drawPointsStatistic() {
        pApplet.fill(Color.WHITE, 200f)

        pApplet.text("Hp:     ${board.polandBall.hp.format(2)}", pointsColumn, row1)
        pApplet.text("Points: ${board.statistic.getPoints()}", pointsColumn, row2)
        pApplet.text("Money:  ${board.statistic.getMoney()}", pointsColumn, row3)
    }

    private fun drawKillsStatistic() {
        pApplet.fill(ColorKeeper.BLOOD, 200f)

        pApplet.image(board.imageKeeper.tusk, killsColumn1Image, row1Img, imageSize, imageSize)
        pApplet.image(board.imageKeeper.petru, killsColumn1Image, row2Img, imageSize, imageSize)
        pApplet.image(board.imageKeeper.kukiz, killsColumn1Image, row3Img, imageSize, imageSize)

        pApplet.text("${board.statistic.tuskKill}", killsColumn1Text, row1)
        pApplet.text("${board.statistic.petruKill}", killsColumn1Text, row2)
        pApplet.text("${board.statistic.kukizKill}", killsColumn1Text, row3)


        pApplet.image(board.imageKeeper.korwin, killsColumn2Image, row1Img, imageSize, imageSize)
        pApplet.image(board.imageKeeper.kijowski, killsColumn2Image, row2Img, imageSize, imageSize)
        pApplet.image(board.imageKeeper.schetyna, killsColumn2Image, row3Img, imageSize, imageSize)

        pApplet.text("${board.statistic.korwinKill}", killsColumn2Text, row1)
        pApplet.text("${board.statistic.kijowskiKill}", killsColumn2Text, row2)
        pApplet.text("${board.statistic.schetynaKill}", killsColumn2Text, row3)
    }

}

