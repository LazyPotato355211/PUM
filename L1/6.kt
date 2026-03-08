fun main(){
    println(pierwsza(12))

}

fun pierwsza(num : Int):Boolean{
    if (num < 2) return false
 
    for(i in 1 until num){
        if(num%i == 0){
            return false }
    }
    return true
        
}


