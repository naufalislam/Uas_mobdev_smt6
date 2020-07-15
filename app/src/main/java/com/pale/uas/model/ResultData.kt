package com.pale.uas.model

import com.google.gson.annotations.SerializedName

class ResultData(
    @field:SerializedName("error")
    val pesan: String? = null,

    @field:SerializedName("person")
    val staff: List<DataItem>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)