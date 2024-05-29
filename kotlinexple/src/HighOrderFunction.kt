var lambda = {a: Int, b: Int -> a + b }

fun higherfunc(e: (Int, Int) -> Int) {
    var result = e(2, 4)
    println("Result: $result")
}

//if a function is passed into another function is called high order function..
fun main() {
    higherfunc(lambda)
}