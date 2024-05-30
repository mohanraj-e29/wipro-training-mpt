interface Schedulable {
    fun schedule()
    fun reschedule()
}

class Schedule<T : Schedulable> {
    fun addEvent(event: T) {
        event.schedule()
    }

    fun rescheduleEvent(event: T) {
        event.reschedule()
    }
}