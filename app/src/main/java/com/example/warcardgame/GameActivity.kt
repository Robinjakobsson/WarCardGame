package com.example.warcardgame

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.warcardgame.databinding.ActivityGameBinding
import kotlin.random.Random
import kotlin.random.nextInt

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
                warSpin(playerOneCard, playerTwoCard)


            } else if (playerOneCard.value > playerTwoCard.value) {
                player.points += 1
                println("Player wins the round! Player points: ${player.points}")
            } else {
                cpu.points += 1
                println("Cpu wins the round! Cpu points: ${cpu.points}")
            }

            binding.player1TextView.text = player.points.toString()
            binding.player2TextView.text = cpu.points.toString()

        } else {
            val newIntent = Intent(this, ResultActivity::class.java)
            newIntent.putExtra("p1Value", player.points)
            newIntent.putExtra("cpuValue", cpu.points)
            startActivity(newIntent)
        }
    }

    private fun warSpin (playerOneCard : Card, playerTwoCard : Card) {
        binding.warTextView.visibility = View.VISIBLE

        val anim = ObjectAnimator.ofFloat(binding.playerOneImgView, "rotationY", 0f, 360f)
        anim.duration = 500
        anim.start()

        val anim2 = ObjectAnimator.ofFloat(binding.playerTwoImgView, "rotationY", 0f, 360f)
        anim2.duration = 500
        anim2.start()

        war(playerOneCard,playerTwoCard)
    }
    private fun war(playerOneCard: Card,playerTwoCard: Card) {
        if (deck.warDeck.size < 8 && player.points > cpu.points) {
            switchActivity()

        }else if (deck.warDeck.size < 8 && player.points < cpu.points) {
           switchActivity()
        }

        else {

            val playerWarCards = mutableListOf<Card>()
            val cpuWarCards = mutableListOf<Card>()

            playerWarCards.add(playerOneCard)
            cpuWarCards.add(playerTwoCard)

            repeat(3) {
                playerWarCards.add(drawWarCards(deck.warDeck))
                cpuWarCards.add(drawWarCards(deck.warDeck))
            }

            binding.p1c1.setImageResource(playerWarCards[0].imageResourceId)
            binding.p1c2.setImageResource(playerWarCards[1].imageResourceId)
            binding.p1c3.setImageResource(playerWarCards[2].imageResourceId)
            binding.p1c4.setImageResource(playerWarCards[3].imageResourceId)

            binding.p2c1.setImageResource(cpuWarCards[0].imageResourceId)
            binding.p2c2.setImageResource(cpuWarCards[1].imageResourceId)
            binding.p2c3.setImageResource(cpuWarCards[2].imageResourceId)
            binding.p2c4.setImageResource(cpuWarCards[3].imageResourceId)

            val playerValue = playerWarCards.sumOf { it.value }
            val cpuValue = cpuWarCards.sumOf { it.value }

            println("Player's total card value: ${playerValue}")
            println("CPU's total card value: $cpuValue")

            binding.warValuep1.text = playerValue.toString()
            binding.warValuep2.text = cpuValue.toString()

            if (playerValue > cpuValue) {
                player.points += 1
            }

            if (playerValue < cpuValue) {
                cpu.points += 1
            } else {
                war(playerOneCard, playerTwoCard)
            }
            binding.player1TextView.text = player.points.toString()
            binding.player2TextView.text = cpu.points.toString()
        }

    }
    private fun drawWarCards (deck: MutableList<Card>) : Card {
        if (deck.isEmpty()) {
            val randomValue = Random.nextInt(2..14)
            println("Deck is empty. Printing Joker")
            val randomCard = Card("Joker",randomValue,R.drawable.joker)
            return randomCard
        }
        val randomIndex = Random.nextInt(deck.size)
        val drawnCard = deck[randomIndex]
        deck.removeAt(randomIndex)
        return drawnCard
    }
    private fun switchActivity () {
        val newIntent = Intent(this, ResultActivity::class.java)
        newIntent.putExtra("p1Value", player.points)
        newIntent.putExtra("cpuValue", cpu.points)
        startActivity(newIntent)
    }

}