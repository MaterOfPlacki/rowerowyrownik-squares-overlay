package com.michaelwhyte.rowerowyrownik.controller

import com.michaelwhyte.rowerowyrownik.client.SquareClient
import com.michaelwhyte.rowerowyrownik.service.OverlayImageReversedService
import com.michaelwhyte.rowerowyrownik.service.OverlayImageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class OverlayImageController(
    private val client: SquareClient,
    private val imageService: OverlayImageService,
    private val reversedImageService: OverlayImageReversedService
) {

    @GetMapping(
        value = ["/square-overlay/{zoom}/{x}/{y}"],
        produces = [MediaType.IMAGE_PNG_VALUE]
    )
    fun getSquareOverlay(@PathVariable zoom: Int, @PathVariable x: Int, @PathVariable y: Int): ByteArray? {
        return imageService.getOverlay(zoom, x, y)
    }

    @GetMapping(
        value = ["/square-overlay-reversed/{zoom}/{x}/{y}"],
        produces = [MediaType.IMAGE_PNG_VALUE]
    )
    fun getSquareOverlayReversed(@PathVariable zoom: Int, @PathVariable x: Int, @PathVariable y: Int): ByteArray? {
        return reversedImageService.getOverlay(zoom, x, y)
    }

}