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
import com.example.bakedeggs.databinding.ListRecyclerviewBinding
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private lateinit var listAdapter: ListAdapter
    private lateinit var contactRepository: ContactRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //serviceLocator를 사용하여 contactRepository 초기화
        var serviceLocator = ServiceLocator.getInstance(requireActivity().application)
        //contactRepository = ContactRepositoryImpl(serviceLocator.)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //recyclerView 초기화
        //어댑터 초기화 및 설정
        listAdapter = ListAdapter(contactRepository.getContactList())

        listAdapter.setListClickListener(object : ListAdapter.ListClick{

            override fun onClick(view: View, position: Int) {
                //클릭 이벤트 처리
            }

            override fun onPressed(view: View, position: Int) {
                //길게 클릭 이벤트 처리
            }

        })



    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}