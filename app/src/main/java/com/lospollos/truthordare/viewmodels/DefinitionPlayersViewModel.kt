package com.lospollos.truthordare.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lospollos.truthordare.domain.entities.Players

class DefinitionPlayersViewModel: ViewModel() {

    val playersListLiveData = MutableLiveData<List<String>>()

}