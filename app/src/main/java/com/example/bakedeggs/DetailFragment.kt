package com.example.bakedeggs

import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.core.view.isVisible
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.SNS
import com.example.bakedeggs.databinding.FragmentDetailBinding
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import com.example.bakedeggs.snsAdapter.SNSAdapter
import kotlin.random.Random

private val param = "param"

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var snsAdapter: SNSAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactEntity = arguments?.getParcelable<ContactEntity>(param)

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


        val img=contactEntity?.img?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        requireActivity().contentResolver,
                        it
                    )
                )
            } else {
                MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, it)
            }
        }

        img?.let{binding.detailIvProfile.setImageBitmap(it)}
        binding.detailTvName.setText(contactEntity?.name?:"")
        binding.detailTvPhone.setText(contactEntity?.num?:"")



        binding.detailIvCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+contactEntity?.num ))
            startActivity(intent)
        }

        binding.detailIvMessage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("smsto:"+contactEntity?.num ))
            startActivity(intent)
        }

        contactEntity?.sns.let {
            Log.d("sns",it!!.instagram.toString())
            Log.d("sns",it!!.github.toString())
            Log.d("sns",it!!.discord.toString())
        }

        snsAdapter = SNSAdapter(convertSNS(contactEntity?.sns?:SNS()))

        binding.DetailRvSmsList.adapter = snsAdapter


        binding.detailBtnSnsadd.setOnClickListener {
            snsButtonVisibility()
            binding.detailBtnInstagram.apply {
                setOnClickListener {
                    snsAdapter.snsList+=Pair(0,"")
                    snsAdapter.notifyItemInserted(snsAdapter.snsList.size-1)
                    snsButtonVisibility()
                }
            }
            binding.detailBtnGithub.apply {
                setOnClickListener {
                    snsAdapter.snsList+=Pair(1,"")
                    snsAdapter.notifyItemInserted(snsAdapter.snsList.size-1)
                    snsButtonVisibility()
                }
            }
            binding.detailBtnDiscord.apply {
                setOnClickListener {
                    snsAdapter.snsList+=Pair(2,"")
                    snsAdapter.notifyItemInserted(snsAdapter.snsList.size-1)
                    snsButtonVisibility()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle):DetailFragment{
            return DetailFragment().apply {
                arguments=bundle
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun convertSNS(sns: SNS):ArrayList<Pair<Int,String>>{
        val result = ArrayList<Pair<Int,String>>()
        sns.instagram.forEach { result+=Pair(0,it) }
        sns.github.forEach { result+=Pair(1,it) }
        sns.discord.forEach { result+=Pair(2,it) }
        return result
    }

}
