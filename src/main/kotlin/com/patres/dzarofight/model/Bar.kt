package com.patres.dzarofight.model

import com.patres.dzarofight.helper.ColorKeeper
import com.patres.dzarofight.helper.fill
import com.patres.dzarofight.helper.format
import processing.core.PFont
import processing.core.PVector
import java.awt.Color

class Bar(
        var board: Board
) {

    companion object {
        val fontSize = 35f
        val imageSize = 35f
        val textMargin = 20f
        val textMoveX = imageSize + textMargin
        val textMoveY = imageSize / 2.5f

        val pointsColumn = 700f
        val killsColumn1 = 1100f
        val killsColumn2 = 1220f

        val row1 = 40f
        val row2 = 80f
        val row3 = 120f

        val hpPosition = PVector(pointsColumn, row1)
        val pointsPosition = PVector(pointsColumn, row2)
        val moneyPosition = PVector(pointsColumn, row3)
        val tuskPositionImage = PVector(killsColumn1, row1)
        val petruPositionImage = PVector(killsColumn1, row2)
        val kukizPositionImage = PVector(killsColumn1, row3)
        val korwinPositionImage = PVector(killsColumn2, row1)
        val kijowskiPositionImage = PVector(killsColumn2, row2)
        val schetynaPositionImage = PVector(killsColumn2, row3)
    }

    var position = PVector(0f, 0f)
    val pApplet = board.pApplet
    val image = board.imageKeeper.barImage
    private var font: PFont = pApplet.loadFont("fonts/OCRAExtended-30.vlw")

    fun draw() {
        pApplet.image(image, position.x, position.y)
        drawStatistic()
    }

    fun drawStatistic() {
        pApplet.textFont(font, fontSize)
        drawPointsStatistic()
        drawKillsStatistic()
    }

    private fun drawPointsStatistic() {
        pApplet.fill(Color.WHITE, 200f)

        pApplet.text("Hp:     ${board.polandBall.hp.format(2)}", hpPosition.x + textMoveX, hpPosition.y)
        pApplet.text("Points: ${board.statistic.getPoints()}", pointsPosition.x + textMoveX, pointsPosition.y)
        pApplet.text("Money:  ${board.statistic.getMoney()}", moneyPosition.x + textMoveX, moneyPosition.y)
    }

    fun drawKillsStatistic() {
        pApplet.fill(ColorKeeper.BLOOD, 200f)

        pApplet.image(board.imageKeeper.tusk, tuskPositionImage.x, tuskPositionImage.y - imageSize / 1.25f, imageSize, imageSize)
        pApplet.image(board.imageKeeper.petru, petruPositionImage.x, petruPositionImage.y - imageSize / 1.25f, imageSize, imageSize)
        pApplet.image(board.imageKeeper.kukiz, kukizPositionImage.x, kukizPositionImage.y - imageSize / 1.25f, imageSize, imageSize)

        pApplet.text("${board.statistic.tuskKill}", tuskPositionImage.x + textMoveX, tuskPositionImage.y)
        pApplet.text("${board.statistic.petruKill}", petruPositionImage.x + textMoveX, petruPositionImage.y)
        pApplet.text("${board.statistic.kukizKill}", kukizPositionImage.x + textMoveX, kukizPositionImage.y)


        pApplet.image(board.imageKeeper.korwin, korwinPositionImage.x, korwinPositionImage.y - imageSize / 1.25f, imageSize, imageSize)
        pApplet.image(board.imageKeeper.kijowski, kijowskiPositionImage.x, kijowskiPositionImage.y - imageSize / 1.25f, imageSize, imageSize)
        pApplet.image(board.imageKeeper.schetyna, schetynaPositionImage.x, schetynaPositionImage.y - imageSize / 1.25f, imageSize, imageSize)

        pApplet.text("${board.statistic.korwinKill}", korwinPositionImage.x + textMoveX, korwinPositionImage.y)
        pApplet.text("${board.statistic.kijowskiKill}", kijowskiPositionImage.x + textMoveX, kijowskiPositionImage.y)
        pApplet.text("${board.statistic.schetynaKill}", schetynaPositionImage.x + textMoveX, schetynaPositionImage.y)
    }

}

