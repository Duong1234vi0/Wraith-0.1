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

import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.OutgoingPacketSentEvent
import net.ccbluex.liquidbounce.event.events.ScheduleInventoryActionEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.features.module.modules.player.offhand.ModuleOffhand
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.inventory.ClickInventoryAction
import net.ccbluex.liquidbounce.utils.inventory.ElytraFireworkExecutor
import net.ccbluex.liquidbounce.utils.inventory.InventoryActionChain
import net.ccbluex.liquidbounce.utils.inventory.InventoryActionMovementGate
import net.ccbluex.liquidbounce.utils.inventory.InventoryClickSignature
import net.ccbluex.liquidbounce.utils.inventory.InventoryManager
import net.ccbluex.liquidbounce.utils.inventory.ItemSlot
import net.ccbluex.liquidbounce.utils.inventory.PlayerInventoryConstraints
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.inventory.inventoryClickSignature
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.ccbluex.liquidbounce.utils.network.ActionTickBoundary
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.player.LocalPlayer
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket
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
    private var observedPlayer: LocalPlayer? = null
    private var observedLevel: ClientLevel? = null

    override fun enable() {
        swapping = false
        observedPlayer = mc.player
        observedLevel = mc.level
        resetFireworkFlags()
        resetPeakAssistState()
        EMaceArmorTransaction.cancelBeforeStart()
    }

    override fun disable() {
        swapping = false
        resetFireworkFlags()
        resetPeakAssistState()
        ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.PEAK_ASSIST)
        ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.FIREWORK_VECTOR)
        EMaceArmorTransaction.cancelBeforeStart()
        observedPlayer = null
        observedLevel = null
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val player = mc.player
        val level = mc.level
        if (player == null || level == null) {
            resetFireworkFlags()
            resetPeakAssistState()
            EMaceArmorTransaction.cancelBeforeStart()
            observedPlayer = null
            observedLevel = null
            return@handler
        }

        if (observedPlayer !== player || observedLevel !== level) {
            resetFireworkFlags()
            resetPeakAssistState()
            EMaceArmorTransaction.cancelBeforeStart()
            observedPlayer = player
            observedLevel = level
        }

        if (peakAssist) {
            handlePeakAssist()
        } else {
            resetPeakAssistState()
        }

        if (armorSwap) {
            handleArmorSwap()
        } else {
            EMaceArmorTransaction.cancelBeforeStart()
        }

        if (fireWorkVector) {
            handleFireworkVector()
        } else {
            resetFireworkFlags()
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        resetFireworkFlags()
        resetPeakAssistState()
        EMaceArmorTransaction.cancelBeforeStart()
        observedPlayer = null
        observedLevel = null
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        resetFireworkFlags()
        resetPeakAssistState()
        EMaceArmorTransaction.cancelBeforeStart()
        observedPlayer = null
        observedLevel = null
    }

    /**
     * V-Core's Auto Target predicate is intentionally a skip predicate: a true result excludes
     * the otherwise valid Aura target rather than selecting it.
     */
    fun shouldSkipAuraTarget(target: Entity?): Boolean {
        if (target == null || target.isRemoved || !canUseAutoTarget()) {
            return false
        }

        val livingTarget = target as? LivingEntity ?: return false
        return livingTarget.isFallFlying ||
            target is Player && target.health == 0.0f ||
            livingTarget.isSwimming && isEyeInWater(livingTarget) ||
            isTargetBlockedBySolidBlock(target)
    }

    private fun isEyeInWater(target: LivingEntity): Boolean {
        val level = mc.level ?: return false
        return level.getFluidState(BlockPos.containing(target.eyePosition)).`is`(FluidTags.WATER)
    }

    private fun canUseAutoTarget() = running && autoTarget && canUseMaceAuraSupport()

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
            val hit = level.clip(
                ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)
            )
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

    /** Exact Mace Aura support gate, using the current typed Mace item and MaceSwap mode predicate. */
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

    internal fun setArmorSwapState(value: Boolean) {
        swapping = value
    }

    /** Shared Firework execution must not enter an EMace pickup transaction's packet window. */
    internal fun isArmorSwapTransactionActive() = EMaceArmorTransaction.isBusy()

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
            ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.PEAK_ASSIST)
            return
        }

        val offset = peakAssistOffsetPosition(target) ?: return
        if (peakOffsetActive && shouldDisablePeakOffset(offset)) {
            peakOffsetActive = false
            peakOffsetDisabledPendingFirework = true
        }

        if (peakOffsetActive) {
            if (hasPeakFireworkIntervalElapsed()) {
                peakAssistFireworkTimerNanos = System.nanoTime()
                triggerPeakAssistFirework()
            }
        } else if (peakOffsetDisabledPendingFirework) {
            triggerPeakAssistFirework()
            peakOffsetDisabledPendingFirework = false
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
        // V-Core intentionally uses this very high Y offset and strict comparisons.
        return player.y > offset.y || player.position().distanceToSqr(offset) < 1.0
    }

    private fun triggerPeakAssistFirework() {
        val player = mc.player ?: return
        if (hasFirework() && !player.cooldowns.isOnCooldown(Items.FIREWORK_ROCKET.defaultInstance)) {
            ElytraFireworkExecutor.request(
                owner = ElytraFireworkExecutor.Owner.PEAK_ASSIST,
                order = ElytraFireworkExecutor.SelectionOrder.INVENTORY_FIRST,
            )
        }
    }

    private fun resetPeakAssistState() {
        peakOffsetActive = true
        peakOffsetDisabledPendingFirework = false
        peakAssistPostAttackTriggered = false
        peakAssistFireworkTimerNanos = System.nanoTime()
        ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.PEAK_ASSIST)
    }

    private fun handleArmorSwap() {
        if (isPeakAssistActive() || ElytraFireworkExecutor.isBusy()) {
            EMaceArmorTransaction.cancelBeforeStart()
            return
        }

        if (swapping || EMaceArmorTransaction.isBusy()) {
            return
        }

        val player = mc.player ?: return
        if (mc.screen != null || InventoryManager.isInventoryOpen || ModuleOffhand.hasActiveInventoryTransaction() ||
            !canUseMaceAuraSupport() || !ModuleMaceSwap.running ||
            !ModuleMaceSwap.hasMaceInHotbar()) {
            EMaceArmorTransaction.cancelBeforeStart()
            return
        }

        val target = ModuleKillAura.targetTracker.target ?: run {
            EMaceArmorTransaction.cancelBeforeStart()
            return
        }
        if (target.isRemoved || !isWearingElytra(player) || !player.isFallFlying ||
            player.y <= target.y + 5.0 || armorSwapDistance(player, target) > swapDistance) {
            EMaceArmorTransaction.cancelBeforeStart()
            return
        }

        val chestplate = getChestPlateSlot() ?: run {
            EMaceArmorTransaction.cancelBeforeStart()
            return
        }
        EMaceArmorTransaction.request(chestplate)
    }

    private fun armorSwapDistance(player: LocalPlayer, target: Entity): Double {
        val point = (target as? LivingEntity)
            ?.takeIf(ModuleElytraTarget::shouldTarget)
            ?.let(ModuleElytraTarget::getElytraTargetVec)
        return player.position().distanceTo(point ?: target.position())
    }

    private fun getChestPlateSlot(): ItemSlot? {
        for (item in CHESTPLATE_PRIORITY) {
            val slot = findInventoryFirst(item)
            if (slot != null) {
                return slot
            }
        }

        return null
    }

    private fun handleFireworkVector() {
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

        ElytraFireworkExecutor.request(
            owner = ElytraFireworkExecutor.Owner.FIREWORK_VECTOR,
            order = ElytraFireworkExecutor.SelectionOrder.HOTBAR_FIRST,
            onUseAccepted = { fireworkTriggered = true },
        )
    }

    private fun resetFireworkFlags() {
        fireworkTriggered = false
        auraPostAttackTriggered = false
        ElytraFireworkExecutor.cancelOwner(ElytraFireworkExecutor.Owner.FIREWORK_VECTOR)
    }

    private fun hasFirework() = ElytraFireworkExecutor.hasFirework(ElytraFireworkExecutor.SelectionOrder.HOTBAR_FIRST)

    private fun isWearingElytra(player: LocalPlayer) = player.getItemBySlot(EquipmentSlot.CHEST).item == Items.ELYTRA

    private fun findInventoryFirst(item: Item): ItemSlot? {
        for (slot in Slots.Inventory.asReversed()) {
            if (slot.itemStack.item == item) {
                return slot
            }
        }

        for (slot in Slots.Hotbar.asReversed()) {
            if (slot.itemStack.item == item) {
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

/**
 * Owns the delayed three-click source armor transaction even after EMaceHelper is toggled off.
 * The user-visible module exposes no inventory settings; this only maps V-Core's pickup chain to
 * Wraith's scheduler and stationary-input gate.
 */
private object EMaceArmorTransaction : EventListener {

    private data class Pending(
        val player: LocalPlayer,
        val level: ClientLevel,
        val source: ItemSlot,
        val expectedSourceItem: Item,
        val actions: List<ClickInventoryAction>,
        var request: InventoryActionMovementGate.Request? = null,
        var expectedClicks: MutableList<InventoryClickSignature> = mutableListOf(),
        var finalBoundarySerial: Long = Long.MIN_VALUE,
        var createdTick: Int,
    )

    private val constraints = PlayerInventoryConstraints("EMaceHelperSafeArmorTransaction")
    private val gate = InventoryActionMovementGate(
        ownerRunning = { pending != null },
        diagnosticPrefix = "[EMaceArmorTxnDiag]"
    )
    private var pending: Pending? = null

    override val running: Boolean
        get() = true

    fun isBusy() = pending != null || gate.state != InventoryActionMovementGate.State.IDLE

    fun request(source: ItemSlot): Boolean {
        if (pending != null || gate.state != InventoryActionMovementGate.State.IDLE || source.itemStack.isEmpty ||
            mc.screen != null || InventoryManager.isInventoryOpen || ModuleOffhand.hasActiveInventoryTransaction()) {
            return false
        }

        val player = mc.player ?: return false
        val level = mc.level ?: return false
        val actions = listOf(
            ClickInventoryAction.performPickup(slot = source).withDetachedPlayerInventoryClickPermit(),
            ClickInventoryAction.performPickup(slot = Slots.Armor[2]).withDetachedPlayerInventoryClickPermit(),
            ClickInventoryAction.performPickup(slot = source).withDetachedPlayerInventoryClickPermit(),
        )
        pending = Pending(
            player = player,
            level = level,
            source = source,
            expectedSourceItem = source.itemStack.item,
            actions = actions,
            createdTick = player.tickCount,
        )
        ModuleEMaceHelper.setArmorSwapState(true)
        return true
    }

    fun cancelBeforeStart() {
        val current = pending ?: run {
            ModuleEMaceHelper.setArmorSwapState(false)
            return
        }
        val request = current.request
        if (request == null || !gate.hasStarted(request)) {
            gate.cancel("EMace armor cancelled before first pickup")
            pending = null
            ModuleEMaceHelper.setArmorSwapState(false)
        }
    }

    @Suppress("unused")
    private val scheduleHandler = handler<ScheduleInventoryActionEvent> { event ->
        val current = pending ?: return@handler
        if (mc.player !== current.player || mc.level !== current.level) {
            gate.cancel("EMace armor player or world changed")
            pending = null
            ModuleEMaceHelper.setArmorSwapState(false)
            return@handler
        }

        val request = current.request ?: InventoryActionMovementGate.Request(
            mode = "EMaceArmorSwap",
            sourceSlot = current.source,
            expectedSourceItem = current.expectedSourceItem,
            switchMode = "PICKUP",
            sendDirectly = false,
            clickSignatures = current.actions.mapNotNull(ClickInventoryAction::inventoryClickSignature),
            expectsOffhandSwap = false,
            timeoutTicks = ARMOR_TRANSACTION_TIMEOUT_TICKS,
            onFirstActionSent = {},
        ).also {
            current.request = it
            current.expectedClicks = it.clickSignatures.toMutableList()
        }

        if (ModuleOffhand.hasActiveInventoryTransaction() && !gate.hasStarted(request)) {
            return@handler
        }

        gate.request(request)
        if (!gate.isCurrent(request) || !gate.isReady(request) ||
            !current.actions.all { it.canPerformAction(constraints) } || !gate.beginExecution(request)) {
            return@handler
        }

        // #Edit By Codex 2026-07-17: this is V-Core's pickup/chest/pickup result, but the
        // scheduler and movement gate keep its detached inventory clicks out of a moving window.
        event.schedule.add(
            InventoryActionChain(
                inventoryConstraints = constraints,
                actions = current.actions.toTypedArray(),
                priority = Priority.NORMAL,
                precondition = {
                    when {
                        pending !== current || !gate.isCurrent(request) -> false
                        !current.actions.all { it.canPerformAction(constraints) } -> {
                            gate.cancel("EMace armor action unavailable")
                            false
                        }
                        gate.hasStarted(request) -> true
                        else -> gate.isSourceValid(request).also { valid ->
                            if (!valid) gate.cancel("EMace armor source moved before pickup")
                        }
                    }
                }
            )
        )
    }

    @Suppress("unused")
    private val packetHandler = handler<OutgoingPacketSentEvent> { event ->
        val current = pending ?: return@handler
        val packet = event.packet as? ServerboundContainerClickPacket ?: return@handler
        val index = current.expectedClicks.indexOfFirst { it.matches(packet) }
        if (index == -1) {
            return@handler
        }

        current.expectedClicks.removeAt(index)
        if (current.expectedClicks.isEmpty()) {
            current.finalBoundarySerial = ActionTickBoundary.currentSerial()
        }
    }

    @Suppress("unused")
    private val lifecycleHandler = handler<GameTickEvent>(priority = -1000) {
        val current = pending ?: return@handler
        if (mc.player !== current.player || mc.level !== current.level) {
            gate.cancel("EMace armor player or world changed")
            pending = null
            ModuleEMaceHelper.setArmorSwapState(false)
            return@handler
        }

        if (current.finalBoundarySerial != Long.MIN_VALUE &&
            ActionTickBoundary.hasPassed(current.finalBoundarySerial) &&
            gate.state == InventoryActionMovementGate.State.IDLE) {
            pending = null
            ModuleEMaceHelper.setArmorSwapState(false)
            return@handler
        }

        if (current.player.tickCount - current.createdTick > ARMOR_TRANSACTION_TIMEOUT_TICKS &&
            current.finalBoundarySerial == Long.MIN_VALUE) {
            gate.cancel("EMace armor transaction timed out")
            pending = null
            ModuleEMaceHelper.setArmorSwapState(false)
        }
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        gate.cancel("EMace armor world changed")
        pending = null
        ModuleEMaceHelper.setArmorSwapState(false)
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        gate.cancel("EMace armor disconnected")
        pending = null
        ModuleEMaceHelper.setArmorSwapState(false)
    }

    private const val ARMOR_TRANSACTION_TIMEOUT_TICKS = 160
}
