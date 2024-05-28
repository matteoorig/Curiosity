package com.curiosity.data.model

/**
 * @author matteooriggi
 */

data class User(
    val uuid: String? = "",
    val username: String? = "",
    val email: String? = "",
    val password: String? = "",
    val level: Int = 1,
    val coins: Int = 0,
    val preferences: HashMap<String, Map<String, Any>>
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
        // Add preferences separately since it's already a HashMap
        hashMap["preferences"] = (user ?: this).preferences
        return hashMap
    }
}

