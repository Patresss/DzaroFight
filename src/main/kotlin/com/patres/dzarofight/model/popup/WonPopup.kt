package com.patres.dzarofight.model.popup

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.model.Board
import processing.core.PVector

class WonPopup(
        board: Board
) : OptionPopup(
        board = board,
        image = board.imageKeeper.youWon,
        buttonSize = PVector(MainSketch.SIZE_X * 0.19f, MainSketch.SIZE_Y * 0.07f)
)