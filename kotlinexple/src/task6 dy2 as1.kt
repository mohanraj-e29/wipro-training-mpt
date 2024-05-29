class EventManagerWrapper {

    // Define the Event data class
    data class Event(val id: String, val name: String, val date: String, val location: String)

    // Define the EventManager class implementing the Display interface
    class EventManager : Display {

        private val events: MutableMap<String, Event> = mutableMapOf()

        fun addEvent(event: Event): String {
            return if (events.containsKey(event.id)) {
                "Event with ID ${event.id} already exists."
            } else {
                events[event.id] = event
                "Event with ID ${event.id} has been added."
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

        // Implementation of the showEventDetails method from Display interface
        override fun showEventDetails(event: Event) {
            println("Event ID: ${event.id}")
            println("Event Name: ${event.name}")
            println("Event Date: ${event.date}")
            println("Event Location: ${event.location}")
        }
    }
}

// Define the Display interface
interface Display {
    fun showEventDetails(event: EventManagerWrapper.Event)
}

// Example usage
fun main() {
    val manager = EventManagerWrapper.EventManager()

    val event1 = EventManagerWrapper.Event("1", "Aniruth-Concert", "2024-06-19", "dubai")
    val event2 = EventManagerWrapper.Event("2", "ilayaraja-concert", "2024-07-20", "chennai")

    println(manager.addEvent(event1))
    println(manager.addEvent(event2))
    println(manager.listEvents())

    // Display event details
    manager.showEventDetails(event1)
    manager.showEventDetails(event2)

    println(manager.getEvent("1"))
    println(manager.removeEvent("1"))
    println(manager.listEvents())
    println(manager.getEvent("1"))
}
