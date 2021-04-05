fun main() {
    val now = System.currentTimeMillis()
    var hour = Hour(1)
    println(hour::class.java)
    repeat(600_000_000) {
        hour = Hour(it)
    }
    println(hour.toMinutes().v)
    println(System.currentTimeMillis() - now)
    hour.toMinutes().toSecond()
}

inline class Hour(val v: Int) {
    fun toMinutes() = Minute(v * 60)

}

class Minute(val v: Int) {
    fun toSecond() = v * 60
}
