package io.fajarca.project.apiclient.exception

data class ServerErrorException(val code : Int) : Exception()