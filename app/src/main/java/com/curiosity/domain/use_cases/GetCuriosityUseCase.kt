package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.CuriosityData
import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.StorageRepository
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

/**
 * Use case for retrieving a random curiosity from a dataset stored in Firebase.
 *
 * @property storageRepository The repository for accessing the storage data.
 */
class GetCuriosityUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {

    /**
     * Executes the use case to retrieve a random curiosity.
     *
     * @return A Flow emitting the resource state of the operation.
     */
    operator fun invoke(): Flow<Resource<CuriosityData>> = flow {

        try {
            emit(Resource.Loading<CuriosityData>())

            val bytes: ByteArray = storageRepository.getCuriositiesDataset()
            val curiosities = readCSVFile(bytes)

            if (curiosities != null) {
                val curiosity = getRandomCuriosity(curiosities)
                emit(Resource.Success<CuriosityData>(data = curiosity))
            } else {
                emit(Resource.Error<CuriosityData>("Server error on reading curiosities"))
            }

        }catch (e: Exception){
            emit(Resource.Error<CuriosityData>("GetCuriosityUseCase" + e.message))
        }
    }.flowOn(Dispatchers.IO)

    /**
     * Reads the CSV file from the provided byte array and parses it into a list of CuriosityData.
     *
     * @param bytes The byte array representing the CSV file.
     * @return A list of CuriosityData or null if an error occurs.
     */
    private fun readCSVFile(bytes: ByteArray): List<CuriosityData>? {
         try {
            val inputStream: InputStream = ByteArrayInputStream(bytes)
            val reader = CSVReader(InputStreamReader(inputStream))
             reader.readNext()
            val curiosities = reader.readAll().map { line ->
                CuriosityData(
                    idx = line[0].toInt(),
                    category = line[1].toString(),
                    value = line[2].toString()
                )
            }
            reader.close()
            return curiosities
        } catch (e: Exception) {
            return null
        }
    }

    /**
     * Selects a random curiosity from the list.
     *
     * @param curiosities The list of curiosities.
     * @return A random CuriosityData object.
     */
    private fun getRandomCuriosity(curiosities: List<CuriosityData>): CuriosityData {
        return curiosities.random()
    }
}