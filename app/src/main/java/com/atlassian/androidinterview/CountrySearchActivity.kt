package com.atlassian.androidinterview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText

class CountrySearchActivity : AppCompatActivity() {

    private val repository = CountryRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchEditText: EditText = findViewById(R.id.searchEditText)
        val countryRecyclerView: RecyclerView = findViewById(R.id.countryRecyclerView)
        val countryAdapter = CountryAdapter()

        countryRecyclerView.layoutManager = LinearLayoutManager(this)
        countryRecyclerView.adapter = countryAdapter
        countryAdapter.submitList(repository.getAllCountries())

        searchEditText.afterTextChanged { text ->
            countryAdapter.submitList(repository.getFilteredCountries(text))
        }
    }
}

