package io.fajarca.project.core.abstraction.storage

interface Storage {
     fun getString(key : String) : String
     fun setString(key : String, value : String)
}