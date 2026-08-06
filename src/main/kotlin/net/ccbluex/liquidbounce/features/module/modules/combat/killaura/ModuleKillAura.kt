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
@file:Suppress("WildcardImport")
package net.ccbluex.liquidbounce.features.module.modules.combat.killaura
import net.ccbluex.liquidbounce.utils.entity.*

import com.google.gson.JsonObject
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.Sequence
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.RotationUpdateEvent
import net.ccbluex.liquidbounce.event.events.SprintEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.event.tickHandler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleAutoCart
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleAutoWeapon
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleEMaceHelper
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleElytraTarget
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleMaceSwap
import net.ccbluex.liquidbounce.features.module.modules.combat.ModuleWallsBypass
import net.ccbluex.liquidbounce.features.module.modules.combat.criticals.ModuleCriticals.CriticalsSelectionMode
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.KillAuraRotationsConfigurable.KillAuraRotationTiming.ON_TICK
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.KillAuraRotationsConfigurable.KillAuraRotationTiming.SNAP
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura.RaycastMode.*
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.features.KillAuraAutoBlock
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.features.KillAuraFailSwing
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.features.KillAuraFailSwing.dealWithFakeSwing
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.features.KillAuraFightBot
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.features.KillAuraNotifyWhenFail
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.features.KillAuraNotifyWhenFail.failedHits
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.features.KillAuraNotifyWhenFail.renderFailedHits
import net.ccbluex.liquidbounce.features.module.modules.misc.debugrecorder.modes.GenericDebugRecorder
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleDebug
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.utils.aiming.features.FocusedFullMovementContextManager
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.data.RotationWithVector
import net.ccbluex.liquidbounce.utils.aiming.point.PointTracker
import net.ccbluex.liquidbounce.utils.aiming.preference.LeastDifferencePreference
import net.ccbluex.liquidbounce.utils.aiming.utils.facingEnemy
import net.ccbluex.liquidbounce.utils.aiming.utils.raytraceBox
import net.ccbluex.liquidbounce.utils.aiming.utils.raytraceEntity
import net.ccbluex.liquidbounce.utils.combat.CombatManager
import net.ccbluex.liquidbounce.utils.combat.attack
import net.ccbluex.liquidbounce.utils.combat.shouldBeAttacked
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.network
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.entity.rotation
import net.ccbluex.liquidbounce.utils.entity.squaredBoxedDistanceTo
import net.ccbluex.liquidbounce.utils.inventory.InventoryManager
import net.ccbluex.liquidbounce.utils.inventory.InventoryManager.isInventoryOpen
import net.ccbluex.liquidbounce.utils.inventory.isInContainerScreen
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.ccbluex.liquidbounce.utils.item.sharpnessLevel
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.ccbluex.liquidbounce.utils.kotlin.EventPriorityConvention.FIRST_PRIORITY
import net.ccbluex.liquidbounce.utils.kotlin.random
import net.ccbluex.liquidbounce.utils.render.WorldTargetRenderer
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.Items
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import kotlin.math.pow

/**
 * KillAura module
 *
 * Automatically attacks enemies.
 */
@Suppress("MagicNumber")
object ModuleKillAura : ClientModule("KillAura", Category.COMBAT) {

    // Attack speed
    val clickScheduler = tree(KillAuraClicker)

    // Range
    internal val range by float("Range", 4.2f, 1f..8f)
    internal val wallRange by float("WallRange", 3f, 0f..8f)
        .onChange { wallRange ->
            if (wallRange > range) {
                range
            } else {
                wallRange
            }
        }

    private val scanExtraRange by floatRange("ScanExtraRange", 2.0f..3.0f, 0.0f..7.0f)
        .onChanged { range ->
            currentScanExtraRange = range.random()
        }
    private var currentScanExtraRange: Float = scanExtraRange.random()

    internal val targetStrafeMaxRange: Float
        get() = if (player.isFallFlying) activeElytraAcquisitionRange() else range + currentScanExtraRange

    // #Edit By Claude: public enemy-detection range = base Range + the current
    // ScanExtra (RangeExtra) value, i.e. the furthest distance KillAura will
    // pick up an enemy. The TargetHud uses this to cap its own target search so
    // its appear/disappear animation triggers at the same distance KillAura
    // actually engages, instead of finding targets from across the map.
    val enemyDetectionRange: Float
        get() = if (player.isFallFlying) activeElytraAcquisitionRange() else range + currentScanExtraRange

    // V-Core keeps a small combat range while gliding, but rotates/acquires using the much larger
    // Elytra aim range and its existing-target +4 allowance.
    internal val elytraRange by float("ElytraRange", 0.5f, 0.0f..6.0f)
    internal val elytraAimRange by int("ElytraAimRange", 32, 0..64)

    // Target
    val targetTracker = tree(KillAuraTargetTracker)

    // Rotation
    private val rotations = tree(KillAuraRotationsConfigurable)

    private val pointTracker = tree(PointTracker())

    private val requires by multiEnumChoice<KillAuraRequirements>("Requires")

    private val requirementsMet
        get() = requires.all { it.meets() }

    // Bypass techniques
    internal val raycast by enumChoice("Raycast", TRACE_ALL)
    private val criticalsSelectionMode by enumChoice("Criticals", CriticalsSelectionMode.SMART)
    private val keepSprint by boolean("KeepSprint", true)

    // Inventory Handling
    internal val ignoreOpenInventory by boolean("IgnoreOpenInventory", true)
    internal val simulateInventoryClosing by boolean("SimulateInventoryClosing", true)
    internal val shieldBreaker by boolean("ShieldBreaker", true)

    // V-Core Track owns this mutable point state on Aura and resets it on enable.
    internal var rotationPoint = Vec3.ZERO
    internal var rotationMotion = Vec3.ZERO
    private var trackAimTickIdentity = 0L
    private val trackAimPointCache = VCoreTrackAimPointCache()

    private var shieldBreakerHitTicks = 0
    private var directShieldBreakerAttackInProgress = false

    internal val isDirectShieldBreakerAttackInProgress: Boolean
        get() = directShieldBreakerAttackInProgress

    init {
        tree(KillAuraAutoBlock)
    }

    // Target rendering
    private val targetRenderer = tree(WorldTargetRenderer(this))

    init {
        tree(KillAuraFailSwing)
        tree(KillAuraFightBot)
    }

    override fun disable() {
        resetTrackedTarget("module-disable")
        FocusedFullMovementContextManager.endSession("killaura-disable")
        rotationPoint = Vec3.ZERO
        rotationMotion = Vec3.ZERO
        trackAimPointCache.clear()
        shieldBreakerHitTicks = 0
        directShieldBreakerAttackInProgress = false
        failedHits.clear()
        KillAuraAutoBlock.stopBlocking()
        KillAuraNotifyWhenFail.failedHitsIncrement = 0
    }

    override fun enable() {
        FocusedFullMovementContextManager.beginSession()
        rotationPoint = Vec3.ZERO
        rotationMotion = Vec3.ZERO
        trackAimPointCache.clear()
        shieldBreakerHitTicks = 0
        directShieldBreakerAttackInProgress = false
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        val matrixStack = event.matrixStack

        renderTarget(matrixStack, event.partialTicks)
        renderFailedHits(matrixStack)
    }

    @Suppress("unused")
    private val trackAimTickHandler = handler<GameTickEvent>(priority = (FIRST_PRIORITY + 1).toShort()) {
        trackAimTickIdentity++
    }

    @Suppress("unused")
    private val trackAimWorldChangeHandler = handler<WorldChangeEvent> {
        trackAimPointCache.clear()
    }

    private fun renderTarget(matrixStack: PoseStack, partialTicks: Float) {
        val target = targetTracker.target
            ?.takeIf { targetRenderer.enabled }
            ?: return

        renderEnvironmentForWorld(matrixStack) {
            targetRenderer.render(this, target, partialTicks)
        }
    }

    @Suppress("unused")
    private val rotationUpdateHandler = handler<RotationUpdateEvent> {
        // Make sure killaura-logic is not running while inventory is open
        val isInInventoryScreen =
            InventoryManager.isInventoryOpen || mc.screen is AbstractContainerScreen<*>

        val shouldResetTarget = player.isSpectator || player.isDead || !requirementsMet

        if (isInInventoryScreen && !ignoreOpenInventory || shouldResetTarget) {
            // Reset current target
            resetTrackedTarget("rotation-update-invalidation")
            return@handler
        }

        // Update current target tracker to make sure you attack the best enemy
        updateTarget()

        // Update Auto Weapon
        ModuleAutoWeapon.prepare(targetTracker.target)
    }

    @Suppress("unused")
    private val gameHandler = tickHandler {
        if (shieldBreakerHitTicks > 0) {
            shieldBreakerHitTicks--
        }

        if (player.isDead || player.isSpectator) {
            return@tickHandler
        }

        // Check if there is target to attack
        val target = targetTracker.target

        if (CombatManager.shouldPauseCombat) {
            KillAuraAutoBlock.stopBlocking()
            return@tickHandler
        }

        if (target == null) {
            val hasUnblocked = KillAuraAutoBlock.stopBlocking()

            // Deal with fake swing when there is no target
            if (KillAuraFailSwing.enabled && requirementsMet) {
                if (hasUnblocked) {
                    waitTicks(KillAuraAutoBlock.currentTickOff)
                }
                dealWithFakeSwing(this, null)
            }
            return@tickHandler
        }

        // Check if the module should (not) continue after the blocking state is updated
        if (!requirementsMet) {
            return@tickHandler
        }

        val rotation = (if (rotations.rotationTiming == ON_TICK) {
            getSpot(target, activeAimRange().toDouble(), PointTracker.AimSituation.FOR_NOW)?.rotation
        } else {
            null
        } ?: RotationManager.currentRotation ?: player.rotation).normalize()

        val isElytraTarget = target is LivingEntity && ModuleElytraTarget.shouldTarget(target)
        val crosshairTarget = when {
            isElytraTarget -> target
            raycast != TRACE_NONE -> {
                raytraceEntity(activeAttackRange().toDouble(), rotation, filter = {
                    when (raycast) {
                        TRACE_ONLYENEMY -> it.shouldBeAttacked()
                        TRACE_ALL -> true
                        else -> false
                    }
                })?.entity ?: target
            }
            else -> target
        }

        if (crosshairTarget is LivingEntity && crosshairTarget.shouldBeAttacked() &&
            !ModuleEMaceHelper.shouldSkipAuraTarget(crosshairTarget) && crosshairTarget != target) {
            setTrackedTarget(crosshairTarget, "crosshair-substitution")
        }

        attackTarget(this, crosshairTarget, rotation)
    }

    val shouldBlockSprinting
        get() = criticalsSelectionMode.shouldStopSprinting(clickScheduler, targetTracker.target)

    @Suppress("unused")
    private val sprintHandler = handler<SprintEvent> { event ->
        if (shouldBlockSprinting && (event.source == SprintEvent.Source.MOVEMENT_TICK ||
                event.source == SprintEvent.Source.INPUT)) {
            event.sprint = false
        }
    }

    @Suppress("CognitiveComplexMethod", "CyclomaticComplexMethod")
    private suspend fun attackTarget(
        sequence: Sequence,
        target: Entity,
        rotation: Rotation,
    ) {
        // Make it seem like we are blocking
        KillAuraAutoBlock.makeSeemBlock()

        val elytraTarget = target as? LivingEntity
            ?: run {
                // Ordinary targets retain Wraith's existing boxed facing/raycast behavior.
                null
            }
        val isSourceElytraTarget = elytraTarget != null && ModuleElytraTarget.shouldTarget(elytraTarget)

        if (isSourceElytraTarget && !canAttackElytraTarget(elytraTarget!!)) {
            return
        }

        // An Elytra target uses the source attack-window formula above. Every other target keeps
        // Wraith's normal facingEnemy validation, including its wall-range and raycast semantics.
        val isFacingEnemy = isSourceElytraTarget || facingEnemy(
            toEntity = target,
            rotation = rotation,
            range = activeAttackRange().toDouble(),
            wallsRange = activeWallRange().toDouble()
        )

        ModuleDebug.debugParameter(ModuleKillAura, "Is Facing Enemy", isFacingEnemy)
        ModuleDebug.debugParameter(ModuleKillAura, "Rotation", rotation)
        ModuleDebug.debugParameter(ModuleKillAura, "Target", target.scoreboardName)

        // Check if our target is in range, otherwise deal with auto block
        if (!isFacingEnemy) {
            if (KillAuraAutoBlock.enabled && KillAuraAutoBlock.onScanRange &&
                squaredDistanceToTargetPoint(target) <= targetStrafeMaxRange.pow(2)) {
                KillAuraAutoBlock.startBlocking()
                return
            }

            // Make sure we are not blocking
            val hasUnblocked = KillAuraAutoBlock.stopBlocking()

            // Deal with fake swing
            if (KillAuraFailSwing.enabled) {
                if (hasUnblocked) {
                    sequence.waitTicks(KillAuraAutoBlock.currentTickOff)
                }

                dealWithFakeSwing(sequence, target)
            }
            return
        }

        ModuleDebug.debugParameter(ModuleKillAura, "Valid Rotation", rotation)

        // Attack enemy, according to the attack scheduler
        if (clickScheduler.isClickTick && validateAttack(target)) {
            clickScheduler.attack(sequence, rotation, preAttack = {
                if (!validateAttack(target)) {
                    false
                } else {
                    performShieldBreaker(target)
                }
            }) {
                // On each click, we check if we are still ready to attack
                if (!validateAttack(target)) {
                    return@attack false
                }

                val skipShieldAttack = targetTracker.shouldSkipActualShieldAttack(target)

                if (!skipShieldAttack) {
                    // V-Core sets Aura's MaceSwap source flag immediately before its actual attack.
                    if (ModuleMaceSwap.running) {
                        ModuleMaceSwap.calledFromAura = true
                        ModuleMaceSwap.silentSwapToMace()
                    }

                    ModuleMaceSwap.withMaceAttackBoundary {
                        target.attack(true, keepSprint && !shouldBlockSprinting)
                    }
                }

                runVCoreAuraPostAction(
                    dedicatedShieldBreak = false,
                    attacked = !skipShieldAttack,
                    markEMace = ModuleEMaceHelper::markAuraPostAttack,
                    markMaceSwap = ModuleMaceSwap::markAuraPostAttack,
                )
                currentScanExtraRange = scanExtraRange.random()
                KillAuraNotifyWhenFail.failedHitsIncrement = 0

                GenericDebugRecorder.recordDebugInfo(ModuleKillAura, "attackEntity", JsonObject().apply {
                    add("player", GenericDebugRecorder.debugObject(player))
                    add("targetPos", GenericDebugRecorder.debugObject(target))
                })

                true
            }
        } else if (KillAuraAutoBlock.currentTickOff > 0 && clickScheduler.willClickAt(KillAuraAutoBlock.currentTickOff)
            && KillAuraAutoBlock.shouldUnblockToHit) {
            KillAuraAutoBlock.stopBlocking(pauses = true)
        } else {
            KillAuraAutoBlock.startBlocking()
        }
    }

    private fun updateTarget() {
        // Determine aim situation based on click scheduler
        val situation = when {
            clickScheduler.isClickTick || clickScheduler.willClickAt(1)
                -> PointTracker.AimSituation.FOR_NEXT_TICK
            else -> PointTracker.AimSituation.FOR_THE_FUTURE
        }
        ModuleDebug.debugParameter(ModuleKillAura, "AimSituation", situation)

        // Calculate maximum range based on enemy distance
        val attackRange: Float
        val maximumRange: Float
        if (player.isFallFlying) {
            attackRange = elytraRange
            maximumRange = activeElytraAcquisitionRange()
        } else {
            attackRange = range
            maximumRange = if (targetTracker.closestSquaredEnemyDistance > attackRange.pow(2)) {
                targetStrafeMaxRange
            } else {
                attackRange
            }
        }

        ModuleDebug.debugParameter(ModuleKillAura, "Maximum Range", maximumRange)
        ModuleDebug.debugParameter(ModuleKillAura, "Range", attackRange)
        val squaredMaxRange = maximumRange.pow(2)

        // Find suitable target
        val candidates = targetTracker.targets()
            .filter { entity -> squaredDistanceToTargetPoint(entity) <= squaredMaxRange }

        // #Edit By Claude: VCore LockTarget — hold the current target while it stays valid/in-range
        // (Aura.java:497-514) instead of re-picking the best one each tick. processTarget() fires the
        // rotation as a side effect, so a kept target keeps aiming normally.
        val locked = targetTracker.target?.takeIf { current ->
            targetTracker.lockTarget &&
                current in candidates &&
                processTarget(current, maximumRange, situation)
        }

        val target = locked
            ?: candidates.firstOrNull { entity -> processTarget(entity, maximumRange, situation) }

        if (target != null) {
            setTrackedTarget(target, "update-target")
        } else if (KillAuraFightBot.enabled) {
            KillAuraFightBot.updateTarget()

            RotationManager.setRotationTarget(
                rotations.toRotationTarget(
                    KillAuraFightBot.getMovementRotation(),
                    considerInventory = !ignoreOpenInventory
                ),
                priority = Priority.IMPORTANT_FOR_USAGE_2,
                provider = ModuleKillAura
            )
        } else {
            resetTrackedTarget("no-valid-target")
        }
    }

    private fun setTrackedTarget(target: LivingEntity, source: String) {
        val oldId = targetTracker.target?.id
        targetTracker.target = target
        FocusedFullMovementContextManager.noteTargetChange(oldId, target.id, source)
    }

    private fun resetTrackedTarget(source: String) {
        val oldId = targetTracker.target?.id
        targetTracker.reset()
        FocusedFullMovementContextManager.noteTargetChange(oldId, null, source)
    }

    @Suppress("ReturnCount")
    private fun processTarget(
        entity: LivingEntity,
        maximumRange: Float,
        situation: PointTracker.AimSituation
    ): Boolean {
        val (rotation, _) = getSpot(entity, maximumRange.toDouble(), situation) ?: return false
        val ticks = rotations.calculateTicks(rotation)
        ModuleDebug.debugParameter(ModuleKillAura, "Rotation Ticks", ticks)

        when (rotations.rotationTiming) {

            // If our click scheduler is not going to click the moment we reach the target,
            // we should not start aiming towards the target just yet.
            SNAP -> if (!clickScheduler.willClickAt(ticks.coerceAtLeast(1))) {
                return true
            }

            // [ON_TICK] will always instantly aim onto the target on attack, however, if
            // our rotation is unable to be ready in time, we can at least start aiming towards
            // the target.
            ON_TICK -> if (ticks <= 1) {
                return true
            }

            else -> {
                // Continue with regular aiming
            }
        }

        RotationManager.setRotationTarget(
            rotations.toRotationTarget(
                rotation,
                entity,
                considerInventory = !ignoreOpenInventory
            ),
            priority = Priority.IMPORTANT_FOR_USAGE_2,
            provider = this@ModuleKillAura
        )

        return true
    }

    /**
     * Get the best spot to attack the entity
     *
     * @param entity The entity to attack
     * @param range The range to attack the entity (NOT SQUARED)
     * @param situation The aim situation we are in
     *  - [PointTracker.AimSituation.FOR_NOW] if we are going to attack the entity on the current tick (ON_TICK)
     *  - [PointTracker.AimSituation.FOR_THE_FUTURE] if we are going to attack the entity in the future
     *  - [PointTracker.AimSituation.FOR_NEXT_TICK] if we are going to attack the entity on the next tick
     *
     *  @return The best spot to attack the entity
     */
    private fun getSpot(entity: LivingEntity, range: Double,
                        situation: PointTracker.AimSituation): RotationWithVector? {
        if (KillAuraRotationsConfigurable.usesVCoreTrackAim) {
            val aimPoint = resolveVCoreTrackAimPoint(entity) ?: return null
            return RotationWithVector(Rotation.lookingAt(point = aimPoint, from = player.eyePosition), aimPoint)
        }

        if (KillAuraRotationsConfigurable.usesVCoreGrimAim) {
            val aimPoint = resolveVCoreGrimAimPoint(entity) ?: return null
            return RotationWithVector(Rotation.lookingAt(point = aimPoint, from = player.eyePosition), aimPoint)
        }

        if (ModuleElytraTarget.shouldTarget(entity)) {
            val predicted = ModuleElytraTarget.getElytraTargetVec(entity) ?: return null
            val aimPoint = ModuleEMaceHelper.applyPeakAssistAimOffset(entity, predicted) ?: return null
            // Do not pass this point through PointTracker: V-Core's velocity prediction is applied once.
            return RotationWithVector(Rotation.lookingAt(point = aimPoint, from = player.eyePosition), aimPoint)
        }

        val point = pointTracker.gatherPoint(
            entity,
            situation
        )

        val eyes = point.fromPoint
        val nextPoint = point.toPoint

        ModuleDebug.debugGeometry(this, "AABB",
            ModuleDebug.DebuggedBox(point.box, Color4b.RED.with(a = 60)))
        ModuleDebug.debugGeometry(this, "CutOffBox",
            ModuleDebug.DebuggedBox(point.cutOffBox, Color4b.GREEN.with(a = 90)))
        ModuleDebug.debugGeometry(this, "Point", ModuleDebug.DebuggedPoint(nextPoint, Color4b.WHITE))

        val rotationPreference = LeastDifferencePreference.leastDifferenceToLastPoint(eyes, nextPoint)

        // find best spot
        val spot = raytraceBox(
            eyes, point.cutOffBox,
            // Since [range] is squared, we need to square root
            range = range,
            wallsRange = activeWallRange().toDouble(),
            rotationPreference = rotationPreference
        ) ?: raytraceBox(
            eyes, point.box,
            range = range,
            wallsRange = activeWallRange().toDouble(),
            rotationPreference = rotationPreference
        )

        val resolvedSpot = if (spot == null && rotations.aimThroughWalls) {
            val throughSpot = raytraceBox(
                eyes, point.cutOffBox,
                // Since [range] is squared, we need to square root
                range = range,
                wallsRange = range,
                rotationPreference = rotationPreference
            ) ?: raytraceBox(
                eyes, point.box,
                range = range,
                wallsRange = range,
                rotationPreference = rotationPreference
            )

            throughSpot
        } else {
            spot
        }

        val ordinaryAimPoint = resolvedSpot?.vec ?: return null
        val aimPoint = ModuleEMaceHelper.applyPeakAssistAimOffset(entity, ordinaryAimPoint) ?: return null
        return RotationWithVector(Rotation.lookingAt(point = aimPoint, from = eyes), aimPoint)
    }

    /**
     * V-Core Track's authoritative point selection.  ElytraPlus is intentionally absent from
     * this fork, so the source's first branch is equivalent to its disabled state here.
     */
    internal fun resolveVCoreTrackAimPoint(entity: LivingEntity): Vec3? {
        val localPlayer = mc.player ?: return null
        val localWorld = mc.level ?: return null

        return trackAimPointCache.getOrUpdate(
            tickIdentity = trackAimTickIdentity,
            targetIdentity = entity,
            playerIdentity = localPlayer,
            worldIdentity = localWorld,
        ) {
            val dynamic = !localPlayer.isFallFlying
            val base = if (dynamic) {
                getDynamicTrackLookPoint(entity, activeAttackRange().toDouble())
            } else if (ModuleElytraTarget.shouldTarget(entity)) {
                ModuleElytraTarget.getElytraTargetVec(entity)
            } else {
                entity.eyePosition
            } ?: return@getOrUpdate null

            val withPeekHigh = if (dynamic) {
                // getDynamicTrackLookPoint contains V-Core's own PeekHigh branch.
                base
            } else if (KillAuraRotationsConfigurable.aimThroughWalls && ModuleWallsBypass.running &&
                ModuleWallsBypass.isPeekHigh() && !localPlayer.canSee(entity)) {
                entity.position().add(
                    (-0.15..0.15).random(),
                    entity.boundingBox.maxY - entity.boundingBox.minY,
                    (-0.15..0.15).random(),
                )
            } else {
                base
            }

            ModuleEMaceHelper.applyPeakAssistAimOffset(entity, withPeekHigh)
        }
    }

    /** V-Core Grim's base/prediction -> PeekHigh -> PeakAssist point order. */
    internal fun resolveVCoreGrimAimPoint(entity: LivingEntity): Vec3? {
        val predicted = ModuleElytraTarget.shouldTarget(entity)
        val base = if (predicted) {
            ModuleElytraTarget.getElytraTargetVec(entity) ?: return null
        } else {
            entity.boundingBox.center
        }

        val withPeekHigh = if (KillAuraRotationsConfigurable.aimThroughWalls && ModuleWallsBypass.running &&
            ModuleWallsBypass.isPeekHigh() && !player.canSee(entity)) {
            entity.position().add((-0.15..0.15).random(), entity.boundingBox.maxY - entity.boundingBox.minY, (-0.15..0.15).random())
        } else {
            base
        }

        return ModuleEMaceHelper.applyPeakAssistAimOffset(entity, withPeekHigh)
    }

    /** Exact V-Core Track dynamic point state and scan, adapted to current Vec/AABB names. */
    private fun getDynamicTrackLookPoint(target: LivingEntity, attackRange: Double): Vec3 {
        val minMotionXZ = 0.003f
        val maxMotionXZ = 0.03f
        val minMotionY = 0.001f
        val maxMotionY = 0.03f
        val lengthX = target.boundingBox.maxX - target.boundingBox.minX
        val lengthY = target.boundingBox.maxY - target.boundingBox.minY
        val lengthZ = target.boundingBox.maxZ - target.boundingBox.minZ

        if (rotationMotion == Vec3.ZERO) {
            rotationMotion = Vec3(
                (-0.05f..0.05f).random().toDouble(),
                (-0.05f..0.05f).random().toDouble(),
                (-0.05f..0.05f).random().toDouble(),
            )
        }

        rotationPoint = rotationPoint.add(rotationMotion)
        rotationMotion = updateVCoreTrackBoundaryMotion(
            rotationPoint = rotationPoint,
            rotationMotion = rotationMotion,
            lengthX = lengthX,
            lengthY = lengthY,
            lengthZ = lengthZ,
            randomMotionXZ = { (minMotionXZ..maxMotionXZ).random().toDouble() },
            randomMotionY = { (minMotionY..maxMotionY).random().toDouble() },
        )

        // Preserve V-Core's non-assigned random add exactly; it is intentionally ineffective.
        rotationPoint.add((-0.03f..0.03f).random().toDouble(), 0.0, (-0.03f..0.03f).random().toDouble())

        if (KillAuraRotationsConfigurable.aimThroughWalls && ModuleWallsBypass.running &&
            ModuleWallsBypass.isPeekHigh() && !player.canSee(target)) {
            return target.position().add((-0.15..0.15).random(), lengthY, (-0.15..0.15).random())
        }

        val initialRotation = selectVCoreTrackInitialRotation(
            killAuraOwnedRotation = activeServerFacingRotationFor(target),
            playerRotation = player.rotation,
        )
        val initialWallRange = activeWallRange().toDouble()
        if (!evaluateVCoreTrackInitialRaytrace(
                raytraceEnabled = raycast != TRACE_NONE,
                attackRange = attackRange,
                wallRange = initialWallRange,
            ) { range, wallsRange ->
                facingEnemy(
                    toEntity = target,
                    rotation = initialRotation,
                    range = range,
                    wallsRange = wallsRange,
                )
            }) {
            val center = target.position().add(0.0, target.eyeHeight / 2.0, 0.0)
            if (player.eyePosition.distanceToSqr(center) <= attackRange.pow(2) &&
                trackSourceFacingEnemy(toEntity = target, rotation = Rotation.lookingAt(point = center, from = player.eyePosition), range = attackRange, wallsRange = 0.0)
            ) {
                rotationPoint = Vec3(
                    (-0.1f..0.1f).random().toDouble(),
                    target.eyeHeight.toDouble() / (1.8f..2.5f).random().toDouble(),
                    (-0.1f..0.1f).random().toDouble(),
                )
            } else {
                val halfBox = (lengthX / 2.0).toFloat()
                for (x in sequence {
                    var value = -halfBox
                    while (value <= halfBox) {
                        yield(value)
                        value += 0.05f
                    }
                }) {
                    for (z in sequence {
                        var value = -halfBox
                        while (value <= halfBox) {
                            yield(value)
                            value += 0.05f
                        }
                    }) {
                        for (y in sequence {
                            var value = 0.05f
                            while (value <= lengthY) {
                                yield(value)
                                value += 0.15f
                            }
                        }) {
                            val point = Vec3(target.x + x, target.y + y, target.z + z)
                            if (player.eyePosition.distanceToSqr(point) <= attackRange.pow(2) &&
                                trackSourceFacingEnemy(toEntity = target, rotation = Rotation.lookingAt(point = point, from = player.eyePosition), range = attackRange, wallsRange = 0.0)
                            ) {
                                rotationPoint = Vec3(x.toDouble(), y.toDouble(), z.toDouble())
                                break
                            }
                        }
                    }
                }
            }
        }

        return target.position().add(rotationPoint)
    }

    private fun trackSourceFacingEnemy(
        toEntity: Entity,
        rotation: Rotation,
        range: Double,
        wallsRange: Double,
    ): Boolean = raycast == TRACE_NONE || facingEnemy(
        toEntity = toEntity,
        rotation = rotation,
        range = range,
        wallsRange = wallsRange,
    )

    /** True when the source ShieldBreaker would own this target before normal Aura preparation. */
    internal fun canDirectShieldBreak(entity: Entity): Boolean {
        val target = entity as? Player ?: return false
        return shieldBreaker && target.isUsingItem &&
            (target.useItem.item == Items.SHIELD || target.mainHandItem.item == Items.SHIELD) &&
            findVCoreAxe() != null
    }

    /** V-Core Aura shield-break flow, including its raw same-update inventory sequence. */
    private fun performShieldBreaker(entity: Entity): Boolean {
        if (!canDirectShieldBreak(entity)) {
            return false
        }

        val axe = findVCoreAxe() ?: return false
        val target = entity as Player
        val originalSelectedSlot = player.inventory.selectedSlot
        directShieldBreakerAttackInProgress = true

        try {
            if (axe.hotbarSlot != null) {
                // Preserve the visible selected slot: this route is packet-only for the Axe.
                network.sendPacket(ServerboundSetCarriedItemPacket(axe.hotbarSlot))
                rawShieldBreakerAttack(target)
                tryMaceStunShieldBreaker(target)
                vCoreShieldBreakerReturnSlot(player.inventory.selectedSlot)?.let { sourceReturnSlot ->
                    network.sendPacket(ServerboundSetCarriedItemPacket(sourceReturnSlot))
                }
            } else {
                // V-Core swaps a main-inventory Axe into the selected hotbar slot, attacks in
                // the same update, and swaps the exact source slot back before closing again.
                val sourceSlot = axe.inventoryServerSlot ?: return false
                interaction.clickSlot(
                    player.screenHandler.syncId,
                    sourceSlot,
                    originalSelectedSlot,
                    ContainerInput.SWAP,
                    player,
                )
                sendShieldBreakerClose()
                rawShieldBreakerAttack(target)
                tryMaceStunShieldBreaker(target)
                interaction.clickSlot(
                    player.screenHandler.syncId,
                    sourceSlot,
                    player.inventory.selectedSlot,
                    ContainerInput.SWAP,
                    player,
                )
                sendShieldBreakerClose()
            }

            shieldBreakerHitTicks = 10
            currentScanExtraRange = scanExtraRange.random()
            KillAuraNotifyWhenFail.failedHitsIncrement = 0
            return true
        } finally {
            directShieldBreakerAttackInProgress = false
        }
    }

    private fun rawShieldBreakerAttack(target: Entity) {
        interaction.attackEntity(player, target)
        player.swingHand(InteractionHand.MAIN_HAND)
    }

    private fun tryMaceStunShieldBreaker(target: Entity) {
        if (!ModuleMaceSwap.running || !ModuleMaceSwap.isMaceStun()) {
            return
        }

        // Source quirk: no hasMaceInHotbar() guard. If silentSwapToMace() returns -1,
        // V-Core still sends this second attack using the current item.
        ModuleMaceSwap.calledFromAura = true
        ModuleMaceSwap.beginMaceStunAttack()
        try {
            ModuleMaceSwap.silentSwapToMace()
            ModuleMaceSwap.withMaceAttackBoundary {
                rawShieldBreakerAttack(target)
            }
        } finally {
            ModuleMaceSwap.endMaceStunAttack()
        }
    }

    private fun sendShieldBreakerClose() {
        network.sendPacket(ServerboundContainerClosePacket(player.screenHandler.syncId))
    }

    /** InventoryUtility.getAxe() port: durability plus Sharpness, strict first-best scan order. */
    private fun findVCoreAxe(): ShieldBreakerAxe? {
        var bestScore = 1.0f
        var bestRawSlot = -1

        for (rawSlot in 9..44) {
            val inventorySlot = if (rawSlot >= 36) rawSlot - 36 else rawSlot
            val stack = player.inventory.getStack(inventorySlot)
            if (!stack.isAxe) {
                continue
            }

            val score = stack.maxDamage.toFloat() + stack.sharpnessLevel
            if (score > bestScore) {
                bestScore = score
                bestRawSlot = rawSlot
            }
        }

        if (bestRawSlot == -1) {
            return null
        }

        return if (bestRawSlot >= 36) {
            ShieldBreakerAxe(hotbarSlot = bestRawSlot - 36, inventoryServerSlot = null)
        } else {
            ShieldBreakerAxe(hotbarSlot = null, inventoryServerSlot = bestRawSlot)
        }
    }

    private data class ShieldBreakerAxe(
        val hotbarSlot: Int?,
        val inventoryServerSlot: Int?,
    )

    /**
     * Check if we can attack the target at the current moment
     */
    internal fun validateAttack(target: Entity? = null): Boolean {
        val criticalHit = target == null || player.isGliding || criticalsSelectionMode.isCriticalHit(target)
        val isInInventoryScreen = isInventoryOpen || isInContainerScreen
        val isBreakingBlock = mc.gameMode?.isDestroying == true

        return criticalHit &&
            shieldBreakerHitTicks <= 0 &&
            !isBreakingBlock &&
            !(isInInventoryScreen && !ignoreOpenInventory && !simulateInventoryClosing)
    }

    internal fun isTrackAttackReady(target: Entity): Boolean =
        targetTracker.target === target && clickScheduler.isClickTick && validateAttack(target)

    internal fun isRotationRaytracingTarget(target: Entity, rotation: Rotation): Boolean {
        if (raycast == TRACE_NONE) {
            return true
        }

        val traceRange = if (player.isFallFlying) activeElytraAcquisitionRange() else targetStrafeMaxRange
        return facingEnemy(toEntity = target, range = traceRange.toDouble(), rotation = rotation)
    }

    /** V-Core Track pitch acceleration range: attack range plus aim range, without Aura's +4 acquisition allowance. */
    internal fun isTrackPitchRaytracingTarget(target: Entity, rotation: Rotation): Boolean {
        val traceRange = trackPitchRaytraceRange().toDouble()
        return evaluateTrackPitchRaytrace(
            isCurrentTarget = targetTracker.target === target,
            raytraceEnabled = raycast != TRACE_NONE,
            traceRange = traceRange,
        ) { range, wallsRange ->
            facingEnemy(
                toEntity = target,
                rotation = rotation,
                range = range,
                wallsRange = wallsRange,
            )
        }
    }

    private fun isActiveKillAuraRotationFor(target: Entity): Boolean {
        val rotationTarget = RotationManager.activeRotationTarget ?: return false
        return rotationTarget.provider === this && rotationTarget.entity === target
    }

    private fun activeServerFacingRotationFor(target: Entity): Rotation? {
        if (targetTracker.target !== target) {
            return null
        }

        val rotation = RotationManager.currentRotation ?: return null
        return rotation.takeIf {
            running && !target.isRemoved &&
                it.yaw.isFinite() && it.pitch.isFinite() &&
                isActiveKillAuraRotationFor(target)
        }
    }

    /** Returns Aura's active server-facing rotation only while Aura owns the current target. */
    @JvmStatic
    fun activeServerFacingRotationForCurrentTarget(): Rotation? {
        val target = targetTracker.target ?: return null
        return activeServerFacingRotationFor(target)
    }

    @JvmStatic
    fun isFocusedFullMoveFixActive(): Boolean = FocusedFullMovementContextManager.isActive()

    private fun squaredDistanceToTargetPoint(entity: Entity): Double {
        val living = entity as? LivingEntity
        if (living != null && ModuleElytraTarget.shouldTarget(living)) {
            val targetPoint = ModuleElytraTarget.getElytraTargetVec(living)
            if (targetPoint != null) {
                return player.eyePosition.distanceToSqr(targetPoint)
            }
        }

        return entity.squaredBoxedDistanceTo(player)
    }

    private fun activeAttackRange(): Float = if (player.isFallFlying) elytraRange else range

    private fun activeAimRange(): Float = if (player.isFallFlying) activeElytraAcquisitionRange() else range

    private fun trackPitchRaytraceRange(): Float = activeAttackRange() +
        if (player.isFallFlying) {
            ModuleEMaceHelper.getElytraAimRangeOverride(elytraAimRange).toFloat()
        } else {
            range
        }

    private fun activeWallRange(): Float = resolveVCoreActiveWallRange(
        aimThroughWalls = KillAuraRotationsConfigurable.aimThroughWalls,
        isFallFlying = player.isFallFlying,
        activeAttackRange = activeAttackRange(),
        groundedWallRange = wallRange,
        effectiveElytraAimRange = ModuleEMaceHelper.getElytraAimRangeOverride(elytraAimRange).toFloat(),
    )

    private fun activeElytraAcquisitionRange(): Float {
        val aimRange = ModuleEMaceHelper.getElytraAimRangeOverride(elytraAimRange).toFloat()
        return elytraRange + aimRange + if (targetTracker.target != null) 4.0f else 0.0f
    }

    /** V-Core's intentionally unusual Elytra attack window, measured from the player position. */
    private fun canAttackElytraTarget(target: LivingEntity): Boolean {
        val predicted = ModuleElytraTarget.getElytraTargetVec(target) ?: return false
        val prediction = ModuleElytraTarget.getPrediction(target)
        return player.position().distanceTo(predicted) <= prediction - 0.5 ||
            player.boundingBox.intersects(AABB(predicted, predicted))
    }

    enum class RaycastMode(override val choiceName: String) : NamedChoice {
        TRACE_NONE("None"),
        TRACE_ONLYENEMY("Enemy"),
        TRACE_ALL("All")
    }

}

internal inline fun evaluateTrackPitchRaytrace(
    isCurrentTarget: Boolean,
    raytraceEnabled: Boolean,
    traceRange: Double,
    facingTarget: (range: Double, wallsRange: Double) -> Boolean,
): Boolean {
    if (!isCurrentTarget) {
        return false
    }
    if (!raytraceEnabled) {
        return true
    }

    return facingTarget(traceRange, traceRange)
}

internal inline fun evaluateVCoreTrackInitialRaytrace(
    raytraceEnabled: Boolean,
    attackRange: Double,
    wallRange: Double,
    facingTarget: (range: Double, wallsRange: Double) -> Boolean,
): Boolean = !raytraceEnabled || facingTarget(attackRange, wallRange)

internal fun selectVCoreTrackInitialRotation(
    killAuraOwnedRotation: Rotation?,
    playerRotation: Rotation,
): Rotation = killAuraOwnedRotation ?: playerRotation

internal fun resolveVCoreActiveWallRange(
    aimThroughWalls: Boolean,
    isFallFlying: Boolean,
    activeAttackRange: Float,
    groundedWallRange: Float,
    effectiveElytraAimRange: Float,
): Float {
    if (!aimThroughWalls) {
        return 0.0f
    }

    return if (isFallFlying) {
        activeAttackRange + effectiveElytraAimRange
    } else {
        groundedWallRange
    }
}

internal class VCoreTrackAimPointCache {
    private var tickIdentity = Long.MIN_VALUE
    private var targetIdentity: Any? = null
    private var playerIdentity: Any? = null
    private var worldIdentity: Any? = null
    private var cachedPoint: Vec3? = null
    private var populated = false

    fun getOrUpdate(
        tickIdentity: Long,
        targetIdentity: Any,
        playerIdentity: Any,
        worldIdentity: Any,
        update: () -> Vec3?,
    ): Vec3? {
        if (populated && this.tickIdentity == tickIdentity &&
            this.targetIdentity === targetIdentity && this.playerIdentity === playerIdentity &&
            this.worldIdentity === worldIdentity) {
            return cachedPoint
        }

        return update().also { point ->
            this.tickIdentity = tickIdentity
            this.targetIdentity = targetIdentity
            this.playerIdentity = playerIdentity
            this.worldIdentity = worldIdentity
            cachedPoint = point
            populated = true
        }
    }

    fun clear() {
        tickIdentity = Long.MIN_VALUE
        targetIdentity = null
        playerIdentity = null
        worldIdentity = null
        cachedPoint = null
        populated = false
    }
}

internal inline fun updateVCoreTrackBoundaryMotion(
    rotationPoint: Vec3,
    rotationMotion: Vec3,
    lengthX: Double,
    lengthY: Double,
    lengthZ: Double,
    randomMotionXZ: () -> Double,
    randomMotionY: () -> Double,
): Vec3 {
    var motion = rotationMotion

    if (rotationPoint.x >= (lengthX - 0.05) / 2.0) {
        motion = Vec3(-randomMotionXZ(), motion.y, motion.z)
    }
    if (rotationPoint.y >= lengthY) {
        motion = Vec3(motion.x, -randomMotionY(), motion.z)
    }
    if (rotationPoint.z >= (lengthZ - 0.05) / 2.0) {
        motion = Vec3(motion.x, motion.y, -randomMotionXZ())
    }
    if (rotationPoint.x <= -(lengthX - 0.05) / 2.0) {
        motion = Vec3(randomMotionXZ(), motion.y, motion.z)
    }
    if (rotationPoint.y <= 0.05) {
        motion = Vec3(motion.x, randomMotionY(), motion.z)
    }
    if (rotationPoint.z <= -(lengthZ - 0.05) / 2.0) {
        motion = Vec3(motion.x, motion.y, randomMotionXZ())
    }

    return motion
}

internal fun vCoreShieldBreakerReturnSlot(currentSelectedSlot: Int): Int? =
    currentSelectedSlot.takeIf { it in 0..8 }

internal inline fun runVCoreAuraPostAction(
    dedicatedShieldBreak: Boolean,
    attacked: Boolean,
    markEMace: () -> Unit,
    markMaceSwap: (Boolean) -> Unit,
) {
    if (dedicatedShieldBreak) {
        return
    }

    markEMace()
    markMaceSwap(attacked)
}
