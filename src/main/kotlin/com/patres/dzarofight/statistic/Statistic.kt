package com.patres.dzarofight.statistic

import com.patres.dzarofight.model.enemy.*

class Statistic {

    private var sandedMoney = 0

    var tuskKill = 0
    var petruKill = 0
    var korwinKill = 0
    var kukizKill = 0
    var kijowskiKill = 0
    var schetynaKill = 0

    fun getPoints() : Int =
            tuskKill * Tusk.pointsForKill +
            petruKill * Petru.pointsForKill +
            korwinKill * Korwin.pointsForKill +
            kukizKill * Kukiz.pointsForKill +
            kijowskiKill * Kijowski.pointsForKill +
            schetynaKill * Schetyna.pointsForKill

    fun getMoney() : Int = getPoints() - sandedMoney


}