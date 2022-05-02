package com.atlassian.androidinterview

class CountryRepository(private val localeProvider: LocaleProvider = AndroidLocaleProvider()) {

    /**
     * @return list of countries sorted by ASC. Each [Country.name] must start with the provided
     * [startsWith] value ignoring upper/lower case.
     */
    fun getFilteredCountries(startsWith: String) = getAllCountries()
            .filter { country -> country.name.startsWith(startsWith) }

    /**
     * @return list of countries sorted by ASC.
     */
    fun getAllCountries(): List<Country> {
        val countries = mutableListOf<Country>()
        for (locale in localeProvider.provideLocales()) {
            countries.add(Country(locale.displayCountry))
        }
        return countries
    }

}