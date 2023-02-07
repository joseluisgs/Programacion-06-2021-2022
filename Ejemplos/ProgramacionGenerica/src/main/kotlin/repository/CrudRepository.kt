package repository

interface CrudRepository<T, ID> {
    fun findAll(): Array<T>
    fun findById(id: ID): T?
    fun save(entity: T): T
    fun update(id: ID, entity: T): T?
    fun deleteById(id: ID): T?

}