fun main() {
    open class Transaction(
        val id: Int,
        val amount: Double,
        val date: String
    )

    // Income class inherits from Transaction
    class Income(
        id: Int,
        amount: Double,
        date: String
    ) : Transaction(id, amount, date) {
        override fun toString(): String {
            return "Income(id=$id, amount=$amount, date=$date)"
        }
    }

    // Expense class inherits from Transaction
    class Expense(
        id: Int,
        amount: Double,
        date: String
    ) : Transaction(id, amount, date) {
        override fun toString(): String {
            return "Expense(id=$id, amount=$amount, date=$date)"
        }
    }

    class TransactionList {
        private val transactions = mutableListOf<Transaction>()
        private var nextId = 1

        fun addTransaction(transaction: Transaction) {
            transactions.add(transaction)
            println("$transaction added")
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
    }


    val transactionList = TransactionList()

    // Add income and expense transactions
    val income = Income(1, 45000.0, "2024-05-02")
    val expense = Expense(2, 10000.0, "2024-05-09")
    val expense1 = Expense(2, 10000.0, "2024-05-11")
    val expense2= Expense(2, 10000.0, "2024-05-13")

    transactionList.addTransaction(income)
    transactionList.addTransaction(expense)
    transactionList.addTransaction(expense1)
    transactionList.addTransaction(expense2)
}

