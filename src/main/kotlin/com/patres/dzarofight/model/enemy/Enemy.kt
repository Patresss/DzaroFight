package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.helper.RandomGenerator
import com.patres.dzarofight.helper.toPVector
import com.patres.dzarofight.model.Board
import com.patres.dzarofight.statistic.StatisticEnemy
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.Body
import org.jbox2d.dynamics.BodyDef
import org.jbox2d.dynamics.BodyType
import org.jbox2d.dynamics.FixtureDef
import processing.core.PApplet
import processing.core.PImage
import processing.core.PVector


abstract class Enemy(
        val board: Board,
        val radius: Float = 12f,
        var position: PVector = RandomGenerator.generatePVectorOnBorder(MainSketch.CAMERA_RESOLUTION_WIDTH, MainSketch.CAMERA_RESOLUTION_HEIGHT, radius),
        val speed: Float = 0.1f,
        val image: PImage,
        val audioFile: String
) {

    var alive = true
    private val pApplet: PApplet = board.pApplet
    private val destinationPosition = board.polandBall.position
    private var destinationPositionSpeed = Vec2((destinationPosition.x - position.x) * speed, -(destinationPosition.y - position.y) * speed)
    private val box2d = board.box2d
    lateinit var body: Body

    companion object : StatisticEnemy()

    init {
        makeBody(position.x, position.y, radius)
    }

    private fun makeBody(x: Float, y: Float, r: Float) {
        val bd = BodyDef().apply {
            position = box2d.coordPixelsToWorld(x, y)
            type = BodyType.DYNAMIC
        }

        val cs = CircleShape().apply {
            m_radius = box2d.scalarPixelsToWorld(r)
        }
        val fd = FixtureDef().apply {
            shape = cs
            density = 10f
            friction = 0.01f
            restitution = 0.3f
        }
        body = box2d.world.createBody(bd).apply {
            isBullet = true
            createFixture(fd)
            linearVelocity = destinationPositionSpeed
        }
        body.userData = this
    }

    fun display() {
        val scaledX = (position.x) * MainSketch.SCALE_X
        val scaledY = (position.y) * MainSketch.SCALE_Y
        val scaledRadius = radius * MainSketch.SCALE_X
        pApplet.run {
            pushMatrix()
            translate(scaledX, scaledY)
            image(image, -scaledRadius, -scaledRadius, scaledRadius * 2, scaledRadius * 2)
            popMatrix()
        }
    }

    fun draw() {
        position = box2d.getBodyPixelCoord(body).toPVector()
        display()
    }

    private fun playAudio() {
        if (board.song?.isPlaying != true) {
            board.song = board.minim.loadFile(audioFile)
            board.song?.play()
        }
    }

    fun hit() {
        playAudio()
        hurtPolandBall()
        killBody()
    }

    fun killBody() {
        box2d.destroyBody(body)
    }


    open fun hurtPolandBall() {
        board.polandBall.hp -= pointsForKill
    }

    fun containsPixel(x: Int, y: Int): Boolean = distanceFromCenter(x, y) < radius

    fun getNumberOfPixels(): Int = (Math.pow(radius * 2.0, 2.0).toInt() * Math.PI / 4.0).toInt()

    private fun distanceFromCenter(x: Int, y: Int): Double = Math.sqrt(Math.pow(position.x.toDouble() - x, 2.0) + Math.pow(position.y.toDouble() - y, 2.0))

    abstract fun updateStatistic()
}


