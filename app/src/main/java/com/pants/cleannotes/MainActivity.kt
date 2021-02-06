package com.pants.cleannotes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pants.cleannotes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fabClickListener.observe(this) {
            binding.fab.apply {
                if (it == null) hide() else show()
                setOnClickListener(it)
            }
        }

        viewModel.loading.observe(this) {
            binding.progress.apply {
                if (it == true) show() else hide()
            }
        }
    }
}