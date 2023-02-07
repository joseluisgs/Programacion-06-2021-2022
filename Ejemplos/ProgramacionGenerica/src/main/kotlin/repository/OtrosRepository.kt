package repository

data class Otro(val id: Int, val nombre: String)

class PersonRepository : CrudRepository<Otro?, Int> {
    private val personas: Array<Otro?> = arrayOfNulls(50)

    override fun findAll(): Array<Otro?> {
        return personas
    }

    override fun findById(id: Int): Otro? {
        return personas[id]
    }

    override fun save(entity: Otro?): Otro {
        if (entity != null) {
            personas[entity.id] = entity
        }
        return entity!!
    }

    override fun update(id: Int, entity: Otro?): Otro {
        personas[id] = entity
        return entity!!
    }

    override fun deleteById(id: Int): Otro {
        val p = personas[id]
        personas[id] = null
        return p!!
    }
}