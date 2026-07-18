package net.ccbluex.liquidbounce.features.module.modules.render.popchams

import net.minecraft.world.phys.Vec3

internal object PopChamsMotion {

    fun verticalDelta(ySpeed: Int): Double = ySpeed / 50.0

    fun nextPosition(current: Vec3, ySpeed: Int): Vec3 =
        current.add(0.0, verticalDelta(ySpeed), 0.0)
}
