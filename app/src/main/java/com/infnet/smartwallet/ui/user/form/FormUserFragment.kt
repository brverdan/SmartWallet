package com.infnet.smartwallet.ui.user.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.infnet.smartwallet.R
import kotlinx.android.synthetic.main.form_ticket_fragment.*
import kotlinx.android.synthetic.main.form_user_fragment.*

class FormUserFragment : Fragment() {

    companion object {
        fun newInstance() = FormUserFragment()
    }

    private lateinit var viewModel: FormUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.form_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabSaveUpdateUsuario.setOnClickListener {
            findNavController().navigate(R.id.detailsUserFragment)
        }
    }
}