package com.example.warcardgame

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.warcardgame.databinding.ActivityMainBinding
import kotlin.random.Random

class SetupActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val Deck = Deck()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.drawButton.setOnClickListener {
            drawCards()
        }
    }
    fun drawCards(){
        val playerOneIndex = Random.nextInt(Deck.deck.size)
        val playerTwoIndex = Random.nextInt(Deck.deck.size)

        val playerOneCard = Deck.deck[playerOneIndex]
        val playerTwoCard = Deck.deck[playerTwoIndex]

        Deck.deck.removeAt(playerOneIndex)
        Deck.deck.removeAt(playerTwoIndex)

        binding.p1ImageView.setImageResource(playerOneCard.imageResourceId)
        binding.p2ImageView.setImageResource(playerTwoCard.imageResourceId)




    }
}