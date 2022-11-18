package com.michaelwhyte.rowerowyrownik.controller

import com.michaelwhyte.rowerowyrownik.client.SquareClient
import com.michaelwhyte.rowerowyrownik.service.OverlayImageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.awt.Color
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import javax.imageio.ImageIO


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

    @GetMapping(
        value = ["/generate"],
        produces = [MediaType.IMAGE_PNG_VALUE]
    )
    fun generate(): ByteArray? {
        val zoom = 12
        val x = 2315
        val y = 1378

        val EDGE = 256

        val finalImage = BufferedImage(EDGE, EDGE, BufferedImage.TYPE_INT_ARGB)
        val graphics2D = finalImage.createGraphics()
        graphics2D.color = Color(255,45, 206, 127)

        graphics2D.fill(Rectangle(0, 0, 128, 128))

        graphics2D.dispose();



        val out  = ByteArrayOutputStream()
        ImageIO.write(finalImage, "png", out)

        return out.toByteArray()
    }


}