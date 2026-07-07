package net.ccbluex.liquidbounce.utils.aiming.projectiles
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.utils.aiming.utils.findVisiblePointFromVirtualEye
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3

/**
 * Utility class which finds a visible (= hittable) point on the target.
 */
object ProjectileTargetPointFinder {
    fun findHittablePosition(
        playerHeadPosition: Vec3,
        directionOnImpact: Vec3,
        entityPositionOnImpact: Vec3,
        targetEntityBox: AABB
    ): Vec3? {
        val virtualEyes = playerHeadPosition.add(
            0.0,
            directionOnImpact.y * -(playerHeadPosition.distanceTo(entityPositionOnImpact)),
            0.0
        )
        return findVisiblePointFromVirtualEye(virtualEyes, targetEntityBox, 5.0)
    }
}
