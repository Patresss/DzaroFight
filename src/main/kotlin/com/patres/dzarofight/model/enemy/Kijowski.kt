package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.statistic.StatisticEnemy
import com.patres.dzarofight.model.Board
import processing.core.PImage

class Kijowski(board: Board) : Enemy(board = board, image = board.imageKeeper.kijowski, audioFile = "audio/kijowski1.mp3") {

    companion object : StatisticEnemy()

    override fun updateStatistic() {
        board.statistic.kijowskiKill++
    }

}