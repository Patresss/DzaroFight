package com.patres.dzarofight.helper

import com.patres.dzarofight.MainSketch
import com.patres.dzarofight.model.enemy.*
import processing.core.PApplet
import processing.core.PImage

class ImageKeeper(val pApplet: PApplet) {

    val barImage: PImage = pApplet.loadImage("img/bar.png") ?: PImage()
    val polandBallImage: PImage = pApplet.loadImage("img/poland-ball.png") ?: PImage()
    val background: PImage = pApplet.loadImage("img/background.png") ?: PImage()

    val nextLevel: PImage = pApplet.loadImage("img/next-level.png") ?: PImage()
    val youLose: PImage = pApplet.loadImage("img/you-lose.png") ?: PImage()
    val youWon: PImage = pApplet.loadImage("img/you-won.png") ?: PImage()

    val tusk: PImage = pApplet.loadImage("img/tusk.png") ?: PImage()
    val petru: PImage = pApplet.loadImage("img/petru.png") ?: PImage()
    val kukiz: PImage = pApplet.loadImage("img/kukiz.png") ?: PImage()
    val korwin: PImage = pApplet.loadImage("img/korwin.png") ?: PImage()
    val kijowski: PImage = pApplet.loadImage("img/kijowski.png") ?: PImage()
    val schetyna: PImage = pApplet.loadImage("img/schetyna.png") ?: PImage()

    val pawlowicz: PImage = pApplet.loadImage("img/pawłowicz.png") ?: PImage()

    init {
        barImage.resize(MainSketch.SIZE_X, (MainSketch.SIZE_Y * 0.1259).toInt())
        background.resize(MainSketch.SIZE_X, MainSketch.SIZE_Y)

        resizeOptionBoard(nextLevel)
        resizeOptionBoard(youLose)
        resizeOptionBoard(youWon)

        resizeEnemy(tusk, Tusk.radius)
        resizeEnemy(petru, Petru.radius)
        resizeEnemy(kukiz, Kukiz.radius)
        resizeEnemy(korwin, Korwin.radius)
        resizeEnemy(kijowski, Kijowski.radius)
        resizeEnemy(schetyna, Schetyna.radius)

        resizeEnemy(pawlowicz, Tusk.radius)
    }

    private fun resizeEnemy(image: PImage, radius: Float) {
        val scaledRadius = (radius.toInt() * 2 * MainSketch.SCALE_Y).toInt()
        image.resize(scaledRadius, scaledRadius)
    }

    private fun resizeOptionBoard(image: PImage) {
        image.resize((MainSketch.SIZE_X * 0.41).toInt(), (MainSketch.SIZE_Y * 0.37).toInt())
    }

}