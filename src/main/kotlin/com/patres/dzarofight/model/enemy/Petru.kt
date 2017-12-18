package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.statistic.StatisticEnemy
import com.patres.dzarofight.model.Board
import processing.core.PImage

class Petru(
        board: Board,
        image: PImage = board.imageKeeper.petru,
        audioFile: String = "audio/petru1.mp3"
) : Enemy(board = board, image = image, audioFile = audioFile) {

    companion object : StatisticEnemy()

    override fun updateStatistic() {
        board.statistic.petruKill++
    }

}