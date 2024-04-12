import com.larkes.hackathonpriceapp.data.database.core.databaseModule
import com.larkes.hackathonpriceapp.data.remote.core.remoteModule
import com.larkes.hackathonpriceapp.data.repository.repositoryModule
import com.larkes.hackathonpriceapp.data.settings.core.settingModule
import com.larkes.hackathonpriceapp.data.settings.source.AuthSettingsDataSource
import com.larkes.hackathonpriceapp.domain.core.domainModule
import com.larkes.hackathonpriceapp.domain.platform.PlatformConfiguration
import org.koin.core.context.startKoin
import org.koin.dsl.module

object TestPlatformSDK {
    fun init() {
        val diTree = startKoin {
            modules(module {
                single {
                }
                single {
                    AuthSettingsDataSource(get())
                }
            })
            modules(databaseModule)
            modules(remoteModule)
            modules(repositoryModule)
            modules(domainModule)
        }
    }


}