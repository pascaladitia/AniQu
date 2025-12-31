package com.pascal.aniqu.ui.screen.bookmark

import androidx.compose.runtime.Composable
import com.pascal.aniqu.data.local.entity.FavoritesEntity
import org.koin.compose.koinInject

@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = koinInject<BookmarkViewModel>(),
    onNavBack: () -> Unit,
    onDetail: (FavoritesEntity?) -> Unit
) {

}