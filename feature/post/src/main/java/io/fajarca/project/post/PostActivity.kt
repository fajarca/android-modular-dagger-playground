package io.fajarca.project.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.fajarca.project.base.router.Routable
import io.fajarca.project.post.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPostBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val extras = intent.extras
        val data = extras?.getParcelable(Routable.ROUTE_DATA) as? io.fajarca.project.common.route.PostRouterData
            ?: return
        val postId = data.postId

        binding.tvPostId.text = "I received post id $postId"
    }
}