package com.example.mvvmnotes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmnotes.databinding.ActivityMainBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        LoadTask().execute()
    }

    inner class LoadTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            try {
                Thread.sleep(3000)
            } catch (e: Exception) {
                Thread.interrupted()
            }
            return "success"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }
    }

}
