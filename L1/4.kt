
// suma alikwotowa sumy s(n) - suma wszystkich dzielnikow wlasciwych liczby n

fun main(){
    sprawdz(28)
    sprawdz(12)
    sprawdz(8)
}

fun sprawdz(num : Int){
    var sum = 0
    for(i in 1 until num){
        if(num%i==0){
            sum += i
        }
    }

    when{
        sum == num -> println("doskonala - $sum")
        sum > num -> println("obfita - $sum")
        else -> println("niedomiarowa - $sum")
    }
}