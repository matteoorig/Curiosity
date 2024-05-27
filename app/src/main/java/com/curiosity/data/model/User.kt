package com.curiosity.data.model

data class User(
    val uuid: String? = "",
    val username: String? = "",
    val email: String? = "",
    val password: String? = "",
    val level: Int = 1,
    val coins: Int = 0,
    val preferences: List<Preferences>
) {

    /**
     * This method is created for accept a single user or an empty user
     */
    fun getHashMap(user: User? = null): HashMap<String, Any> {
        // define hash map
        val hashMap: HashMap<String, Any> = hashMapOf<String, Any>()
        // getting the field of User data class
        val properties = User::class.java.declaredFields
        // for ech field i add the correct value of the property in the hash map
        properties.forEach { property ->
            if (property.name != "\$stable") {
                property.isAccessible = true
                hashMap[property.name] = property.get(user ?: this) ?: ""
            }
        }
        return hashMap
    }
}

