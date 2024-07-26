package com.example.bakedeggs.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bakedeggs.databinding.FragmentMyPageBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class MyPageFragment : Fragment() {

    private val binding by lazy { FragmentMyPageBinding.inflate(layoutInflater) }

    private val viewModel: MyPageViewModel by viewModels<MyPageViewModel> {
        MyPageViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyPageFlagObj.initData()
        MyPageDataObj.initData(application = requireActivity().application)
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
        val adapter = MyPageRecyclerViewAdapter(0, mainActivity)

        adapter.submitList(
            listOf()
        )

        adapter.itemChange = object : MyPageRecyclerViewAdapter.ItemChange {
            override fun onChangeData() {
//                viewModel.setData()
                adapter.submitList(listOf())
                adapter.submitList(MyPageDataObj.changeUIList())
            }

            override fun onChangeEditable(isEditable: Boolean) {

            }
        }

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.itemAnimator = null
        viewModel.liveData.observe(viewLifecycleOwner) {
            println("값 변경됨!!!")
            if (viewModel.getData() != null) {
                adapter.submitList(listOf())
                adapter.submitList(MyPageDataObj.changeUIList())
            }
        }
    }
}