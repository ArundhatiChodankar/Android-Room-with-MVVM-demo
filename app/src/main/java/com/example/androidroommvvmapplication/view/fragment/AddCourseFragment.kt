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
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.androidroommvvmapplication.R
import com.example.androidroommvvmapplication.databinding.FragmentAddCourseBinding
import com.example.androidroommvvmapplication.databinding.FragmentCourseListBinding
import com.example.androidroommvvmapplication.model.data.model.Course
import com.example.androidroommvvmapplication.view.activity.MainActivity
import com.example.androidroommvvmapplication.view.utils.CommonUtils.Companion.getDateInDisplayFormat
import com.example.androidroommvvmapplication.viewmodel.viewmodel.CourseViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddCourseFragment : Fragment(R.layout.fragment_add_course), OnItemSelectedListener {


    private lateinit var startDate: Date
    private lateinit var category: String
    private lateinit var courseViewModel: CourseViewModel
    private var binding: FragmentAddCourseBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddCourseBinding.bind(view)

        courseViewModel = (activity as MainActivity).courseViewModel


        binding?.spinner?.apply {
            val category = resources.getStringArray(R.array.categories)
            val arrayAdapter =
                context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, category) }
            onItemSelectedListener = this@AddCourseFragment
            adapter = arrayAdapter
        }

        binding?.startDateET?.setOnClickListener {
            it.isFocusable = false
            showDatePickerDialog()

        }

        binding?.saveBTN?.setOnClickListener {
            val name = binding?.nameET?.text.toString()
            val description = binding?.descriptionET?.text.toString()
            val duration = binding?.durationET?.text.toString()
            Log.d("EDIT", "onViewCreated: $category")
            val course = Course(
                name = name,
                description = description,
                duration = duration,
                startDate = startDate,
                type = category
            )
            courseViewModel.insertCourse(course)
            findNavController().navigateUp()

        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        category = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

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
            startDate = calendar.time
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