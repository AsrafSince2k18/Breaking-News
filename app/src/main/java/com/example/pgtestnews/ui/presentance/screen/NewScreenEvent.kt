package com.example.pgtestnews.ui.presentance.screen

import com.example.pgtestnews.domain.data.Article

sealed class NewScreenEvent{
    data class OnCardClick(val article: Article) : NewScreenEvent()

    data class OnCategory(val category :String):NewScreenEvent()

    data class OnSearchQuery(val query : String) : NewScreenEvent()

    object SearchIconOpened : NewScreenEvent()

    object SearchIconClosed : NewScreenEvent()
}
