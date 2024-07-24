package com.example.bakedeggs.AddContact

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.bakedeggs.databinding.FragmentAddBinding

class AddDialogFragment : DialogFragment() {
    private var _binding : FragmentAddBinding? = null
    private val binding  get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Do you like this app?")
                .setPositiveButton("Yes") { dialog, id ->
                    // Send the positive button event back to the host activity
                }
                .setNegativeButton("No") { dialog, id ->
                    // Send the negative button event back to the host activity
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
//        isCancelable = true
//    }
//
    override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.addIvProfile.setOnClickListener {
//
//        }
//        binding.addBtnSave.setOnClickListener {
//            onDestroy()
//        }
//        binding.addEtName
//        binding.addEtNameWarning
//        binding.addEtPhone
//        binding.addEtPhoneWarning
//        binding.addEtEmail
//        binding.addEtEmailWarning
//    }
//
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
//    companion object {
//        @JvmStatic
//        fun newInstance() = AddDialogFragment()
//    }
}