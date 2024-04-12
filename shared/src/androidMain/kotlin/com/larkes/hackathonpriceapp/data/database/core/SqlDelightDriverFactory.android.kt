package com.larkes.hackathonpriceapp.data.database.core

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.larkes.hackathonpriceapp.domain.platform.PlatformConfiguration

actual class SqlDelightDriverFactory actual constructor(private val platformConfiguration: PlatformConfiguration) {
    actual fun makeDriver(schema: SqlSchema<QueryResult.AsyncValue<Unit>>, name:String): SqlDriver {
        return AndroidSqliteDriver(schema.synchronous(), platformConfiguration.androidContext, name)
    }

}