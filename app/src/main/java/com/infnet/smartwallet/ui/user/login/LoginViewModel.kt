package com.infnet.smartwallet.ui.user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.infnet.smartwallet.database.UsuarioFirebaseDao

class LoginViewModel : ViewModel() {
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun verificarCredenciais(email: String, senha: String) {
        UsuarioFirebaseDao.verificarCredencias(email, senha).addOnSuccessListener {
            _status.value = true
        }.addOnFailureListener {
            _msg.value = it.message
        }
    }

}