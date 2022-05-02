package com.atlassian.androidinterview

class CountryRepository(private val localeProvider: LocaleProvider = AndroidLocaleProvider()) {

    /**
     * @return list of countries sorted by ASC. Each [Country.name] must start with the provided
     * [startsWith] value ignoring upper/lower case.
     */
    fun getFilteredCountries(startsWith: String) = getAllCountries()
            .filter { country -> country.name.startsWith(startsWith, true) }

    /**
     * Should remove an empty name and duplicate name from the list.
     * @return list of countries sorted by ASC.
     */
    fun getAllCountries(): List<Country> {
        val countries = mutableSetOf<Country>()
        for (locale in localeProvider.provideLocales()) {
            countries.add(Country(locale.displayCountry))
        }
        return countries
            .filter { it.name.isNotEmpty() }
            .sortedBy { it.name }
    }

}