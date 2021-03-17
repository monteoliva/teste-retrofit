package br.monteoliva.testeretrofit.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.activity_main.*

import br.monteoliva.testeretrofit.R
import br.monteoliva.testeretrofit.core.extensions.balanceFormat
import br.monteoliva.testeretrofit.core.extensions.gone
import br.monteoliva.testeretrofit.core.extensions.visible
import br.monteoliva.testeretrofit.core.preferences.SharedPreferencesImpl
import br.monteoliva.testeretrofit.view.BaseActivity
import br.monteoliva.testeretrofit.view.adapter.ItemAdapter
import br.monteoliva.testeretrofit.view.detail.DetailActivity
import br.monteoliva.testeretrofit.view.pagnation.PaginationListener
import br.monteoliva.testeretrofit.viewmodel.MainViewModel

class MainActivity : BaseActivity(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModel()
    private var preferences: SharedPreferencesImpl by Delegates.notNull()
    private var mAdapter: ItemAdapter by Delegates.notNull()

    private var offset: Int = 0
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    override fun initViews() {
        baseContext?.let {
            preferences = SharedPreferencesImpl(it)
        }

        btnEyesAction.onClick = { hasShow -> balanceAction.showHide(hasShow) }
    }

    override fun initViewModel() {
        mainViewModel.apply {
            getBalance()
            getStatementsLoadFirst(offset)
            myBalance.observe(this@MainActivity, {
                it.amount?.balanceFormat().apply {
                    preferences.setValue("BALANCE", this.toString())
                    balanceAction.setBalanceValue(this.toString())
                }
            })
            myStatements.observe(this@MainActivity, { isFirst ->
                if (isFirst) { loadMyStatements() }
                else {
                    mAdapter.updateList(listItems)
                }
            })
        }
    }

    private fun loadMyStatements() {
        val mLayoutManager = GridLayoutManager(this, 1)

        mAdapter = ItemAdapter(this).apply {
            updateList(mainViewModel.listItems)
        }

        findViewById<RecyclerView>(R.id.rv).apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mAdapter
            addOnScrollListener(object : PaginationListener(mLayoutManager) {
                override fun loadMoreItems() {
                    isLoading = true
                    retrieveList()
                }

                override fun isLastPage(): Boolean = isLastPage
                override fun isLoading(): Boolean  = isLoading
            })
        }
    }

    private fun retrieveList() {
        offset += 1
        mainViewModel.getStatementsLoadUpdate(offset)
    }

    fun detail(id: String) {
        Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.ITEM, id)
            startActivity(this)
        }

        animLeftToRight()
    }

    fun setLoading(hasShow: Boolean) {
        progressBar.apply {
            if (hasShow) { this.visible() }
            else         { this.gone() }
        }
    }

    override fun back() { finish() }

    companion object {
        const val TAG = "MainActivity"
    }


}