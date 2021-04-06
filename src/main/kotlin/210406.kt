import java.io.*
import java.lang.StringBuilder
import java.net.Socket
import java.nio.ByteBuffer
import java.nio.charset.Charset

fun main() {
    val file = File("./ioOutputs/test")
    val newFile = File("./ioOutputs/newTest")
    if (!file.exists()) file.parentFile.mkdirs()
    fun ioOutput() = FileOutputStream(file).apply {
        write('a'.toInt())
        write('b'.toInt())
    }.close()

    fun ioInput() = FileInputStream(file).apply {
        while (true) {
            val res = read()
            if (res != -1) print(res.toChar())
            else break
        }
    }.close()

    fun ioBufferInput() = FileInputStream(file).apply {
        println(BufferedReader(InputStreamReader(this, Charset.defaultCharset())).readLine())
    }.close()

    fun ioWriterOut() = FileOutputStream(file).apply {
        BufferedWriter(OutputStreamWriter(this, Charset.defaultCharset())).apply {
            write("草")
            flush()
        }.close()
        ioBufferInput()
    }.close()

    fun ioBufferOutput() = FileOutputStream(file).apply {
        100 repeat {
            BufferedOutputStream(this).apply {
                write('a'.toInt())
                flush()
            }
        }
        ioBufferInput()
    }.close()

    fun ioCopy() {
        val inputStream = BufferedInputStream(FileInputStream(file))
        val outputStream = BufferedOutputStream(newFile.outputStream())
        val buffer = ByteArray(16)
        while (true) {
            val readed = inputStream.read(buffer)
            if (readed == -1) break
            outputStream.write(buffer, 0, readed)
        }
        outputStream.close()
    }

    fun ioSocket() {
        val socket = Socket("www.baidu.com", 80) // 不知道为啥请求 baidu.com 巨慢
        val outputWriter = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
        val inputReader = BufferedReader(InputStreamReader(socket.getInputStream()))
        outputWriter.write(
            "GET / HTTP/1.1\n" +
                "Host: www.baidu.com\n\n"
        )
        outputWriter.flush()
        val output = StringBuilder()
        while (true) {
            val readed = inputReader.readLine() ?: break
            output.append(readed + "\n")
        }
        println(output)
    }

    fun ioNIO() {
        val raf = RandomAccessFile(file, "rw")
        val channel = raf.channel
        val byteBuffer = ByteBuffer.allocate(16)
        val sb = StringBuilder()
        while (true) {
            val readed = channel.read(byteBuffer)
            byteBuffer.flip()
            if (readed <= 0) break
            sb.append(Charset.defaultCharset().decode(byteBuffer), 0, readed)
            byteBuffer.clear()
        }
        print(sb.toString())
    }
    ioNIO()
}

infix fun Int.repeat(method: (Int) -> Unit) = repeat(this, method)
