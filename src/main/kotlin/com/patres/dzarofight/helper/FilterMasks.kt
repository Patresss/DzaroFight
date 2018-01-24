package com.patres.dzarofight.helper

class FilterMasks {

    companion object {
        val CATEGORY_POLAND_BALL = 0x0001
        val CATEGORY_ENEMY = 0x0002
        val CATEGORY_FRIEND = 0x0003

        val MASK_POLAND_BALL = CATEGORY_ENEMY
        val MASK_ENEMY = CATEGORY_POLAND_BALL
        val MASK_FRIEND = CATEGORY_ENEMY
    }

}