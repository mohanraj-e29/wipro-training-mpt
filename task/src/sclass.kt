/*
* Kotlin provides an important new type of class that is not present in Java. These are known as sealed classes. As the word sealed suggests, sealed classes conform to
*  restricted or bounded class hierarchies. A sealed class defines a set of subclasses within it. It is used when it is known in advance that a type will conform to one of
* the subclass types. Sealed classes ensure type safety by restricting the types to be matched at compile-time rather than at runtime.*/

sealed class sclass {
    fun main() {
        println("ok")
    }

}