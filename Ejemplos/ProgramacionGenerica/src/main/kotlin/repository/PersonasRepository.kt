package repository

import java.util.*

open class Persona(val id: UUID, val nombre: String, val edad: Int) {
    override fun toString(): String {
        return "Persona(id=$id, nombre='$nombre', edad=$edad)"
    }

    fun esMayorDeEdad(): Boolean {
        return edad >= 18
    }
}

class Alumno(id: UUID, nombre: String, edad: Int, val nota: Double) : Persona(id, nombre, edad) {
    override fun toString(): String {
        return "Alumno(id=$id, nombre='$nombre', edad=$edad, nota=$nota)"
    }

    fun esAprobado(): Boolean {
        return nota >= 5
    }
}

class Profesor(id: UUID, nombre: String, edad: Int, val modulo: String) : Persona(id, nombre, edad) {
    override fun toString(): String {
        return "Profesor(id=$id, nombre='$nombre', edad=$edad, modulo='$modulo')"
    }

    fun esJefeDeModulo(): Boolean {
        return modulo == "Programación"
    }
}

// typeAlias PersonasRepository = CrudRepository<Persona, UUID> --> no se puede puede
interface PersonasRepository : CrudRepository<Persona, UUID> {
    fun findByNombre(nombre: String): Persona?
    fun getAllSortedByEdad(): Array<Persona?>
}

class PersonasRepositoryImpl : PersonasRepository {
    private val personas: Array<Persona?> = arrayOfNulls(30)
    override fun findByNombre(nombre: String): Persona? {
        for (persona in personas) {
            if (persona?.nombre?.lowercase()?.contains(nombre, true) == true) {
                return persona
            }
        }
        return null
    }

    override fun getAllSortedByEdad(): Array<Persona?> {
        // burbuja
        for (i in personas.indices) {
            for (j in 0 until personas.size - 1) {
                if ((personas[j]?.edad ?: 0) > (personas[j + 1]?.edad ?: 0)) {
                    val aux = personas[j]
                    personas[j] = personas[j + 1]
                    personas[j + 1] = aux
                }
            }
        }
        return personas
    }

    override fun findAll(): Array<Persona> {
        // cuantos no son null?
        var contador = 0
        for (persona in personas) {
            if (persona != null) {
                contador++
            }
        }
        // crear array de tamaño contador
        val resultado: Array<Persona?> = arrayOfNulls(contador)
        // copiar los no null
        var i = 0
        for (persona in personas) {
            if (persona != null) {
                resultado[i] = persona
                i++
            }
        }
        return resultado as Array<Persona> // Deberás pensar  porque puedo hacer esto!!!
    }

    override fun findById(id: UUID): Persona? {
        for (persona in personas) {
            if (persona?.id == id) {
                return persona
            }
        }
        return null
    }

    private fun getPosicion(id: UUID): Int {
        for (i in personas.indices) {
            if (personas[i]?.id == id) {
                return i
            }
        }
        return -1
    }

    override fun save(entity: Persona): Persona {
        // Existe antes de guardar?
        val posicion = getPosicion(entity.id)
        if (posicion == -1) {
            // No existe, guardamos
            for (i in personas.indices) {
                if (personas[i] == null) {
                    personas[i] = entity
                    return entity
                }
            }
        } else {
            // Existe, actualizamos
            personas[posicion] = entity
            return entity
        }
        throw RuntimeException("No hay espacio para guardar")
    }

    override fun update(id: UUID, entity: Persona): Persona? {
        val posicion = getPosicion(id)
        if (posicion != -1) {
            personas[posicion] = entity
            return entity
        }
        return null
    }

    override fun deleteById(id: UUID): Persona? {
        val posicion = getPosicion(id)
        if (posicion != -1) {
            val p = personas[posicion]
            personas[posicion] = null
            return p!!
        }
        return null
    }
}

fun main() {
    val p = Persona(UUID.randomUUID(), "Pepe", 20)
    val a = Alumno(UUID.randomUUID(), "Juan", 18, 7.0)

    presentarPersona(p)
    presentarPersona(a) // Si porque es un subtipo


    val arrayPersonas = arrayOfNulls<Persona>(10)
    val arrayAlumno = arrayOfNulls<Alumno>(10)

    presentarPersonasArray(arrayPersonas)
    // presentarPersonasArray(arrayAlumno) // No compila, no es un subtipo --> Varianza

    val listPersonas = List<Persona?>(10) { null }
    val listAlumnos = List<Alumno?>(10) { null }

    presentarPersonasList(listPersonas)
    presentarPersonasList(listAlumnos)  // Si porque es un subtipo --> Covarianza
}

fun presentarPersona(persona: Persona) {
    println(persona.esMayorDeEdad())
}

fun presentarPersonasArray(personas: Array<Persona?>) {
    for (persona in personas) {
        println(persona)
    }
}

fun presentarPersonasList(personas: List<Persona?>) {
    for (persona in personas) {
        println(persona)
    }
}


