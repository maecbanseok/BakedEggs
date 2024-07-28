package com.example.bakedeggs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.core.view.isVisible
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.databinding.FragmentDetailBinding
import com.example.bakedeggs.mypage.MyPageData
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import kotlin.random.Random

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var snsAdapter: MyPageRecyclerViewAdapter
    private val snsList: MutableList<MyPageUIModel> = mutableListOf()
    private val dummy = ContactEntity("나는가짜","ㄴㄴㄱㅉ","01065408611",0,null,null,null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fun snsButtonVisibility() {

            if (binding.detailBtnInstagram.isVisible) {
                binding.detailBtnInstagram.visibility = View.INVISIBLE
                binding.detailBtnGithub.visibility = View.INVISIBLE
                binding.detailBtnDiscord.visibility = View.INVISIBLE
            } else {
                binding.detailBtnInstagram.visibility = View.VISIBLE
                binding.detailBtnGithub.visibility = View.VISIBLE
                binding.detailBtnDiscord.visibility = View.VISIBLE
            }
            binding.detailLoScroll.fullScroll(ScrollView.FOCUS_DOWN);
        }

        val adList = listOf<Uri>(
            Uri.parse("android.resource://" + requireActivity().packageName + "/raw/ad1"),
            Uri.parse("android.resource://" +  requireActivity().packageName + "/raw/ad2"),
            Uri.parse("android.resource://" +  requireActivity().packageName + "/raw/ad3"),
            Uri.parse("android.resource://" +  requireActivity().packageName + "/raw/ad4"),
            Uri.parse("android.resource://" +  requireActivity().packageName + "/raw/ad5"),
        )



        binding.detailIvProfile.clipToOutline = true
        var uri: Uri = adList.get(Random.nextInt(adList.size))
        binding.DetailVvAd.setVideoURI(uri)

        val tel = "010-0000-0000"

        binding.detailIvCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel))
            startActivity(intent)
        }

        binding.detailIvMessage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("smsto:" + tel))
            startActivity(intent)
        }

        snsAdapter = MyPageRecyclerViewAdapter(MyPageData(),requireActivity())
        snsAdapter.submitList(listOf())

        binding.DetailRvSmsList.adapter = snsAdapter
        binding.detailBtnSnsadd.setOnClickListener {
            snsButtonVisibility()
            binding.detailBtnInstagram.apply {
                setOnClickListener {
                    snsList.add(
                        MyPageUIModel.ListModel(
                            snsAdapter.itemCount + 1,
                            R.drawable.instagram_24,
                            "",
                            0
                        )
                    )
                    snsAdapter.submitList(snsList)
                    snsButtonVisibility()
                    snsAdapter.notifyItemInserted(snsList.size-1)
                }

            }
            binding.detailBtnGithub.apply {
                setOnClickListener {
                    snsList.add(
                        MyPageUIModel.ListModel(
                            snsAdapter.itemCount + 1,
                            R.drawable.github_24,
                            "",
                            1
                        )
                    )
                    snsAdapter.submitList(snsList)
                    snsButtonVisibility()
                    snsAdapter.notifyItemInserted(snsList.size-1)
                }

            }
            binding.detailBtnDiscord.apply {
                setOnClickListener {
                    snsList.add(
                        MyPageUIModel.ListModel(
                            snsAdapter.itemCount + 1,
                            R.drawable.discord_24,
                            "",
                            2
                        )
                    )
                    snsAdapter.submitList(snsList)
                    snsButtonVisibility()
                    snsAdapter.notifyItemInserted(snsList.size-1)
                }

            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailFragment()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
