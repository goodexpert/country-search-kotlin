package com.atlassian.androidinterview

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText

class CountrySearchActivity : AppCompatActivity() {

    private val repository = CountryRepository()

    private var searchTask: MyAsyncTask? = null
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
            searchTask?.cancel(false)
            searchTask = MyAsyncTask().apply {
                execute(text)
            }
        }
    }

    inner class MyAsyncTask : AsyncTask<String, Void, List<Country>>() {
        override fun doInBackground(vararg params: String): List<Country> {
            return repository.getFilteredCountries(params[0])
        }

        override fun onPostExecute(result: List<Country>?) {
            countryAdapter.submitList(result)
        }
    }
}

