package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.statistic.StatisticEnemy
import com.patres.dzarofight.model.Board
import processing.core.PImage

class Korwin(
        board: Board,
        image: PImage = board.imageKeeper.korwin,
        audioFile: String = "audio/korwin1.mp3"
) : Enemy(board = board, image = image, audioFile = audioFile) {

    companion object : StatisticEnemy()

    override fun updateStatistic() {
        board.statistic.korwinKill++
    }

}