package com.michaelwhyte.rowerowyrownik.service

import org.springframework.stereotype.Service
import java.awt.Color
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import kotlin.math.pow

@Service
class OverlayImageService(
    val squareService: SquareService
) {
    val BASE_ZOOM_LEVEL = 14
    val COLOR = Color(255,45, 206, 127)
    val tileImage = generateSingeTile()

//    val image: ByteArray = loadResource("static/overlay.png").readBytes()
//    val image: ByteArray = loadResource("static/overlay_32x32.png").readBytes()
    fun getOverlay(zoom: Int, x: Int, y: Int): ByteArray? {
        if (zoom < BASE_ZOOM_LEVEL) return generate(zoom, x, y)
        if (zoom == BASE_ZOOM_LEVEL && squareService.isSquareCompleted(x, y)) return tileImage
        if (zoom > BASE_ZOOM_LEVEL && isSquareCompletedForZoomedOut(zoom, x, y)) return tileImage

        return null
    }

    fun isSquareCompletedForZoomedOut(zoom: Int, x: Int, y: Int): Boolean {
        val power = zoom - BASE_ZOOM_LEVEL
        val magnitude = 2.toDouble().pow(power.toDouble())

        return squareService.isSquareCompleted((x/magnitude).toInt(), (y/magnitude).toInt())
    }

    fun generate(zoom: Int, x: Int, y: Int): ByteArray? {
        val power = BASE_ZOOM_LEVEL - zoom
        val magnitude = 2.toDouble().pow(power.toDouble()).toInt()

        val edge = 256
        val calculatedEdge = (edge / magnitude).toInt()

        val finalImage = BufferedImage(edge, edge, BufferedImage.TYPE_INT_ARGB)
        val graphics2D = finalImage.createGraphics()
        graphics2D.color = COLOR

        for (i in 0..magnitude) {
            for (j in 0..magnitude) {
                if(squareService.isSquareCompleted((x*magnitude.toInt()) + i, (y*magnitude.toInt()) + j)) {
                        graphics2D.fill(Rectangle(i*calculatedEdge, j*calculatedEdge, calculatedEdge, calculatedEdge))
                }
            }
        }

        graphics2D.dispose();

        val out  = ByteArrayOutputStream()
        ImageIO.write(finalImage, "png", out)

        return out.toByteArray()
    }

    fun generateSingeTile(): ByteArray {
        val edge = 256
        val finalImage = BufferedImage(edge, edge, BufferedImage.TYPE_INT_ARGB)
        val graphics2D = finalImage.createGraphics()
        graphics2D.color = COLOR
        graphics2D.fill(Rectangle(0, 0, edge, edge))
        graphics2D.dispose();

        val out  = ByteArrayOutputStream()
        ImageIO.write(finalImage, "png", out)

        return out.toByteArray()
    }

}