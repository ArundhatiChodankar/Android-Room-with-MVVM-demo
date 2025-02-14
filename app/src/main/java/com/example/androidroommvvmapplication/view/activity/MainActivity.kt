package com.example.androidroommvvmapplication.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.androidroommvvmapplication.R
import com.example.androidroommvvmapplication.databinding.ActivityMainBinding
import com.example.androidroommvvmapplication.model.data.model.Course
import com.example.androidroommvvmapplication.model.data.room.AppDatabase
import com.example.androidroommvvmapplication.model.repository.CourseRepository
import com.example.androidroommvvmapplication.viewmodel.viewmodel.CourseViewModel
import com.example.androidroommvvmapplication.viewmodel.viewmodelfactory.CourseViewModelProviderFactory

class MainActivity : AppCompatActivity() {

     lateinit var courseViewModel: CourseViewModel
    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        setViewNavController(binding?.fragmentContainerView!!, navController)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val courseRepository = CourseRepository(AppDatabase(this))
        val viewModelProviderFactory = CourseViewModelProviderFactory(courseRepository)
        courseViewModel = ViewModelProvider(this,viewModelProviderFactory)[CourseViewModel::class]
    }
}