package io.fajarca.project.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.fajarca.project.post.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPostBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val extras = intent.extras
        val id = extras?.getString("id")
        binding.tvPostId.text = "I received id $id"
    }
}