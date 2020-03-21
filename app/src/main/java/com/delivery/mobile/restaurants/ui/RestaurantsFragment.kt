package com.delivery.mobile.restaurants.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.RadioGroup
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.delivery.mobile.R
import com.delivery.mobile.data.Result
import com.delivery.mobile.databinding.FragmentRestaurantsBinding
import com.delivery.mobile.fragment.BaseFragment
import com.delivery.mobile.restaurants.data.Restaurant
import com.delivery.mobile.ui.Creteria
import com.delivery.mobile.ui.customSort
import com.delivery.mobile.ui.hide
import com.delivery.mobile.ui.show
import kotlinx.android.synthetic.main.fragment_restaurants.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class RestaurantsFragment : BaseFragment(), SearchView.OnQueryTextListener,
    RadioGroup.OnCheckedChangeListener {

    private val olderRadioId: Int? = -1
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var restaurantList: List<Restaurant>? = arrayListOf()


    private val viewModel: RestaurantsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(RestaurantsViewModel::class.java)
    }


    private val adapter: RestaurantsAdapter by lazy {
        RestaurantsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRestaurantsBinding.inflate(inflater, container, false)
        context ?: return binding.root
        initSearch(binding)
        binding.recyclerView.adapter = adapter
        binding.radioGroup.setOnCheckedChangeListener(this)
        subscribeUi(binding, adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun subscribeUi(binding: FragmentRestaurantsBinding, adapter: RestaurantsAdapter) {
        launch {
            val restaurants = viewModel.restaurants
            restaurants.observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    Result.Status.SUCCESS -> {
                        restaurantList = result.data
                        restaurantList?.let {
                            adapter.submitList(it)
                            binding.progressBar.hide() }
                    }
                    Result.Status.LOADING -> binding.progressBar.show()
                    Result.Status.ERROR -> {
                        binding.progressBar.hide()
                        Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

    fun favouriteClicked(restaurant: Restaurant) { 
        restaurant.favourited = !restaurant.favourited
        adapter.notifyDataSetChanged()
        restaurant.let { viewModel.insertRestaurant(it) }
    }


    private fun initSearch(binding: FragmentRestaurantsBinding) {
        binding.searchView?.apply {
            isIconifiedByDefault = false
            setOnQueryTextListener(this@RestaurantsFragment)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String): Boolean {

        val filteredModelList: ArrayList<Restaurant> =
            filter(restaurantList, query)
        adapter.submitList(filteredModelList)
        recyclerView.scrollToPosition(0)
        return true
    }

    @SuppressLint("DefaultLocale")
    private fun filter(
        models: List<Restaurant>?,
        query: String
    ): ArrayList<Restaurant> {
        val filteredModelList: ArrayList<Restaurant> = ArrayList()
        if (models != null) {
            for (model in models) {
                if (model.name.toLowerCase().contains(query.toLowerCase())) {
                    filteredModelList.add(model)
                }
            }
        }
        return filteredModelList

    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val checked = olderRadioId != checkedId
        restaurantList =
            if (checked) {
                when (checkedId) {
                    R.id.radio_newest -> restaurantList?.customSort(Creteria.newest)
                    R.id.radio_rating -> restaurantList?.customSort(Creteria.rating)
                    R.id.radio_distance -> restaurantList?.customSort(Creteria.distance)
                    R.id.radio_popularity -> restaurantList?.customSort(Creteria.popularity)
                    R.id.radio_avarage_product -> restaurantList?.customSort(Creteria.avarage)
                    R.id.radio_delivery_cost -> restaurantList?.customSort(Creteria.deliverycost)
                    R.id.radio_min_cost -> restaurantList?.customSort(Creteria.mincost)
                    else ->
                        restaurantList?.customSort(Creteria.popularity)

                }
            } else {
                restaurantList?.customSort(Creteria.mincost)
            }
//        adapter.submitList(null)
        adapter.submitList(restaurantList)
        adapter.notifyDataSetChanged()
    }

    private fun bottomsheetopenClose() {
        BottomSheetBehavior.from(bottom_sheet).state =
            if (BottomSheetBehavior.from(bottom_sheet).state == BottomSheetBehavior.STATE_EXPANDED)
                BottomSheetBehavior.STATE_COLLAPSED else
                BottomSheetBehavior.STATE_EXPANDED
    }


    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                bottomsheetopenClose()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}

