package net.ccbluex.liquidbounce.utils.entity
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.utils.math.minus
import net.ccbluex.liquidbounce.utils.math.plus
import net.ccbluex.liquidbounce.utils.math.times
import net.fabricmc.fabric.impl.`object`.builder.FabricEntityTypeImpl.Builder.Living
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.Vec3
import kotlin.math.round

/**
 * A utility which predicts the position of something in n ticks.
 */
interface PositionExtrapolation {
    fun getPositionInTicks(ticks: Double): Vec3

    companion object {
        fun getBestForEntity(target: LivingEntity): PositionExtrapolation {
            return when (target) {
                is Player -> PlayerSimulationExtrapolation(target)
                else -> LinearPositionExtrapolation(target)
            }
        }
    }
}

class ConstantPositionExtrapolation(private val pos: Vec3) : PositionExtrapolation {
    override fun getPositionInTicks(ticks: Double): Vec3 {
        return pos
    }

}

/**
 * A utility class which assumes that the subject is moving at a specified speed.
 */
class LinearPositionExtrapolation(
    private val basePosition: Vec3,
    private val velocity: Vec3
) : PositionExtrapolation {
    constructor(entity: LivingEntity) : this(entity.pos, entity.pos - entity.prevPos)

    override fun getPositionInTicks(ticks: Double): Vec3 {
        return basePosition + velocity * ticks
    }

}

class PlayerSimulationExtrapolation(private val simulation: SimulatedPlayerCache) : PositionExtrapolation {
    constructor(player: Player) : this(PlayerSimulationCache.getSimulationForOtherPlayers(player))

    override fun getPositionInTicks(ticks: Double): Vec3 {
        return this.simulation.getSnapshotAt(round(ticks.coerceAtMost(30.0)).toInt()).pos
    }
}
