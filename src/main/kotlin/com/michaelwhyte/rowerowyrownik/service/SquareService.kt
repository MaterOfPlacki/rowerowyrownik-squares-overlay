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

    fun isSquareCompleted(x: Int, y: Int) = getSquareMap().isSquareCompleted(x, y)

    fun getSquareMap(): SquaresMap {
        refreshSquaresMapIfNecessary()
        return squaresMap
    }

    private final fun initSquaresMap(): SquaresMap {
        return SquaresMap(getSquareDataJson())
    }

    private final fun refreshSquaresMapIfNecessary() {
        if(LocalDate.now().dayOfYear != this.squaresMap.date.dayOfYear) {
            squaresMap = initSquaresMap()
        }
    }

    fun getSquareDataJson(): List<Square> {
        return jacksonObjectMapper().readValue(loadResource("test-json/squares.json"))
    }
}
