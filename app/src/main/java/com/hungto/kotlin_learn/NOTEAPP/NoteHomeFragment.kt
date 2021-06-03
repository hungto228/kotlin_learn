package com.hungto.kotlin_learn.NOTEAPP

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hungto.kotlin_learn.R
import kotlinx.android.synthetic.main.fragment_note_home.*


class NoteHomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_home, container, false)
    }

    companion object {
        fun newIntance() = NoteHomeFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floating_home.setOnClickListener {
            replayFragment(CreateNoteFragment.newIntance(), true)
        }
    }

    fun replayFragment(fragment: Fragment, istransition: Boolean) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        if (istransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
        }
        fragmentTransition.replace(R.id.frame_home, fragment).addToBackStack(fragment.javaClass.simpleName)
    }
}