fun main(){
    println( srt(listOf(
        "cherry", 
        "blueberry", 
        "citrus", 
        "apple", 
        "apricot", 
        "banana", 
        "coconut")
    ))
}

fun srt(input: List<String>) : List<Pair<String, List<String>>>{
    return input
    .filter {it.length %2 == 0} // parzysta ilosc znakow
    .groupBy {it.first().toString()} // groupowanie po pierwszej lit slowa (klucz)
    .toSortedMap() // sort wedlg klucz - lit (automat)
    .toList() // map na liste par
}