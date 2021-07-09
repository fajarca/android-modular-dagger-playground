package io.fajarca.project.core.abstraction.mapper

abstract class Mapper<I, O> {
    abstract fun map(input : I) : O
}