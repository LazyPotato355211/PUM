fun tp( n : Int ){
    for(i in 1..n){
        if(i%3==0 && i%5==0){
            println("trzypiec")
        } else if(i%3==0){
            println("trzy")
        } else if(i%5==0){
            println("piec")
        } else {
            println(i)
        }
    }
}

fun main(){
    println(tp(15))
}
