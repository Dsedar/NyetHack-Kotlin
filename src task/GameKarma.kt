class GameKarma {
}
fun main() {
    val name = "Артур"
    var healthPoints = 89
    var isBlessed = true
    val isImmortal = true

    //Раса
    val race = "Человек"
    val description = when (race) {
        "Дварф" -> "Скрытый народ Драконьих гор"
        "Краснолюд" -> "Жители и страны Махакам"
        "Орк" -> "Яростный и агрессивный народ воинов, по ту сторону Синих гор"
        "Человек" -> "Обычный человек, ни прибавить ни убавить"
        "Эльф" -> "Народ эльфов - искусные лучники и следопыты"
        else -> "Вы - никто и всем на вас плевать"
    }

    //Аура
    val auraVisible = isBlessed && healthPoints >50 || isImmortal
    //var auraColor = if (auraVisible) "Зеленая" else "Красная"
    //Карма
    val karma = (Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20 ).toInt()
    var auraColor = when (karma) {
        in 0..5 -> "Красный"
        in 6..10 -> "Оранжевый"
        in 11..15 -> "Пурпурный"
        in 16..20 -> "Зеленый"
        else -> "Ни туда ни сюда"
    }

    //HP
    val healthStatus = when (healthPoints){
        100 -> ", вы полностью здоровы!"
        in 90..99 -> "выглядит слегка потрепанным"
        in 70..89 -> if (isBlessed)
            ", вам бывало и лучше, но будьте уверены, это скоро пройдет!"
        else
            ", вам бывало и лучше!"
        in 50..69 -> ", вы получили несколько ран!"
        in 30..49 -> ", вам стоит как можно скорее выпить зелье лечения!"
        in 10..29 -> ", вы чувствуете, что вот вот отдадите концы!"
        else -> ", вы до сих пор не умерли?"
    }
    //Состояние игрока
    val statusFormatString = "(HP: $healthPoints)(A:${if (auraVisible) auraColor else "Не видна"}) -> $name$healthStatus"
    println(statusFormatString)

    /*println("$race - $description")
    println("Аура: ${if (auraVisible) auraColor else "Не видна"}" + " Благославление: ${if(isBlessed) "Есть" else "Нет"}")
    println("$name$healthStatus")*/
}