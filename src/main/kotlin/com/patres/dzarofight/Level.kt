package com.patres.dzarofight

enum class Level(
        val counterLevel: Int,
        val pointsToNextLevel: Int,
        val frequencyOfNewEnemy: Int
) {
    LEVEL_1(1, 3, 2000),
    LEVEL_2(2, 10, 1500),
    LEVEL_3(3, 25, 1000),
    LEVEL_4(4, 50, 750),
    LEVEL_5(5, 100, 600),
    LEVEL_6(6, 175, 500),
    LEVEL_7(7, 250, 400),
    LEVEL_8(8, 500, 300),
    LEVEL_9(9, 750, 200),
    LEVEL_FINAL(10, 1000, 100);

    fun getNextLevel(): Level {
        val nextLevelCounter = this.counterLevel + 1
        val levels = Level.values().filter { it.counterLevel ==  nextLevelCounter}
        if(levels.size == 1) {
            return levels.first()
        } else {
            throw Exception("Wrong level: $counterLevel")
        }
    }
}