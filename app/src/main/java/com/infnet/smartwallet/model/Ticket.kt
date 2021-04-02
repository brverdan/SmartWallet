package com.infnet.smartwallet.model

import com.google.firebase.firestore.DocumentId
import com.infnet.smartwallet.database.CriptoString
import java.util.*

class Ticket (
    var nome: String? = null,
    var local: CriptoString? = null,
    var data: String? = null,
    var hora: String? = null,
    var categoria: String? = null,
    var usuarioId: String? = null,
    @DocumentId
    var id: String? = null
)