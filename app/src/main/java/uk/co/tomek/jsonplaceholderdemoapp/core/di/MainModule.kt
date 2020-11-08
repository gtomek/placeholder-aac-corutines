package uk.co.tomek.jsonplaceholderdemoapp.core.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.data.MainRepository
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.data.Repostitory
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.data.network.NetworkService
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.domain.Interactor
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.domain.MainInteractor
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.presentation.viewmodel.MainViewModel
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.presentation.viewstate.MainViewState

/**
 * KOIN modules declarations.
 */
val applicationModule : Module = module {
    viewModel { MainViewModel(get()) }
    factory<Repostitory> {
        MainRepository(
            get()
        )
    }
    factory<Interactor<MainViewState>> {
        MainInteractor(
            get()
        )
    }
}

val networkModule : Module = module {
    single { createOkHttpClient() }
    single {
        creteNetService<NetworkService>(
            get(),
            SERVER_URL
        )
    }
}

fun createOkHttpClient() : OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .addInterceptor(logInterceptor)
        .build()
}

inline fun <reified T> creteNetService(httpClient: OkHttpClient, baseUrl: String) : T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    return retrofit.create(T::class.java)
}

const val SERVER_URL = "https://jsonplaceholder.typicode.com"
