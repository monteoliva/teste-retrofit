package br.monteoliva.testeretrofit.view.detail

import br.monteoliva.testeretrofit.R
import br.monteoliva.testeretrofit.view.BaseActivity

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