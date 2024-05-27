package com.curiosity.data.model

data class Preferences(
    val preferenceValue: String,
    val interest: Int
){
    fun getHashMap(preference: Preferences? = null): HashMap<String, Any> {
        // define hash map
        val hashMap: HashMap<String, Any> = hashMapOf<String, Any>()
        // getting the field of User data class
        val properties = Preferences::class.java.declaredFields
        // for ech field i add the correct value of the property in the hash map
        properties.forEach { property ->
            if (property.name != "\$stable") {
                property.isAccessible = true
                hashMap[property.name] = property.get(preference ?: this) ?: ""
            }
        }
        return hashMap
    }
}
