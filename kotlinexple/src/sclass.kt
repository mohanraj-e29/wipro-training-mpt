/**/
sealed class SClass {
    fun show() {
        println("sealed class we can't create object and inheritance")
    }
}

fun main() {
    val example = SClassExample()
    example.show()
}

class SClassExample : SClass()
