package com.michaelwhyte.rowerowyrownik.service

import com.michaelwhyte.rowerowyrownik.client.SquareClient
import org.springframework.stereotype.Service

@Service
class ExampleService(
    private val client: SquareClient
) {
}