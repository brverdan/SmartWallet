package com.infnet.smartwallet.ui.ticket.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.infnet.smartwallet.R

class DetailsTicketFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsTicketFragment()
    }

    private lateinit var viewModel: DetailsTicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_ticket_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsTicketViewModel::class.java)
        // TODO: Use the ViewModel
    }

}