package com.patres.dzarofight.helper

class FilterMasks {

    companion object {
        const val CATEGORY_DEFAULT = 0x0000
        const val CATEGORY_POLAND_BALL = 0x0001
        const val CATEGORY_ENEMY = 0x0002
        const val CATEGORY_FRIEND = 0x0003

        const val MASK_DEFAULT = -1
        const val MASK_POLAND_BALL = CATEGORY_ENEMY
        const val MASK_ENEMY = CATEGORY_POLAND_BALL
        const val MASK_FRIEND = CATEGORY_ENEMY
    }

}