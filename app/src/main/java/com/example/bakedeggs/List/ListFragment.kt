package com.example.bakedeggs.List

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.R
import com.example.bakedeggs.data.ContactDataSource
import com.example.bakedeggs.data.ContactRepositoryImpl
import com.example.bakedeggs.data.EventBus
import com.example.bakedeggs.data.ServiceLocator
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        lifecycleScope.launch {
            EventBus.events.collect { Boolean
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val serviceLocator =
            ServiceLocator.getInstance(requireActivity().application) as ServiceLocator
        //val repository = serviceLocator.contactDataSource.ContactEntities


        /*val adapter = ListAdapter(arrayList)
        val listRecyclerView : RecyclerView = findViewById(R.id.list_recyclerview)
        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.adapter = adapter*/


    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}