import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.larkes.hackathonpriceapp.PriceAppDatabase
import com.larkes.hackathonpriceapp.UserEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TestRepository {

    private lateinit var database:PriceAppDatabase

    @Before
    fun init(){

        runBlocking {
            val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            PriceAppDatabase.Schema.create(driver).await()
            database = PriceAppDatabase(driver)
        }

    }

    @Test
    fun `Should insert user`(){
        runBlocking {
            database.userQueries.insertUser("1","name")

        }
    }

}