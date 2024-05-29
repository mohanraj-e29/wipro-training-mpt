<<<<<<< HEAD
data class emp(val name: String, val salary: Double, val role:String)

fun getEmp(name: String, salary: Double, role: String): emp {
    return emp(name, salary, role)
}

fun main() {
    val emp1 = emp("mohanraj", 10.0, "Trainer")
    val emp2 = emp("sathish", 11.0, "Developer")
    val emp3 = emp("ragnar", 100.0, "Tester")

    val (name, salary, role) = getEmp("Aaryan", 10.0, "Trainer");
    println("$name, $salary, $role")

=======
data class emp(val name: String, val salary: Double, val role:String)

fun getEmp(name: String, salary: Double, role: String): emp {
    return emp(name, salary, role)
}

fun main() {
    val emp1 = emp("mohanraj", 10.0, "Trainer")
    val emp2 = emp("sathish", 11.0, "Developer")
    val emp3 = emp("ragnar", 100.0, "Tester")

    val (name, salary, role) = getEmp("Aaryan", 10.0, "Trainer");
    println("$name, $salary, $role")

>>>>>>> origin/kotlin
}