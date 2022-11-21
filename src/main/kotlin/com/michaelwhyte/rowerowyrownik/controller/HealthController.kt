package com.michaelwhyte.rowerowyrownik.controller

import com.michaelwhyte.rowerowyrownik.client.SquareClient
import com.michaelwhyte.rowerowyrownik.service.OverlayImageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class HealthController(
    private val client: SquareClient,
    private val service: OverlayImageService
) {

    @GetMapping(value = ["health"])
    fun getSquareOverlay(@PathVariable zoom: Int, @PathVariable x: Int, @PathVariable y: Int): String {
        return "OK"
    }

}