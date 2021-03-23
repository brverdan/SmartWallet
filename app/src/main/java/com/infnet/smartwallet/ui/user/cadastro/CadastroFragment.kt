package com.infnet.smartwallet.ui.user.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.infnet.smartwallet.R
import kotlinx.android.synthetic.main.cadastro_fragment.*

class CadastroFragment : Fragment() {
    private lateinit var viewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cadastro_fragment, container, false)

        viewModel = ViewModelProvider(this).get(CadastroViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it)
                findNavController().popBackStack()
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                makeToast(it)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCadastrar.setOnClickListener {
            val senha = editTextSenhaCadastro.text.toString()
            val resenha = editTextConfirmarSenhaCadastro.text.toString()
            val email = editTextEmailCadastro.text.toString()
            val nome = editTextNomeCadastro.text.toString()
            val sobrenome = editTextSobrenomeCadastro.text.toString()
            if(validarCamposCadastrarUsuario(email, senha, resenha, nome, sobrenome)) {
                if(verificarTamanhoSenha(senha)) {
                    if(verificarSenhasIguais(senha, resenha)) {
                        viewModel.salvarCadastro(email, senha, nome, sobrenome)
                    }
                    else {
                        makeToast("Senhas não batem.")
                    }
                }
                else {
                    makeToast("Senha deve conter mais de 6 caracteres.")
                }
            }
            else {
                makeToast("Todos os campos devem ser preenchidos!")
            }

        }

        imageViewBackCadastro.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun verificarSenhasIguais(senha: String, resenha: String) = senha == resenha

    fun verificarTamanhoSenha(senha: String) = senha.length >= 6

    fun validarCamposCadastrarUsuario(email: String, senha: String, resenha: String, nome: String, sobrenome: String) =
        !email.isNullOrBlank() && !senha.isNullOrBlank() && !resenha.isNullOrBlank() && !nome.isNullOrBlank() && !sobrenome.isNullOrBlank()

    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}