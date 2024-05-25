package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.CuriosityAreasOfInterestItemData
import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.DataRepository
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoadAreasCategoriesUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    operator fun invoke(): Flow<Resource<List<CuriosityAreasOfInterestItemData>>> = flow {
        try {
            var list: List<CuriosityAreasOfInterestItemData> = emptyList()
            emit(Resource.Loading<List<CuriosityAreasOfInterestItemData>>())

            val snapshot: QuerySnapshot? = dataRepository.loadAreasOfInterestCategories()
            if(snapshot != null){
                list = snapshot.documents.mapNotNull { document ->
                    document.toObject(CuriosityAreasOfInterestItemData::class.java)
                }

                if(list.isNotEmpty()){

                }else {
                    // TODO: catch error when list is empty
                    emit(Resource.Error<List<CuriosityAreasOfInterestItemData>>("LoadAreasCategoriesUseCase" + " "))
                }
            }else{
                emit(Resource.Error<List<CuriosityAreasOfInterestItemData>>("LoadAreasCategoriesUseCase" + " Error reading Areas Of Interest Categories"))

            }
        }catch (e: Exception){
            emit(Resource.Error<List<CuriosityAreasOfInterestItemData>>("LoadAreasCategoriesUseCase" + e.message))

        }
    }.flowOn(Dispatchers.IO)
}