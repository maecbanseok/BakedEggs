package com.example.bakedeggs.main

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bakedeggs.List.ListFragment
import com.example.bakedeggs.R
import com.example.bakedeggs.data.ServiceLocator
import com.example.bakedeggs.databinding.ActivityMainBinding
import com.example.bakedeggs.databinding.DialogAlarmBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var isContact = true

    private val myNotificationID = 1
    private val channelID = "default"

    private val mainViewPagerAdapter by lazy {
        MainViewPagerAdapter(this)
    }

    private lateinit var serviceLocator: ServiceLocator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        serviceLocator=ServiceLocator.getInstance(application)

        getPermission()
        createNotificationChannel()

    }

    fun getPermission(){
        if(Build.VERSION.SDK_INT < 33){
            val permissions = arrayOf(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.SEND_SMS,
                android.Manifest.permission.INTERNET, android.Manifest.permission.READ_CALL_LOG)
            var flag=false
            for(i in permissions){
                if(checkSelfPermission(i) == PackageManager.PERMISSION_DENIED){
                    flag=true
                }
            }
            if(flag) requestPermissions(permissions,0)
            else initView()
        }else{
            val permissions = arrayOf(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.POST_NOTIFICATIONS,android.Manifest.permission.SEND_SMS,
                android.Manifest.permission.INTERNET, android.Manifest.permission.READ_CALL_LOG)
            var flag=false
            for(i in permissions){
                if(checkSelfPermission(i) == PackageManager.PERMISSION_DENIED){
                    flag=true
                }
            }
            if(flag) requestPermissions(permissions,0)
            else initView()
        }



    }

    fun initView(){

        with(binding){

            mainViewpager.adapter=mainViewPagerAdapter
            mainViewpager.offscreenPageLimit=2

            TabLayoutMediator(mainTabs,mainViewpager){tab,position ->
                when(position){
                    0->tab.text="CONTACT"
                    1->tab.text="MYPAGE"
                }
            }.attach()

            mainFbtnAdd.setOnClickListener{

            }

            mainFbtnAddalarm.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("알림 추가")
                builder.setIcon(R.mipmap.ic_launcher)

                val bindingDialog = DialogAlarmBinding.inflate(layoutInflater)
                val items = arrayOf("없음","5분","15분","30분")
                val adapter = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,items)
                bindingDialog.dialongSpinnerTime.adapter=adapter
                builder.setView(bindingDialog.root)

                val listener = DialogInterface.OnClickListener { _,_->
                    val selected=bindingDialog.dialongSpinnerTime.selectedItemPosition
                    val time=when(selected){
                        0 -> 0
                        1 -> 5
                        2 -> 15
                        else -> 30
                    }
                    showNotification(bindingDialog.dialogEtName.text.toString(), time)
                }

                builder.setPositiveButton("확인",listener)
                builder.setNegativeButton("취소",null)

                builder.show()
            }
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
                if(i==PackageManager.PERMISSION_DENIED) flag=false

            }
            if(flag) initView()
            else println(grantResults.contentToString())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Android 8.0
            val channel = NotificationChannel(
                channelID, "default channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "call alarm"
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(str:String, time:Int) {
        val builder = NotificationCompat.Builder(this, channelID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("연락처 알림")
            .setContentText(str+"에게 연락할 시간입니다.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            getPermission()
            return
        }
        Thread{
            Thread.sleep((60*1000*time).toLong())
            NotificationManagerCompat.from(this).notify(myNotificationID, builder.build())
        }.start()

    }
}