package com.curiosity.domain.repository

/**
 * @author matteooriggi
 */

import com.google.firebase.storage.FirebaseStorage

/**
 * Interface for managing storage operations.
 */
interface StorageRepository {

    /**
     * The instance of FirebaseStorage used for storage operations.
     */
    val storage: FirebaseStorage

    /**
     * Retrieves the dataset of curiosities as a ByteArray.
     *
     * @return A ByteArray containing the dataset of curiosities.
     */
    suspend fun getCuriositiesDataset(): ByteArray
}