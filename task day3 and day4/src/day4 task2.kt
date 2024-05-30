
fun main() {
    val event = Event("conference", "2024-05-21", "11:00 AM")

    // Handling parsing errors
    try {
        val eventDate = event.date!!.toInt() // Simulating parsing error
        println("Event date: $eventDate")
    } catch (e: NumberFormatException) {
        println("Error parsing event date: ${e.message}")
    }
}
