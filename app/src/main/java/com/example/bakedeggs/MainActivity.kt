package com.example.bakedeggs

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.bakedeggs.data.EventBus
import com.example.bakedeggs.data.ServiceLocator
import com.example.bakedeggs.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var isGrid = false
    var isContact = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val serviceLocator=ServiceLocator.getInstance(application)

        getPermission()

    }

    fun getPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
        val permissions = arrayOf(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.POST_NOTIFICATIONS,android.Manifest.permission.SEND_SMS, android.Manifest.permission.INTERNET)
        var flag=false
        for(i in permissions){
            if(checkSelfPermission(i)== PackageManager.PERMISSION_DENIED){
                flag=true
            }
        }
        if(flag) requestPermissions(permissions,0)
        else initView()

    }

    fun initView(){

        with(binding){
            mainLlGridlist.setOnClickListener{
                binding.mainViewWhitebtn.callOnClick()
                isGrid=!isGrid
                lifecycleScope.launch {
                    EventBus.produceEvent(isGrid)
                }
            }

            mainBtnContact.setOnClickListener {
                if(isContact) return@setOnClickListener
                isContact=!isContact
                setFragment(isContact)
            }
            mainBtnMypage.setOnClickListener {
                if(!isContact) return@setOnClickListener
                isContact=!isContact
                setFragment(isContact)
            }

            mainFbtnAdd.setOnClickListener{

            }

            mainFbtnAddalarm.setOnClickListener {

            }
        }
    }

    fun setFragment(isContact: Boolean){
        if(isContact){

        }else{

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode ==0){
            var flag=true
            for(i in grantResults){
                if(i!=PackageManager.PERMISSION_GRANTED) flag=false
            }
            if(flag) initView()
        }
    }
}