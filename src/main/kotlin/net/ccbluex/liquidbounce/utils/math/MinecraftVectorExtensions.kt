/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
@file:Suppress("NOTHING_TO_INLINE", "TooManyFunctions")

package net.ccbluex.liquidbounce.utils.math
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.render.engine.type.Vec3 as RenderVec3
import net.ccbluex.liquidbounce.utils.block.Region
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.Vec3
import net.minecraft.core.Vec3i

inline operator fun BlockPos.rangeTo(other: BlockPos) = Region(this, other)

inline operator fun Vec3i.component1() = this.x
inline operator fun Vec3i.component2() = this.y
inline operator fun Vec3i.component3() = this.z

inline fun BlockPos.copy(x: Int = this.x, y: Int = this.y, z: Int = this.z) = BlockPos(x, y, z)

inline operator fun Vec3i.plus(other: Vec3i): Vec3i {
    return this.offset(other)
}

inline operator fun Vec3i.minus(other: Vec3i): Vec3i {
    return this.subtract(other)
}

inline operator fun Vec3i.times(scalar: Int): Vec3i {
    return this.multiply(scalar)
}

inline operator fun Vec3.plus(other: Vec3): Vec3 {
    return this.add(other)
}

inline operator fun Vec3.minus(other: Vec3): Vec3 {
    return this.subtract(other)
}

inline operator fun Vec3.times(scalar: Double): Vec3 {
    return this.scale(scalar)
}

inline fun Vec3.interpolate(start: Vec3, multiple: Double) =
    Vec3(
        this.x.interpolate(start.x, multiple),
        this.y.interpolate(start.y, multiple),
        this.z.interpolate(start.z, multiple),
    )

inline fun Double.interpolate(old: Double, scale: Double) = old + (this - old) * scale

inline fun Vec3.copy(x: Double = this.x, y: Double = this.y, z: Double = this.z) = Vec3(x, y, z)

inline operator fun Vec3.component1(): Double = this.x
inline operator fun Vec3.component2(): Double = this.y
inline operator fun Vec3.component3(): Double = this.z

fun Collection<Vec3>.average(): Vec3 {
    val result = doubleArrayOf(0.0, 0.0, 0.0)
    for (vec in this) {
        result[0] += vec.x
        result[1] += vec.y
        result[2] += vec.z
    }
    return Vec3(result[0] / size, result[1] / size, result[2] / size)
}

inline fun forEach3D(v0: Vec3, v1: Vec3, step: Double, fn: (Double, Double, Double) -> Unit) {
    val (startX, startY, startZ) = v0
    val (endX, endY, endZ) = v1

    var x = startX
    while (x <= endX) {
        var y = startY
        while (y <= endY) {
            var z = startZ
            while (z <= endZ) {
                fn(x, y, z)

                z += step
            }
            y += step
        }
        x += step
    }
}

inline fun forEach3D(v0: Vec3i, v1: Vec3i, step: Int = 1, fn: (Int, Int, Int) -> Unit) {
    val (startX, startY, startZ) = v0
    val (endX, endY, endZ) = v1

    var x = startX
    while (x <= endX) {
        var y = startY
        while (y <= endY) {
            var z = startZ
            while (z <= endZ) {
                fn(x, y, z)

                z += step
            }
            y += step
        }
        x += step
    }
}

fun Vec3i.toVec3d(): Vec3 = Vec3.atLowerCornerOf(this)
fun Vec3i.toVec3d(
    xOffset: Double = 0.0,
    yOffset: Double = 0.0,
    zOffset: Double = 0.0,
): Vec3 = Vec3(x + xOffset, y + yOffset, z + zOffset)

fun Vec3.toVec3() = RenderVec3(this.x.toFloat(), this.y.toFloat(), this.z.toFloat())
fun Vec3.toVec3i() = Vec3i(this.x.toInt(), this.y.toInt(), this.z.toInt())

fun Vec3.toBlockPos() = BlockPos.containing(x, y, z)!!

fun Vec3.preferOver(other: Vec3): Vec3 {
    val x = if (this.x == 0.0) other.x else this.x
    val y = if (this.y == 0.0) other.y else this.y
    val z = if (this.z == 0.0) other.z else this.z
    return Vec3(x, y, z)
}
