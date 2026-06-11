fun main(){
    println(evenPositiveSquare(listOf(1, 2, 3, 5, -6, -1, -1, 2, 3)))
}

fun evenPositiveSquare(input : List<Int>) : List<Int> {
    return input // indexed daje dostep do indeksu i liczby
    .filterIndexed {index, value -> index % 2!= 0 && value > 0} // parametry: index (pos), value (liczba)
    .map {it * it} // przeksztalca kazda liczbe po filter, i podnosi do ^2

}