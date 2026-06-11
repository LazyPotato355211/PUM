fun main(){
    println(addToBoolean())
}

fun addToBoolean() : Map<Int, Boolean> {
   val dupli = mutableMapOf<Int, Boolean>() // twrz pusta mape
   for (i in 1..20){
    if(i%2==0){

        dupli[i] = true // do i przypis wart true
    }else {
    dupli[i]=false} // do i wart false
   }
   return dupli
}
