package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.model.Board
import com.patres.dzarofight.statistic.CharacterCompanion

class Korwin(board: Board) : Enemy(board = board, image = board.imageKeeper.korwin, audioFile = "audio/korwin1.mp3") {

    companion object : CharacterCompanion()

    override fun updateStatistic() {
        board.statistic.korwinKill++
    }

}