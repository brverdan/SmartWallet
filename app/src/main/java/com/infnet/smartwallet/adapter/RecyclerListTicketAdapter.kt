package com.infnet.smartwallet.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.infnet.smartwallet.R
import com.infnet.smartwallet.model.Ticket


class RecyclerListTicketAdapter(
    private val tickets: List<Ticket>)
    //val actionClick: (Ticket) -> Unit)
    : RecyclerView.Adapter<RecyclerListTicketAdapter.TicketsViewHolder>() {

    class TicketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNomeTicket: TextView = itemView.findViewById(R.id.txtNomeTicket)
        val imgCategoria: ImageView = itemView.findViewById(R.id.imgCategoriaTicket)
        val txtDataTicket: TextView = itemView.findViewById(R.id.txtDataTicket)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_tickets, parent, false)
        return TicketsViewHolder(view)
    }

    override fun getItemCount(): Int = tickets.size

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        val ticket = tickets[position]
        holder.txtNomeTicket.text = ticket.nome
        holder.txtDataTicket.text = ticket.data.toString()

        var mBitmapIds = arrayListOf<Int>()
        var uris = arrayListOf<Uri>()
        mBitmapIds.add(R.drawable.ticket_icon)
        uris.add(Uri.parse("android.resource://com.infnet.smartwallet/drawable/" + mBitmapIds[0]))

//        val uri =
//            Uri.parse("android.resource://"+ java.lang.Package.getPackage("com.infnet.smartwallet") + R.drawable.ticket_icon)

        holder.imgCategoria.setImageURI(uris[0])

//        holder.itemView.setOnClickListener {
//            actionClick(pessoa)
//        }

//        storageReference.child("yuri/foto0.png")
//            .getBytes(1024*1024*1024)
//            .addOnSuccessListener {
//                val bitmap = BitmapFactory.decodeByteArray(it, 0 , it.size)
//                holder.imgPessoa.setImageBitmap(bitmap)
//            }
//            .addOnFailureListener {
//                Log.i("FirebaseStorage", "Falhou: ${it.message}")
//            }
    }
}