fun main(){
    println(suma(listOf( 1, -4, 12, 0, -3, 29, -150))
)
}

fun suma(a: List<Int>): Int{
    return a.filter {it > 0} // twrz list zaiwer liczby spelniajace warunek w nawiasie
    .sum() // sumuje wszystkie liczby z powyzszej listy
}



