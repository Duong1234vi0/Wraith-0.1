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
package net.ccbluex.liquidbounce.features.module.modules.render

import com.mojang.blaze3d.vertex.BufferBuilder
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.ccbluex.liquidbounce.config.types.NamedChoice
import net.ccbluex.liquidbounce.event.events.AttackEntityEvent
import net.ccbluex.liquidbounce.event.events.WorldChangeEvent
import net.ccbluex.liquidbounce.event.events.WorldRenderEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.Category
import net.ccbluex.liquidbounce.features.module.ClientModule
import net.ccbluex.liquidbounce.features.module.modules.render.gui.ClientTheme
import net.ccbluex.liquidbounce.render.WorldRenderEnvironment
import net.ccbluex.liquidbounce.render.compat.GlStateManager
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.render.compat.ShaderProgramKeys
import net.ccbluex.liquidbounce.render.drawCustomMesh
import net.ccbluex.liquidbounce.render.renderEnvironmentForWorld
import net.ccbluex.liquidbounce.render.vertex
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.entity.*
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3
import org.joml.Matrix4f
import java.util.Random
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Wyvern-DLC Cubes behavior adapted to Wraith's supported world renderer.
 */
@Suppress("MagicNumber", "TooManyFunctions")
object ModuleCubes : ClientModule("Cubes", Category.RENDER) {

    private val glowTexture = Identifier.fromNamespaceAndPath("liquidbounce", "icons/glow.png")

    private val animation by enumChoice("Animation", Animation.SCATTER)
    private val shape by enumChoice("Shape", Shape.CUBES)
    private val count by int("Count", 30, 5..100)
    private val size by float("Size", 1.0F, 0.1F..3.0F)
    private val speed by float("Speed", 1.0F, 0.1F..5.0F)

    private const val IMPULSE_DEBOUNCE_MS = 75L

    private val GLOW_SCALES = WyvernCubesRules.glowScales
    private val GLOW_ALPHA_SCALES = WyvernCubesRules.glowAlphas

    // 12 cube edges as [x1,y1,z1, x2,y2,z2] in unit (-1..1) space.
    private val CUBE_EDGES = arrayOf(
        byteArrayOf(-1, -1, -1, 1, -1, -1),
        byteArrayOf(1, -1, -1, 1, -1, 1),
        byteArrayOf(1, -1, 1, -1, -1, 1),
        byteArrayOf(-1, -1, 1, -1, -1, -1),
        byteArrayOf(-1, 1, -1, 1, 1, -1),
        byteArrayOf(1, 1, -1, 1, 1, 1),
        byteArrayOf(1, 1, 1, -1, 1, 1),
        byteArrayOf(-1, 1, 1, -1, 1, -1),
        byteArrayOf(-1, -1, -1, -1, 1, -1),
        byteArrayOf(1, -1, -1, 1, 1, -1),
        byteArrayOf(1, -1, 1, 1, 1, 1),
        byteArrayOf(-1, -1, 1, -1, 1, 1)
    )
    // Triangle (pyramid) edges as index pairs into [apex, 4 base corners].
    private val TRIANGLE_EDGES = arrayOf(
        byteArrayOf(0, 1), byteArrayOf(0, 2), byteArrayOf(0, 3), byteArrayOf(0, 4),
        byteArrayOf(1, 2), byteArrayOf(2, 3), byteArrayOf(3, 4), byteArrayOf(4, 1)
    )

    private val cubes = ArrayList<CubeParticle>()
    private val visibleCubes = ArrayList<CubeParticle>()
    private val random = Random()
    private var lastAttackPressed = false
    private var cr = 0.0F
    private var cg = 0.0F
    private var cb = 0.0F
    private var updateCounter = 0
    private var lastImpulseAt = 0L

    override fun enable() {
        cubes.clear()
        visibleCubes.clear()
        updateCounter = 0
        lastAttackPressed = false
    }

    override fun disable() {
        cubes.clear()
        visibleCubes.clear()
    }

    @Suppress("unused")
    private val renderHandler = handler<WorldRenderEvent> { event ->
        val player = mc.player ?: return@handler
        mc.level ?: return@handler

        val attackPressed = mc.options.attackKey.isDown
        if (attackPressed && !lastAttackPressed) {
            applyHitImpulseFromCrosshair()
        }
        lastAttackPressed = attackPressed

        updateCounter++
        if (WyvernCubesRules.shouldUpdate(updateCounter)) {
            updateCubes(player)
        }

        renderEnvironmentForWorld(event.matrixStack) {
            renderCubes(this)
        }
    }

    @Suppress("unused")
    private val attackHandler = handler<AttackEntityEvent> {
        applyHitImpulseFromCrosshair()
    }

    @Suppress("unused")
    private val worldChangeHandler = handler<WorldChangeEvent> {
        cubes.clear()
        visibleCubes.clear()
        updateCounter = 0
        lastAttackPressed = false
    }

    private fun applyHitImpulseFromCrosshair() {
        if (cubes.isEmpty()) {
            return
        }
        val now = System.currentTimeMillis()
        if (now - lastImpulseAt < IMPULSE_DEBOUNCE_MS) {
            return
        }

        val camera = mc.gameRenderer.camera
        val origin = camera.position()
        val look = org.joml.Vector3f(0.0F, 0.0F, -1.0F).rotate(camera.rotation)
        val dirX = look.x.toDouble()
        val dirY = look.y.toDouble()
        val dirZ = look.z.toDouble()

        var best: CubeParticle? = null
        var bestT = Double.MAX_VALUE
        for (p in cubes) {
            val opX = p.x - origin.x
            val opY = p.y - origin.y
            val opZ = p.z - origin.z
            val t = opX * dirX + opY * dirY + opZ * dirZ
            if (t < 0.0 || t > WyvernCubesRules.RAY_RANGE) {
                continue
            }
            val cx = origin.x + dirX * t
            val cy = origin.y + dirY * t
            val cz = origin.z + dirZ * t
            val dx = p.x - cx
            val dy = p.y - cy
            val dz = p.z - cz
            val distSq = dx * dx + dy * dy + dz * dz
            if (distSq > WyvernCubesRules.RAY_DISTANCE_SQ || t >= bestT) {
                continue
            }
            bestT = t
            best = p
        }

        best?.let { p ->
            lastImpulseAt = now
            val force = WyvernCubesRules.IMPULSE_FORCE * speed
            p.vx = (p.vx + dirX * force).toFloat()
            p.vy = (p.vy + (dirY * force + WyvernCubesRules.IMPULSE_VERTICAL)).toFloat()
            p.vz = (p.vz + dirZ * force).toFloat()
        }
    }

    private fun updateCubes(player: LivingEntity) {
        val target = count
        val currentSize = cubes.size
        if (currentSize < target) {
            val toAdd = minOf(target - currentSize, 5)
            repeat(toAdd) { cubes.add(spawnCube(player)) }
        } else if (currentSize > target) {
            cubes.subList(target, currentSize).clear()
        }

        val spd = WyvernCubesRules.BASE_SPEED * speed
        val falling = animation == Animation.FALLING
        val playerPos = player.position()
        val maxRSq = WyvernCubesRules.SPAWN_RADIUS * WyvernCubesRules.SPAWN_RADIUS * 6.25

        for (i in cubes.indices.reversed()) {
            val p = cubes[i]
            if (falling) {
                p.wobblePhase += 0.06F * spd
                p.x += p.vx * spd + sin((p.wobblePhase + p.wobbleOffset).toDouble()) * 0.0024F * spd
                p.y += p.vy * spd
                p.z += p.vz * spd + cos((p.wobblePhase * 0.8F + p.wobbleOffset).toDouble()) * 0.002F * spd
                p.vy = maxOf(p.vy - 8.0E-5F * spd, -0.032F)
                p.rotX += p.rotSpeedX * 0.2F * spd
                p.rotY += p.rotSpeedY * 0.2F * spd
                p.rotZ += p.rotSpeedZ * 0.2F * spd
            } else {
                p.x += p.vx * spd
                p.y += p.vy * spd
                p.z += p.vz * spd
                p.rotX += p.rotSpeedX * spd
                p.rotY += p.rotSpeedY * spd
                p.rotZ += p.rotSpeedZ * spd
                p.vx *= 0.995F
                p.vy *= 0.995F
                p.vz *= 0.995F
            }

            p.life--
            val dx = p.x - playerPos.x
            val dy = p.y - playerPos.y
            val dz = p.z - playerPos.z
            val distSq = dx * dx + dy * dy + dz * dz
            if (p.life <= 0 || distSq > maxRSq || (falling && p.y < playerPos.y - 2.5)) {
                cubes.removeAt(i)
                cubes.add(spawnCube(player))
            }
        }
    }

    private fun renderCubes(env: WorldRenderEnvironment) {
        val cam = env.camera.position()
        val camera = mc.gameRenderer.camera
        val s = WyvernCubesRules.BASE_SIZE * size
        updateThemeColor()
        prepareVisibleCubes(cam, camera)

        if (visibleCubes.isEmpty()) {
            return
        }

        RenderSystem.enableBlend()
        RenderSystem.disableCull()
        RenderSystem.disableDepthTest()
        RenderSystem.depthMask(false)

        try {
            // Pass 1: Wyvern glow billboards (additive).
            RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
            RenderSystem.enableAdditive()
            RenderSystem.setShaderTexture(0, glowTexture)
            drawGlowBatch(env, s)

            // Pass 2: faces.
            val isCubes = shape == Shape.CUBES
            RenderSystem.defaultBlendFunc()
            RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA)
            if (isCubes) {
                drawCubeFaces(env, s)
            } else {
                drawTriangleFaces(env, s)
            }

            // Pass 3: dashed wireframe edges (additive).
            RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE)
            RenderSystem.enableAdditive()
            if (isCubes) {
                drawCubeDashedEdges(env, s)
            } else {
                drawTriangleDashedEdges(env, s)
            }
        } finally {
            RenderSystem.depthMask(true)
            RenderSystem.enableDepthTest()
            RenderSystem.defaultBlendFunc()
            RenderSystem.enableCull()
        }
    }

    private fun updateThemeColor() {
        val color = ClientTheme.accentA
        cr = ((color ushr 16) and 0xFF) / 255.0F
        cg = ((color ushr 8) and 0xFF) / 255.0F
        cb = (color and 0xFF) / 255.0F
    }

    private fun prepareVisibleCubes(cam: Vec3, camera: net.minecraft.client.Camera) {
        visibleCubes.clear()
        val look = org.joml.Vector3f(0.0F, 0.0F, -1.0F).rotate(camera.rotation)
        val lookX = look.x.toDouble()
        val lookY = look.y.toDouble()
        val lookZ = look.z.toDouble()

        for (p in cubes) {
            val dx = p.x - cam.x
            val dy = p.y - cam.y
            val dz = p.z - cam.z
            val distSq = dx * dx + dy * dy + dz * dz
            if (!WyvernCubesRules.isVisible(distSq, dx * lookX + dy * lookY + dz * lookZ)) {
                continue
            }
            p.renderAlpha = alphaOf(p)
            if (p.renderAlpha >= 0.01F) {
                visibleCubes.add(p)
            }
        }
    }

    private fun drawGlowBatch(env: WorldRenderEnvironment, s: Float) {
        val cam = env.camera.position()
        val rotation = mc.gameRenderer.camera.rotation
        val right = org.joml.Vector3f(1.0F, 0.0F, 0.0F).rotate(rotation)
        val up = org.joml.Vector3f(0.0F, 1.0F, 0.0F).rotate(rotation)

        env.drawCustomMesh(
            VertexFormat.Mode.QUADS,
            DefaultVertexFormat.POSITION_TEX_COLOR,
            ShaderProgramKeys.POSITION_TEX_COLOR
        ) { matrix ->
            for (p in visibleCubes) {
                val px = (p.x - cam.x).toFloat()
                val py = (p.y - cam.y).toFloat()
                val pz = (p.z - cam.z).toFloat()
                for (i in 0 until 3) {
                    val scale = s * GLOW_SCALES[i] * WyvernCubesRules.GLOW_MULTIPLIER
                    val a = (p.renderAlpha * GLOW_ALPHA_SCALES[i] * WyvernCubesRules.GLOW_MULTIPLIER)
                        .coerceIn(0.0F, 1.0F)
                    val argb = colorToInt(cr, cg, cb, a)
                    val hs = scale * 0.5F
                    val rx = right.x * hs
                    val ry = right.y * hs
                    val rz = right.z * hs
                    val ux = up.x * hs
                    val uy = up.y * hs
                    val uz = up.z * hs
                    vertex(matrix, px - rx + ux, py - ry + uy, pz - rz + uz).setUv(0.0F, 1.0F).setColor(argb)
                    vertex(matrix, px + rx + ux, py + ry + uy, pz + rz + uz).setUv(1.0F, 1.0F).setColor(argb)
                    vertex(matrix, px + rx - ux, py + ry - uy, pz + rz - uz).setUv(1.0F, 0.0F).setColor(argb)
                    vertex(matrix, px - rx - ux, py - ry - uy, pz - rz - uz).setUv(0.0F, 0.0F).setColor(argb)
                }
            }
        }
    }

    private fun alphaOf(p: CubeParticle): Float {
        return WyvernCubesRules.alpha(p.life, p.maxLife)
    }

    private fun drawCubeFaces(env: WorldRenderEnvironment, s: Float) {
        if (visibleCubes.none { it.renderAlpha >= 0.025F }) {
            return
        }
        val cam = env.camera.position()
        env.drawCustomMesh(
            VertexFormat.Mode.QUADS,
            DefaultVertexFormat.POSITION_COLOR,
            ShaderProgramKeys.POSITION_COLOR
        ) { matrix ->
            for (p in visibleCubes) {
                val a = p.renderAlpha * 0.4F
                if (a < 0.01F) {
                    continue
                }
                val m = p.localMatrix(matrix, cam)
                appendCubeFaces(this, m, s, colorToInt(cr, cg, cb, a))
            }
        }
    }

    private fun drawTriangleFaces(env: WorldRenderEnvironment, s: Float) {
        if (visibleCubes.none { it.renderAlpha >= 0.025F }) {
            return
        }
        val cam = env.camera.position()
        env.drawCustomMesh(
            VertexFormat.Mode.TRIANGLES,
            DefaultVertexFormat.POSITION_COLOR,
            ShaderProgramKeys.POSITION_COLOR
        ) { matrix ->
            for (p in visibleCubes) {
                val a = p.renderAlpha * 0.4F
                if (a < 0.01F) {
                    continue
                }
                val m = p.localMatrix(matrix, cam)
                appendTriangleFaces(this, m, s, colorToInt(cr, cg, cb, a))
            }
        }
    }

    private fun drawCubeDashedEdges(env: WorldRenderEnvironment, s: Float) {
        val cam = env.camera.position()
        env.drawCustomMesh(
            VertexFormat.Mode.DEBUG_LINES,
            DefaultVertexFormat.POSITION_COLOR,
            ShaderProgramKeys.POSITION_COLOR
        ) { matrix ->
            for (p in visibleCubes) {
                val m = p.localMatrix(matrix, cam)
                appendCubeDashedEdges(this, m, s, p.renderAlpha)
            }
        }
    }

    private fun drawTriangleDashedEdges(env: WorldRenderEnvironment, s: Float) {
        val cam = env.camera.position()
        env.drawCustomMesh(
            VertexFormat.Mode.DEBUG_LINES,
            DefaultVertexFormat.POSITION_COLOR,
            ShaderProgramKeys.POSITION_COLOR
        ) { matrix ->
            for (p in visibleCubes) {
                val m = p.localMatrix(matrix, cam)
                appendTriangleDashedEdges(this, m, s, p.renderAlpha)
            }
        }
    }

    private fun appendCubeFaces(buffer: BufferBuilder, m: Matrix4f, s: Float, color: Int) {
        // 6 faces, 4 verts each (QUADS).
        val v = arrayOf(
            // front (+z)
            floatArrayOf(-s, -s, s), floatArrayOf(s, -s, s), floatArrayOf(s, s, s), floatArrayOf(-s, s, s),
            // back (-z)
            floatArrayOf(s, -s, -s), floatArrayOf(-s, -s, -s), floatArrayOf(-s, s, -s), floatArrayOf(s, s, -s),
            // top (+y)
            floatArrayOf(-s, s, s), floatArrayOf(s, s, s), floatArrayOf(s, s, -s), floatArrayOf(-s, s, -s),
            // bottom (-y)
            floatArrayOf(-s, -s, -s), floatArrayOf(s, -s, -s), floatArrayOf(s, -s, s), floatArrayOf(-s, -s, s),
            // right (+x)
            floatArrayOf(s, -s, s), floatArrayOf(s, -s, -s), floatArrayOf(s, s, -s), floatArrayOf(s, s, s),
            // left (-x)
            floatArrayOf(-s, -s, -s), floatArrayOf(-s, -s, s), floatArrayOf(-s, s, s), floatArrayOf(-s, s, -s)
        )
        for (p in v) {
            buffer.vertex(m, p[0], p[1], p[2]).setColor(color)
        }
    }

    private fun appendTriangleFaces(buffer: BufferBuilder, m: Matrix4f, s: Float, color: Int) {
        val bottom = -s
        val halfBase = s * 0.866F
        val apex = floatArrayOf(0.0F, s, 0.0F)
        val c1 = floatArrayOf(-halfBase, bottom, halfBase)
        val c2 = floatArrayOf(halfBase, bottom, halfBase)
        val c3 = floatArrayOf(halfBase, bottom, -halfBase)
        val c4 = floatArrayOf(-halfBase, bottom, -halfBase)
        // 4 side triangles + 2 base triangles.
        val tris = arrayOf(
            apex, c1, c2,
            apex, c2, c3,
            apex, c3, c4,
            apex, c4, c1,
            c1, c2, c3,
            c1, c3, c4
        )
        for (p in tris) {
            buffer.vertex(m, p[0], p[1], p[2]).setColor(color)
        }
    }

    private fun appendCubeDashedEdges(buffer: BufferBuilder, m: Matrix4f, s: Float, alpha: Float) {
        val color = colorToInt(minOf(1.0F, cr * 1.5F), minOf(1.0F, cg * 1.5F), minOf(1.0F, cb * 1.5F), alpha)
        val dashLen = s * 0.3F
        val gapLen = s * 0.25F
        for (edge in CUBE_EDGES) {
            dashedSegment(
                buffer, m, color, dashLen, gapLen,
                edge[0] * s, edge[1] * s, edge[2] * s,
                edge[3] * s, edge[4] * s, edge[5] * s
            )
        }
    }

    private fun appendTriangleDashedEdges(buffer: BufferBuilder, m: Matrix4f, s: Float, alpha: Float) {
        val color = colorToInt(minOf(1.0F, cr * 1.5F), minOf(1.0F, cg * 1.5F), minOf(1.0F, cb * 1.5F), alpha)
        val dashLen = s * 0.3F
        val gapLen = s * 0.25F
        val top = s
        val bottom = -s
        val halfBase = s * 0.866F
        for (edge in TRIANGLE_EDGES) {
            val x1 = trianglePointX(edge[0].toInt(), halfBase)
            val y1 = if (edge[0].toInt() == 0) top else bottom
            val z1 = trianglePointZ(edge[0].toInt(), halfBase)
            val x2 = trianglePointX(edge[1].toInt(), halfBase)
            val y2 = if (edge[1].toInt() == 0) top else bottom
            val z2 = trianglePointZ(edge[1].toInt(), halfBase)
            dashedSegment(buffer, m, color, dashLen, gapLen, x1, y1, z1, x2, y2, z2)
        }
    }

    @Suppress("LongParameterList")
    private fun dashedSegment(
        buffer: BufferBuilder, m: Matrix4f, color: Int, dashLen: Float, gapLen: Float,
        x1: Float, y1: Float, z1: Float, x2: Float, y2: Float, z2: Float
    ) {
        val dx = x2 - x1
        val dy = y2 - y1
        val dz = z2 - z1
        val len = sqrt(dx * dx + dy * dy + dz * dz)
        if (len < 0.001F) {
            return
        }
        val nx = dx / len
        val ny = dy / len
        val nz = dz / len
        for (interval in WyvernCubesRules.dashedIntervals(len, dashLen, gapLen)) {
            buffer.vertex(m, x1 + nx * interval.start, y1 + ny * interval.start, z1 + nz * interval.start)
                .setColor(color)
            buffer.vertex(m, x1 + nx * interval.end, y1 + ny * interval.end, z1 + nz * interval.end)
                .setColor(color)
        }
    }

    private fun trianglePointX(index: Int, halfBase: Float): Float = when (index) {
        1, 4 -> -halfBase
        2, 3 -> halfBase
        else -> 0.0F
    }

    private fun trianglePointZ(index: Int, halfBase: Float): Float = when (index) {
        1, 2 -> halfBase
        3, 4 -> -halfBase
        else -> 0.0F
    }

    private fun spawnCube(player: LivingEntity): CubeParticle {
        val r = WyvernCubesRules.SPAWN_RADIUS
        val falling = animation == Animation.FALLING
        val baseLife = if (falling) 260 + random.nextInt(220) else 420 + random.nextInt(420)
        val life = baseLife
        val x = player.x + (random.nextDouble() * 2.0 - 1.0) * r
        val y = if (falling) {
            player.y + 4.0 + random.nextDouble() * (r * 0.55)
        } else {
            player.y + 2.0 + random.nextDouble() * (r * 0.8)
        }
        val z = player.z + (random.nextDouble() * 2.0 - 1.0) * r
        val speedMult = speed
        val vx: Float
        val vy: Float
        val vz: Float
        if (falling) {
            vx = (random.nextFloat() - 0.5F) * 0.008F * speedMult
            vy = (-0.012F - random.nextFloat() * 0.012F) * speedMult
            vz = (random.nextFloat() - 0.5F) * 0.008F * speedMult
        } else {
            val yaw = random.nextFloat() * 360.0F
            val vel = (0.01F + random.nextFloat() * 0.02F) * speedMult
            vx = -sin(Math.toRadians(yaw.toDouble())).toFloat() * vel
            vz = cos(Math.toRadians(yaw.toDouble())).toFloat() * vel
            vy = (random.nextFloat() - 0.5F) * 0.01F * speedMult
        }

        return CubeParticle(
            x, y, z, vx, vy, vz,
            random.nextFloat() * 360.0F,
            random.nextFloat() * 360.0F,
            random.nextFloat() * 360.0F,
            (random.nextFloat() - 0.5F) * 1.5F,
            (random.nextFloat() - 0.5F) * 1.5F,
            (random.nextFloat() - 0.5F) * 1.5F,
            life,
            (random.nextDouble() * Math.PI * 2.0).toFloat(),
            random.nextFloat() * 10.0F
        )
    }

    private fun colorToInt(r: Float, g: Float, b: Float, a: Float): Int =
        ((a * 255.0F).toInt() shl 24) or ((r * 255.0F).toInt() shl 16) or
            ((g * 255.0F).toInt() shl 8) or (b * 255.0F).toInt()

    private class CubeParticle(
        var x: Double,
        var y: Double,
        var z: Double,
        var vx: Float,
        var vy: Float,
        var vz: Float,
        var rotX: Float,
        var rotY: Float,
        var rotZ: Float,
        val rotSpeedX: Float,
        val rotSpeedY: Float,
        val rotSpeedZ: Float,
        life: Int,
        var wobblePhase: Float,
        val wobbleOffset: Float
    ) {
        var life: Int = life
        val maxLife: Int = life
        var renderAlpha: Float = 0.0F

        /** Camera-relative local matrix with this particle's translate + XYZ rotation. */
        fun localMatrix(base: Matrix4f, cam: Vec3): Matrix4f {
            val m = Matrix4f(base)
            m.translate((x - cam.x).toFloat(), (y - cam.y).toFloat(), (z - cam.z).toFloat())
            m.rotate(Math.toRadians(rotX.toDouble()).toFloat(), 1.0F, 0.0F, 0.0F)
            m.rotate(Math.toRadians(rotY.toDouble()).toFloat(), 0.0F, 1.0F, 0.0F)
            m.rotate(Math.toRadians(rotZ.toDouble()).toFloat(), 0.0F, 0.0F, 1.0F)
            return m
        }
    }

    @Suppress("unused")
    private enum class Animation(override val choiceName: String) : NamedChoice {
        SCATTER("Scatter"),
        FALLING("Falling")
    }

    @Suppress("unused")
    private enum class Shape(override val choiceName: String) : NamedChoice {
        CUBES("Cubes"),
        TRIANGLES("Triangles")
    }
}
