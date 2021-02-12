package com.pants.cleannotes.util

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProvider @Inject constructor() {
    fun getIO() = Dispatchers.IO
    fun getMain() = Dispatchers.Main
}