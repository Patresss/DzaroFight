package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.model.Board
import com.patres.dzarofight.statistic.StatisticEnemy

class Korwin(board: Board) : Enemy(board = board, image = board.imageKeeper.korwin, audioFile = "audio/korwin1.mp3") {

    companion object : StatisticEnemy()

    override fun updateStatistic() {
        board.statistic.korwinKill++
    }

}