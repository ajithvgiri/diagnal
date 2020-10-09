package com.ajithvgiri.diagnal.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.filter
import androidx.recyclerview.widget.GridLayoutManager
import com.ajithvgiri.diagnal.R
import com.ajithvgiri.diagnal.adapter.ContentAdapter
import com.ajithvgiri.diagnal.ui.base.BaseFragment
import com.ajithvgiri.diagnal.utils.UiHelper
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeFragment : BaseFragment() {


    @Inject
    lateinit var uiHelper: UiHelper
    private val contentAdapter = ContentAdapter()
    private var coroutineJob: Job? = null
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        if (uiHelper.getConnectivityStatus())
            subscribeObservers()
        else
            uiHelper.showSnackBar(view, resources.getString(R.string.error_network_connection))
    }

    private fun initRecyclerView() {
        /*
         * Setup the adapter class for the RecyclerView
         * */
        recyclerView.apply {
            layoutManager =
                if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    GridLayoutManager(requireContext(), 7)
                } else {
                    GridLayoutManager(requireContext(), 3)
                }
            adapter = contentAdapter
        }
    }

    private fun subscribeObservers() {
        /*
         * When a new data is available, we call submitData() method
         * of the PagingDataAdapter class
         * */

        // Make sure we cancel the previous job before creating a new one
        coroutineJob?.cancel()
        coroutineJob = lifecycleScope.launch {
            homeViewModel.contentData.collect {
                it.let {
                    contentAdapter.submitData(it)
                }
            }
        }

        /*
         * Progress Updater
         * */
        contentAdapter.addLoadStateListener { loadState ->

            /*
            * loadState.refresh - represents the load state for loading the PagingData for the first time.
              loadState.prepend - represents the load state for loading data at the start of the list.
              loadState.append - represents the load state for loading data at the end of the list.
            * */

            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                showProgressBar(true)
            else {
                showProgressBar(false)

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
//                    Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    // UPDATE UI ----
    private fun showProgressBar(display: Boolean) {
        if (!display)
            progressBar.visibility = View.GONE
        else
            progressBar.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)

        val searchItem = menu.findItem(R.id.appSearchBar)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.length ?: 0 > 2) {
                    recyclerView.scrollToPosition(0)
                    // Make sure we cancel the previous job before creating a new one
                    coroutineJob?.cancel()
                    coroutineJob = lifecycleScope.launch {
                        homeViewModel.contentData.collect {
                            contentAdapter.submitData(it.filter { content ->
                                content.name.toLowerCase().trim()
                                    .contains("${newText?.toLowerCase()}".trim())
                            })
                        }
                    }
                } else {
                    subscribeObservers()
                }
                return true
            }
        })
    }
}