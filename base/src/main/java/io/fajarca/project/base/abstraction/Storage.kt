package io.fajarca.project.base.abstraction

interface Storage {
     fun getString(key : String) : String
     fun setString(key : String, value : String)
}