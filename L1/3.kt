fun main(){
    trojkat(10)
}

fun trojkat (n : Int){
    val liczba = Array(n) {IntArray(n)}

    for(i in 0 until n){
        for(s in 0 until (n -i)){
            print("  ")
        }

            for(j in 0..i){
    
                if(j==0 || j==i){
                    liczba[i][j]=1
                } 
                else {
                    liczba[i][j] = liczba[i-1][j-1] + liczba[i-1][j]
                }
                System.out.printf("%4d", liczba[i][j])
            }
            println()
    }
}