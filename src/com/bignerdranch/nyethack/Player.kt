package com.bignerdranch.nyethack

class Player(_name: String,
             _healthPoints: Int,
             _isBlessed: Boolean,
             _isImmortal:Boolean) {
    var name = _name
        get() = field.capitalize()
        private set(value) {
            field = value.trim()
        }
    var healthPoints = _healthPoints
    val isBlessed = _isBlessed
    private val isImmortal = _isImmortal
    var currentPosition = Coordinate(0,0)

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

    fun castFireball (numFireballs: Int = 2) =
        println("A glass of Fireball springs into existence. (x$numFireballs)")
}