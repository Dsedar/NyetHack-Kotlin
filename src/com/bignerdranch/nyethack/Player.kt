package com.bignerdranch.nyethack

import java.io.File
import kotlin.random.Random

class Player(_name: String,
             override var healthPoints: Int = 100,
             val isBlessed: Boolean,
             private var isImmortal:Boolean): Fightable {
    var name = _name
        get() = "${field.capitalize()} of $hometown"
        private set(value) {
            field = value.trim()
        }

    val hometown by lazy { selectHomeTown() }

    init {
        require(healthPoints > 0, {" Health points must be greater than zero."})
        require(name.isNotBlank(), {" Player must have a name"})
    }

    var currentPosition = Coordinate(0,0)
    
    constructor  (name: String): this(name,
                                isBlessed = true,
                                isImmortal = false){
        if (name.toLowerCase() == "kar") healthPoints = 40
    }
    
    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "Зеленая" else "Красная"
        return auraColor
    }

    fun formatHealthStatus() = when (healthPoints) {
            100 -> ", вы полностью здоровы!"
            in 90..99 -> " выглядит слегка потрепанным"
            in 70..89 -> if (isBlessed)
                ", вам бывало и лучше, но будьте уверены, это скоро пройдет!"
            else
                ", вам бывало и лучше!"
            in 50..69 -> ", вы получили несколько ран!"
            in 30..49 -> ", вам стоит как можно скорее выпить зелье лечения!"
            in 10..29 -> ", вы чувствуете, что вот вот отдадите концы!"
            else -> ", вы до сих пор не умерли?"
        }

    private fun selectHomeTown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .shuffled()
        .first()

    fun castFireball (numFireballs: Int = 2) =
        println("A glass of Fireball springs into existence. (x$numFireballs)")

    override val diceCount: Int =3
    override val diceSides: Int = 6
    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}