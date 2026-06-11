fun main() {
    println(countElements(listOf(listOf("a", "b", "c"), listOf("c", "d", "f"), listOf("d", "f", "g"))))
}

fun countElements(input : List<List<String>>): Map<String, Int> {
    return input.flatten() // splaszcza liste do list<str> (prostej listy )
    .groupingBy { it } // grupuje te same napisy, kluczem - tekst
    .eachCount() // zlicza wyatepowania

}