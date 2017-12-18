package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.helper.RandomGenerator
import com.patres.dzarofight.model.Board
import com.patres.dzarofight.statistic.StatisticEnemy
import processing.core.PApplet
import processing.core.PImage
import processing.core.PVector


abstract class Enemy(
        val board: Board,
        val radius: Float = 12f,
        val position: PVector = RandomGenerator.generatePVectorOnBorder(MainSketch.CAMERA_RESOLUTION_WIDTH, MainSketch.CAMERA_RESOLUTION_HEIGHT, radius),
        val stepSpeed: Float = 100f,
        val image: PImage,
        val audioFile: String
) {

    val pApplet: PApplet = board.pApplet
    var alive = true
    val destinationPosition = board.polandBall.position
    var destinationPositionSpeed = PVector((destinationPosition.x - position.x) / stepSpeed, (destinationPosition.y - position.y) / stepSpeed)


    companion object : StatisticEnemy()


    fun draw() {
        val scaledX = (position.x - radius) * MainSketch.SCALE_X
        val scaledY = (position.y - radius) * MainSketch.SCALE_Y
        val scaledRadius = radius * MainSketch.SCALE_X
        pApplet.image(image, scaledX, scaledY, scaledRadius * 2, scaledRadius * 2)
    }


    fun move() {
        val speedX = destinationPositionSpeed.x * board.frameSpeedBooster
        val speedY = destinationPositionSpeed.y * board.frameSpeedBooster

        val distance = Math.sqrt(Math.pow(position.x.toDouble() - destinationPosition.x, 2.0) + Math.pow(position.y.toDouble() - destinationPosition.y, 2.0))
        val minDist = radius + board.polandBall.currentRadius
        if (distance < minDist) {
            alive = false
            hurtPolandBall()
            playAudio()
        } else {
            position.x += speedX
            position.y += speedY
        }
    }

    fun playAudio() {
        if (board.song?.isPlaying != true) {
            board.song = board.minim.loadFile(audioFile)
            board.song?.play()
        }
    }


    open fun hurtPolandBall() {
        board.polandBall.hp -= pointsForKill
    }

    fun containsPixel(x: Int, y: Int): Boolean = distanceFromCenter(x, y) < radius

    fun getNumberOfPixels(): Int = (Math.pow(radius * 2.0, 2.0).toInt() * Math.PI / 4.0).toInt()

    private fun distanceFromCenter(x: Int, y: Int): Double = Math.sqrt(Math.pow(position.x.toDouble() - x, 2.0) + Math.pow(position.y.toDouble() - y, 2.0))

    abstract fun updateStatistic()
}