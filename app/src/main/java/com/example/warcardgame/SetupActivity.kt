package com.example.warcardgame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.warcardgame.databinding.ActivitySetupBinding


/*TODO
        SetupActivity : Knapp : Let's play!
        GameActivity : CardView,DrawButton,PoängSystem, Previous Round highlights
        WarActivity? WarFragment? kom på vad som händer om man får lika.
        ResultActivity : Presentera resultat
        Klämma in sharedPrefs? gson?

 */

class SetupActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySetupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.playButton.setOnClickListener {
            val newIntent = Intent(this,GameActivity::class.java)
            startActivity(newIntent)
        }
    }
}