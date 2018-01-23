package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.model.Board
import com.patres.dzarofight.statistic.StatisticEnemy

class Petru(board: Board) : Enemy(board = board, image = board.imageKeeper.petru, audioFile = "audio/petru1.mp3") {

    companion object : StatisticEnemy()

    override fun updateStatistic() {
        board.statistic.petruKill++
    }

}