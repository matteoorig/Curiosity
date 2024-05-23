package com.curiosity.domain.model

/**
 * @author matteooriggi
 */

/**
 * A sealed class that represents the state of a resource that can be loaded.
 *
 * This class can be used to represent the state of data being loaded, including
 * successful loading, error states, and loading states.
 *
 * @param T The type of data of this resource.
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    /**
     * Represents a successful state where the resource has been loaded successfully.
     *
     * @param T The type of data.
     * @param data The data that has been loaded.
     */
    class Success<T>(data: T? = null) : Resource<T>(data)

    /**
     * Represents an error state where the resource loading has failed.
     *
     * @param T The type of data.
     * @param message The error message.
     * @param data Optional data.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    /**
     * Represents a loading state where the resource is currently being loaded.
     *
     * @param T The type of data.
     * @param data Optional data.
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
