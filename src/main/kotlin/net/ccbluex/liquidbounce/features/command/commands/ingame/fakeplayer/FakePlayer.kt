/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 */
package net.ccbluex.liquidbounce.features.command.commands.ingame.fakeplayer
import net.ccbluex.liquidbounce.utils.entity.*

import com.mojang.authlib.GameProfile
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.player.RemotePlayer
import net.minecraft.world.entity.Entity

open class FakePlayer(
    clientWorld: ClientLevel,
    gameProfile: GameProfile,
) : RemotePlayer(clientWorld, gameProfile) {

    var onRemoval: () -> Unit = {}

    fun loadAttributes(snapshot: PosPoseSnapshot) {
        setPos(snapshot.x, snapshot.y, snapshot.z)
        setYRot(snapshot.yaw)
        setXRot(snapshot.pitch)
        yHeadRot = snapshot.headYaw
        yBodyRot = snapshot.bodyYaw
        pose = snapshot.pose
    }

    override fun remove(reason: Entity.RemovalReason) {
        super.remove(reason)
        onRemoval()
    }

}
