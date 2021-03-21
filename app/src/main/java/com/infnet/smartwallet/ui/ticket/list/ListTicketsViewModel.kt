package com.infnet.smartwallet.ui.ticket.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infnet.smartwallet.database.TicketDao
import com.infnet.smartwallet.model.Ticket

class ListTicketsViewModel(private val ticketDao: TicketDao) : ViewModel() {
    private val _tickets = MutableLiveData<MutableList<Ticket>>()
    val tickets: LiveData<MutableList<Ticket>> = _tickets

    fun attListTickets () {
        ticketDao.all().addSnapshotListener { value, error ->
            if (error != null) {
                Log.i("FirebaseFirestore", "${error.message}")
            } else {
                if (value != null && !value.isEmpty) {
                    _tickets.value = value.toObjects(Ticket::class.java)
                }
            }
        }
    }
}