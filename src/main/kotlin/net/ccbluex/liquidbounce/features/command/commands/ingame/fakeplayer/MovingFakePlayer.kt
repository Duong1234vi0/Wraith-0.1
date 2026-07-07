/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 */
package net.ccbluex.liquidbounce.features.command.commands.ingame.fakeplayer
import net.ccbluex.liquidbounce.utils.entity.*

import com.mojang.authlib.GameProfile
import net.minecraft.client.multiplayer.ClientLevel

class MovingFakePlayer(
    private vararg val snapshots: PosPoseSnapshot,
    clientWorld: ClientLevel,
    gameProfile: GameProfile,
) : FakePlayer(clientWorld, gameProfile) {

    private var index = 0

    override fun tick() {
        super.tick()
        if (snapshots.isNotEmpty()) {
            loadAttributes(snapshots[index++ % snapshots.size])
        }
    }

}
