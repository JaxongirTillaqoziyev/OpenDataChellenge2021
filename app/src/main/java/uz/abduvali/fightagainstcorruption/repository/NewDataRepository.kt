package uz.abduvali.fightagainstcorruption.repository

import kotlinx.coroutines.flow.flow
import uz.abduvali.fightagainstcorruption.database.dao.NewDao
import uz.abduvali.fightagainstcorruption.database.entity.NewEntity
import uz.abduvali.fightagainstcorruption.networking.ApiService

class NewDataRepository(
    private val apiService: ApiService,
    private val newDao: NewDao
) {

    suspend fun getNews(query: String) = flow { emit(apiService.getNews(query)) }

//    suspend fun addNews(list: List<NewEntity>) = newDao.addNews(list)

    suspend fun addNew(list: NewEntity) = newDao.addNew(list)

    suspend fun getDbNews() = flow { emit(newDao.getNews()) }
}