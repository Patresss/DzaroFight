package com.patres.dzarofight.handler

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.Mode
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
        var pApplet: PApplet
) {

    var mode = Mode.BACKGROUND_IMAGE
    var transparentDiffMode = false
    lateinit var backgroundImage: PImage
    lateinit var board: Board
    private var backgroundFromCamera: PImage = PImage()
    private var output = openCv.output.flipVerticalImage()
    private var acceptableCover: Double = 0.01

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

        when(mode) {
            Mode.BACKGROUND_IMAGE -> drawBackgroundImage()
            Mode.BACKGROUND_CAMERA -> drawImageFromCamera()
            Mode.BACKGROUND_MIX_IMAGE_WITH_CAMERA -> drawImageWithBackground()
        }

    }

    fun getTouchedEnemies(enemies: List<Enemy>): List<Enemy> {
        val touchedEnemiesPixelsMap = HashMap(enemies.associateBy({ it }, { 0 }))
        for (x in 1..output.width) {
            for (y in 1..output.height) {
                if (pApplet.brightness(output.get(x, y)) >= 255) {
                    enemies.filter { it.containsPixel(x, y) }.forEach { touchedEnemiesPixelsMap.put(it, touchedEnemiesPixelsMap[it]?.plus(1)) }
                }
            }
        }
        return enemies.filter { touchedEnemiesPixelsMap[it]!!.toDouble() / it.getNumberOfPixels().toDouble() > acceptableCover }
    }

    fun saveImageToBackground() {
        backgroundFromCamera = camera.get().flipVerticalImage()
        backgroundFromCamera.resize(MainSketch.SIZE_X,  MainSketch.SIZE_Y)
    }

    private fun drawImageWithBackground() {
        val image = camera.get().flipVerticalImage()
        image.resize(MainSketch.SIZE_X,  MainSketch.SIZE_Y)
        val imageWithBackground = backgroundImage.copy()
        for (x in 1..image.width) {
            for (y in 1..image.height) {
                val foregroundColor = image.get(x, y)
                val cameraBackgroundColor = backgroundFromCamera.get(x, y)
                val imageBackground = imageWithBackground.get(x, y)
                val r1 = pApplet.red(foregroundColor)
                val g1 = pApplet.green(foregroundColor)
                val b1 = pApplet.blue(foregroundColor)
                val r2 = pApplet.red(cameraBackgroundColor)
                val g2 = pApplet.green(cameraBackgroundColor)
                val b2 = pApplet.blue(cameraBackgroundColor)
                val diff = PApplet.dist(r1, g1, b1, r2, g2, b2)
                if (diff < 30) {
                    image.set(x, y, imageBackground)
                }
            }
        }
        pApplet.image(image, 0f, 0f)
    }

    private fun drawImageFromCamera() {
        val cameraImage = camera.get().flipVerticalImage()
        addTransparentDiff(cameraImage)
        pApplet.image(cameraImage, 0f, 0f, MainSketch.SIZE_X.toFloat(), MainSketch.SIZE_Y.toFloat())
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