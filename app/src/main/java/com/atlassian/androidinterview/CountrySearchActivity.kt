package com.atlassian.androidinterview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import kotlinx.coroutines.*

class CountrySearchActivity : AppCompatActivity() {

    private val repository = CountryRepository()
    private val searchJob = CoroutineScope(Job() + Dispatchers.IO)
    private val countryAdapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchEditText: EditText = findViewById(R.id.searchEditText)
        val countryRecyclerView: RecyclerView = findViewById(R.id.countryRecyclerView)

        countryRecyclerView.layoutManager = LinearLayoutManager(this)
        countryRecyclerView.adapter = countryAdapter
        countryAdapter.submitList(repository.getAllCountries())

        searchEditText.afterTextChanged { text ->
            // Should run this on other thread using coroutine or asynctask or thread
            searchJob.launch {
                val result = repository.getFilteredCountries(text)
                withContext(Dispatchers.Main) {
                    countryAdapter.submitList(result)
                }
            }
        }
    }

    override fun onDestroy() {
        searchJob.cancel()
        super.onDestroy()
    }
}

