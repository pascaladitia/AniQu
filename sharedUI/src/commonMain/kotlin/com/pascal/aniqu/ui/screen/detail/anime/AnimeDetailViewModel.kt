package com.pascal.aniqu.ui.screen.detail.anime

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.aniqu.domain.mapper.mapToStreamList
import com.pascal.aniqu.domain.mapper.toEntity
import com.pascal.aniqu.domain.model.anime.AnimeDetail
import com.pascal.aniqu.domain.model.anime.Stream
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCase
import com.pascal.aniqu.domain.usecase.local.LocalUseCase
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
import com.pascal.aniqu.utils.download.DownloadManager
import com.pascal.aniqu.utils.showToast
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    private val localUseCase: LocalUseCase,
    private val animeUseCase: AnimeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnimeDetailUIState())
    val uiState: StateFlow<AnimeDetailUIState> = _uiState.asStateFlow()

    private val favoriteSlug = MutableStateFlow<String?>(null)

    init {
        observeFavorite()
    }

    fun setTransition(
        sharedTransitionScope: SharedTransitionScope,
        animatedVisibilityScope: AnimatedVisibilityScope
    ) {
        _uiState.update {
            it.copy(
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadAnimeDetail(slug: String?) {
        if (slug.isNullOrBlank()) return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    isLoadingStream = true,
                    error = false to "",
                    animeDetail = null
                )
            }

            val detailFlow = animeUseCase.getAnimeDetail(slug)

            val streamingFlow = detailFlow
                .mapNotNull { it?.episodes?.firstOrNull()?.slug }
                .flatMapLatest { animeUseCase.getAnimeStreaming(it) }

            val genresFlow = detailFlow
                .mapNotNull { it?.genres?.firstOrNull() }
                .flatMapLatest { animeUseCase.getAnimeGenre(it) }

            combine(detailFlow, streamingFlow, genresFlow) { detail, streaming, genres ->
                Triple(detail, streaming, genres)
            }
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoadingStream = false,
                            error = true to (e.message ?: "Unknown error")
                        )
                    }
                }
                .collect { (detail, streaming, genres) ->

                    val streamList = mapToStreamList(
                        downloads = streaming?.downloads,
                        streams = streaming?.streams
                    )

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoadingStream = false,
                            animeId = slug,
                            animeDetail = detail,
                            recomendList = genres.toImmutableList(),
                            streamList = streamList.toImmutableList(),
                            streamSelected = streamList.firstOrNull()
                        )
                    }

                    loadFavorite(slug)
                }
        }
    }

    fun loadFavorite(title: String) {
        favoriteSlug.value = title
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeFavorite() {
        viewModelScope.launch {
            favoriteSlug
                .filterNotNull()
                .distinctUntilChanged()
                .flatMapLatest { slug ->
                    localUseCase.getFavorite(slug)
                }
                .collect { isFav ->
                    _uiState.update { it.copy(isFavorite = isFav) }
                }
        }
    }

    fun loadAnimeStream(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingStream = true) }

            animeUseCase.getAnimeStreaming(id)
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoadingStream = false,
                            error = true to e.message.toString()
                        )
                    }
                }
                .collect { result ->
                    val streamList = mapToStreamList(
                        downloads = result?.downloads,
                        streams = result?.streams
                    )

                    _uiState.update {
                        it.copy(
                            isLoadingStream = false,
                            streamList = streamList.toImmutableList(),
                            streamSelected = streamList.firstOrNull()
                        )
                    }
                }
        }
    }

    fun setFavorite(anime: AnimeDetail?) {
        if (anime == null) {
            _uiState.update { it.copy(error = true to "Failed save favorite") }
            return
        }

        val modify = anime.copy(slug = _uiState.value.animeId)

        viewModelScope.launch {
            try {
                if (_uiState.value.isFavorite) {
                    localUseCase.deleteFavorite(modify.slug)
                } else {
                    localUseCase.insertFavorite(modify.toEntity())
                }

                _uiState.update {
                    it.copy(isFavorite = !_uiState.value.isFavorite)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = true to e.message.toString())
                }
            }
        }
    }


    fun loadDownload(stream: Stream) {
        val downloader  = DownloadManager()
        downloader.download(stream.url)
        showToast("Video Downloading...")
    }

    fun streamSelected(stream: Stream) {
        _uiState.update { it.copy(streamSelected = stream) }
    }

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}