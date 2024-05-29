fun main() {
    data class Transaction(
        val id: Int,
        val amount: Double,
        val date: String
    )

    class TransactionList {
        private val transactions = mutableListOf<Transaction>()
        private var nextId = 1

        fun addTransaction(amount: Double, date: String) {
            val transaction = Transaction(nextId++, amount, date)
            transactions.add(transaction)
            println("Transaction added: $transaction")
        }

        fun deleteTransaction(id: Int) {
            val transaction = transactions.find { it.id == id }
            if (transaction != null) {
                transactions.remove(transaction)
                println("Transaction deleted: $transaction")
            } else {
                println("Transaction with id $id not found.")
            }
        }

        fun filterTransactions(predicate: (Transaction) -> Boolean): List<Transaction> {
            return transactions.filter(predicate)
        }

        fun sortTransactions(comparator: Comparator<Transaction>) {
            transactions.sortWith(comparator)
        }
    }

    val transactionList = TransactionList()


    transactionList.addTransaction(10000.0, "2024-05-09")
    transactionList.addTransaction(20390.0, "2024-05-15")
    transactionList.addTransaction(14350.0, "2024-05-14")


    val filteredByAmount = transactionList.filterTransactions { it.amount > 150 }
    println("Filtered Transactions by Amount:")
    filteredByAmount.forEach { println(it) }


    transactionList.sortTransactions(compareBy { it.date })
    println("\nTransactions Sorted by Date:")
    transactionList.filterTransactions { true }.forEach { println(it) }


    transactionList.sortTransactions(compareByDescending { it.amount })
    println("\nTransactions Sorted by Amount:")
    transactionList.filterTransactions { true }.forEach { println(it) }
}
