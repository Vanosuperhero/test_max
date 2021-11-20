package com.example.test_compose.presentation.ui.card



sealed class CardEvent{

    data class GetCardEvent(
        val id: Int
    ): CardEvent()
}