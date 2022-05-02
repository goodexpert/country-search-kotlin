package com.atlassian.androidinterview

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class CountryAdapter : ListAdapter<Country, CountryAdapter.CountryViewHolder>(CountryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CountryViewHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView)

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) = holder.bindTo(getItem(position))


    class CountryViewHolder(private val view: TextView) : RecyclerView.ViewHolder(view) {

        fun bindTo(country: Country) {
            view.text = country.name
        }
    }

    private object CountryDiffCallback : DiffUtil.ItemCallback<Country>() {

        override fun areItemsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem

    }
}