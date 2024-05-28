package com.curiosity.domain.model

/**
 * @author matteooriggi
 */

data class Preferences(
    val preferenceValue: String,
    var interest: Int = 0
) {
    companion object {
        /**
         * Convert a list of Preferences objects to a HashMap.
         *
         * @param preferencesList The list of Preferences objects to be converted.
         * @return A HashMap where each key is a preference value and the value is a map with the preference details.
         */
        fun getHashMap(preferencesList: List<Preferences>): HashMap<String, Map<String, Any>> {
            val preferencesHashMap: HashMap<String, Map<String, Any>> = HashMap()

            preferencesList.forEach { preference ->
                // The key is the preference value
                // The value is a map containing the preference's properties
                preferencesHashMap[preference.preferenceValue] = mapOf(
                    "preferenceValue" to preference.preferenceValue,
                    "interest" to preference.interest
                )
            }
            return preferencesHashMap
        }

        /**
         * Convert a HashMap to a list of Preferences objects.
         *
         * @param preferencesHashMap The HashMap to be converted.
         * @return A list of Preferences objects created from the HashMap.
         */
        fun fromHashMap(preferencesHashMap: HashMap<String, Map<String, Any>>): List<Preferences> {
            val preferencesList = mutableListOf<Preferences>()

            preferencesHashMap.forEach { (_, value) ->
                // Extract the preferenceValue and interest from the map
                // Use safe casting and provide default values in case of type mismatch or missing keys
                val preferenceValue = value["preferenceValue"] as? String ?: ""
                val interest = (value["interest"] as? Int) ?: 0

                preferencesList.add(Preferences(preferenceValue, interest))
            }
            return preferencesList
        }

        /**
         * Convert two lists of strings to a list of Preferences objects.
         *
         * @param preferenceValues The list of strings containing preference values.
         * @param interests The list of strings containing interests.
         * @return A list of Preferences objects created from the provided values.
         */
        fun fromStringLists(preferenceValues: List<String>, interests: List<String>): List<Preferences> {
            if (preferenceValues.size != interests.size) {
                throw IllegalArgumentException("The size of preferenceValues and interests lists must be the same")
            }

            val preferencesList = mutableListOf<Preferences>()

            for (i in preferenceValues.indices) {
                val interest = interests[i].toIntOrNull() ?: 0
                preferencesList.add(Preferences(preferenceValues[i], interest))
            }

            return preferencesList
        }

        /**
         * Convert a list of Preferences objects to a list of their preference values.
         *
         * @param preferencesList The list of Preferences objects to be converted.
         * @return A list of strings containing the preference values.
         */
        fun toStringValueList(preferencesList: List<Preferences>): List<String> {
            return preferencesList.map { it.preferenceValue }
        }

        /**
         * Convert a list of Preferences objects to a list of their interest values.
         *
         * @param preferencesList The list of Preferences objects to be converted.
         * @return A list of strings containing the interest.
         */
        fun toStringInterestList(preferencesList: List<Preferences>): List<String> {
            return preferencesList.map { it.interest.toString() }
        }
    }
}
