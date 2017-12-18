package com.patres.dzarofight.helper

import processing.core.PApplet
import processing.core.PImage

class ImageKeeper(val pApplet: PApplet) {

    val barImage: PImage = pApplet.loadImage("img/bar.png") ?: PImage()
    val polandBallImage: PImage = pApplet.loadImage("img/poland-ball.png") ?: PImage()
    val background: PImage = pApplet.loadImage("img/background.png") ?: PImage()

    val nextLevel: PImage = pApplet.loadImage("img/next-level.png") ?: PImage()

    val tusk: PImage = pApplet.loadImage("img/tusk.png") ?: PImage()
    val petru: PImage = pApplet.loadImage("img/petru.png") ?: PImage()
    val kukiz: PImage = pApplet.loadImage("img/kukiz.png") ?: PImage()
    val korwin: PImage = pApplet.loadImage("img/korwin.png") ?: PImage()
    val kijowski: PImage = pApplet.loadImage("img/kijowski.png") ?: PImage()
    val schetyna: PImage = pApplet.loadImage("img/schetyna.png") ?: PImage()


}