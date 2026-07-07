package net.ccbluex.liquidbounce.render
import net.ccbluex.liquidbounce.utils.entity.*

import com.mojang.blaze3d.vertex.VertexConsumer
import org.joml.Matrix4f

fun VertexConsumer.vertex(matrix: Matrix4f, x: Float, y: Float, z: Float): VertexConsumer =
    addVertex(matrix, x, y, z)

fun VertexConsumer.vertex(x: Float, y: Float, z: Float): VertexConsumer =
    addVertex(x, y, z)

fun VertexConsumer.texture(u: Float, v: Float): VertexConsumer = setUv(u, v)

fun VertexConsumer.color(argb: Int): VertexConsumer = setColor(argb)

fun VertexConsumer.color(red: Int, green: Int, blue: Int, alpha: Int): VertexConsumer =
    setColor(red, green, blue, alpha)
