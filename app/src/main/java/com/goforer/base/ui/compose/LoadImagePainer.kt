package com.goforer.base.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.DataSource
import coil.decode.Decoder
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun loadImagePainter(
    data: Any,
    factory: Decoder.Factory? = null,
    size: Size
): AsyncImagePainter {
    return if (factory != null)
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data)
                .decoderFactory(factory)
                .size(size)
                .build()
        )
    else
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data)
                .size(size)
                .build()
        )
}

@Composable
fun ImageCrossFade(painter: AsyncImagePainter, durationMillis: Int?) {
    val painterState = painter.state

    if (painterState is AsyncImagePainter.State.Success && painterState.result.dataSource != DataSource.MEMORY_CACHE) {
        if (durationMillis != null && durationMillis > 0)
            painter.imageLoader.newBuilder().crossfade(durationMillis)
        else
            painter.imageLoader.newBuilder().crossfade(true)
    }
}