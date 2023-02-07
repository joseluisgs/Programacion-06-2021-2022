package genericos

data class ParGen<T, U>(var primero: T, var segundo: U){
    fun mostrarContenido() {
        println("Primero: $primero, Segundo: $segundo")
    }
}

data class TripleGen<T, U, V>(var primero: T, var segundo: U, var tercero: V){
    fun mostrarContenido() {
        println("Primero: $primero, Segundo: $segundo, Tercero: $tercero")
    }
}

private class Persona(a: Int, b: String)

fun main(args: Array<String>) {
    val gen = TripleGen<String, String, String>("Hola", "Adios", "Buenos dias")
    gen.mostrarContenido()

    val gen2 = TripleGen<Int, String, Boolean>(1, "Adios", true)

    val gen3 = TripleGen(1, "Adios", true)

    val gen4: ParGen<String, Int> = ParGen("Hola", 1)

    val gen5: ParGen<Int, String> = ParGen(1, "Hola")

    val gen6: ParGen<Int, Persona> = ParGen(1, Persona(1, "Hola"))
}

