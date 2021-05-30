package io.fajarca.project.daggerplayground.abstraction

interface Storage {
    fun getString(key : String) : String
    fun setString(key : String, value : String)
}