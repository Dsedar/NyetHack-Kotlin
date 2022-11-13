package com.bignerdranch.nyethack


import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val menuList = File("data/tavern-menu-items.txt")
        .readText()
        .split("\n")
val patronGold = mutableMapOf<String, Double>()

private fun String.toDragonSpeak() {
    this.replace(Regex("[aeiou]")){
        when (it.value){
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }
}

fun main(args: Array<String>) {
    val uniguePatrons: Set<String> = generateSequence {
        val first = patronList.random()
        val last = lastName.random()
        "$first $last"
    }.take(10).toSet()

    uniguePatrons.forEach{
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while(orderCount < 9) {
        placeOrder(uniguePatrons.random(), menuList.random())
        orderCount++
    }

    displayPatronBalances()
}

fun performPurchase(price: Double, patronName: String){
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

fun displayPatronBalances(){
    patronGold.forEach{ patron, balance ->
        println("$patron BALANCE: ${"%.2f".format(balance)}")
    }
}



fun placeOrder(patronName: String ,menuData: String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster for order")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buy $name ($type) for $price"
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if(name == "Dragon's Breath") {
        "$patronName exclaims: " + "Ah, delicious $name!".toDragonSpeak()
    }
    else {
        "$patronName says: Thanks for the $name"
    }
    println(phrase)
}