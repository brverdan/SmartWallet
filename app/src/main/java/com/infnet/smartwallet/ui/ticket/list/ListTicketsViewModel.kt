package com.infnet.smartwallet.ui.ticket.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infnet.smartwallet.model.Ticket

class ListTicketsViewModel : ViewModel() {
    private val _tickets = MutableLiveData<List<Ticket>>()
    val ticket = Ticket("Cinema", "Norte Shopping", null, "CINEMA")
    var lista = mutableListOf<Ticket>()


    val tickets: LiveData<List<Ticket>> = _tickets

    fun add (){
        lista.add(ticket)
    }

    fun sla(){
        _tickets.value = lista
    }




    
}