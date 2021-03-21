package com.infnet.smartwallet.ui.ticket.form

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infnet.smartwallet.database.ObjetoUtil
import com.infnet.smartwallet.database.TicketDao
import com.infnet.smartwallet.model.Ticket

class FormTicketViewModel(private val ticketDao: TicketDao) : ViewModel() {
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status
    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message
    var categoriaSelecionadaString : String? = null

    init{
        _status.value = false
        _message.value = null
    }

    fun salvarTicket(nome: String, local: String, data: String, hora: String, categoria: String){
        _status.value = false
        _message.value = "Aguarde a persistência..."

        val ticket = Ticket(nome, local, data, hora, categoria)

        if(ObjetoUtil.ticketSelecionado != null){
            ticket.id = ObjetoUtil.ticketSelecionado!!.id
            ticketDao.edit(ticket)
        }
        else{
            ticketDao.insert(ticket).addOnSuccessListener {
                _status.value = true
                _message.value = "Persistência realizada com êxito!"
            }.addOnFailureListener{
                _message.value = "Falha na persistência!"
                Log.e("SerieDaoFirebase", "${it.message}")
            }
        }
    }

    fun categoriaSelecionada(categoria : String) {
        categoriaSelecionadaString = categoria
    }


}