package com.example.bakedeggs.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bakedeggs.MainActivity
import com.example.bakedeggs.databinding.FragmentMyPageBinding
import com.example.bakedeggs.mypage.adapter.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.MyPageUIModel


class MyPageFragment : Fragment() {

    private val binding by lazy { FragmentMyPageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyPageDataObj.initData(application = requireActivity().application)
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
        val adapter = MyPageRecyclerViewAdapter(mainActivity)
        adapter.submitList(
            listOf(
                MyPageUIModel.TopBarModel(),
                MyPageUIModel.CardModel(),
                MyPageUIModel.HeaderModel(2, "SNS 계정 추가"),
                MyPageUIModel.ListModel(3, 0, "0"),
                MyPageUIModel.SnsPlusButtonModel
            )
        )
        mainActivity.binding.mainFramelayout.isVisible = false
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}