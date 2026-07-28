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
@file:Suppress("MagicNumber")
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.network
import net.ccbluex.liquidbounce.utils.entity.*
import net.ccbluex.liquidbounce.utils.inventory.ItemSlot
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3

/** Elytra mace helper automation. */
object ModuleEMaceHelper : ClientModule("EMaceHelper", Category.COMBAT) {

    private val autoTarget by boolean("Auto Target", false)
    private val peakAssist by boolean("PeakAssist", false)
    private val height by int("Height", 100, 25..1000).visibleWhen { peakAssist }
    private val armorSwap by boolean("Armor Swap", false)
    private val fireWorkVector by boolean("FireWork Vector", false)
    private val extraAimRange by boolean("Extra AimRange", false)
    private val swapDistance by int("Swap Distance", 10, 5..16).visibleWhen { armorSwap }
    private val fireWorkDistance by int("FireWork Distance", 30, 16..64).visibleWhen { fireWorkVector }
    private val aimDistance by int("Aim Distance", 900, 800..1000).visibleWhen { extraAimRange }

    var swapping = false
        private set
    var fireworkTriggered = false
        private set
    var peakOffsetActive = true
        private set
    var peakOffsetDisabledPendingFirework = false
        private set

    @JvmField
    var auraPostAttackTriggered = false

    @JvmField
    var peakAssistPostAttackTriggered = false

    private var peakAssistFireworkTimerNanos = System.nanoTime()

    init {
        EMaceFireworkPhaseCoordinator
    }

    override fun enable() {
        swapping = false
        resetFireworkFlags()
        resetPeakAssistState()
    }

    override fun disable() {
        swapping = false
        EMaceFireworkPhaseCoordinator.onEMaceDisabled()
        resetFireworkFlags()
        resetPeakAssistState()
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        if (mc.player == null || mc.level == null) {
            resetFireworkFlags()
            resetPeakAssistState()
            return@handler
        }

        if (peakAssist) {
            handlePeakAssist()
        } else {
            resetPeakAssistState()
        }

        if (armorSwap) {
            handleArmorSwap()
        }

        if (fireWorkVector) {
            handleAutoFireWork()
        } else {
            resetFireworkFlags()
        }
    }

    /** V-Core's Auto Target predicate is a skip predicate, not a target selector. */
    fun shouldSkipAuraTarget(target: Entity?): Boolean {
        if (target == null || target.isRemoved || !canUseAutoTarget()) {
            return false
        }

        val livingTarget = target as? LivingEntity ?: return false
        return livingTarget.isFallFlying ||
            target is Player && target.armorValue == 0 ||
            livingTarget.isSwimming && isEyeInWater(livingTarget) ||
            isTargetBlockedBySolidBlock(target)
    }

    private fun canUseAutoTarget() = running && autoTarget && canUseMaceAuraSupport()

    private fun isEyeInWater(target: LivingEntity): Boolean {
        val level = mc.level ?: return false
        return level.getFluidState(BlockPos.containing(target.eyePosition)).`is`(FluidTags.WATER)
    }

    private fun isTargetBlockedBySolidBlock(target: Entity): Boolean {
        val player = mc.player ?: return false
        val level = mc.level ?: return false
        var start = player.eyePosition
        val end = closestPointInBox(start, target.boundingBox)
        val direction = end.subtract(start)
        if (direction.lengthSqr() < 1.0E-6) {
            return false
        }

        val step = direction.normalize().scale(0.05)
        repeat(8) {
            val hit = level.clip(ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player))
            if (hit.type != HitResult.Type.BLOCK) {
                return false
            }

            val block = level.getBlockState(hit.blockPos).block
            if (block != Blocks.SHORT_GRASS && block != Blocks.TALL_GRASS) {
                return true
            }

            start = hit.location.add(step)
        }

        return false
    }

    fun applyPeakAssistAimOffset(target: Entity?, aimPos: Vec3?): Vec3? {
        if (aimPos == null || target == null || target.isRemoved || !canUsePeakAssist() ||
            !peakOffsetActive || ModuleKillAura.targetTracker.target !== target) {
            return aimPos
        }

        val offset = aimPos.add(0.0, height.toDouble(), 0.0)
        if (shouldDisablePeakOffset(offset)) {
            peakOffsetActive = false
            peakOffsetDisabledPendingFirework = true
            return aimPos
        }

        return offset
    }

    fun isPeakAssistActive() = canUsePeakAssist() && peakOffsetActive

    fun getElytraAimRangeOverride(defaultRange: Int): Int =
        if (running && extraAimRange && canUseMaceAuraSupport()) aimDistance else defaultRange

    fun canUseMaceAuraSupport(): Boolean {
        val player = mc.player ?: return false
        if (mc.level == null || !ModuleKillAura.running) {
            return false
        }

        return player.mainHandItem.item == Items.MACE ||
            ModuleMaceSwap.running && ModuleMaceSwap.hasMaceInHotbar()
    }

    @JvmStatic
    fun markAuraPostAttack() {
        auraPostAttackTriggered = true
        peakAssistPostAttackTriggered = true
    }

    private fun handlePeakAssist() {
        if (!canUsePeakAssist()) {
            resetPeakAssistState()
            return
        }

        if (peakAssistPostAttackTriggered) {
            peakAssistPostAttackTriggered = false
            peakOffsetActive = true
            peakAssistFireworkTimerNanos = System.nanoTime()
        }

        val target = ModuleKillAura.targetTracker.target
        if (target == null || target.isRemoved) {
            peakOffsetDisabledPendingFirework = false
            return
        }

        val offset = peakAssistOffsetPosition(target) ?: return
        if (peakOffsetActive && shouldDisablePeakOffset(offset)) {
            peakOffsetActive = false
            peakOffsetDisabledPendingFirework = true
        }

        if (peakOffsetActive) {
            if (hasPeakFireworkIntervalElapsed()) {
                if (triggerPeakAssistFirework()) {
                    peakAssistFireworkTimerNanos = System.nanoTime()
                }
            }
        } else if (peakOffsetDisabledPendingFirework) {
            if (triggerPeakAssistFirework()) {
                peakOffsetDisabledPendingFirework = false
            }
        }
    }

    private fun canUsePeakAssist() = running && peakAssist && mc.player?.isFallFlying == true && canUseMaceAuraSupport()

    private fun peakAssistOffsetPosition(target: Entity): Vec3? {
        val base = (target as? LivingEntity)
            ?.takeIf(ModuleElytraTarget::shouldTarget)
            ?.let(ModuleElytraTarget::getElytraTargetVec)
            ?: target.eyePosition
        return base.add(0.0, height.toDouble(), 0.0)
    }

    private fun shouldDisablePeakOffset(offset: Vec3): Boolean {
        val player = mc.player ?: return false
        return player.y > offset.y || player.position().distanceToSqr(offset) < 1.0
    }

    private fun triggerPeakAssistFirework(): Boolean {
        val player = mc.player ?: return false
        if (hasFirework() && !player.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            return EMaceFireworkPhaseCoordinator.requestPeakAssist(emaceOwned = true)
        }
        return false
    }

    private fun resetPeakAssistState() {
        peakOffsetActive = true
        peakOffsetDisabledPendingFirework = false
        peakAssistPostAttackTriggered = false
        peakAssistFireworkTimerNanos = System.nanoTime()
    }

    private fun handleArmorSwap() {
        if (isPeakAssistActive() || swapping || EMaceFireworkPhaseCoordinator.isPending) {
            return
        }

        val player = mc.player ?: return
        if (mc.screen != null || !canUseMaceAuraSupport() || !ModuleMaceSwap.running ||
            !ModuleMaceSwap.hasMaceInHotbar()) {
            return
        }

        val target = ModuleKillAura.targetTracker.target ?: return
        if (target.isRemoved || !isWearingElytra(player) || !player.isFallFlying ||
            player.y <= target.y + 5.0 || armorSwapDistance(player, target) > swapDistance) {
            return
        }

        getChestPlateSlot()?.let(::swapToChestplate)
    }

    private fun swapToChestplate(source: ItemSlot) {
        val player = mc.player ?: return
        val sourceId = source.getIdForServer(null) ?: return
        val chestId = Slots.Armor[2].getIdForServer(null) ?: return
        if (sourceId !in player.screenHandler.slots.indices || chestId !in player.screenHandler.slots.indices) {
            return
        }

        swapping = true
        try {
            interaction.clickSlot(player.screenHandler.syncId, sourceId, 0, ContainerInput.PICKUP, player)
            interaction.clickSlot(player.screenHandler.syncId, chestId, 0, ContainerInput.PICKUP, player)
            interaction.clickSlot(player.screenHandler.syncId, sourceId, 0, ContainerInput.PICKUP, player)
            network.sendPacket(ServerboundContainerClosePacket(player.screenHandler.syncId))
        } finally {
            swapping = false
        }
    }

    private fun armorSwapDistance(player: net.minecraft.client.player.LocalPlayer, target: Entity): Double {
        val point = (target as? LivingEntity)
            ?.takeIf(ModuleElytraTarget::shouldTarget)
            ?.let(ModuleElytraTarget::getElytraTargetVec)
        return player.position().distanceTo(point ?: target.position())
    }

    private fun getChestPlateSlot(): ItemSlot? {
        for (item in CHESTPLATE_PRIORITY) {
            findInventoryFirst(item)?.let { return it }
        }
        return null
    }

    private fun handleAutoFireWork() {
        val player = mc.player ?: return
        val target = ModuleKillAura.targetTracker.target
        if (target == null || target.isRemoved) {
            resetFireworkFlags()
            return
        }

        val wearingElytra = isWearingElytra(player)
        val distance = player.position().distanceTo(target.position())
        if (!wearingElytra || !player.isFallFlying || auraPostAttackTriggered || distance > fireWorkDistance) {
            resetFireworkFlags()
            return
        }

        if (!canUseMaceAuraSupport() || !hasFirework()) {
            return
        }

        val holdingMace = player.mainHandItem.item == Items.MACE
        if (!holdingMace && (!ModuleMaceSwap.running || !ModuleMaceSwap.hasMaceInHotbar())) {
            return
        }

        if (player.y <= target.y + 10.0 || distance > fireWorkDistance || fireworkTriggered) {
            return
        }

        if (EMaceFireworkPhaseCoordinator.requestFireworkVector()) {
            fireworkTriggered = true
        }
    }

    private fun resetFireworkFlags() {
        fireworkTriggered = false
        auraPostAttackTriggered = false
    }

    private fun hasFirework() =
        mc.player?.offhandItem?.item == Items.FIREWORK_ROCKET ||
            findHotbarAscending(Items.FIREWORK_ROCKET) != null ||
            findInventoryDescending(Items.FIREWORK_ROCKET) != null

    fun hasPendingSelectedSlotFireworkCycle(): Boolean = EMaceFireworkPhaseCoordinator.ownsSelectedSlotCycle

    private fun findInventoryFirst(item: Item): ItemSlot? {
        Slots.Inventory.asReversed().firstOrNull { it.itemStack.item == item }?.let { return it }
        return Slots.Hotbar.asReversed().firstOrNull { it.itemStack.item == item }
    }

    private fun findInventoryDescending(item: Item): Int? {
        val player = mc.player ?: return null
        for (slot in 35 downTo 9) {
            if (player.inventory.getStack(slot).item == item) {
                return slot
            }
        }
        return null
    }

    private fun findHotbarAscending(item: Item): Int? {
        val player = mc.player ?: return null
        for (slot in 0..8) {
            if (player.inventory.getStack(slot).item == item) {
                return slot
            }
        }
        return null
    }

    private fun closestPointInBox(point: Vec3, box: net.minecraft.world.phys.AABB) = Vec3(
        point.x.coerceIn(box.minX, box.maxX),
        point.y.coerceIn(box.minY, box.maxY),
        point.z.coerceIn(box.minZ, box.maxZ),
    )

    private fun isWearingElytra(player: net.minecraft.client.player.LocalPlayer) =
        player.getItemBySlot(EquipmentSlot.CHEST).item == Items.ELYTRA

    private const val PEAK_FIREWORK_INTERVAL_MS = 500L
    private const val NANOS_PER_MILLISECOND = 1_000_000L
    private val CHESTPLATE_PRIORITY = arrayOf(
        Items.NETHERITE_CHESTPLATE,
        Items.DIAMOND_CHESTPLATE,
        Items.CHAINMAIL_CHESTPLATE,
        Items.IRON_CHESTPLATE,
        Items.GOLDEN_CHESTPLATE,
        Items.LEATHER_CHESTPLATE,
    )

    private fun hasPeakFireworkIntervalElapsed() =
        System.nanoTime() - peakAssistFireworkTimerNanos >= PEAK_FIREWORK_INTERVAL_MS * NANOS_PER_MILLISECOND
}
