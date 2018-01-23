package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.model.Board
import com.patres.dzarofight.statistic.StatisticEnemy

class Kukiz(board: Board) : Enemy(board = board, image = board.imageKeeper.kukiz, audioFile = "audio/kukiz1.mp3") {

    companion object : StatisticEnemy()

    override fun updateStatistic() {
        board.statistic.kukizKill++
    }

}