package com.infnet.smartwallet.ui.ticket.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.infnet.smartwallet.R
import com.infnet.smartwallet.adapter.RecyclerListTicketAdapter
import kotlinx.android.synthetic.main.cadastro_fragment.*
import kotlinx.android.synthetic.main.list_tickets_fragment.*

class ListTicketsFragment : Fragment() {
    private lateinit var viewModel: ListTicketsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_tickets_fragment, container, false)

        viewModel = ViewModelProvider(this).get(ListTicketsViewModel::class.java)
        viewModel.add()
        viewModel.tickets.observe(viewLifecycleOwner){
            recyclerlistTickets.adapter = RecyclerListTicketAdapter(it) {
                findNavController().navigate(R.id.detailsTicketFragment)
            }
            recyclerlistTickets.layoutManager = LinearLayoutManager(requireContext())
        }

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationTickets)
        bottomNavigationView.visibility = View.VISIBLE

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAddTicket.setOnClickListener {
            findNavController().navigate(R.id.formTicketFragment)
        }
    }
}