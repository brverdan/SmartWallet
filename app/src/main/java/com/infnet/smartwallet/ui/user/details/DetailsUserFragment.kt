package com.infnet.smartwallet.ui.user.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.infnet.smartwallet.R

class DetailsUserFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsUserFragment()
    }

    private lateinit var viewModel: DetailsUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}