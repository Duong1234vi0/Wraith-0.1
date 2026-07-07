package net.ccbluex.liquidbounce.utils.entity

import com.mojang.datafixers.util.Pair as DfuPair
import com.mojang.authlib.GameProfile
import net.minecraft.client.KeyMapping
import net.minecraft.client.MouseHandler
import net.minecraft.client.User
import net.minecraft.client.Camera
import net.minecraft.client.particle.ParticleEngine
import com.mojang.blaze3d.platform.InputConstants
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.input.KeyEvent
import net.minecraft.client.Minecraft
import net.minecraft.client.Options
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.client.renderer.LevelRenderer
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.client.multiplayer.ServerData
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.client.multiplayer.PlayerInfo
import net.minecraft.client.multiplayer.MultiPlayerGameMode
import net.minecraft.client.multiplayer.prediction.PredictiveAction
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.player.ClientInput
import net.minecraft.client.player.LocalPlayer
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.network.chat.Component
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ServerGamePacketListener
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import net.minecraft.network.protocol.game.ClientboundAnimatePacket
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.AreaEffectCloud
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Abilities
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.player.Input
import net.minecraft.world.entity.player.Player as McPlayer
import net.minecraft.world.food.FoodData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUseAnimation
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.material.FogType
import net.minecraft.world.level.saveddata.maps.MapId
import net.minecraft.world.level.saveddata.maps.MapItemSavedData
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.scores.PlayerTeam
import net.minecraft.world.scores.Scoreboard
import org.joml.Quaternionf
import org.joml.Quaternionfc

val Entity.world: Level
    get() = level()

val Entity.pos: Vec3
    get() = position()

var LivingEntity.renderYaw: Float
    get() = yBodyRot
    set(value) {
        yBodyRot = value
    }

var LivingEntity.lastRenderYaw: Float
    get() = yBodyRotO
    set(value) {
        yBodyRotO = value
    }

var Entity.velocity: Vec3
    get() = deltaMovement
    set(value) {
        deltaMovement = value
    }

var Entity.deltaMovement: Vec3
    get() = getDeltaMovement()
    set(value) {
        setDeltaMovement(value)
    }

val Entity.age: Int
    get() = tickCount

var Entity.yaw: Float
    get() = yRot
    set(value) {
        yRot = value
    }

var Entity.pitch: Float
    get() = xRot
    set(value) {
        xRot = value
    }

val Entity.prevYaw: Float
    get() = yRotO

val Entity.prevPitch: Float
    get() = xRotO

val Entity.lastRenderX: Double
    get() = xo

val Entity.lastRenderY: Double
    get() = yo

val Entity.lastRenderZ: Double
    get() = zo

val Entity.lastX: Double
    get() = xo

val Entity.lastBaseY: Double
    get() = yo

val Entity.lastY: Double
    get() = yo

val Entity.lastZ: Double
    get() = zo

val Entity.prevX: Double
    get() = xo

val Entity.prevZ: Double
    get() = zo

val Entity.lastYaw: Float
    get() = yRotO

val Entity.lastPitch: Float
    get() = xRotO

val Entity.prevY: Double
    get() = yOld

val Entity.isDead: Boolean
    get() = !isAlive

val Entity.isTouchingWater: Boolean
    get() = isInWater

val Entity.isGliding: Boolean
    get() = this is LivingEntity && isFallFlying

val Entity.hasVehicle: Boolean
    get() = isPassenger

fun Entity.hasVehicle(): Boolean = hasVehicle

val Entity.isRiding: Boolean
    get() = isPassenger

val Entity.hasNoGravity: Boolean
    get() = isNoGravity

fun Entity.hasNoGravity(): Boolean = hasNoGravity

val Entity.targetingMargin: Float
    get() = pickRadius

val Entity.eyePos: Vec3
    get() = eyePosition

fun Entity.getCameraPosVec(tickDelta: Float): Vec3 = getEyePosition(tickDelta)

fun Entity.canHit(): Boolean = isPickable

val Entity.soundCategory: SoundSource
    get() = soundSource

val Entity.horizontalFacing: Direction
    get() = direction

val Entity.isSneaking: Boolean
    get() = isShiftKeyDown

val Entity.controllingVehicle: Entity?
    get() = controlledVehicle

val Entity.vehicle: Entity?
    get() = getVehicle()

val Entity.entityId: Int
    get() = id

val Entity.deltaMovementMultiplier: Float
    get() = level().getBlockState(blockPosition()).block.speedFactor

fun Entity.setPosition(x: Double, y: Double, z: Double) = setPos(x, y, z)

fun Entity.setPosition(pos: Vec3) = setPos(pos)

fun Entity.updatePosition(x: Double, y: Double, z: Double) = setPos(x, y, z)

fun Entity.updatePosition(pos: Vec3) = setPos(pos)

fun Entity.copyPositionAndRotation(entity: Entity) = snapTo(entity.x, entity.y, entity.z, entity.yRot, entity.xRot)

var Entity.onGround: Boolean
    get() = onGround()
    set(value) {
        setOnGround(value)
    }

var Entity.noClip: Boolean
    get() = noPhysics
    set(value) {
        noPhysics = value
    }

var Entity.deltaMovementX: Double
    get() = deltaMovement.x
    set(value) {
        setDeltaMovement(value, deltaMovement.y, deltaMovement.z)
    }

var Entity.deltaMovementY: Double
    get() = deltaMovement.y
    set(value) {
        setDeltaMovement(deltaMovement.x, value, deltaMovement.z)
    }

var Entity.deltaMovementZ: Double
    get() = deltaMovement.z
    set(value) {
        setDeltaMovement(deltaMovement.x, deltaMovement.y, value)
    }

val Entity.height: Float
    get() = bbHeight

val Entity.width: Float
    get() = bbWidth

val Minecraft.crosshairTarget: HitResult?
    get() = hitResult

val Minecraft.currentServerEntry: ServerData?
    get() = currentServer

val Minecraft.session: User
    get() = user

var Minecraft.attackCooldown: Int
    get() = missTime
    set(value) {
        missTime = value
    }

val Minecraft.particleManager: ParticleEngine
    get() = particleEngine

val Minecraft.mouse: MouseHandler
    get() = mouseHandler

fun MouseHandler.lockCursor() = grabMouse()

val GameRenderer.camera: Camera
    get() = mainCamera

val GameRenderer.lightmapTextureManager: LegacyLightmapTextureManager
    get() = LegacyLightmapTextureManager

object LegacyLightmapTextureManager {
    fun enable() = Unit
    fun disable() = Unit
    val lightmapFramebuffer: LegacyLightmapFramebuffer
        get() = LegacyLightmapFramebuffer
}

object LegacyLightmapFramebuffer {
    val colorAttachment: Int
        get() = 0
}

val Camera.yaw: Float
    get() = yRot()

val Camera.pitch: Float
    get() = xRot()

val Camera.rotation: Quaternionf
    get() = rotation()

val Camera.isThirdPerson: Boolean
    get() = isDetached

val Camera.submersionType: FogType
    get() = fluidInCamera

fun PoseStack.multiply(rotation: Quaternionfc) = mulPose(rotation)

fun PoseStack.push() = pushPose()

fun PoseStack.pop() = popPose()

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
fun VertexFormat.bindAttributes(program: Int) = Unit

val DynamicTexture.glId: Int
    get() = 0

fun ParticleEngine.addEmitter(entity: Entity, particle: ParticleOptions) = createTrackingEmitter(entity, particle)

fun ParticleEngine.addEmitter(entity: Entity, particle: ParticleOptions, maxAge: Int) =
    createTrackingEmitter(entity, particle, maxAge)

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
fun ParticleEngine.addBlockBreakingParticles(pos: BlockPos, direction: Direction) = Unit

val Minecraft.firstPersonRenderer
    get() = gameRenderer.itemInHandRenderer

val Minecraft.worldRenderer: LevelRenderer
    get() = levelRenderer

val Minecraft.debugOverlay: net.minecraft.client.gui.components.DebugScreenOverlay
    get() = gui.debugOverlay

fun LevelRenderer.reload() = allChanged()

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
fun net.minecraft.client.renderer.ItemInHandRenderer.resetEquipProgress(hand: InteractionHand) = Unit

val Options.attackKey: KeyMapping
    get() = keyAttack

val Options.useKey: KeyMapping
    get() = keyUse

val Options.inventoryKey: KeyMapping
    get() = keyInventory

val Options.forwardKey: KeyMapping
    get() = keyUp

val Options.backKey: KeyMapping
    get() = keyDown

val Options.leftKey: KeyMapping
    get() = keyLeft

val Options.rightKey: KeyMapping
    get() = keyRight

val Options.jumpKey: KeyMapping
    get() = keyJump

val Options.sneakKey: KeyMapping
    get() = keyShift

val Options.sprintKey: KeyMapping
    get() = keySprint

val Options.pickItemKey: KeyMapping
    get() = keyPickItem

val Options.playerListKey: KeyMapping
    get() = keyPlayerList

fun Entity.squaredDistanceTo(other: Entity): Double = distanceToSqr(other)

fun Entity.squaredDistanceTo(x: Double, y: Double, z: Double): Double = distanceToSqr(x, y, z)

fun Vec3.squaredDistanceTo(other: Vec3): Double = distanceToSqr(other)

fun Vec3.squaredDistanceTo(x: Double, y: Double, z: Double): Double = distanceToSqr(x, y, z)

val Vec3.lengthSquared: Double
    get() = lengthSqr()

fun Vec3.lengthSquared(): Double = lengthSqr()

fun Vec3.multiply(value: Double): Vec3 = scale(value)

fun Vec3.multiply(x: Double, y: Double, z: Double): Vec3 = multiply(Vec3(x, y, z))

val AbstractContainerScreen<*>.rows: Int
    get() = menu.slots.size / 9

fun McPlayer.incrementStat(stat: net.minecraft.stats.Stat<*>): Unit = awardStat(stat)

fun McPlayer.incrementStat(stat: Identifier): Unit = awardStat(Stats.CUSTOM.get(stat))

fun AABB.stretch(vec: Vec3): AABB = expandTowards(vec)

val Vec3.magnitude: Double
    get() = length()

fun Vec3.normalizeOrZero(): Vec3 = if (lengthSqr() > 1.0E-12) normalize() else Vec3.ZERO

val net.minecraft.world.phys.Vec2.magnitude: Float
    get() = length()

fun Vec3.dotProduct(other: Vec3): Double = dot(other)

fun Vec3.crossProduct(other: Vec3): Vec3 = cross(other)

fun Vec3.approximatelyEquals(other: Vec3, epsilon: Double = 1.0E-7): Boolean =
    distanceToSqr(other) <= epsilon * epsilon

fun movementInputToVelocity(movementInput: Vec3, speed: Float, yaw: Float): Vec3 {
    val normalizedInput = if (movementInput.lengthSqr() > 1.0) movementInput.normalize() else movementInput
    val input = normalizedInput.scale(speed.toDouble())
    val sin = Mth.sin(yaw * (Math.PI / 180.0))
    val cos = Mth.cos(yaw * (Math.PI / 180.0))

    return Vec3(
        input.x * cos - input.z * sin,
        input.y,
        input.z * cos + input.x * sin
    )
}

val LivingEntity.mainHandStack
    get() = getMainHandItem()

val LivingEntity.mainHandItem: ItemStack
    get() = getMainHandItem()

val LivingEntity.offhandItem: ItemStack
    get() = getOffhandItem()

val LivingEntity.offHandStack
    get() = getOffhandItem()

fun LivingEntity.getStackInHand(hand: InteractionHand): ItemStack = getItemInHand(hand)

fun LivingEntity.setStackInHand(hand: InteractionHand, stack: ItemStack) = setItemInHand(hand, stack)

fun LivingEntity.swingHand(hand: InteractionHand) = swing(hand)

fun LivingEntity.swingHand(hand: InteractionHand, fromServerPlayer: Boolean) = swing(hand, fromServerPlayer)

val Player.isCreative: Boolean
    get() = isCreative()

val Player.isCreativeLevelTwoOp: Boolean
    get() = canUseGameMasterBlocks()

fun MultiPlayerGameMode.hasCreativeInventory(): Boolean = playerMode.isCreative

fun MultiPlayerGameMode.attackBlock(pos: BlockPos, direction: Direction): Boolean =
    startDestroyBlock(pos, direction)

fun MultiPlayerGameMode.updateBlockBreakingProgress(pos: BlockPos, direction: Direction): Boolean =
    continueDestroyBlock(pos, direction)

fun MultiPlayerGameMode.cancelBlockBreaking() = stopDestroyBlock()

fun LivingEntity.hasStatusEffect(effect: net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect>): Boolean =
    hasEffect(effect)

val MobEffectInstance.effectType: net.minecraft.core.Holder<MobEffect>
    get() = effect

val LivingEntity.isAffectedBySplashPotions: Boolean
    get() = isAffectedByPotions()

val AreaEffectCloud.potionContentsComponent: PotionContents
    get() = potionContents

val PotionContents.effects: Iterable<MobEffectInstance>
    get() = allEffects

val LivingEntity.isClimbing: Boolean
    get() = onClimbable()

fun Player.getAttackCooldownProgress(baseTime: Float): Float = getAttackStrengthScale(baseTime)

val Player.attackCooldown: Float
    get() = getAttackStrengthScale(0.5f)

val Player.attackCooldownProgressPerTick: Float
    get() = getCurrentItemAttackStrengthDelay()

val Player.lastAttackedTicks: Int
    get() = attackStrengthTicker

fun Player.resetLastAttackedTicks() = resetAttackStrengthTicker()

fun Player.addCritParticles(entity: Entity) = crit(entity)

fun Player.addEnchantedHitParticles(entity: Entity) = magicCrit(entity)

fun Player.hasLimitedAttackSpeed(): Boolean = true

fun Player.canSee(entity: Entity): Boolean = hasLineOfSight(entity)

fun LocalPlayer.addVelocity(x: Double, y: Double, z: Double) {
    deltaMovement = deltaMovement.add(x, y, z)
}

fun LocalPlayer.setVelocity(x: Double, y: Double, z: Double) {
    deltaMovement = Vec3(x, y, z)
}

fun LocalPlayer.setVelocity(velocity: Vec3) {
    deltaMovement = velocity
}

fun LocalPlayer.updatePosition(x: Double, y: Double, z: Double) = setPos(x, y, z)

fun LocalPlayer.closeHandledScreen() = closeContainer()

fun LocalPlayer.dropSelectedItem(all: Boolean): Boolean = drop(all)

fun Screen.close() = onClose()

val Player.armor: Int
    get() = armorValue

val Player.inSneakingPose: Boolean
    get() = isShiftKeyDown

var Player.allowFlying: Boolean
    get() = abilities.mayfly
    set(value) {
        abilities.mayfly = value
    }

val Player.creativeMode: Boolean
    get() = abilities.instabuild

var Player.flySpeed: Float
    get() = abilities.flyingSpeed
    set(value) {
        abilities.setFlyingSpeed(value)
    }

val Player.hungerManager
    get() = foodData

val FoodData.isNotFull: Boolean
    get() = needsFood()

fun Player.jump() {
    jumpFromGround()
}

fun LivingEntity.getStatusEffect(effect: net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect>) =
    getEffect(effect)

val net.minecraft.world.effect.MobEffectInstance.amplifier: Int
    get() = getAmplifier()

fun LivingEntity.getEquippedStack(slot: net.minecraft.world.entity.EquipmentSlot): ItemStack = getItemBySlot(slot)

fun LivingEntity.startGliding() {
    // The server-side start is still sent through ServerboundPlayerCommandPacket at call sites.
}

fun LivingEntity.stopGliding() {
    stopFallFlying()
}

fun LivingEntity.jump() {
    jumpFromGround()
}

val LivingEntity.standingEyeHeight: Float
    get() = eyeHeight

var LivingEntity.headYaw: Float
    get() = yHeadRot
    set(value) {
        yHeadRot = value
    }

val LivingEntity.prevHeadYaw: Float
    get() = yHeadRotO

val LivingEntity.handSwinging: Boolean
    get() = swinging

val LivingEntity.handItems: Iterable<ItemStack>
    get() = listOf(mainHandItem, offhandItem)

class LegacySkinTextures(val texture: Identifier)

val AbstractClientPlayer.skinTextures: LegacySkinTextures
    get() = LegacySkinTextures(skin.body().id())

val Player.scoreboard: Scoreboard
    get() = level().scoreboard

fun Scoreboard.getTeam(name: String): PlayerTeam? = getPlayersTeam(name) ?: getPlayerTeam(name)

var PlayerTeam.prefix: Component
    get() = playerPrefix
    set(value) {
        setPlayerPrefix(value)
    }

val ClientboundSetEquipmentPacket.equipmentList: List<DfuPair<EquipmentSlot, ItemStack>>
    get() = slots

val ClientboundSetEquipmentPacket.entityId: Int
    get() = entity

val <A, B> DfuPair<A, B>.first: A
    get() = getFirst()

val <A, B> DfuPair<A, B>.second: B
    get() = getSecond()

fun ClientLevel.getMapState(id: MapId): MapItemSavedData? = getMapData(id)

val PlayerInfo.profile: GameProfile
    get() = getProfile()

val ClientPacketListener.playerList: Collection<PlayerInfo>
    get() = onlinePlayers

val ClientPacketListener.playerUuids: Collection<java.util.UUID>
    get() = onlinePlayerIds

fun ClientPacketListener.getPlayerListEntry(uuid: java.util.UUID): PlayerInfo? = getPlayerInfo(uuid)

fun ClientPacketListener.getPlayerListEntry(name: String): PlayerInfo? = getPlayerInfo(name)

fun ClientPacketListener.sendPacket(packet: Packet<*>) {
    @Suppress("UNCHECKED_CAST")
    send(packet as Packet<ServerGamePacketListener>)
}

fun ClientPacketListener.send(packet: Packet<*>) = sendPacket(packet)

fun ClientPacketListener.sendChatMessage(message: String) = sendChat(message)

fun ClientPacketListener.sendChatCommand(command: String) = sendCommand(command)

fun MultiPlayerGameMode.sendSequencedPacket(level: ClientLevel, packetCreator: (Int) -> Packet<*>) {
    startPrediction(level, PredictiveAction { sequence ->
        @Suppress("UNCHECKED_CAST")
        packetCreator(sequence) as Packet<ServerGamePacketListener>
    })
}

fun MultiPlayerGameMode.interactBlock(
    player: LocalPlayer,
    hand: InteractionHand,
    hitResult: BlockHitResult
): InteractionResult = useItemOn(player, hand, hitResult)

fun MultiPlayerGameMode.interactItem(player: Player, hand: InteractionHand): InteractionResult = useItem(player, hand)

fun MultiPlayerGameMode.attackEntity(player: Player, entity: Entity) = attack(player, entity)

fun MultiPlayerGameMode.interactEntity(
    player: Player,
    entity: Entity,
    hand: InteractionHand
): InteractionResult = interact(player, entity, EntityHitResult(entity), hand)

fun MultiPlayerGameMode.interactEntityAtLocation(
    player: Player,
    entity: Entity,
    hitResult: EntityHitResult,
    hand: InteractionHand
): InteractionResult = interact(player, entity, hitResult, hand)

fun MultiPlayerGameMode.clickSlot(
    containerId: Int,
    slot: Int,
    button: Int,
    actionType: ContainerInput,
    player: Player
) = handleContainerInput(containerId, slot, button, actionType, player)

fun MultiPlayerGameMode.stopUsingItem(player: Player) = player.stopUsingItem()

val MultiPlayerGameMode.isBreakingBlock: Boolean
    get() = destroyProgress > 0.0f

val InteractionResult.isAccepted: Boolean
    get() = consumesAction()

fun InteractionResult.shouldSwingHand(): Boolean =
    this is InteractionResult.Success && swingSource() == InteractionResult.SwingSource.CLIENT

fun net.minecraft.world.item.Item.getUseAction(stack: ItemStack): ItemUseAnimation = stack.getUseAnimation()

fun HitResult.pos(): Vec3 = location

val HitResult.pos: Vec3
    get() = location

fun BlockHitResult.blockPosition(): BlockPos = blockPos

val BlockHitResult.side: Direction
    get() = direction

fun BlockPos.up(): BlockPos = above()

fun BlockPos.down(): BlockPos = below()

fun BlockPos.toCenterPos(): Vec3 = center

fun BlockPos.offset(direction: Direction): BlockPos = relative(direction)

fun BlockPos.add(x: Int, y: Int, z: Int): BlockPos = offset(x, y, z) as BlockPos

fun BlockPos.add(offset: net.minecraft.core.Vec3i): BlockPos = offset(offset) as BlockPos

fun BlockPos.mutableCopy(): BlockPos.MutableBlockPos = mutable()

val Direction.vector: net.minecraft.core.Vec3i
    get() = unitVec3i

fun Direction.rotateClockwise(axis: Direction.Axis): Direction = getClockWise(axis)

val BlockState.isAir: Boolean
    get() = isAir()

fun Level.getEntityById(id: Int): Entity? = getEntity(id)

fun Level.getOtherEntities(except: Entity?, box: AABB): List<Entity> = getEntities(except, box) { true }

fun Level.raycast(context: ClipContext): BlockHitResult = clip(context)

fun Level.isAir(pos: BlockPos): Boolean = getBlockState(pos).isAir

fun Level.isSpaceEmpty(box: AABB): Boolean = noCollision(box)

fun Level.getEntities(): Iterable<Entity> = if (this is ClientLevel) entitiesForRendering() else emptyList()

fun Level.playSound(
    x: Double,
    y: Double,
    z: Double,
    sound: net.minecraft.sounds.SoundEvent,
    source: SoundSource,
    volume: Float,
    pitch: Float,
    distanceDelay: Boolean
) = playLocalSound(x, y, z, sound, source, volume, pitch, distanceDelay)

val Minecraft.isInSingleplayer: Boolean
    get() = hasSingleplayerServer()

val Options.perspective
    get() = cameraType

val KeyMapping.isPressed: Boolean
    get() = isDown

val KeyMapping.boundKey: InputConstants.Key
    get() = key

val InputConstants.Key.isPressed: Boolean
    get() = InputConstants.isKeyDown(Minecraft.getInstance().window, value)

fun KeyMapping.setPressed(pressed: Boolean) = setDown(pressed)

fun KeyMapping.matchesKey(keyCode: Int, scanCode: Int): Boolean = matches(KeyEvent(keyCode, scanCode, 0))

val InputConstants.Key.code: Int
    get() = value

val User.username: String
    get() = name

val User.uuidOrNull: java.util.UUID?
    get() = profileId

val ClientboundPlayerInfoUpdatePacket.playerAdditionEntries: List<ClientboundPlayerInfoUpdatePacket.Entry>
    get() = newEntries()

val ClientboundPlayerInfoUpdatePacket.actionsCompat
    get() = actions()

val ClientboundPlayerInfoUpdatePacket.entriesCompat
    get() = entries()

val ClientboundMoveEntityPacket.isPositionChanged: Boolean
    get() = hasPosition()

val ClientboundAnimatePacket.animationId: Int
    get() = action

val ClientboundAnimatePacket.entityId: Int
    get() = id

val ClientboundSetEntityMotionPacket.entityId: Int
    get() = id

val ClientboundSetEntityMotionPacket.deltaMovementX: Double
    get() = movement.x * 8000.0

val ClientboundSetEntityMotionPacket.deltaMovementY: Double
    get() = movement.y * 8000.0

val ClientboundSetEntityMotionPacket.deltaMovementZ: Double
    get() = movement.z * 8000.0

fun EntityDimensions.getBoxAt(pos: Vec3): AABB = makeBoundingBox(pos)

fun EntityDimensions.getBoxAt(x: Double, y: Double, z: Double): AABB = makeBoundingBox(x, y, z)

val Player.jumpVelocity: Float
    get() = getJumpPower()

fun Entity.isTeammate(other: Entity): Boolean = isAlliedTo(other)

val LocalPlayer.isInFluid: Boolean
    get() = isInWater || isInLava

val LocalPlayer.isHoldingOntoLadder: Boolean
    get() = onClimbable()

val ClientInput.untransformed: Input
    get() = keyPresses

var Abilities.allowFlying: Boolean
    get() = mayfly
    set(value) {
        mayfly = value
    }

val Abilities.creativeMode: Boolean
    get() = instabuild

var Abilities.flySpeed: Float
    get() = flyingSpeed
    set(value) {
        setFlyingSpeed(value)
    }

val ClientInput.initial: Input
    get() = keyPresses

val AABB.lengthX: Double
    get() = xsize

val AABB.lengthY: Double
    get() = ysize

val AABB.lengthZ: Double
    get() = zsize

val AABB.bottomY: Double
    get() = minY

val AABB.center: Vec3
    get() = getCenter()

fun AABB.expand(x: Double, y: Double, z: Double): AABB = inflate(x, y, z)

fun AABB.expand(value: Double): AABB = inflate(value)

fun AABB.offset(x: Double, y: Double, z: Double): AABB = move(x, y, z)

fun AABB.offset(offset: Vec3): AABB = move(offset)

fun AABB.raycast(from: Vec3, to: Vec3): java.util.Optional<Vec3> = clip(from, to)

fun AABB.getMin(axis: Direction.Axis): Double = when (axis) {
    Direction.Axis.X -> minX
    Direction.Axis.Y -> minY
    Direction.Axis.Z -> minZ
}

fun AABB.getMax(axis: Direction.Axis): Double = when (axis) {
    Direction.Axis.X -> maxX
    Direction.Axis.Y -> maxY
    Direction.Axis.Z -> maxZ
}

fun Vec3.negate(): Vec3 = reverse()

fun Vec3.offset(direction: Direction, distance: Double): Vec3 =
    add(
        direction.stepX.toDouble() * distance,
        direction.stepY.toDouble() * distance,
        direction.stepZ.toDouble() * distance
    )

fun VoxelShape.getMin(axis: Direction.Axis): Double = min(axis)

fun VoxelShape.getMax(axis: Direction.Axis): Double = max(axis)

fun AABB.withMinY(minY: Double): AABB = AABB(minX, minY, minZ, maxX, maxY, maxZ)

fun AABB.withMaxY(maxY: Double): AABB = AABB(minX, minY, minZ, maxX, maxY, maxZ)

fun AABB.union(other: AABB): AABB = minmax(other)

fun AABB.intersection(other: AABB): AABB = intersect(other)

fun Full(
    x: Double,
    y: Double,
    z: Double,
    yaw: Float,
    pitch: Float,
    onGround: Boolean,
    horizontalCollision: Boolean
): ServerboundMovePlayerPacket.PosRot =
    ServerboundMovePlayerPacket.PosRot(x, y, z, yaw, pitch, onGround, horizontalCollision)

fun PositionAndOnGround(
    x: Double,
    y: Double,
    z: Double,
    onGround: Boolean,
    horizontalCollision: Boolean
): ServerboundMovePlayerPacket.Pos =
    ServerboundMovePlayerPacket.Pos(x, y, z, onGround, horizontalCollision)

fun LookAndOnGround(
    yaw: Float,
    pitch: Float,
    onGround: Boolean,
    horizontalCollision: Boolean
): ServerboundMovePlayerPacket.Rot =
    ServerboundMovePlayerPacket.Rot(yaw, pitch, onGround, horizontalCollision)

fun OnGroundOnly(onGround: Boolean, horizontalCollision: Boolean): ServerboundMovePlayerPacket.StatusOnly =
    ServerboundMovePlayerPacket.StatusOnly(onGround, horizontalCollision)
