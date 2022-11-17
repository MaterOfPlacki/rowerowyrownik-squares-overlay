package com.michaelwhyte.rowerowyrownik.controller

import com.michaelwhyte.rowerowyrownik.client.SquareClient
import com.michaelwhyte.rowerowyrownik.service.OverlayImageService
import com.michaelwhyte.rowerowyrownik.service.loadResource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class OverlayImageController(
    private val client: SquareClient,
    private val service: OverlayImageService
) {

//    // todo remove
//    @GetMapping("/test")
//    fun testEndpoint(): String {
//        return "Witojcie!"
//    }
//
//    @GetMapping("/get-squares")
//    fun getSquares(): String {
//        return "Message is ${client.squaresFile().size}"
//    }
//
//    @GetMapping("/get-test-squares")
//    fun getTestSquares(): String {
//        val squareMap = service.getSquareMap()
//
//        return "Message is ${service.getSquareDataJson().size}"
//    }

//    @GetMapping("/square/{x}/{y}")
//    fun getTestSquare(@PathVariable x: Int, @PathVariable y: Int): Boolean {
//        return service.getSquareMap().isSquareCompleted(x, y)
//    }

    @GetMapping(
        value = ["/square-overlay/{zoom}/{x}/{y}"],
        produces = [MediaType.IMAGE_PNG_VALUE]
    )
    fun getSquareOverlay(@PathVariable zoom: Int, @PathVariable x: Int, @PathVariable y: Int): ByteArray? {
        return service.getOverlay(zoom, x, y)
    }

}