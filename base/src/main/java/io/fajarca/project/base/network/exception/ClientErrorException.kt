package io.fajarca.project.base.network.exception

data class ClientErrorException(val code : Int, val errorMessage : String) : Exception()