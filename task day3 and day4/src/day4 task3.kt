
// Extension function to tag an event
fun Event.tagEvent(tag: String): Event {
    return Event("$name [$tag]", date, time)
}

// Extension function to categorize an event
fun Event.categorizeEvent(category: String): Event {
    return Event("$category: $name", date, time)
}

fun main() {
    val event = Event("Meeting", "2024-05-20", "10:00 AM")
    val event1 = Event("Interview", "2024-05-20", "15:00 pM")

    // Using extension functions
    val taggedEvent = event.tagEvent("Important")
    val categorizedEvent = event.categorizeEvent("Work")

    val taggedEvents = event1.tagEvent("Important")
    val categorizedEvents = event1.categorizeEvent("Work")
println()
    println("Tagged Event: ${taggedEvent.name}")
    println("Categorized Event: ${categorizedEvent.name}")
    println()
    println()
    println("Tagged Event: ${taggedEvents.name}")
    println("Categorized Event: ${categorizedEvents.name}")

}
