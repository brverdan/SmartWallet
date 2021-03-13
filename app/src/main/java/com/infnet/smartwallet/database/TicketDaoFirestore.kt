package com.infnet.smartwallet.database

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.infnet.smartwallet.model.Ticket

class TicketDaoFirestore : TicketDao {

    private val collection = FirebaseFirestore.getInstance().collection("tickets")

    override fun insert(ticket: Ticket): Task<DocumentReference> {
        return collection.add(ticket)
    }

    override fun delete(ticket: Ticket): Task<Void> {
        return collection.document(ticket.id!!).delete()
    }

    override fun all(): Task<QuerySnapshot> {
        return collection.get()
    }

    override fun read(key: String): Query {
        return collection.whereEqualTo("id", key)
    }

    override fun edit(ticket: Ticket): Task<Void> {
        return collection.document(ticket.id!!).set(ticket)
    }
}