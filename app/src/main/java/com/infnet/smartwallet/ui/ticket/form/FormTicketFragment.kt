package com.infnet.smartwallet.ui.ticket.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.infnet.smartwallet.R

class FormTicketFragment : Fragment() {

    companion object {
        fun newInstance() = FormTicketFragment()
    }

    private lateinit var viewModel: FormTicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.form_ticket_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormTicketViewModel::class.java)
        // TODO: Use the ViewModel
    }

}