package com.patres.dzarofight.handler

import com.patres.dzarofight.model.Board
import com.patres.dzarofight.model.friends.Pawlowicz
import net.java.games.input.Controller
import net.java.games.input.ControllerEnvironment
import net.java.games.input.Event


class GamePadHandler(val board: Board) {

    val pApplet = board.pApplet
    var controller = ControllerEnvironment.getDefaultEnvironment().controllers.find { it.type == Controller.Type.STICK }

    fun draw() {
        controller?.poll()

        val queue = controller?.eventQueue
        val event = Event()
        while (queue?.getNextEvent(event) == true) {
            val component = event.component
            if (event.value == 1.0f) {
                when {
                    component.name.contains("0") -> board.pawlowicz.moveUp()
                    component.name.contains("1") -> board.pawlowicz.moveRight()
                    component.name.contains("2") -> board.pawlowicz.moveDown()
                    component.name.contains("3") -> board.pawlowicz.moveLeft()
                    component.name.contains("4") -> {
                        board.pawlowicz.killBody()
                        board.pawlowicz = Pawlowicz(board)
                    }
                    component.name.contains("5") -> board.pawlowicz.stop()
                }
            }
        }
    }

}

