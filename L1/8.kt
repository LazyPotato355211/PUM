fun main(){
    val wynik = countVowels("Programowanie")
    println("$wynik")
}

fun countVowels(word : String):Int{
    val samogloski = "aeiouy"
    var licznik = 0
    val male = word.lowercase()

    for (i in male){
        if(i in samogloski){
            licznik++
        }
    }
    return licznik
}