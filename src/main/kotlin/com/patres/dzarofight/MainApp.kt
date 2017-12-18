package com.patres.dzarofight

import com.patres.dzarofight.MainApp.Companion.OPTIONS_FULL_SCREEN
import javafx.application.Application
import processing.core.PApplet

class MainApp {
    companion object {
        val OPTIONS_FULL_SCREEN = arrayOf("--present", MainSketch::class.java.canonicalName)
        val OPTIONS = arrayOf(MainSketch::class.java.canonicalName)
    }
}


fun main(args: Array<String>) {
    PApplet.main(OPTIONS_FULL_SCREEN)
}