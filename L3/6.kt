fun main(){
    println(perm(listOf(1, 2, 3)))
}

fun perm(input : List<Int>): List<List<Int>>{
    if (input.isEmpty()){ // sprw czy lista pust
        return listOf(emptyList())
    }

    return input.indices.flatMap{  i -> // sprawdzanie po indeksie i liczbie
        val element = input[i] // elm pod indx jako poczatek perm
        val reszta = input.filterIndexed {index, _ -> index != i} // nowa list bez wybranego elem
        perm(reszta).map {listOf(element) + it} // gen perm reszty + elem
    }
}