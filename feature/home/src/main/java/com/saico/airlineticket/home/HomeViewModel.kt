package com.saico.airlineticket.home

import androidx.lifecycle.LiveData
import com.saico.airlineticket.model.LocationModel
import com.saico.airlineticket.domain.repository.MainRepository


class HomeViewModel {
    private val repository = MainRepository()

    fun loadLocations(): LiveData<MutableList<com.saico.airlineticket.model.LocationModel>>{
        return repository.loadLocation()
    }


}