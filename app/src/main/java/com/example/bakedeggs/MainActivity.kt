package com.example.bakedeggs

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.bakedeggs.data.EventBus
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.List.ListAdapter
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.ContactRepository
import com.example.bakedeggs.data.ServiceLocator
import com.example.bakedeggs.databinding.ActivityMainBinding
import com.example.bakedeggs.mypage.MyPageFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

//    var isGrid = false
//    var isContact = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

//        binding.mainLlGridlist.setOnClickListener {
//            binding.mainViewWhitebtn.callOnClick()
//            isGrid = !isGrid
//            lifecycleScope.launch {
//                EventBus.produceEvent(isGrid)
//            }
//        }
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment, MyPageFragment.newInstance("", ""))
        }

    }

//    fun initView() {
//        with(binding) {
//            mainLlGridlist.setOnClickListener {
//                binding.mainViewWhitebtn.callOnClick()
//                isGrid = !isGrid
//                lifecycleScope.launch {
//                    EventBus.produceEvent(isGrid)
//                }
//            }
//
//            mainBtnContact.setOnClickListener {
//                if (isContact) return@setOnClickListener
//                isContact = !isContact
//                setFragment(isContact)
//            }
//            mainBtnMypage.setOnClickListener {
//                if (!isContact) return@setOnClickListener
//                isContact = !isContact
//                setFragment(isContact)
//            }
//        }
//    }
//    //if -> list or grid에 따라 선택
//    //if -> list or grid에 따라 선택
//
//    fun setFragment(isContact: Boolean) {
//        if (isContact) {
//
//        } else {
//
//        }
//    }
}