package com.infnet.smartwallet.ui.ticket.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.infnet.smartwallet.R
import com.infnet.smartwallet.database.ObjetoUtil
import com.infnet.smartwallet.database.TicketDaoFirestore
import com.infnet.smartwallet.model.Ticket
import kotlinx.android.synthetic.main.details_ticket_fragment.*
import kotlinx.android.synthetic.main.form_ticket_fragment.*

class DetailsTicketFragment : Fragment() {
    private lateinit var viewModel: DetailsTicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_ticket_fragment, container, false)

        val detailsTicketViewModelFactory = DetailsTicketViewModelFactory(requireActivity().application, TicketDaoFirestore())

        viewModel = ViewModelProvider(this, detailsTicketViewModelFactory).get(DetailsTicketViewModel::class.java)

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationTickets)
        bottomNavigationView.visibility = View.GONE

        viewModel.receberFoto()
        viewModel.imagemPerfil.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                imageViewFotoTicketDetails.setImageURI(it)
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onViewCreated(view, savedInstanceState)
        if (ObjetoUtil.ticketSelecionado != null)
            preencherDetails(ObjetoUtil.ticketSelecionado!!)

        imageViewBackDetailsTicket.setOnClickListener {
            findNavController().popBackStack()
        }

        fabEditTicket.setOnClickListener {
            findNavController().navigate(R.id.formTicketFragment)
        }

        fabDeleteTicket.setOnClickListener {
            TicketDaoFirestore().delete(ObjetoUtil.ticketSelecionado!!)
            findNavController().popBackStack()
        }
    }
    private fun preencherDetails (ticket: Ticket){
        textViewLocalDetail.setText(ticket.local)
        textViewDataDetail.setText(ticket.data)
        textViewHorarioDetail.setText(ticket.hora)
        textViewNomeDetail.setText(ticket.nome)
        textViewCategoriaDetail.setText(ticket.categoria)
    }
}