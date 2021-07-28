package com.ibtikar.mvvm_starter_koin_coroutines.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*


inline fun <reified T> getKoinInstance(): Lazy<T> {
    return lazy {
        return@lazy object : KoinComponent {
            val value: T by inject()
        }.value
    }
}

