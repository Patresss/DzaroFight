package com.patres.dzarofight

import com.patres.dzarofight.handler.AudioHandler
import com.patres.dzarofight.handler.CameraHandler
import com.patres.dzarofight.handler.GamePadHandler
import com.patres.dzarofight.helper.ImageKeeper
import com.patres.dzarofight.helper.fill
import com.patres.dzarofight.model.Board
import com.patres.dzarofight.model.PolandBall
import com.patres.dzarofight.model.enemy.Enemy
import com.patres.dzarofight.model.friends.Pawlowicz
import ddf.minim.Minim
import gab.opencv.OpenCV
import org.jbox2d.dynamics.contacts.Contact
import processing.core.PApplet
import processing.video.Capture
import shiffman.box2d.Box2DProcessing
import java.awt.Color


class MainSketch : PApplet() {

    companion object {
        const val SIZE_X = 1920
        const val SIZE_Y = 1080
        const val CAMERA_RESOLUTION_WIDTH = 640
        const val CAMERA_RESOLUTION_HEIGHT = 480
        const val SCALE_X = SIZE_X.toFloat() / CAMERA_RESOLUTION_WIDTH.toFloat()
        const val SCALE_Y = SIZE_Y.toFloat() / CAMERA_RESOLUTION_HEIGHT.toFloat()
    }

    private lateinit var board: Board
    private lateinit var imageKeeper: ImageKeeper
    private lateinit var cameraHandler: CameraHandler
    private lateinit var gamePadHandler: GamePadHandler
    private lateinit var minim: Minim
    lateinit var box2d: Box2DProcessing


    var counter = 0;

    override fun settings() {
        size(SIZE_X, SIZE_Y)
        val openCv = OpenCV(this, CAMERA_RESOLUTION_WIDTH, CAMERA_RESOLUTION_HEIGHT)
        val camera = Capture(this, CAMERA_RESOLUTION_WIDTH, CAMERA_RESOLUTION_HEIGHT)
        imageKeeper = ImageKeeper(pApplet = this)

        cameraHandler = CameraHandler(pApplet = this, openCv = openCv, camera = camera)
        box2d = Box2DProcessing(this).apply {
            createWorld()
            setGravity(0f, 0f)
        }
        box2d.listenForCollisions()
        minim = Minim(this)
        board = getNewBoard()
    }

    override fun setup() {
        cameraHandler.setup(board)
        gamePadHandler = GamePadHandler(board)
    }

    override fun draw() {
        update()
        cameraHandler.draw()
        board.draw()
        //drawInformation()
        gamePadHandler.draw()
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
            ' ' -> {
                counter++
                board.addNewEnemies(1)
            }
            't' -> cameraHandler.transparentDiffMode = !cameraHandler.transparentDiffMode
            'c' -> cameraHandler.mode = ModeBackground.BACKGROUND_CAMERA
            'm' -> cameraHandler.mode = ModeBackground.BACKGROUND_MIX_IMAGE_WITH_CAMERA
            'i' -> cameraHandler.mode = ModeBackground.BACKGROUND_IMAGE
            'p' -> board.pause = !board.pause
            's' -> cameraHandler.saveImageToBackground()
        }
    }

    override fun mousePressed() {
        if (board.gameMode == ModeGame.NEXT_LEVEL && board.nextLevelBoard.isInButton()) {
            board.gameMode = ModeGame.GAME
            board.pause = false
        }

        if ((board.gameMode == ModeGame.LOSE || board.gameMode == ModeGame.WON) && board.losePopup.isInButton()) {
            board = getNewBoard()
            cameraHandler.setup(board)
            gamePadHandler = GamePadHandler(board)
        }
    }

    private fun getNewBoard(): Board {
        return Board(
                pApplet = this,
                box2d = box2d,
                imageKeeper = imageKeeper,
                cameraHandler = cameraHandler,
                audioHandler = AudioHandler(minim))

    }

    override fun stop() {
        minim.stop()
        super.stop()
    }

    fun captureEvent(video: Capture) {
        video.read()
    }

    fun beginContact(cp: Contact) {
        val object1 = cp.fixtureA.body.userData
        val object2 = cp.fixtureB.body.userData

        if (object1 is PolandBall && object2 is Enemy) {
            board.hit(object2)
        }

        if (object1 is Pawlowicz && object2 is Enemy) {
            board.removeEnemies(object2)
        }

    }

    fun endContact(cp: Contact) {
    }

    fun keyEvent() {
        println("keyEvent")
    }

    fun post() {
        println("post")
    }

}