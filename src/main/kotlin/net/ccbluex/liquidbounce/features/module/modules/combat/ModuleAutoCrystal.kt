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

import com.mojang.blaze3d.platform.InputConstants
import net.ccbluex.liquidbounce.config.types.nesting.Configurable
import net.ccbluex.liquidbounce.event.events.AttackInputEvent
import net.ccbluex.liquidbounce.event.events.DisconnectEvent
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.ItemUseEvent
import net.ccbluex.liquidbounce.event.events.KeyboardKeyEvent
import net.ccbluex.liquidbounce.event.events.MouseButtonEvent
import net.ccbluex.liquidbounce.event.events.RotationUpdateEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.utils.aiming.PostRotationExecutor
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.aiming.RotationTarget
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.aiming.features.MovementCorrection
import net.ccbluex.liquidbounce.utils.aiming.utils.facingBlock
import net.ccbluex.liquidbounce.utils.aiming.utils.facingEnemy
import net.ccbluex.liquidbounce.utils.aiming.utils.raytraceBox
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.logger
import net.ccbluex.liquidbounce.utils.client.RestrictedSingleUseAction
import net.ccbluex.liquidbounce.utils.entity.attackEntity
import net.ccbluex.liquidbounce.utils.entity.blockPosition
import net.ccbluex.liquidbounce.utils.entity.crosshairTarget
import net.ccbluex.liquidbounce.utils.entity.eyePos
import net.ccbluex.liquidbounce.utils.entity.getStack
import net.ccbluex.liquidbounce.utils.entity.interactBlock
import net.ccbluex.liquidbounce.utils.entity.rotation
import net.ccbluex.liquidbounce.utils.entity.setPressed
import net.ccbluex.liquidbounce.utils.entity.side
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.entity.useKey
import net.ccbluex.liquidbounce.utils.input.InputBind
import net.ccbluex.liquidbounce.utils.item.isAxe
import net.ccbluex.liquidbounce.utils.item.isPickaxe
import net.ccbluex.liquidbounce.utils.item.isShovel
import net.ccbluex.liquidbounce.utils.item.isSword
import net.ccbluex.liquidbounce.utils.kotlin.Priority
import net.ccbluex.liquidbounce.utils.network.ActionTickBoundary
import net.minecraft.world.InteractionHand
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.boss.enderdragon.EndCrystal
import net.minecraft.world.entity.monster.Slime
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.RespawnAnchorBlock
import net.minecraft.world.level.ClipContext
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import org.lwjgl.glfw.GLFW

/**
 * Strict V-core 0.8 AutoCrystal port.
 *
 * This is deliberately a crosshair-driven helper, not a position- or damage-based CrystalAura.
 */
object ModuleAutoCrystal : ClientModule("AutoCrystal", Category.COMBAT) {

    private val activateKey by bind(
        "ActivateKey",
        InputBind(InputConstants.UNKNOWN, InputBind.BindAction.HOLD)
    )
    private val placeDelay by int("PlaceDelay", 0, 0..10, "ticks")
    private val breakDelay by int("BreakDelay", 0, 0..10, "ticks")
    private val headBob by boolean("HeadBob", false)
    private val crystalBase by boolean("CrystalBase", false)
    private val switchDelay by int("SwitchDelay", 0, 0..10, "ticks").visibleWhen { crystalBase }
    private val baseDelay by int("BaseDelay", 0, 0..10, "ticks").visibleWhen { crystalBase }
    private val stopOnKill by boolean("StopOnKill", false)
    private val damageTick by boolean("DamageTick", false)
    private val antiWeakness by boolean("AntiWeakness", false)

    private object BaseSetting : Configurable("BaseSetting") {
        val checkPlace by boolean("CheckPlace", false)
        val workWithTotem by boolean("WorkWithTotem", false)
        val workWithCrystal by boolean("WorkWithCrystal", false)
        val swordSwap by boolean("SwordSwap", true)
    }

    private var placeClock = 0
    private var breakClock = 0
    private var clockSnapshotTick = Int.MIN_VALUE
    private var dontPlaceThisTick = false
    private var dontBreakThisTick = false
    private var basePlaceClock = 0
    private var baseSwitchClock = 0
    private var lastCrystalTick = -1
    private var headBobActionExecutedAtSerial = Long.MIN_VALUE

    private var baseActive = false
    private var baseCrystalling = false
    private var baseCrystalSelected = false
    private var waitingForBasePlaceBoundary = false
    private var basePlaceActionSerial = Long.MIN_VALUE
    private var baseBoundaryWaitLoggedSerial = Long.MIN_VALUE
    private var baseDiagnosticsClaimed = false
    private var baseDiagnosticsEnabled = false

    private data class PendingAntiWeaknessRestoration(
        val previousSlot: Int,
        val actionBoundarySerial: Long,
        val player: Any,
        val level: Any,
        val diagnosticsEnabled: Boolean,
        var lastWaitLoggedSerial: Long = Long.MIN_VALUE,
    )

    private var pendingAntiWeaknessRestoration: PendingAntiWeaknessRestoration? = null
    private var antiWeaknessRestoredAtSerial = Long.MIN_VALUE

    private enum class EntityActionResult {
        NO_TARGET,
        NO_ACTION,
        INHIBITED_WITHOUT_PACKET,
        ATTACK_ATTEMPTED,
    }

    private enum class EntityTargetOrigin {
        CROSSHAIR,
        HEAD_BOB,
    }

    private data class EntityActionOutcome(
        val result: EntityActionResult,
        val diagnostics: ActionTickBoundary.CrystalSequenceDiagnostic? = null,
        val targetId: Int = -1,
        val targetType: String = "none",
    )

    private data class PendingEntityActionBoundary(
        val actionBoundarySerial: Long,
        val targetId: Int,
        val targetType: String,
        val diagnostics: ActionTickBoundary.CrystalSequenceDiagnostic,
        var lastWaitLoggedSerial: Long = Long.MIN_VALUE,
    )

    private sealed interface EntityActionBoundaryGate {
        object Open : EntityActionBoundaryGate
        object Waiting : EntityActionBoundaryGate
        data class Passed(val diagnostics: ActionTickBoundary.CrystalSequenceDiagnostic) : EntityActionBoundaryGate
    }

    private var pendingEntityActionBoundary: PendingEntityActionBoundary? = null

    /**
     * HeadBob deliberately owns a complete action sequence rather than a boolean "rotate then
     * attack" shortcut. In particular, the base phase cannot begin until the real packet boundary
     * after the crystal attack has passed.
     */
    private sealed interface HeadBobState {
        object Idle : HeadBobState

        data class AimingCrystal(
            val generation: Long,
            val basePos: BlockPos,
            val crystalId: Int,
            val originalVisibleHit: BlockHitResult,
            val diagnostic: HeadBobDiagnostic,
            val phaseEnteredTick: Int,
            val phaseEnteredSerial: Long,
            var targetRotation: Rotation? = null,
            var targetDistance: Double? = null,
            var rotationRequestedTick: Int = Int.MIN_VALUE,
            var rotationRequestedSerial: Long = Long.MIN_VALUE,
            var rotationPacketTick: Int = Int.MIN_VALUE,
            var rotationPacketSerial: Long = Long.MIN_VALUE,
            var preMoveActionTick: Int = Int.MIN_VALUE,
            var preMoveActionSerial: Long = Long.MIN_VALUE,
            var postMoveConfirmationScheduled: Boolean = false,
            var postMoveConfirmationScheduledSerial: Long = Long.MIN_VALUE,
            var preMoveActionScheduled: Boolean = false,
            var preMoveActionScheduledSerial: Long = Long.MIN_VALUE,
            val crystalSpawnLatencyTicks: Int = -1,
            var serverFacingLogged: Boolean = false,
            var lastRotationWaitWarningSerial: Long = Long.MIN_VALUE,
        ) : HeadBobState

        data class WaitingAfterAttack(
            val generation: Long,
            val basePos: BlockPos,
            val crystalId: Int,
            val originalVisibleHit: BlockHitResult,
            val actionBoundarySerial: Long,
            val diagnostic: HeadBobDiagnostic,
            val crystalSequenceDiagnostic: ActionTickBoundary.CrystalSequenceDiagnostic,
            val crystalRotation: Rotation?,
            val crystalDistance: Double?,
            val phaseEnteredTick: Int,
            val phaseEnteredSerial: Long,
            val rotationRequestedTick: Int,
            val rotationRequestedSerial: Long,
            val rotationPacketTick: Int,
            val rotationPacketSerial: Long,
            val preMoveActionTick: Int,
            val preMoveActionSerial: Long,
            val crystalSpawnLatencyTicks: Int,
            var lastBoundaryWaitWarningSerial: Long = Long.MIN_VALUE,
        ) : HeadBobState

        data class AimingBase(
            val generation: Long,
            val basePos: BlockPos,
            val crystalId: Int,
            val originalVisibleHit: BlockHitResult,
            val diagnostic: HeadBobDiagnostic,
            val crystalSequenceDiagnostic: ActionTickBoundary.CrystalSequenceDiagnostic?,
            val actionBoundarySerial: Long?,
            val baseAimVariant: Int,
            val phaseEnteredTick: Int,
            val phaseEnteredSerial: Long,
            var targetRotation: Rotation? = null,
            var targetDistance: Double? = null,
            var rotationRequestedTick: Int = Int.MIN_VALUE,
            var rotationRequestedSerial: Long = Long.MIN_VALUE,
            var rotationPacketTick: Int = Int.MIN_VALUE,
            var rotationPacketSerial: Long = Long.MIN_VALUE,
            var preMoveActionTick: Int = Int.MIN_VALUE,
            var preMoveActionSerial: Long = Long.MIN_VALUE,
            var postMoveConfirmationScheduled: Boolean = false,
            var postMoveConfirmationScheduledSerial: Long = Long.MIN_VALUE,
            var preMoveActionScheduled: Boolean = false,
            var preMoveActionScheduledSerial: Long = Long.MIN_VALUE,
            val crystalSpawnLatencyTicks: Int,
            var serverFacingLogged: Boolean = false,
            var lastRotationWaitWarningSerial: Long = Long.MIN_VALUE,
        ) : HeadBobState
    }

    private data class HeadBobDiagnostic(val enabled: Boolean)

    /**
     * A locally resolved crystal is the first observable evidence that the prior HeadBob place
     * reached the server and returned through normal world networking. Keep this separate from
     * rotation timing so diagnostics do not misattribute spawn/ping delay to the action pipeline.
     */
    private data class PendingHeadBobCrystalSpawn(
        val basePos: BlockPos,
        val placementTick: Int,
        val placementSerial: Long,
    )

    /**
     * A cancelled HeadBob attack still needs the existing entity-action boundary to drain. This
     * records that one action phase must not turn into a normal placement after a look-away or
     * setting-disable cancellation.
     */
    private data class PendingHeadBobCancellation(
        val safetyBoundarySerial: Long,
        val diagnostic: HeadBobDiagnostic,
        val basePos: BlockPos,
        val crystalId: Int,
    )

    private var headBobState: HeadBobState = HeadBobState.Idle
    private var headBobGeneration = 0L
    private var headBobBaseAimVariant = 0
    private var pendingHeadBobCancellation: PendingHeadBobCancellation? = null
    private var pendingHeadBobCrystalSpawn: PendingHeadBobCrystalSpawn? = null
    private var headBobDiagnosticCyclesRemaining = MAX_HEAD_BOB_DIAGNOSTIC_CYCLES
    private var headBobDiagnosticMessagesRemaining = MAX_HEAD_BOB_DIAGNOSTIC_MESSAGES

    var crystalling = false
        private set

    private var observedPlayer: Any? = null
    private var observedLevel: Any? = null
    private var observedActivationKey = InputConstants.UNKNOWN
    private var activationBindEventDown = false

    init {
        BaseSetting.visibleWhen { crystalBase }
        tree(BaseSetting)
    }

    override fun enable() {
        observedPlayer = mc.player
        observedLevel = mc.level
        resetHeadBobDiagnosticBudget()
        resetState(clearHeadBobSafety = true)
    }

    override fun disable() {
        observedPlayer = null
        observedLevel = null
        activationBindEventDown = false
        resetState(clearHeadBobSafety = true)
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        runCrystalTick(ignoreActivationKey = false)
    }

    /**
     * RotationManager emits this before it applies the tick's rotation target. HeadBob therefore
     * plans here rather than from the ordinary GameTick handler, which would otherwise miss the
     * current movement packet and require a polling tick just to observe server rotation.
     */
    @Suppress("unused")
    private val headBobRotationUpdateHandler = handler<RotationUpdateEvent> {
        planHeadBobRotationPhase()
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        observedPlayer = null
        observedLevel = null
        resetHeadBobDiagnosticBudget()
        resetState(clearHeadBobSafety = true)
    }

    @Suppress("unused")
    private val disconnectHandler = handler<DisconnectEvent> {
        observedPlayer = null
        observedLevel = null
        resetHeadBobDiagnosticBudget()
        resetState(clearHeadBobSafety = true)
    }

    @Suppress("unused")
    private val activationKeyboardHandler = handler<KeyboardKeyEvent> { event ->
        updateActivationBindState(
            activateKey,
            activateKey.matchesKey(event.keyCode, event.scanCode),
            event.action
        )
    }

    @Suppress("unused")
    private val activationMouseHandler = handler<MouseButtonEvent> { event ->
        updateActivationBindState(activateKey, activateKey.matchesMouse(event.button), event.action)
    }

    @Suppress("unused")
    private val attackInputHandler = handler<AttackInputEvent> { event ->
        val player = mc.player ?: return@handler

        if (crystalBase && player.mainHandItem.item == Items.END_CRYSTAL && !isMouseDown(0)) {
            event.cancelEvent()
        }
    }

    @Suppress("unused")
    private val itemUseHandler = handler<ItemUseEvent> { event ->
        val player = mc.player ?: return@handler
        val mainHandItem = player.mainHandItem.item

        if (crystalBase && (mainHandItem == Items.END_CRYSTAL || mainHandItem == Items.OBSIDIAN) && !isMouseDown(1)) {
            event.cancelEvent()
            return@handler
        }

        if (mainHandItem == Items.END_CRYSTAL) {
            val lookedBlock = getLookedBlock()
            if (lookedBlock != null && isCrystalBase(lookedBlock.blockPosition())) {
                event.cancelEvent()
            }
        }
    }

    /**
     * Runs one V-core AutoCrystal tick. This intentionally remains callable for integrations that
     * need to bypass the activation bind while preserving the same clocks and action order.
     */
    fun runCrystalTick(ignoreActivationKey: Boolean) {
        val player = mc.player
        val level = mc.level

        if (player == null || level == null) {
            if (observedPlayer != null || observedLevel != null) {
                observedPlayer = null
                observedLevel = null
                resetHeadBobDiagnosticBudget()
                resetState(clearHeadBobSafety = true)
            }
            return
        }

        if (observedPlayer !== player || observedLevel !== level) {
            observedPlayer = player
            observedLevel = level
            resetHeadBobDiagnosticBudget()
            resetState(clearHeadBobSafety = true)
        }

        var headBobCancelledThisTick = false
        if (!headBob && headBobState !is HeadBobState.Idle) {
            cancelHeadBobState("setting-disabled")
            headBobCancelledThisTick = true
        }

        // AntiWeakness may temporarily select a tool after the HeadBob attack. Its existing
        // restoration owns that temporary non-crystal hand and must finish before we decide that
        // the user stopped holding a crystal.
        if (headBobState !is HeadBobState.Idle &&
            !isHolding(Items.END_CRYSTAL) && pendingAntiWeaknessRestoration == null) {
            cancelHeadBobState("main-hand-no-longer-end-crystal")
            headBobCancelledThisTick = true
        }

        if (mc.screen != null) {
            cancelHeadBobState("screen-open")
            return
        }

        updateActionClockSnapshot(player.tickCount)
        val dontPlace = dontPlaceThisTick
        val dontBreak = dontBreakThisTick

        // The next held-item restore belongs to an earlier attack. It must complete before
        // any further AutoCrystal action, and only after the actual client tick boundary. Keep
        // the V-core clocks advancing before this mandatory no-action return.
        if (handlePendingAntiWeaknessRestoration(player, level)) {
            return
        }

        // `runCrystalTick` remains callable by integrations. Once a deferred restore has
        // happened, a second invocation in the same server-visible action tick cannot start
        // another attack/place sequence ahead of the next natural CLIENT_TICK_END.
        if (antiWeaknessRestoredAtSerial == ActionTickBoundary.currentSerial()) {
            return
        }

        // A HeadBob executor action runs at GameTick FIRST, before this ordinary handler. Do not
        // let the direct crosshair path send a second action in that same client action tick after
        // the HeadBob state has intentionally reset following a successful placement.
        if (headBobActionExecutedAtSerial == ActionTickBoundary.currentSerial()) {
            return
        }

        if (headBobCancelledThisTick) {
            return
        }

        if (stopOnKill && isDeadPlayerNearby(player)) {
            return
        }

        if (player.isUsingItem) {
            return
        }

        if (damageTick && shouldWaitDamageTick(player)) {
            return
        }

        if (!ignoreActivationKey && !isActivationDown()) {
            resetState()
            return
        }

        // A direct attack is an entity action for Grim even when another Wraith listener later
        // cancels the interaction. Do not enter CrystalBase or block placement until the real
        // CLIENT_TICK_END boundary has left the client.
        val entityBoundaryGate = processPendingEntityActionBoundary()
        if (entityBoundaryGate === EntityActionBoundaryGate.Waiting) {
            return
        }
        val passedEntityBoundaryDiagnostics = (entityBoundaryGate as? EntityActionBoundaryGate.Passed)?.diagnostics

        // A cancellation during the HeadBob post-attack wait must not become a normal block
        // placement as soon as the shared boundary opens.
        if (skipCancelledHeadBobActionPhase()) {
            return
        }

        // CrystalBase's pending base-placement boundary must remain ahead of HeadBob. Once it is
        // clear, an active HeadBob phase owns the tick so normal CrystalBase logic cannot emit a
        // competing action while a silent crystal/base rotation is in progress.
        if (crystalBase && processPendingCrystalBaseBoundary()) {
            return
        }
        if (!crystalBase) {
            resetCrystalBaseState()
        }

        crystalling = true

        if (headBobState !is HeadBobState.Idle) {
            // An active HeadBob phase is planned by RotationUpdateEvent, confirmed post-move,
            // and acted by the following executor pre-move callback. Polling here would add a
            // second confirmation tick and duplicate the action path.
            return
        }

        val crystalBaseBlocked = crystalBase && handleCrystalBase()
        if (crystalBaseBlocked) {
            return
        }

        if (!isHolding(Items.END_CRYSTAL)) {
            return
        }

        if (lastCrystalTick != player.tickCount) {
            lastCrystalTick = player.tickCount

            val entityOutcome = handleEntityTarget(dontBreak)
            when (entityOutcome.result) {
                EntityActionResult.ATTACK_ATTEMPTED -> {
                    armEntityActionBoundary(entityOutcome)
                    return
                }

                EntityActionResult.NO_TARGET -> {
                    // Prefer the diagnostic cycle that just crossed the real boundary, so the
                    // retained test log reads ATTACK -> CLIENT_TICK_END -> USE_ITEM_ON.
                    handleBlockTarget(dontPlace, passedEntityBoundaryDiagnostics ?: entityOutcome.diagnostics)
                }

                EntityActionResult.NO_ACTION,
                EntityActionResult.INHIBITED_WITHOUT_PACKET -> {
                    // Prefer the diagnostic cycle that just crossed the real boundary, so the
                    // retained test log reads ATTACK -> CLIENT_TICK_END -> USE_ITEM_ON.
                    handleBlockTarget(dontPlace, passedEntityBoundaryDiagnostics ?: entityOutcome.diagnostics)
                }
            }
        }
    }

    private fun resetState(clearHeadBobSafety: Boolean = false) {
        cancelHeadBobState("module-reset")
        // Lifecycle reset is itself a generation boundary even when the state was already Idle.
        // This keeps an unexpectedly retained executor callback inert across world/module resets.
        headBobGeneration++
        if (clearHeadBobSafety) {
            pendingHeadBobCancellation = null
        }
        pendingHeadBobCrystalSpawn = null
        placeClock = 0
        breakClock = 0
        clockSnapshotTick = Int.MIN_VALUE
        dontPlaceThisTick = false
        dontBreakThisTick = false
        lastCrystalTick = -1
        headBobActionExecutedAtSerial = Long.MIN_VALUE
        headBobBaseAimVariant = 0
        crystalling = false
        pendingAntiWeaknessRestoration = null
        antiWeaknessRestoredAtSerial = Long.MIN_VALUE
        pendingEntityActionBoundary = null
        resetCrystalBaseState()
    }

    /**
     * HeadBob planning runs before the ordinary AutoCrystal tick. Snapshot the source clocks once
     * so both paths retain V-core's `clock == 1` semantics without double-decrementing.
     */
    private fun updateActionClockSnapshot(playerTick: Int) {
        if (clockSnapshotTick == playerTick) {
            return
        }

        clockSnapshotTick = playerTick
        dontPlaceThisTick = placeClock > 0
        dontBreakThisTick = breakClock > 0

        if (dontPlaceThisTick) {
            placeClock--
        }

        if (dontBreakThisTick) {
            breakClock--
        }
    }

    /**
     * Opens the next AutoCrystal action phase only after the same packet boundary that Grim uses
     * to clear MultiActionsF's entity/block state. This intentionally does not use player ticks,
     * wall-clock time, movement packets, or a fabricated tick-end packet.
     */
    private fun processPendingEntityActionBoundary(): EntityActionBoundaryGate {
        val pending = pendingEntityActionBoundary ?: return EntityActionBoundaryGate.Open

        if (!ActionTickBoundary.hasPassed(pending.actionBoundarySerial)) {
            if (pending.lastWaitLoggedSerial != ActionTickBoundary.currentSerial()) {
                pending.lastWaitLoggedSerial = ActionTickBoundary.currentSerial()
                ActionTickBoundary.crystalSequenceDiagnostic(
                    pending.diagnostics,
                    "block placement skipped before boundary targetId=${pending.targetId} " +
                        "targetType=${pending.targetType} boundarySerial=${pending.actionBoundarySerial}"
                )
            }
            return EntityActionBoundaryGate.Waiting
        }

        pendingEntityActionBoundary = null
        ActionTickBoundary.crystalSequenceDiagnostic(
            pending.diagnostics,
            "CLIENT_TICK_END observed serial=${ActionTickBoundary.currentSerial()} " +
                "after ATTACK boundarySerial=${pending.actionBoundarySerial}"
        )
        ActionTickBoundary.crystalSequenceDiagnostic(
            pending.diagnostics,
            "post-entity boundary passed targetId=${pending.targetId} targetType=${pending.targetType}"
        )
        return EntityActionBoundaryGate.Passed(pending.diagnostics)
    }

    private fun armEntityActionBoundary(outcome: EntityActionOutcome): Long? {
        val diagnostics = outcome.diagnostics ?: return null
        val serial = ActionTickBoundary.currentSerial()

        pendingEntityActionBoundary = PendingEntityActionBoundary(
            actionBoundarySerial = serial,
            targetId = outcome.targetId,
            targetType = outcome.targetType,
            diagnostics = diagnostics
        )
        ActionTickBoundary.crystalSequenceDiagnostic(
            diagnostics,
            "AutoCrystal attack attempted; post-entity boundary armed serial=$serial"
        )
        return serial
    }

    private fun updateCrystalSequenceContext(
        diagnostics: ActionTickBoundary.CrystalSequenceDiagnostic,
        entity: Entity?,
        targetOrigin: EntityTargetOrigin = EntityTargetOrigin.CROSSHAIR,
    ) {
        val crosshairType = mc.crosshairTarget?.type?.name ?: "NONE"
        val origin = if (targetOrigin == EntityTargetOrigin.HEAD_BOB) " origin=HeadBob" else ""
        ActionTickBoundary.updateCrystalSequenceContext(
            diagnostics,
            "entityId=${entity?.id ?: -1} entityType=${entity?.javaClass?.simpleName ?: "none"} " +
                "blatant=${ModuleCrystalOptimizer.isBlatantEnabled()} breakClock=$breakClock " +
                "placeClock=$placeClock crosshair=$crosshairType " +
                "selectedSlot=${mc.player?.inventory?.selectedSlot ?: -1}$origin"
        )
    }

    private fun resetCrystalBaseState() {
        basePlaceClock = baseDelay
        baseSwitchClock = switchDelay
        baseActive = false
        baseCrystalling = false
        baseCrystalSelected = false
        waitingForBasePlaceBoundary = false
        basePlaceActionSerial = Long.MIN_VALUE
        baseBoundaryWaitLoggedSerial = Long.MIN_VALUE
        baseDiagnosticsClaimed = false
        baseDiagnosticsEnabled = false
    }

    /**
     * HeadBob starts only from the player's real camera ray. Minecraft's silent rotation hooks
     * legitimately update its transient hit result while a movement packet is being built; using
     * that transient result here would mistake the silently aimed crystal for the visible base.
     */
    private fun currentHeadBobBaseHit(expectedBase: BlockPos? = null): BlockHitResult? {
        val player = mc.player ?: return null
        val level = mc.level ?: return null
        val start = player.eyePos
        val end = start.add(player.rotation.directionVector.scale(player.blockInteractionRange()))
        val hit = level.clip(
            ClipContext(start, end, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)
        )

        if (hit.type != HitResult.Type.BLOCK) {
            return null
        }

        val basePos = hit.blockPosition()
        if ((expectedBase != null && basePos != expectedBase) || !isCrystalBase(basePos)) {
            return null
        }

        return hit
    }

    private fun crystalPlacementColumn(basePos: BlockPos): AABB {
        val crystalPos = basePos.above()
        return AABB(
            crystalPos.x.toDouble(),
            crystalPos.y.toDouble(),
            crystalPos.z.toDouble(),
            crystalPos.x + 1.0,
            crystalPos.y + 2.0,
            crystalPos.z + 1.0
        )
    }

    private fun boxCenter(box: AABB): Vec3 = Vec3(
        (box.minX + box.maxX) / 2.0,
        (box.minY + box.maxY) / 2.0,
        (box.minZ + box.maxZ) / 2.0
    )

    /**
     * The crystal item creates a crystal at (base.above().x + 0.5, base.above().y,
     * base.above().z + 0.5). The logical block position check excludes a neighbouring crystal
     * whose wide hitbox happens to overlap this placement column.
     */
    private fun isCrystalAboveExactBase(crystal: EndCrystal, basePos: BlockPos): Boolean {
        val column = crystalPlacementColumn(basePos)
        return crystal.isAlive && crystal.blockPosition() == basePos.above() && crystal.boundingBox.intersects(column)
    }

    /**
     * Resolves only an EndCrystal physically belonging to the exact base under the visible
     * crosshair. This is intentionally a placement-column query, not a nearby-crystal search.
     */
    private fun resolveHeadBobCrystal(hit: BlockHitResult): EndCrystal? {
        val level = mc.level ?: return null
        val basePos = hit.blockPosition()
        if (!isCrystalBase(basePos)) {
            return null
        }

        val column = crystalPlacementColumn(basePos)
        val center = boxCenter(column)

        return level.getEntities(null, column) { entity ->
            entity is EndCrystal && isCrystalAboveExactBase(entity, basePos)
        }.asSequence()
            .filterIsInstance<EndCrystal>()
            .minWithOrNull(
                compareBy<EndCrystal> {
                    boxCenter(it.boundingBox).distanceToSqr(center)
                }.thenBy { it.id }
            )
    }

    private fun findHeadBobCrystalById(basePos: BlockPos, crystalId: Int): EndCrystal? {
        val crystal = mc.level?.getEntity(crystalId) as? EndCrystal ?: return null
        return crystal.takeIf { isCrystalAboveExactBase(it, basePos) }
    }

    private data class HeadBobCrystalAim(val rotation: Rotation, val point: Vec3)

    private data class HeadBobBaseAim(val rotation: Rotation, val point: Vec3)

    /**
     * Uses the target-version interaction-range component and Wraith's visible box raytrace.
     * `wallsRange = 0.0` is intentional: HeadBob has no wall-through attack mode.
     */
    private fun findHeadBobCrystalAim(player: Player, crystal: EndCrystal): HeadBobCrystalAim? {
        if (!player.isWithinEntityInteractionRange(crystal, 0.0)) {
            return null
        }

        val (rotation, point) = raytraceBox(
            eyes = player.eyePos,
            box = crystal.boundingBox,
            range = player.entityInteractionRange(),
            wallsRange = 0.0,
        ) ?: return null

        return HeadBobCrystalAim(rotation, point)
    }

    private fun isRotationFacingHeadBobCrystal(player: Player, crystal: EndCrystal, rotation: Rotation): Boolean {
        if (!player.isWithinEntityInteractionRange(crystal, 0.0)) {
            return false
        }

        return facingEnemy(
            fromEntity = player,
            toEntity = crystal,
            rotation = rotation,
            range = player.entityInteractionRange(),
            wallsRange = 0.0,
        )
    }

    private fun isCurrentRotationFacingHeadBobCrystal(player: Player, crystal: EndCrystal): Boolean {
        val rotation = RotationManager.currentRotation ?: return false
        return isRotationFacingHeadBobCrystal(player, crystal, rotation)
    }

    private fun isActualServerFacingHeadBobCrystal(player: Player, crystal: EndCrystal): Boolean =
        isRotationFacingHeadBobCrystal(player, crystal, RotationManager.actualServerRotation)

    private fun isRotationFacingHeadBobBase(player: Player, hit: BlockHitResult, rotation: Rotation): Boolean {
        val basePos = hit.blockPosition()
        if (!isCrystalBase(basePos) || !player.isWithinBlockInteractionRange(basePos, 0.0)) {
            return false
        }

        val range = player.blockInteractionRange()
        val rayEnd = player.eyePos.add(rotation.directionVector.scale(range))
        return facingBlock(
            eyes = player.eyePos,
            vec3 = rayEnd,
            blockPos = basePos,
            expectedSide = hit.direction,
            expectedMaxRange = range,
        )
    }

    private fun isCurrentRotationFacingHeadBobBase(player: Player, hit: BlockHitResult): Boolean {
        val rotation = RotationManager.currentRotation ?: return false
        return isRotationFacingHeadBobBase(player, hit, rotation)
    }

    private fun isActualServerFacingHeadBobBase(player: Player, hit: BlockHitResult): Boolean =
        isRotationFacingHeadBobBase(player, hit, RotationManager.actualServerRotation)

    /**
     * The exact-base proof accepts every ray that lands on the required face. Cycle through a
     * small set of points on that same face instead of sending the identical silent delta for
     * every repeated crystal placement. This remains fully revalidated against the real server
     * rotation before USE_ITEM_ON.
     */
    private fun findHeadBobBaseAim(
        player: Player,
        hit: BlockHitResult,
        variant: Int,
    ): HeadBobBaseAim? {
        val basePos = hit.blockPosition()
        if (!isCrystalBase(basePos) || !player.isWithinBlockInteractionRange(basePos, 0.0)) {
            return null
        }

        val faceCenter = Vec3.atCenterOf(basePos).add(
            hit.direction.stepX * 0.5,
            hit.direction.stepY * 0.5,
            hit.direction.stepZ * 0.5,
        )
        val (firstTangent, secondTangent) = when (hit.direction.axis) {
            Direction.Axis.Y -> Vec3(1.0, 0.0, 0.0) to Vec3(0.0, 0.0, 1.0)
            Direction.Axis.Z -> Vec3(1.0, 0.0, 0.0) to Vec3(0.0, 1.0, 0.0)
            Direction.Axis.X -> Vec3(0.0, 0.0, 1.0) to Vec3(0.0, 1.0, 0.0)
        }
        // The first tangent is horizontal for every face axis above. Keep it distinct in every
        // candidate: changing only the vertical tangent produces the same horizontal rotation
        // delta on a vertical face, which is observable as a repeated rotation/place pattern.
        val offsets = arrayOf(
            -0.22 to 0.11,
            -0.16 to -0.11,
            -0.10 to 0.15,
            -0.04 to -0.15,
            0.04 to 0.15,
            0.10 to -0.15,
            0.16 to 0.11,
            0.22 to -0.11,
        )

        offsets.indices.forEach { index ->
            val (firstOffset, secondOffset) = offsets[Math.floorMod(variant + index, offsets.size)]
            val point = faceCenter
                .add(firstTangent.scale(firstOffset))
                .add(secondTangent.scale(secondOffset))
            val rotation = Rotation.lookingAt(point, player.eyePos)
            if (isRotationFacingHeadBobBase(player, hit, rotation)) {
                return HeadBobBaseAim(rotation, point)
            }
        }

        val fallbackRotation = Rotation.lookingAt(hit.location, player.eyePos)
        return fallbackRotation.takeIf { isRotationFacingHeadBobBase(player, hit, it) }
            ?.let { HeadBobBaseAim(it, hit.location) }
    }

    private fun planHeadBobRotationPhase() {
        val player = mc.player ?: return
        val level = mc.level ?: return

        // World/player lifecycle is normally reset by runCrystalTick later in this same client
        // tick. Invalidate a queued post-move callback now, before it can observe the new world.
        if (observedPlayer !== player || observedLevel !== level) {
            if (headBobState !is HeadBobState.Idle) {
                cancelHeadBobState("player-or-world-changed-before-rotation-update")
            }
            return
        }

        if (!running || !headBob) {
            if (headBobState !is HeadBobState.Idle) {
                cancelHeadBobState("setting-disabled-before-rotation-update")
            }
            return
        }

        if (mc.screen != null) {
            if (headBobState !is HeadBobState.Idle) {
                cancelHeadBobState("screen-open-before-rotation-update")
            }
            return
        }

        updateActionClockSnapshot(player.tickCount)

        if (headBobState !is HeadBobState.Idle &&
            !isHolding(Items.END_CRYSTAL) && pendingAntiWeaknessRestoration == null) {
            cancelHeadBobState("main-hand-no-longer-end-crystal-before-rotation-update")
            return
        }

        if (!isActivationDown()) {
            if (headBobState !is HeadBobState.Idle) {
                cancelHeadBobState("activation-released-before-rotation-update")
            }
            return
        }

        if (stopOnKill && isDeadPlayerNearby(player) || player.isUsingItem ||
            damageTick && shouldWaitDamageTick(player)) {
            return
        }

        // The ordinary tick drains these state machines after RotationManager.update. Do not let
        // a new early plan bypass a prior action's real CLIENT_TICK_END boundary.
        if (pendingHeadBobCancellation != null || waitingForBasePlaceBoundary) {
            return
        }

        if (pendingAntiWeaknessRestoration != null ||
            antiWeaknessRestoredAtSerial == ActionTickBoundary.currentSerial()) {
            return
        }

        when (val state = headBobState) {
            HeadBobState.Idle -> {
                val pendingEntityBoundary = pendingEntityActionBoundary
                if (pendingEntityBoundary != null && !ActionTickBoundary.hasPassed(pendingEntityBoundary.actionBoundarySerial)) {
                    return
                }
                planHeadBobIdle(player)
            }

            is HeadBobState.AimingCrystal -> planHeadBobCrystal(state, player)
            is HeadBobState.WaitingAfterAttack -> planHeadBobPostAttack(state, player)
            is HeadBobState.AimingBase -> planHeadBobBase(state, player)
        }
    }

    private fun planHeadBobIdle(player: Player) {
        // Direct crosshair entity handling retains priority and remains in the normal GameTick
        // path. HeadBob is only the visible-base fallback.
        if (!isHolding(Items.END_CRYSTAL) || getLookedEntity() != null || dontBreakThisTick) {
            return
        }

        val hit = currentHeadBobBaseHit() ?: return
        val crystal = resolveHeadBobCrystal(hit) ?: return

        // Match the existing no-packet optimizer path: do not create a silent HeadBob phase when
        // normal direct-base placement can handle the locally inhibited crystal this tick.
        if (ModuleCrystalOptimizer.isAttackInhibited(crystal) || !canPrepareHeadBobAttack(player)) {
            return
        }

        val aim = findHeadBobCrystalAim(player, crystal) ?: return
        val diagnostic = claimHeadBobDiagnostic()
        val crystalSpawnLatencyTicks = consumeHeadBobCrystalSpawnLatency(hit.blockPosition())
        val state = HeadBobState.AimingCrystal(
            generation = nextHeadBobGeneration(),
            basePos = hit.blockPosition(),
            crystalId = crystal.id,
            originalVisibleHit = hit,
            diagnostic = diagnostic,
            phaseEnteredTick = player.tickCount,
            phaseEnteredSerial = ActionTickBoundary.currentSerial(),
            crystalSpawnLatencyTicks = crystalSpawnLatencyTicks,
        )
        headBobState = state
        logHeadBobState(
            state,
            "Crystal acquired in RotationUpdateEvent" +
                crystalSpawnLatencyTicks.takeIf { it >= 0 }?.let {
                    "; server/network Crystal spawn latency=$it client ticks"
                }.orEmpty(),
        )
        requestHeadBobCrystalRotation(state, crystal, aim, player)
    }

    private fun planHeadBobCrystal(state: HeadBobState.AimingCrystal, player: Player) {
        clearMissedHeadBobScheduledActions(state)

        val hit = currentHeadBobBaseHit(state.basePos)
        if (hit == null) {
            cancelHeadBobState("visible-crosshair-left-base-during-crystal-aim")
            return
        }

        if (dontBreakThisTick) {
            return
        }

        val crystal = findHeadBobCrystalById(state.basePos, state.crystalId)
        if (crystal == null) {
            cancelHeadBobState("stored-crystal-missing-or-no-longer-belongs-to-base")
            return
        }

        if (ModuleCrystalOptimizer.isAttackInhibited(crystal)) {
            logHeadBobState(state, "CrystalOptimizer inhibited Crystal; no Crystal rotation or ATTACK")
            beginHeadBobBaseAim(
                basePos = state.basePos,
                crystalId = state.crystalId,
                hit = hit,
                diagnostic = state.diagnostic,
                crystalSequenceDiagnostic = null,
                actionBoundarySerial = null,
                crystalSpawnLatencyTicks = state.crystalSpawnLatencyTicks,
                player = player,
                message = "CrystalOptimizer inhibited Crystal; base rotation requested in RotationUpdateEvent",
            )
            return
        }

        if (!canPrepareHeadBobAttack(player)) {
            resetHeadBobState("missing-antiweakness-tool-before-crystal-rotation")
            return
        }

        val aim = findHeadBobCrystalAim(player, crystal)
        if (aim == null) {
            cancelHeadBobState("crystal-not-visible-or-out-of-entity-range")
            return
        }

        // The post-move proof already scheduled a verified pre-move ATTACK for this client tick.
        // RotationManager may run before PostRotationExecutor's normal queue; re-requesting here
        // would send a second look packet after that action and is neither needed for the proof
        // nor legitimate-looking for repeated placement checks.
        if (state.preMoveActionScheduled) {
            return
        }

        requestHeadBobCrystalRotation(state, crystal, aim, player)
        warnHeadBobRotationWait(state)
    }

    private fun planHeadBobPostAttack(state: HeadBobState.WaitingAfterAttack, player: Player) {
        val hit = currentHeadBobBaseHit(state.basePos)
        if (hit == null) {
            cancelHeadBobState("visible-crosshair-left-base-during-boundary-wait")
            return
        }

        if (!ActionTickBoundary.hasPassed(state.actionBoundarySerial)) {
            warnHeadBobBoundaryWait(state)
            return
        }

        if (pendingAntiWeaknessRestoration != null ||
            antiWeaknessRestoredAtSerial == ActionTickBoundary.currentSerial()) {
            return
        }

        logHeadBobState(state, "post-attack boundary passed boundarySerial=${state.actionBoundarySerial}")
        beginHeadBobBaseAim(
            basePos = state.basePos,
            crystalId = state.crystalId,
            hit = hit,
            diagnostic = state.diagnostic,
            crystalSequenceDiagnostic = state.crystalSequenceDiagnostic,
            actionBoundarySerial = state.actionBoundarySerial,
            crystalSpawnLatencyTicks = state.crystalSpawnLatencyTicks,
            player = player,
            message = "base rotation requested in same RotationUpdateEvent",
        )
    }

    private fun planHeadBobBase(state: HeadBobState.AimingBase, player: Player) {
        clearMissedHeadBobScheduledActions(state)

        val hit = currentHeadBobBaseHit(state.basePos)
        if (hit == null) {
            cancelHeadBobState("visible-crosshair-left-base-during-base-aim")
            return
        }

        if (pendingAntiWeaknessRestoration != null ||
            state.actionBoundarySerial != null && !ActionTickBoundary.hasPassed(state.actionBoundarySerial) ||
            dontPlaceThisTick) {
            return
        }

        // See the matching Crystal guard above: this state already owns a next-tick pre-move
        // placement whose server-facing rotation was confirmed in the prior movement phase.
        if (state.preMoveActionScheduled) {
            return
        }

        requestHeadBobBaseRotation(state, hit, player)
        warnHeadBobRotationWait(state)
    }

    private fun beginHeadBobBaseAim(
        basePos: BlockPos,
        crystalId: Int,
        hit: BlockHitResult,
        diagnostic: HeadBobDiagnostic,
        crystalSequenceDiagnostic: ActionTickBoundary.CrystalSequenceDiagnostic?,
        actionBoundarySerial: Long?,
        crystalSpawnLatencyTicks: Int,
        player: Player,
        message: String,
    ) {
        val baseState = HeadBobState.AimingBase(
            generation = nextHeadBobGeneration(),
            basePos = basePos,
            crystalId = crystalId,
            originalVisibleHit = hit,
            diagnostic = diagnostic,
            crystalSequenceDiagnostic = crystalSequenceDiagnostic,
            actionBoundarySerial = actionBoundarySerial,
            baseAimVariant = nextHeadBobBaseAimVariant(),
            phaseEnteredTick = player.tickCount,
            phaseEnteredSerial = ActionTickBoundary.currentSerial(),
            crystalSpawnLatencyTicks = crystalSpawnLatencyTicks,
        )
        headBobState = baseState
        requestHeadBobBaseRotation(baseState, hit, player)
        logHeadBobState(baseState, message)
    }

    private fun requestHeadBobCrystalRotation(
        state: HeadBobState.AimingCrystal,
        crystal: EndCrystal,
        aim: HeadBobCrystalAim,
        player: Player,
    ) {
        state.targetRotation = aim.rotation
        state.targetDistance = player.eyePos.distanceTo(aim.point)
        if (state.rotationRequestedTick == Int.MIN_VALUE) {
            state.rotationRequestedTick = player.tickCount
            state.rotationRequestedSerial = ActionTickBoundary.currentSerial()
        }
        requestSilentHeadBobRotation(
            rotation = aim.rotation,
            entity = crystal,
            whenReached = RestrictedSingleUseAction(
                canExecute = {
                    canScheduleHeadBobCrystalRotationConfirmation(state.generation, state.basePos, state.crystalId)
                },
                action = {
                    scheduleHeadBobCrystalRotationConfirmation(state.generation, state.basePos, state.crystalId)
                }
            ),
        )
        if (state.rotationRequestedTick == state.phaseEnteredTick) {
            logHeadBobState(state, "Crystal acquired and rotation requested in RotationUpdateEvent")
        }
    }

    private fun requestHeadBobBaseRotation(
        state: HeadBobState.AimingBase,
        hit: BlockHitResult,
        player: Player,
    ) {
        if (state.targetRotation == null) {
            val aim = findHeadBobBaseAim(player, hit, state.baseAimVariant) ?: run {
                cancelHeadBobState("base-face-no-longer-valid-for-rotation")
                return
            }
            state.targetRotation = aim.rotation
            state.targetDistance = player.eyePos.distanceTo(aim.point)
        }
        val rotation = state.targetRotation ?: return
        if (state.rotationRequestedTick == Int.MIN_VALUE) {
            state.rotationRequestedTick = player.tickCount
            state.rotationRequestedSerial = ActionTickBoundary.currentSerial()
        }
        requestSilentHeadBobRotation(
            rotation = rotation,
            whenReached = RestrictedSingleUseAction(
                canExecute = {
                    canScheduleHeadBobBaseRotationConfirmation(state.generation, state.basePos, state.crystalId)
                },
                action = {
                    scheduleHeadBobBaseRotationConfirmation(state.generation, state.basePos, state.crystalId)
                }
            ),
        )
    }

    /**
     * A direct one-tick SILENT plan is intentional: there is no user-facing smoothing option and
     * `actualServerRotation` remains the only action gate. If another module wins the request, the
     * state simply retries instead of acting on a desired-but-unsent rotation.
     */
    private fun requestSilentHeadBobRotation(
        rotation: Rotation,
        entity: Entity? = null,
        whenReached: RestrictedSingleUseAction,
    ) {
        RotationManager.setRotationTarget(
            RotationTarget(
                rotation = rotation,
                entity = entity,
                ticksUntilReset = 1,
                resetThreshold = 180f,
                considerInventory = false,
                movementCorrection = MovementCorrection.SILENT,
                whenReached = whenReached,
            ),
            priority = Priority.IMPORTANT_FOR_USAGE_3,
            provider = this,
        )
    }

    private fun canScheduleHeadBobCrystalRotationConfirmation(
        generation: Long,
        basePos: BlockPos,
        crystalId: Int,
    ): Boolean {
        val state = matchingHeadBobCrystalState(generation, basePos, crystalId) ?: return false
        val player = mc.player ?: return false
        val crystal = findHeadBobCrystalById(basePos, crystalId) ?: return false
        return !state.postMoveConfirmationScheduled && !state.preMoveActionScheduled && isHeadBobLifecycleValid(player) &&
            currentHeadBobBaseHit(basePos) != null && !dontBreakThisTick &&
            isCurrentRotationFacingHeadBobCrystal(player, crystal)
    }

    private fun canScheduleHeadBobBaseRotationConfirmation(
        generation: Long,
        basePos: BlockPos,
        crystalId: Int,
    ): Boolean {
        val state = matchingHeadBobBaseState(generation, basePos, crystalId) ?: return false
        val player = mc.player ?: return false
        val hit = currentHeadBobBaseHit(basePos) ?: return false
        return !state.postMoveConfirmationScheduled && !state.preMoveActionScheduled && isHeadBobLifecycleValid(player) &&
            pendingAntiWeaknessRestoration == null && !dontPlaceThisTick &&
            (state.actionBoundarySerial == null || ActionTickBoundary.hasPassed(state.actionBoundarySerial)) &&
            isCurrentRotationFacingHeadBobBase(player, hit)
    }

    /**
     * The post-move hook is deliberately confirmation-only. On current Grim, sending ATTACK or
     * USE_ITEM_ON after a normal movement packet but before CLIENT_TICK_END violates both Post
     * and PacketOrderO. The verified action is therefore scheduled for the following pre-move
     * GameTick phase.
     */
    private fun scheduleHeadBobCrystalRotationConfirmation(generation: Long, basePos: BlockPos, crystalId: Int) {
        val state = matchingHeadBobCrystalState(generation, basePos, crystalId) ?: return
        if (state.postMoveConfirmationScheduled || state.preMoveActionScheduled) {
            return
        }

        state.postMoveConfirmationScheduled = true
        state.postMoveConfirmationScheduledSerial = ActionTickBoundary.currentSerial()
        PostRotationExecutor.addTask(
            module = this,
            postMove = true,
            task = { confirmHeadBobCrystalRotationPostMove(generation, basePos, crystalId) },
            priority = true,
        )
    }

    private fun scheduleHeadBobBaseRotationConfirmation(generation: Long, basePos: BlockPos, crystalId: Int) {
        val state = matchingHeadBobBaseState(generation, basePos, crystalId) ?: return
        if (state.postMoveConfirmationScheduled || state.preMoveActionScheduled) {
            return
        }

        state.postMoveConfirmationScheduled = true
        state.postMoveConfirmationScheduledSerial = ActionTickBoundary.currentSerial()
        PostRotationExecutor.addTask(
            module = this,
            postMove = true,
            task = { confirmHeadBobBaseRotationPostMove(generation, basePos, crystalId) },
            priority = true,
        )
    }

    private fun confirmHeadBobCrystalRotationPostMove(generation: Long, basePos: BlockPos, crystalId: Int) {
        val state = matchingHeadBobCrystalState(generation, basePos, crystalId) ?: return
        val player = mc.player ?: return
        state.postMoveConfirmationScheduled = false
        updateActionClockSnapshot(player.tickCount)

        if (!isHeadBobLifecycleValid(player)) {
            cancelHeadBobState("crystal-post-move-confirmation-lifecycle-invalid")
            return
        }

        if (shouldPauseHeadBobAction(player) || dontBreakThisTick) {
            return
        }

        val hit = currentHeadBobBaseHit(basePos)
        if (hit == null) {
            cancelHeadBobState("visible-crosshair-left-base-before-crystal-post-move-confirmation")
            return
        }

        val crystal = findHeadBobCrystalById(basePos, crystalId)
        if (crystal == null) {
            cancelHeadBobState("stored-crystal-missing-before-crystal-post-move-confirmation")
            return
        }

        // currentRotation only proves RotationManager reached its desired target. This post-move
        // confirmation records that the normal movement packet carried a usable server rotation;
        // the interaction itself must wait for the next pre-move tick to remain Grim-safe.
        if (!isActualServerFacingHeadBobCrystal(player, crystal)) {
            logHeadBobState(state, "post-move Crystal rotation packet not server-valid; retrying")
            return
        }

        state.rotationPacketTick = player.tickCount
        state.rotationPacketSerial = ActionTickBoundary.currentSerial()

        if (!state.serverFacingLogged) {
            state.serverFacingLogged = true
            logHeadBobState(state, "Crystal rotation packet verified; scheduling next pre-move ATTACK")
        }

        scheduleHeadBobCrystalPreMoveAction(generation, basePos, crystalId)
    }

    private fun scheduleHeadBobCrystalPreMoveAction(generation: Long, basePos: BlockPos, crystalId: Int) {
        val state = matchingHeadBobCrystalState(generation, basePos, crystalId) ?: return
        if (state.preMoveActionScheduled) {
            return
        }

        state.preMoveActionScheduled = true
        // CLIENT_TICK_END increments before the next GameTick. Retain the due serial so the
        // ordinary RotationUpdateEvent does not mistake a still-pending same-tick executor task
        // for a stale callback.
        state.preMoveActionScheduledSerial = ActionTickBoundary.currentSerial() + 1
        PostRotationExecutor.addTask(
            module = this,
            postMove = false,
            task = { executeHeadBobCrystalPreMoveAction(generation, basePos, crystalId) },
            // This is enqueued from the current priority post-move callback. A new priority
            // action would be cleared by that callback's executor loop; normal tasks run at the
            // next GameTick FIRST handler, still before player movement.
            priority = false,
        )
    }

    private fun executeHeadBobCrystalPreMoveAction(generation: Long, basePos: BlockPos, crystalId: Int) {
        val state = matchingHeadBobCrystalState(generation, basePos, crystalId) ?: return
        val player = mc.player ?: return
        state.preMoveActionScheduled = false
        updateActionClockSnapshot(player.tickCount)

        if (!isHeadBobLifecycleValid(player)) {
            cancelHeadBobState("crystal-pre-move-lifecycle-invalid")
            return
        }

        if (shouldPauseHeadBobAction(player) || dontBreakThisTick) {
            return
        }

        val hit = currentHeadBobBaseHit(basePos)
        if (hit == null) {
            cancelHeadBobState("visible-crosshair-left-base-before-crystal-pre-move-action")
            return
        }

        val crystal = findHeadBobCrystalById(basePos, crystalId)
        if (crystal == null) {
            cancelHeadBobState("stored-crystal-missing-before-crystal-pre-move-action")
            return
        }

        if (ModuleCrystalOptimizer.isAttackInhibited(crystal)) {
            beginHeadBobBaseAim(
                basePos = basePos,
                crystalId = crystalId,
                hit = hit,
                diagnostic = state.diagnostic,
                crystalSequenceDiagnostic = null,
                actionBoundarySerial = null,
                crystalSpawnLatencyTicks = state.crystalSpawnLatencyTicks,
                player = player,
                message = "Crystal became inhibited before pre-move ATTACK; base rotation deferred",
            )
            return
        }

        if (!canPrepareHeadBobAttack(player)) {
            resetHeadBobState("missing-antiweakness-tool-before-crystal-pre-move-action")
            return
        }

        if (!isActualServerFacingHeadBobCrystal(player, crystal)) {
            logHeadBobState(state, "pre-move Crystal action lost server rotation proof; retrying")
            return
        }

        state.preMoveActionTick = player.tickCount
        state.preMoveActionSerial = ActionTickBoundary.currentSerial()

        val outcome = handleEntityTarget(
            dontBreak = false,
            explicitTarget = crystal,
            targetOrigin = EntityTargetOrigin.HEAD_BOB,
        )
        when (outcome.result) {
            EntityActionResult.ATTACK_ATTEMPTED -> {
                val sequenceDiagnostic = outcome.diagnostics
                val boundarySerial = armEntityActionBoundary(outcome)
                if (sequenceDiagnostic == null || boundarySerial == null) {
                    cancelHeadBobState("attack-without-boundary-diagnostics")
                    return
                }

                headBobActionExecutedAtSerial = ActionTickBoundary.currentSerial()
                val waitingState = HeadBobState.WaitingAfterAttack(
                    generation = nextHeadBobGeneration(),
                    basePos = basePos,
                    crystalId = crystal.id,
                    originalVisibleHit = hit,
                    actionBoundarySerial = boundarySerial,
                    diagnostic = state.diagnostic,
                    crystalSequenceDiagnostic = sequenceDiagnostic,
                    crystalRotation = state.targetRotation,
                    crystalDistance = state.targetDistance,
                    phaseEnteredTick = player.tickCount,
                    phaseEnteredSerial = ActionTickBoundary.currentSerial(),
                    rotationRequestedTick = state.rotationRequestedTick,
                    rotationRequestedSerial = state.rotationRequestedSerial,
                    rotationPacketTick = state.rotationPacketTick,
                    rotationPacketSerial = state.rotationPacketSerial,
                    preMoveActionTick = state.preMoveActionTick,
                    preMoveActionSerial = state.preMoveActionSerial,
                    crystalSpawnLatencyTicks = state.crystalSpawnLatencyTicks,
                )
                headBobState = waitingState
                logHeadBobState(
                    waitingState,
                    "Crystal pre-move ATTACK executed; post-attack boundary armed boundarySerial=$boundarySerial"
                )
            }

            EntityActionResult.INHIBITED_WITHOUT_PACKET -> beginHeadBobBaseAim(
                basePos = basePos,
                crystalId = crystalId,
                hit = hit,
                diagnostic = state.diagnostic,
                crystalSequenceDiagnostic = null,
                actionBoundarySerial = null,
                crystalSpawnLatencyTicks = state.crystalSpawnLatencyTicks,
                player = player,
                message = "Crystal became inhibited during pre-move ATTACK; base rotation deferred",
            )

            EntityActionResult.NO_TARGET,
            EntityActionResult.NO_ACTION -> resetHeadBobState("entity-attack-not-attempted-pre-move")
        }
    }

    private fun confirmHeadBobBaseRotationPostMove(generation: Long, basePos: BlockPos, crystalId: Int) {
        val state = matchingHeadBobBaseState(generation, basePos, crystalId) ?: return
        val player = mc.player ?: return
        state.postMoveConfirmationScheduled = false
        updateActionClockSnapshot(player.tickCount)

        if (!isHeadBobLifecycleValid(player)) {
            cancelHeadBobState("base-post-move-confirmation-lifecycle-invalid")
            return
        }

        if (shouldPauseHeadBobAction(player) || pendingAntiWeaknessRestoration != null || dontPlaceThisTick ||
            state.actionBoundarySerial != null && !ActionTickBoundary.hasPassed(state.actionBoundarySerial)) {
            return
        }

        val hit = currentHeadBobBaseHit(basePos)
        if (hit == null) {
            cancelHeadBobState("visible-crosshair-left-base-before-base-post-move-confirmation")
            return
        }

        if (!isActualServerFacingHeadBobBase(player, hit)) {
            logHeadBobState(state, "post-move base rotation packet not server-valid; retrying")
            return
        }

        state.rotationPacketTick = player.tickCount
        state.rotationPacketSerial = ActionTickBoundary.currentSerial()

        if (!state.serverFacingLogged) {
            state.serverFacingLogged = true
            logHeadBobState(state, "base rotation packet verified; scheduling next pre-move PLACE")
        }

        scheduleHeadBobBasePreMoveAction(generation, basePos, crystalId)
    }

    private fun scheduleHeadBobBasePreMoveAction(generation: Long, basePos: BlockPos, crystalId: Int) {
        val state = matchingHeadBobBaseState(generation, basePos, crystalId) ?: return
        if (state.preMoveActionScheduled) {
            return
        }

        state.preMoveActionScheduled = true
        state.preMoveActionScheduledSerial = ActionTickBoundary.currentSerial() + 1
        PostRotationExecutor.addTask(
            module = this,
            postMove = false,
            task = { executeHeadBobBasePreMoveAction(generation, basePos, crystalId) },
            priority = false,
        )
    }

    private fun executeHeadBobBasePreMoveAction(generation: Long, basePos: BlockPos, crystalId: Int) {
        val state = matchingHeadBobBaseState(generation, basePos, crystalId) ?: return
        val player = mc.player ?: return
        state.preMoveActionScheduled = false
        updateActionClockSnapshot(player.tickCount)

        if (!isHeadBobLifecycleValid(player)) {
            cancelHeadBobState("base-pre-move-lifecycle-invalid")
            return
        }

        if (shouldPauseHeadBobAction(player) || pendingAntiWeaknessRestoration != null || dontPlaceThisTick ||
            state.actionBoundarySerial != null && !ActionTickBoundary.hasPassed(state.actionBoundarySerial)) {
            return
        }

        val hit = currentHeadBobBaseHit(basePos)
        if (hit == null) {
            cancelHeadBobState("visible-crosshair-left-base-before-base-pre-move-action")
            return
        }

        if (!isActualServerFacingHeadBobBase(player, hit)) {
            logHeadBobState(state, "pre-move base action lost server rotation proof; retrying")
            return
        }

        state.preMoveActionTick = player.tickCount
        state.preMoveActionSerial = ActionTickBoundary.currentSerial()

        val placed = handleBlockTarget(
            dontPlace = false,
            diagnostics = state.crystalSequenceDiagnostic,
            explicitHit = hit,
        )
        if (placed) {
            headBobActionExecutedAtSerial = ActionTickBoundary.currentSerial()
            recordHeadBobCrystalPlacement(basePos, player)
            logHeadBobState(state, "base pre-move PLACE executed; awaiting server/network Crystal spawn")
            resetHeadBobState("placement-attempt-complete")
        } else {
            // A just-attacked crystal may remain client-side until its destroy/spawn networking
            // catches up. Retain the phase and retry; the only fixed wait is the verified
            // rotation-packet-to-pre-move-action boundary required by Grim packet ordering.
            logHeadBobState(state, "base pre-move PLACE unavailable; retaining base phase")
        }
    }

    private fun matchingHeadBobCrystalState(
        generation: Long,
        basePos: BlockPos,
        crystalId: Int,
    ): HeadBobState.AimingCrystal? = (headBobState as? HeadBobState.AimingCrystal)?.takeIf {
        it.generation == generation && it.basePos == basePos && it.crystalId == crystalId &&
            headBobGeneration == generation
    }

    private fun matchingHeadBobBaseState(
        generation: Long,
        basePos: BlockPos,
        crystalId: Int,
    ): HeadBobState.AimingBase? = (headBobState as? HeadBobState.AimingBase)?.takeIf {
        it.generation == generation && it.basePos == basePos && it.crystalId == crystalId &&
            headBobGeneration == generation
    }

    private fun clearMissedHeadBobScheduledActions(state: HeadBobState.AimingCrystal) {
        val currentSerial = ActionTickBoundary.currentSerial()
        if (state.postMoveConfirmationScheduled && state.postMoveConfirmationScheduledSerial < currentSerial) {
            state.postMoveConfirmationScheduled = false
            logHeadBobState(state, "queued Crystal post-move rotation confirmation did not execute; retrying rotation")
        }
        if (state.preMoveActionScheduled && state.preMoveActionScheduledSerial < currentSerial) {
            state.preMoveActionScheduled = false
            logHeadBobState(state, "queued Crystal pre-move action did not execute; retrying rotation")
        }
    }

    private fun clearMissedHeadBobScheduledActions(state: HeadBobState.AimingBase) {
        val currentSerial = ActionTickBoundary.currentSerial()
        if (state.postMoveConfirmationScheduled && state.postMoveConfirmationScheduledSerial < currentSerial) {
            state.postMoveConfirmationScheduled = false
            logHeadBobState(state, "queued base post-move rotation confirmation did not execute; retrying rotation")
        }
        if (state.preMoveActionScheduled && state.preMoveActionScheduledSerial < currentSerial) {
            state.preMoveActionScheduled = false
            logHeadBobState(state, "queued base pre-move action did not execute; retrying rotation")
        }
    }

    private fun isHeadBobLifecycleValid(player: Player): Boolean =
        running && headBob && mc.player === player && mc.level != null && observedPlayer === player &&
            observedLevel === mc.level && mc.screen == null && isActivationDown()

    private fun shouldPauseHeadBobAction(player: Player): Boolean =
        stopOnKill && isDeadPlayerNearby(player) || player.isUsingItem || damageTick && shouldWaitDamageTick(player)

    private fun canPrepareHeadBobAttack(player: Player): Boolean =
        !antiWeakness || !cantBreakCrystal(player) || findAntiWeaknessTool(player) != null

    private fun nextHeadBobGeneration(): Long = ++headBobGeneration

    private fun nextHeadBobBaseAimVariant(): Int = headBobBaseAimVariant++

    private fun hasHeadBobRotationConflict(): Boolean =
        RotationManager.activeRotationTarget?.provider?.let { it !== this } == true

    private fun warnHeadBobRotationWait(state: HeadBobState.AimingCrystal) {
        val currentSerial = ActionTickBoundary.currentSerial()
        if (ticksSinceHeadBobSerial(state.phaseEnteredSerial) > 1 && !state.postMoveConfirmationScheduled &&
            !state.preMoveActionScheduled &&
            !hasHeadBobRotationConflict() && state.lastRotationWaitWarningSerial != currentSerial) {
            state.lastRotationWaitWarningSerial = currentSerial
            logHeadBobState(
                state,
                "AimingCrystal waited more than one full client tick for rotation confirmation/action scheduling; " +
                    "packet queue or fake lag may be involved",
                warning = true,
            )
        }
    }

    private fun warnHeadBobRotationWait(state: HeadBobState.AimingBase) {
        val currentSerial = ActionTickBoundary.currentSerial()
        if (ticksSinceHeadBobSerial(state.phaseEnteredSerial) > 1 && !state.postMoveConfirmationScheduled &&
            !state.preMoveActionScheduled &&
            !hasHeadBobRotationConflict() && state.lastRotationWaitWarningSerial != currentSerial) {
            state.lastRotationWaitWarningSerial = currentSerial
            logHeadBobState(
                state,
                "AimingBase waited more than one full client tick for rotation confirmation/action scheduling; " +
                    "packet queue or fake lag may be involved",
                warning = true,
            )
        }
    }

    private fun warnHeadBobBoundaryWait(state: HeadBobState.WaitingAfterAttack) {
        val currentSerial = ActionTickBoundary.currentSerial()
        if (ticksSinceHeadBobSerial(state.actionBoundarySerial) > 1 &&
            state.lastBoundaryWaitWarningSerial != currentSerial) {
            state.lastBoundaryWaitWarningSerial = currentSerial
            logHeadBobState(
                state,
                "WaitingAfterAttack exceeded one full client tick; awaiting real CLIENT_TICK_END boundary",
                warning = true,
            )
        }
    }

    private fun cancelHeadBobState(reason: String) = finishHeadBobState(reason, cancelled = true)

    private fun resetHeadBobState(reason: String) = finishHeadBobState(reason, cancelled = false)

    private fun finishHeadBobState(reason: String, cancelled: Boolean) {
        val state = headBobState
        if (state is HeadBobState.Idle) {
            return
        }

        // Every queued RotationTarget/PostRotationExecutor action captures this generation.
        // Bump it before exposing Idle so callbacks from a replaced phase cannot emit packets.
        headBobGeneration++

        if (cancelled) {
            val (basePos, crystalId, diagnostic) = when (state) {
                is HeadBobState.AimingCrystal -> Triple(state.basePos, state.crystalId, state.diagnostic)
                is HeadBobState.WaitingAfterAttack -> Triple(state.basePos, state.crystalId, state.diagnostic)
                is HeadBobState.AimingBase -> Triple(state.basePos, state.crystalId, state.diagnostic)
                HeadBobState.Idle -> return
            }
            pendingHeadBobCancellation = PendingHeadBobCancellation(
                safetyBoundarySerial = ActionTickBoundary.currentSerial(),
                diagnostic = diagnostic,
                basePos = basePos,
                crystalId = crystalId,
            )
        }

        logHeadBobState(state, "state ${if (cancelled) "cancelled" else "reset"} reason=$reason")
        headBobState = HeadBobState.Idle
    }

    private fun skipCancelledHeadBobActionPhase(): Boolean {
        val pending = pendingHeadBobCancellation ?: return false
        if (!ActionTickBoundary.hasPassed(pending.safetyBoundarySerial)) {
            return true
        }

        pendingHeadBobCancellation = null
        logHeadBob(
            pending.diagnostic,
            pending.basePos,
            pending.crystalId,
            "CancelledBoundary",
            null,
            null,
            "state reset reason=cancelled-silent-rotation-reset-boundary-passed " +
                "boundarySerial=${pending.safetyBoundarySerial}"
        )
        return true
    }

    private fun claimHeadBobDiagnostic(): HeadBobDiagnostic {
        val enabled = headBobDiagnosticCyclesRemaining > 0 && headBobDiagnosticMessagesRemaining > 0
        if (enabled) {
            headBobDiagnosticCyclesRemaining--
        }
        return HeadBobDiagnostic(enabled)
    }

    private fun resetHeadBobDiagnosticBudget() {
        headBobDiagnosticCyclesRemaining = MAX_HEAD_BOB_DIAGNOSTIC_CYCLES
        headBobDiagnosticMessagesRemaining = MAX_HEAD_BOB_DIAGNOSTIC_MESSAGES
    }

    private data class HeadBobTiming(
        val phaseEnteredTick: Int = -1,
        val phaseEnteredSerial: Long = -1,
        val rotationRequestedTick: Int = -1,
        val rotationRequestedSerial: Long = -1,
        val rotationPacketTick: Int = -1,
        val rotationPacketSerial: Long = -1,
        val preMoveActionTick: Int = -1,
        val preMoveActionSerial: Long = -1,
        val boundarySerial: Long = -1,
        val ticksWaitingForRotation: Int = -1,
        val ticksWaitingForPreMoveAction: Int = -1,
        val ticksWaitingForBoundary: Int = -1,
        val serverNetworkCrystalSpawnLatencyTicks: Int = -1,
    )

    /**
     * Player.tickCount advances during the same client tick between RotationUpdateEvent and the
     * movement post hook. ActionTickBoundary's serial is the authoritative full-client-tick clock
     * for the HeadBob timing diagnostics.
     */
    private fun ticksSinceHeadBobSerial(serial: Long): Int =
        if (serial == Long.MIN_VALUE) -1 else
            (ActionTickBoundary.currentSerial() - serial).coerceAtLeast(0L).coerceAtMost(Int.MAX_VALUE.toLong()).toInt()

    private fun elapsedHeadBobSerial(start: Long, end: Long = ActionTickBoundary.currentSerial()): Int =
        if (start == Long.MIN_VALUE || end == Long.MIN_VALUE) -1 else
            (end - start).coerceAtLeast(0L).coerceAtMost(Int.MAX_VALUE.toLong()).toInt()

    private fun recordHeadBobCrystalPlacement(basePos: BlockPos, player: Player) {
        pendingHeadBobCrystalSpawn = PendingHeadBobCrystalSpawn(
            basePos = basePos,
            placementTick = player.tickCount,
            placementSerial = ActionTickBoundary.currentSerial(),
        )
    }

    private fun consumeHeadBobCrystalSpawnLatency(basePos: BlockPos): Int {
        val pending = pendingHeadBobCrystalSpawn ?: return -1
        if (pending.basePos != basePos) {
            return -1
        }

        pendingHeadBobCrystalSpawn = null
        return elapsedHeadBobSerial(pending.placementSerial)
    }

    private fun timingForHeadBobState(state: HeadBobState): HeadBobTiming {
        return when (state) {
            HeadBobState.Idle -> HeadBobTiming()
            is HeadBobState.AimingCrystal -> HeadBobTiming(
                phaseEnteredTick = state.phaseEnteredTick,
                phaseEnteredSerial = state.phaseEnteredSerial,
                rotationRequestedTick = state.rotationRequestedTick.coerceAtLeast(-1),
                rotationRequestedSerial = state.rotationRequestedSerial.coerceAtLeast(-1),
                rotationPacketTick = state.rotationPacketTick.coerceAtLeast(-1),
                rotationPacketSerial = state.rotationPacketSerial.coerceAtLeast(-1),
                preMoveActionTick = state.preMoveActionTick.coerceAtLeast(-1),
                preMoveActionSerial = state.preMoveActionSerial.coerceAtLeast(-1),
                ticksWaitingForRotation = elapsedHeadBobSerial(
                    state.rotationRequestedSerial,
                    state.rotationPacketSerial.takeIf { it != Long.MIN_VALUE } ?: ActionTickBoundary.currentSerial(),
                ),
                ticksWaitingForPreMoveAction = state.rotationPacketSerial.takeIf { it != Long.MIN_VALUE }?.let {
                    elapsedHeadBobSerial(it, state.preMoveActionSerial.takeIf { serial -> serial != Long.MIN_VALUE }
                        ?: ActionTickBoundary.currentSerial())
                } ?: -1,
                serverNetworkCrystalSpawnLatencyTicks = state.crystalSpawnLatencyTicks,
            )

            is HeadBobState.WaitingAfterAttack -> HeadBobTiming(
                phaseEnteredTick = state.phaseEnteredTick,
                phaseEnteredSerial = state.phaseEnteredSerial,
                rotationRequestedTick = state.rotationRequestedTick.coerceAtLeast(-1),
                rotationRequestedSerial = state.rotationRequestedSerial.coerceAtLeast(-1),
                rotationPacketTick = state.rotationPacketTick.coerceAtLeast(-1),
                rotationPacketSerial = state.rotationPacketSerial.coerceAtLeast(-1),
                preMoveActionTick = state.preMoveActionTick.coerceAtLeast(-1),
                preMoveActionSerial = state.preMoveActionSerial.coerceAtLeast(-1),
                boundarySerial = state.actionBoundarySerial,
                ticksWaitingForRotation = elapsedHeadBobSerial(state.rotationRequestedSerial, state.rotationPacketSerial),
                ticksWaitingForPreMoveAction = elapsedHeadBobSerial(state.rotationPacketSerial, state.preMoveActionSerial),
                ticksWaitingForBoundary = ticksSinceHeadBobSerial(state.actionBoundarySerial),
                serverNetworkCrystalSpawnLatencyTicks = state.crystalSpawnLatencyTicks,
            )

            is HeadBobState.AimingBase -> HeadBobTiming(
                phaseEnteredTick = state.phaseEnteredTick,
                phaseEnteredSerial = state.phaseEnteredSerial,
                rotationRequestedTick = state.rotationRequestedTick.coerceAtLeast(-1),
                rotationRequestedSerial = state.rotationRequestedSerial.coerceAtLeast(-1),
                rotationPacketTick = state.rotationPacketTick.coerceAtLeast(-1),
                rotationPacketSerial = state.rotationPacketSerial.coerceAtLeast(-1),
                preMoveActionTick = state.preMoveActionTick.coerceAtLeast(-1),
                preMoveActionSerial = state.preMoveActionSerial.coerceAtLeast(-1),
                boundarySerial = state.actionBoundarySerial ?: -1,
                ticksWaitingForRotation = elapsedHeadBobSerial(
                    state.rotationRequestedSerial,
                    state.rotationPacketSerial.takeIf { it != Long.MIN_VALUE } ?: ActionTickBoundary.currentSerial(),
                ),
                ticksWaitingForPreMoveAction = state.rotationPacketSerial.takeIf { it != Long.MIN_VALUE }?.let {
                    elapsedHeadBobSerial(it, state.preMoveActionSerial.takeIf { serial -> serial != Long.MIN_VALUE }
                        ?: ActionTickBoundary.currentSerial())
                } ?: -1,
                ticksWaitingForBoundary = state.actionBoundarySerial?.let(::ticksSinceHeadBobSerial) ?: -1,
                serverNetworkCrystalSpawnLatencyTicks = state.crystalSpawnLatencyTicks,
            )
        }
    }

    private fun logHeadBobState(state: HeadBobState, message: String, warning: Boolean = false) {
        when (state) {
            HeadBobState.Idle -> Unit
            is HeadBobState.AimingCrystal -> logHeadBob(
                state.diagnostic,
                state.basePos,
                state.crystalId,
                "AimingCrystal",
                state.targetRotation,
                state.targetDistance,
                message,
                warning,
                timingForHeadBobState(state),
            )

            is HeadBobState.WaitingAfterAttack -> logHeadBob(
                state.diagnostic,
                state.basePos,
                state.crystalId,
                "WaitingAfterAttack",
                state.crystalRotation,
                state.crystalDistance,
                message,
                warning,
                timingForHeadBobState(state),
            )

            is HeadBobState.AimingBase -> logHeadBob(
                state.diagnostic,
                state.basePos,
                state.crystalId,
                "AimingBase",
                state.targetRotation,
                state.targetDistance,
                message,
                warning,
                timingForHeadBobState(state),
            )
        }
    }

    private fun logHeadBob(
        diagnostic: HeadBobDiagnostic,
        basePos: BlockPos,
        crystalId: Int,
        state: String,
        targetRotation: Rotation?,
        distance: Double?,
        message: String,
        warning: Boolean = false,
        timing: HeadBobTiming = HeadBobTiming(),
    ) {
        if (!diagnostic.enabled || headBobDiagnosticMessagesRemaining <= 0) {
            return
        }

        headBobDiagnosticMessagesRemaining--
        val visible = when (val target = mc.crosshairTarget) {
            null -> "NONE"
            is BlockHitResult -> "BLOCK@${target.blockPosition()}/${target.direction}"
            is EntityHitResult -> "ENTITY@${target.entity.id}"
            else -> "${target.type}@${target.location}"
        }
        val visibleCamera = currentHeadBobBaseHit()?.let { "BLOCK@${it.blockPosition()}/${it.direction}" } ?: "NONE"
        val serverRotation = RotationManager.actualServerRotation
        val targetYaw = targetRotation?.yaw ?: Float.NaN
        val targetPitch = targetRotation?.pitch ?: Float.NaN
        val targetDistance = distance ?: Double.NaN
        val formatted = "$HEAD_BOB_DIAGNOSTIC_PREFIX tick=${mc.player?.tickCount ?: -1} " +
            "serial=${ActionTickBoundary.currentSerial()} base=$basePos crystalId=$crystalId visible=$visible " +
            "visibleCamera=$visibleCamera serverYaw=${serverRotation.yaw} serverPitch=${serverRotation.pitch} " +
            "targetYaw=$targetYaw targetPitch=$targetPitch distance=$targetDistance " +
            "phaseEnteredTick=${timing.phaseEnteredTick} phaseEnteredSerial=${timing.phaseEnteredSerial} " +
            "rotationRequestedTick=${timing.rotationRequestedTick} rotationRequestedSerial=${timing.rotationRequestedSerial} " +
            "rotationPacketTick=${timing.rotationPacketTick} rotationPacketSerial=${timing.rotationPacketSerial} " +
            "preMoveActionTick=${timing.preMoveActionTick} preMoveActionSerial=${timing.preMoveActionSerial} " +
            "boundarySerial=${timing.boundarySerial} " +
            "ticksWaitingForRotation=${timing.ticksWaitingForRotation} " +
            "ticksWaitingForPreMoveAction=${timing.ticksWaitingForPreMoveAction} " +
            "ticksWaitingForBoundary=${timing.ticksWaitingForBoundary} " +
            "serverNetworkCrystalSpawnLatencyTicks=${timing.serverNetworkCrystalSpawnLatencyTicks} " +
            "selectedSlot=${mc.player?.inventory?.selectedSlot ?: -1} breakClock=$breakClock " +
            "placeClock=$placeClock state=$state $message"
        if (warning) {
            logger.warn(formatted)
        } else {
            logger.info(formatted)
        }
    }

    /**
     * @return true when a just-emitted base placement still owns this action tick. The explicit
     * boundary state is intentionally stronger than changing the source's two independent `if`s:
     * integrations may invoke [runCrystalTick] more than once before Minecraft sends tick-end.
     */
    private fun handleCrystalBase(): Boolean {
        if (processPendingCrystalBaseBoundary()) {
            return true
        }

        val hit = getLookedBlock() ?: return false
        val player = mc.player ?: return false

        if (baseActive || !BaseSetting.checkPlace || canPlaceAgainst(hit)) {
            if (baseActive || isValidBaseStartItem(player.mainHandItem)) {
                if (!baseActive && BaseSetting.swordSwap && isCrystalBase(hit.blockPosition())) {
                    baseCrystalling = true
                }

                baseActive = true

                if (!baseCrystalling && handleBasePlacement(hit)) {
                    return true
                }

                if (baseCrystalling) {
                    handleBaseCrystalSelection()
                }
            }
        }

        return false
    }

    /** Keeps CrystalBase's accepted post-placement packet boundary ahead of HeadBob. */
    private fun processPendingCrystalBaseBoundary(): Boolean {
        if (!waitingForBasePlaceBoundary) {
            return false
        }

        if (!ActionTickBoundary.hasPassed(basePlaceActionSerial)) {
            if (baseBoundaryWaitLoggedSerial != ActionTickBoundary.currentSerial()) {
                baseBoundaryWaitLoggedSerial = ActionTickBoundary.currentSerial()
                ActionTickBoundary.diagnostic(
                    baseDiagnosticsEnabled,
                    "CrystalBase waiting post-place boundary serial=$basePlaceActionSerial"
                )
            }
            return true
        }

        waitingForBasePlaceBoundary = false
        ActionTickBoundary.diagnostic(
            baseDiagnosticsEnabled,
            "CrystalBase boundary passed serial=${ActionTickBoundary.currentSerial()} " +
                "postPlaceSerial=$basePlaceActionSerial"
        )
        return false
    }

    /**
     * @return true only when this invocation emitted the base-placement action and must not flow
     * into Crystal selection until a real CLIENT_TICK_END has been observed.
     */
    private fun handleBasePlacement(hit: BlockHitResult): Boolean {
        if (!isCrystalBase(hit.blockPosition()) && !isChargedAnchor(hit.blockPosition())) {
            mc.options.useKey.setPressed(false)

            val baseDiagnostics = getBaseDiagnostics()
            val placingThisInvocation = basePlaceClock == 0
            var action: ActionTickBoundary.ActionToken? = null

            if (!isHolding(Items.OBSIDIAN)) {
                if (!tickBaseSwitchClock()) {
                    return false
                }

                baseSwitchClock = switchDelay
                if (placingThisInvocation) {
                    action = ActionTickBoundary.beginAction(
                        owner = "CrystalBase",
                        kind = ActionTickBoundary.ActionKind.USE_ITEM_ON,
                        diagnosticsEnabled = baseDiagnostics
                    )
                }

                if (selectHotbarItem(
                        item = Items.OBSIDIAN,
                        owner = "CrystalBase",
                        action = action,
                        purpose = "selected Obsidian",
                        diagnosticsEnabled = baseDiagnostics
                    )
                ) {
                    ActionTickBoundary.diagnostic(
                        baseDiagnostics,
                        "CrystalBase selected Obsidian slot=${mc.player?.inventory?.selectedSlot}"
                    )
                }
            }

            if (isHolding(Items.OBSIDIAN) && tickBasePlaceClock()) {
                val placementAction = action ?: ActionTickBoundary.beginAction(
                    owner = "CrystalBase",
                    kind = ActionTickBoundary.ActionKind.USE_ITEM_ON,
                    diagnosticsEnabled = baseDiagnostics
                )
                if (!placeCrystal(hit, placementAction)) {
                    ActionTickBoundary.cancelAction(placementAction)
                    return false
                }

                basePlaceClock = baseDelay
                baseCrystalling = true
                basePlaceActionSerial = ActionTickBoundary.currentSerial()
                waitingForBasePlaceBoundary = true
                baseBoundaryWaitLoggedSerial = Long.MIN_VALUE
                ActionTickBoundary.diagnostic(
                    baseDiagnostics,
                    "CrystalBase placed Obsidian boundarySerial=$basePlaceActionSerial"
                )
                ActionTickBoundary.diagnostic(
                    baseDiagnostics,
                    "CrystalBase waiting post-place boundary serial=$basePlaceActionSerial"
                )
                return true
            }

            action?.let(ActionTickBoundary::cancelAction)
        }

        return false
    }

    private fun handleBaseCrystalSelection() {
        if (!isHolding(Items.END_CRYSTAL) && !baseCrystalSelected) {
            if (!tickBaseSwitchClock()) {
                return
            }

            baseCrystalSelected = selectHotbarItem(
                item = Items.END_CRYSTAL,
                owner = "CrystalBase",
                purpose = "selected End Crystal",
                diagnosticsEnabled = getBaseDiagnostics()
            )
            if (baseCrystalSelected) {
                ActionTickBoundary.diagnostic(
                    baseDiagnosticsEnabled,
                    "CrystalBase selected End Crystal slot=${mc.player?.inventory?.selectedSlot}"
                )
            }
            baseSwitchClock = switchDelay
        }
    }

    private fun handleBlockTarget(
        dontPlace: Boolean,
        diagnostics: ActionTickBoundary.CrystalSequenceDiagnostic? = null,
        explicitHit: BlockHitResult? = null,
    ): Boolean {
        // The normal action-phase gate handles this first. Retain a local guard so a future
        // direct call cannot reintroduce ATTACK -> USE_ITEM_ON before CLIENT_TICK_END.
        val pendingEntityBoundary = pendingEntityActionBoundary
        if (pendingEntityBoundary != null && !ActionTickBoundary.hasPassed(pendingEntityBoundary.actionBoundarySerial)) {
            ActionTickBoundary.crystalSequenceWarning(
                pendingEntityBoundary.diagnostics,
                "suppressing block placement: AutoCrystal-owned ATTACK is still waiting " +
                    "for CLIENT_TICK_END boundarySerial=${pendingEntityBoundary.actionBoundarySerial}"
            )
            return false
        }

        val hit = explicitHit ?: getLookedBlock() ?: return false

        if (!dontPlace && canPlaceCrystal(hit.blockPosition())) {
            if (diagnostics != null && !ActionTickBoundary.canSendCrystalSequenceBlockAction(diagnostics)) {
                return false
            }

            val basePlacementAction = if (crystalBase && baseCrystalling) {
                ActionTickBoundary.beginAction(
                    owner = "CrystalBase",
                    kind = ActionTickBoundary.ActionKind.USE_ITEM_ON,
                    diagnosticsEnabled = getBaseDiagnostics(),
                    onObserved = diagnostics?.let { crystalDiagnostics ->
                        { kind -> ActionTickBoundary.recordCrystalSequenceAction(crystalDiagnostics, kind) }
                    }
                )
            } else if (diagnostics != null) {
                ActionTickBoundary.beginAction(
                    owner = "AutoCrystal",
                    kind = ActionTickBoundary.ActionKind.USE_ITEM_ON,
                    diagnosticsEnabled = false,
                    onObserved = { kind -> ActionTickBoundary.recordCrystalSequenceAction(diagnostics, kind) }
                )
            } else {
                null
            }

            if (!placeCrystal(hit, basePlacementAction)) {
                basePlacementAction?.let(ActionTickBoundary::cancelAction)
                return false
            }

            if (basePlacementAction != null) {
                ActionTickBoundary.diagnostic(
                    baseDiagnosticsEnabled,
                    "CrystalBase placed End Crystal slot=${mc.player?.inventory?.selectedSlot}"
                )
            }
            if (diagnostics != null) {
                ActionTickBoundary.crystalSequenceDiagnostic(
                    diagnostics,
                    "AutoCrystal block placement attempted packetObserved=" +
                        "${basePlacementAction?.let(ActionTickBoundary::wasActionObserved) ?: false}"
                )
            }
            placeClock = placeDelay
            return true
        }

        return false
    }

    /**
     * An entity target is not equivalent to an entity packet. In particular, an already inhibited
     * crystal is removed/ignored locally without AutoCrystal issuing an attack, so its block
     * fallback remains safe in the same action tick.
     */
    private fun handleEntityTarget(
        dontBreak: Boolean,
        explicitTarget: Entity? = null,
        targetOrigin: EntityTargetOrigin = EntityTargetOrigin.CROSSHAIR,
    ): EntityActionOutcome {
        val entity = explicitTarget ?: getLookedEntity()?.entity ?: return EntityActionOutcome(EntityActionResult.NO_TARGET)
        val diagnostics = ActionTickBoundary.claimCrystalSequenceDiagnostic()
        updateCrystalSequenceContext(diagnostics, entity, targetOrigin)

        if (dontBreak) {
            ActionTickBoundary.crystalSequenceDiagnostic(
                diagnostics,
                "AutoCrystal entity target result=NO_ACTION reason=BreakDelay"
            )
            return EntityActionOutcome(EntityActionResult.NO_ACTION, diagnostics, entity.id, entity.javaClass.simpleName)
        }

        if (entity !is EndCrystal && entity !is Slime) {
            ActionTickBoundary.crystalSequenceDiagnostic(
                diagnostics,
                "AutoCrystal entity target result=NO_ACTION reason=unsupported-target"
            )
            return EntityActionOutcome(EntityActionResult.NO_ACTION, diagnostics, entity.id, entity.javaClass.simpleName)
        }

        val inhibitedWithoutPacket = ActionTickBoundary.withCrystalSequenceDiagnostic(diagnostics) {
            ModuleCrystalOptimizer.isAttackInhibited(entity)
        }
        if (inhibitedWithoutPacket) {
            ActionTickBoundary.crystalSequenceDiagnostic(
                diagnostics,
                "AutoCrystal entity target result=INHIBITED_WITHOUT_PACKET"
            )
            return EntityActionOutcome(
                EntityActionResult.INHIBITED_WITHOUT_PACKET,
                diagnostics,
                entity.id,
                entity.javaClass.simpleName
            )
        }

        val player = mc.player ?: run {
            ActionTickBoundary.crystalSequenceDiagnostic(
                diagnostics,
                "AutoCrystal entity target result=NO_ACTION reason=missing-player"
            )
            return EntityActionOutcome(EntityActionResult.NO_ACTION, diagnostics, entity.id, entity.javaClass.simpleName)
        }
        val level = mc.level ?: run {
            ActionTickBoundary.crystalSequenceDiagnostic(
                diagnostics,
                "AutoCrystal entity target result=NO_ACTION reason=missing-level"
            )
            return EntityActionOutcome(EntityActionResult.NO_ACTION, diagnostics, entity.id, entity.javaClass.simpleName)
        }
        val previousSlot = player.inventory.selectedSlot
        var antiWeaknessSwitched = false
        var antiWeaknessDiagnostics = false
        val attackAction: ActionTickBoundary.ActionToken

        if (antiWeakness && cantBreakCrystal(player)) {
            val weaponSlot = findAntiWeaknessTool(player) ?: run {
                ActionTickBoundary.crystalSequenceDiagnostic(
                    diagnostics,
                    "AutoCrystal entity target result=NO_ACTION reason=missing-antiweakness-tool"
                )
                return EntityActionOutcome(EntityActionResult.NO_ACTION, diagnostics, entity.id, entity.javaClass.simpleName)
            }
            antiWeaknessDiagnostics = ActionTickBoundary.claimDiagnosticCycle()
            attackAction = ActionTickBoundary.beginAction(
                owner = "AutoCrystal AntiWeakness",
                kind = ActionTickBoundary.ActionKind.ATTACK,
                diagnosticsEnabled = antiWeaknessDiagnostics,
                onObserved = { kind -> ActionTickBoundary.recordCrystalSequenceAction(diagnostics, kind) }
            )
            if (!selectHotbarSlot(
                    slot = weaponSlot,
                    owner = "AutoCrystal AntiWeakness",
                    action = attackAction,
                    purpose = "selected weakness tool",
                    diagnosticsEnabled = antiWeaknessDiagnostics
                )
            ) {
                ActionTickBoundary.cancelAction(attackAction)
                ActionTickBoundary.crystalSequenceDiagnostic(
                    diagnostics,
                    "AutoCrystal entity target result=NO_ACTION reason=antiweakness-switch-failed"
                )
                return EntityActionOutcome(EntityActionResult.NO_ACTION, diagnostics, entity.id, entity.javaClass.simpleName)
            }

            antiWeaknessSwitched = player.inventory.selectedSlot != previousSlot
            ActionTickBoundary.diagnostic(
                antiWeaknessDiagnostics,
                "AutoCrystal selected weakness tool slot=$weaponSlot previousSlot=$previousSlot"
            )
        } else {
            attackAction = ActionTickBoundary.beginAction(
                owner = "AutoCrystal",
                kind = ActionTickBoundary.ActionKind.ATTACK,
                diagnosticsEnabled = false,
                onObserved = { kind -> ActionTickBoundary.recordCrystalSequenceAction(diagnostics, kind) }
            )
        }

        val attackAttempted = ActionTickBoundary.withCrystalSequenceDiagnostic(diagnostics) {
            attackEntity(entity, attackAction)
        }
        if (!attackAttempted) {
            ActionTickBoundary.cancelAction(attackAction)
            if (antiWeaknessSwitched) {
                selectHotbarSlot(
                    slot = previousSlot,
                    owner = "AutoCrystal AntiWeakness",
                    purpose = "restoring weakness tool after no attack",
                    diagnosticsEnabled = antiWeaknessDiagnostics
                )
            }
            ActionTickBoundary.crystalSequenceDiagnostic(
                diagnostics,
                "AutoCrystal entity target result=NO_ACTION reason=missing-interaction-manager"
            )
            return EntityActionOutcome(EntityActionResult.NO_ACTION, diagnostics, entity.id, entity.javaClass.simpleName)
        }

        breakClock = breakDelay
        updateCrystalSequenceContext(diagnostics, entity, targetOrigin)
        ActionTickBoundary.crystalSequenceDiagnostic(
            diagnostics,
            "AutoCrystal entity target result=ATTACK_ATTEMPTED " +
                "packetObserved=${ActionTickBoundary.wasActionObserved(attackAction)}"
        )

        if (antiWeaknessSwitched) {
            val actionBoundarySerial = ActionTickBoundary.currentSerial()
            pendingAntiWeaknessRestoration = PendingAntiWeaknessRestoration(
                previousSlot = previousSlot,
                actionBoundarySerial = actionBoundarySerial,
                player = player,
                level = level,
                diagnosticsEnabled = antiWeaknessDiagnostics
            )
            ActionTickBoundary.diagnostic(
                antiWeaknessDiagnostics,
                "AutoCrystal attacked Crystal boundarySerial=$actionBoundarySerial previousSlot=$previousSlot"
            )
            ActionTickBoundary.diagnostic(
                antiWeaknessDiagnostics,
                "AutoCrystal waiting post-attack boundary serial=$actionBoundarySerial"
            )
        }

        return EntityActionOutcome(EntityActionResult.ATTACK_ATTEMPTED, diagnostics, entity.id, entity.javaClass.simpleName)
    }

    /**
     * Handles only the deferred post-attack slot restoration. A successful restore intentionally
     * ends this [runCrystalTick] invocation so an integration cannot create a second action after
     * the restore in the same server-visible action tick.
     */
    private fun handlePendingAntiWeaknessRestoration(player: Player, level: Any): Boolean {
        val pending = pendingAntiWeaknessRestoration ?: return false

        if (pending.player !== player || pending.level !== level) {
            pendingAntiWeaknessRestoration = null
            antiWeaknessRestoredAtSerial = ActionTickBoundary.currentSerial()
            return true
        }

        if (!ActionTickBoundary.hasPassed(pending.actionBoundarySerial)) {
            if (pending.lastWaitLoggedSerial != ActionTickBoundary.currentSerial()) {
                pending.lastWaitLoggedSerial = ActionTickBoundary.currentSerial()
                ActionTickBoundary.diagnostic(
                    pending.diagnosticsEnabled,
                    "AutoCrystal waiting post-attack boundary serial=${pending.actionBoundarySerial}"
                )
            }
            return true
        }

        if (pending.previousSlot !in 0..8) {
            ActionTickBoundary.diagnostic(
                pending.diagnosticsEnabled,
                "AutoCrystal dropped invalid weakness restoration slot=${pending.previousSlot}"
            )
            pendingAntiWeaknessRestoration = null
            antiWeaknessRestoredAtSerial = ActionTickBoundary.currentSerial()
            return true
        }

        if (!ActionTickBoundary.canSendPostActionHeldItemChange(
                owner = "AutoCrystal AntiWeakness",
                actionBoundarySerial = pending.actionBoundarySerial,
                diagnosticsEnabled = pending.diagnosticsEnabled
            )
        ) {
            return true
        }

        ActionTickBoundary.diagnostic(
            pending.diagnosticsEnabled,
            "AutoCrystal restored previous slot=${pending.previousSlot} " +
                "boundarySerial=${pending.actionBoundarySerial}"
        )
        if (!selectHotbarSlot(
                slot = pending.previousSlot,
                owner = "AutoCrystal AntiWeakness",
                purpose = "restored previous slot",
                diagnosticsEnabled = pending.diagnosticsEnabled,
                postActionBoundarySerial = pending.actionBoundarySerial
            )
        ) {
            // Keep the pending state so a transient interaction-manager absence cannot turn into
            // a stale restore in a different world or an unsafe immediate slot packet.
            return true
        }

        pendingAntiWeaknessRestoration = null
        antiWeaknessRestoredAtSerial = ActionTickBoundary.currentSerial()
        return true
    }

    private fun placeCrystal(
        hit: BlockHitResult,
        action: ActionTickBoundary.ActionToken? = null,
    ): Boolean {
        val player = mc.player ?: return false
        val interaction = mc.gameMode ?: return false

        interaction.interactBlock(player, InteractionHand.MAIN_HAND, hit)
        action?.let(ActionTickBoundary::actionCallCompleted)
        player.swingHand(InteractionHand.MAIN_HAND)
        return true
    }

    private fun attackEntity(
        entity: Entity,
        action: ActionTickBoundary.ActionToken? = null,
    ): Boolean {
        val player = mc.player ?: return false
        val interaction = mc.gameMode ?: return false

        interaction.attackEntity(player, entity)
        action?.let(ActionTickBoundary::actionCallCompleted)
        player.swingHand(InteractionHand.MAIN_HAND)
        return true
    }

    private fun tickBaseSwitchClock(): Boolean {
        if (baseSwitchClock > 0) {
            baseSwitchClock--
            return false
        }

        return true
    }

    private fun tickBasePlaceClock(): Boolean {
        if (basePlaceClock > 0) {
            basePlaceClock--
            return false
        }

        return true
    }

    private fun getBaseDiagnostics(): Boolean {
        if (!baseDiagnosticsClaimed) {
            baseDiagnosticsClaimed = true
            baseDiagnosticsEnabled = ActionTickBoundary.claimDiagnosticCycle()
        }

        return baseDiagnosticsEnabled
    }

    private fun updateActivationBindState(bind: InputBind, matches: Boolean, action: Int) {
        if (observedActivationKey != bind.boundKey) {
            observedActivationKey = bind.boundKey
            activationBindEventDown = false
        }

        if (matches) {
            activationBindEventDown = action != GLFW.GLFW_RELEASE
        }
    }

    private fun isActivationDown(): Boolean {
        val bind = activateKey
        if (bind.isUnbound) {
            return true
        }

        val key = bind.boundKey
        if (observedActivationKey != key) {
            observedActivationKey = key
            activationBindEventDown = false
        }

        return when (key.type) {
            InputConstants.Type.MOUSE -> isMouseDown(key.value)
            InputConstants.Type.KEYSYM -> InputConstants.isKeyDown(mc.window, key.value)
            InputConstants.Type.SCANCODE -> activationBindEventDown
        }
    }

    private fun isMouseDown(button: Int): Boolean =
        GLFW.glfwGetMouseButton(mc.window.handle(), button) == GLFW.GLFW_PRESS

    private fun isHolding(item: Item): Boolean = mc.player?.mainHandItem?.item == item

    private fun isDeadPlayerNearby(player: Player): Boolean = mc.level?.players()?.any { otherPlayer ->
        otherPlayer !== player && otherPlayer.distanceToSqr(player) <= 36.0 &&
            (otherPlayer.isDeadOrDying || otherPlayer.health <= 0.0f)
    } == true

    private fun shouldWaitDamageTick(player: Player): Boolean {
        val targetDamageTick = mc.level?.players()?.any { otherPlayer ->
            otherPlayer !== player && otherPlayer.distanceToSqr(player) < 36.0 &&
                otherPlayer.vehicle == null && !otherPlayer.onGround() && otherPlayer.hurtTime >= 2
        } == true

        return targetDamageTick && player.vehicle !is Player
    }

    private fun cantBreakCrystal(player: Player): Boolean {
        val weakness = player.getEffect(MobEffects.WEAKNESS)
        val strength = player.getEffect(MobEffects.STRENGTH)

        return weakness != null && (strength == null || strength.amplifier <= weakness.amplifier) && !isTool(player.mainHandItem)
    }

    private fun isTool(stack: ItemStack): Boolean =
        stack.isSword || stack.isPickaxe || stack.isAxe || stack.isShovel

    private fun findAntiWeaknessTool(player: Player): Int? =
        (0..8).firstOrNull { isTool(player.inventory.getStack(it)) }

    private fun isValidBaseStartItem(stack: ItemStack): Boolean =
        stack.isSword || BaseSetting.workWithTotem && stack.item == Items.TOTEM_OF_UNDYING ||
            BaseSetting.workWithCrystal && stack.item == Items.END_CRYSTAL

    private fun selectHotbarItem(
        item: Item,
        owner: String? = null,
        action: ActionTickBoundary.ActionToken? = null,
        purpose: String = "selected hotbar item",
        diagnosticsEnabled: Boolean = false,
        postActionBoundarySerial: Long? = null,
    ): Boolean {
        val player = mc.player ?: return false
        val slot = (0..8).firstOrNull { player.inventory.getStack(it).item == item } ?: return false
        return selectHotbarSlot(
            slot = slot,
            owner = owner,
            action = action,
            purpose = purpose,
            diagnosticsEnabled = diagnosticsEnabled,
            postActionBoundarySerial = postActionBoundarySerial
        )
    }

    private fun selectHotbarSlot(
        slot: Int,
        owner: String? = null,
        action: ActionTickBoundary.ActionToken? = null,
        purpose: String = "selected hotbar slot",
        diagnosticsEnabled: Boolean = false,
        postActionBoundarySerial: Long? = null,
    ): Boolean {
        if (slot !in 0..8) {
            return false
        }

        val player = mc.player ?: return false
        val interaction = mc.gameMode ?: return false

        if (owner != null && postActionBoundarySerial != null && !ActionTickBoundary.canSendPostActionHeldItemChange(
                owner,
                postActionBoundarySerial,
                diagnosticsEnabled
            )
        ) {
            return false
        }

        val changedSlot = player.inventory.selectedSlot != slot
        val expectation = if (owner != null && changedSlot) {
            ActionTickBoundary.expectHeldItemChange(
                owner = owner,
                purpose = purpose,
                action = action,
                postActionBoundarySerial = postActionBoundarySerial,
                diagnosticsEnabled = diagnosticsEnabled
            )
        } else {
            null
        }
        if (expectation != null && !expectation.allowed) {
            return false
        }

        player.inventory.selectedSlot = slot
        interaction.ensureHasSentCarriedItem()
        expectation?.let(ActionTickBoundary::completeHeldItemChangeAttempt)
        return true
    }

    private fun canPlaceAgainst(hit: BlockHitResult): Boolean =
        mc.level?.getBlockState(hit.blockPosition().relative(hit.side))?.canBeReplaced() == true

    private fun canPlaceCrystal(basePos: BlockPos): Boolean {
        val level = mc.level ?: return false
        if (!isCrystalBase(basePos)) {
            return false
        }

        val crystalPos = basePos.above()
        if (!level.getBlockState(crystalPos).isAir) {
            return false
        }

        val crystalBox = crystalPlacementColumn(basePos)

        return level.getEntities(null, crystalBox) { it.isAlive }.none(::blocksCrystalPlacement)
    }

    private fun blocksCrystalPlacement(entity: Entity): Boolean =
        !ModuleCrystalOptimizer.shouldIgnoreForPlacement(entity)

    private fun isCrystalBase(pos: BlockPos): Boolean {
        val block = mc.level?.getBlockState(pos)?.block ?: return false
        return block == Blocks.OBSIDIAN || block == Blocks.BEDROCK
    }

    private fun isChargedAnchor(pos: BlockPos): Boolean {
        val state = mc.level?.getBlockState(pos) ?: return false
        return state.block == Blocks.RESPAWN_ANCHOR && state.getValue(RespawnAnchorBlock.CHARGE) > 0
    }

    private fun getLookedBlock(): BlockHitResult? {
        val target = mc.crosshairTarget ?: return null

        if (target.type == HitResult.Type.BLOCK) {
            return target as? BlockHitResult
        }

        if (target is EntityHitResult && ModuleCrystalOptimizer.shouldIgnoreForPlacement(target.entity)) {
            val player = mc.player ?: return null
            val raycastResult = player.pick(5.0, 1.0f, false)

            if (raycastResult.type == HitResult.Type.BLOCK) {
                return raycastResult as? BlockHitResult
            }
        }

        return null
    }

    private fun getLookedEntity(): EntityHitResult? {
        val target = mc.crosshairTarget ?: return null
        return if (target.type == HitResult.Type.ENTITY) target as? EntityHitResult else null
    }

    private const val HEAD_BOB_DIAGNOSTIC_PREFIX = "[HeadBobDiag]"
    private const val MAX_HEAD_BOB_DIAGNOSTIC_CYCLES = 32
    private const val MAX_HEAD_BOB_DIAGNOSTIC_MESSAGES = MAX_HEAD_BOB_DIAGNOSTIC_CYCLES * 16
}
