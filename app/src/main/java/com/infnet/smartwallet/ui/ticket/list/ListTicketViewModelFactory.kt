package com.infnet.smartwallet.ui.ticket.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.infnet.smartwallet.database.TicketDao
import java.lang.IllegalArgumentException

class ListTicketViewModelFactory(private val ticketDao: TicketDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListTicketsViewModel::class.java))
            return ListTicketsViewModel(ticketDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}