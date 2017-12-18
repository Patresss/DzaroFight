package com.patres.dzarofight.handler

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.helper.flipVerticalImage
import com.patres.dzarofight.model.Board
import com.patres.dzarofight.model.enemy.Enemy
import gab.opencv.OpenCV
import processing.core.PApplet
import processing.core.PImage
import processing.video.Capture
import java.util.*

class CameraHandler(
        var openCv: OpenCV,
        var camera: Capture,
        var pApplet: PApplet,
        private var acceptableCover: Double = 0.01
) {

    var transparentDiffMode = false
    var backgroundMode = true
    lateinit var backgroundImage : PImage
    lateinit var board : Board
    private var output = openCv.output.flipVerticalImage()

    fun setup(board: Board) {
        this.board = board
        backgroundImage = board.imageKeeper.background
        camera.start()
        camera.read()
        openCv.startBackgroundSubtraction(1, 3, 0.5)
    }

    fun draw() {
        openCv.loadImage(camera)
        openCv.updateBackground()
        openCv.dilate()
        openCv.erode()
        output = openCv.output.flipVerticalImage()
        if(backgroundMode) {
            drawBackgroundImage()
        } else {
            drawImageFromCamera()
        }
    }

    fun getTouchedEnemies(enemies: List<Enemy>): List<Enemy> {
        val touchedEnemiesPixeksMap = HashMap(enemies.associateBy({ it }, { 0 }))
        for (x in 1..output.width) {
            for (y in 1..output.height) {
                if (pApplet.brightness(output.get(x, y)) >= 255) {
                    enemies.filter { it.containsPixel(x, y) }.forEach { touchedEnemiesPixeksMap.put(it, touchedEnemiesPixeksMap[it]?.plus(1)) }
                }
            }
        }
        return enemies.filter { touchedEnemiesPixeksMap[it]!!.toDouble() / it.getNumberOfPixels().toDouble() > acceptableCover }
    }


    private fun drawImageFromCamera() {
        val cameraImage = camera.get().flipVerticalImage()
        addTransparentDiff(cameraImage)
        pApplet.image(cameraImage, 0f, 0f, MainSketch.CAMERA_RESOLUTION_WIDTH * MainSketch.SCALE_X, MainSketch.CAMERA_RESOLUTION_HEIGHT * MainSketch.SCALE_Y)
    }

    private fun drawBackgroundImage() {
        val image = backgroundImage
        addTransparentDiff(image)
        pApplet.image(image, 0f, board.bar.image.height.toFloat())
    }

    private fun addTransparentDiff(image: PImage) {
        if (transparentDiffMode) {
            drawDiffInImage(image)
        }
    }

    private fun drawDiffInImage(image: PImage) {
        for (x in 1..output.width) {
            for (y in 1..output.height) {
                if (pApplet.brightness(output.get(x, y)) >= 100) {
                    image.set(x, y, output.get(x, y))
                }
            }
        }
    }

}