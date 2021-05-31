package io.fajarca.project.base.abstraction

import android.app.Application
import io.fajarca.project.base.di.component.BaseComponent

abstract class  BaseApplication: Application() {
    abstract fun getBaseComponent(): BaseComponent
}