package com.saico.airlineticket.home

import androidx.lifecycle.LiveData
import com.saico.airlineticket.domain.LocationModel
import com.saico.airlineticket.domain.repository.MainRepository


class HomeViewModel {
    private val repository = MainRepository()

    fun loadLocations(): LiveData<MutableList<LocationModel>>{
        return repository.loadLocation()
    }


}