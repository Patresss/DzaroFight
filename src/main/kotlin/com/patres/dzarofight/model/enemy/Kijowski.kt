package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.statistic.StatisticEnemy
import com.patres.dzarofight.model.Board
import processing.core.PImage

class Kijowski(
        board: Board,
        image: PImage = board.imageKeeper.kijowski,
        audioFile: String = "audio/kijowski1.mp3"
) : Enemy(board = board, image = image, audioFile = audioFile) {

    companion object : StatisticEnemy()

    override fun updateStatistic() {
        board.statistic.kijowskiKill++
    }

}