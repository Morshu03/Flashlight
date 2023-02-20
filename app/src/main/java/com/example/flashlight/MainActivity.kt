package com.example.flashlight

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flashlight.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var fls: Boolean = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        flashlight()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun flashlight() {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        binding.flashlightButton.setOnClickListener {
            if (!fls) {
                try {
                    binding.flashlightButton.setImageDrawable(getDrawable(R.drawable.baseline_flashlight_on_24))
                    cameraManager.setTorchMode(cameraId, true)
                    fls = true
                } catch (_: CameraAccessException) {
                }
            } else {
                try {
                    binding.flashlightButton.setImageDrawable(getDrawable(R.drawable.baseline_flashlight_off_24))
                    cameraManager.setTorchMode(cameraId, false)
                    fls = false
                } catch (_: CameraAccessException) {
                }
            }
        }
    }
}
