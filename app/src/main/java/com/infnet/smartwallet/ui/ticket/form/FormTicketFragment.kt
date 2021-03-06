package com.infnet.smartwallet.ui.ticket.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.infnet.smartwallet.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.form_ticket_fragment.*
import kotlinx.android.synthetic.main.form_user_fragment.*

class FormTicketFragment : Fragment() {

    companion object {
        fun newInstance() = FormTicketFragment()
    }

    private lateinit var viewModel: FormTicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_ticket_fragment, container, false)

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationTickets)
        bottomNavigationView.visibility = View.GONE

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormTicketViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewBackFormTicket.setOnClickListener {
            findNavController().popBackStack()
        }

        fabSaveTicket.setOnClickListener {
            // if (TicketUtil.ticketSelecionado == null)
                findNavController().navigate(R.id.listTicketsFragment)

            // findNavController().navigate(R.id.detailsTicketFragment)
        }
    }
}