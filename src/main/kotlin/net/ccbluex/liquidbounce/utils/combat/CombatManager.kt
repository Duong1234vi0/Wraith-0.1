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
package net.ccbluex.liquidbounce.utils.combat
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.EventManager
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TargetChangeEvent
import net.ccbluex.liquidbounce.event.events.TotemPopEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player

/**
 * A rotation manager
 */
object CombatManager : EventListener {

    // useful for something like autoSoup
    private var pauseCombat: Int = 0

    // useful for something like autopot
    private var pauseRotation: Int = 0

    // useful for autoblock
    private var pauseBlocking: Int = 0

    const val PAUSE_COMBAT = 40 // 40 ticks = 2 seconds
    var duringCombat: Int = 0

    // #Edit By Claude 2026-06-14: ported from VCore's CombatManager.popList. Tracks how many totems
    // each player (by name) has popped so TotemPopEvent can carry an accurate pop count, exactly like
    // VCore. Cleared per name when that player dies (see tickHandler) and on world change.
    val popList = HashMap<String, Int>()

    private const val TOTEM_USE_STATUS: Int = 35

    private fun updatePauseRotation() {
        if (pauseRotation <= 0) return

        pauseRotation--
    }

    private fun updatePauseCombat() {
        if (pauseCombat <= 0) return

        pauseCombat--
    }

    private fun updatePauseBlocking() {
        if (pauseBlocking <= 0) return

        pauseBlocking--
    }

    private fun updateDuringCombat() {
        if (duringCombat <= 0) return

        duringCombat--
    }

    /**
     * Update current rotation to new rotation step
     */
    fun update() {
        updatePauseRotation()
        updatePauseCombat()
        // TODO: implement this for killaura autoblock and other
        updatePauseBlocking()
        updateDuringCombat()
    }

    val tickHandler = handler<GameTickEvent> {
        update()

        // #Edit By Claude 2026-06-14: VCore's CombatManager.onPostTick — drop a player's pop count
        // when they die so a fresh login/respawn starts counting from one again.
        val level = mc.level ?: return@handler
        if (popList.isNotEmpty()) {
            for (player in level.players()) {
                if (player.health <= 0.0f) {
                    popList.remove(player.gameProfile.name)
                }
            }
        }
    }

    @Suppress("unused")
    val attackHandler = handler<AttackEntityEvent> { event ->
        val entity = event.entity

        if (entity is LivingEntity && entity.shouldBeAttacked()) {
            duringCombat = PAUSE_COMBAT

            if (entity is Player) {
                EventManager.callEvent(TargetChangeEvent(entity.gameProfile.name))
            }
        }
    }

    // #Edit By Claude 2026-06-14: ported from VCore CombatManager.onPacketReceive. A real totem pop
    // arrives as an EntityStatusS2CPacket / ClientboundEntityEventPacket with status 35. We count it
    // per player name and post a TotemPopEvent so consumers (e.g. TotemPop) react without each one
    // re-parsing raw packets — exactly VCore's decoupling.
    @Suppress("unused")
    val totemPopHandler = handler<PacketEvent> { event ->
        if (event.origin != TransferOrigin.INCOMING) {
            return@handler
        }
        val packet = event.packet as? ClientboundEntityEventPacket ?: return@handler
        if (packet.eventId.toInt() != TOTEM_USE_STATUS) {
            return@handler
        }
        val level = mc.level ?: return@handler
        val entity = packet.getEntity(level) as? Player ?: return@handler

        val name = entity.gameProfile.name
        val pops = popList.merge(name, 1, Integer::sum) ?: 1
        EventManager.callEvent(TotemPopEvent(entity, pops))
    }

    val shouldPauseCombat: Boolean
        get() = pauseCombat > 0
    val shouldPauseRotation: Boolean
        get() = pauseRotation > 0
    val shouldPauseBlocking: Boolean
        get() = pauseBlocking > 0
    val isInCombat: Boolean
        get() = this.duringCombat > 0 ||
            (ModuleKillAura.running && ModuleKillAura.targetTracker.target != null)

    fun pauseCombatForAtLeast(pauseTime: Int) {
        pauseCombat = pauseCombat.coerceAtLeast(pauseTime)
    }

    fun pauseRotationForAtLeast(pauseTime: Int) {
        pauseRotation = pauseRotation.coerceAtLeast(pauseTime)
    }

    fun pauseBlockingForAtLeast(pauseTime: Int) {
        pauseBlocking = pauseBlocking.coerceAtLeast(pauseTime)
    }

}
