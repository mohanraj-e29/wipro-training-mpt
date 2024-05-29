class Task8Wrapper {

    // Task 8: Define the Event data class
    open class Event(val id: String, val name: String, val date: String, val location: String)

    // Task 8: Define the SpecialEvent subclass with additional features
    class SpecialEvent(
        id: String,
        name: String,
        date: String,
        location: String,
        val vipList: List<String>,
        val premiumServices: List<String>
    ) : Event(id, name, date, location)

    // Task 8: Example usage
    fun main() {
        val event = SpecialEvent(
            "1",
            "Political Meeting",
            "2024-06-19",
            "pondicherry",
            listOf("Vip1", "Vip2"),
            listOf("Front-row seats", "VIP lounge access")
        )

        // Display event details
        println("Event ID: ${event.id}")
        println("Event Name: ${event.name}")
        println("Event Date: ${event.date}")
        println("Event Location: ${event.location}")
        println("VIP List: ${event.vipList}")
        println("Premium Services: ${event.premiumServices}")
    }
}

// Instantiate Task8Wrapper and call its main function
fun main() {
    val task8Wrapper = Task8Wrapper()
    task8Wrapper.main()
}


/*
* Task 5: Design a EventManager class with methods to add and remove events.
Task 6: Create a Display interface with a method to show event details and implement it in the EventManager.
Task 7: Utilize higher-order functions to implement a simple notification system for event updates.
Task 8: Construct subclass SpecialEvent with additional features like VIP lists and premium services.*/