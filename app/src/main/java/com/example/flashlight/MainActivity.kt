package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.flashlight.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var isFlashlightActive: Boolean = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraWithFlashlight = cameraManager.cameraIdList.find {
            cameraManager.getCameraCharacteristics(it)
                .get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
        }

        binding.flashlightButton.setOnClickListener {
            if (cameraWithFlashlight != null) {
                onFlashlightClicked(cameraManager, cameraWithFlashlight)
            } else {

            }
        }
    }

    private fun onFlashlightClicked(
        cameraManager: CameraManager,
        cameraId: String
    ) {
        if (!isFlashlightActive) {
            binding.flashlightButton.setImageDrawable(
                AppCompatResources.getDrawable(this, R.drawable.baseline_flashlight_on_24)
            )
            cameraManager.setTorchMode(cameraId, true)
        } else {
            binding.flashlightButton.setImageDrawable(
                AppCompatResources.getDrawable(this, R.drawable.baseline_flashlight_off_24)
            )
            cameraManager.setTorchMode(cameraId, false)
        }
        isFlashlightActive = !isFlashlightActive
    }
}
