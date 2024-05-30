class EventManager {
    private val events = mutableListOf<Event>()

    fun addEvent(event: Event) {
        events.add(event)
    }

    fun filterByDate(date: String): List<Event> {
        return events.filter { it.date == date }
    }

    fun filterByType(type: String): List<Event> {
        return events.filter { it.type == type }
    }
}
