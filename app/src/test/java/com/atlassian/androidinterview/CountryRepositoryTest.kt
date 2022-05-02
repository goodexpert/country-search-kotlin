@file:Suppress("RemoveRedundantBackticks")

package com.atlassian.androidinterview

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*
import org.hamcrest.CoreMatchers.`is` as matches

@RunWith(JUnit4::class)
class CountryRepositoryTest {

    private class TestLocaleProvider : LocaleProvider {
        override fun provideLocales(): List<Locale> = listOf(
                Locale("", ""),
                Locale("en", "US"),
                Locale("en", "GB"),
                Locale("en", "GB"),
                Locale("fr", "FR")
        )
    }

    @Test
    fun `getAllCountries provides sorted list without duplicated`() {
        // given
        val repository = CountryRepository(TestLocaleProvider())

        // when
        val countries: List<Country> = repository.getAllCountries()

        // then
        assertThat(countries, matches(listOf(
                Country("France"),
                Country("United Kingdom"),
                Country("United States")
        )))
    }

    @Test
    fun `getFilteredCountries with empty search string must provide all items`() {
        // given
        val repository = CountryRepository(TestLocaleProvider())

        // when
        val countries: List<Country> = repository.getFilteredCountries("")

        // then
        assertThat(countries, matches(listOf(
                Country("France"),
                Country("United Kingdom"),
                Country("United States")
        )))
    }

    @Test
    fun `getFilteredCountries with upper case search string must provide single item`() {
        // given
        val repository = CountryRepository(TestLocaleProvider())

        // when
        val countries: List<Country> = repository.getFilteredCountries("United K")

        // then
        assertThat(countries, matches(listOf(
                Country("United Kingdom")
        )))
    }

    @Test
    fun `getFilteredCountries with lower case search string must provide single item`() {
        // given
        val repository = CountryRepository(TestLocaleProvider())

        // when
        val countries: List<Country> = repository.getFilteredCountries("united k")

        // then
        assertThat(countries, matches(listOf(
            Country("United Kingdom")
        )))
    }

}