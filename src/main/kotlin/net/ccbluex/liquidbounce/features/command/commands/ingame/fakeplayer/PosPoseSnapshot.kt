/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 */
package net.ccbluex.liquidbounce.features.command.commands.ingame.fakeplayer
import net.ccbluex.liquidbounce.utils.entity.*

import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.player.Inventory

@JvmRecord
data class PosPoseSnapshot(
    val x: Double,
    val y: Double,
    val z: Double,
    val prevX: Double,
    val prevY: Double,
    val prevZ: Double,
    val handSwinging: Boolean,
    val handSwingTicks: Int,
    val handSwingProgress: Float,
    val yaw: Float,
    val prevYaw: Float,
    val pitch: Float,
    val prevPitch: Float,
    val bodyYaw: Float,
    val prevBodyYaw: Float,
    val headYaw: Float,
    val prevHeadYaw: Float,
    val pose: Pose,
    val preferredHand: InteractionHand,
    val inventory: Inventory?,
    val limbPos: Float,
)

fun fromPlayer(entity: AbstractClientPlayer): PosPoseSnapshot =
    PosPoseSnapshot(
        entity.x, entity.y, entity.z,
        entity.x, entity.y, entity.z,
        false, 0, 0f,
        entity.yRot, entity.yRot,
        entity.xRot, entity.xRot,
        entity.yBodyRot, entity.yBodyRot,
        entity.yHeadRot, entity.yHeadRot,
        entity.pose,
        InteractionHand.MAIN_HAND,
        entity.inventory,
        0f,
    )

fun fromPlayerMotion(entity: AbstractClientPlayer): PosPoseSnapshot = fromPlayer(entity)
