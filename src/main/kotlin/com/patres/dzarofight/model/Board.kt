package com.patres.dzarofight.model

import com.patres.dzarofight.Level
import com.patres.dzarofight.handler.AudioHandler
import com.patres.dzarofight.statistic.Statistic
import com.patres.dzarofight.handler.CameraHandler
import com.patres.dzarofight.helper.ImageKeeper
import com.patres.dzarofight.helper.RandomGenerator
import com.patres.dzarofight.model.enemy.Enemy
import ddf.minim.AudioPlayer
import ddf.minim.Minim
import processing.core.PApplet

class Board(
        val pApplet: PApplet,
        val imageKeeper: ImageKeeper,
        val cameraHandler: CameraHandler,
        val audioHandler: AudioHandler
) {

    val polandBall = PolandBall(board = this)
    val bar = Bar(board = this)
    var enemys = ArrayList<Enemy>()
    var lastCreationTime = System.currentTimeMillis()
    var frameSpeedBooster = 60f
    var pause = false
    var statistic = Statistic()
    val minim = Minim(pApplet)
    var song: AudioPlayer? = null
    var level = Level.LEVEL_1

    fun setup() {
        if(!pause) {
            updateNumberOfEnemies()
            enemys.removeIf { !it.alive }
            removeTouchedEnemies()
            updateFrameSpeedBooster()
            updatePolandBallHp()

            if(statistic.getPoints() > level.pointsToNextLevel) {
                level = level.getNextLevel()
                pause = true
                pApplet.image(imageKeeper.nextLevel, pApplet.width / 2f, pApplet.height / 2f)
            }
        }
    }

    private fun updateNumberOfEnemies() {
        if (shouldAddNewEnemy()) {
            addNewEnemies(1)
            lastCreationTime = System.currentTimeMillis()
        }
    }

    private fun updatePolandBallHp() {
        if (audioHandler.isLoud()) {
            polandBall.hp += audioHandler.getVolume()
        }
    }

    private fun updateFrameSpeedBooster() {
        frameSpeedBooster = 60f / pApplet.frameRate
    }

    private fun removeTouchedEnemies() {
        val touchedEnemies = cameraHandler.getTouchedEnemies(enemys)
        touchedEnemies.forEach { it.updateStatistic() }
        enemys.removeIf { touchedEnemies.contains(it) }
    }

    fun draw() {
        setup()
        if(!pause) {
            move()
        }
        enemys.forEach { it.draw() }
        bar.draw()
        polandBall.draw()
    }

    fun move() {
        enemys.forEach { it.move() }
    }

    fun addNewEnemies(number: Int) {
        for (i in 1..number) {
            enemys.add(RandomGenerator.generateEnemy(board = this))
        }
    }

    private fun shouldAddNewEnemy(): Boolean = (System.currentTimeMillis() - lastCreationTime) > level.frequencyOfNewEnemy
}