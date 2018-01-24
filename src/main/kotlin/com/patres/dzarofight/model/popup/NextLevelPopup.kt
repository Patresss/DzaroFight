package com.patres.dzarofight.model.popup

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.model.Board
import processing.core.PVector

class NextLevelPopup(
        board: Board
) : OptionPopup(
        board = board,
        image = board.imageKeeper.nextLevel,
        buttonSize = PVector(MainSketch.SIZE_X * 0.17f, MainSketch.SIZE_Y * 0.07f)
)

