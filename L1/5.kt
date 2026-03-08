import kotlin.math.pow

fun main(){
    println(armstrong(153))
    println(armstrong(154))
}

fun armstrong(num : Int):Boolean{
    var suma = 0.0

    var nstring = num.toString() // najpierw liczba na string zamieniona

    var potega = nstring.length // potem sprawdzam dlugosc zeby okreslic sibie potege

    for(char in nstring){
        val liczba = char.digitToInt().toDouble()
        suma += liczba.pow(potega)
    }
    return suma.toInt() == num
}




