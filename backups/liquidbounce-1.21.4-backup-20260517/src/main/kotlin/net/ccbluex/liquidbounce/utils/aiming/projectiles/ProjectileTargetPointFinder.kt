package net.ccbluex.liquidbounce.utils.aiming.projectiles

import net.ccbluex.liquidbounce.utils.aiming.utils.findVisiblePointFromVirtualEye
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d

/**
 * Utility class which finds a visible (= hittable) point on the target.
 */
object ProjectileTargetPointFinder {
    fun findHittablePosition(
        playerHeadPosition: Vec3d,
        directionOnImpact: Vec3d,
        entityPositionOnImpact: Vec3d,
        targetEntityBox: Box
    ): Vec3d? {
        val virtualEyes = playerHeadPosition.add(
            0.0,
            directionOnImpact.y * -(playerHeadPosition.distanceTo(entityPositionOnImpact)),
            0.0
        )
        return findVisiblePointFromVirtualEye(virtualEyes, targetEntityBox, 5.0)
    }
}
