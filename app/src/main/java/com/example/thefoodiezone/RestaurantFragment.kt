package com.example.thefoodiezone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thefoodiezone.data.util.Resource
import com.example.thefoodiezone.databinding.FragmentRestaurantBinding
import com.example.thefoodiezone.presentation.adapter.RestaurantAdapter
import com.example.thefoodiezone.presentation.viewModel.RestaurantsViewModel

class RestaurantFragment : Fragment() {
    private lateinit var viewModel: RestaurantsViewModel
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var fragmentRestaurantBinding: FragmentRestaurantBinding

    private var location = "Montreal"
    private var term = "restaurants"

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
        initRecyclerView()
        viewRestaurantsList()
    }

    private fun viewRestaurantsList(){
        viewModel.getLocalRestaurants(location, term)

        viewModel.localRestaurants.observe(viewLifecycleOwner, {response->
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
        restaurantAdapter = RestaurantAdapter()
        fragmentRestaurantBinding.rvRestaurant.apply {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar(){
        fragmentRestaurantBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        fragmentRestaurantBinding.progressBar.visibility = View.GONE
    }

}