package com.ibtikar.mvvm_starter_koin_coroutines.data.network

import com.ibtikar.mvvm_starter_koin_coroutines.data.network.APIS.CONSTANTS.TOP_HEADLINES

object APIS {

    object CONSTANTS {
        const val TOP_HEADLINES = "top-headlines"
    }

    object URL {

        object NEWS {
            const val URL_GET_NEWS = "$TOP_HEADLINES/"
        }

    }
}
