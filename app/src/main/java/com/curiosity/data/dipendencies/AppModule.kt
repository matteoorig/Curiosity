package com.curiosity.data.dipendencies

/**
 * @author matteooriggi
 */

import com.curiosity.data.AuthRepositoryImpl
import com.curiosity.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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
}