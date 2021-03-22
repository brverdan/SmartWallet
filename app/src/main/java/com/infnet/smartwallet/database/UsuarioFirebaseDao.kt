package com.infnet.smartwallet.database

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.infnet.smartwallet.model.Usuario
import java.io.ByteArrayOutputStream

object UsuarioFirebaseDao {
    val firebaseAuth = FirebaseAuth.getInstance()
    private val collection = FirebaseFirestore.getInstance().collection("usuarios")

    fun cadastrarCredenciais(email: String, senha: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, senha)
    }

    fun cadastrarPerfil(uid: String, nome: String, sobrenome: String): Task<Void>{
        return collection.document(uid).set(Usuario(nome, sobrenome))
    }

    fun verificarCredencias(email: String, senha: String): Task<AuthResult>{
        return firebaseAuth.signInWithEmailAndPassword(email, senha)
    }

    fun encerrarSessao(){
        firebaseAuth.signOut()
    }

    fun consultarUsuario(): Task<DocumentSnapshot>{
        val firebaseUser = firebaseAuth.currentUser
        return collection.document(firebaseUser!!.uid).get()
    }

    fun atualizarNomeSobrenome(nome: String, sobrenome: String, uid: String): Task<Void> {
        collection.document(uid).update("nome", nome)
        return collection.document(uid).update("sobrenome", sobrenome)
    }
}