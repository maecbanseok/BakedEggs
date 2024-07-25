package com.example.bakedeggs.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.ActivityMainBinding
import com.example.bakedeggs.databinding.FragmentMyPageBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyPageFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy { FragmentMyPageBinding.inflate(layoutInflater) }
    private val mainBinding by lazy { ActivityMainBinding.inflate(requireActivity().layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
        val adapter = MyPageRecyclerView()
        adapter.submitList(
            listOf(
                MyPageUIModel.TopBarModel(),
                MyPageUIModel.CardModel(),
                MyPageUIModel.HeaderModel(2, "SNS 계정 추가"),
                MyPageUIModel.ListModel(3, 0, "0"),
                MyPageUIModel.SnsPlusButtonModel
            )
        )
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}