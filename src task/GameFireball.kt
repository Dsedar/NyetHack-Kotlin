
fun main() {
    val name = "Артур"
    var healthPoints = 89
    var isBlessed = true
    val isImmortal = true

    //Раса
    val race = "человек"
    val faction = when (race) {
        "дварф" -> "Скрытый народ Драконьих гор"
        "краснолюд" -> "Жители и страны Махакам"
        "орк" -> "Яростный и агрессивный народ воинов, по ту сторону Синих гор"
        "человек" -> "Обычный человек, ни прибавить ни убавить"
        "эльф" -> "Народ эльфов - искусные лучники и следопыты"
        else -> "Вы - никто и всем на вас плевать"
    }
    println(faction)

    //Аура
    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)

    //HP
    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    //Состояние игрока
    printPlayerStatus(auraColor, isBlessed, name, healthStatus)

    castFireballHypnose(1, 3)
}

private fun printPlayerStatus(  auraColor: String,
                                isBlessed: Boolean,
                                name: String,
                                healthStatus: String) {
    println("(Аура: $auraColor)" + "(Благославление: ${if (isBlessed) "Есть" else "Нет"})")
    println("$name$healthStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean): String {
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    val auraColor = if (auraVisible) "Зеленая" else "Красная"
    return auraColor
}

private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) =
     when (healthPoints) {
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


private fun castFireballHypnose(numFireballs: Int = 2, numHypnoseEffect: Int) {
    println ("У вас в руках появляется огненный шар! (x$numFireballs)")
    when (numHypnoseEffect) {
        in 1..10 -> println("На веселе")
        in 11..20 -> println("Выпивший")
        in 21..30 -> println("Пьяный")
        in 31..40 -> println("Сильно пьяный")
        in 41..50 -> println("В стельку")
    }
}