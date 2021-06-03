package com.hungto.kotlin_learn.NOTEAPP.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Notes")
class Notes : Serializable {
    //id, name, title, sub_title,datatime,note_text,img_path,web_link,color
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "sub_title")
    var sub_title: String? = null

    @ColumnInfo(name = "date_time")
    var date_time: String? = null

    @ColumnInfo(name = "note_text")
    var note_text: String? = null

    @ColumnInfo(name = "img_path")
    var img_path: String? = null

    @ColumnInfo(name = "web_link")
    var web_link: String? = null

    @ColumnInfo(name = "color")
    var color: String? = null

    override fun toString(): String {
        return "$title:$date_time"
    }


}