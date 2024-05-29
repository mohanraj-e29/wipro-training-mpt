class EventManager {

    data class Event(val id: String, val name: String, val date: String, val location: String)

    private val events: MutableMap<String, Event> = mutableMapOf()

    fun addEvent(event: Event): String {
        return if (events.containsKey(event.id)) {
            "Event with ID ${event.id} already exists."
        } else {
            events[event.id] = event
            "Event with ID ${event.id} has been added successfully.."
        }
    }

    fun removeEvent(eventId: String): String {
        return if (!events.containsKey(eventId)) {
            "Event with ID $eventId does not exist."
        } else {
            events.remove(eventId)
            "Event with ID $eventId has been removed."
        }
    }

    fun getEvent(eventId: String): String {
        val event = events[eventId]
        return event?.toString() ?: "Event with ID $eventId does not exist."
    }

    fun listEvents(): List<Event> {
        return events.values.toList()
    }
}

// Example usage
fun main() {
    val manager = EventManager()

    val event1 = EventManager.Event("1", "AR Rahman-Concert", "2024-06-15", "Chennai")
    val event2 = EventManager.Event("2", "Conference", "2024-07-20", "Delhi")

    println(manager.addEvent(event1))
    println(manager.addEvent(event2))
    println(manager.listEvents())
    println(manager.getEvent("1"))
    println(manager.removeEvent("1"))
    println(manager.listEvents())
    println(manager.getEvent("1"))
}
