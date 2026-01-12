package com.pascal.aniqu.ui.screen.detail.streaming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import chaintech.videoplayer.host.MediaPlayerHost
import chaintech.videoplayer.model.ScreenResize
import chaintech.videoplayer.model.VideoPlayerConfig
import chaintech.videoplayer.ui.video.VideoPlayerComposable
import com.multiplatform.webview.request.RequestInterceptor
import com.multiplatform.webview.request.WebRequest
import com.multiplatform.webview.request.WebRequestInterceptResult
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.WebViewNavigator
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState
import com.pascal.aniqu.ui.screen.detail.streaming.state.AnimeStreamingUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun AnimeStreamingScreen(
    modifier: Modifier = Modifier,
    uiState: AnimeStreamingUIState = AnimeStreamingUIState()
) {
    val stream = uiState.stream ?: return

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (!stream.isEmbed) {
            val playerHost = remember(stream.url) {
                MediaPlayerHost(
                    mediaUrl = stream.url,
                    autoPlay = true,
                    isLooping = false,
                    isFullScreen = true,
                    initialVideoFitMode = ScreenResize.FILL
                )
            }

            VideoPlayerComposable(
                modifier = Modifier.fillMaxSize(),
                playerHost = playerHost,
                playerConfig = VideoPlayerConfig(isFullScreenEnabled = false)
            )
        } else {
            val webViewState = rememberWebViewState(
                url = stream.url,
                extraSettings = {
                    this.isJavaScriptEnabled = true
                }
            )

            val navigator =
                rememberWebViewNavigator(
                    requestInterceptor =
                        object : RequestInterceptor {
                            override fun onInterceptUrlRequest(
                                request: WebRequest,
                                navigator: WebViewNavigator,
                            ): WebRequestInterceptResult {
                                return if (request.url.contains("kotlin")) {
                                    WebRequestInterceptResult.Modify(
                                        WebRequest(
                                            url = "https://kotlinlang.org/docs/multiplatform.html",
                                            headers = mutableMapOf("info" to "test"),
                                        ),
                                    )
                                } else {
                                    WebRequestInterceptResult.Allow
                                }
                            }
                        },
                )

            WebView(
                modifier = Modifier.fillMaxSize(),
                state = webViewState
            )
        }
    }
}

@Preview
@Composable
private fun AnimeStreamingPreview() {
    AppTheme {
        AnimeStreamingScreen()
    }
}

