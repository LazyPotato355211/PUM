fun main(){
    pierwsza(12)
    pierwsza(7)

}

fun pierwsza(num : Int){
    if (num <= 1) {
        println("$num nie jest liczba pierwsza")
    }

    var sum = 0
    for(i in 1 until num){
        if(num%i == 0){
            sum += i }
    }
    if (sum == 1) { 
        println("$num to liczba pierwsza")}
    else {
        println("$num to nie jest liczba pierwsza")}
        
}