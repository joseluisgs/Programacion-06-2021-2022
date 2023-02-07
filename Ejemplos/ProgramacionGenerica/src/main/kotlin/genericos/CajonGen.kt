package genericos

// Ejemplo de una clase genérica
class CajonGen<T>(var contenido: T) {
    fun meter(objeto: T) {
        contenido = objeto
    }

    fun sacar(): T {
        return contenido
    }

    fun mostrarContenido() {
        println(contenido)
    }
}

// ejemplo de una función genérica
fun <T> mostrarContenido(cajon: CajonGen<T>): Unit {
    println(cajon.contenido)
}

fun <T> obtenerContenido(cajon: CajonGen<T>): T {
    return cajon.contenido
}

fun main(args: Array<String>) {
    val cajonGen = CajonGen<Int>(1)
    cajonGen.mostrarContenido()

    val cajonGen2 = CajonGen<String>("Hola")

    mostrarContenido(cajonGen)

    val arrayEnteros: Array<Int> = arrayOf(1, 2, 3)
    val par: Pair<Int, String> = Pair(1, "Hola")

}