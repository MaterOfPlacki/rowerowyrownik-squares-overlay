package com.michaelwhyte.rowerowyrownik.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.michaelwhyte.rowerowyrownik.client.SquareClient
import com.michaelwhyte.rowerowyrownik.model.client.Square
import com.michaelwhyte.rowerowyrownik.model.data.SquaresMap
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SquareService(
    private val client: SquareClient
) {

    var squaresMap: SquaresMap = initSquaresMap()

    fun getSquareMap(): SquaresMap {
        refreshSquaresMapIfNecessary()
        return squaresMap
    }

    final fun initSquaresMap(): SquaresMap {
        return SquaresMap(getSquareData())
    }

    final fun refreshSquaresMapIfNecessary() {
        if(LocalDate.now().dayOfYear != this.squaresMap.date.dayOfYear) {
            squaresMap = initSquaresMap()
        }
    }

    fun getSquareData(): List<Square> {
        return jacksonObjectMapper().readValue(loadTestResource("test-json/squares.json"))
    }
}
