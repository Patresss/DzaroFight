package com.patres.dzarofight.model

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.ModeGame
import com.patres.dzarofight.helper.FilterMasks
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.dynamics.Body
import org.jbox2d.dynamics.BodyDef
import org.jbox2d.dynamics.BodyType
import org.jbox2d.dynamics.FixtureDef
import processing.core.PVector

class PolandBall(
        val board: Board,
        val position: PVector = PVector(MainSketch.CAMERA_RESOLUTION_WIDTH / 2f, MainSketch.CAMERA_RESOLUTION_HEIGHT / 2f)
) {
    val baseRadius = 30f
    var currentRadius = baseRadius
    var hp = 100f
        set(value) {
            if(value <= 0f) {
                board.gameMode = ModeGame.LOSE
                field = 0f
            } else {
                field = value
                currentRadius = baseRadius * hp / 100f
                updateShapeRadius()
            }
        }
    val pApplet = board.pApplet
    val image = board.imageKeeper.polandBallImage

    private val box2d = board.box2d
    private lateinit var body: Body


    init {
        makeBody(position.x, position.y, currentRadius)
    }

    fun draw() {
        display()
    }

    private fun display() {
        val pos = box2d.getBodyPixelCoord(body)
        val scaledX = (pos.x - currentRadius) * MainSketch.SCALE_X
        val scaledY = (pos.y - currentRadius) * MainSketch.SCALE_Y
        val scaledRadius = currentRadius * MainSketch.SCALE_X
        pApplet.run {
            pushMatrix()
            translate(scaledX, scaledY)
            image(image, 0f, 0f, 2f * scaledRadius, 2f * scaledRadius)
            popMatrix()
        }
    }


    private fun makeBody(x: Float, y: Float, r: Float) {
        val bd = BodyDef().apply {
            position = box2d.coordPixelsToWorld(x, y)
            type = BodyType.STATIC
        }

        val circleShape = CircleShape().apply {
            m_radius = box2d.scalarPixelsToWorld(r)
        }
        val fd = FixtureDef().apply {
            shape = circleShape
            density = 10f
            friction = 0.01f
            restitution = 0.3f
            filter.categoryBits = FilterMasks.CATEGORY_POLAND_BALL
            filter.maskBits = FilterMasks.MASK_POLAND_BALL
        }
        body = box2d.world.createBody(bd).apply {
            createFixture(fd)
        }
        body.userData = this
    }

    private fun updateShapeRadius() {
        body.fixtureList.shape.m_radius = box2d.scalarPixelsToWorld(currentRadius)
    }


}