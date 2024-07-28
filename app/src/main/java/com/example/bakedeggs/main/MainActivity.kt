package com.example.bakedeggs.main

import android.app.AlarmManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.bakedeggs.AddContact.AddDialogFragment
import com.example.bakedeggs.R
import com.example.bakedeggs.alarm.AlarmCall
import com.example.bakedeggs.alarm.AlarmDataBase
import com.example.bakedeggs.alarm.AlarmEntity
import com.example.bakedeggs.alarm.ViewModel.AlarmViewModel
import com.example.bakedeggs.alarm.ViewModel.AlarmViewModelFactory
import com.example.bakedeggs.data.ViewModel.ContactViewModel
import com.example.bakedeggs.data.ViewModel.ContactViewModelFactory
import com.example.bakedeggs.databinding.ActivityMainBinding
import com.example.bakedeggs.databinding.DialogAlarmBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private val contactViewModel: ContactViewModel by viewModels {
        ContactViewModelFactory(application)
    }

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewPagerAdapter by lazy {
        MainViewPagerAdapter(this)
    }

    private val alarmViewModel: AlarmViewModel by viewModels {
        AlarmViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getPermission()
    }

    fun getPermission(){
        val permissions = if(Build.VERSION.SDK_INT >=33) arrayOf(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.POST_NOTIFICATIONS,android.Manifest.permission.SEND_SMS,
            android.Manifest.permission.INTERNET, android.Manifest.permission.READ_CALL_LOG)
        else arrayOf(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.CALL_PHONE,
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
                AddDialogFragment().show(supportFragmentManager,"Add Contact")

            }

            mainFbtnAddalarm.setOnClickListener {
                if(Build.VERSION.SDK_INT>=31&&!(getSystemService(Context.ALARM_SERVICE) as AlarmManager).canScheduleExactAlarms())
                    startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                        data = Uri.parse("package:"+this@MainActivity.packageName)
                    })

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
                    val name= bindingDialog.dialogEtName.text.toString()
                    val time=genCode(when(selected){
                        0 -> 0L
                        1 -> 5L
                        2 -> 15L
                        else -> 30L
                    })
                    println("dif: ${time-System.currentTimeMillis()}")
                    AlarmCall(this@MainActivity).callAlarm(name, time)
                    alarmViewModel.addAlarm(AlarmEntity(time,name))

                }
                builder.setPositiveButton("확인",listener)
                builder.setNegativeButton("취소",null)

                builder.show()
            }
        }
    }

    fun genCode(time:Long):Long{
        val format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        val cur =LocalDateTime.now().atZone(ZoneId.systemDefault())
        return if(time!=0L) cur.plusMinutes(time).toInstant().toEpochMilli()
            else cur.plusSeconds(1L).toInstant().toEpochMilli()
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
}