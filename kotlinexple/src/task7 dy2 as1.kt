// the notification system using a higher-order function
fun notifyEventUpdate(eventId: Int, notificationHandler: (EventManager.Event) -> Unit) {

    val event = fetchEventDetails(eventId)
    notificationHandler(event)
}


fun fetchEventDetails(eventId: Int): EventManager.Event {
    return EventManager.Event(eventId.toString(), "sports event", "2024-08-31", "pondicherry")
}


fun main() {

    val eventUpdateHandler: (EventManager.Event) -> Unit = { event ->
        println("Event ID: ${event.id}")
        println("Event Name: ${event.name}")
        println("Event Date: ${event.date}")
        println("Event Location: ${event.location}")
        println("Notification sent for event update!")
    }

    notifyEventUpdate(1, eventUpdateHandler)
}
