// Define the MyExportableData interface
interface MyExportableData {
    fun exportToCSV(): String
}

// Implement the MyExportableData interface
class MyExportableDataImpl(
    private val data: List<List<String>>,
    private val delimiter: String = ","
) : MyExportableData {
    override fun exportToCSV(): String {
        val csvContent = StringBuilder()
        for (row in data) {
            for ((index, value) in row.withIndex()) {
                csvContent.append(value)
                if (index < row.size - 1) {
                    csvContent.append(delimiter)
                }
            }
            csvContent.append("\n")
        }
        return csvContent.toString()
    }
}

fun main() {
    // Example data
    val data = listOf(
        listOf("Name", "Age", "City"),
        listOf("John", "30", "New York"),
        listOf("Alice", "25", "Los Angeles"),
        listOf("Bob", "35", "Chicago")
    )

    // Create an instance of MyExportableDataImpl
    val exportableData = MyExportableDataImpl(data)

    // Export data to CSV
    val csvContent = exportableData.exportToCSV()

    // Print the CSV content
    println("CSV Content:")
    println(csvContent)
}
