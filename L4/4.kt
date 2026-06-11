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
    val costs = listOf(
    Cost(CostType.PARKING, LocalDate.of(2025, 1, 15), 30),
    Cost(CostType.SERVICE, LocalDate.of(2025, 1, 5), 900)
)

println(formatCosts(costs, PlCostFormatter))
}

interface CostFormatter { // kontrakt dla klas od formatowania kosztow
    fun format(cost: Cost): String
}

object PlCostFormatter : CostFormatter {
    override fun format(cost: Cost): String {
        val day = cost.date.dayOfMonth.toString().padStart(2, '0') // dodanie 0# do dnia, jesli cyfra 10<
        return "$day ${cost.type.name} ${cost.amount} zl"
    }
}

fun formatCosts(costs: List<Cost>, formatter: CostFormatter): String {
    return costs
        .sortedBy { it.date} // sort listy chronologicznie od oldest
        .joinToString("\n") {formatter.format(it) } // join laczy map ze zlacz, kazdy elem formatt... + \n
}