fun main(){
    println(pierwsza(12))
    println(pierwsza(13))
}

fun pierwsza(num : Int):Boolean{
    if (num < 2) return false
 
    for(i in 2 until num){
        if(num%i == 0){
            return false
        }
    }
    return true
        
}


