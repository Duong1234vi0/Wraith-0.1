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
package net.ccbluex.liquidbounce.features.module.modules.render.hats
import net.ccbluex.liquidbounce.utils.entity.*

import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.config.types.nesting.Choice
import net.ccbluex.liquidbounce.config.types.nesting.ChoiceConfigurable
import net.ccbluex.liquidbounce.config.types.nesting.Configurable
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.misc.FriendManager
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleFreeLook
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.drawCustomMesh
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.vertex
import net.ccbluex.liquidbounce.render.withPositionRelativeToCamera
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.aiming.data.Rotation
import net.ccbluex.liquidbounce.utils.client.registerAsDynamicImageFromClientResources
import net.ccbluex.liquidbounce.utils.entity.interpolateCurrentPosition
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKeys
import com.mojang.blaze3d.vertex.BufferBuilder
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.util.Mth
import org.joml.Matrix4f
import org.joml.Quaternionf
import java.util.UUID
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

/**
 * LiquidBounce Hats port.
 *
 * Source reference: AnotherProject/LiquidBounce render/hats.
 */
object ModuleHats : ClientModule("Hats", Category.RENDER) {

    object HeightOffset : Configurable("HeightOffset") {
        private val height by float("Height", 0.2f, 0f..2f)

        fun current() = height
    }

    init {
        tree(HeightOffset)
    }

    internal val modes = choices(
        "Mode",
        HatsCone,
        arrayOf(HatsCone, HatsHalo, HatsOrbs, HatsFlower, HatsStar, HatsImage)
    )
        .apply { tagBy(this) }

}

private class HatsColorSettings : Configurable("Colors") {
    val syncColors by boolean("SyncColors", true)
    val firstColor by color("FirstColor", Color4b(0, 0, 255, 125))
    private val secondColor by color("SecondColor", Color4b(0, 0, 255, 125))
    private val spinSpeed by float("SpinSpeed", 1f, 0f..10f)

    fun getCurrentStepColor(angle: Float): Color4b {
        val second = if (syncColors) {
            firstColor
        } else {
            secondColor
        }

        return getColorByAngle(angle, firstColor, second, spinSpeed)
    }

    private fun getColorByAngle(angle: Float, color1: Color4b, color2: Color4b, speed: Float): Color4b {
        val timeOffset = if (speed > 0f) {
            ((System.currentTimeMillis().toDouble() / 10000.0) * speed.toDouble() % 1.0) * TWO_PI.toDouble()
        } else {
            0.0
        }

        val progress = sin(angle + timeOffset).toFloat() * 0.5f + 0.5f
        return color1.interpolateTo(color2, progress.toDouble())
    }
}

internal abstract class HatsMode(name: String) : Choice(name) {
    final override val parent: ChoiceConfigurable<*>
        get() = ModuleHats.modes

    private val autoOffSet by boolean("AutoOffSet", true)
    private val followRotation by boolean("FollowRotation", false)
    private val rotationSmooth by float("RotationSmooth", 0.35f, 0f..0.95f)
    private val smoothRotations = hashMapOf<UUID, SmoothRotation>()

    private object EquipOffset : Configurable("EquipmentOffset") {
        val equipmentOffset by float("ArmorOffset", 0.1f, 0f..1f)
    }

    private object FriendsOptions : Configurable("FriendsOptions") {
        val friendView by boolean("ViewOnFriend", true)
        val distance by int("Distance", 64, 8..512, "blocks")
    }

    private val hurtMarked by boolean("ShowDamage", true)
    protected val showInFirstPerson by boolean("FirstPersonView", true)

    init {
        tree(EquipOffset)
        tree(FriendsOptions)
    }

    override fun disable() {
        smoothRotations.clear()
    }

    protected abstract fun WorldRenderEnvironment.drawHat(isHurt: Boolean)

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        val localPlayer = mc.player ?: return@handler

        renderEnvironmentForWorld(event.matrixStack) {
            RenderSystem.disableCull()

            for (entity in world.players()) {
                val isMe = entity == localPlayer
                val isFriend = FriendManager.isFriend(entity)
                val inDistance = localPlayer.distanceTo(entity) <= FriendsOptions.distance

                val shouldRender = if (isMe) {
                    !mc.options.perspective.isFirstPerson || showInFirstPerson || ModuleFreeLook.running
                } else {
                    inDistance && isFriend && FriendsOptions.friendView
                }

                if (!shouldRender) {
                    continue
                }

                val isHurt = entity.hurtTime > 0 && hurtMarked
                val pos = entity.interpolateCurrentPosition(event.partialTicks)
                val rotation = entity.smoothHeadRotation(event.partialTicks)
                val eyeHeight = entity.getEyeHeight(entity.pose)
                val height = ModuleHats.HeightOffset.current()
                val equipOffset = if (!entity.getEquippedStack(EquipmentSlot.HEAD).isEmpty) {
                    EquipOffset.equipmentOffset
                } else {
                    0.0f
                }
                val pivotY = if (autoOffSet) {
                    entity.hatHeadPivotOffset(eyeHeight)
                } else {
                    eyeHeight
                }
                val localOffsetY = entity.height - pivotY + height + equipOffset

                withPositionRelativeToCamera(pos.add(0.0, pivotY.toDouble(), 0.0)) {
                    matrixStack.withPush {
                        if (autoOffSet || followRotation) {
                            multiply(rotation.toHatQuaternion())
                        }

                        translate(0.0, localOffsetY.toDouble(), 0.0)
                        drawHat(isHurt)
                    }
                }
            }
        }
    }

    private fun Player.smoothHeadRotation(tickDelta: Float): Rotation {
        val targetRotation = if (this == mc.player) {
            RotationManager.currentRotation
        } else {
            null
        }
        val targetYaw = targetRotation?.yaw ?: Mth.rotLerp(tickDelta, prevHeadYaw, headYaw)
        val targetPitch = targetRotation?.pitch ?: Mth.lerp(tickDelta, prevPitch, pitch)
        val state = smoothRotations.getOrPut(uuid) { SmoothRotation(targetYaw, targetPitch) }

        if ((!autoOffSet && !followRotation) || rotationSmooth <= 0f) {
            state.yaw = targetYaw
            state.pitch = targetPitch
            return Rotation(targetYaw, targetPitch, true)
        }

        val factor = (1f - rotationSmooth).coerceIn(0.05f, 1f)
        state.yaw += Mth.wrapDegrees(targetYaw - state.yaw) * factor
        state.pitch += (targetPitch - state.pitch) * factor

        return Rotation(state.yaw, state.pitch, true)
    }

    private fun Player.hatHeadPivotOffset(eyeHeight: Float): Float {
        return (height - HEAD_PIVOT_FROM_TOP).coerceIn(0.0f, eyeHeight)
    }

    protected fun BufferBuilder.addTorusQuad(
        matrix: Matrix4f,
        innerSegments: Int,
        outerCurAngle: Float,
        outerNextAngle: Float,
        outerCurRadius: Float,
        outerNextRadius: Float,
        innerRadius: Float,
        innerI: Int,
        color: Color4b
    ) {
        val innerCurAngle = getAngle(innerI, innerSegments)
        val innerNextAngle = getNextAngle(innerI, innerSegments)

        val curMainSin = sin(outerCurAngle)
        val curMainCos = cos(outerCurAngle)
        val nextMainSin = sin(outerNextAngle)
        val nextMainCos = cos(outerNextAngle)

        val innerCurSin = sin(innerCurAngle)
        val innerCurCos = cos(innerCurAngle)
        val innerNextSin = sin(innerNextAngle)
        val innerNextCos = cos(innerNextAngle)

        val curTubeY = innerRadius * innerCurSin
        val nextTubeY = innerRadius * innerNextSin
        val curTubeOffset = innerRadius * innerCurCos
        val nextTubeOffset = innerRadius * innerNextCos

        val p1Radius = outerCurRadius + curTubeOffset
        val p2Radius = outerCurRadius + nextTubeOffset
        val p3Radius = outerNextRadius + curTubeOffset
        val p4Radius = outerNextRadius + nextTubeOffset

        val p1x = p1Radius * curMainSin
        val p1z = p1Radius * curMainCos
        val p2x = p2Radius * curMainSin
        val p2z = p2Radius * curMainCos
        val p3x = p3Radius * nextMainSin
        val p3z = p3Radius * nextMainCos
        val p4x = p4Radius * nextMainSin
        val p4z = p4Radius * nextMainCos
        val argb = color.toARGB()

        vertex(matrix, p1x, curTubeY, p1z).setColor(argb)
        vertex(matrix, p2x, nextTubeY, p2z).setColor(argb)
        vertex(matrix, p3x, curTubeY, p3z).setColor(argb)
        vertex(matrix, p2x, nextTubeY, p2z).setColor(argb)
        vertex(matrix, p4x, nextTubeY, p4z).setColor(argb)
        vertex(matrix, p3x, curTubeY, p3z).setColor(argb)
    }

    protected inline fun WorldRenderEnvironment.withHatRotation(
        angle: Float,
        block: WorldRenderEnvironment.() -> Unit
    ) {
        matrixStack.withPush {
            if (abs(angle) > 1.0E-6f) {
                multiply(Quaternionf().rotationY(angle))
            }

            this@withHatRotation.block()
        }
    }

    protected fun getAngle(i: Int, segments: Int) = i * TWO_PI / segments

    protected fun getNextAngle(i: Int, segments: Int) = (i + 1) * TWO_PI / segments

    protected fun getRotationAngle(speed: Float): Float {
        return if (abs(speed) <= 1.0E-6f) {
            0f
        } else {
            (System.currentTimeMillis() % 360000) * 0.001f * speed
        }
    }
}

private data class SmoothRotation(var yaw: Float, var pitch: Float)

private object HatsCone : HatsMode("Cone") {
    private val colors = HatsColorSettings()

    private object HatConeSettings : Configurable("HatSettings") {
        object RadiusSettings : Configurable("RadiusSettings") {
            val outerRadius by float("OuterRadius", 0.6f, 0.1f..2f)
        }

        val peak by float("Peak", 0.3f, 0.01f..2f)
    }

    init {
        tree(HatConeSettings)
        tree(HatConeSettings.RadiusSettings)
        tree(colors)
    }

    override fun WorldRenderEnvironment.drawHat(isHurt: Boolean) {
        drawCustomMesh(TRIANGLES, DefaultVertexFormat.POSITION_COLOR, ShaderProgramKeys.POSITION_COLOR) { matrix ->
            val segments = 128

            for (i in 0 until segments) {
                val angle = getAngle(i, segments)
                val nextAngle = getNextAngle(i, segments)
                val color = if (!isHurt) {
                    colors.getCurrentStepColor(angle)
                } else {
                    Color4b(255, 0, 0, colors.firstColor.a)
                }.toARGB()

                vertex(
                    matrix,
                    cos(angle) * HatConeSettings.RadiusSettings.outerRadius,
                    0f,
                    sin(angle) * HatConeSettings.RadiusSettings.outerRadius
                ).setColor(color)
                vertex(
                    matrix,
                    cos(nextAngle) * HatConeSettings.RadiusSettings.outerRadius,
                    0f,
                    sin(nextAngle) * HatConeSettings.RadiusSettings.outerRadius
                ).setColor(color)
                vertex(matrix, 0f, HatConeSettings.peak, 0f).setColor(color)
            }
        }
    }
}

private object HatsHalo : HatsMode("Halo") {
    private val colors = HatsColorSettings()

    private object HatHaloSettings : Configurable("HatSettings") {
        val outerRadius by float("Radius", 0.3f, 0.1f..2f)
        val innerRadius by float("Thickness", 0.05f, 0.01f..1f)
    }

    init {
        tree(HatHaloSettings)
        tree(colors)
    }

    override fun WorldRenderEnvironment.drawHat(isHurt: Boolean) {
        drawCustomMesh(TRIANGLES, DefaultVertexFormat.POSITION_COLOR, ShaderProgramKeys.POSITION_COLOR) { matrix ->
            val outerSegments = 144
            val innerSegments = 12

            for (outerI in 0 until outerSegments) {
                val outerCurAngle = getAngle(outerI, outerSegments)
                val outerNextAngle = getNextAngle(outerI, outerSegments)
                val color = if (!isHurt) {
                    colors.getCurrentStepColor(outerCurAngle)
                } else {
                    Color4b(255, 0, 0, colors.firstColor.a)
                }

                for (innerI in 0 until innerSegments) {
                    addTorusQuad(
                        matrix,
                        innerSegments,
                        outerCurAngle,
                        outerNextAngle,
                        HatHaloSettings.outerRadius,
                        HatHaloSettings.outerRadius,
                        HatHaloSettings.innerRadius,
                        innerI,
                        color
                    )
                }
            }
        }
    }
}

private object HatsOrbs : HatsMode("Orbs") {
    private val color by color("Color", Color4b(0, 0, 255, 125))

    private object HatOrbsSettings : Configurable("HatSettings") {
        val radius by float("Radius", 0.5f, 0f..2f)
        val speed by float("Speed", 0.5f, 0.1f..10f)
        val size by float("OrbsSize", 0.1f, 0.01f..0.5f)
        val count by int("OrbsCount", 6, 1..12)
        val wave by boolean("Wave", true)
        val waveHeight by float("WaveHeight", 0.1f, 0.01f..1f)
        val waveSpeed by float("WaveSpeed", 2.0f, 0.1f..10f)
        val spinSpeed by float("SpinSpeed", 2f, -10f..10f)
    }

    init {
        tree(HatOrbsSettings)
    }

    override fun WorldRenderEnvironment.drawHat(isHurt: Boolean) {
        drawCustomMesh(TRIANGLES, DefaultVertexFormat.POSITION_COLOR, ShaderProgramKeys.POSITION_COLOR) { matrix ->
            val time = ((System.currentTimeMillis() % 1000000L).toFloat() / 1000f) * HatOrbsSettings.speed

            for (i in 0 until HatOrbsSettings.count) {
                val angle = getAngle(i, HatOrbsSettings.count) + time
                val x = sin(angle) * HatOrbsSettings.radius
                val z = cos(angle) * HatOrbsSettings.radius
                val y = if (HatOrbsSettings.wave) {
                    sin(time * HatOrbsSettings.waveSpeed + i) * HatOrbsSettings.waveHeight
                } else {
                    0f
                }
                val rotAngle = getRotationAngle(HatOrbsSettings.spinSpeed)
                val sinA = sin(rotAngle) * HatOrbsSettings.size
                val cosA = cos(rotAngle) * HatOrbsSettings.size
                val top = y + HatOrbsSettings.size
                val bottom = y - HatOrbsSettings.size
                val ax = x + sinA
                val az = z + cosA
                val bx = x + cosA
                val bz = z - sinA
                val cx = x - sinA
                val cz = z - cosA
                val dx = x - cosA
                val dz = z + sinA
                val renderColor = if (!isHurt) color else Color4b(255, 0, 0, color.a)

                addOrbVertex(matrix, x, top, z, renderColor)
                addOrbVertex(matrix, dx, y, dz, renderColor)
                addOrbVertex(matrix, ax, y, az, renderColor)
                addOrbVertex(matrix, x, top, z, renderColor)
                addOrbVertex(matrix, ax, y, az, renderColor)
                addOrbVertex(matrix, bx, y, bz, renderColor)
                addOrbVertex(matrix, x, top, z, renderColor)
                addOrbVertex(matrix, bx, y, bz, renderColor)
                addOrbVertex(matrix, cx, y, cz, renderColor)
                addOrbVertex(matrix, x, top, z, renderColor)
                addOrbVertex(matrix, cx, y, cz, renderColor)
                addOrbVertex(matrix, dx, y, dz, renderColor)

                addOrbVertex(matrix, x, bottom, z, renderColor)
                addOrbVertex(matrix, dx, y, dz, renderColor)
                addOrbVertex(matrix, ax, y, az, renderColor)
                addOrbVertex(matrix, x, bottom, z, renderColor)
                addOrbVertex(matrix, ax, y, az, renderColor)
                addOrbVertex(matrix, bx, y, bz, renderColor)
                addOrbVertex(matrix, x, bottom, z, renderColor)
                addOrbVertex(matrix, bx, y, bz, renderColor)
                addOrbVertex(matrix, cx, y, cz, renderColor)
                addOrbVertex(matrix, x, bottom, z, renderColor)
                addOrbVertex(matrix, cx, y, cz, renderColor)
                addOrbVertex(matrix, dx, y, dz, renderColor)
            }
        }
    }

    private fun BufferBuilder.addOrbVertex(matrix: Matrix4f, x: Float, y: Float, z: Float, color: Color4b) {
        vertex(matrix, x, y, z).setColor(color.toARGB())
    }
}

private object HatsFlower : HatsMode("Flower") {
    private val colors = HatsColorSettings()

    private object HatFlowerSettings : Configurable("HatSettings") {
        val outerRadius by float("Radius", 0.3f, 0.1f..2f)
        val innerRadius by float("Thickness", 0.05f, 0.01f..1f)
        val sharpness by float("Sharpness", 0.6f, 0.1f..0.9f)
        val petalCount by int("PetalCount", 5, 5..15)
        val spinSpeed by float("SpinSpeed", 1f, -10f..10f)
    }

    init {
        tree(HatFlowerSettings)
        tree(colors)
    }

    override fun WorldRenderEnvironment.drawHat(isHurt: Boolean) {
        withHatRotation(getRotationAngle(HatFlowerSettings.spinSpeed)) {
            drawCustomMesh(TRIANGLES, DefaultVertexFormat.POSITION_COLOR, ShaderProgramKeys.POSITION_COLOR) { matrix ->
                val petals = HatFlowerSettings.petalCount
                val outerSegments = petals * 32
                val innerSegments = 12

                for (outerI in 0 until outerSegments) {
                    val outerCurAngle = getAngle(outerI, outerSegments)
                    val outerNextAngle = getNextAngle(outerI, outerSegments)
                    val curRadius = getFlowerRadius(
                        outerCurAngle,
                        HatFlowerSettings.outerRadius,
                        petals,
                        HatFlowerSettings.sharpness
                    )
                    val nextRadius = getFlowerRadius(
                        outerNextAngle,
                        HatFlowerSettings.outerRadius,
                        petals,
                        HatFlowerSettings.sharpness
                    )
                    val color = if (!isHurt) {
                        colors.getCurrentStepColor(outerCurAngle)
                    } else {
                        Color4b(255, 0, 0, colors.firstColor.a)
                    }

                    for (innerI in 0 until innerSegments) {
                        addTorusQuad(
                            matrix,
                            innerSegments,
                            outerCurAngle,
                            outerNextAngle,
                            curRadius,
                            nextRadius,
                            HatFlowerSettings.innerRadius,
                            innerI,
                            color
                        )
                    }
                }
            }
        }
    }

    private fun getFlowerRadius(angle: Float, baseRadius: Float, points: Int, sharpness: Float): Float {
        val innerRadius = baseRadius * sharpness
        val section = PI.toFloat() / points
        val progress = abs(angle % (section * 2f) - section) / section

        return innerRadius + (baseRadius - innerRadius) * (1f - progress)
    }
}

private object HatsStar : HatsMode("Star") {
    private val colors = HatsColorSettings()

    private object HatStarSettings : Configurable("HatSettings") {
        val outerRadius by float("Radius", 0.3f, 0.1f..2f)
        val innerRadius by float("Thickness", 0.05f, 0.01f..1f)
        val sharpness by float("Sharpness", 0.6f, 0.1f..0.7f)
        val pointsCount by int("PointsCount", 5, 5..15)
        val spinSpeed by float("SpinSpeed", 1f, -10f..10f)
    }

    init {
        tree(HatStarSettings)
        tree(colors)
    }

    override fun WorldRenderEnvironment.drawHat(isHurt: Boolean) {
        withHatRotation(getRotationAngle(HatStarSettings.spinSpeed)) {
            drawCustomMesh(TRIANGLES, DefaultVertexFormat.POSITION_COLOR, ShaderProgramKeys.POSITION_COLOR) { matrix ->
                val points = HatStarSettings.pointsCount
                val outerSegments = points * 32
                val innerSegments = 12

                for (mainI in 0 until outerSegments) {
                    val outerCurAngle = getAngle(mainI, outerSegments)
                    val outerNextAngle = getNextAngle(mainI, outerSegments)
                    val curRadius = getStarRadius(
                        outerCurAngle,
                        HatStarSettings.outerRadius,
                        points,
                        HatStarSettings.sharpness,
                        1.75f
                    )
                    val nextRadius = getStarRadius(
                        outerNextAngle,
                        HatStarSettings.outerRadius,
                        points,
                        HatStarSettings.sharpness,
                        1.75f
                    )
                    val color = if (!isHurt) {
                        colors.getCurrentStepColor(outerCurAngle)
                    } else {
                        Color4b(255, 0, 0, colors.firstColor.a)
                    }

                    for (innerI in 0 until innerSegments) {
                        addTorusQuad(
                            matrix,
                            innerSegments,
                            outerCurAngle,
                            outerNextAngle,
                            curRadius,
                            nextRadius,
                            HatStarSettings.innerRadius,
                            innerI,
                            color
                        )
                    }
                }
            }
        }
    }

    private fun getStarRadius(angle: Float, baseRadius: Float, points: Int, sharpness: Float, exponent: Float): Float {
        val section = TWO_PI / points
        val progress = (angle % section) / section
        val distance = abs(progress * 2.0f - 1.0f)
        val linearProgress = 1.0f - distance

        return baseRadius * (1.0f - sharpness + sharpness * linearProgress.pow(exponent))
    }
}

private object HatsImage : HatsMode("Image") {
    private val texture = "particles/star.png".registerAsDynamicImageFromClientResources()
    private val colorModulator by color("ColorModulator", Color4b.WHITE)
    private val scaleX by float("ScaleX", 1f, 0.1f..3f)
    private val scaleY by float("ScaleY", 1f, 0.1f..3f)
    private val spinSpeed by float("SpinSpeed", 1f, -10f..10f)

    override fun WorldRenderEnvironment.drawHat(isHurt: Boolean) {
        RenderSystem.setShaderTexture(0, texture)

        matrixStack.withPush {
            multiply(Quaternionf().rotationX(HALF_PI).rotateZ(getRotationAngle(spinSpeed)))
            scale(scaleX, scaleY, 1f)

            val color = if (!isHurt) {
                colorModulator
            } else {
                Color4b(255, 0, 0, colorModulator.a)
            }.toARGB()

            drawCustomMesh(QUADS, DefaultVertexFormat.POSITION_TEX_COLOR, ShaderProgramKeys.POSITION_TEX_COLOR) { matrix ->
                vertex(matrix, -0.5f, -0.5f, 0.0f).setUv(0.0f, 1.0f).setColor(color)
                vertex(matrix, 0.5f, -0.5f, 0.0f).setUv(1.0f, 1.0f).setColor(color)
                vertex(matrix, 0.5f, 0.5f, 0.0f).setUv(1.0f, 0.0f).setColor(color)
                vertex(matrix, -0.5f, 0.5f, 0.0f).setUv(0.0f, 0.0f).setColor(color)
            }
        }
    }
}

private inline fun PoseStack.withPush(block: PoseStack.() -> Unit) {
    push()
    try {
        block()
    } finally {
        pop()
    }
}

private fun net.ccbluex.liquidbounce.utils.aiming.data.Rotation.toHatQuaternion() =
    Quaternionf().rotationYXZ(PI.toFloat() - Math.toRadians(yaw.toDouble()).toFloat(),
        -Math.toRadians(pitch.toDouble()).toFloat(), 0f)

private const val TWO_PI = 6.2831855f
private const val HALF_PI = 1.5707964f
private const val HEAD_PIVOT_FROM_TOP = 0.32f
private val TRIANGLES = VertexFormat.Mode.TRIANGLES
private val QUADS = VertexFormat.Mode.QUADS
