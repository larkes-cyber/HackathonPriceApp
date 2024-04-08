package com.larkes.hackathonpriceapp.di

import org.koin.core.Koin

object Inject {
    private var _di: Koin? = null

    val di: Koin? = _di

    fun createDependencies(tree: Koin) {
        _di = tree
    }

}