/*
Kotlin introduces the concept of Extension Functions – which are a handy way of extending existing classes with new functionality without using inheritance or any
 forms of the Decorator pattern – after defining an extension. we can essentially use it – as it was part of the original API.
 */

class shapes {

    fun area(): Double {
        return 0.0
    }
}

fun main() {
    fun shapes.perimeter(): Double {
        return 0.0
    }

    var s = shapes()

    s.perimeter()
}