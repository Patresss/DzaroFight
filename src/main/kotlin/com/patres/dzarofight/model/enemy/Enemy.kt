package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.helper.FilterMasks
import com.patres.dzarofight.helper.RandomGenerator
import com.patres.dzarofight.model.Board
import com.patres.dzarofight.model.Character
import com.patres.dzarofight.statistic.CharacterCompanion
import org.jbox2d.common.Vec2
import processing.core.PImage
import processing.core.PVector


abstract class Enemy(board: Board, speed: Float = 0.1f, image: PImage, val audioFile: String, position: PVector = RandomGenerator.generatePVectorOnBorder(MainSketch.CAMERA_RESOLUTION_WIDTH, MainSketch.CAMERA_RESOLUTION_HEIGHT, radius)) : Character(
        board = board,
        speed = speed,
        image = image,
        position = position,
        positionSpeed = Vec2((board.polandBall.position.x - position.x) * speed, -(board.polandBall.position.y - position.y) * speed),
        categoryBits = FilterMasks.CATEGORY_ENEMY,
        maskBits = FilterMasks.MASK_ENEMY
) {
    var alive = true

    companion object : CharacterCompanion()

    open fun hurtPolandBall() {
        board.polandBall.hp -= pointsForKill
    }

    fun hit() {
        playAudio()
        hurtPolandBall()
        killBody()
    }

    fun containsPixel(x: Int, y: Int): Boolean = distanceFromCenter(x, y) < radius

    fun getNumberOfPixels(): Int = (Math.pow(radius * 2.0, 2.0).toInt() * Math.PI / 4.0).toInt()

    abstract fun updateStatistic()

    private fun distanceFromCenter(x: Int, y: Int): Double = Math.sqrt(Math.pow(position.x.toDouble() - x, 2.0) + Math.pow(position.y.toDouble() - y, 2.0))

    private fun playAudio() {
        if (board.song?.isPlaying != true) {
            board.song = board.minim.loadFile(audioFile)
            board.song?.play()
        }
    }

}


