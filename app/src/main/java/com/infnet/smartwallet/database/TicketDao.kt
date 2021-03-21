package com.infnet.smartwallet.database

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.infnet.smartwallet.model.Ticket

interface TicketDao {
    fun insert(ticket: Ticket) : Task<DocumentReference>

    fun delete(ticket: Ticket) : Task<Void>

    fun all(): Query

    fun read(key: String): Query

    fun edit(ticket: Ticket): Task<Void>
}