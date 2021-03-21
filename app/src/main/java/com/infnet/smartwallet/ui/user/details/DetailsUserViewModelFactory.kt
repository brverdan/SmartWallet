package com.infnet.smartwallet.ui.user.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.infnet.smartwallet.database.TicketDao
import java.lang.IllegalArgumentException

class DetailsUserViewModelFactory(private val ticketDao: TicketDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsUserViewModel::class.java))
            return DetailsUserViewModel(ticketDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}