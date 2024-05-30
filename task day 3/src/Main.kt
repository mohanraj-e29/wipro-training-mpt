fun main() {
    // Task 9: Schedule class
    val schedule = Schedule<Event>()
    val event1 = Event("office trip", "2024-05-24", "tour")
    val event2 = Event("Meeting", "2024-05-21", "Meeting")
    val event3 = Event("conference", "2024-05-20", "conference")
    val event4 = Event("project submission", "2024-05-20", "dead line")

    schedule.addEvent(event1)
    schedule.rescheduleEvent(event2)

    // Task 10: Event encapsulation
    println("Event name: ${event1.name}, Date: ${event1.date}, Type: ${event1.type}")

    // Task 11: EventManager for filtering
    val eventManager = EventManager()
    eventManager.addEvent(event1)
    eventManager.addEvent(event2)
    eventManager.addEvent(event3)
    eventManager.addEvent(event4)

    val eventsOnMay20 = eventManager.filterByDate("2024-05-20")
    println("Events on 2024-05-20:")
    eventsOnMay20.forEach { println(it.name) }

    // Task 12: DataManager for flexible data handling
    val dataManager = DataManager<String>()
    dataManager.addData("jojo")
    dataManager.addData("dio")

    val attendees = dataManager.getData()
    println("Attendees:")
    attendees.forEach { println(it) }
}
