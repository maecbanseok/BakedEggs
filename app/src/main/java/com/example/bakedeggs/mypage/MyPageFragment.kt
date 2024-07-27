package com.example.bakedeggs.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.data.ViewModel.ContactViewModel
import com.example.bakedeggs.data.ViewModel.ContactViewModelFactory
import com.example.bakedeggs.databinding.FragmentMyPageBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import kotlinx.coroutines.launch

class MyPageFragment : Fragment() {

    private val binding by lazy { FragmentMyPageBinding.inflate(layoutInflater) }

    private val viewModel: MyPageViewModel by activityViewModels<MyPageViewModel> {
        MyPageViewModelFactory()
    }

    private val contactViewModel: ContactViewModel by activityViewModels<ContactViewModel> {
        ContactViewModelFactory(application = requireActivity().application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyPageFlagObj.initData()
        MyPageDataObj.initData()
        viewModel.initData()
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyPageFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mainActivity: MainActivity = activity as MainActivity
        val adapter = MyPageRecyclerViewAdapter(MyPageDataObj.getDataSource(), mainActivity)

        Log.d("마이페이지","init")
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.d("마이페이지","0")
                contactViewModel.notNormal()
            }
        }

        adapter.submitList(
            listOf()
        )

        adapter.itemChange = object : MyPageRecyclerViewAdapter.ItemChange {
            override fun onChangeData() {
//                viewModel.setData()
                adapter.submitList(listOf())
                adapter.submitList(MyPageDataObj.getDataSource()?.changeUIList())
            }

            override fun onChangeEditable(isEditable: Boolean) {

            }
        }

//        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
//            override fun onChanged() {
//                binding.recycler.scrollToPosition(MyPageDataObj.getData().snsIds?.size?.plus(
//                    MyPageDataObj.getFirst()
//                ) ?: 0)
//            }
//        })

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.itemAnimator = null
        viewModel.liveData.observe(viewLifecycleOwner) {
            println("값 변경됨!!!")
            if (viewModel.getData() != null) {
                adapter.submitList(listOf())
                adapter.submitList(MyPageDataObj.getDataSource()?.changeUIList())
            }
        }
    }
}