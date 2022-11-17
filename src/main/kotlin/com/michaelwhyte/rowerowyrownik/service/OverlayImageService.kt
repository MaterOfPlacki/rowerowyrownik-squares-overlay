package com.michaelwhyte.rowerowyrownik.service

import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class OverlayImageService(
    val squareService: SquareService
) {
    val BASE_ZOOM_LEVEL = 14

    val image: ByteArray = loadResource("static/overlay.png").readBytes()
    fun getOverlay(zoom: Int, x: Int, y: Int): ByteArray? {
        if (zoom < BASE_ZOOM_LEVEL) return null // todo implement dynamic image creation
        if (zoom == BASE_ZOOM_LEVEL && squareService.isSquareCompleted(x, y)) return image
        if (zoom > BASE_ZOOM_LEVEL && isSquareCompletedForZoomedOut(zoom, x, y)) return image

        return null
    }

    fun isSquareCompletedForZoomedOut(zoom: Int, x: Int, y: Int): Boolean {
        val power = zoom - BASE_ZOOM_LEVEL
        val magnitude = 2.toDouble().pow(power.toDouble())

        return squareService.isSquareCompleted((x/magnitude).toInt(), (y/magnitude).toInt())
    }

}