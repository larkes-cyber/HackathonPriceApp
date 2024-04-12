import com.larkes.hackathonpriceapp.di.InjectUseCase
import com.larkes.hackathonpriceapp.di.PlatformSDK
import com.larkes.hackathonpriceapp.di.TestPlatformSDK
import com.larkes.hackathonpriceapp.domain.model.AuthData
import com.larkes.hackathonpriceapp.domain.platform.PlatformConfiguration
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AuthTest {

    @Before
    fun init(){
        TestPlatformSDK.init()
    }

    @Test
    fun `Should login user`(){
        runBlocking {
            val authData = AuthData(
                number = null,
                email = "play@gmail.com",
                password = "23456"
            )
            InjectUseCase.useLoginUser.execute(authData)
        }
    }
}