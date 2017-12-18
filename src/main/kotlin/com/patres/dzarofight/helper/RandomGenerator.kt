package com.patres.dzarofight.helper

import com.patres.dzarofight.model.Board
import com.patres.dzarofight.model.enemy.*
import processing.core.PVector
import java.awt.Color
import java.util.*


class RandomGenerator {
    companion object {
        var random = Random()
        fun generateFloat(min: Float = 0f, max: Float): Float = random.nextFloat() * (max - min) + min
        fun generateInt(min: Int = 0, max: Int): Int = random.nextInt((max - min) + 1) + min
        fun generateColor(): Color = Color(random.nextInt(256 * 256 * 256))
        fun generateVector(min: Float = 0f, max: Float): PVector = PVector(generateFloat(min, max), generateFloat(min, max))

        fun generatePVectorOnBorder(sizeX: Int, sizeY: Int, radius: Float): PVector {
            val przedzial = (sizeX + sizeY) * 2
            val leftSideMax = sizeY
            val rightSideMax = leftSideMax + sizeY
            val topSideMax = rightSideMax + sizeX
            val bottomSideMax = topSideMax + sizeX
            val randomPlace = generateInt(1, przedzial)
            return when (randomPlace) {
                in 0..leftSideMax -> PVector(-radius, generateFloat(max = sizeY.toFloat()))
                in leftSideMax + 1..rightSideMax -> PVector(sizeX + radius, generateFloat(max = sizeY.toFloat()))
                in rightSideMax + 1..topSideMax -> PVector(generateFloat(max = sizeX.toFloat()), -radius)
                in topSideMax + 1..bottomSideMax -> PVector(generateFloat(max = sizeX.toFloat()), sizeY + radius)
                else -> throw Exception("Wrong position: $randomPlace in sizeX: $sizeX and sizeY: $sizeY")
            }
        }

        fun generateEnemy(board: Board): Enemy {
            val randomPlace = generateInt(1, 6)
            return when (randomPlace) {
                1 -> Tusk(board)
                2 -> Petru(board)
                3 -> Kukiz(board)
                4 -> Korwin(board)
                5 -> Kijowski(board)
                6 -> Schetyna(board)
                else -> throw Exception("Wrong enemy: $randomPlace")
            }
        }
    }
}

