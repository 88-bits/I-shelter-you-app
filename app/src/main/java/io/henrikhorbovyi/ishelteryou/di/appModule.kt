package io.henrikhorbovyi.ishelteryou.di

import io.henrikhorbovyi.ishelteryou.viewmodel.HostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HostsViewModel(get()) }
}