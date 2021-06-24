package io.fajarca.project.apiclient.exception

data class ClientErrorException(val code : Int) : Exception()