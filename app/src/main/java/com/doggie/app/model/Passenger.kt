package com.doggie.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PassengersResponse(
    val data: List<Passenger>,
    val totalPages: Int,
    val totalPassengers: Int
)

@Serializable
data class Passenger(
    val __v: Int,
    val _id: String,
    val airline: Airline,
    val name: String,
    val trips: Int
)

@Serializable
data class Airline(
    val country: String,
    val established: String,
    @SerializedName("head_quaters")
    val headQuaters: String,
    val id: Int,
    val logo: String,
    val name: String,
    val slogan: String,
    val website: String
)
