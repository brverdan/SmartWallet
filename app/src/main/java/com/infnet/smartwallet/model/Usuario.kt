package com.infnet.smartwallet.model

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentId

class Usuario (
        var nome: String? = null,
        var sobrenome: String? = null,
        var email: String? = null,
        var firebaseUser : FirebaseUser? = null,
        var qntdTickets : Int = 0,
        @DocumentId
        var uid: String? = null
)