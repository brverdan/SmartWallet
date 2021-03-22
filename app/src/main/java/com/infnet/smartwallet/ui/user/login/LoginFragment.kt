package com.infnet.smartwallet.ui.user.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationTickets)
        bottomNavigationView.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it){
                findNavController().navigate(R.id.listTicketsFragment)
            }
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrBlank())
                makeToast(it)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            val email = editTextEmailLogin.text.toString()
            val senha = editTextSenhaLogin.text.toString()
            if(!email.isNullOrBlank() && !senha.isNullOrBlank()) {
                viewModel.verificarCredenciais(email, senha)
            }
            else {
                makeToast("Email e Senha obrigatórios.")
            }
        }
        textViewCadastro.setOnClickListener {
            findNavController().navigate(R.id.cadastroFragment)
        }
    }
    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}