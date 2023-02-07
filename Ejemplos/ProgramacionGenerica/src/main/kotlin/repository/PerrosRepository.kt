package repository

import java.util.*

data class Perro(val id: UUID, val nombre: String)

class PerrosRepository: CrudRepository<Perro?, UUID> {
    val perros: Array<Perro?> = arrayOfNulls(50)
    override fun findAll(): Array<Perro?> {
        return perros
    }

    override fun findById(id: UUID): Perro? {
        for (perro in perros) {
            if (perro?.id == id) {
                return perro
            }
        }
        return null
    }

    override fun save(entity: Perro?): Perro? {
        for (i in perros.indices) {
            if (perros[i] == null) {
                perros[i] = entity
                return entity
            }
        }
        return entity
    }

    override fun update(id: UUID, entity: Perro?): Perro? {
        for (i in perros.indices) {
            if (perros[i]?.id == id) {
                perros[i] = entity
                return entity
            }
        }
        return null
    }

    override fun deleteById(id: UUID): Perro? {
        for (i in perros.indices) {
            if (perros[i]?.id == id) {
                val p = perros[i]
                perros[i] = null
                return p!!
            }
        }
        return null
    }
}