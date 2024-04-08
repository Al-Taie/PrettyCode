package com.altaie.prettycode.core.mapper.base

/**
 * This interface defines a contract for a mapper function that transforms objects of one type to another.
 *
 * @param <I> The type of the input object.
 * @param <O> The type of the output object.
 *
 * A mapper takes an object of type `I` as input and returns a new object of type `O`. The specific mapping logic
 * is implemented by concrete classes that extend this interface.
 */
interface Mapper<in I, out O> {

    /**
     * Maps an object of type `I` to an object of type `O`.
     *
     * @param from The input object to be mapped.
     * @return The mapped object of type `O`.
     */
    fun map(from: I): O
}
