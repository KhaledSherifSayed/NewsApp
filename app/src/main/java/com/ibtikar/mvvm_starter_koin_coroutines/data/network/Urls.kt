package com.ibtikar.mvvm_starter_koin_coroutines.data.network

import com.ibtikar.mvvm_starter_koin_coroutines.data.network.APIS.CONSTANTS.API_KEY
import com.ibtikar.mvvm_starter_koin_coroutines.data.network.APIS.CONSTANTS.PATH_VIEWED
import com.ibtikar.mvvm_starter_koin_coroutines.data.network.APIS.CONSTANTS.VERSION

object APIS {

    object CONSTANTS {
        const val VERSION = "v2"
        const val PATH_VIEWED = "viewed"
        const val API_KEY = "?api-key="

    }

    object URL {

        object Articles {
            const val URL_GET_ARTICLES = "$VERSION/$PATH_VIEWED/{period}.json$API_KEY"
        }

    }
}
