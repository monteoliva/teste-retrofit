package br.monteoliva.testeretrofit.repository.model.contracts.detail

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Detail (
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("authentication")
    val authentication: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("tType")
    val tType: String?,
    @SerializedName("to")
    val to: String?
) : Parcelable