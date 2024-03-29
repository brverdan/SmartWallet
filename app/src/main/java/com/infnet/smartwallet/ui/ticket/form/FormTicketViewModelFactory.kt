package com.infnet.smartwallet.ui.ticket.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.infnet.smartwallet.database.TicketDao
import java.lang.IllegalArgumentException

class FormTicketViewModelFactory(val application: Application, val ticketDao: TicketDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormTicketViewModel::class.java)){
            return FormTicketViewModel(application, ticketDao) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}