package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.model.Board
import com.patres.dzarofight.statistic.CharacterCompanion

class Schetyna(board: Board) : Enemy(board = board, image = board.imageKeeper.schetyna, audioFile = "audio/schetyna1.mp3") {

    companion object : CharacterCompanion()

    override fun updateStatistic() {
        board.statistic.schetynaKill++
    }


}