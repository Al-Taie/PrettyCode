package com.altaie.prettycode.core.mapper.base

/**
 * This interface extends the `Mapper` interface and provides a default implementation for mapping a list of objects.
 *
 * @param <I> The type of the elements in the input list.
 * @param <O> The type of the elements in the output list.
 *
 * `MapperList` inherits the ability to map single objects from the `Mapper` interface. Additionally, it provides a
 * default implementation for `mapList` that iterates over a list of type `I` and applies the single object mapping
 * defined in `map` to each element. This results in a new list of type `O`.
 */
interface MapperList<in I, out O> : Mapper<I, O> {

    /**
     * This method provides a default implementation to map a list of objects of type `I` to a list of objects of type `O`.
     *
     * @param from The input list to be mapped.
     * @return A new list containing the mapped elements of type `O`.
     */
    fun mapList(from: List<I>): List<O> = from.map { map(it) }
}
