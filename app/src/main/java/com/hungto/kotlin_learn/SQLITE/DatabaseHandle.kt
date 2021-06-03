package com.hungto.kotlin_learn.SQLITE

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandle(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "EMPLOYEEDATABASE"
        private val DATABASE_TABLE = "EMPLOYEETABLE"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACT_TABLE =
            ("CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE)
        onCreate(db)
    }

    //TODO insert data
    fun addDataEmployee(employeeModel: EmployeeModel): Long {
        val  db=this.writableDatabase
        val  contentValues=ContentValues()
        contentValues.put(KEY_ID,employeeModel.userId)
        contentValues.put(KEY_NAME,employeeModel.userName)
        contentValues.put(KEY_EMAIL,employeeModel.userEmail)

        //insert row
        val success=db.insert(DATABASE_TABLE,null,contentValues)

        //close database connect

        db.close()
        return success
    }
    //todo read data
    fun viewDataEmployee():List<EmployeeModel>{
        val empList:ArrayList<EmployeeModel> = ArrayList<EmployeeModel>()
        val  selectQuery="select *from $DATABASE_TABLE"
        val db=this.readableDatabase
        var  cusor:Cursor?=null
        try {
            cusor=db.rawQuery(selectQuery,null)
        }catch (e:SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userid:Int
        var userName:String
        var userEmail:String
        if(cusor.moveToFirst()){
            do {
                userid=cusor.getInt(cusor.getColumnIndex("id"))
                userName=cusor.getString(cusor.getColumnIndex("name"))
                userEmail=cusor.getString(cusor.getColumnIndex("email"))
                val emp=EmployeeModel(userId= userid,userName =userName,userEmail =userEmail)
                empList.add(emp)
            }while (cusor.moveToNext())
        }
        return empList
    }
    //todo update data
    fun updateEmployee(employeeModel: EmployeeModel):Int{
        val db=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(KEY_ID,employeeModel.userId)
        contentValues.put(KEY_NAME,employeeModel.userName)
        contentValues.put(KEY_EMAIL,employeeModel.userEmail)

        //updating row
        val  success=db.update(DATABASE_TABLE,contentValues,"id="+employeeModel.userId,null)
        db.close()
        return success
    }
    //todo menthod deleteEmployee
    fun deleteEmployee(employeeModel: EmployeeModel):Int{
        val db=this.writableDatabase
        val contentValues=ContentValues();
        contentValues.put(KEY_ID,employeeModel.userId)

        //update row
        val  success=db.delete(DATABASE_TABLE,"id="+employeeModel.userId,null)
        db.close()
        return success
    }

}

