package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.statistic.CharacterCompanion
import com.patres.dzarofight.model.Board

class Kijowski(board: Board) : Enemy(board = board, image = board.imageKeeper.kijowski, audioFile = "audio/kijowski1.mp3") {

    companion object : CharacterCompanion()

    override fun updateStatistic() {
        board.statistic.kijowskiKill++
    }

}