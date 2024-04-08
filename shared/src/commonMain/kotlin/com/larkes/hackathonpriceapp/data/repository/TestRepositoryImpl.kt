package com.larkes.hackathonpriceapp.data.repository

import com.larkes.hackathonpriceapp.domain.repository.TestRepository

class TestRepositoryImpl:TestRepository {
    override fun test(): String {
        return "test"
    }
}