package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.model.Board
import com.patres.dzarofight.statistic.StatisticEnemy
import processing.core.PImage

class Kukiz(
        board: Board,
        image: PImage = board.imageKeeper.kukiz,
        audioFile: String = "audio/kukiz1.mp3"
) : Enemy(board = board, image = image, audioFile = audioFile) {

    companion object : StatisticEnemy()

    override fun updateStatistic() {
        board.statistic.kukizKill++
    }

}