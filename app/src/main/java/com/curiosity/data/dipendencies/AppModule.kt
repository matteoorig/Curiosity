package com.curiosity.data.dipendencies

/**
 * @author matteooriggi
 */

import android.content.Context
import com.curiosity.data.AuthRepositoryImpl
import com.curiosity.data.DataRepositoryImpl
import com.curiosity.data.SharedPreferencesRepositoryImpl
import com.curiosity.domain.repository.AuthRepository
import com.curiosity.domain.repository.DataRepository
import com.curiosity.domain.repository.SharedPreferencesRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module that provides dependencies for the application.
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    /**
     * Provides an instance of FirebaseAuth.
     *
     * @return The FirebaseAuth instance.
     */
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Provides an implementation of AuthRepository.
     *
     * @param impl The implementation of AuthRepository.
     * @return The AuthRepository instance.
     */
    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    /**
     * Provides an instance of FirebaseFirestore.
     *
     * @return The FirebaseFirestore instance.
     */
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    /**
     * Provides an implementation of DataRepository.
     *
     * @param impl The implementation of DataRepository.
     * @return The DataRepository instance.
     */
    @Provides
    fun provideDataRepository(impl: DataRepositoryImpl): DataRepository = impl

    /**
     * Provides the application context.
     *
     * @param context The application context.
     * @return The application context.
     */
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun provideSharedPreferences(impl: SharedPreferencesRepositoryImpl): SharedPreferencesRepository = impl
}