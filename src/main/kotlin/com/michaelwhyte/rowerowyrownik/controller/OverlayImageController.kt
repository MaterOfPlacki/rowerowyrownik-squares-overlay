package com.michaelwhyte.rowerowyrownik.controller

import com.michaelwhyte.rowerowyrownik.client.SquareClient
import com.michaelwhyte.rowerowyrownik.service.ExampleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OverlayImageController(
    private val client: SquareClient,
    private val service: ExampleService
) {

    @GetMapping("/test")
    fun testEndpoint(): String {
        return "Witojcie!"
    }

    @GetMapping("/test-message")
    fun testEndpointWithMessage(): String {
        return "Message is ${client.test()}"
    }


}