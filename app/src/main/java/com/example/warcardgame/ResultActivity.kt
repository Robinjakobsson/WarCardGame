package com.example.warcardgame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.warcardgame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val playerScore = intent.getIntExtra("p1Value",0)
        val cpuscore = intent.getIntExtra("cpuValue",0)

        if (playerScore > cpuscore) {
            binding.resultText.text = "Player has won with $playerScore points!"
        }else if (playerScore < cpuscore) {
            binding.resultText.text = "Cpu has won with $cpuscore points!"
        }
        else {
            binding.resultText.text = "Tie!"
        }

        binding.playerValue.text = playerScore.toString()
        binding.cpuValue.text = cpuscore.toString()

        binding.playButton.setOnClickListener {
            val newIntent = Intent(this,SetupActivity::class.java)
            startActivity(newIntent)
        }
    }
}