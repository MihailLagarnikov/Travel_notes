package com.twosmalpixels.travel_notes

import com.twosmalpixels.travel_notes.core.offline_mode.IOflineModeUseCase
import com.twosmalpixels.travel_notes.core.offline_mode.OflineModeUseCase
import com.twosmalpixels.travel_notes.core.repositoriy.CloudFirestoreBase
import com.twosmalpixels.travel_notes.core.repositoriy.FairbaseStorageBase
import com.twosmalpixels.travel_notes.core.repositoriy.ICloudFirestoreBase
import com.twosmalpixels.travel_notes.core.repositoriy.IFairbaseStorageBase
import com.twosmalpixels.travel_notes.core.repositoriy.LocalRepositoriy.ILocalRepositoriy
import com.twosmalpixels.travel_notes.core.repositoriy.LocalRepositoriy.LocalRepositoriy
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.ISharedPrefHelper
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.SharedPrefHelper
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceRepository
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceUseCase
import com.twosmalpixels.travel_notes.ui.add_expence.IExpenceRepository
import com.twosmalpixels.travel_notes.ui.add_expence.IExpenceUseCase
import com.twosmalpixels.travel_notes.ui.all_currency.AllCurrencyUseCase
import com.twosmalpixels.travel_notes.ui.all_currency.IAllCurrencyUseCase
import com.twosmalpixels.travel_notes.ui.expense_all.ExpenceAllUseCase
import com.twosmalpixels.travel_notes.ui.expense_all.IExpenceAllUseCase
import com.twosmalpixels.travel_notes.ui.auth.AuthInterface
import com.twosmalpixels.travel_notes.ui.auth.AuthProvider
import com.twosmalpixels.travel_notes.ui.auth.AuthUseCase
import com.twosmalpixels.travel_notes.ui.auth.IAuthUseCase
import com.twosmalpixels.travel_notes.ui.choose_travel_skin.ChooseSkinUseCase
import com.twosmalpixels.travel_notes.ui.choose_travel_skin.IChooseSkinUseCase
import com.twosmalpixels.travel_notes.ui.currency.CurrencyUseCase
import com.twosmalpixels.travel_notes.ui.currency.ICurrencyUseCase
import com.twosmalpixels.travel_notes.ui.expense_all.ExpenceAllRepositoriy
import com.twosmalpixels.travel_notes.ui.expense_all.IExpenceAllRepositoriy
import com.twosmalpixels.travel_notes.ui.new_travel.INewTravelsRepositoriy
import com.twosmalpixels.travel_notes.ui.new_travel.INewTravelsUseCase
import com.twosmalpixels.travel_notes.ui.new_travel.NewTravelsRepositoiy
import com.twosmalpixels.travel_notes.ui.new_travel.NewTravelsUseCase
import com.twosmalpixels.travel_notes.ui.you_travels.IYouTravelsRepositoriy
import com.twosmalpixels.travel_notes.ui.you_travels.IYouTravelsUseCase
import com.twosmalpixels.travel_notes.ui.you_travels.YouTravelsRepositoriy
import com.twosmalpixels.travel_notes.ui.you_travels.YouTravelsUseCase
import org.koin.dsl.module.module

val appModule = module {
    single { this }

    single { AuthProvider() as AuthInterface }
    single { AuthUseCase(get()) as IAuthUseCase }
    single { CloudFirestoreBase(get(), get()) as ICloudFirestoreBase }
    single { YouTravelsRepositoriy(get()) as IYouTravelsRepositoriy }
    single { YouTravelsUseCase(get()) as IYouTravelsUseCase }
    single { NewTravelsUseCase(get()) as INewTravelsUseCase }
    single { NewTravelsRepositoiy(get()) as INewTravelsRepositoriy }
    single { SharedPrefHelper() as ISharedPrefHelper }
    single { ChooseSkinUseCase() as IChooseSkinUseCase }
    single { FairbaseStorageBase(get()) as IFairbaseStorageBase }
    single { ExpenceAllUseCase(get()) as IExpenceAllUseCase }
    single { CurrencyUseCase() as ICurrencyUseCase }
    single { AllCurrencyUseCase() as IAllCurrencyUseCase }
    single { ExpenceRepository(get()) as IExpenceRepository }
    single { ExpenceUseCase() as IExpenceUseCase }
    single { ExpenceAllRepositoriy(get(), get()) as IExpenceAllRepositoriy }
    single { LocalRepositoriy(get()) as ILocalRepositoriy }
    single { OflineModeUseCase() as IOflineModeUseCase }


}