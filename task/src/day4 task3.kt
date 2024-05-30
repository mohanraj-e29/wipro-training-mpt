// Custom exception class for transaction handling errors
class TransactionHandlingException(message: String) : Exception(message)

// Define the MyExportableData interface with a different name
interface MyExportableData {
    fun exportToCSV(): String
}

// Define the MyTransactionData class with a different name
class MyTransactionData(
    private var id: Int?,
    private var amount: Double?,
    private var description: String?,
    private var date: String?
) : MyExportableData {

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

    // Implement exportToCSV method from MyExportableData interface
    override fun exportToCSV(): String {
        return "${id ?: ""},${amount ?: ""},${description ?: ""},${date ?: ""}"
    }

    override fun toString(): String {
        return "MyTransactionData(id=$id, amount=$amount, description='$description', date='$date')"
    }
}

// Extension function to calculate total expenses
fun List<MyTransactionData>.totalExpenses(): Double {
    var total = 0.0
    for (transaction in this) {
        transaction.getAmount()?.let { amount ->
            if (amount < 0) {
                total += amount
            }
        }
    }
    return total
}

// Extension function to calculate total incomes
fun List<MyTransactionData>.totalIncomes(): Double {
    var total = 0.0
    for (transaction in this) {
        transaction.getAmount()?.let { amount ->
            if (amount >= 0) {
                total += amount
            }
        }
    }
    return total
}

fun main() {
    // Example transactions
    val transactions = listOf(
        MyTransactionData(1, 100.0, "Grocery", "2024-01-01"),
        MyTransactionData(2, -50.0, "Fuel", "2024-01-02"),
        MyTransactionData(3, 200.0, "Salary", "2024-01-03")
    )

    // Calculate and print total expenses and incomes
    println("Total Expenses: ${transactions.totalExpenses()}")
    println("Total Incomes: ${transactions.totalIncomes()}")
}
