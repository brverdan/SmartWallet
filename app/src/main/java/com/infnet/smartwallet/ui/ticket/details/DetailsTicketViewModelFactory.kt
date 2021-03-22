package com.infnet.smartwallet.ui.ticket.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.infnet.smartwallet.database.TicketDao
import java.lang.IllegalArgumentException

class DetailsTicketViewModelFactory(private val application: Application, private val ticketDao: TicketDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsTicketViewModel::class.java)){
            return DetailsTicketViewModel(application, ticketDao) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}