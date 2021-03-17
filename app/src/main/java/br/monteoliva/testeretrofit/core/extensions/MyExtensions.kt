package br.monteoliva.testeretrofit.core.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

import android.view.View

fun Int.balanceFormat() : String {
    val symbols  = DecimalFormatSymbols(Locale("pt", "BR"))
    val fDecimal = DecimalFormat("###,##0.00", symbols)

    return "R$ ${fDecimal.format(this.toDouble())}"
}

fun String.dateFormat() : String {
    val locale = Locale("pt", "BR")
    val f0 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)
    val f1 = SimpleDateFormat("dd/MM", locale)
    val date = this.replace("T", " ")

    try {
        GregorianCalendar(locale).apply {
            f0.parse(date)?.let { time = it }
            return f1.format(time)
        }
    }
    catch (pe: ParseException) {}

    return ""
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}