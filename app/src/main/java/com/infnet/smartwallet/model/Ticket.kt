package com.infnet.smartwallet.model

import com.google.firebase.firestore.DocumentId
import java.util.*

class Ticket (
    var nome: String? = null,
    var local: String? = null,
    var data: String? = null,
    var hora: String? = null,
    var categoria: String? = null,
    var usuarioId: String? = null,
    @DocumentId
    var id: String? = null
)