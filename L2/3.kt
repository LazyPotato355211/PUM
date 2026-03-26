fun main(){
    val w1 = isSorted(listOf(1, 2, 3, 4), {i: Int, j: Int -> i < j})
    val w2 = isSorted(listOf(1, 1, 1, 1), {i: Int, j: Int -> i==j})
    val w3 = isSorted(listOf("ahyyhh", "bkjn", "cnn", "duu"), {i: String, j: String -> i.first() < j.first()})
    println(w1)
    println(w2)
    println(w3)
}

fun <A> isSorted(lst: List<A> , order: (A, A) -> Boolean): Boolean{
    for(i in 0 until lst.size - 1){
       if (!order(lst[i], lst[i+1])){
        return false
    }
    }
    return true
}