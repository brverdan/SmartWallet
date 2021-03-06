package com.infnet.smartwallet.ui.ticket.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infnet.smartwallet.model.Ticket

class ListTicketsViewModel : ViewModel() {
    private val _tickets = MutableLiveData<MutableList<Ticket>>()
    val tickets: LiveData<MutableList<Ticket>> = _tickets

     init {
         _tickets.value = mutableListOf<Ticket>()
     }

    fun add (){
        _tickets.value!!.add(Ticket("Cinema", "Norte Shopping", null, "CINEMA"))
    }
}