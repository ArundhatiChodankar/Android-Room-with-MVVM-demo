package com.example.androidroommvvmapplication.view.fragment

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidroommvvmapplication.R
import com.example.androidroommvvmapplication.databinding.FragmentEditCourseBinding
import com.example.androidroommvvmapplication.model.data.model.Course
import com.example.androidroommvvmapplication.view.activity.MainActivity
import com.example.androidroommvvmapplication.view.utils.CommonUtils.Companion.getDateInDisplayFormat
import com.example.androidroommvvmapplication.viewmodel.viewmodel.CourseViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class EditCourseFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var binding: FragmentEditCourseBinding? = null

    private val args: EditCourseFragmentArgs by navArgs()

    private lateinit var course: Course

    private  val TAG = "EditCourseFragment"

    private lateinit var courseViewModel: CourseViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentEditCourseBinding.inflate(layoutInflater)


        courseViewModel = (activity as MainActivity).courseViewModel

        course = args.course
        binding?.course = course

        val category = resources.getStringArray(R.array.categories)
        val index = category.indexOf(course.type)
        binding?.spinner?.setSelection(index)

        val calendar = Calendar.getInstance()
        calendar.time = course.startDate
        updateSelectedDateET(calendar)

        binding?.spinner?.apply {
            val arrayAdapter =
                context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, category) }
            onItemSelectedListener = this@EditCourseFragment
            adapter = arrayAdapter
        }

        binding?.startDateET?.setOnClickListener {
            it.isFocusable = false
            showDatePickerDialog()
        }

        binding?.editBTN?.setOnClickListener {

            course. name = binding?.nameET?.text.toString()
            course. description = binding?.descriptionET?.text.toString()
            course. duration = binding?.durationET?.text.toString()
            courseViewModel.updateCourse(course)
            findNavController().navigateUp()
        }

        return binding?.root
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        course.type = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            course.startDate = calendar.time
            updateSelectedDateET(calendar)
        }


        val dialog =
            DatePickerDialog(requireContext(), datePicker, currentYear, currentMonth, currentDay)
        dialog.datePicker.minDate = calendar.timeInMillis
        dialog.show()
    }

    private fun updateSelectedDateET(calendar: Calendar?) {
        val startDate = calendar?.let { getDateInDisplayFormat(it.time) }
        binding?.startDateET?.setText(startDate)

    }

}