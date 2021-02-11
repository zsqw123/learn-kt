fun main() {
    // kotlin 解构
    data class Person(var id: Int, var name: String, var sex: Boolean)

    val person = Person(0, "nmd", true)

    // 你可以同时声明三个变量 a,b,c 他们分别对应person的三个成员变量
    val (a, b, c) = person
    // 也可也声明其中两个
    val (id, name) = person
    // 你甚至可以跳过前两个, 只声明最后一个
    val (_, _, sex) = person
    listOf(a, b, c, id, name, sex).forEach { println(it) }

    listOf(person, person).forEach { (id, name) ->
        println("id: $id, name: $name")
    }

    // kotlin 中缀: infix
    infix fun Person.changeSex(newSex: Boolean) {
        this.sex = newSex
    }
    println(person.sex)
    person changeSex false
    println(person.sex)
}