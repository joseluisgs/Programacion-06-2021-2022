package varianza

/*
Varianza es la capacidad de un tipo de ser usado en un contexto donde se espera otro tipo.
Covarianza es la capacidad de un tipo de ser usado en un contexto donde se espera un tipo más general.
Contravarianza es la capacidad de un tipo de ser usado en un contexto donde se espera un tipo más específico.
Invarianza: un tipo no puede ser usado en un contexto donde se espera otro tipo.
Colecciones Mutables es invariante con respecto a su tipo genérico
(no podemos ni consumir ni producir/añadir valores con un tipo más genérico)
Colecciones Inmutables es covariante con respecto a su tipo genérico
(podemos consumir sus valores con un tipo más genérico)
¿Qué significa consumir (covarianza) y qué producir (contravarianza)?
Consumir implica tener funciones que devuelven un valor del tipo genérico
Producir implica tener funciones que reciben por argumento un objeto del tipo genérico
 */
fun main() {
    var numero: Number = 1
    var entero: Int = 1
    var doble: Double = 1.0

    // Por ser subtipos de Number, se puede asignar un Int a un Number
    numero = entero
    // entero = numero as Int // Se debe hacer un casting explícito
    numero = doble

    var numeros: Array<Number> = arrayOf(numero, entero, doble)
    var enteros: Array<Int> = arrayOf(entero)
    var dobles: Array<Double> = arrayOf(doble)

    // Por ser subtipos de Number, se puede asignar un Array<Int> a un Array<Number>???
    // NO!!, porque al ser una colección de lectura y escritura es Invariante
    // numeros = enteros ??? // Error de compilación

    var numeroList = listOf(numero, entero, doble)
    var enteroList = listOf(entero)
    var dobleList = listOf(doble)

    // Por ser subtipos de Number, se puede asignar un List<Int> a un List<Number>???
    numeroList = enteroList

    // Any <- Int
    // MutableList<Any> <- MutableList<Int>?????  NO
    // Kotlin no deja hacer esto,
    // es decir, no es seguro
    //val list: MutableList<Int> = mutableListOf(1, 2, 3)
    //val list2: MutableList<Any> = list
    //list2.add("Hello")
    // val i = list[3]
    // Pasa esto porque el genérico esta para consumir y para producir
    // Consumir: get o []
    // Producir: add o set

    // Probemos con esto ahora
    val list: List<Int> = listOf(1, 2, 3)
    val list2: List<Any> = list
    println(list2)
    // Ahora si deja, ¿por qué?
    // Porque List es inmutable, no se puede modificar
    // y por lo tanto no hay problema
    // No producimos!!! No hay add, set, etc

    // Si simplemente vamos a consumir datos (somos consumidores, o consumers),
    // entonces podemos relajar esa condición:
    val list3: List<Int> = listOf(1, 2, 3)
    val list4: List<Number> = list
    val obj: Number = list3[0] // Consumimos, porque le pedimos que nos devuleva un dato
    println(obj.toString())
    // si solo consumimos tenemos que
    // List<Any> <- List<Int>  OK (subtipo) Covarianza
}
