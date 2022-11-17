package com.michaelwhyte.rowerowyrownik.client

import com.michaelwhyte.rowerowyrownik.model.client.Square
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "\${rowerowyrownik.service-name}",
    url = "\${rowerowyrownik.service-address}"
)
interface SquareClient {

    @GetMapping("\${rowerowyrownik.endpoint.squares}")
    fun squaresFile(): List<Square>

}