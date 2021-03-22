package com.infnet.smartwallet.ui.ticket.details

import android.app.Application
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
import java.io.File
import kotlin.random.Random

class DetailsTicketViewModel(application: Application, private val ticketDao: TicketDao) : AndroidViewModel(application) {
    val app = application

    private val _imagemPerfil = MutableLiveData<Uri>()
    var imagemPerfil: LiveData<Uri> = _imagemPerfil

    fun receberFoto() {
        val file = File(app.cacheDir, "${Random.nextInt(0, Int.MAX_VALUE)}.jpeg")
        val usuarioId = UsuarioFirebaseDao.firebaseAuth.currentUser.uid
        if(ObjetoUtil.ticketSelecionado!!.nome != null) {
            ticketDao.receberImagem(usuarioId, file, ObjetoUtil.ticketSelecionado!!.nome!!)
                .addOnSuccessListener {
                    _imagemPerfil.value = file.toUri()
                }
                .addOnFailureListener {
                    Log.i("UploadImagem", "${it.message}")
                }
        }
    }
}