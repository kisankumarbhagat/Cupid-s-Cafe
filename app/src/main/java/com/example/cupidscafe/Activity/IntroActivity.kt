package com.example.cupidscafe.Activity

import android.content.Intent
import android.os.Bundle
import com.example.cupidscafe.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding=ActivityIntroBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_intro)
        setContentView(binding.root)

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        binding.startBtn.setOnClickListener{
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }
    }
}