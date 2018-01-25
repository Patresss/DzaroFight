package com.patres.dzarofight.model.friends

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.helper.FilterMasks
import com.patres.dzarofight.model.Board
import com.patres.dzarofight.model.Character
import org.jbox2d.common.Vec2
import processing.core.PVector

class Pawlowicz(
        board: Board)
    : Character(
        board = board,
        speed = 30f,
        image = board.imageKeeper.pawlowicz,
        position = PVector(MainSketch.CAMERA_RESOLUTION_WIDTH / 2f, MainSketch.CAMERA_RESOLUTION_HEIGHT * 0.3f),
        categoryBits = FilterMasks.CATEGORY_FRIEND,
        maskBits = FilterMasks.MASK_FRIEND
) {

    fun moveLeft() {
        body.apply {
            linearVelocity = Vec2(-speed, 0f)
        }
    }

    fun moveDown() {
        body.apply {
            linearVelocity = Vec2(0f, -speed)
        }
    }

    fun moveRight() {
        body.apply {
            linearVelocity = Vec2(speed, 0f)
        }
    }

    fun moveUp() {
        body.apply {
            linearVelocity = Vec2(0f, speed)
        }
    }

    fun stop() {
        body.apply {
            linearVelocity = Vec2(0f, 0f)
        }
    }


}