package br.monteoliva.testeretrofit.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

import br.monteoliva.testeretrofit.R
import br.monteoliva.testeretrofit.core.extensions.invisible
import br.monteoliva.testeretrofit.core.extensions.visible

class Balance(context: Context, private val attrs: AttributeSet) : FrameLayout(context, attrs) {
    private var txtBalanceOn: TextView? = null
    private var txtBalanceOff: ImageView? = null

    init {
        initViews()
    }

    private fun initViews() {
        context?.let {
            setBackgroundColor(ContextCompat.getColor(it, android.R.color.transparent))

            LayoutInflater.from(it).inflate(R.layout.balance, this).apply {
                txtBalanceOn  = findViewById(R.id.txtBalanceOn)
                txtBalanceOff = findViewById(R.id.txtBalanceOff)
            }
        }
    }

    fun setBalanceValue(balance: String) { txtBalanceOn?.text = balance }

    fun showHide(hasShow: Boolean) {
        if (hasShow) { showBalance() }
        else         { hideBalance() }
    }

    private fun showBalance() {
        txtBalanceOff?.invisible()
        txtBalanceOn?.visible()
    }

    private fun hideBalance() {
        txtBalanceOn?.invisible()
        txtBalanceOff?.visible()
    }
}