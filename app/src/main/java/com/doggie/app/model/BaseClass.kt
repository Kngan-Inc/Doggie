package com.doggie.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BaseClass<T>(
    @SerializedName("message")
    val message: T,
    val status: String
)