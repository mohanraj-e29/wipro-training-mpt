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
            val iterator = transactions.iterator()
            while (iterator.hasNext()) {
                val transaction = iterator.next()
                if (transaction.id == id) {
                    iterator.remove()
                    println("Transaction deleted: $transaction")
                    return
                }
            }
            println("Transaction with id $id not found.")
        }

        fun editTransaction(id: Int, amount: Double? = null, date: String? = null) {
            val transaction = transactions.find { it.id == id }
            if (transaction != null) {
                val updatedTransaction = transaction.copy(
                    amount = amount ?: transaction.amount,
                    date = date ?: transaction.date
                )
                transactions[transactions.indexOf(transaction)] = updatedTransaction
                println("Transaction updated: $updatedTransaction")
            } else {
                println("Transaction with id $id not found.")
            }
        }
    }


    val transactionList = TransactionList()


    transactionList.addTransaction(1500.0, "2024-05-16")
    transactionList.addTransaction(20000.0, "2024-05-14")
    transactionList.deleteTransaction(1)
    transactionList.editTransaction(2, amount = 250.0)
}
