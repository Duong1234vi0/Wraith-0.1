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
package net.ccbluex.liquidbounce.features.module.modules.misc.antibot
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.event.events.TagEntityEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldEntityRemoveEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.command.commands.ingame.fakeplayer.FakePlayer
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

/**
 * AntiBot module
 *
 * #Edit By Claude: Re-ported from VCore (vcore/features/modules/combat/AntiBot.java), single "Matrix"
 * heuristic. A remote player that visually wears armor but has none in its (client-side) inventory, or
 * whose armor value is 0, is flagged as a bot. Flagged players are reported through [isBot] so combat
 * targeting and nametags ignore them (via [TagEntityEvent]); optionally the entity is removed from the
 * world client-side when [enabledRemove] is on.
 *
 * The previous upstream multi-mode design (Custom/Matrix/IntaveHeavy/Horizon + IAntiBotMode) was
 * deleted on request in favor of this port. [isBot] / [TagEntityEvent] / world-change reset are kept
 * so external consumers (NametagTextFormatter, combat targeting) keep working unchanged.
 */
object ModuleAntiBot : ClientModule("AntiBot", Category.MISC) {

    private val enabledRemove by boolean("Remove", false)
    private val onlyAura by boolean("OnlyAura", false)
    private val notify by boolean("Notify", true)

    // Tracks UUIDs we've decided are bots. ConcurrentHashMap-backed to match VCore's thread-safe set,
    // since detection runs on tick while removal/lookups can come from other handlers.
    private val botIds: MutableSet<UUID> = ConcurrentHashMap.newKeySet()

    @Suppress("unused")
    private val tagHandler = handler<TagEntityEvent> {
        if (isBot(it.entity)) {
            it.ignore()
        }
    }

    @Suppress("unused")
    private val detectionHandler = tickHandler {
        if (onlyAura) {
            // #Edit By Claude: VCore's OnlyAura only checks the current aura target.
            (ModuleKillAura.targetTracker.target as? Player)?.let(::detect)
        } else {
            for (player in world.players()) {
                detect(player)
            }
        }

        if (enabledRemove) {
            for (player in world.players()) {
                if (player !is FakePlayer && isBot(player)) {
                    world.removeEntity(player.id, Entity.RemovalReason.KILLED)
                }
            }
        }
    }

    @Suppress("unused")
    private val entityRemoveHandler = handler<WorldEntityRemoveEvent> {
        (it.entity as? Player)?.let { player -> botIds.remove(player.uuid) }
    }

    @Suppress("unused")
    private val handleWorldChange = handler<WorldChangeEvent> {
        reset()
    }

    override fun disable() {
        reset()
    }

    private fun reset() {
        botIds.clear()
    }

    /**
     * #Edit By Claude: faithful port of VCore's detect(): flag a remote player that shows armor on the
     * model but has no armor in its (client-side) inventory, or whose armor value is 0.
     */
    private fun detect(entity: Player) {
        if (entity === player || entity is FakePlayer || isBot(entity)) {
            return
        }

        // Only real network players; the local player is excluded above.
        if (entity !is net.minecraft.client.player.RemotePlayer) {
            return
        }

        val visualArmor = entity.armorItems.any { !it.isEmpty }
        val invArmor = entity.inventory.armor.any { !it.isEmpty }

        if (visualArmor && (!invArmor || entity.armor == 0)) {
            addBot(entity)
        }
    }

    private fun addBot(entity: Player) {
        if (botIds.add(entity.uuid) && notify) {
            net.ccbluex.liquidbounce.utils.client.chat(
                net.ccbluex.liquidbounce.utils.client.regular(
                    "${entity.name.string} is a bot (Matrix)!"
                )
            )
        }
    }

    /**
     * Check if player might be a bot. Kept as the public contract used by combat targeting and nametags.
     */
    fun isBot(entity: Entity): Boolean {
        if (!running || entity !is Player) {
            return false
        }

        if (entity is FakePlayer) {
            return false
        }

        return botIds.contains(entity.uuid)
    }
}
