package io.fajarca.project.user.data.source

import io.fajarca.project.base.abstraction.storage.Storage
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val storage: Storage) {

    fun setPin(pin: String) {
        storage.setString("pin", pin)
    }

    fun getPin(): String {
        return storage.getString("pin")
    }
}