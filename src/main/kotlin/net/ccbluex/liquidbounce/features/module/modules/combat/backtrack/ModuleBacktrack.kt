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
package net.ccbluex.liquidbounce.features.module.modules.combat.backtrack

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.config.types.nesting.Choice
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.config.types.nesting.ToggleableConfigurable
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.render.drawSolidBox
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.withColor
import net.ccbluex.liquidbounce.render.withPositionRelativeToCamera
import net.ccbluex.liquidbounce.utils.client.Chronometer
import net.ccbluex.liquidbounce.utils.client.PacketSnapshot
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.combat.findEnemy
import net.ccbluex.liquidbounce.utils.combat.shouldBeAttacked
import net.ccbluex.liquidbounce.utils.entity.age
import net.ccbluex.liquidbounce.utils.entity.boxedDistanceTo
import net.ccbluex.liquidbounce.utils.entity.expand
import net.ccbluex.liquidbounce.utils.entity.pos
import net.ccbluex.liquidbounce.utils.entity.rotation
import net.ccbluex.liquidbounce.utils.entity.squareBoxedDistanceTo
import net.ccbluex.liquidbounce.utils.entity.squaredBoxedDistanceTo
import net.ccbluex.liquidbounce.utils.render.WireframePlayer
import net.minecraft.client.renderer.entity.EntityRenderDispatcher
import net.minecraft.client.renderer.state.level.LevelRenderState
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.common.ClientboundDisconnectPacket
import net.minecraft.network.protocol.game.ClientboundEntityPositionSyncPacket
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket
import net.minecraft.network.protocol.game.ClientboundSoundPacket
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
import net.minecraft.network.protocol.game.ServerboundChatCommandPacket
import net.minecraft.network.protocol.game.ServerboundChatPacket
import net.minecraft.network.protocol.game.ServerboundCommandSuggestionPacket
import net.minecraft.network.protocol.game.VecDeltaCodec
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Holds incoming packets so the client can attack a previous server-side target position.
 *
 * This is a source-faithful restoration of the historical Backtrack module. It deliberately keeps
 * its own packet queues instead of using PacketQueueManager.
 */
@Suppress("MagicNumber")
object ModuleBacktrack : ClientModule("Backtrack", Category.COMBAT) {

    internal val range by floatRange("Range", 1f..3f, 0f..10f)
    internal val delay by intRange("Delay", 100..150, 0..1000, "ms")
    private val nextBacktrackDelay by intRange("NextBacktrackDelay", 0..10, 0..2000, "ms")
    private val trackingBuffer by int("TrackingBuffer", 500, 0..2000, "ms")
    private val chance by float("Chance", 50f, 0f..100f, "%")

    private object PauseOnHurtTime : ToggleableConfigurable(ModuleBacktrack, "PauseOnHurtTime", false) {
        val hurtTime by int("HurtTime", 3, 0..10)
    }

    private val targetMode by enumChoice("TargetMode", TargetMode.ATTACK)
    private val lastAttackTimeToWork by int("LastAttackTimeToWork", 1000, 0..5000)
    private val espMode = choices("EspMode", 2) {
        arrayOf(BoxMode, ModelMode, WireframeMode, NoneMode)
    }.apply { doNotIncludeAlways() }

    private object BoxMode : Choice("Box") {
        override val parent: ChoiceConfigurable<*>
            get() = espMode

        val color by color("Color", Color4b(36, 32, 147, 87))
    }

    private object WireframeMode : Choice("Wireframe") {
        override val parent: ChoiceConfigurable<*>
            get() = espMode

        val color by color("Color", Color4b(36, 32, 147, 87))
        val outlineColor by color("OutlineColor", Color4b(36, 32, 147, 255))
    }

    private object ModelMode : Choice("Model") {
        override val parent: ChoiceConfigurable<*>
            get() = espMode

        val lightAmount by float("LightAmount", 0.3f, 0.01f..1f)
    }

    private object NoneMode : Choice("None") {
        override val parent: ChoiceConfigurable<*>
            get() = espMode
    }

    init {
        tree(PauseOnHurtTime)
    }

    internal val delayedPacketQueue: ConcurrentLinkedQueue<PacketSnapshot> = ConcurrentLinkedQueue()
    internal val packetProcessQueue: ConcurrentLinkedQueue<Packet<*>> = ConcurrentLinkedQueue()

    internal var currentChance: Int = (0..100).random()
    internal var currentDelay: Int = delay.random()

    private var target: Entity? = null
    private var position: BacktrackPositionTracker? = null
    private var shouldPause = false

    private val chronometer = Chronometer()
    private val trackingBufferChronometer = Chronometer()
    private val attackChronometer = Chronometer()

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        if (event.origin != TransferOrigin.INCOMING || event.isCancelled) {
            return@handler
        }

        if (arePacketQueuesEmpty() && !shouldCancelPackets()) {
            return@handler
        }

        val packet = event.packet

        when (packet) {
            is ServerboundChatPacket,
            is ClientboundSystemChatPacket,
            is ServerboundChatCommandPacket,
            is ServerboundCommandSuggestionPacket -> return@handler

            is ClientboundPlayerPositionPacket,
            is ClientboundDisconnectPacket -> {
                clear(handlePackets = true)
                return@handler
            }

            is ClientboundSoundPacket -> {
                if (packet.sound.value() == SoundEvents.PLAYER_HURT) {
                    return@handler
                }
            }

            is ClientboundSetHealthPacket -> {
                if (packet.health <= 0.0f) {
                    clear(handlePackets = true)
                    return@handler
                }
            }
        }

        val movementPosition = targetMovementPosition(packet)
        if (movementPosition != null) {
            position?.pos = movementPosition

            val currentTarget = target ?: return@handler
            if (currentTarget.squareBoxedDistanceTo(player, movementPosition) <
                currentTarget.squaredBoxedDistanceTo(player)
            ) {
                processPackets(clear = true)
                return@handler
            }
        }

        event.cancelEvent()
        delayedPacketQueue += PacketSnapshot(packet, event.origin, System.currentTimeMillis())
    }

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent> { event ->
        attackChronometer.reset()
        currentChance = (0..100).random()

        if (targetMode != TargetMode.ATTACK) {
            return@handler
        }

        processTarget(event.entity)
    }

    @Suppress("unused")
    private val rangeHandler = tickHandler {
        if (targetMode != TargetMode.RANGE) {
            return@tickHandler
        }

        val enemy = world.findEnemy(range) ?: run {
            clear()
            return@tickHandler
        }

        processTarget(enemy)
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> { event ->
        if (event.world == null) {
            clear(clearOnly = true)
            packetProcessQueue.clear()
        }
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        val currentTarget = target ?: return@handler
        val trackedPosition = position?.pos ?: return@handler

        when (espMode.activeChoice) {
            BoxMode -> renderBox(event, currentTarget, trackedPosition)
            ModelMode -> Unit
            WireframeMode -> WireframePlayer(
                trackedPosition,
                currentTarget.rotation.yaw,
                currentTarget.rotation.pitch
            ).render(event, WireframeMode.color, WireframeMode.outlineColor)
            NoneMode -> Unit
        }
    }

    override fun enable() {
        clear(handlePackets = false)
        super.enable()
    }

    override fun disable() {
        clear(handlePackets = true)
        super.disable()
    }

    internal fun processPackets(clear: Boolean = false) {
        val now = System.currentTimeMillis()
        delayedPacketQueue.removeIf { snapshot ->
            if (clear || snapshot.timestamp <= now - currentDelay) {
                packetProcessQueue += snapshot.packet
                true
            } else {
                false
            }
        }
    }

    internal fun clear(
        handlePackets: Boolean = true,
        clearOnly: Boolean = false,
        resetChronometer: Boolean = true
    ) {
        if (handlePackets && !clearOnly) {
            processPackets(clear = true)
        } else if (clearOnly) {
            delayedPacketQueue.clear()
        }

        if (target != null && resetChronometer) {
            chronometer.waitForAtLeast(nextBacktrackDelay.random().toLong())
        }

        target = null
        position = null
    }

    internal fun shouldCancelPackets(): Boolean {
        val currentTarget = target ?: return false
        return currentTarget.isAlive && shouldBacktrack(currentTarget)
    }

    internal fun arePacketQueuesEmpty(): Boolean = delayedPacketQueue.isEmpty() && packetProcessQueue.isEmpty()

    fun isLagging(): Boolean = running && !arePacketQueuesEmpty()

    @JvmStatic
    fun appendModelRenderState(
        dispatcher: EntityRenderDispatcher,
        levelRenderState: LevelRenderState,
        partialTicks: Float
    ) {
        if (!running || espMode.activeChoice !== ModelMode) {
            return
        }

        val currentTarget = target ?: return
        val trackedPosition = position?.pos ?: return

        val renderState = dispatcher.extractEntity(currentTarget, partialTicks)
        renderState.x = trackedPosition.x
        renderState.y = trackedPosition.y
        renderState.z = trackedPosition.z
        renderState.lightCoords = ((mc.level?.getMaxLocalRawBrightness(BlockPos.ZERO) ?: 0) *
            ModelMode.lightAmount).toInt()

        levelRenderState.entityRenderStates += renderState
    }

    private fun processTarget(enemy: Entity) {
        shouldPause = enemy is LivingEntity && enemy.hurtTime >= PauseOnHurtTime.hurtTime

        if (!shouldBacktrack(enemy)) {
            return
        }

        if (enemy != target) {
            clear(resetChronometer = false)
            position = BacktrackPositionTracker(enemy.getPositionCodec().base)
        }

        target = enemy
    }

    private fun shouldBacktrack(enemy: Entity): Boolean {
        val inRange = enemy.boxedDistanceTo(player) in range

        if (inRange) {
            trackingBufferChronometer.reset()
        }

        return (inRange || !trackingBufferChronometer.hasElapsed(trackingBuffer.toLong())) &&
            enemy.shouldBeAttacked() &&
            player.age > 10 &&
            currentChance < chance &&
            chronometer.hasElapsed() &&
            !shouldPause() &&
            !attackChronometer.hasElapsed(lastAttackTimeToWork.toLong())
    }

    private fun shouldPause() = PauseOnHurtTime.running && shouldPause

    private fun targetMovementPosition(packet: Packet<*>): Vec3? {
        val currentTarget = target ?: return null
        val currentPosition = position ?: return null

        return when (packet) {
            is ClientboundMoveEntityPacket -> {
                if (packet.getEntity(world) != currentTarget || !packet.hasPosition()) {
                    return null
                }

                currentPosition.withDelta(
                    packet.xa.toLong(),
                    packet.ya.toLong(),
                    packet.za.toLong()
                )
            }

            is ClientboundTeleportEntityPacket -> {
                if (packet.id != currentTarget.id) {
                    return null
                }

                packet.change.position()
            }

            is ClientboundEntityPositionSyncPacket -> {
                if (packet.id != currentTarget.id) {
                    return null
                }

                packet.values.position()
            }

            else -> null
        }
    }

    private fun renderBox(event: WorldRenderEvent, entity: Entity, trackedPosition: Vec3) {
        val dimensions = entity.getDimensions(entity.pose)
        val halfWidth = dimensions.width() / 2.0
        val box = AABB(
            -halfWidth,
            0.0,
            -halfWidth,
            halfWidth,
            dimensions.height().toDouble(),
            halfWidth
        ).expand(0.05)

        renderEnvironmentForWorld(event.matrixStack) {
            withPositionRelativeToCamera(trackedPosition) {
                withColor(BoxMode.color) {
                    drawSolidBox(box)
                }
            }
        }
    }

    private class BacktrackPositionTracker(base: Vec3) {
        private val codec = VecDeltaCodec().apply { setBase(base) }

        var pos: Vec3
            get() = codec.base
            set(value) {
                codec.setBase(value)
            }

        fun withDelta(x: Long, y: Long, z: Long): Vec3 = codec.decode(x, y, z)
    }

    private enum class TargetMode(override val choiceName: String) : NamedChoice {
        ATTACK("Attack"),
        RANGE("Range")
    }

}
