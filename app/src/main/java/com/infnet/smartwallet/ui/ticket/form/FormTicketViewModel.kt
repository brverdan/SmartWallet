package com.infnet.smartwallet.ui.ticket.form

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infnet.smartwallet.database.ObjetoUtil
import com.infnet.smartwallet.database.TicketDao
import com.infnet.smartwallet.database.UsuarioFirebaseDao
import com.infnet.smartwallet.model.Ticket
import java.io.File
import kotlin.random.Random

class FormTicketViewModel(application: Application, private val ticketDao: TicketDao) : AndroidViewModel(application) {

    private var fotoPerfil: Bitmap? = null

    val app = application

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    var categoriaSelecionadaString : String? = null

    private val _imagemPerfil = MutableLiveData<Uri>()
    var imagemPerfil: LiveData<Uri> = _imagemPerfil

    init {
        _status.value = false
        _message.value = null
    }

    fun salvarTicket(nome: String, local: String, data: String, hora: String, categoria: String) {
        _status.value = false
        _message.value = "Aguarde a persistência..."

        val ticket = Ticket(nome, local, data, hora, categoria)

        if(ObjetoUtil.ticketSelecionado != null) {
            ticket.id = ObjetoUtil.ticketSelecionado!!.id
            ticket.usuarioId = ObjetoUtil.ticketSelecionado!!.usuarioId
            if(fotoPerfil != null) {
                ticketDao.cadastrarImagemPerfil(fotoPerfil!!, UsuarioFirebaseDao.firebaseAuth.currentUser.uid, ticket.nome!!.trim())
                    .addOnSuccessListener {
                        ticketDao.edit(ticket).addOnSuccessListener {
                            _status.value = true
                            _message.value = "Persistência realizada com êxito!"
                        }.addOnFailureListener {
                            Log.e("ticketFirestore", "${it.message}")
                        }
                    }
                    .addOnFailureListener {
                        Log.e("FotoFirestore", "${it.message}")
                    }
            }

        }
        else {
            if(fotoPerfil != null) {
                    ticketDao.cadastrarImagemPerfil(fotoPerfil!!, UsuarioFirebaseDao.firebaseAuth.currentUser.uid, ticket.nome!!.trim())
                        .addOnSuccessListener {
                            ticketDao.insert(ticket).addOnSuccessListener {
                                _status.value = true
                                _message.value = "Persistência realizada com êxito!"
                            }.addOnFailureListener{
                                Log.e("ticketFirestore", "${it.message}")
                            }
                        }
                        .addOnFailureListener {
                            Log.e("FotoFirestore", "${it.message}")
                        }
            }
        }
    }

    fun receberFoto() {
        val file = File(app.cacheDir, "${Random.nextInt(0, Int.MAX_VALUE)}.jpeg")
        val usuarioId = UsuarioFirebaseDao.firebaseAuth.currentUser.uid
        if(ObjetoUtil.ticketSelecionado!!.nome != null) {
            ticketDao.receberImagem(usuarioId, file, ObjetoUtil.ticketSelecionado!!.nome!!)
                    .addOnSuccessListener {
                        _imagemPerfil.value = file.toUri()
                        val bitmap = BitmapFactory.decodeFile(file.path)
                        fotoPerfil = bitmap
                    }
                    .addOnFailureListener {
                        Log.i("UploadImagem", "${it.message}")
                    }
        }
    }

    fun alterarImagemPerfil(img: Bitmap) {
        fotoPerfil = img
    }

    fun categoriaSelecionada(categoria : String) {
        categoriaSelecionadaString = categoria
    }
}