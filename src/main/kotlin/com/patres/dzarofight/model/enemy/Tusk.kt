package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.statistic.StatisticEnemy
import com.patres.dzarofight.model.Board
import processing.core.PImage

class Tusk(
        board: Board,
        radius: Float = 15f,
        stepSpeed: Float = 50f,
        image: PImage = board.imageKeeper.tusk,
        audioFile: String = "audio/tusk1.mp3"
) : Enemy(board = board, radius = radius, stepSpeed = stepSpeed, image = image, audioFile = audioFile) {

    companion object : StatisticEnemy() {
        override val pointsForKill: Int = 3
    }

    override fun updateStatistic() {
        board.statistic.tuskKill++
    }

    override fun hurtPolandBall() {
        board.polandBall.hp -= pointsForKill
    }

}