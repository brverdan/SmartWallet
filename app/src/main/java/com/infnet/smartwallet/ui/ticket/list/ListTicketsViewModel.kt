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
        ticketDao.all().addOnSuccessListener {
            val ticketFB = it.toObjects(Ticket::class.java)
            _tickets.value = ticketFB
        }.addOnFailureListener {
            Log.i("ListTicketsFrag", "${it.message}")
        }
    }
}