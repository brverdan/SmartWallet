package com.infnet.smartwallet.ui.ticket.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.infnet.smartwallet.R

class ListTicketsFragment : Fragment() {

    companion object {
        fun newInstance() = ListTicketsFragment()
    }

    private lateinit var viewModel: ListTicketsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_tickets_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListTicketsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}