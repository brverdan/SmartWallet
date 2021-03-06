package com.infnet.smartwallet.ui.user.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.infnet.smartwallet.R
import com.infnet.smartwallet.ui.ticket.list.ListTicketsViewModel
import kotlinx.android.synthetic.main.cadastro_fragment.*
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.login_fragment, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            if (viewModel.authenticate())
                findNavController().navigate(R.id.listTicketsFragment)
            else {
                Toast.makeText(
                    requireContext(),
                    "Login não sucedido!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        textViewCadastro.setOnClickListener {
            findNavController().navigate(R.id.cadastroFragment)
        }
    }
}