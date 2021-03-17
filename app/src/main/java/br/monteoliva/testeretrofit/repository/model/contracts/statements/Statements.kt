package br.monteoliva.testeretrofit.repository.model.contracts.statements

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Statements (
    @SerializedName("items")
    val items: MutableList<Item>? = null
) : Parcelable