package com.michaelwhyte.rowerowyrownik.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.michaelwhyte.rowerowyrownik.client.SquareClient
import com.michaelwhyte.rowerowyrownik.model.client.Square
import org.springframework.stereotype.Service

@Service
class SquareService(
    private val client: SquareClient
) {

    fun getSquareData(): List<Square> {
        return jacksonObjectMapper().readValue(loadTestResource("test-json/squares.json"))
    }

}