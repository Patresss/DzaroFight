package com.patres.dzarofight.model.enemy

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.helper.FilterMasks
import com.patres.dzarofight.helper.RandomGenerator
import com.patres.dzarofight.helper.toPVector
import com.patres.dzarofight.model.Board
import com.patres.dzarofight.statistic.CharacterCompanion
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.Body
import org.jbox2d.dynamics.BodyDef
import org.jbox2d.dynamics.BodyType
import org.jbox2d.dynamics.FixtureDef
import processing.core.PApplet
import processing.core.PImage
import processing.core.PVector


abstract class Character(
        val board: Board,
        val speed: Float = 0.1f,
        val image: PImage,

        var position: PVector = RandomGenerator.generatePVectorOnBorder(MainSketch.CAMERA_RESOLUTION_WIDTH, MainSketch.CAMERA_RESOLUTION_HEIGHT, radius),
        val positionSpeed: Vec2 = Vec2(0f, 0f),
        val categoryBits:Int,
        val maskBits:Int
) {

    lateinit var body: Body
    private val pApplet: PApplet = board.pApplet
    private val box2d = board.box2d

    companion object : CharacterCompanion()

    init {
        makeBody()
    }

    fun draw() {
        setup()
        display()
    }

    private fun setup() {
        position = box2d.getBodyPixelCoord(body).toPVector()
    }

    private fun display() {
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

    fun makeBody() {
        val x = position.x
        val y = position.y
        val r = Enemy.radius
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
            filter.categoryBits = categoryBits
            filter.maskBits = maskBits
        }
        body = box2d.world.createBody(bd).apply {
            createFixture(fd)
            linearVelocity = positionSpeed
        }
        body.userData = this
    }


}


