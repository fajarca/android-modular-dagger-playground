package io.fajarca.project.base.abstraction

abstract class Mapper<I, O> {
    abstract fun map(input : I) : O
}