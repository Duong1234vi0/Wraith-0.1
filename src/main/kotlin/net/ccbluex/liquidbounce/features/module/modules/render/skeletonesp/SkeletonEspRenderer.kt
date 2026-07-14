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

import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.math.Axis
import net.ccbluex.liquidbounce.features.module.modules.render.alphadlc.AlphaProjection
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKeys
import net.ccbluex.liquidbounce.render.drawCustomMesh
import net.ccbluex.liquidbounce.render.engine.type.Color4b
import net.ccbluex.liquidbounce.render.vertex
import net.minecraft.util.Mth
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.Vec3
import org.joml.Vector3f
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

object SkeletonEspRenderer {

    private const val MAX_BONES_PER_PLAYER = 15
    private const val MAX_BONES = 960
    private const val RAD_TO_DEG = 57.2958f

    private val transformed = Vector3f()
    private val bones = ArrayList<Bone>(MAX_BONES)

    fun clear() {
        bones.clear()
    }

    fun render(
        env: WorldRenderEnvironment,
        players: List<Player>,
        cameraPos: Vec3,
        tickDelta: Float,
        lineWidth: Float,
        color: Color4b
    ) {
        bones.clear()

        for (player in players) {
            if (bones.size + MAX_BONES_PER_PLAYER > MAX_BONES) {
                break
            }
            if (player.isRemoved || !player.isAlive || player.isDeadOrDying) {
                continue
            }
            if (!AlphaProjection.isWithinRenderDistance(player) || !AlphaProjection.canSee(player.boundingBox)) {
                continue
            }

            collectBones(player, tickDelta)
        }

        if (bones.isEmpty()) {
            return
        }

        try {
            RenderSystem.enableBlend()
            RenderSystem.defaultBlendFunc()
            RenderSystem.disableDepthTest()
            RenderSystem.disableCull()
            RenderSystem.depthMask(false)
            RenderSystem.lineWidth(lineWidth)
            env.drawCustomMesh(
                VertexFormat.Mode.DEBUG_LINES,
                DefaultVertexFormat.POSITION_COLOR,
                ShaderProgramKeys.POSITION_COLOR
            ) { matrix ->
                val argb = color.toARGB()
                for (bone in bones) {
                    this.vertex(
                        matrix,
                        (bone.start.x - cameraPos.x).toFloat(),
                        (bone.start.y - cameraPos.y).toFloat(),
                        (bone.start.z - cameraPos.z).toFloat()
                    ).setColor(argb)
                    this.vertex(
                        matrix,
                        (bone.end.x - cameraPos.x).toFloat(),
                        (bone.end.y - cameraPos.y).toFloat(),
                        (bone.end.z - cameraPos.z).toFloat()
                    ).setColor(argb)
                }
            }
        } finally {
            RenderSystem.lineWidth(1.0f)
            RenderSystem.depthMask(true)
            RenderSystem.enableCull()
            RenderSystem.enableDepthTest()
            RenderSystem.defaultBlendFunc()
            RenderSystem.disableBlend()
        }
    }

    private fun collectBones(player: Player, tickDelta: Float) {
        val x = Mth.lerp(tickDelta.toDouble(), player.xo, player.x)
        val y = Mth.lerp(tickDelta.toDouble(), player.yo, player.y)
        val z = Mth.lerp(tickDelta.toDouble(), player.zo, player.z)
        val bodyYaw = Mth.rotLerp(tickDelta, player.yBodyRotO, player.yBodyRot)
        val headYaw = Mth.rotLerp(tickDelta, player.yHeadRotO, player.yHeadRot)
        val pitch = Mth.lerp(tickDelta, player.xRotO, player.xRot)
        val walkPosition = player.walkAnimation.position(tickDelta)
        val walkSpeed = min(player.walkAnimation.speed(tickDelta), 1.0f) * 0.5f
        val attackSwing = player.getAttackAnim(tickDelta)
        val height = player.bbHeight
        val fallFlying = player.isFallFlying
        val crouching = player.isCrouching

        val stack = PoseStack()
        stack.translate(x, y, z)
        if (crouching && !fallFlying) {
            stack.translate(0.0, 0.125, 0.0)
        }
        stack.mulPose(Axis.YP.rotationDegrees(-bodyYaw))

        val bodyPitch = when {
            fallFlying -> 1.57f + pitch / RAD_TO_DEG
            crouching -> 0.5f
            else -> 0.0f
        }
        if (fallFlying || crouching) {
            stack.mulPose(Axis.XP.rotationDegrees(bodyPitch * RAD_TO_DEG))
        }
        if (crouching && !fallFlying) {
            stack.translate(0.0, -0.13, 0.0)
        }

        stack.pushPose()
        stack.translate(0.0, height * 0.75, 0.0)
        val neck = stack.origin()

        stack.pushPose()
        stack.mulPose(Axis.YP.rotationDegrees(bodyYaw - headYaw))
        if (!fallFlying) {
            stack.mulPose(Axis.XP.rotationDegrees(pitch))
        }
        stack.translate(0.0, height * 0.15, 0.0)
        val head = stack.origin()
        stack.popPose()

        stack.pushPose()
        stack.translate(0.25, 0.0, 0.0)
        val leftShoulder = stack.origin()
        var leftArmRot = if (fallFlying) {
            -0.2f
        } else {
            cos(walkPosition * 0.6662f + Math.PI.toFloat()) * 0.8f * walkSpeed
        }
        if (fallFlying) {
            stack.mulPose(Axis.ZP.rotationDegrees(-5.0f))
        }
        stack.mulPose(Axis.XP.rotationDegrees(leftArmRot * RAD_TO_DEG))
        stack.translate(0.0, -0.25, 0.0)
        val leftElbow = stack.origin()
        stack.mulPose(Axis.XP.rotationDegrees(max(0.0f, leftArmRot * 15.0f)))
        stack.translate(0.0, -0.25, 0.0)
        val leftHand = stack.origin()
        stack.popPose()

        stack.pushPose()
        stack.translate(-0.25, 0.0, 0.0)
        val rightShoulder = stack.origin()
        var rightArmRot = if (fallFlying) {
            -0.2f
        } else {
            cos(walkPosition * 0.6662f) * 0.8f * walkSpeed
        }
        if (fallFlying) {
            stack.mulPose(Axis.ZP.rotationDegrees(5.0f))
        }
        if (attackSwing > 0.0f && !fallFlying) {
            var swingProgress = 1.0f - attackSwing
            swingProgress *= swingProgress
            val swingRot = sin(swingProgress * Math.PI.toFloat())
            val yawFactor = Mth.clamp((headYaw - bodyYaw) / 75.0f, -1.0f, 1.0f)
            stack.mulPose(Axis.YP.rotationDegrees(swingRot * 15.0f * yawFactor))
            rightArmRot += -swingRot * 0.8f
        }
        stack.mulPose(Axis.XP.rotationDegrees(rightArmRot * RAD_TO_DEG))
        stack.translate(0.0, -0.25, 0.0)
        val rightElbow = stack.origin()
        stack.mulPose(Axis.XP.rotationDegrees(max(0.0f, rightArmRot * 15.0f)))
        stack.translate(0.0, -0.25, 0.0)
        val rightHand = stack.origin()
        stack.popPose()

        stack.popPose()

        stack.pushPose()
        stack.translate(0.0, height * 0.5, 0.0)
        val waist = stack.origin()
        stack.popPose()

        stack.pushPose()
        stack.translate(0.0, height * 0.3, 0.0)
        val pelvis = stack.origin()

        stack.pushPose()
        stack.translate(0.125, 0.0, 0.0)
        val leftHip = stack.origin()
        val leftLegRot = if (fallFlying) {
            0.1f
        } else {
            cos(walkPosition * 0.6662f) * 0.5f * walkSpeed
        }
        stack.mulPose(Axis.XP.rotationDegrees(leftLegRot * RAD_TO_DEG))
        stack.translate(0.0, -0.25, 0.0)
        val leftKnee = stack.origin()
        stack.mulPose(Axis.XP.rotationDegrees(abs(leftLegRot) * 15.0f))
        stack.translate(0.0, -0.25, 0.0)
        val leftFoot = stack.origin()
        stack.popPose()

        stack.pushPose()
        stack.translate(-0.125, 0.0, 0.0)
        val rightHip = stack.origin()
        val rightLegRot = if (fallFlying) {
            0.1f
        } else {
            cos(walkPosition * 0.6662f + Math.PI.toFloat()) * 0.5f * walkSpeed
        }
        stack.mulPose(Axis.XP.rotationDegrees(rightLegRot * RAD_TO_DEG))
        stack.translate(0.0, -0.25, 0.0)
        val rightKnee = stack.origin()
        stack.mulPose(Axis.XP.rotationDegrees(abs(rightLegRot) * 15.0f))
        stack.translate(0.0, -0.25, 0.0)
        val rightFoot = stack.origin()
        stack.popPose()
        stack.popPose()

        addBone(neck, head)
        addBone(neck, waist)
        addBone(waist, pelvis)
        addBone(neck, leftShoulder)
        addBone(neck, rightShoulder)
        addBone(leftShoulder, leftElbow)
        addBone(leftElbow, leftHand)
        addBone(rightShoulder, rightElbow)
        addBone(rightElbow, rightHand)
        addBone(pelvis, leftHip)
        addBone(pelvis, rightHip)
        addBone(leftHip, leftKnee)
        addBone(leftKnee, leftFoot)
        addBone(rightHip, rightKnee)
        addBone(rightKnee, rightFoot)
    }

    private fun addBone(start: Vec3, end: Vec3) {
        if (bones.size >= MAX_BONES) {
            return
        }
        bones += Bone(start, end)
    }

    private fun PoseStack.origin(): Vec3 {
        last().pose().transformPosition(0.0f, 0.0f, 0.0f, transformed)
        return Vec3(transformed.x.toDouble(), transformed.y.toDouble(), transformed.z.toDouble())
    }

    private data class Bone(val start: Vec3, val end: Vec3)
}
