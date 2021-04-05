import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

// 生产者消费者模型
fun main() {
//    byQueue()
    HandleClass.byThread()
}

private fun byQueue() {
    val queue = LinkedBlockingDeque<Int>(5)
    Executors.newCachedThreadPool().run {
        execute {
            while (true) {
                queue.take()
                println("take item..." + queue.size)
                Thread.sleep((200..400).random().toLong())
            }
        }
        execute {
            while (true) {
                queue.put(1)
                println("put item..." + queue.size)
                Thread.sleep(260)
            }
        }
    }
}

private object HandleClass {
    const val LIMIT = 9
    @Volatile var a = 0
    private fun addItem() {
        synchronized(this) {
            if (a >= LIMIT) return
            println("put item...${++a}")
        }
        Thread.sleep(266)
    }

    private fun getItem() {
        synchronized(this) {
            if (a <= 0) return
            println("take item...${--a}")
        }
        Thread.sleep((200..400).random().toLong())
    }

    fun byThread() {
        thread {
            while (true) {
                addItem()
            }
        }
        thread {
            while (true) {
                getItem()
            }
        }
    }
}
