package com.happypills.ui.pills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.happypills.R
import com.happypills.ui.pills.utils.PillListGridRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_pills.view.*

class PillsFragment : Fragment() {

    private lateinit var pillsViewModel: PillsViewModel
    private lateinit var recyclerView: RecyclerView
    private var recyclerViewAdapter = PillListGridRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pillsViewModel =
            ViewModelProviders.of(this).get(PillsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pills, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupView()
    }

    private fun setupRecyclerView() {
        view?.let { recyclerView = it.pills_recycler_view }
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = recyclerViewAdapter
        }
        recyclerViewAdapter.setPillsList(pillsViewModel.pillsList.value!!)
    }

    private fun setupView(){
        view?.add_pill_button?.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_pills_to_addPillFragment)
        }
    }

}