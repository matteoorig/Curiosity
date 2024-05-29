package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import android.content.Context
import com.curiosity.R
import com.curiosity.domain.model.CuriosityAreasOfInterestItemData
import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.DataRepository
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use case for loading areas of interest categories.
 *
 * This use case handles the process of loading areas of interest categories from the database.
 * It interacts with the DataRepository to fetch the data and processes it into a list of
 * CuriosityAreasOfInterestItemData. The state of the operation is managed using Flow<Resource<List<CuriosityAreasOfInterestItemData>>>.
 *
 * @param dataRepository The DataRepository used for database operations.
 * @param context The context used for accessing resources.
 */
class LoadAreasCategoriesUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val context: Context
) {
    operator fun invoke(): Flow<Resource<List<CuriosityAreasOfInterestItemData>>> = flow {
        try {
            // Initialize an empty list of CuriosityAreasOfInterestItemData.
            var list: List<CuriosityAreasOfInterestItemData> = emptyList()

            emit(Resource.Loading<List<CuriosityAreasOfInterestItemData>>())

            // Load the areas of interest categories from the repository.
            val snapshot: QuerySnapshot? = dataRepository.loadAreasOfInterestCategories()
            if(snapshot != null){
                var count = 0
                val list = mutableListOf<CuriosityAreasOfInterestItemData>()
                // For each document in Firestore "areas_of_interest" collection.
                snapshot.documents.mapNotNull { document ->

                    // Extract the value and icon drawable string from the document.
                    val areaValue = document.getString("value")!!
                    val areaIconDrawable = document.getString("icon_value")!!
                    val iconDrawableId = CuriosityAreasOfInterestItemData.getIconResId(context, areaIconDrawable)

                    // Add the item data to the list.
                    list.add(
                        CuriosityAreasOfInterestItemData(
                            idx = list.size,
                            value = areaValue,
                            icon = iconDrawableId
                        )
                    )
                }

                if(list.isNotEmpty()){
                    emit(Resource.Success<List<CuriosityAreasOfInterestItemData>>(data = list))
                }else {
                    emit(Resource.Error<List<CuriosityAreasOfInterestItemData>>("LoadAreasCategoriesUseCase" + " There are no Areas Of Interest Categories"))
                }
            }else{
                emit(Resource.Error<List<CuriosityAreasOfInterestItemData>>("LoadAreasCategoriesUseCase" + " Error reading Areas Of Interest Categories"))
            }
        }catch (e: Exception){
            emit(Resource.Error<List<CuriosityAreasOfInterestItemData>>("LoadAreasCategoriesUseCase" + e.message))
        }
    }.flowOn(Dispatchers.IO)
}