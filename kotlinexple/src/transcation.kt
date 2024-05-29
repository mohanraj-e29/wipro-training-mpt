data class Transaction(
    val amount: Double,
    val date: String,
    val category: String,
    val description: String? = null
)

fun main() {
    // Create a list of transactions, including an electronic transaction
    val transactions = listOf(

        Transaction(50000.0, "2023-05-25", "Electronics", "New smartphone purchase")
    )

    // Print details of each transaction
    transactions.forEach { transaction ->
        println("Transaction: Amount = ${transaction.amount}, Date = ${transaction.date}, Category = ${transaction.category}, Description = ${transaction.description ?: "N/A"}")
    }
}
