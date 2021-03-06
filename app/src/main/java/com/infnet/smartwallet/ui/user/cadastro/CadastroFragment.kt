package com.infnet.smartwallet.ui.user.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        return inflater.inflate(R.layout.cadastro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCadastrar.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        imageViewBackCadastro.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}