package singenerico

class CajonAny(var contenido: Any) {
    fun meter(objeto: Any) {
        contenido = objeto
    }

    fun sacar(): Any {
        return contenido
    }

    fun mostrarContenido() {
        println(contenido)
    }
}

sealed class Item
class Libro(val titulo: String): Item()
class Disco(val titulo: String): Item()
class Boligrafo(val color: String): Item()

class CajonItem(var contenido: Item) {
    fun meter(objeto: Item) {
        contenido = objeto
    }

    fun sacar(): Item {
        return contenido
    }

    fun mostrarContenido() {
        println(contenido)
    }
}


fun main(args: Array<String>) {
    val cajon = CajonAny(1)
    cajon.mostrarContenido()
    cajon.meter("Hola")
    cajon.mostrarContenido()
    val objeto = cajon.sacar()

    val cajon2 = CajonItem(Libro("El Quijote"))
    cajon2.mostrarContenido()
    val objeto2 = cajon2.sacar()
    when (objeto2) {
        is Libro -> println("Es un libro")
        is Disco -> println("Es un disco")
        is Boligrafo -> println("Es un boligrafo")
    }
}