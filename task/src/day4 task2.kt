// Custom exception class for transaction processing errors
class TransactionProcessingException(message: String) : Exception(message)

// Define the ExportableData interface
interface Exportabledata {
    fun exportToCSV(): String
}

// Define the TransactionData class implementing the ExportableData interface
class Transactiondata(
    private var id: Int?,
    private var amount: Double?,
    private var description: String?,
    private var date: String?
) : ExportableData {

    // Getter and Setter for id
    fun getId(): Int? {
        return id
    }

    fun setId(newId: Int?) {
        id = newId
    }

    // Getter and Setter for amount
    fun getAmount(): Double? {
        return amount
    }

    fun setAmount(newAmount: Double?) {
        amount = newAmount
    }

    // Getter and Setter for description
    fun getDescription(): String? {
        return description
    }

    fun setDescription(newDescription: String?) {
        description = newDescription
    }

    // Getter and Setter for date
    fun getDate(): String? {
        return date
    }

    fun setDate(newDate: String?) {
        date = newDate
    }

    // Implement exportToCSV method from ExportableData interface
    override fun exportToCSV(): String {
        return "${id ?: ""},${amount ?: ""},${description ?: ""},${date ?: ""}"
    }

    override fun toString(): String {
        return "TransactionData(id=$id, amount=$amount, description='$description', date='$date')"
    }
}

fun main() {
    try {
        // Example of processing transactions
        processTransactions()
    } catch (e: TransactionProcessingException) {
        println("Transaction processing error: ${e.message}")
    }
}

fun processTransactions() {
    // Simulate transaction processing and throw an exception if an error occurs
    val transaction1 = TransactionData(1, 100.0, "Grocery", "2024-01-01")
    val transaction2 = TransactionData(2, null, "Fuel", "2024-01-02")

    if (transaction2.getAmount() == null) {
        throw TransactionProcessingException("Amount cannot be null for transaction 2")
    }

    println("Transactions processed successfully")
}
