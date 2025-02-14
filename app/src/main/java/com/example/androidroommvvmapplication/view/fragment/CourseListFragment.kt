package com.example.androidroommvvmapplication.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidroommvvmapplication.R
import com.example.androidroommvvmapplication.databinding.FragmentCourseListBinding
import com.example.androidroommvvmapplication.model.data.model.Course
import com.example.androidroommvvmapplication.view.interfaces.ItemClickListener
import com.example.androidroommvvmapplication.view.activity.MainActivity
import com.example.androidroommvvmapplication.view.adapter.CourseAdapter
import com.example.androidroommvvmapplication.viewmodel.viewmodel.CourseViewModel


class CourseListFragment : Fragment(R.layout.fragment_course_list), ItemClickListener {

    private lateinit var courseViewModel: CourseViewModel
    private lateinit var adapter: CourseAdapter
    private var binding: FragmentCourseListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCourseListBinding.bind(view)

        courseViewModel = (activity as MainActivity).courseViewModel

        setUpRecyclerView()

        courseViewModel.courseList.observe(viewLifecycleOwner, {
            adapter.differ.submitList(it)
        })

        binding?.fabBTN?.setOnClickListener {
            val action = CourseListFragmentDirections.actionCourseListFragmentToAddCourseFragment()
            findNavController().navigate(action)
        }


    }

    private fun setUpRecyclerView() {
        adapter = context?.let { CourseAdapter(it, this) }!!

        binding?.apply {
            courseRV.layoutManager = LinearLayoutManager(context)
            courseRV.adapter = adapter
        }
    }

    override fun onItemDeleteClick(course: Course) {
        courseViewModel.deleteCourse(course)

    }

    override fun onItemEditClick(course: Course) {
        val action =
            CourseListFragmentDirections.actionCourseListFragmentToEditCourseFragment(course)
        findNavController().navigate(action)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}