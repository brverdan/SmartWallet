package com.infnet.smartwallet.database

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.UploadTask
import com.infnet.smartwallet.model.Ticket
import java.io.File

interface TicketDao {
    fun insert(ticket: Ticket) : Task<DocumentReference>

    fun delete(ticket: Ticket) : Task<Void>

    fun all(): Query

    fun read(key: String): Query

    fun edit(ticket: Ticket): Task<Void>

    fun cadastrarImagemPerfil(imagem: Bitmap, uid: String, nomeTicket: String): UploadTask

    fun receberImagem(uid: String, file: File, nomeTicket: String): FileDownloadTask
}