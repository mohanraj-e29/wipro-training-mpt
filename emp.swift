struct Employee {
  let name: String
  let role: String
  let salary: Double
}

var employees: [Employee] = []

func displayMenu() {
  print("Employee Management System")
  print("1. Add Employee")
  print("2. View Employees")
  print("3. Delete Employee")
  print("4. Print List")
  print("5. Exit")
}

func getInput() -> String {
  return readLine()!
}

func getDoubleInput() -> Double {
  while true {
    if let input = readLine(), let value = Double(input) {
      return value
    } else {
      print("Invalid input. Please enter a number.")
    }
  }
}

func addEmployee() {
  print("Enter employee name:")
  let name = getInput()
  print("Enter employee role:")
  let role = getInput()
  print("Enter employee salary:")
  let salary = getDoubleInput()
  employees.append(Employee(name: name, role: role, salary: salary))
  print("Employee added successfully!")
}

func viewEmployees() {
  if employees.isEmpty {
    print("No employees found.")
    return
  }

  for (index, employee) in employees.enumerated() {
    print("\(index + 1). \(employee.name) - \(employee.role) - \(employee.salary)")
  }
}

func deleteEmployee() {
  if employees.isEmpty {
    print("No employees found. Please add employees first.")
    return
  }

  print("Enter employee number (1-\(employees.count)) or 0 to exit:")
  let employeeIndex = Int(getInput())! - 1
  if employeeIndex < 0 || employeeIndex >= employees.count {
    print("Invalid employee number.")
    return
  }

  employees.remove(at: employeeIndex)
  print("Employee deleted successfully!")
}

func printList() {
  if employees.isEmpty {
    print("No employees found.")
    return
  }

  for employee in employees {
    print("\(employee.name) - \(employee.role) - $\(employee.salary)")
  }
}

func run() {
  while true {
    displayMenu()
    print("Enter your choice:")
    let choice = getInput()

    switch choice {
    case "1": addEmployee()
    case "2": viewEmployees()
    case "3": deleteEmployee()
    case "4": printList()
    case "5":
      print("Exiting program.")
      return
    default: print("Invalid choice. Please try again.")
    }
  }
}

run()
