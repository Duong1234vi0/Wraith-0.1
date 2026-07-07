package net.ccbluex.liquidbounce.utils.render.trajectory
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.utils.client.player
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.arrow.Arrow
import net.minecraft.world.entity.projectile.arrow.ThrownTrident
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEgg
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownExperienceBottle
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion
import net.minecraft.world.item.*

object TrajectoryData {
    fun getRenderedTrajectoryInfo(player: Player, item: Item, alwaysShowBow: Boolean): TrajectoryInfo? {
        return when (item) {
            is BowItem -> {
                val useTime = if (alwaysShowBow && player.ticksUsingItem < 1) {
                    40
                } else {
                    player.ticksUsingItem
                }

                TrajectoryInfo.bowWithUsageDuration(useTime)
            }
            is CrossbowItem -> TrajectoryInfo.BOW_FULL_PULL
            is FishingRodItem -> TrajectoryInfo.FISHING_ROD
            is ThrowablePotionItem -> TrajectoryInfo.POTION
            is TridentItem -> TrajectoryInfo.TRIDENT
            is SnowballItem -> TrajectoryInfo.GENERIC
            is EnderpearlItem -> TrajectoryInfo.GENERIC
            is EggItem -> TrajectoryInfo.GENERIC
            is ExperienceBottleItem -> TrajectoryInfo.EXP_BOTTLE
            is FireChargeItem -> TrajectoryInfo.FIREBALL
            is WindChargeItem -> TrajectoryInfo.WIND_CHARGE
            else -> null
        }
    }

    fun getColorForEntity(it: Entity): Color4b {
        return when (it) {
            is Arrow -> Color4b(255, 0, 0, 200)
            is ThrownEnderpearl -> Color4b(128, 0, 128, 200)
            else -> Color4b(200, 200, 200, 200)
        }
    }

    fun getRenderTrajectoryInfoForOtherEntity(
        entity: Entity,
        activeArrows: Boolean,
        activeOthers: Boolean,
    ): TrajectoryInfo? {
        if (activeArrows && entity is Arrow && !entity.isInGround()) {
            return TrajectoryInfo(0.05, 0.3)
        }
        if (!activeOthers) {
            return null
        }

        return when (entity) {
            is ThrownSplashPotion -> TrajectoryInfo.POTION
            is ThrownTrident -> {
                if (!entity.isInGround()) {
                    TrajectoryInfo.TRIDENT
                } else {
                    null
                }
            }
            is ThrownEnderpearl -> TrajectoryInfo.GENERIC
            is Snowball -> TrajectoryInfo.GENERIC
            is ThrownExperienceBottle -> TrajectoryInfo.EXP_BOTTLE
            is ThrownEgg -> TrajectoryInfo.GENERIC
            is AbstractHurtingProjectile -> TrajectoryInfo.FIREBALL
            else -> null
        }
    }
}


data class TrajectoryInfo(
    val gravity: Double,
    /**
     * Radius (!!) of the projectile
     */
    val hitboxRadius: Double,
    val initialVelocity: Double = 1.5,
    val drag: Double = 0.99,
    val dragInWater: Double = 0.6,
    val roll: Float = 0.0F,
    val copiesPlayerVelocity: Boolean = true,
) {
    companion object {
        val GENERIC = TrajectoryInfo(0.03, 0.25)
        private val PERSISTENT = TrajectoryInfo(0.05, 0.5)
        val POTION = GENERIC.copy(gravity = 0.05, initialVelocity = 0.5, roll = -20.0F)
        val EXP_BOTTLE = POTION.copy(initialVelocity = 0.7)
        val FISHING_ROD = GENERIC.copy(gravity = 0.04, drag = 0.92)
        val TRIDENT = PERSISTENT.copy(initialVelocity = 2.5, gravity = 0.05, dragInWater = 0.99)
        val BOW_FULL_PULL = PERSISTENT.copy(initialVelocity = 3.0)
        val FIREBALL = TrajectoryInfo(gravity = 0.0, hitboxRadius = 1.0)
        val WIND_CHARGE = TrajectoryInfo(gravity = 0.0, hitboxRadius = 1.0, copiesPlayerVelocity = false)

        fun bowWithUsageDuration(usageDuration: Int = player.ticksUsingItem): TrajectoryInfo? {
            // Calculate the power of bow
            var power = usageDuration / 20f
            power = (power * power + power * 2F) / 3F

            if (power < 0.1F) {
                return null
            }

            val v0 = power.coerceAtMost(1.0F) * BOW_FULL_PULL.initialVelocity

            return BOW_FULL_PULL.copy(initialVelocity = v0)
        }
    }
}
