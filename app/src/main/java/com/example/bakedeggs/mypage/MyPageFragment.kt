package com.example.bakedeggs.mypage

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.bakedeggs.R
import com.example.bakedeggs.data.ContactRepositoryImpl
import com.example.bakedeggs.data.ViewModel.ContactViewModel
import com.example.bakedeggs.data.ViewModel.ContactViewModelFactory
import com.example.bakedeggs.databinding.FragmentMyPageBinding
import com.example.bakedeggs.main.MainActivity
import kotlinx.coroutines.launch

class MyPageFragment : Fragment() {

    private val binding by lazy { FragmentMyPageBinding.inflate(layoutInflater) }

    private val viewModel: MyPageViewModel by activityViewModels<MyPageViewModel> {
        MyPageViewModelFactory()
    }

    private val contactViewModel: ContactViewModel by activityViewModels {
        ContactViewModelFactory(application = requireActivity().application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyPageFlagObj.initData()

//        MyPageDataObj.initData()
        viewModel.initData()
        arguments?.let {}
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
        val mainActivity: MainActivity = activity as MainActivity
        val adapter = MyPageRecyclerViewAdapter(MyPageDataObj.getDataSource(), mainActivity)

        Uri.parse("android.resource://" + mainActivity.packageName + "/" + R.drawable.mypage_base_photo_summer)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                contactViewModel.notNormal(adapter)
            }
        }

        adapter.submitList(
            listOf()
        )

        binding.root.viewTreeObserver.addOnGlobalFocusChangeListener { old, new ->
            println("프래그먼트에서 옵저버 $old, $new")
            new?.performClick()
        }

        adapter.itemChange = object : MyPageRecyclerViewAdapter.ItemChange {
            override fun onChangeData() {
//                viewModel.setData()
                adapter.submitList(listOf())
                adapter.submitList(MyPageDataObj.getDataSource().changeUIList())
            }

            override fun onChangeEditable(isEditable: Boolean) {

            }

            override fun onChangeDataRange(position: Int, itemCount: Int) {
                onChangeData()
//                adapter.notifyItemRangeChanged(position, itemCount)
            }
        }

//        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
//            override fun onChanged() {
//                binding.recycler.scrollToPosition(MyPageDataObj.getData().snsIds?.size?.plus(
//                    MyPageDataObj.getFirst()
//                ) ?: 0)
//            }
//        })

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.itemAnimator = null
        viewModel.liveData.observe(viewLifecycleOwner) {
            println("값 변경됨!!!")
            if (viewModel.getData() != null) {
                adapter.submitList(listOf())
                adapter.submitList(MyPageDataObj.getDataSource().changeUIList())
            }
        }
    }

    fun initPhoto(
        myPageData: MyPageData,
    ): ActivityResultLauncher<PickVisualMediaRequest> {
        var profileUri: Uri? = null
        //이미지 자르기
        val cropImage = registerForActivityResult(CropImageContract()) { result ->
            if (result.isSuccessful) {
                // returned uri 사용
                profileUri = result.uriContent
                if(profileUri != null) myPageData.setPhotoFromPicker(profileUri!!)
            } else {
                val exception = result.error
            }
        }

        //이미지 가져오기
        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: $uri")
                    cropImage.launch(
                        CropImageContractOptions(
                            uri = uri, // 크롭할 이미지 uri
                            cropImageOptions = CropImageOptions(
                                outputCompressFormat = Bitmap.CompressFormat.PNG,//사진 확장자 변경
                                minCropResultHeight = 50,//사진 최소 세로크기
                                minCropResultWidth = 80,//사진 최소 가로크기
                                aspectRatioY = 5,//세로 비율
                                aspectRatioX = 8,//가로 비율
                                fixAspectRatio = true,//커터? 크기 고정 여부
                                borderLineColor = Color.GREEN//커터? 태두리 색
                                // 원하는 옵션 추가
                            )
                        )
                    )
                } else {
                    Log.d("PhotoPicker", "No media selected")
                }
            }
        return pickMedia
    }
}