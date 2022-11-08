package com.bignerdranch.nyethack

class SwordJuggler {
}

fun main() {
    var swordJuggling: Int ?=null
    val isJugglingProficient = (1..3).shuffled().last() ==3
    if (isJugglingProficient){
        swordJuggling = 2
    }

    try {
        proficiencyCheck(swordJuggling)
        swordJuggling = swordJuggling!!.plus(1)
    } catch (e: Exception){
        println(e)
    }


    println("Вы жонглируете $swordJuggling мечами!")
}

fun proficiencyCheck(swordsJuggling: Int?) {
    //swordsJuggling ?: throw com.bignerdranch.nyethack.UnskilledSwordJugglerException()
    checkNotNull(swordsJuggling, {"Игрок не может жонглировать"})
}

class UnskilledSwordJugglerException():
        IllegalStateException("Игрок не может жонглировать")