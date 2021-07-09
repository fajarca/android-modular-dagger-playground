package io.fajarca.project.base.abstraction.mapper

abstract class Mapper<I, O> {
    abstract fun map(input : I) : O
}