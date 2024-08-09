package com.ftgrl.bankingapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ftgrl.bankingapp.databinding.FragmentHomeBinding
import com.ftgrl.bankingapp.databinding.PartialMainActivityBinding

class HomeFragment : Fragment() {

    private lateinit var bindingFragment: FragmentHomeBinding
    private lateinit var bindingPartial: PartialMainActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the Fragment's layout
        bindingFragment = FragmentHomeBinding.inflate(inflater, container, false)
        val view = bindingFragment.root

        // Bind the PartialMainActivity layout
        val partialView = bindingFragment.partialMainActivityInclude.root
        bindingPartial = PartialMainActivityBinding.bind(partialView)





        // Change the TextView text
        bindingPartial.textView.text = "Updated Balance"

        return view
    }
}