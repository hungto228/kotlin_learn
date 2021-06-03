package com.hungto.kotlin_learn.NOTEAPP

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hungto.kotlin_learn.R
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {
        @JvmStatic
        fun newIntance() = CreateNoteFragment().apply {
            arguments = Bundle().apply { }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentdate = date.format(Date())
        tv_datetime.text = currentdate
        img_check.setOnClickListener {
            savenote()
        }
        img_back.setOnClickListener {
            replaypFragment(NoteHomeFragment.newIntance(), false)
        }

    }

    private fun savenote() {
        if (edt_note_title.text.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Tiêu đề không được trống", Toast.LENGTH_SHORT).show()
        }
        if (edt_notDesc.text.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Nội dung không được trống", Toast.LENGTH_SHORT).show()
        }
        if (edt_subTitle.text.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Phụ đề không được trống", Toast.LENGTH_SHORT).show()
        }
        launch { }
    }

    fun replaypFragment(fragment: Fragment, isTransition: Boolean) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        if (isTransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
        }
        fragmentTransition.replace(R.id.frame_home, fragment)
            .addToBackStack(fragment.javaClass.simpleName)
    }
}