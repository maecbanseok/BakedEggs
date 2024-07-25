package com.example.bakedeggs.List

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.R
import com.example.bakedeggs.data.ContactDataSource
import com.example.bakedeggs.data.ContactRepositoryImpl
import com.example.bakedeggs.data.EventBus
import com.example.bakedeggs.data.ServiceLocator
import com.example.bakedeggs.databinding.FragmentListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    var isGrid = true
    private lateinit var listAdapter: ListAdapter
    private lateinit var contactRepository: ContactRepositoryImpl

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var serviceLocator: ServiceLocator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //serviceLocator를 사용하여 contactRepository 초기화
        var serviceLocator = ServiceLocator.getInstance(requireActivity().application)
        //contactRepository = ContactRepositoryImpl(serviceLocator.)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //recyclerView 초기화
        //어댑터 초기화 및 설정
        listAdapter = ListAdapter(contactRepository.getContactList())

        listAdapter.setListClickListener(object : ListAdapter.ListClick {

            override fun onClick(view: View, position: Int) {
                //클릭 이벤트 처리
            }

            override fun onPressed(view: View, position: Int) {
                //길게 클릭 이벤트 처리
            }

        })

        /*val adapter = ListAdapter(arrayList)
        val listRecyclerView : RecyclerView = findViewById(R.id.list_recyclerview)
        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.adapter = adapter*/
        initView()

    }

    fun initView() {
        serviceLocator = ServiceLocator(requireActivity().application)
        with(binding) {
            listLlGridlist.setOnClickListener {
                mainViewWhitebtn.callOnClick()
                listMlGridlist.setTransitionListener(object : MotionLayout.TransitionListener {
                    override fun onTransitionStarted(
                        motionLayout: MotionLayout?,
                        startId: Int,
                        endId: Int
                    ) {
                        return
                    }

                    override fun onTransitionChange(
                        motionLayout: MotionLayout?,
                        startId: Int,
                        endId: Int,
                        progress: Float
                    ) {
                        return
                    }

                    override fun onTransitionCompleted(
                        motionLayout: MotionLayout?,
                        currentId: Int
                    ) {
                        isGrid = motionLayout!!.currentState == motionLayout!!.startState
                        println(isGrid)
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