package uk.co.tomek.jsonplaceholderdemoapp.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewmodel.MainViewModel

/**
 * TODO: Add class description.
 */
val applicationModule : Module = module {
    viewModel { MainViewModel() }
//    single { NetworkService() }
}