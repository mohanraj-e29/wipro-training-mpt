fun main() {
    val transactions = listOf(
        Transaction(1200.0, "2023-05-21", "Food", "Grocery shopping"),
        Transaction(1500.0, "2023-05-14", "Utilities", "wifi bill"),
        Transaction(1500.0, "2023-04-15", "Entertainment", "Movie with family"),
        Transaction(20000.0, "2023-05-20", "fees", "school fees")
    )

    transactions.forEach { transaction ->
        println("Transaction: Amount = ${transaction.amount}, Date = ${transaction.date}, Category = ${transaction.category}, Description = ${transaction.description ?: "N/A"}")
    }
}
