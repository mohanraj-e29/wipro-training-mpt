data class Event(val name: String, val date: String?, val time: String?)

fun main() {
    val event = Event("Meeting", null, "10:00 AM")

    // Gracefully handling null references
    val eventDate = event.date ?: "Date not specified"
    println("Event: ${event.name}, Date: $eventDate, Time: ${event.time ?: "Time not specified"}")
}
