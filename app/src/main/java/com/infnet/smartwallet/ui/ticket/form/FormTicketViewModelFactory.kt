package com.infnet.smartwallet.ui.ticket.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.infnet.smartwallet.database.TicketDao
import java.lang.IllegalArgumentException

class FormTicketViewModelFactory(val ticketDao: TicketDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormTicketViewModel::class.java)){
            return FormTicketViewModel(ticketDao) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}