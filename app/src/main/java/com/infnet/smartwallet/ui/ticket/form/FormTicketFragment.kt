package com.infnet.smartwallet.ui.ticket.form

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.infnet.smartwallet.R
import com.infnet.smartwallet.database.ObjetoUtil
import com.infnet.smartwallet.database.TicketDaoFirestore
import com.infnet.smartwallet.model.Ticket
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.details_ticket_fragment.*
import kotlinx.android.synthetic.main.form_ticket_fragment.*
import kotlinx.android.synthetic.main.form_user_fragment.*

class FormTicketFragment : Fragment() {
    private lateinit var viewModel: FormTicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_ticket_fragment, container, false)

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationTickets)
        bottomNavigationView.visibility = View.GONE

        val formTicketViewModelFactory = FormTicketViewModelFactory(requireActivity().application, TicketDaoFirestore())

        viewModel = ViewModelProvider(this, formTicketViewModelFactory).get(FormTicketViewModel::class.java)

        viewModel.let {
            it.message.observe(viewLifecycleOwner, Observer { message ->
                if(!message.isNullOrBlank()) {
                    makeToast(message)
                }
            })
            it.status.observe(viewLifecycleOwner, Observer { status ->
                    if(status) {
                        findNavController().popBackStack(R.id.listTicketsFragment, false)
                    }
            })

        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ArrayAdapter.createFromResource(requireActivity(), R.array.categorias_array, android.R.layout.simple_spinner_dropdown_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCategoriaAddTicket.adapter = adapter
        }
        spinnerCategoriaAddTicket.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.categoriaSelecionada(parent!!.getItemAtPosition(position).toString())
                makeToast("${viewModel.categoriaSelecionadaString}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        if(ObjetoUtil.ticketSelecionado != null) {
            preencherFormulario(ObjetoUtil.ticketSelecionado!!)
        }

        imageViewBackFormTicket.setOnClickListener {
            findNavController().popBackStack()
        }

        fabSaveTicket.setOnClickListener {
            val local = editTextLocalAddTicket.text.toString()
            val nome = editTextNomeAddTicket.text.toString()
            val data = editTextDataAddTicket.text.toString()
            val hora = editTextHoraAddTicket.text.toString()
            val categoria = viewModel.categoriaSelecionadaString
            if(categoria == "Selecionar Categoria") {
                makeToast("Categoria deve ser selecionada!!")
            }
            viewModel.salvarTicket(nome, local, data, hora, categoria!!)
        }

        imageViewAddTicketImage.setOnClickListener {
            tirarFoto()
        }
    }

    private fun tirarFoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //startActivityForResult(intent, 200)
        //if(intent.resolveActivity(requireActivity().packageManager) != null){
        startActivityForResult(intent, 200)
        //}
        //else {
        //   makeToast("Nenhum recurso disponível.")
        //}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            var imageBitmap = data!!.extras!!.get("data") as Bitmap
            imageViewAddTicketImage.setImageBitmap(imageBitmap)
            viewModel.alterarImagemPerfil(imageBitmap)
        }
    }

    private fun preencherFormulario(ticket: Ticket) {
        editTextLocalAddTicket.setText(ticket.local)
        editTextNomeAddTicket.setText(ticket.nome)
        editTextDataAddTicket.setText(ticket.data)
        editTextHoraAddTicket.setText(ticket.hora)
        spinnerCategoriaAddTicket.setSelection((spinnerCategoriaAddTicket.getAdapter() as? ArrayAdapter<String>)!!.getPosition(ticket.categoria))

        viewModel.receberFoto()
        viewModel.imagemPerfil.observe(viewLifecycleOwner, Observer { foto ->
            if(foto != null) {
                imageViewAddTicketImage.setImageURI(foto)
            }
        })
    }

    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}