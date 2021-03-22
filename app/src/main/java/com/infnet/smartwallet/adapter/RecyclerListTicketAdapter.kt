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
import com.infnet.smartwallet.enums.CategoriaEnum
import com.infnet.smartwallet.model.Ticket


class RecyclerListTicketAdapter(
    private val tickets: List<Ticket>,
    val actionClick: (Ticket) -> Unit
) : RecyclerView.Adapter<RecyclerListTicketAdapter.TicketsViewHolder>() {

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
        mBitmapIds.add(CategoriaEnum.CINEMA.caminho)
        mBitmapIds.add(CategoriaEnum.TEATRO.caminho)
        mBitmapIds.add(CategoriaEnum.FESTA.caminho)
        mBitmapIds.add(CategoriaEnum.SHOW.caminho)
        mBitmapIds.add(CategoriaEnum.EVENTOESPORTIVO.caminho)

        if(ticket.categoria == "Cinema") {
            uris.add(Uri.parse("android.resource://com.infnet.smartwallet/drawable/" + mBitmapIds[0]))
        }
        else if(ticket.categoria == "Teatro") {
            uris.add(Uri.parse("android.resource://com.infnet.smartwallet/drawable/" + mBitmapIds[1]))
        }
        else if(ticket.categoria == "Festa") {
            uris.add(Uri.parse("android.resource://com.infnet.smartwallet/drawable/" + mBitmapIds[2]))
        }
        else if(ticket.categoria == "Show") {
            uris.add(Uri.parse("android.resource://com.infnet.smartwallet/drawable/" + mBitmapIds[3]))
        }
        else {
            uris.add(Uri.parse("android.resource://com.infnet.smartwallet/drawable/" + mBitmapIds[4]))
        }

        holder.imgCategoria.setImageURI(uris[0])

        holder.itemView.setOnClickListener {
            actionClick(ticket)
        }
    }
}