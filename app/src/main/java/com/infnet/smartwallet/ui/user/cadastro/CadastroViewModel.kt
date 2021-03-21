package com.infnet.smartwallet.ui.user.cadastro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infnet.smartwallet.database.UsuarioFirebaseDao

class CadastroViewModel : ViewModel() {
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun salvarCadastro(email: String, password: String, nome: String, sobrenome: String) {
        UsuarioFirebaseDao.cadastrarCredenciais(email, password).addOnSuccessListener {
            val uid = it.user!!.uid
            UsuarioFirebaseDao.cadastrarPerfil(uid, nome, sobrenome).addOnSuccessListener {
                _status.value = true
                _msg.value = "Usuario cadastrado com sucesso!"
            }.addOnFailureListener {
                _msg.value = "${it.message}"
            }

        }.addOnFailureListener {
            _msg.value = "${it.message}"
        }
    }
}