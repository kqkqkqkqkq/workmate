package k.di.module

import k.ui_logic.DetailViewModel
import k.ui_logic.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        println("[Main VM created]")
        MainViewModel(
            repository = get()
        )
    }

    viewModel<DetailViewModel> {
        DetailViewModel()
    }
}

val appModules = listOf(
    appModule,
    apiModule,
    databaseModule,
    repositoryModule,
)