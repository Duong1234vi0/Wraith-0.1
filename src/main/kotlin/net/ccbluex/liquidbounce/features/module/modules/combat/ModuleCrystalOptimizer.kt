/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
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
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.ping
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.ccbluex.liquidbounce.utils.item.isPickaxe
import net.ccbluex.liquidbounce.utils.item.isShovel
import net.ccbluex.liquidbounce.utils.item.isSword
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.boss.enderdragon.EndCrystal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.network.protocol.game.ClientboundExplodePacket
import net.minecraft.network.protocol.game.ServerboundAttackPacket
import java.util.concurrent.ConcurrentHashMap

/** Strict V-core 0.8 CrystalOptimizer port. */
object ModuleCrystalOptimizer : ClientModule("CrystalOptimizer", Category.COMBAT) {

    private const val MIN_BLATANT_TIME_MS = 150L
    private const val CHAIN_CRYSTAL_RADIUS_SQ = 144.0

    private val blatant by boolean("Blatant", true)
    private val inhibitedCrystals = ConcurrentHashMap<Int, Long>()

    private var observedPlayer: Any? = null
    private var observedLevel: Any? = null

    override fun enable() {
        observedPlayer = mc.player
        observedLevel = mc.level
    }

    override fun disable() {
        clearInhibitedCrystals()
        observedPlayer = null
        observedLevel = null
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        if (!ensureWorldState()) {
            return@handler
        }

        if (blatant) {
            pruneInhibitedCrystals()
        } else {
            clearInhibitedCrystals()
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clearInhibitedCrystals()
        observedPlayer = null
        observedLevel = null
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        clearInhibitedCrystals()
        observedPlayer = null
        observedLevel = null
    }

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        if (event.origin != TransferOrigin.OUTGOING || !ensureWorldState()) {
            return@handler
        }

        val packet = event.packet as? ServerboundAttackPacket ?: return@handler

        if (blatant) {
            pruneInhibitedCrystals()
        }

        val entity = mc.level?.getEntity(packet.entityId())
        if (blatant) {
            handleBlatantAttack(event, packet.entityId(), entity)
        } else if (entity is EndCrystal && !cantBreakCrystal()) {
            removeCrystal(entity)
        }
    }

    @Suppress("unused")
    private val explosionHandler = handler<PacketEvent> { event ->
        if (event.origin != TransferOrigin.INCOMING || !blatant || !ensureWorldState()) {
            return@handler
        }

        val packet = event.packet as? ClientboundExplodePacket ?: return@handler
        pruneInhibitedCrystals()

        val center = packet.center()
        markNearbyCrystalsDead(center.x, center.y, center.z)
    }

    /**
     * Mirrors V-core's pre-attack optimizer query. An inhibited crystal is removed locally and
     * causes the caller to skip its direct attack.
     */
    fun isAttackInhibited(entity: Entity): Boolean {
        if (!ensureWorldState() || !enabled || !blatant || entity !is EndCrystal) {
            return false
        }

        pruneInhibitedCrystals()
        if (!isInhibited(entity.id)) {
            return false
        }

        removeCrystal(entity)
        return true
    }

    /** Only blatantly inhibited EndCrystals may be ignored by AutoCrystal placement checks. */
    fun shouldIgnoreForPlacement(entity: Entity): Boolean {
        if (!ensureWorldState() || !enabled || !blatant || entity !is EndCrystal) {
            return false
        }

        pruneInhibitedCrystals()
        return isInhibited(entity.id)
    }

    private fun handleBlatantAttack(event: PacketEvent, entityId: Int, entity: Entity?) {
        if (isInhibited(entityId)) {
            if (entity is EndCrystal) {
                removeCrystal(entity)
            }

            event.cancelEvent()
            return
        }

        if (entity is EndCrystal && !cantBreakCrystal()) {
            markInhibited(entity.id)
            markNearbyCrystalsDead(entity.x, entity.y, entity.z)
            removeCrystal(entity)
        }
    }

    private fun removeCrystal(entity: Entity) {
        // ClientLevel.removeEntity performs the current-version client removal callback exactly once.
        mc.level?.removeEntity(entity.id, Entity.RemovalReason.KILLED)
    }

    private fun markNearbyCrystalsDead(x: Double, y: Double, z: Double) {
        val level = mc.level ?: return
        val now = System.currentTimeMillis()

        for (entity in level.entitiesForRendering()) {
            if (entity is EndCrystal && entity.distanceToSqr(x, y, z) <= CHAIN_CRYSTAL_RADIUS_SQ && !isInhibited(entity.id)) {
                inhibitedCrystals[entity.id] = now
            }
        }
    }

    private fun markInhibited(entityId: Int) {
        inhibitedCrystals[entityId] = System.currentTimeMillis()
    }

    private fun pruneInhibitedCrystals() {
        val now = System.currentTimeMillis()
        inhibitedCrystals.entries.removeIf { (_, timestamp) -> now - timestamp > blatantTimeMs() }
    }

    private fun isInhibited(entityId: Int): Boolean = inhibitedCrystals.containsKey(entityId)

    private fun blatantTimeMs(): Long =
        maxOf(MIN_BLATANT_TIME_MS, (mc.player?.ping ?: 0).toLong() * 2L)

    private fun cantBreakCrystal(): Boolean {
        val player = mc.player ?: return false
        val weakness = player.getEffect(MobEffects.WEAKNESS)
        val strength = player.getEffect(MobEffects.STRENGTH)

        return weakness != null && (strength == null || strength.amplifier <= weakness.amplifier) && !isTool(player.mainHandItem)
    }

    private fun isTool(stack: ItemStack): Boolean =
        stack.isSword || stack.isPickaxe || stack.isAxe || stack.isShovel

    private fun ensureWorldState(): Boolean {
        val player = mc.player
        val level = mc.level

        if (player == null || level == null) {
            clearInhibitedCrystals()
            observedPlayer = null
            observedLevel = null
            return false
        }

        if (observedPlayer !== player || observedLevel !== level) {
            clearInhibitedCrystals()
            observedPlayer = player
            observedLevel = level
        }

        return true
    }

    private fun clearInhibitedCrystals() {
        inhibitedCrystals.clear()
    }
}
