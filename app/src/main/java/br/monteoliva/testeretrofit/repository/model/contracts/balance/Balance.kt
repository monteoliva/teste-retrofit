package br.monteoliva.testeretrofit.repository.model.contracts.balance

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Balance (@SerializedName("amount") val amount: Int?) : Parcelable