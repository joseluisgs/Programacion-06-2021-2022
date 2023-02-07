package varianza

// Invariante: No sabemos si vamos a consumir o producir
class EncrypterInvariante<T>(val item: T)

// Covariante: Nosotros aseguramos que solo vamos a consumir con el generico
//  usamos out, es decir, solo vamos a consumir, no producir
// el generico esta en la devolución de las funciones
class EncrypterCovariante<out T>(val item: T) {
    fun metodo(): T {
        return item
    }
}

// Contravariante: Nosotros aseguramos que solo vamos a producir con el generico
//  usamos int, es decir, solo vamos a producir (usarlo como parametro), no consumir (devolver)
// el generico esta en los parámetros de las funciones
class EncrypterContravariante<in T>() {
    fun metodo(item: T): String {
        return "Hola"
    }
}


fun main(args: Array<String>) {
    // sigamos
    // Tenemos esto
    // Number <- Int
    // EncrypterInvariante<Number> <- EncrypterInvariante<Int>  NO // Invaruiante, porque no aseguramos si consumimos o producimos
    // Es decir, no sabemos si va a estar en lso retunoes de las funciones o en los parametros de los métodos
    // O en ambos lados
    val e: EncrypterInvariante<Int> = EncrypterInvariante(10)
    //val e2: EncrypterInvariante<Number> = e // Por defecto es invariante

    // si sabemos que solo vamos a consumir, podemos permitirlo
    // el generico solo esta en los devolución de las funciones
    // EncrypterCovariante<Number> <- EncrypterCovariante<Int>  OK // Covariante, porque solo consumimos
    val e3: EncrypterCovariante<Int> = EncrypterCovariante(10)
    val e4: EncrypterCovariante<Number> = e3

    // si sabemos que solo vamos a producir, podemos permitirlo
    // Es decir el genérico solo esta en las parametros de las funciones
    // EncrypterContravariante<Int> <- EncrypterContravariante<Out>  OK // Contravariante, porque solo producimos
    val e5: EncrypterContravariante<Number> = EncrypterContravariante()
    val e6: EncrypterContravariante<Int> = e5

}

interface ComparableInvariante<T> {
    fun compare(other: T): Int
}

/**
 * Esto va a dar error, ya que no hemos definido el tipo de varianza, y por tanto el tipo aquí es invariante: si no indicamos la varianza,
 * no podemos convertir un Comparable<Number en un Comparable<Float>.
 */
fun testInvariante(comparable: ComparableInvariante<Number>) {
    //val comp: ComparableInvariante<Float> = comparable
}

interface ComparableContravariante<in T> {
    fun compare(other: T): Int
}

/**
 * Tiene sentido que puedas hacerlo, ya que si el Comparable permite comparar Numbers,
 * comparar con Floats es un subconjunto de lo que este tipo te deja hacer.
 */
fun testCotravariante(comparable: ComparableContravariante<Number>) {
    val comp: ComparableContravariante<Float> = comparable
}