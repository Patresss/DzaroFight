package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.model.Board
import com.patres.dzarofight.statistic.CharacterCompanion

class Tusk(board: Board) : Enemy(board = board, speed = 1f, image = board.imageKeeper.tusk, audioFile = "audio/tusk1.mp3") {

    companion object : CharacterCompanion() {
        override val pointsForKill: Int = 3
        override val radius = 15f
    }

    override fun updateStatistic() {
        board.statistic.tuskKill++
    }

    override fun hurtPolandBall() {
        board.polandBall.hp -= pointsForKill
    }

}