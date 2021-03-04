package com.infnet.smartwallet.ui.ticket.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.infnet.smartwallet.R
import kotlinx.android.synthetic.main.activity_main.*

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

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormTicketViewModel::class.java)

    }

}