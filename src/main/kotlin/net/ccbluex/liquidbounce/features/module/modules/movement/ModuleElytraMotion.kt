/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2026 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package net.ccbluex.liquidbounce.features.module.modules.movement

import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.PlayerMoveEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleEMaceHelper
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.inventory.ElytraFireworkExecutor
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3
import kotlin.math.sqrt

/** Helps stabilize elytra targeting movement. */
object ModuleElytraMotion : ClientModule("ElytraMotion", Category.MOVEMENT) {

    private val attackDistance by float("AttackDistance", 3.0f, 0.1f..5.0f)
    private val autoFirework by boolean("AutoFirework", false)
    private val matrixBypass by boolean("MatrixBypass", false)

    var freeze = false
        private set

    private var fireworkTimerNanos = System.nanoTime()

    override fun disable() {
        freeze = false
        ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.ELYTRA_MOTION)
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val player = mc.player ?: run {
            freeze = false
            ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.ELYTRA_MOTION)
            return@handler
        }

        if (ModuleEMaceHelper.running && ModuleEMaceHelper.isPeakAssistActive()) {
            freeze = false
            // #Edit By Codex 2026-07-17: deferred work has no V-Core equivalent once
            // PeakAssist takes this tick over.
            ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.ELYTRA_MOTION)
            return@handler
        }

        if (!player.isFallFlying) {
            freeze = false
            ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.ELYTRA_MOTION)
            return@handler
        }

        val target = ModuleKillAura.targetTracker.target
        if (target == null) {
            ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.ELYTRA_MOTION)
        }
        freeze = check(target, ModuleElytraTarget)

        if (freeze) {
            if (matrixBypass && hasFireworkIntervalElapsed()) {
                ElytraFireworkExecutor.request(
                    owner = ElytraFireworkExecutor.Owner.ELYTRA_MOTION,
                    order = ElytraFireworkExecutor.SelectionOrder.INVENTORY_FIRST,
                )
                // V-Core resets after attempting the use, whether or not an item was found.
                fireworkTimerNanos = System.nanoTime()
            }
        } else if (autoFirework && target != null && getBps(player) <= 30.0 &&
            hasFireworkIntervalElapsed()) {
            ElytraFireworkExecutor.request(
                owner = ElytraFireworkExecutor.Owner.ELYTRA_MOTION,
                order = ElytraFireworkExecutor.SelectionOrder.INVENTORY_FIRST,
            )
            fireworkTimerNanos = System.nanoTime()
        }
    }

    @Suppress("unused")
    private val moveHandler = handler<PlayerMoveEvent> { event ->
        val player = mc.player ?: return@handler
        if (ModuleEMaceHelper.running && ModuleEMaceHelper.isPeakAssistActive()) {
            freeze = false
        } else if (freeze) {
            // PlayerMoveEvent is mutable rather than cancellable in current Wraith. Clear both
            // event movement and local velocity so no stale glide delta survives this source freeze.
            event.movement = Vec3.ZERO
            player.deltaMovement = Vec3.ZERO
        }
    }

    @Suppress("unused")
    private val lifecycleHandler = handler<WorldChangeEvent> {
        freeze = false
        ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.ELYTRA_MOTION)
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        freeze = false
        ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.ELYTRA_MOTION)
    }

    fun check(target: LivingEntity?, elytraTarget: ModuleElytraTarget): Boolean {
        val player = mc.player ?: return false
        return target != null && player.isFallFlying && !elytraTarget.shouldTarget(target) &&
            target.distanceTo(player) < attackDistance
    }

    private fun getBps(entity: LivingEntity): Double {
        val dx = entity.x - entity.xo
        val dy = entity.y - entity.yo
        val dz = entity.z - entity.zo
        return sqrt(dx * dx + dy * dy + dz * dz) * 20.0
    }

    private const val FIREWORK_INTERVAL_MS = 500L
    private const val NANOS_PER_MILLISECOND = 1_000_000L

    private fun hasFireworkIntervalElapsed() =
        System.nanoTime() - fireworkTimerNanos >= FIREWORK_INTERVAL_MS * NANOS_PER_MILLISECOND
}
