package uz.abduvali.fightagainstcorruption.viewmodels

import uz.abduvali.fightagainstcorruption.database.entity.NewEntity

sealed class NewDataResource {

    object Loading : NewDataResource()
    data class Success(val list: List<NewEntity>) : NewDataResource()
    data class Error(val message: String) : NewDataResource()
}