import java.time.LocalDate
import java.util.*
import kotlin.collections.LinkedHashMap

object 前

object 后

object 告诉我

infix fun Int.天(fromNow: 后): LocalDate = baseDate().plusDays(toLong())

infix fun Int.天(ago: 前): LocalDate = baseDate().minusDays(toLong())

private fun baseDate() = LocalDate.now()

infix fun LocalDate.是啥时候(tellme: 告诉我) = println(this)

fun main() {
    1 天 前 是啥时候 告诉我
    1 天 后 是啥时候 告诉我
    val a by lazy {  }
    class LruMap(private val maxSize: Int) : LinkedHashMap<Int, Int>((Math.ceil(maxSize / 0.75) + 1).toInt(), 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
            return size > maxSize
        }
    }
}