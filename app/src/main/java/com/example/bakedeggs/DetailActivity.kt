package com.example.bakedeggs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.bakedeggs.databinding.ActivityDetailBinding
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import kotlin.random.Random

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var snsAdapter: MyPageRecyclerViewAdapter
    private val snsList: MutableList<MyPageUIModel> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        fun snsButtonVisibility() {
            snsAdapter.notifyDataSetChanged()
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
            Uri.parse("android.resource://" + this.packageName + "/raw/ad1"),
            Uri.parse("android.resource://" + this.packageName + "/raw/ad2"),
            Uri.parse("android.resource://" + this.packageName + "/raw/ad3"),
            Uri.parse("android.resource://" + this.packageName + "/raw/ad4"),
            Uri.parse("android.resource://" + this.packageName + "/raw/ad5"),
        )

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        snsAdapter = MyPageRecyclerViewAdapter(activity as DetailActivity)
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
                    susButtonVisibility()
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
                    susButtonVisibility()
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
                    susButtonVisibility()
                }

            }

        }

    }

    private fun susButtonVisibility() {
        TODO("Not yet implemented")
    }

    override fun onStart() {
        super.onStart()
        binding.DetailVvAd.start()
    }
}