package com.hungto.kotlin_learn.FragmentMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hungto.kotlin_learn.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newIntance() = HomeFragment().apply {
            arguments = Bundle().apply { }
        }
    }


}