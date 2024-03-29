package br.monteoliva.testeretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import br.monteoliva.testeretrofit.repository.core.RepositoryServer
import br.monteoliva.testeretrofit.repository.model.contracts.balance.Balance
import br.monteoliva.testeretrofit.repository.model.contracts.statements.Item

class MainViewModel(private val repositoryResponse: RepositoryServer) : ViewModel() {
    var myBalance: MutableLiveData<Balance> = MutableLiveData()
    var myStatements: MutableLiveData<Boolean> = MutableLiveData()

    private var itemsList: MutableList<Item> = emptyList<Item>().toMutableList()

    fun getBalance() {
        repositoryResponse.getBalance { myBalance.postValue(it) }
    }

    fun getStatementsLoadFirst(offset: Int) {
        itemsList.clear()
        loadList(offset, true)
    }

    fun getStatementsLoadUpdate(offset: Int) {
        loadList(offset, false)
    }

    private fun loadList(offset: Int, isFirst: Boolean) {
        repositoryResponse.getStatements(offset) { items ->
            items.items?.let {
                itemsList.addAll(it)
            }
            myStatements.postValue(isFirst)
        }
    }

    val listItems: MutableList<Item>  get() = itemsList
}