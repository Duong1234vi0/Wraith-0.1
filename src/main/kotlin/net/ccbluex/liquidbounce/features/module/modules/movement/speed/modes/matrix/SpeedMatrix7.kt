package net.ccbluex.liquidbounce.features.module.modules.movement.speed.modes.matrix
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.modules.movement.speed.modes.SpeedBHopBase
import net.ccbluex.liquidbounce.utils.entity.moving
import net.ccbluex.liquidbounce.utils.entity.withStrafe

/**
 * bypassing matrix version > 7
 * testing in 6/23/25 at loyisa
 *
 * @author XeContrast
 */
class SpeedMatrix7(override val parent : ChoiceConfigurable<*>) : SpeedBHopBase("Matrix7",parent) {

    @Suppress("unused")
    private val tickHandle = tickHandler {
        if (player.moving) {
            if (player.onGround()) {
                player.deltaMovement.y = 0.419652
                player.deltaMovement = player.deltaMovement.withStrafe()
            } else {
                if (player.deltaMovement.x * player.deltaMovement.x + player.deltaMovement.z * player.deltaMovement.z < 0.04) {
                    player.deltaMovement = player.deltaMovement.withStrafe()
                }
            }
        }
    }
}
