package com.infnet.smartwallet.database

import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi

class CriptoString {

    companion object{
        @JvmStatic
        val criptoGrafador = Criptografador()
    }
    private var cripto: ByteArray? = null

    fun getCriptoBase64(): String?{
        return Base64.encodeToString(cripto,Base64.DEFAULT)
    }
    fun setCriptoBase64(value: String?){
        cripto = Base64.decode(value,Base64.DEFAULT)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getClearText(): String?{
        return criptoGrafador.decipher(cripto!!)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun setClearText(value: String?){
        cripto = criptoGrafador.cipher(value!!)
    }
}