import java.time.LocalDate
import java.time.Month
import kotlin.random.*

object DataProvider {
    val generalCosts = List(20) {
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
    println(groupedCostMap(DataProvider.generalCosts))
}

fun groupedCostMap(costs: List<Cost>): Map<Month, List<Cost>> {
    return costs
        .groupBy { it.date.month } // mapa - kluczem miesiac z daty kosztu
        .toSortedMap() // sort po kluczu rosnaco
}