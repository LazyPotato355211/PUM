import java.time.LocalDate
import java.time.Month
import kotlin.random.*

object DataProvider {
    val generalCosts = List(5) {
        Cost(
            CostType
                .values()[Random.nextInt(CostType.values().size)],
            LocalDate.of(
                2025, 
                Random.nextInt(1,13), 
                Random.nextInt(1,28)),
            Random.nextInt(5000)
        )
    }
}
enum class CostType(val costType: String) {
    REFUELING("Tankowanie"),
    SERVICE("Serwis"),
    PARKING("Parking"),
    INSURANCE("Ubezpieczenie"),
    TICKET("Mandat")
}


data class Cost (
    val type: CostType,
    val date: LocalDate,
    val amount: Int
)

sealed class MonthlyCostStatus {
    object NoCosts : MonthlyCostStatus() {
        override fun toString(): String = "NoCosts"
    }
    data class WithinLimit(val total: Int) : MonthlyCostStatus() 
    data class OverLimit(val total: Int, val exceededBy: Int) : MonthlyCostStatus()
}
 // data class przechowuje info/dane

fun main(){
    val costs = listOf(
    Cost(CostType.REFUELING, LocalDate.of(2025, 1, 10), 300),
    Cost(CostType.PARKING, LocalDate.of(2025, 1, 12), 50),
    Cost(CostType.SERVICE, LocalDate.of(2025, 2, 4), 1200)
)

println(classifyMonthlyCosts(costs, Month.JANUARY, 400))
println(classifyMonthlyCosts(costs, Month.FEBRUARY, 1000))
println(classifyMonthlyCosts(costs, Month.MARCH, 500))
}

fun classifyMonthlyCosts(costs: List<Cost>, month: Month, limit: Int): MonthlyCostStatus {
    val monthlyCosts = costs.filter { it.date.month == month } // filtr na koszty w danym miesiacu
    
    return when {
        monthlyCosts.isEmpty() -> MonthlyCostStatus.NoCosts // lista pusta = brak kosztow w miesiacu
        else -> {
            val total = monthlyCosts.sumOf { it.amount } // suma wszystkich przefiltrowanych kosztow
            if (total <= limit) {
                MonthlyCostStatus.WithinLimit(total) // porow suma z podanym limitem
            } else {
                MonthlyCostStatus.OverLimit(total, exceededBy = total - limit) // limit przekroczony -> oblicz o ile
            }
        }
    }
}