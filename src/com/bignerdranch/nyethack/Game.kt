package com.bignerdranch.nyethack

import kotlin.system.exitProcess


fun main() {
    /*//Раса
    val race = "человек"
    val faction = when (race) {
        "дварф" -> "Скрытый народ Драконьих гор"
        "краснолюд" -> "Жители и страны Махакам"
        "орк" -> "Яростный и агрессивный народ воинов, по ту сторону Синих гор"
        "человек" -> "Обычный человек ни прибавить, ни убавить"
        "эльф" -> "Народ эльфов - искусные лучники и следопыты"
        else -> "Вы - никто и всем на вас плевать"
    }
    println(faction)*/
    Game.play()
}

object Game {
    val player = Player("madrigal")
    var currentRoom: Room = TownSquare()

    private var worldMap =  listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds)
                throw IllegalStateException("$direction out of Bounds")
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        }
        catch (e: Exception){
            "Invalid direction: $directionInput."
        }

    private  fun fight() = currentRoom.monster?.let{
        while (player.healthPoints>0 && it.healthPoints > 0)
            slay(it)
            Thread.sleep(1000)
        "Combat complete"
    } ?: "There's nothing here to fight"

    private fun slay(monster: Monster){
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")

        if (player.healthPoints<=0){
            println(">>>> You have been defeated! Thanks for playing. <<<<")
            exitProcess(0)
        }

        if (monster.healthPoints <= 0){
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }

    }

    init {
        println("Welcome, adventurer")
        //player.castFireball()
    }

    fun play(){
        while (true){
            //Игровой процесс
            println(currentRoom.description())
            println(currentRoom.load())

            //Состояние игрока
            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
    }
    private class GameInput(arg: String?){
        private val input = arg?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.toLowerCase()) {
            "fight" -> fight()
            "move" -> move(argument)
            else -> commandNotFound()
        }

        private fun commandNotFound()= "I'm not quite sure what you're trying to do!"
    }

    private fun printPlayerStatus(player: Player){
        println("(Аура: ${player.auraColor()})" + "(Благославление: ${if (player.isBlessed) "Есть" else "Нет"})")
        println("${player.name}${player.formatHealthStatus()}")
    }
}


