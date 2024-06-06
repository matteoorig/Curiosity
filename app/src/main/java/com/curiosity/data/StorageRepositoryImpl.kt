package com.curiosity.data

/**
 * @author matteooriggi
 */

import com.curiosity.common.CuriosityUrl
import com.curiosity.domain.repository.StorageRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of the StorageRepository interface for managing Firebase storage operations.
 *
 * @property firebaseStorage The FirebaseStorage instance used for storage operations.
 */
@Singleton
class StorageRepositoryImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage
): StorageRepository {

    /**
     * The instance of FirebaseStorage used for storage operations.
     */
    override val storage: FirebaseStorage get() = firebaseStorage

    /**
     * Retrieves the dataset of curiosities from Firebase storage as a ByteArray.
     *
     * @return A ByteArray containing the dataset of curiosities.
     */
    override suspend fun getCuriositiesDataset(): ByteArray {
        val curiositiesGsReference = storage.getReferenceFromUrl(CuriosityUrl.storageCuriositiesDataset)
        return curiositiesGsReference.getBytes(Long.MAX_VALUE).await()
    }

}



