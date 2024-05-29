import java.util.Scanner

data class employee(val id: Int, var name: String, var position: String, var salary: Double)

class EmployeManagementSystem {
    private val employees = mutableListOf<Employee>()
    private val scanner = Scanner(System.`in`)

    fun start() {
        while (true) {
            println("\nEmployee Management System")
            println("1. Add Employee")
            println("2. View Employees")
            println("3. Update Employee")
            println("4. Delete Employee")
            println("5. Exit")
            print("Enter your choice: ")

            when (scanner.nextInt()) {
                1 -> addEmployee()
                2 -> viewEmployees()
                3 -> updateEmployee()
                4 -> deleteEmployee()
                5 -> {
                    println("Exiting...")
                    return
                }
                else -> println("Invalid choice. Please try again.")
            }
        }
    }

    private fun addEmployee() {
        print("Enter Employee ID: ")
        val id = scanner.nextInt()
        scanner.nextLine() // consume newline

        print("Enter Employee Name: ")
        val name = scanner.nextLine()

        print("Enter Employee Position: ")
        val position = scanner.nextLine()

        print("Enter Employee Salary: ")
        val salary = scanner.nextDouble()

        employees.add(Employee(id, name, position, salary))
        println("Employee added successfully.")
    }

    private fun viewEmployees() {
        if (employees.isEmpty()) {
            println("No employees found.")
        } else {
            println("\nEmployee List:")
            employees.forEach { println(it) }
        }
    }

    private fun updateEmployee() {
        print("Enter Employee ID to update: ")
        val id = scanner.nextInt()
        val employee = employees.find { it.id == id }

        if (employee != null) {
            scanner.nextLine() // consume newline

            print("Enter new name (leave blank to keep current: ${employee.name}): ")
            val newName = scanner.nextLine()
            if (newName.isNotBlank()) employee.name = newName

            print("Enter new position (leave blank to keep current: ${employee.position}): ")
            val newPosition = scanner.nextLine()
            if (newPosition.isNotBlank()) employee.position = newPosition

            print("Enter new salary (leave blank to keep current: ${employee.salary}): ")
            val newSalary = scanner.nextLine()
            if (newSalary.isNotBlank()) employee.salary = newSalary.toDouble()

            println("Employee updated successfully.")
        } else {
            println("Employee not found.")
        }
    }

    private fun deleteEmployee() {
        print("Enter Employee ID to delete: ")
        val id = scanner.nextInt()
        val employee = employees.find { it.id == id }

        if (employee != null) {
            employees.remove(employee)
            println("Employee deleted successfully.")
        } else {
            println("Employee not found.")
        }
    }
}

fun main() {
    val system = EmployeeManagementSystem()
    system.start()
}
