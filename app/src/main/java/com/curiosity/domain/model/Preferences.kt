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
         * Convert parsed strings of preference values and interests into a list of Preferences objects.
         *
         * @param preferenceValuesString The string of preference values separated by ';'.
         * @param interestsString The string of interest values separated by ';'.
         * @return A list of Preferences objects.
         */
        fun fromParsedStrings(preferenceValuesString: String, interestsString: String): List<Preferences> {
            if (preferenceValuesString.isEmpty() && interestsString.isEmpty()) {
                return emptyList()
            }
            val preferenceValues = preferenceValuesString.split(";")
            val interests = interestsString.split(";").map { it.toInt() }

            if (preferenceValues.size != interests.size) {
                throw IllegalArgumentException("Preference values and interests must have the same number of elements")
            }

            return preferenceValues.zip(interests).map { (value, interest) ->
                Preferences(preferenceValue = value, interest = interest)
            }
        }

        /**
         * Convert a list of Preferences objects to a single string of their preference values.
         *
         * @param preferencesList The list of Preferences objects to be converted.
         * @return A string containing the preference values separated by ';'.
         */
        fun toStringValuesParsed(preferencesList: List<Preferences>): String {
            val str: String = preferencesList.joinToString(separator = ";") { it.preferenceValue }
            return str
        }

        /**
         * Convert a list of Preferences objects to a single string of their interest values.
         *
         * @param preferencesList The list of Preferences objects to be converted.
         * @return A string containing the interest values separated by ';'.
         */
        fun toStringInterestsParsed(preferencesList: List<Preferences>): String {
            val str: String = preferencesList.joinToString(separator = ";") { it.interest.toString() }
            return str
        }
    }
}
