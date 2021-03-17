package br.monteoliva.testeretrofit.ui.feature.detail

import br.monteoliva.testeretrofit.R
import br.monteoliva.testeretrofit.ui.feature.BaseActivity

class DetailActivity : BaseActivity(R.layout.activity_detail) {

    override fun initViews() {
    }

    override fun initViewModel() {
    }


    override fun back() {
        finish()
        animRightToLeft()
    }

    companion object {
        private const val TAG = "DetailActivity"
        const val ITEM = "itemId"
    }
}