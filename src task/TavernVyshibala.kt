import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniguePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
        .readText()
        .split("\n")
val patronGold = mutableMapOf<String, Double>()
var newPatronGold = mutableMapOf<String, Double>()
var newUniquePatrons = mutableSetOf<String>()

fun main(args: Array<String>) {
    (0..9).forEach{
        val first = com.bignerdranch.nyethack.patronList.shuffled().first()
        val last = com.bignerdranch.nyethack.lastName.shuffled().first()
        val name = "$first $last"
        com.bignerdranch.nyethack.uniguePatrons += name
    }
    com.bignerdranch.nyethack.uniguePatrons.forEach{
        com.bignerdranch.nyethack.patronGold[it] = 6.0
    }

    var orderCount = 0
    while(orderCount < 9) {
        com.bignerdranch.nyethack.placeOrder(
            com.bignerdranch.nyethack.uniguePatrons.shuffled().first(),
            com.bignerdranch.nyethack.menuList.shuffled().first()
        )
        orderCount++
    }

    com.bignerdranch.nyethack.displayPatronBalances()

    com.bignerdranch.nyethack.patronGold.forEach{ patron, balance ->
        if (balance<=0.0){
            newUniquePatrons = com.bignerdranch.nyethack.uniguePatrons.remove(patron)
            newPatronGold = com.bignerdranch.nyethack.patronGold.remove(patron)
        }
    }
    println(newPatronGold)
    println(newUniquePatrons)
}


fun performPurchase(price: Double, patronName: String){
    val totalPurse = com.bignerdranch.nyethack.patronGold.getValue(patronName)
    com.bignerdranch.nyethack.patronGold[patronName] = totalPurse - price
}

fun displayPatronBalances(){
    com.bignerdranch.nyethack.patronGold.forEach{ patron, balance ->
        println("$patron BALANCE: ${"%.2f".format(balance)}")
    }
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


fun placeOrder(patronName: String ,menuData: String){
    val indexOfApostrophe = com.bignerdranch.nyethack.TAVERN_NAME.indexOf('\'')
    val tavernMaster = com.bignerdranch.nyethack.TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster for order")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buy $name ($type) for $price"
    println(message)

    com.bignerdranch.nyethack.performPurchase(price.toDouble(), patronName)

    val phrase = if(name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    }
    else {
        "$patronName says: Thanks for the $name"
    }
    println(phrase)
}