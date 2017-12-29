package com.patres.dzarofight

import com.patres.dzarofight.handler.AudioHandler
import com.patres.dzarofight.handler.CameraHandler
import com.patres.dzarofight.helper.ImageKeeper
import com.patres.dzarofight.helper.fill
import com.patres.dzarofight.model.Board
import ddf.minim.Minim
import gab.opencv.OpenCV
import processing.core.PApplet
import processing.video.Capture
import java.awt.Color


class MainSketch : PApplet() {

    companion object {
        val SIZE_X = 1920
        val SIZE_Y = 1080
        val CAMERA_RESOLUTION_WIDTH = 640
        val CAMERA_RESOLUTION_HEIGHT = 480
        val SCALE_X = SIZE_X.toFloat() / CAMERA_RESOLUTION_WIDTH.toFloat()
        val SCALE_Y = SIZE_Y.toFloat() / CAMERA_RESOLUTION_HEIGHT.toFloat()
    }

    private lateinit var board: Board
    private lateinit var imageKeeper: ImageKeeper
    private lateinit var cameraHandler: CameraHandler

    override fun settings() {
        size(SIZE_X, SIZE_Y)
        val openCv = OpenCV(this, CAMERA_RESOLUTION_WIDTH, CAMERA_RESOLUTION_HEIGHT)
        val camera = Capture(this, CAMERA_RESOLUTION_WIDTH, CAMERA_RESOLUTION_HEIGHT)
        imageKeeper = ImageKeeper(pApplet = this)
        cameraHandler = CameraHandler(pApplet = this, openCv = openCv, camera = camera)
        board = Board(pApplet = this, imageKeeper = imageKeeper, cameraHandler = cameraHandler, audioHandler = AudioHandler(Minim(this)))
    }

    override fun setup() {
        cameraHandler.setup(board)
    }

    override fun draw() {
        update()
        cameraHandler.draw()
        board.draw()
        drawInformation()
    }

    private fun drawInformation() {
        textSize(14f)
        fill(Color.WHITE)
        text("Frame: $frameRate", 10f, 20f)
    }

    fun update() {
    }

    override fun keyPressed() {
        when (key) {
            ' ' -> board.addNewEnemies(1)
            't' -> cameraHandler.transparentDiffMode = !cameraHandler.transparentDiffMode
            'b' -> cameraHandler.backgroundMode = !cameraHandler.backgroundMode
            'p' -> board.pause = !board.pause

        }
    }

    override fun mousePressed() {
        if (board.nextLevelBoard.isInButton()) {
            board.newLevel = false
        }
    }

    override fun stop() {
    }

    fun captureEvent(video: Capture) {
        video.read()
    }

}