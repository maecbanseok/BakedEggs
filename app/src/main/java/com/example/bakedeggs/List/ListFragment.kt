package com.example.bakedeggs.List

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.R
import com.example.bakedeggs.data.ViewModel.ContactViewModel
import com.example.bakedeggs.data.ViewModel.ContactViewModelFactory
import com.example.bakedeggs.data.ContactDataSource
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.ContactRepository
import com.example.bakedeggs.data.ContactRepositoryImpl
import com.example.bakedeggs.data.EventBus
import com.example.bakedeggs.databinding.FragmentListBinding
import com.example.bakedeggs.databinding.ListRecyclerviewBinding
import com.example.bakedeggs.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private val contactViewModel: ContactViewModel by activityViewModels {
        ContactViewModelFactory(requireActivity().application)
    }

    var isGrid = true
    private val listAdapter = ListAdapter(ArrayList())

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    fun initView() {



        with(binding) {

            listClProfileContainer.setOnClickListener {
                (requireActivity() as MainActivity).binding.mainViewpager.setCurrentItem(1)
            }

            listLlGridlist.setOnClickListener {
                mainViewWhitebtn.callOnClick()
                listMlGridlist.setTransitionListener(object : MotionLayout.TransitionListener {
                    override fun onTransitionStarted(
                        motionLayout: MotionLayout?, startId: Int, endId: Int
                    ) {
                        return
                    }

                    override fun onTransitionChange(
                        motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float
                    ) {
                        return
                    }

                    override fun onTransitionCompleted(
                        motionLayout: MotionLayout?, currentId: Int
                    ) {
                        isGrid = motionLayout!!.currentState == motionLayout!!.startState
                        listRecyclerview.layoutManager = if (isGrid) {
                            GridLayoutManager(requireContext(), 2)
                        } else {
                            LinearLayoutManager(requireContext())
                        }
                        return
                    }

                    override fun onTransitionTrigger(
                        motionLayout: MotionLayout?,
                        triggerId: Int,
                        positive: Boolean,
                        progress: Float
                    ) {
                        return
                    }

                })
            }


            //어댑터 초기화 및 설정

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    contactViewModel.search(listAdapter)
                }
            }
            listRecyclerview.adapter=listAdapter
            listRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
            listAdapter.listClick = object : ListAdapter.ListClick {

                override fun onClick(view: View, position: Int) {
                    // 컬렉터로 받기
                    lifecycleScope.launch {
                        EventBus.produceEvent(Bundle().apply {
                            putInt("ContactDetail", position)
                        })
                    }
                    //Intent
//                    val intent = Intent(activity, DetailActivity::class.java).apply {
//                        putExtra("contactNum", getData[position].num)
//                    }
//                    startActivity(intent)

                }


                override fun onLongClick(view: View, contactEntity: ContactEntity) {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("삭제 혹은 차단하시겠습니까?")
                        .setNeutralButton("취소") { dialog, which -> return@setNeutralButton}
                        .setNegativeButton("차단") { dialog, which ->
                            contactViewModel.modifyContact(contactEntity,contactEntity.copy(tag = 2))
                        }
                        .setPositiveButton("삭제") { dialog, which ->
                            contactViewModel.removeContact(contactEntity)
                        }
                        .show()
                }
            }

            //androidx.appcompat.widget.SearchView -> 위젯 사용
            listSearchView.setOnQueryTextListener (object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // 검색해서 나온 데이터들
                    contactViewModel.str=newText?:""
                    contactViewModel.fetch()
                    return true
                }
            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }



}