package com.patres.dzarofight.model.friends

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.helper.FilterMasks
import com.patres.dzarofight.helper.toPVector
import com.patres.dzarofight.model.Board
import com.patres.dzarofight.model.enemy.Enemy
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.Body
import org.jbox2d.dynamics.BodyDef
import org.jbox2d.dynamics.BodyType
import org.jbox2d.dynamics.FixtureDef
import processing.core.PVector

class Pawlowicz(
        val board: Board
) {
    val pApplet = board.pApplet
    val box2d = board.box2d
    var position = PVector(MainSketch.CAMERA_RESOLUTION_WIDTH / 2f, MainSketch.CAMERA_RESOLUTION_HEIGHT * 0.3f)
    val image = board.imageKeeper.pawlowicz
    val speed: Float = 10f
    lateinit var body: Body
    lateinit var bodyDef: BodyDef

    init {
        makeBody(position.x, position.y, Enemy.radius)
    }

    private fun makeBody(x: Float, y: Float, r: Float) {
        bodyDef = BodyDef().apply {
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
            filter.categoryBits = FilterMasks.CATEGORY_FRIEND
            filter.maskBits  = FilterMasks.MASK_FRIEND
        }
        body = box2d.world.createBody(bodyDef).apply {
            createFixture(fd)
        }
        body.userData = this
    }

    fun display() {
        val scaledX = (position.x) * MainSketch.SCALE_X
        val scaledY = (position.y) * MainSketch.SCALE_Y
        pApplet.run {
            pushMatrix()
            translate(scaledX, scaledY)
            image(image, -image.width / 2f, -image.height / 2f)
            popMatrix()
        }
    }

    fun killBody() {
        box2d.destroyBody(body)
    }

    fun draw() {
        position = box2d.getBodyPixelCoord(body).toPVector()
        display()
    }

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