package com.saico.airlineticket.home.search

import androidx.lifecycle.LiveData
import com.saico.airlineticket.domain.repository.MainRepository
import com.saico.airlineticket.model.FlightModel

class SearchViewModel {
    private val repository = MainRepository()

    fun loadFiltered(from: String, to: String): LiveData<MutableList<FlightModel>> {
        return repository.loadFiltered(from, to)

    }
}