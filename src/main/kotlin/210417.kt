fun main() {
    class CountingList<T> : ArrayList<T>() {
        var count = 0
        override fun add(element: T): Boolean {
            count++
            return super.add(element)
        }

        override fun addAll(elements: Collection<T>): Boolean {
            count += elements.size
            return super.addAll(elements)
        }
    }

    class CountingSet<T> : HashSet<T>() {
        var count = 0
        override fun add(element: T): Boolean {
            count++
            return super.add(element)
        }

        override fun addAll(elements: Collection<T>): Boolean {
            count += elements.size
            return super.addAll(elements)
        }
    }

    val countingList = CountingList<Int>()
    countingList.addAll(listOf(1, 2, 3))
    val countingSet = CountingSet<Int>()
    countingSet.addAll(listOf(1, 2, 3))
    println(countingList.count)
    println(countingSet.count)
}

private class CountingSet2(val innerSet: HashSet<Int> = HashSet()) {
    var count = 0
    fun add(e: Int): Boolean {
        count++
        return innerSet.add(e)
    }

    fun addAll(es: Collection<Int>): Boolean {
        count += es.size
        return innerSet.addAll(es)
    }

    fun contains(e: Int) {
        innerSet.contains(e)
    }
    // ...
}

private data class ListedSet3<T>(val innerSet: HashSet<T> = HashSet()) :
    Any(), MutableCollection<T> by innerSet, Cloneable by innerSet {
    var count = 0
    override fun add(element: T): Boolean {
        count++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        count += elements.size
        return innerSet.addAll(elements)
    }
}