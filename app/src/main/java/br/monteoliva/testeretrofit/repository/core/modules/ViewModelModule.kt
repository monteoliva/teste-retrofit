package br.monteoliva.testeretrofit.repository.core.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

import br.monteoliva.testeretrofit.viewmodel.DetailViewModel
import br.monteoliva.testeretrofit.viewmodel.MainViewModel

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel() }
}
