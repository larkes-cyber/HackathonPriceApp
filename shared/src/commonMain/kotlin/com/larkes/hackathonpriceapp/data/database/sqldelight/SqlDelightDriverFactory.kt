package com.larkes.hackathonpriceapp.data.database.sqldelight

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.larkes.hackathonpriceapp.domain.platform.PlatformConfiguration

expect class SqlDelightDriverFactory constructor(platformConfiguration: PlatformConfiguration){
    fun makeDriver(schema: SqlSchema<QueryResult.AsyncValue<Unit>>, name:String): SqlDriver
}