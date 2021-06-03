package com.hungto.kotlin_learn.SQLITE


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.hungto.kotlin_learn.R
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.activity_sqlite_curd.*


class sqliteCurd : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_curd)
        btn_save.setOnClickListener {
            saveRecord()
        }
        btn_view.setOnClickListener {
            viewRecord()
        }
        btn_update.setOnClickListener {
            updateRecord()
        }
        btn_delete.setOnClickListener {
            deleteRecord()
        }
    }

    //menthod saving in database
    fun saveRecord() {
        val id = edt_userid.text.toString()
        val name = edt_username.text.toString()
        val email = edt_useremail.textColors.toString()
        val databaseHandle: DatabaseHandle = DatabaseHandle(this)
        if (!id.isEmpty() && !name.isEmpty() && !email.isEmpty()) {

            val status =
                databaseHandle.addDataEmployee(EmployeeModel(Integer.parseInt(id), name, email))
            if (status > -1) {
                edt_userid.clear()
                edt_username.clear()
                edt_useremail.clear()

            }
        } else {
            Toast.makeText(applicationContext, "nhap du thong tin", Toast.LENGTH_SHORT).show()
        }
    }

    //method read record from database
    fun viewRecord() {
        val databaseHandle: DatabaseHandle = DatabaseHandle(this)

        val employeeModellist: List<EmployeeModel> = databaseHandle.viewDataEmployee()
        val empArrayId = Array<String>(employeeModellist.size) { "0" }
        val empArrayName = Array<String>(employeeModellist.size) { "Null" }
        val empArrayEmail = Array<String>(employeeModellist.size) { "null" }
        var index = 0
        for (e in employeeModellist) {
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail
            index++
        }
        val adapterEmployee = AdapterEmployee(this, empArrayId, empArrayName, empArrayName)
        lv_lisst.adapter = adapterEmployee
    }

    fun updateRecord() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView =
            LayoutInflater.from(this).inflate(R.layout.update_curdsqlite_dialog, null)

        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById<MaterialEditText>(R.id.edt_userid)
        val edt_name = dialogView.findViewById<MaterialEditText>(R.id.edt_username)
        val edtmail = dialogView.findViewById<MaterialEditText>(R.id.edt_useremail)

        dialogBuilder.setTitle("update record")
        dialogBuilder.setMessage("enter data beblow")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->
            val updateId = edtId.text.toString()
            val updatename = edt_name.text.toString()
            val updateEmail = edtmail.text.toString()
            //creating the instance
            val databaseHandle: DatabaseHandle = DatabaseHandle(this)
            if (!updateId.isEmpty() && !updateEmail.isEmpty() && !updatename.isEmpty()) {
                val status = databaseHandle.updateEmployee(
                    EmployeeModel(
                        Integer.parseInt(updateId),
                        updatename,
                        updateEmail
                    )
                )
                if (status > -1) {
                    Toast.makeText(this, "update thanh cong", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "nhap du thong tin", Toast.LENGTH_SHORT).show()
            }
        })
        dialogBuilder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> })
        val b = dialogBuilder.create()
        b.show()
    }

    fun deleteRecord() {
        //create alerdilog
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater =
            LayoutInflater.from(this).inflate(R.layout.delete_curdsqlite_dialog, null)
        dialogBuilder.setView(inflater)
        val editId = inflater.findViewById<MaterialEditText>(R.id.edt_userid)
        dialogBuilder.setTitle("delete record")
        dialogBuilder.setMessage("enter id below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->
            val deleteId = editId.text.toString()

            //create instance database
            val databaseHandle: DatabaseHandle = DatabaseHandle(this)
            if (!deleteId.isEmpty()) {
                val status =
                    databaseHandle.deleteEmployee(EmployeeModel(Integer.parseInt(deleteId), "", ""))
                if (status > -1) {
                    Toast.makeText(this, "delete success", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "dia chi khong duwocj trong", Toast.LENGTH_SHORT).show()
            }
        })
        dialogBuilder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> })
        val b = dialogBuilder.create()
        b.show()
    }

}

private fun MaterialEditText.clear() {
    text?.clear()
}

