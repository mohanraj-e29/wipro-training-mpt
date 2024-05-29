enum class days(var color: String) {
    Sunday("yellow"),
    Monday("green"),
    Tuesday("black"),
    Wednesday("red"),
    Thursday("blue"),
    Friday("white"),
    Saturday("pink")
}

fun main() {
    val color = days.Sunday.color;
    println(color)
    val color1 = days.Monday.color;
    println(color1)
}