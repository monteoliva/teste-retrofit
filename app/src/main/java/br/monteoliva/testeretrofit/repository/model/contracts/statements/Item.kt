package br.monteoliva.testeretrofit.repository.model.contracts.statements

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Item(
    @SerializedName("amount")
    val amount: Int?,
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