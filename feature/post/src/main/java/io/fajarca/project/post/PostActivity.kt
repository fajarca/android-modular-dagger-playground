package io.fajarca.project.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.fajarca.project.base.router.Routable
import io.fajarca.project.common.route.PostRouterData
import io.fajarca.project.post.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPostBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val extras = intent.extras
        val data = extras?.getParcelable(Routable.ROUTE_DATA) as? PostRouterData
        val postId = data?.postId

        binding.tvPostId.text = "I received post id $postId"
    }
}