package com.curiosity.data.model

/**
 * @author matteooriggi
 */

data class Preferences(
    val preferenceValue: String,
    val interest: Int = 0
){
    companion object {
        fun getHashMap(preferencesList: List<Preferences>): HashMap<String, Map<String, Any>> {

            val preferencesHashMap: HashMap<String, Map<String, Any>> = HashMap()
            preferencesList.forEach { preference ->
                preferencesHashMap[preference.preferenceValue] = mapOf(
                    "preferenceValue" to preference.preferenceValue,
                    "interest" to preference.interest
                )
            }
            return preferencesHashMap
        }
    }
}
