package com.example.warcardgame

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.warcardgame.databinding.ActivityGameBinding
import kotlin.random.Random

/**
 * Class where the Game happens
 */
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

    /**
     * Method to Draw a card and play a round
     */
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

            if (playerOneCard.value == playerTwoCard.value) {
                warFunction(playerOneCard,playerTwoCard)

            }else if (playerOneCard.value > playerTwoCard.value) {
                player.points += 1
                println("Player wins the round! Player points: ${player.points}")
            }

            else {
                cpu.points += 1
                println("Cpu wins the round! Cpu points: ${cpu.points}")
                }

            binding.player1TextView.text = player.points.toString()
            binding.player2TextView.text = cpu.points.toString()

        }
    }

    private fun warFunction (playerOneCard : Card, playerTwoCard : Card) {
        binding.warTextView.visibility = View.VISIBLE

        val anim = ObjectAnimator.ofFloat(binding.playerOneImgView, "rotationY", 0f, 360f)
        anim.duration = 500
        anim.start()

        val anim2 = ObjectAnimator.ofFloat(binding.playerTwoImgView, "rotationY", 0f, 360f)
        anim2.duration = 500
        anim2.start()
    }
}