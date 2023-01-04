package ru.android.myrecipesbook.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.android.myrecipesbook.ui.viewmodel.FavoriteViewModelDB

class FavoriteFromDBViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModelDB(application) as T
    }
}