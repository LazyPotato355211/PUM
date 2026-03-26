val <T> List<T>.head: T
    get() = this.first()

val <T> List<T>.tail: List<T>
    get() = this.drop(1)

    fun main() {
        val lista = listOf(10,11,14,255,53)

        val h = lista.head
        val t = lista.tail
        println("pierwszy element - $h")
        println("lista bez pierwszego elementu - $t")
    }