fun rev(word : String) : Boolean{
    val wordrev = word.reversed()
    return word == wordrev
}
fun main(){
    println(rev("nie"))
}
