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
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.GameTickEvent
import net.ccbluex.liquidbounce.event.events.OverlayRenderEvent
import net.ccbluex.liquidbounce.event.events.PacketEvent
import net.ccbluex.liquidbounce.event.events.TransferOrigin
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.combat.killaura.ModuleKillAura
import net.ccbluex.liquidbounce.utils.block.isIn
import net.ccbluex.liquidbounce.utils.client.interaction
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.client.player
import net.ccbluex.liquidbounce.utils.client.world
import net.ccbluex.liquidbounce.utils.combat.shouldBeAttacked
import net.ccbluex.liquidbounce.utils.combat.CombatManager
import net.ccbluex.liquidbounce.utils.entity.blockPosition
import net.ccbluex.liquidbounce.utils.entity.eyePos
import net.ccbluex.liquidbounce.utils.entity.getStackInHand
import net.ccbluex.liquidbounce.utils.entity.getStack
import net.ccbluex.liquidbounce.utils.entity.clickSlot
import net.ccbluex.liquidbounce.utils.entity.interactBlock
import net.ccbluex.liquidbounce.utils.entity.interactItem
import net.ccbluex.liquidbounce.utils.entity.isRiding
import net.ccbluex.liquidbounce.utils.entity.item
import net.ccbluex.liquidbounce.utils.entity.main
import net.ccbluex.liquidbounce.utils.entity.offHand
import net.ccbluex.liquidbounce.utils.entity.pitch
import net.ccbluex.liquidbounce.utils.entity.pos
import net.ccbluex.liquidbounce.utils.entity.selectedSlot
import net.ccbluex.liquidbounce.utils.entity.setPressed
import net.ccbluex.liquidbounce.utils.entity.swingHand
import net.ccbluex.liquidbounce.utils.entity.toCenterPos
import net.ccbluex.liquidbounce.utils.entity.useKey
import net.ccbluex.liquidbounce.utils.entity.yaw
import net.ccbluex.liquidbounce.utils.input.isPressed
import net.ccbluex.liquidbounce.utils.inventory.Slots
import net.ccbluex.liquidbounce.utils.inventory.findClosestSlot
import net.ccbluex.liquidbounce.utils.item.toRegistryEntry
import net.minecraft.client.gui.DrawContext
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import net.minecraft.tags.BlockTags
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.arrow.Arrow
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.item.ArrowItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import org.lwjgl.glfw.GLFW
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * VCore AutoCart port.
 *
 * Bow mode reacts to releasing a bow and places a rail + TNT minecart at the predicted impact position.
 * CrossBow mode places rail/cart, optionally ignites with flint, then fires a loaded crossbow at the cart.
 */
@Suppress("MagicNumber", "TooManyFunctions")
object ModuleAutoCart : ClientModule("AutoCart", Category.COMBAT) {

    private const val TICK_LENGTH_MS = 50L
    private const val TOTEM_USE_STATUS: Byte = 35

    private val mode by enumChoice("Mode", Mode.BOW).apply(::tagBy)
    private val maxDistance by float("MaxDistance", 4.5f, 2.0f..6.0f)
    private val startDelay by int("StartDelay", 25, 0..60, "ms")
    private val activeBind by key("ActiveBind", GLFW.GLFW_KEY_UNKNOWN)
    private val delay by int("Delay", 25, 0..100, "ms")
    private val cartAuraDelay by int("CartAuraDelay", 0, 0..20, "ticks")
    private val refillSlot by int("Slot", 9, 1..9)
    private val swapBack by boolean("SwapBack", true)
    private val changeLook by boolean("ChangeLook", false)
    private val cartAura by boolean("CartAura", false)
    private val cartAuraTarget by boolean("AuraTarget", true)
    private val cartOtherPlayer by boolean("OtherPlayer", false)
    private val reFill by enumChoice("ReFill", ReFillMode.NONE)

    private val scheduledActions = mutableListOf<ScheduledAction>()

    private var silentRotation: FloatArray? = null
    private var rotating = false
    private var crossBowPressed = false
    private var cartAuraExecuting = false
    private var scheduledRefillSwap = false
    private var scheduledRefillInventorySlot = -1
    private var scheduledRefillHotbarSlot = -1
    private var scheduledRefillAt = -1L

    override fun enable() = resetState()

    override fun disable() = resetState()

    private fun resetState() {
        scheduledActions.clear()
        silentRotation = null
        rotating = false
        crossBowPressed = false
        cartAuraExecuting = false
        clearRefillState()
        mc.options.useKey.setPressed(false)
    }

    @Suppress("unused")
    private val tickHandler = handler<GameTickEvent> {
        val now = System.currentTimeMillis()

        runScheduledActions(now)
        handleScheduledRefill(now)
        handleRefill(now)

        if (mode == Mode.CROSSBOW) {
            val pressed = activeBind.value != GLFW.GLFW_KEY_UNKNOWN && activeBind.isPressed

            if (pressed && !crossBowPressed) {
                crossBowPressed = true
                executeCrossBowMode()
            }

            if (!pressed) {
                crossBowPressed = false
            }
        }
    }

    @Suppress("unused")
    private val packetHandler = handler<PacketEvent> { event ->
        if (event.origin == TransferOrigin.OUTGOING && !event.isCancelled) {
            handleBowRelease(event.packet)
        } else if (event.origin == TransferOrigin.INCOMING) {
            handleTotemPop(event.packet)
        }
    }

    @Suppress("unused")
    private val overlayRenderHandler = handler<OverlayRenderEvent> { event ->
        if (mode == Mode.CROSSBOW && !mc.options.hideGui && hasCrossbowDebugBaseRequirements()) {
            renderCrossbowDebug(event.context)
        }
    }

    private fun handleBowRelease(packet: Packet<*>) {
        if (mode != Mode.BOW ||
            packet !is ServerboundPlayerActionPacket ||
            packet.action != ServerboundPlayerActionPacket.Action.RELEASE_USE_ITEM ||
            player.mainHandItem.item != Items.BOW) {
            return
        }

        executeBowMode()
    }

    private fun handleRefill(now: Long) {
        if (reFill == ReFillMode.NONE) {
            clearRefillState()
            return
        }

        if (scheduledRefillSwap || mc.screen != null || player.containerMenu != player.inventoryMenu) {
            return
        }

        val targetHotbarSlot = refillSlot - 1
        if (player.inventory.getStack(targetHotbarSlot).item == Items.TNT_MINECART) {
            return
        }

        val inventorySlot = Slots.Inventory.findSlot(Items.TNT_MINECART) ?: return

        if (reFill == ReFillMode.LEGIT && isMoving()) {
            scheduleRefillSwap(inventorySlot.getIdForServer(null), targetHotbarSlot, now + 5L)
        } else {
            performRefillSwap(inventorySlot.getIdForServer(null), targetHotbarSlot)
        }
    }

    private fun scheduleRefillSwap(inventorySlot: Int, hotbarSlot: Int, dueTime: Long) {
        scheduledRefillSwap = true
        scheduledRefillInventorySlot = inventorySlot
        scheduledRefillHotbarSlot = hotbarSlot
        scheduledRefillAt = dueTime
    }

    private fun handleScheduledRefill(now: Long) {
        if (scheduledRefillSwap && now >= scheduledRefillAt) {
            performRefillSwap(scheduledRefillInventorySlot, scheduledRefillHotbarSlot)
            clearRefillState()
        }
    }

    private fun performRefillSwap(inventorySlot: Int, hotbarSlot: Int) {
        if (inventorySlot == -1 || hotbarSlot !in 0..8 || mc.screen != null || player.containerMenu != player.inventoryMenu) {
            return
        }

        if (player.inventory.getStack(hotbarSlot).item != Items.TNT_MINECART) {
            interaction.clickSlot(0, inventorySlot, hotbarSlot, ContainerInput.SWAP, player)
        }
    }

    private fun clearRefillState() {
        scheduledRefillSwap = false
        scheduledRefillInventorySlot = -1
        scheduledRefillHotbarSlot = -1
        scheduledRefillAt = -1L
    }

    private fun handleTotemPop(packet: Packet<*>) {
        if (mode != Mode.CROSSBOW || !cartAura || cartAuraExecuting || packet !is ClientboundEntityEventPacket ||
            packet.eventId != TOTEM_USE_STATUS) {
            return
        }

        val popTarget = packet.getEntity(world) as? Player ?: return
        if (popTarget == player) {
            return
        }

        val auraTarget = ModuleKillAura.targetTracker.target
        val isAuraTarget = cartAuraTarget && auraTarget != null && popTarget == auraTarget
        val isOtherPlayer = cartOtherPlayer && popTarget.shouldBeAttacked() && player.pos.distanceTo(popTarget.pos) <= 6.0

        if (!isAuraTarget && !isOtherPlayer) {
            return
        }

        val placePos = findCartAuraPosition(popTarget) ?: return
        val crossbowSlot = findLoadedCrossbowSlot() ?: return
        val cartSlot = Slots.Hotbar.findClosestSlot(Items.TNT_MINECART)?.hotbarSlot ?: return
        val crossbowStack = player.inventory.getStack(crossbowSlot)
        val hasFlame = hasFlameEnchant(crossbowStack)
        val flintSlot = Slots.Hotbar.findClosestSlot(Items.FLINT_AND_STEEL)?.hotbarSlot

        if (!hasFlame && flintSlot == null) {
            return
        }

        val railExists = isRailBlock(world.getBlockState(placePos.above()).block)
        val railSlot = findRailInHotbar()

        if (!railExists && railSlot == null) {
            return
        }

        executeCartAura(placePos, crossbowSlot, railSlot, cartSlot, flintSlot, hasFlame, railExists)
    }

    private fun executeCartAura(
        basePos: BlockPos,
        crossbowSlot: Int,
        railSlot: Int?,
        cartSlot: Int,
        flintSlot: Int?,
        hasFlame: Boolean,
        railExists: Boolean
    ) {
        cartAuraExecuting = true
        val previousSlot = player.inventory.selectedSlot
        val startDelayMs = cartAuraDelay * TICK_LENGTH_MS
        val totalDurationMs = startDelayMs + delay * (if (railExists) 4 else 5)
        CombatManager.pauseCombatForAtLeast((totalDurationMs / TICK_LENGTH_MS).toInt() + 2)

        schedule(startDelayMs) {
            applyRotation(angleTo(Vec3(basePos.x + 0.5, basePos.above().y.toDouble(), basePos.z + 0.5)))
        }

        var offset = startDelayMs + delay
        if (!railExists) {
            schedule(offset.toLong()) {
                if (!isRailBlock(world.getBlockState(basePos.above()).block) && railSlot != null) {
                    selectSlot(railSlot)
                    placeRailOn(basePos)
                }
            }
            offset += delay
        }

        schedule(offset.toLong()) {
            selectSlot(cartSlot)
            placeMinecartOn(basePos)
        }
        offset += delay

        if (!hasFlame && flintSlot != null) {
            val firePos = findFirePosition(basePos) ?: return
            schedule(offset.toLong()) {
                val fireVec = Vec3(firePos.x + 0.5, firePos.y + 1.0, firePos.z + 0.5)
                applyRotation(angleTo(fireVec))
                selectSlot(flintSlot)
                interactWithBlock(BlockHitResult(fireVec, Direction.UP, firePos, false))
            }
            offset += delay
        }

        schedule(offset.toLong()) {
            val minecartCenter = Vec3(basePos.x + 0.5, basePos.y + 1.5, basePos.z + 0.5)
            applyRotation(angleTo(minecartCenter))
            selectSlot(crossbowSlot)
            interactWithItem()
            player.swingHand(InteractionHand.MAIN_HAND)
        }
        offset += delay

        schedule(offset.toLong()) {
            if (swapBack) {
                selectSlot(previousSlot)
            }
            endRotation()
            cartAuraExecuting = false
        }
    }

    private fun executeBowMode() {
        val cartSlot = Slots.Hotbar.findClosestSlot(Items.TNT_MINECART)?.hotbarSlot ?: return
        val targetPos = calcBowTrajectory(player.yaw) ?: return
        val basePos = getCartBasePos(targetPos)
        val railExists = isRailBlock(world.getBlockState(basePos.above()).block)
        val railSlot = findRailInHotbar()

        if (!railExists && railSlot == null) {
            return
        }

        val distSq = player.eyePos.distanceToSqr(basePos.above().toCenterPos())
        if (distSq > maxDistance * maxDistance || distSq < 4.0) {
            return
        }

        schedule(startDelay.toLong()) {
            executeBowPlacement(basePos, railExists, railSlot, cartSlot)
        }
    }

    private fun executeBowPlacement(basePos: BlockPos, railExistsAtStart: Boolean, railSlot: Int?, cartSlot: Int) {
        if (mode != Mode.BOW) {
            return
        }

        val previousSlot = player.inventory.selectedSlot

        applyRotation(angleTo(Vec3(basePos.x + 0.5, basePos.above().y.toDouble(), basePos.z + 0.5)))

        if (!railExistsAtStart) {
            schedule(delay.toLong()) {
                if (!isRailBlock(world.getBlockState(basePos.above()).block) && railSlot != null) {
                    selectSlot(railSlot)
                    placeRailOn(basePos)
                }
            }
        }

        val cartOffset = if (railExistsAtStart) delay.toLong() else (delay * 2L)
        schedule(cartOffset) {
            selectSlot(cartSlot)
            placeMinecartOn(basePos)
        }

        schedule(cartOffset + delay) {
            if (swapBack) {
                selectSlot(previousSlot)
            }
            endRotation()
        }
    }

    private fun executeCrossBowMode() {
        val crossbowSlot = findLoadedCrossbowSlot() ?: return
        val railSlot = findRailInHotbar() ?: return
        val cartSlot = Slots.Hotbar.findClosestSlot(Items.TNT_MINECART)?.hotbarSlot ?: return
        val crossbowStack = player.inventory.getStack(crossbowSlot)
        val hasFlame = hasFlameEnchant(crossbowStack)
        val flintSlot = Slots.Hotbar.findClosestSlot(Items.FLINT_AND_STEEL)?.hotbarSlot

        if (!hasFlame && flintSlot == null) {
            return
        }

        val rayResult = rayTraceFromEyes(4.5) ?: return
        if (rayResult.type != HitResult.Type.BLOCK) {
            return
        }

        val hitPos = rayResult.blockPosition()
        if (player.eyePos.distanceToSqr(hitPos.toCenterPos()) > 20.25) {
            return
        }

        val basePos = if (world.getBlockState(hitPos).isAir) hitPos.below() else hitPos
        val firePos = if (hasFlame) null else findFirePosition(basePos) ?: return
        val previousSlot = player.inventory.selectedSlot

        applyRotation(angleTo(Vec3(basePos.x + 0.5, basePos.above().y.toDouble(), basePos.z + 0.5)))

        schedule(delay.toLong()) {
            if (!isRailBlock(world.getBlockState(basePos.above()).block)) {
                selectSlot(railSlot)
                placeRailOn(basePos)
            }
        }

        schedule(delay * 2L) {
            selectSlot(cartSlot)
            placeMinecartOn(basePos)
        }

        var shootOffset = delay * 3L
        if (!hasFlame && firePos != null && flintSlot != null) {
            schedule(shootOffset) {
                val fireVec = Vec3(firePos.x + 0.5, firePos.y + 1.0, firePos.z + 0.5)
                applyRotation(angleTo(fireVec))
                selectSlot(flintSlot)
                interactWithBlock(BlockHitResult(fireVec, Direction.UP, firePos, false))
            }
            shootOffset += delay
        }

        schedule(shootOffset) {
            val minecartCenter = Vec3(basePos.x + 0.5, basePos.y + 1.5, basePos.z + 0.5)
            applyRotation(angleTo(minecartCenter))
            selectSlot(crossbowSlot)
            interactWithItem()
            player.swingHand(InteractionHand.MAIN_HAND)
        }

        schedule(shootOffset + delay) {
            if (swapBack) {
                selectSlot(previousSlot)
            }
            endRotation()
        }
    }

    private fun getCartBasePos(targetPos: BlockPos): BlockPos {
        val targetState = world.getBlockState(targetPos)
        return if (!isRailBlock(targetState.block) && !targetState.isAir) targetPos else targetPos.below()
    }

    private fun applyRotation(angle: FloatArray) {
        if (changeLook) {
            player.yaw = angle[0]
            player.pitch = angle[1]
        } else {
            silentRotation = angle
            rotating = true
        }
    }

    private fun endRotation() {
        silentRotation = null
        rotating = false
    }

    fun isAutoCartMoveFixActive(): Boolean = getAutoCartMoveFixRotation() != null

    private fun getAutoCartMoveFixRotation(): FloatArray? {
        val rotation = silentRotation
        return if (running && !changeLook && rotating && rotation != null && !player.isRiding) rotation else null
    }

    private fun runWithInteractionRotation(angle: FloatArray?, action: () -> Unit) {
        if (!changeLook && angle != null) {
            val previousYaw = player.yaw
            val previousPitch = player.pitch

            player.yaw = angle[0]
            player.pitch = angle[1]

            try {
                action()
            } finally {
                player.yaw = previousYaw
                player.pitch = previousPitch
            }
        } else {
            action()
        }
    }

    private fun interactWithItem() {
        runWithInteractionRotation(silentRotation) {
            interaction.interactItem(player, InteractionHand.MAIN_HAND)
        }
    }

    private fun placeRailOn(basePos: BlockPos) {
        val hitResult = BlockHitResult(
            Vec3(basePos.x + 0.5, basePos.above().y.toDouble(), basePos.z + 0.5),
            Direction.UP,
            basePos,
            false
        )

        interactWithBlock(hitResult)
    }

    private fun placeMinecartOn(basePos: BlockPos) {
        val hitResult = BlockHitResult(
            Vec3(basePos.x + 0.5, basePos.above().y + 0.125, basePos.z + 0.5),
            Direction.UP,
            basePos.above(),
            false
        )

        interactWithBlock(hitResult)
    }

    private fun interactWithBlock(hitResult: BlockHitResult) {
        runWithInteractionRotation(silentRotation) {
            interaction.interactBlock(player, InteractionHand.MAIN_HAND, hitResult)
        }
        player.swingHand(InteractionHand.MAIN_HAND)
    }

    private fun calcBowTrajectory(yaw: Float): BlockPos? {
        var x = player.x
        var y = player.y + player.getEyeHeight(player.pose) - 0.1000000014901161
        var z = player.z

        val yawRad = Math.toRadians(yaw.toDouble())
        val pitchRad = Math.toRadians(player.pitch.toDouble())
        var motionX = -sin(yawRad) * cos(pitchRad)
        var motionY = -sin(pitchRad)
        var motionZ = cos(yawRad) * cos(pitchRad)

        var power = player.ticksUsingItem / 20.0f
        power = (power * power + power * 2.0f) / 3.0f
        power = power.coerceAtMost(1.0f)

        if (power < 0.1f) {
            return null
        }

        val distance = sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ)
        if (distance <= 0.0) {
            return null
        }

        motionX /= distance
        motionY /= distance
        motionZ /= distance

        val bowPower = power * 3.0f
        motionX *= bowPower
        motionY *= bowPower
        motionZ *= bowPower

        if (!player.onGround()) {
            motionY += player.deltaMovement.y
        }

        repeat(300) {
            val lastPos = Vec3(x, y, z)

            x += motionX
            y += motionY
            z += motionZ

            motionX *= 0.99
            motionY *= 0.99
            motionZ *= 0.99
            motionY -= 0.05

            val projectileBox = AABB(x - 0.3, y - 0.3, z - 0.3, x + 0.3, y + 0.3, z + 0.3)
            if (world.entitiesForRendering().any { entity ->
                    entity !is Arrow && entity != player && entity.boundingBox.intersects(projectileBox)
                }) {
                return null
            }

            val pos = Vec3(x, y, z)
            val blockHit = world.clip(ClipContext(lastPos, pos, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player))

            if (blockHit.type == HitResult.Type.BLOCK) {
                return blockHit.blockPosition()
            }

            if (y <= world.minY - 1.0) {
                return null
            }
        }

        return null
    }

    private fun findCartAuraPosition(target: Entity): BlockPos? {
        val playerEyes = player.eyePos
        val targetBox = target.boundingBox
        val targetFeet = Vec3(target.x, targetBox.minY, target.z)
        val targetBlockPos = target.blockPosition()
        val radius = 4
        var bestPos: BlockPos? = null
        var bestDist = Double.MAX_VALUE

        for (x in targetBlockPos.x - radius..targetBlockPos.x + radius) {
            for (z in targetBlockPos.z - radius..targetBlockPos.z + radius) {
                for (y in targetBlockPos.y - radius..targetBlockPos.y) {
                    val candidate = BlockPos(x, y, z)
                    if (!world.getBlockState(candidate).isSolidRender || candidate.y + 1 > targetBox.minY) {
                        continue
                    }

                    val aboveState = world.getBlockState(candidate.above())
                    if (!aboveState.isAir && !isRailBlock(aboveState.block)) {
                        continue
                    }

                    val surfacePos = Vec3(candidate.x + 0.5, candidate.y + 1.0, candidate.z + 0.5)
                    if (playerEyes.distanceToSqr(surfacePos) > 20.25) {
                        continue
                    }

                    val blockCheck = world.clip(
                        ClipContext(playerEyes, surfacePos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)
                    )

                    if (blockCheck.type == HitResult.Type.BLOCK && blockCheck.blockPosition() != candidate) {
                        continue
                    }

                    if (!isPathClearOfPlayers(playerEyes, surfacePos, target)) {
                        continue
                    }

                    val cartCenter = Vec3(candidate.x + 0.5, candidate.y + 1.5, candidate.z + 0.5)
                    val damageCheck = world.clip(
                        ClipContext(cartCenter, targetFeet, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)
                    )

                    if (damageCheck.type == HitResult.Type.BLOCK) {
                        continue
                    }

                    val distToTarget = cartCenter.distanceToSqr(targetFeet)
                    if (distToTarget < bestDist) {
                        bestDist = distToTarget
                        bestPos = candidate
                    }
                }
            }
        }

        return bestPos
    }

    private fun isPathClearOfPlayers(from: Vec3, to: Vec3, exclude: Entity): Boolean {
        return world.players().none { otherPlayer ->
            otherPlayer != player && otherPlayer != exclude && otherPlayer.boundingBox.clip(from, to).isPresent
        }
    }

    private fun findRailInHotbar(): Int? =
        Slots.Hotbar.findClosestSlot(Items.RAIL, Items.ACTIVATOR_RAIL, Items.DETECTOR_RAIL, Items.POWERED_RAIL)?.hotbarSlot

    private fun renderCrossbowDebug(context: DrawContext) {
        val hotbarLeft = mc.window.guiScaledWidth / 2 - 91
        val hotbarTop = mc.window.guiScaledHeight - 22

        for (slot in 0..8) {
            if (isCrossbowDebugCandidate(player.inventory.getStack(slot))) {
                val x = hotbarLeft + slot * 20 + 1
                val y = hotbarTop + 1
                context.fill(x, y, x + 20, y + 20, 0x64FF0000)
            }
        }
    }

    private fun hasCrossbowDebugBaseRequirements(): Boolean {
        if (findRailInHotbar() == null || Slots.Hotbar.findClosestSlot(Items.TNT_MINECART) == null ||
            !hasArrowAmmoInInventory()) {
            return false
        }

        return (0..8).any { slot -> isCrossbowDebugCandidate(player.inventory.getStack(slot)) }
    }

    private fun isCrossbowDebugCandidate(stack: ItemStack): Boolean =
        isUnloadedCrossbow(stack) && (hasFlameEnchant(stack) || Slots.Hotbar.findClosestSlot(Items.FLINT_AND_STEEL) != null)

    private fun hasArrowAmmoInInventory(): Boolean =
        player.inventory.main.any(::isArrowStack) || player.inventory.offHand.any(::isArrowStack)

    private fun isArrowStack(stack: ItemStack): Boolean = !stack.isEmpty && stack.item is ArrowItem

    private fun isUnloadedCrossbow(stack: ItemStack): Boolean = stack.item == Items.CROSSBOW && !isCrossbowCharged(stack)

    private fun isCrossbowCharged(stack: ItemStack): Boolean =
        stack.item == Items.CROSSBOW && stack.get(DataComponents.CHARGED_PROJECTILES)?.isEmpty == false

    private fun findLoadedCrossbowSlot(): Int? =
        Slots.Hotbar.findClosestSlot(::isCrossbowCharged)?.hotbarSlot

    private fun isRailBlock(block: Block): Boolean =
        block == Blocks.RAIL || block == Blocks.POWERED_RAIL || block == Blocks.DETECTOR_RAIL ||
            block == Blocks.ACTIVATOR_RAIL

    private fun hasFlameEnchant(stack: ItemStack): Boolean =
        EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAME.toRegistryEntry(), stack) > 0

    private fun rayTraceFromEyes(maxRange: Double): BlockHitResult {
        val eyePos = player.eyePos
        val lookVec = player.getViewVector(1.0f)
        val endPos = eyePos.add(lookVec.scale(maxRange))

        return world.clip(ClipContext(eyePos, endPos, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player))
    }

    private fun findFirePosition(targetPos: BlockPos): BlockPos? {
        val eyePos = player.eyePos
        val minecartPos = Vec3(targetPos.x + 0.5, targetPos.y + 1.5, targetPos.z + 0.5)
        val direction = minecartPos.subtract(eyePos).normalize()
        val horizontals = arrayOf(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
        var bestPos: BlockPos? = null
        var bestScore = Double.MAX_VALUE

        for (facing in horizontals) {
            val candidate = targetPos.relative(facing)
            if (world.getBlockState(candidate).isSolidRender && world.getBlockState(candidate.above()).isAir) {
                val fireCenter = Vec3(candidate.x + 0.5, candidate.y + 1.0, candidate.z + 0.5)
                val eyeToFire = fireCenter.subtract(eyePos)
                val t = eyeToFire.dot(direction)
                val eyeToMinecart = minecartPos.subtract(eyePos).dot(direction)

                if (t > 0.0 && t < eyeToMinecart) {
                    val closest = eyePos.add(direction.scale(t))
                    val distToLine = closest.distanceTo(fireCenter)

                    if (distToLine < bestScore) {
                        bestScore = distToLine
                        bestPos = candidate
                    }
                }
            }
        }

        if (bestPos == null) {
            for (facing in horizontals) {
                val adjacent = targetPos.relative(facing)
                if (world.getBlockState(adjacent).isSolidRender && world.getBlockState(adjacent.above()).isAir) {
                    return adjacent
                }
            }
        }

        return bestPos
    }

    private fun angleTo(vec: Vec3): FloatArray {
        val diff = vec.subtract(player.eyePos)
        val diffX = diff.x
        val diffY = diff.y
        val diffZ = diff.z

        return floatArrayOf(
            Mth.wrapDegrees(Math.toDegrees(kotlin.math.atan2(diffZ, diffX)).toFloat() - 90f),
            Mth.wrapDegrees((-Math.toDegrees(kotlin.math.atan2(diffY, sqrt(diffX * diffX + diffZ * diffZ)))).toFloat())
        )
    }

    private fun selectSlot(slot: Int) {
        if (slot !in 0..8 || player.inventory.selectedSlot == slot) {
            return
        }

        player.inventory.selectedSlot = slot
        interaction.ensureHasSentCarriedItem()
    }

    private fun isMoving(): Boolean = player.input.getMoveVector().lengthSquared() > 0.0f

    private fun schedule(delayMs: Long, action: () -> Unit) {
        scheduledActions += ScheduledAction(System.currentTimeMillis() + max(0L, delayMs), action)
    }

    private fun runScheduledActions(now: Long) {
        val iterator = scheduledActions.iterator()

        while (iterator.hasNext()) {
            val scheduledAction = iterator.next()

            if (now < scheduledAction.dueTime) {
                continue
            }

            iterator.remove()
            scheduledAction.action()
        }
    }

    private data class ScheduledAction(
        val dueTime: Long,
        val action: () -> Unit
    )

    private enum class Mode(override val choiceName: String) : NamedChoice {
        BOW("Bow"),
        CROSSBOW("CrossBow")
    }

    private enum class ReFillMode(override val choiceName: String) : NamedChoice {
        NONE("None"),
        NORMAL("Normal"),
        LEGIT("Legit")
    }
}
