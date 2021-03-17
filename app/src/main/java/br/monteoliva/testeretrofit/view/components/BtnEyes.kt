package br.monteoliva.testeretrofit.view.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat

import br.monteoliva.testeretrofit.R

class BtnEyes(context: Context, private val attrs: AttributeSet) : FrameLayout(context, attrs) {
    private var drawableOn: Drawable? = null
    private var drawableOff: Drawable? = null
    private var isOn: Boolean = true

    var onClick: ((Boolean) -> Unit)? = null

    init {
        initViews()
    }

    private fun initViews() {
        context?.let {
            setBackgroundColor(ContextCompat.getColor(it, android.R.color.transparent))

            drawableOn  = ContextCompat.getDrawable(it, R.drawable.ic_eye_on)
            drawableOff = ContextCompat.getDrawable(it, R.drawable.ic_eye_off)

            LayoutInflater.from(it).inflate(R.layout.btn_eyes, this).apply {
                findViewById<ImageView>(R.id.btnEyes).apply {
                    isClickable = true
                    setOnClickListener {
                        val image = if (isOn) drawableOff else drawableOn
                        this.setImageDrawable(image)
                        isOn = !isOn
                        onClick?.invoke(isOn)
                    }
                }
            }
        }
    }
}