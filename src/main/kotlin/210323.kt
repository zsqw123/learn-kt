fun main() {
    val now = System.currentTimeMillis()
    var hour = Hour(1)
    repeat(1_000_000_000) {
        hour = Hour(it)
    }
    println(hour.toMinutes().v)
    println(System.currentTimeMillis() - now)
}
inline class Hour(private val v: Int) {
    fun toMinutes() = Minute(v * 60)
}

inline class Minute(val v: Int)
