package com.example.warcardgame
import kotlin.random.Random
class Deck {

    private val suits = listOf("Hearts","Diamonds","Clubs","Spades")
    private val values = 2..14
    var deck: MutableList<Card> = mutableListOf()


    init {
        generateDeck()
    }

    private fun generateDeck() {
        for (suit in suits) {
            for(value in values) {
                val imageResourceId = getImageResourceID(suit,value)
                deck.add(Card(suit,value,imageResourceId))

            }
        }
        deck.shuffle()
    }
    fun getImageResourceID(suit : String,value : Int): Int {
        return when (suit) {
            "Hearts" -> when (value) {
                2 -> R.drawable.hjarter_tva
                3 -> R.drawable.hjarter_tre
                4 -> R.drawable.hjarter_fyra
                5 -> R.drawable.hjarter_fem
                6 -> R.drawable.hjarter_sex
                7 -> R.drawable.hjarter_sju
                8 -> R.drawable.hjarter_atta
                9 -> R.drawable.hjarter_nio
                10 -> R.drawable.hjarter_tio
                11 -> R.drawable.hjarter_knackt
                12 -> R.drawable.hjarter_drottning
                13 -> R.drawable.hjarter_kung
                14 -> R.drawable.hjarter_ess
                else -> R.drawable.card_backside
            }
            "Diamonds" -> when (value) {
                2 -> R.drawable.ruter_tva
                3 -> R.drawable.ruter_tre
                4 -> R.drawable.images
                5 -> R.drawable.ruter_fem
                6 -> R.drawable.ruter_sex
                7 -> R.drawable.ruter_sju
                8 -> R.drawable.ruter_atta
                9 -> R.drawable.ruter_nio
                10 -> R.drawable.ruter_tio
                11 -> R.drawable.ruter_knackt
                12 -> R.drawable.ruter_drottning
                13 -> R.drawable.ruter_kung
                14 -> R.drawable.ruter_ess
                else -> R.drawable.card_backside
            }
            "Clubs" -> when (value) {
                2 -> R.drawable.klover_tvo
                3 -> R.drawable.klover_tre
                4 -> R.drawable.klover_fyra
                5 -> R.drawable.klover_fem
                6 -> R.drawable.klover_sex
                7 -> R.drawable.klover_sju
                8 -> R.drawable.klover_atta
                9 -> R.drawable.klover_nio
                10 -> R.drawable.klover_tio
                11 -> R.drawable.klover_knackt
                12 -> R.drawable.klover_drottning
                13 -> R.drawable.klover_kung
                14 -> R.drawable.klover_ess
                else -> R.drawable.card_backside
            }
            "Spades" -> when (value) {
                2 -> R.drawable.spadertva
                3 -> R.drawable.three_of_spades
                4 -> R.drawable.spader_fyra
                5 -> R.drawable.spader_fem
                6 -> R.drawable.spader_sex
                7 -> R.drawable.spader_sju
                8 -> R.drawable.spader_atta
                9 -> R.drawable.spader_nio
                10 -> R.drawable.spader_tio
                11 -> R.drawable.spader_knackt
                12 -> R.drawable.spader_drottning
                13 -> R.drawable.spader_kung
                14 -> R.drawable.spader_ess
                else -> R.drawable.card_backside
            }

            else -> {R.drawable.joker}
        }
        return R.drawable.card_backside
    }

}