data class Expense(
    val name: String,
    val amount: Double
)

class User(private val username: String, private val password: String) {
    private val expenses = mutableListOf<Expense>()

    fun login(inputPassword: String): Boolean {
        return inputPassword == password
    }

    fun addExpense(name: String, amount: Double) {
        expenses.add(Expense(name, amount))
        println("Expense added: $name - $amount")
    }

    fun displaySummary() {
        println("User: $username")
        println("Summary of Expenses:")
        expenses.forEachIndexed { index, expense ->
            println("Expense ${index + 1}: ${expense.name} - ${expense.amount}")
        }
        val totalExpense = expenses.sumByDouble { it.amount }
        println("Total Expenses: $totalExpense")
    }
}

fun main() {

    while(true) {
        print("Enter username: ")
        val username = readLine() ?: ""

        print("Enter password: ")
        val password = readLine() ?: ""


        val user = User(username, password)



        if (username == "mohan" && password == "123") {
            println("Login successful!")

            user.addExpense("Expense 1", 10000.0)
            user.addExpense("Expense 2", 12200.0)
            user.displaySummary()
        } else {
            println("Login failed!")
        }
    }
}
