package com.hungto.kotlin_learn.SQLITE

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.hungto.kotlin_learn.R

class AdapterEmployee(
    private val context: Activity,
    private val id: Array<String>,
    private val name: Array<String>,
    private val email: Array<String>
) : ArrayAdapter<String>(context, R.layout.custom_itemview_curdsql, name) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val root = inflater.inflate(R.layout.custom_itemview_curdsql, null, false)

        val idText = root.findViewById<TextView>(R.id.tv_id)
        val nameTextView = root.findViewById<TextView>(R.id.tv_username)
        val emailTextView = root.findViewById<TextView>(R.id.tv_email)

        idText.text = "Id:${id[position]}"
        nameTextView.text = "Name:${name[position]}"
        emailTextView.text = "Email:${email[position]}"

        return root
    }
}

