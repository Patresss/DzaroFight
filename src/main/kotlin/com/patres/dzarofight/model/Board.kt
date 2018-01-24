package com.patres.dzarofight.model

import com.patres.dzarofight.Level
import com.patres.dzarofight.handler.AudioHandler
import com.patres.dzarofight.statistic.Statistic
import com.patres.dzarofight.handler.CameraHandler
import com.patres.dzarofight.helper.ImageKeeper
import com.patres.dzarofight.helper.RandomGenerator
import com.patres.dzarofight.model.enemy.Enemy
import com.patres.dzarofight.model.friends.Pawlowicz
import ddf.minim.AudioPlayer
import ddf.minim.Minim
import processing.core.PApplet
import shiffman.box2d.Box2DProcessing

class Board(
        val pApplet: PApplet,
        val box2d: Box2DProcessing,
        val imageKeeper: ImageKeeper,
        val cameraHandler: CameraHandler,
        val audioHandler: AudioHandler
) {

    val polandBall = PolandBall(board = this)
    val bar = Bar(board = this)
    val nextLevelBoard = NextLevelBoard(board = this)
    var enemys = ArrayList<Enemy>()
    var lastCreationTime = System.currentTimeMillis()
    var frameSpeedBooster = 60f
    var pause = false
    var newLevel = false
    var statistic = Statistic()
    val minim = Minim(pApplet)
    var song: AudioPlayer? = null
    var level = Level.LEVEL_1

    var pawlowicz = Pawlowicz(this)

    init {
        addNewEnemies(1)
    }

    private fun setup() {
        if(!pause && !newLevel) {
            updateNumberOfEnemies()
            enemys.removeIf { !it.alive }
            removeTouchedEnemies()
            updateFrameSpeedBooster()
            updatePolandBallHp()

            if(statistic.getPoints() >= level.pointsToNextLevel) {
                level = level.getNextLevel()
                newLevel = true
            }
            pApplet.cursor(PApplet.ARROW)
        }
    }

    fun hit(enemy: Enemy) {
        enemy.hit()
        enemys.remove(enemy)
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
        touchedEnemies.forEach {
            it.updateStatistic()
            it.killBody()
        }
        enemys.removeIf { touchedEnemies.contains(it) }
    }

    fun draw() {
        setup()
        if(!pause && !newLevel) {
            move()
        }
        if(newLevel) {
            nextLevelBoard.draw()
        } else {
            enemys.forEach { it.draw() }
            polandBall.draw()
            pawlowicz.draw()
        }
        bar.draw()
    }

    fun move() {
        box2d.step()
    }

    fun addNewEnemies(number: Int) {
        for (i in 1..number) {
            enemys.add(RandomGenerator.generateEnemy(board = this))
        }
    }

    private fun shouldAddNewEnemy(): Boolean = (System.currentTimeMillis() - lastCreationTime) > level.frequencyOfNewEnemy


}