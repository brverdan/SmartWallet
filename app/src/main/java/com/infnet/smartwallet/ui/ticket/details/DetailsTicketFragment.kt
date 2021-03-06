package com.infnet.smartwallet.ui.ticket.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.infnet.smartwallet.R
import kotlinx.android.synthetic.main.details_ticket_fragment.*
import kotlinx.android.synthetic.main.form_ticket_fragment.*

class DetailsTicketFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsTicketFragment()
    }

    private lateinit var viewModel: DetailsTicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_ticket_fragment, container, false)
        viewModel = ViewModelProvider(this).get(DetailsTicketViewModel::class.java)

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationTickets)
        bottomNavigationView.visibility = View.GONE

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewBackDetailsTicket.setOnClickListener {
            findNavController().popBackStack()
        }

        fabEditTicket.setOnClickListener {
            findNavController().navigate(R.id.formTicketFragment)
        }

        fabDeleteTicket.setOnClickListener {
            // TODO: Pop-up confirmacao
            findNavController().popBackStack()
        }
    }
}