import java.util.Scanner
data class Book(
    val title: String,
    val author: String,
    val publicationDate: String,
    val genre: String
)


fun main() {
    val scanner = Scanner(System.`in`)
    val books = mutableListOf<Book>()

    while (true) {
        println("Enter book title (or type 'exit' to quit):")
        val title = scanner.nextLine()
        if (title.equals("exit", ignoreCase = true)) break

        println("Enter book author:")
        val author = scanner.nextLine()

        println("Enter publication date (YYYY-MM-DD):")
        val publicationDate = scanner.nextLine()

        println("Enter book genre:")
        val genre = scanner.nextLine()

        val newBook = Book(title, author, publicationDate, genre)
        books.add(newBook)

        println("New Book Added: $newBook")


        if (genre.equals("Fiction", ignoreCase = true)) {
            println("This is a fiction book.")
        } else {
            println("This is a non-fiction book.")
        }


        when {
            genre.contains("Science", ignoreCase = true) -> println("This is a science book.")
            genre.contains("History", ignoreCase = true) -> println("This is a history book.")
            genre.contains("Fantasy", ignoreCase = true) -> println("This is a fantasy book.")
            else -> println("This is a general book.")
        }

        println("\nCurrent Book Collection:")
        books.forEach { println(it) }
    }
}
