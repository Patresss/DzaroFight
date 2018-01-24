package com.patres.dzarofight.helper

import org.jbox2d.common.Vec2
import processing.core.PApplet
import processing.core.PImage
import processing.core.PVector
import java.awt.Color
import java.text.DecimalFormat

fun PApplet.fill(color: Color) {
    this.fill(color.rgb)
}

fun PImage.flipVerticalImage(): PImage {
    val verticalImage = PImage(this.width, this.height)
    for (w in 0..this.width) {
        for (h in 0..this.height) {
            val orgColor = this.get(w, h)
            verticalImage.set(this.width - w, h, orgColor)
        }
    }
    return verticalImage
}

fun Float.format(fracDigits: Int): String {
    val df = DecimalFormat()
    df.maximumFractionDigits = fracDigits
    return df.format(this)
}

fun PApplet.fill(color: Color, alpha: Float) {
    fill(color.red.toFloat(), color.green.toFloat(), color.blue.toFloat(), alpha)
}

fun Vec2.toPVector() = PVector(x, y)