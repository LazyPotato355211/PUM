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

fun main(){
    printGroupedCosts(DataProvider.generalCosts)
}

fun printGroupedCosts(costs: List<Cost>) {
    val result = costs
        .groupBy {it.date.month }
        .toSortedMap()
        .entries
        .joinToString("\n") { (month, monthCosts) ->
            val formattedCosts = monthCosts
                .sortedBy { it.date }
                .joinToString("\n") { cost ->
                    val day = cost.date.dayOfMonth.toString().padStart(2, '0')
                    "$day ${cost.type.name} ${cost.amount} zl"
                }
            
            "${month.name}\n$formattedCosts"
        }
        
    println(result)
}