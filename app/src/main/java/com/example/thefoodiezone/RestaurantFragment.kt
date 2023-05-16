package com.example.thefoodiezone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thefoodiezone.data.util.Resource
import com.example.thefoodiezone.databinding.FragmentRestaurantBinding
import com.example.thefoodiezone.presentation.adapter.RestaurantAdapter
import com.example.thefoodiezone.presentation.viewModel.RestaurantsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RestaurantFragment : Fragment() {
    private lateinit var viewModel: RestaurantsViewModel
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var fragmentRestaurantBinding: FragmentRestaurantBinding

    private var location = "Montreal"
    private var term = "restaurants"

    private var isSearchByName = true
    private var isSearchByAddress = false
    private var isSearchByCuisineType = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentRestaurantBinding = FragmentRestaurantBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        restaurantAdapter = (activity as MainActivity).restaurantAdapter

        restaurantAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_restaurant", it)
            }
            findNavController().navigate(
                R.id.action_restaurantFragment_to_infoFragment,
                bundle
            )
        }

        initRecyclerView()
        setSpinner()
        viewRestaurantsList()
        radioButtonClickListener()
        setSearchView()
    }

    private fun setSpinner() {
        val sortByItems = arrayListOf<String>("Sort by", "Rating", "Distance")
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, sortByItems)
        fragmentRestaurantBinding.spinnerSort.adapter = arrayAdapter
        fragmentRestaurantBinding.spinnerSort.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, p1: View?, p: Int, p3: Long) {
                if (p != 0) {
                    viewModel.sortRestaurants(location, adapterView?.getItemAtPosition(p).toString().lowercase())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // not implemented
            }

        }
    }

    private fun radioButtonClickListener() {
        fragmentRestaurantBinding.radioGroupSearch.setOnCheckedChangeListener { radioGroup, checkedId ->
            isSearchByName = false
            isSearchByAddress = false
            isSearchByCuisineType = false
            when (checkedId) {
                fragmentRestaurantBinding.radioButtonName.id -> {
                    isSearchByName = true
                }
                fragmentRestaurantBinding.radioButtonAddress.id -> {
                    //isSearchByCuisineType = true
                    isSearchByAddress = true
                }
                else -> {
                    //isSearchByAddress = true
                    isSearchByCuisineType = true
                }
            }
        }
    }

    private fun viewRestaurantsList(){
        viewModel.getLocalRestaurants(location, term)
        viewModel.restaurants.observe(viewLifecycleOwner, { response->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let {
                        restaurantAdapter.differ.submitList(it.businesses.toList())
                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occured: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }
        })
    }

    private fun initRecyclerView(){
        //restaurantAdapter = RestaurantAdapter()
        fragmentRestaurantBinding.rvRestaurant.apply {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar(){
        fragmentRestaurantBinding.apply {
            progressBar.visibility = View.VISIBLE
            svRestaurant.visibility = View.GONE
            radioGroupSearch.visibility = View.GONE
            tvSearch.visibility = View.GONE
            spinnerSort.visibility = View.GONE
        }
    }

    private fun hideProgressBar(){
        fragmentRestaurantBinding.apply {
            progressBar.visibility = View.GONE
            svRestaurant.visibility = View.VISIBLE
            radioGroupSearch.visibility = View.VISIBLE
            tvSearch.visibility = View.VISIBLE
            spinnerSort.visibility = View.VISIBLE
        }
    }

    //search
    private fun setSearchView(){
        fragmentRestaurantBinding.svRestaurant.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    callSearch(query)
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    MainScope().launch {
                        delay(5000)
                        callSearch(query)
                    }
                    return false
                }

            }
        )
        fragmentRestaurantBinding.svRestaurant.setOnCloseListener(
            object : SearchView.OnCloseListener{
                override fun onClose(): Boolean {
                    initRecyclerView()
                    viewRestaurantsList()
                    return false
                }
            }
        )
    }

    private fun callSearch(query: String?) {
        if (isSearchByName) {
            searchByName(query)
        } else if (isSearchByAddress) {
            searchByAddress(query)
        } else {
            searchByCuisineType(query)
        }
    }

    private fun searchByName(query: String?) {
        query?.let { searchQuery ->
            viewModel.getLocalRestaurants(location, searchQuery)
        }
    }
    private fun searchByAddress(query: String?) {
        query?.let {searchQuery ->
            viewModel.getLocalRestaurantsByAddress(searchQuery)
        }
    }
    private fun searchByCuisineType(query: String?) {
        query?.let { searchQuery ->
            viewModel.getRestaurantsWithCuisineType(location, searchQuery)
        }
    }





}