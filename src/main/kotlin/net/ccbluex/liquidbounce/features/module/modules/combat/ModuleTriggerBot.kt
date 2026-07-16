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

import net.ccbluex.liquidbounce.event.events.AttackInputEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.client.ModuleTargets
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.combat.Targets
import net.ccbluex.liquidbounce.utils.entity.crosshairTarget
import net.ccbluex.liquidbounce.utils.entity.mainHandItem
import net.ccbluex.liquidbounce.utils.entity.offhandItem
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.entity.wouldBlockHit
import net.ccbluex.liquidbounce.utils.input.TriggerBotClickSimulation
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.ccbluex.liquidbounce.utils.item.isSword
import net.minecraft.core.component.DataComponents
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.monster.zombie.Zombie
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.item.ShieldItem
import net.minecraft.world.phys.EntityHitResult
import org.lwjgl.glfw.GLFW
import kotlin.random.Random

/**
 * Strict Argon TriggerBot port.
 *
 * The module only acts on the current crosshair entity. It neither rotates nor scans for targets.
 */
object ModuleTriggerBot : ClientModule("TriggerBot", Category.COMBAT) {

    private val workInScreen by boolean("WorkInScreen", false)
    private val whileUse by boolean("WhileUse", false)
    private val onLeftClick by boolean("OnLeftClick", false)
    private val allItems by boolean("AllItems", false)
    private val swordDelay by intRange("SwordDelay", 540..550, 0..1000, "ms")
    private val axeDelay by intRange("AxeDelay", 780..800, 0..1000, "ms")
    private val checkShield by boolean("CheckShield", false)
    private val whileAscending by boolean("WhileAscending", false)
    private val samePlayer by boolean("SamePlayer", false)
    private val onlyCritSword by boolean("OnlyCritSword", false)
    private val onlyCritAxe by boolean("OnlyCritAxe", false)
    private val swingHand by boolean("SwingHand", true)
    private val clickSimulation by boolean("ClickSimulation", false)
    private val strayBypass by boolean("StrayBypass", false)
    private val allEntities by boolean("AllEntities", false)
    private val useShield by boolean("UseShield", false)
    private val shieldTime by int("ShieldTime", 350, 100..1000, "ms")

    // Argon's TimerUtils is constructed with its module, then deliberately not reset in onEnable.
    private var lastAttackTimeNanos = System.nanoTime()
    private var currentSwordDelay = 0
    private var currentAxeDelay = 0

    override fun enable() {
        currentSwordDelay = sampleUpperExclusive(swordDelay)
        currentAxeDelay = sampleUpperExclusive(axeDelay)
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val player = mc.player ?: return@handler
        val gameMode = mc.gameMode ?: return@handler

        if (!workInScreen && mc.screen != null) {
            return@handler
        }

        if (isAimingAtExcludedFriend()) {
            return@handler
        }

        val mainHand = player.mainHandItem

        if (onLeftClick && !isMouseDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
            return@handler
        }

        if (!whileUse && isMouseDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT) && isOffhandFoodOrShield(player)) {
            return@handler
        }

        if (!whileAscending && !player.onGround() &&
            (player.deltaMovement.y > 0.0 || player.fallDistance <= 0.0f)) {
            return@handler
        }

        val entity = (mc.crosshairTarget as? EntityHitResult)?.entity ?: return@handler
        if (!isEligible(entity)) {
            return@handler
        }

        when {
            allItems -> attackSwordBranch(player, gameMode, entity, allItemsBranch = true)
            mainHand.isSword -> attackSwordBranch(player, gameMode, entity, allItemsBranch = false)
            mainHand.isAxe -> attackAxeBranch(player, gameMode, entity)
        }
    }

    /**
     * Mirrors Argon's AttackListener: this is independent from the OnLeftClick option and affects physical
     * Minecraft.startAttack input only. Direct interaction-manager attacks above bypass AttackInputEvent.
     */
    @Suppress("unused")
    private val attackInputHandler = handler<AttackInputEvent> { event ->
        if (!isMouseDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
            event.cancelEvent()
        }
    }

    private fun attackSwordBranch(
        player: net.minecraft.client.player.LocalPlayer,
        gameMode: net.minecraft.client.multiplayer.MultiPlayerGameMode,
        entity: Entity,
        allItemsBranch: Boolean,
    ) {
        // Yarn PlayerEntity#getAttacking maps to 26.1.2 LivingEntity#getLastHurtMob.
        if (samePlayer && entity !== player.lastHurtMob) {
            return
        }

        if (shouldSkipBlockingPlayer(player, entity)) {
            return
        }

        if (onlyCritSword && player.fallDistance <= 0.0f) {
            return
        }

        if (hasReached(currentSwordDelay)) {
            // Argon releases a synthetic shield marker only in the actual Sword ready branch, not AllItems.
            if (!allItemsBranch && useShield && player.offhandItem.item == Items.SHIELD && player.isBlocking) {
                TriggerBotClickSimulation.recordRightRelease()
            }

            attackDirectly(player, gameMode, entity)
            currentSwordDelay = sampleUpperExclusive(swordDelay)
            resetTimer()
        } else {
            recordShieldCooldown(player)
        }
    }

    private fun attackAxeBranch(
        player: net.minecraft.client.player.LocalPlayer,
        gameMode: net.minecraft.client.multiplayer.MultiPlayerGameMode,
        entity: Entity,
    ) {
        // Argon's SamePlayer / sticky check intentionally does not run in the Axe branch.
        if (shouldSkipBlockingPlayer(player, entity)) {
            return
        }

        if (onlyCritAxe && player.fallDistance <= 0.0f) {
            return
        }

        if (hasReached(currentAxeDelay)) {
            attackDirectly(player, gameMode, entity)
            currentAxeDelay = sampleUpperExclusive(axeDelay)
            resetTimer()
        } else {
            recordShieldCooldown(player)
        }
    }

    private fun attackDirectly(
        player: net.minecraft.client.player.LocalPlayer,
        gameMode: net.minecraft.client.multiplayer.MultiPlayerGameMode,
        entity: Entity,
    ) {
        // Keep the direct 26.1.2 interaction-manager path. Its existing Wraith mixin still exposes
        // AttackEntityEvent cancellation/safety behavior, which is an unavoidable project integration adaptation.
        gameMode.attack(player, entity)

        if (swingHand) {
            player.swingHand(InteractionHand.MAIN_HAND)
        }

        if (clickSimulation) {
            TriggerBotClickSimulation.recordLeftClick()
        }
    }

    private fun isAimingAtExcludedFriend(): Boolean {
        val target = (mc.crosshairTarget as? EntityHitResult)?.entity as? Player ?: return false
        return FriendManager.isFriend(target) && Targets.FRIENDS !in ModuleTargets.combat
    }

    private fun isOffhandFoodOrShield(player: Player): Boolean =
        player.offhandItem.components.has(DataComponents.FOOD) || player.offhandItem.item is ShieldItem

    private fun isEligible(entity: Entity): Boolean =
        entity is Player || strayBypass && entity is Zombie || allEntities

    private fun shouldSkipBlockingPlayer(player: Player, entity: Entity): Boolean =
        checkShield && entity is Player && entity.isBlocking && entity.wouldBlockHit(player)

    private fun recordShieldCooldown(player: Player) {
        if (useShield && player.offhandItem.item == Items.SHIELD) {
            // This remains Argon's synthetic marker only; it deliberately does not start real shield use.
            TriggerBotClickSimulation.recordRightClick(shieldTime.toLong())
        }
    }

    private fun hasReached(delayMs: Int): Boolean =
        System.nanoTime() - lastAttackTimeNanos >= delayMs.toLong() * NANOS_PER_MILLISECOND

    private fun resetTimer() {
        lastAttackTimeNanos = System.nanoTime()
    }

    private fun sampleUpperExclusive(range: IntRange): Int =
        if (range.last > range.first) Random.nextInt(range.first, range.last) else range.first

    private fun isMouseDown(button: Int): Boolean =
        GLFW.glfwGetMouseButton(mc.window.handle(), button) == GLFW.GLFW_PRESS

    private const val NANOS_PER_MILLISECOND = 1_000_000L
}
