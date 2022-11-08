package com.bignerdranch.nyethack


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

private fun printPlayerStatus(player: Player){
    println("(Аура: ${player.auraColor()})" + "(Благославление: ${if (player.isBlessed) "Есть" else "Нет"})")
    println("${player.name}${player.formatHealthStatus()}")
}

object Game {
    val player = Player("Madrigal", 89,true, false)
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

    init {
        println("Welcome, adventurer")
        player.castFireball()
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


