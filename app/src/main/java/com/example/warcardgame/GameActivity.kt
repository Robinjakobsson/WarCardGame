package com.example.warcardgame

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.warcardgame.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGameBinding
    private lateinit var deck: Deck
    private lateinit var player : Player
    private lateinit var cpu : Player
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        player = Player(0)
        cpu = Player(0)
        deck = Deck()

        binding.drawButton.setOnClickListener {
            drawCard()
        }
    }

    private fun drawCard() {
        if (deck.deck.size >= 2) {

            val playerOneIndex = Random.nextInt(deck.deck.size)
            val playerTwoIndex = Random.nextInt(deck.deck.size)

            val playerOneCard = deck.deck[playerOneIndex]
            val playerTwoCard = deck.deck[playerTwoIndex]

            println("Player One Card Value: ${playerOneCard.value}, Player Two Card Value: ${playerTwoCard.value}")
            binding.playerOneImgView.setImageResource(playerOneCard.imageResourceId)
            binding.playerTwoImgView.setImageResource(playerTwoCard.imageResourceId)

            if (playerOneIndex > playerTwoIndex) {
                deck.deck.removeAt(playerOneIndex)
                deck.deck.removeAt(playerTwoIndex)
            } else {
                deck.deck.removeAt(playerTwoIndex)
                deck.deck.removeAt(playerOneIndex)
            }

            if (playerOneCard.value > playerTwoCard.value) {
                player.points += 1
                println("Player wins the round! Player points: ${player.points}")

            } else if (playerTwoCard.value > playerOneCard.value) {
                cpu.points += 1
                println("CPU wins the round! CPU points: ${cpu.points}")

            } else {
                println("It's a tie!")
            }

            binding.player1TextView.text = player.points.toString()
            binding.player2TextView.text = cpu.points.toString()
        }
    }
}