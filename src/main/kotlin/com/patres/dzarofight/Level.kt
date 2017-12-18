package com.patres.dzarofight

enum class Level(
        val counterLevel: Int,
        val pointsToNextLevel: Int,
        val frequencyOfNewEnemy: Int
) {
    LEVEL_1(1, 10, 2000),
    LEVEL_2(2, 25, 1500),
    LEVEL_3(3, 60, 1000),
    LEVEL_4(4, 100, 750),
    LEVEL_5(5, 150, 600),
    LEVEL_6(6, 225, 500),
    LEVEL_7(7, 300, 400),
    LEVEL_8(8, 500, 300),
    LEVEL_9(9, 750, 200),
    LEVEL_10(10, 1000, 100),
    LEVEL_FINAL(11, 10000, 100);

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