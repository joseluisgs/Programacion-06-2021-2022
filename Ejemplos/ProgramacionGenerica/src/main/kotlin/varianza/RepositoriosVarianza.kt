package varianza

// No hay mas remedio que sea invariante porque
// T aparece como parametro de tipo en la firma (productor, va a ser intoroducido desde fuera)
// y como tipo de retorno (va a ser consumido desde fuera)
interface CrudRepositoryInvariante<T, ID> {
    fun findAll(): Array<T>
    fun findById(id: ID): T?
    fun save(entity: T): T
    fun update(id: ID, entity: T): T?
    fun deleteById(id: ID): T?
}

interface CrudRepositoryCovariante<out T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?

    // fun save(entity: T): T
    // fun update(id: ID, entity: T): T?
    fun deleteById(id: ID): T?
}

interface CrudRepositoryContravariante<in T, ID> {
    // fun findAll(): List<T>
    // fun findById(id: ID): T?
    //fun save(entity: T): T
    //fun update(id: ID, entity: T): T?
    // fun deleteById(id: ID): T?
    fun deleteById(id: ID, entity: T): Boolean
    fun save(entity: T): Boolean
    fun update(id: ID, entity: T): Boolean
    fun findById(id: ID): Boolean
}