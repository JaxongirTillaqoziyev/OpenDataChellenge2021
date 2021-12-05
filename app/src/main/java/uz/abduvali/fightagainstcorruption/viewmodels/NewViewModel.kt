package uz.abduvali.fightagainstcorruption.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.abduvali.fightagainstcorruption.database.entity.NewEntity
import uz.abduvali.fightagainstcorruption.repository.NewDataRepository
import uz.abduvali.fightagainstcorruption.utils.NetworkHelper

class NewViewModel(
    private val newDataRepository: NewDataRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    fun getNewsData(query: String): StateFlow<NewDataResource> {
        val stateFlow = MutableStateFlow<NewDataResource>(NewDataResource.Loading)
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                val flow = newDataRepository.getNews(query)
                flow.catch {
                    stateFlow.emit(NewDataResource.Error(it.message ?: ""))
                }.collect {
                    if (it.isSuccessful) {
                        val body = it.body()
                        val list = ArrayList<NewEntity>()
                        body?.articles?.forEach { article ->
                            val newData = NewEntity(
                                article.url ?: "",
                                article.author ?: "",
                                article.content ?: "",
                                article.description ?: "",
                                article.title ?: "",
                                article.source?.name ?: "",
                                article.urlToImage ?: ""
                            )
                            list.add(newData)
                            newDataRepository.addNew(newData)
                        }
                        stateFlow.emit(NewDataResource.Success(list))
                    }
                }
            } else {
                newDataRepository.getDbNews()
                    .collect {
                        if (it.isNotEmpty()) {
                            stateFlow.emit(NewDataResource.Success(it))
                        } else {
                            stateFlow.emit(NewDataResource.Error("No internet connection"))
                        }
                    }
            }
        }
        return stateFlow
    }
}