fun main(){
    dodawanie(30)
}

fun dodawanie(num : Int){
    var sum = 0
    for (i in 2..num step 2){
        sum+=i
    }
    println("$sum")
}
