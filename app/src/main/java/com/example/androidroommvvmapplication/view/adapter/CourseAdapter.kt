package com.example.androidroommvvmapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.androidroommvvmapplication.databinding.CourseItemLayoutBinding
import com.example.androidroommvvmapplication.model.data.model.Course
import com.example.androidroommvvmapplication.view.interfaces.ItemClickListener
import com.example.androidroommvvmapplication.view.utils.CommonUtils
import com.example.androidroommvvmapplication.view.utils.CommonUtils.Companion.getDateInDisplayFormat
import java.text.SimpleDateFormat
import java.util.Locale

class CourseAdapter(
    private val context: Context,
    private var itemClickListener: ItemClickListener,
) : RecyclerView.Adapter<CourseAdapter.MyViewHolder>() {


    class MyViewHolder(private val binding: CourseItemLayoutBinding) : ViewHolder(binding.root) {
        fun bind(course: Course, itemClickListener: ItemClickListener) {
            binding.course = course
            binding.deleteIV.setOnClickListener {
                itemClickListener.onItemDeleteClick(course)
            }
            binding.courseEditIV.setOnClickListener {
                itemClickListener.onItemEditClick(course)
            }
            updateSelectedDateET(binding)
        }

        private fun updateSelectedDateET(binding: CourseItemLayoutBinding) {
           val startDate = binding.course?.startDate?.let { getDateInDisplayFormat(it)}
            binding.startDateTV.text = startDate
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CourseItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Course>() {

        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position],itemClickListener)


    }


}

