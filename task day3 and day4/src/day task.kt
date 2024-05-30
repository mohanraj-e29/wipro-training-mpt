class `DayTask` {
    // Task 1: Implement null safety features to handle the absence of transaction data.
    fun handleNullSafety(transaction: Transaction?) {
        if (transaction != null) {
            // Transaction data is not null, proceed with processing
            println()
            println("Transaction data is not null: $transaction")
            println()
        } else {
            // Transaction data is null, handle accordingly
            println()
            println("Transaction data is null")
            println()
        }
    }

    // Task 2: Write custom exception classes to handle errors related to transaction processing.
    class TransactionProcessingException(message: String) : Exception(message)

    fun processTransaction(transaction: Transaction) {
        // Example of throwing a custom exception
        if (transaction.getAmount() <= 0) {
            throw TransactionProcessingException("Invalid transaction amount: ${transaction.getAmount()}")
        }
        // Otherwise, proceed with transaction processing
    }
}

class Transaction {
    private var id: String = ""
    private var description: String = ""
    private var amount: Double = 0.0

    fun getId(): String {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getDescription(): String {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getAmount(): Double {
        return amount
    }

    fun setAmount(amount: Double) {
        this.amount = amount
    }

    override fun toString(): String {
        return "Transaction(id=$id, description=$description, amount=$amount)"
    }
}

// Task 3: Create extension functions for the List<Transaction> class to calculate total expenses and incomes.
fun List<Transaction>.calculateTotalExpenses(): Double {
    return this.filter { it.getAmount() < 0 }.sumByDouble { it.getAmount() }
}

fun List<Transaction>.calculateTotalIncomes(): Double {
    return this.filter { it.getAmount() >= 0 }.sumByDouble { it.getAmount() }
}

fun main() {
    // Example usage

    // Task 1: Null safety handling
    println()
    println("Null Safety Handling")

    val task1 = `DayTask`()
    val transaction: Transaction? = null
    task1.handleNullSafety(transaction)
    println()
    println()
    println()
    println() // Empty line for separation

    // Task 2: Custom exception handling
    println("Custom exception Handling")
    println()
    println()
    println()
    val task2 = `DayTask`()
    val transaction2 = Transaction().apply {
        setId("1")
        setDescription("Food")
        setAmount(-100.0)
    }
    try {
        task2.processTransaction(transaction2)
        println("Transaction processed successfully.")
    } catch (e: `DayTask`.TransactionProcessingException) {
        println("Transaction processing failed: ${e.message}")
    }

    println() // Empty line for separation

    // Task 3: Extension functions
    val transactions = listOf(
        Transaction().apply { setId("1"); setDescription("Food"); setAmount(-100.0) },
        Transaction().apply { setId("2"); setDescription("Salary"); setAmount(3000.0) },
        Transaction().apply { setId("3"); setDescription("Rent"); setAmount(-1500.0) }
    )
    println()
    println("Expenses")
    println()
    println()
    println()
    println("Total Expenses: ${transactions.calculateTotalExpenses()}")
    println("Total Incomes: ${transactions.calculateTotalIncomes()}")
}
