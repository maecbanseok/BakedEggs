package com.example.bakedeggs

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bakedeggs.databinding.ActivityDetailBinding
import kotlin.random.Random

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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

    }

    override fun onStart() {
        super.onStart()
        binding.DetailVvAd.start()
    }
}