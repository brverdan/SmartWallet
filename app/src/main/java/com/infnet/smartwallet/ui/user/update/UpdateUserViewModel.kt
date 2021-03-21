package com.infnet.smartwallet.ui.user.update

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infnet.smartwallet.database.UsuarioFirebaseDao
import com.infnet.smartwallet.model.Usuario
import kotlin.math.log

class UpdateUserViewModel : ViewModel() {
    private val _usuario = MutableLiveData<Usuario?>()
    val usuario : LiveData<Usuario?> = _usuario

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    fun updateUsuario(nome:String, sobrenome:String) {
        var usuarioId = UsuarioFirebaseDao.firebaseAuth.currentUser.uid
        UsuarioFirebaseDao.atualizarNomeSobrenome(nome, sobrenome, usuarioId)
            .addOnSuccessListener {
                _status.value = true
            }
            .addOnFailureListener {
                Log.e("upadteUsuario", "${it.message}")
            }
    }
}