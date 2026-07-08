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
package net.ccbluex.liquidbounce.features.module.modules.render.skeletonesp

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaProjection
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.utils.client.mc
import net.minecraft.client.CameraType
import net.minecraft.world.entity.player.Player
import kotlin.math.sqrt

object ModuleSkeletonESP : ClientModule(
    "SkeletonESP",
    Category.RENDER,
    aliases = arrayOf("EspSkeleton", "Skeleton")
) {

    private const val MAX_PLAYERS = 64

    private val targets by multiEnumChoice("Targets", Target.PLAYERS)
    private val thickness by float("Thickness", 2.0f, 0.5f..5.0f)
    private val color by color("Color", Color4b.WHITE)

    private val playerBuffer = ArrayList<Player>(MAX_PLAYERS)

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        clear()
    }

    @Suppress("unused")
    private val worldRenderHandler = handler<WorldRenderEvent> { event ->
        val localPlayer = mc.player ?: return@handler
        val level = mc.level ?: return@handler
        if (targets.isEmpty()) {
            return@handler
        }

        collectTargets(localPlayer, level.players())
        if (playerBuffer.isEmpty()) {
            return@handler
        }

        val cameraPos = event.camera.position()
        renderEnvironmentForWorld(event.matrixStack) {
            SkeletonEspRenderer.render(
                this,
                playerBuffer,
                cameraPos,
                event.partialTicks,
                thickness,
                color
            )
        }
    }

    override fun disable() {
        clear()
        super.disable()
    }

    private fun collectTargets(localPlayer: Player, players: List<Player>) {
        val includePlayers = Target.PLAYERS in targets
        val includeFriends = Target.FRIENDS in targets
        val includeLocal = includePlayers && mc.options.cameraType != CameraType.FIRST_PERSON
        val renderDistance = sqrt(AlphaProjection.maxRenderDistanceSq())
        val searchBox = localPlayer.boundingBox.inflate(renderDistance)

        playerBuffer.clear()

        for (player in players) {
            if (playerBuffer.size >= MAX_PLAYERS) {
                break
            }

            if (player === localPlayer && !includeLocal) {
                continue
            }

            if (!isEligible(player, searchBox)) {
                continue
            }

            val friend = FriendManager.isFriend(player)
            if (includePlayers || includeFriends && friend) {
                playerBuffer += player
            }
        }
    }

    private fun isEligible(player: Player, searchBox: net.minecraft.world.phys.AABB): Boolean {
        return !player.isRemoved &&
            player.isAlive &&
            !player.isDeadOrDying &&
            !isGhost(player) &&
            searchBox.intersects(player.boundingBox) &&
            AlphaProjection.isWithinRenderDistance(player) &&
            AlphaProjection.canSee(player.boundingBox)
    }

    private fun isGhost(player: Player): Boolean {
        return player.displayName?.string?.startsWith("Ghost_") == true ||
            player.customName?.string?.startsWith("Ghost_") == true
    }

    private fun clear() {
        playerBuffer.clear()
        SkeletonEspRenderer.clear()
    }

    private enum class Target(override val choiceName: String) : NamedChoice {
        PLAYERS("Players"),
        FRIENDS("Friends")
    }
}
