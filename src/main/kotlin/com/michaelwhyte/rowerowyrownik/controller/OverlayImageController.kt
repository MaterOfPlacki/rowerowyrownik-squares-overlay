package com.michaelwhyte.rowerowyrownik.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.michaelwhyte.rowerowyrownik.client.SquareClient
import com.michaelwhyte.rowerowyrownik.service.SquareService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OverlayImageController(
    private val client: SquareClient,
    private val service: SquareService
) {

    // todo remove
    @GetMapping("/test")
    fun testEndpoint(): String {
        return "Witojcie!"
    }

    @GetMapping("/get-squares")
    fun getSquares(): String {
        return "Message is ${client.squaresFile().size}"
    }

    @GetMapping("/get-test-squares")
    fun getTestSquares(): String {


        return "Message is ${service.getSquareData().size}"
    }


}