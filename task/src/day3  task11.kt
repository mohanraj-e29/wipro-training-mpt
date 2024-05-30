// Define the CSVExportable interface
interface CSVExportable {
    fun exportToCSV(): String
}

// Define the FinancialTransaction class
class FinancialTransaction(
    private var id: Int,
    private var amount: Double,
    private var description: String,
    private var date: String
) : CSVExportable {

    // Getter and Setter for id
    fun getId(): Int {
        return id
    }

    fun setId(newId: Int) {
        id = newId
    }

    // Getter and Setter for amount
    fun getAmount(): Double {
        return amount
    }

    fun setAmount(newAmount: Double) {
        amount = newAmount
    }

    // Getter and Setter for description
    fun getDescription(): String {
        return description
    }

    fun setDescription(newDescription: String) {
        description = newDescription
    }

    // Getter and Setter for date
    fun getDate(): String {
        return date
    }

    fun setDate(newDate: String) {
        date = newDate
    }

    // Implement exportToCSV method
    override fun exportToCSV(): String {
        return "$id,$amount,$description,$date"
    }

    override fun toString(): String {
        return "FinancialTransaction(id=$id, amount=$amount, description='$description', date='$date')"
    }
}

// Generic function to handle a list of items that are CSVExportable
fun <T : CSVExportable> handleList(items: List<T>) {
    for (item in items) {
        println(item)
    }
}

// Generic function to handle a set of items that are CSVExportable
fun <T : CSVExportable> handleSet(items: Set<T>) {
    for (item in items) {
        println(item)
    }
}

// Generic function to handle a map where values are CSVExportable
fun <K, V : CSVExportable> handleMap(items: Map<K, V>) {
    for ((key, value) in items) {
        println("$key -> $value")
    }
}

fun main() {
    // Create instances of FinancialTransaction
    val transaction1 = FinancialTransaction(1, 100.0, "Grocery", "2024-01-01")
    val transaction2 = FinancialTransaction(2, 50.0, "Fuel", "2024-01-02")

    // Collections of FinancialTransaction objects
    val transactionsList = listOf(transaction1, transaction2)
    val transactionsSet = setOf(transaction1, transaction2)
    val transactionsMap = mapOf(
        transaction1.getId() to transaction1,
        transaction2.getId() to transaction2
    )

    // Handle collections using generic functions
    println("Transactions List:")
    handleList(transactionsList)

    println("\nTransactions Set:")
    handleSet(transactionsSet)

    println("\nTransactions Map:")
    handleMap(transactionsMap)

    // Example of exporting to CSV
    println("\nExport to CSV:")
    println(transaction1.exportToCSV())
    println(transaction2.exportToCSV())
}
