package com.hungto.kotlin_learn.FragmentMain

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hungto.kotlin_learn.R
import com.hungto.kotlin_learn.SQLITE.sqliteCurd
import kotlinx.android.synthetic.main.fragment_explore.*

class ExploreFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        //initalizal
       btn_curd_sqlite.setOnClickListener {
           val intent=Intent(requireContext(),sqliteCurd::class.java)
           startActivity(intent)
       }
        return root
    }

    companion object {
        @JvmStatic
        fun newIntance() = ExploreFragment().apply {
            arguments = Bundle().apply { }
        }
    }



}