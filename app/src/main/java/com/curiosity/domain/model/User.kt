package com.curiosity.domain.model

/**
 * @author matteooriggi
 */

data class User(
    var uuid: String? = "",
    var username: String? = "",
    var email: String? = "",
    var password: String? = "",
    var level: Int = 1,
    var coins: Int = 0,
    var preferences: List<Preferences> = emptyList()
) {

    /**
     * This method is created for accepting a single user or an empty user
     */
    fun getHashMap(user: User? = null): HashMap<String, Any> {
        // Define hash map
        val hashMap: HashMap<String, Any> = hashMapOf()
        // Getting the fields of User data class
        val properties = User::class.java.declaredFields
        // For each field, add the correct value of the property in the hash map
        properties.forEach { property ->
            if (property.name != "\$stable" && property.name != "preferences") {
                property.isAccessible = true
                hashMap[property.name] = property.get(user ?: this) ?: ""
            }
        }
        // Add preferences with its getHashMap method
        hashMap["preferences"] = Preferences.getHashMap((user ?: this).preferences)
        return hashMap
    }
}

