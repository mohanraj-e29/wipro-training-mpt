class DataManager<T> {
    private val data = mutableListOf<T>()

    fun addData(item: T) {
        data.add(item)
    }

    fun getData(): List<T> {
        return data
    }
}
