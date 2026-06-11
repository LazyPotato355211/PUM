fun main(){
    val lst = listOf(0, 1, 1, 1, 4, 4, 4, 9, 3, 3, 3, 3, 3, 3)
    println(findDuplicates(lst))
}

fun <T : Comparable<T>> findDuplicates(lst:List<T>):List<T>{  // comparable zeby typ byl porownywalny
    val duplicat = mutableSetOf<T>() // tworz pusty
    val sortowane = lst.sorted()

    for(i in 0 until sortowane.size - 1){
        if (sortowane[i] == sortowane[i + 1]){ // jesli duplikat dodaj do lidty
            duplicat.add(sortowane[i])
        }
    }
    return duplicat.sorted()
}