package com.example.lokflix.Views

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import com.example.lokflix.R
import com.example.lokflix.databinding.ActivityVideoPlayerBinding

class VideoPlayer : AppCompatActivity() {
    private lateinit var binding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate( layoutInflater  )
        setContentView(binding.root)

        val videoView:VideoView


        videoView = binding.videoView

        val video =intent.extras?.getString("video")

        videoView.setMediaController(MediaController(this))
        videoView.setVideoURI(Uri.parse(video))
        videoView.requestFocus()
        videoView.start()





    }
}