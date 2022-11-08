import kotlin.math.roundToInt


var volumeOfDrink: Double = 0.0
val pintOfDrink: Double = 0.125
var remainingVolumeOfDrink: Double = 0.0
var playerDracoin: Double = 5.0
var playerGold = 10.0
var playerSilver = 10.0

class TavernTask {
}

fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91,1")
    //com.bignerdranch.nyethack.placeOrder("elixir,Shirley's Temple,4.12")

}

private fun performPurchase(price: Double){
    displayBalance()
    val totalPurse = playerDracoin + (playerGold*1.43) + (playerSilver/100.0)
    println("ОБЩИЙ БАЛАНС - ${"%.2f".format(totalPurse)}")

    val remainingBalance = totalPurse - price
    if (remainingBalance<0.0)
        throw IllegalStateException("У ВАС НЕДОСТАТОЧНО СРЕДСТВ")


    println("ПОКУПКА - $price")

    //remainingBalance = totalPurse - price
    println("ОСТАТОК БАЛАНСА - ${"%.2f".format(remainingBalance)}")

    val remainingDracoin = remainingBalance
    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerDracoin = remainingDracoin
    playerGold = remainingGold.toDouble()
    playerSilver = remainingSilver.toDouble()
    displayBalance()
}

private fun displayBalance(){
    println("БАЛАНС: GOLD: $playerGold, SILVER: $playerSilver, DRACOIN: $playerDracoin")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")){
        when (it.value){
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }


fun placeOrder(menuData: String){
    val indexOfApostrophe = com.bignerdranch.nyethack.TAVERN_NAME.indexOf('\'')
    val tavernMaster = com.bignerdranch.nyethack.TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Вы говорите с $tavernMaster для заказа")

    val (type, name, price, count) = menuData.split(',')

    if (name == "Dragon's Breath")
        volumeOfDrink = 5.0
    remainingVolumeOfDrink = volumeOfDrink - pintOfDrink * count.toDouble()

    if (count.toDouble() > 1.0) {
        val countPrice = price.toDouble() * count.toDouble()
        val message = "Вы покупаете $count $name ($type) за $countPrice"
        println(message)
        println("Осталось $name - $remainingVolumeOfDrink")
        performPurchase(countPrice)
    }
    else {
        val message = "Вы покупаете $count $name ($type) за $price"
        println(message)
        println("Осталось $name - $remainingVolumeOfDrink")
        performPurchase(price.toDouble())
    }

    //val message = "Вы покупаете $count $name ($type) за $price"
    //println(message)
    //println("Осталось $name - $getRemainingVolumeOfDrink")
    //com.bignerdranch.nyethack.performPurchase(price.toDouble())

    val phrase = if(name == "Dragon's Breath") {
        "Вы говорите: ${toDragonSpeak("Ah, delicious $name!")}"
    }
    else {
        "Вы говорите: Спасибо за $name"
    }
    println(phrase)
}
