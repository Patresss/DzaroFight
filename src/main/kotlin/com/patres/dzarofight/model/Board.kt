package com.patres.dzarofight.model

import com.patres.dzarofight.Level
import com.patres.dzarofight.ModeGame
import com.patres.dzarofight.handler.AudioHandler
import com.patres.dzarofight.handler.CameraHandler
import com.patres.dzarofight.helper.ImageKeeper
import com.patres.dzarofight.helper.RandomGenerator
import com.patres.dzarofight.model.enemy.Enemy
import com.patres.dzarofight.model.friends.Pawlowicz
import com.patres.dzarofight.model.popup.LosePopup
import com.patres.dzarofight.model.popup.NextLevelPopup
import com.patres.dzarofight.model.popup.WonPopup
import com.patres.dzarofight.statistic.Statistic
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
    val nextLevelBoard = NextLevelPopup(board = this)
    var losePopup = LosePopup(board = this)
    var wonPopup = WonPopup(board = this)
    var enemys = ArrayList<Enemy>()
    var lastCreationTime = System.currentTimeMillis()
    var frameSpeedBooster = 60f
    var pause = false
    var gameMode = ModeGame.GAME
    var statistic = Statistic()
    val minim = Minim(pApplet)
    var song: AudioPlayer? = null
    var level = Level.LEVEL_1

    var pawlowicz = Pawlowicz(this)

    init {
        addNewEnemies(1)
    }

    private fun setup() {
        if (!pause) {
            updateNumberOfEnemies()
            enemys.removeIf { !it.alive }
            removeTouchedEnemies()
            updateFrameSpeedBooster()
            updatePolandBallHp()

            if (statistic.getPoints() >= Level.LEVEL_FINAL.pointsToNextLevel) {
                gameMode = ModeGame.WON
            } else if (statistic.getPoints() >= level.pointsToNextLevel) {
                level = level.getNextLevel()
                gameMode = ModeGame.NEXT_LEVEL
            }
        }
    }

    fun removeEnemies(enemy: Enemy) {
        enemy.killBody()
        enemy.updateStatistic()
        enemys.remove(enemy)
    }

    fun draw() {
        setup()
        if (!pause) {
            move()
        }
        when (gameMode) {
            ModeGame.LOSE -> {
                losePopup.draw()
                pause = true
            }
            ModeGame.WON -> {
                wonPopup.draw()
                pause = true
            }
            ModeGame.NEXT_LEVEL -> {
                nextLevelBoard.draw()
                pause = true
            }
            ModeGame.GAME -> {
                enemys.forEach { it.draw() }
                polandBall.draw()
                pawlowicz.draw()
            }
        }
        bar.draw()
    }

    fun addNewEnemies(number: Int) {
        for (i in 1..number) {
            enemys.add(RandomGenerator.generateEnemy(board = this))
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
            removeEnemies(it)
        }
    }

    private fun move() {
        box2d.step()
    }


    private fun shouldAddNewEnemy(): Boolean = (System.currentTimeMillis() - lastCreationTime) > level.frequencyOfNewEnemy


}