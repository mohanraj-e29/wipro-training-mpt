class Event(public val name: String, public val date: String, public val type: String) : Schedulable {
    override fun schedule() {
        println("Event $name scheduled for $date")
    }

    override fun reschedule() {
        println("Event $name rescheduled for $date")
    }
}
