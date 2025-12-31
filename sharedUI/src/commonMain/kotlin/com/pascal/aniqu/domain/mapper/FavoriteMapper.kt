package com.pascal.aniqu.domain.mapper

import com.pascal.aniqu.domain.usecase.local.LocalUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun LocalUseCase.getFavoriteTitlesFlow(): Flow<Set<String>> = flow {
    emit(getFavorite()?.map { it.title }?.toSet() ?: emptySet())
}