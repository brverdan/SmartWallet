package com.infnet.smartwallet.ui.user.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infnet.smartwallet.database.TicketDao
import com.infnet.smartwallet.database.UsuarioFirebaseDao
import com.infnet.smartwallet.model.Ticket
import com.infnet.smartwallet.model.Usuario

class DetailsUserViewModel(private val ticketDao: TicketDao) : ViewModel() {
    private val _usuario = MutableLiveData<Usuario?>()
    val usuario: LiveData<Usuario?> = _usuario
    private var _tickets = listOf<Ticket>()

    init{
        ticketDao.all().addSnapshotListener { value, error ->
            if (error != null) {
                Log.i("FirebaseFirestore", "${error.message}")
            } else {
                if (value != null && !value.isEmpty) {
                    _tickets = value.toObjects(Ticket::class.java)
                }
            }
        }
    }
    fun attPerfil() {
        UsuarioFirebaseDao.consultarUsuario().addOnSuccessListener {
            val usuario = it.toObject(Usuario::class.java)
            usuario!!.firebaseUser = UsuarioFirebaseDao.firebaseAuth.currentUser
            usuario.email = UsuarioFirebaseDao.firebaseAuth.currentUser.email
            usuario.qntdTickets = _tickets.size
            _usuario.value = usuario!!
        }
    }

    fun encerrarSessao() {
        UsuarioFirebaseDao.encerrarSessao()
        _usuario.value = null
    }
}