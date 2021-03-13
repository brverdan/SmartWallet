package com.infnet.smartwallet.model

import com.google.firebase.firestore.DocumentId
import java.util.*

class Ticket (
    val nome: String? = null,
    val local: String? = null,
    val data: Date? = null,
    val categoria: String? = null,
    @DocumentId
    var id: String? = null
)