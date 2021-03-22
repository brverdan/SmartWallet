package com.infnet.smartwallet.database

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.infnet.smartwallet.model.Ticket
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.random.Random


class TicketDaoFirestore : TicketDao {

    private val collection = FirebaseFirestore.getInstance().collection("tickets")
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance().reference.child("codigoDeBarrasQrCode")

    override fun insert(ticket: Ticket): Task<DocumentReference> {
        ticket.usuarioId = firebaseAuth.currentUser.uid
        return collection.add(ticket)
    }

    override fun delete(ticket: Ticket): Task<Void> {
        return collection.document(ticket.id!!).delete()
    }

    override fun all(): Query {
        return collection.whereEqualTo("usuarioId", firebaseAuth.currentUser.uid)
    }

    override fun read(key: String): Query {
        return collection.whereEqualTo("id", key)
    }

    override fun edit(ticket: Ticket): Task<Void> {
        return collection.document(ticket.id!!).set(ticket)
    }

    override fun cadastrarImagemPerfil(imagem: Bitmap, uid: String, nomeTicket: String): UploadTask {
        val outPutStream = ByteArrayOutputStream()
        imagem.compress(Bitmap.CompressFormat.JPEG, 100, outPutStream)
        return storage.child("${uid}/${nomeTicket}.jpeg").putBytes(outPutStream.toByteArray())
    }

    override fun receberImagem(uid: String, file: File, nomeTicket: String): FileDownloadTask {
        return storage.child("${uid}/${nomeTicket}.jpeg").getFile(file)
    }
}