package com.altaie.prettycode.core.mapper.base

interface MapperList<in I, out O> : Mapper<I, O> {
    fun mapList(from: List<I>): List<O>
}
