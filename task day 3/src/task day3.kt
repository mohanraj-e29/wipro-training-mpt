// Task 9: Define an Exportable interface with a method to export transaction data to CSV.
interface Exportable {
    fun exportToCSV()
}

// Task 10: Apply encapsulation to Transaction properties using getters and setters ensuring sensitive data is protected.
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

// Task 11: Create generic functions to handle different types of collections (List, Set, Map) of transactions.
object DataHandler {
    fun <T> handleCollection(collection: Collection<T>, title: String) {
        println("-------- $title --------")
        for (item in collection) {
            println(item.toString())
        }
        println("------------------------")
    }
}

fun main() {
    // Example usage

    // Creating transactions
    val transaction1 = Transaction().apply {
        setId("1")
        setDescription("food")
        setAmount(200.0)
    }
    val transaction2 = Transaction().apply {
        setId("2")
        setDescription("fuel")
        setAmount(500.0)
    }
    val transaction3 = Transaction().apply {
        setId("3")
        setDescription("electronics")
        setAmount(1000.0)
    }

    // Handling different types of collections
    val transactionList = mutableListOf(transaction1, transaction2, transaction3)
    val transactionSet = mutableSetOf(transaction1, transaction2, transaction3)
    val transactionMap = mutableMapOf<String, Transaction>().apply {
        put(transaction1.getId(), transaction1)
        put(transaction2.getId(), transaction2)
        put(transaction3.getId(), transaction3)
    }

    // Printing transactions from collections
    DataHandler.handleCollection(transactionList, "List")
    DataHandler.handleCollection(transactionSet, "Set")
    DataHandler.handleCollection(transactionMap.values, "Map")
}
