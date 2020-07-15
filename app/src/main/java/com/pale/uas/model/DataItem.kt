package com.pale.uas.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataItem:Serializable {

    @field:SerializedName("name")
    val staffName: String? = null

    @field:SerializedName("id")
    val staffId: String? = null

    @field:SerializedName("phone")
    val staffHp: String? = null

    @field:SerializedName("address")
    val staffAlamat: String? = null
}