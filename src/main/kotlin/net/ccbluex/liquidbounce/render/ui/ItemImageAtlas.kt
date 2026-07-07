package net.ccbluex.liquidbounce.render.ui
import net.ccbluex.liquidbounce.utils.entity.*

import com.mojang.blaze3d.ProjectionType
import net.ccbluex.liquidbounce.render.compat.RenderSystem
import net.ccbluex.liquidbounce.common.GlobalFramebuffer
import net.ccbluex.liquidbounce.event.EventListener
import net.ccbluex.liquidbounce.event.events.ResourceReloadEvent
import net.ccbluex.liquidbounce.event.handler
import net.ccbluex.liquidbounce.features.module.MinecraftShortcuts
import net.ccbluex.liquidbounce.utils.client.mc
import net.ccbluex.liquidbounce.utils.kotlin.toTypedArray
import net.ccbluex.liquidbounce.utils.math.Vec2i
import com.mojang.blaze3d.pipeline.RenderTarget
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gl.Framebuffer
import net.minecraft.client.gl.SimpleFramebuffer
import com.mojang.blaze3d.platform.NativeImage
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.core.BlockPos
import org.joml.Matrix4f
import java.awt.image.BufferedImage
import kotlin.math.ceil
import kotlin.math.sqrt

private const val NATIVE_ITEM_SIZE: Int = 16

private class Atlas(
    val map: Map<Item, Pair<Vec2i, Vec2i>>,
    val image: BufferedImage,
    /**
     * Contains aliases. For example `minecraft:blue_wall_banner` -> `minecraft:wall_banner` which is necessary since
     * `minecraft:blue_wall_banner` has no texture.
     */
    val aliasMap: Map<Identifier, Identifier>
)

/**
 *
 */
object ItemImageAtlas : EventListener {

    private var atlas: Atlas? = null

    fun updateAtlas(drawContext: DrawContext) {
        if (this.atlas != null) {
            return
        }

        val renderer = ItemFramebufferRenderer(
            BuiltInRegistries.ITEM.stream().toTypedArray(),
            4
        )

        val items = renderer.render(drawContext)

        val image = renderer.getImage()

        val img = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_ARGB)

        for (x in 0 until image.width) {
            for (y in 0 until image.height) {
                img.setRGB(x, y, image.getPixel(x, y))
            }
        }

        renderer.deleteFramebuffer()

        this.atlas = Atlas(items, img, findAliases())
    }

    private fun findAliases(): Map<Identifier, Identifier> {
        val map = hashMapOf<Identifier, Identifier>()

        BuiltInRegistries.BLOCK.forEach {
            val pickUpState = it.defaultBlockState().getCloneItemStack(mc.level!!, BlockPos.ZERO, false)

            if (pickUpState.item != it.asItem()) {
                val blockId = BuiltInRegistries.BLOCK.getKey(it)
                val itemId = BuiltInRegistries.ITEM.getKey(pickUpState.item)

                map[blockId] = itemId
            }
        }

        return map
    }

    @Suppress("unused")
    private val resourceReloadHandler = handler<ResourceReloadEvent> {
        this.atlas = null
    }

    val isAtlasAvailable
        get() = this.atlas != null

    fun resolveAliasIfPresent(name: Identifier): Identifier {
        return atlas!!.aliasMap[name] ?: return name
    }

    fun getItemImage(item: Item): BufferedImage? {
        val atlas = requireNotNull(this.atlas) { "Atlas is not available yet" }
        val (atlasStart, atlasEnd) = atlas.map[item] ?: return null

        return atlas.image.getSubimage(
            atlasStart.x,
            atlasStart.y,
            atlasEnd.x - atlasStart.x,
            atlasEnd.y - atlasStart.y,
        )!!
    }
}


private class ItemFramebufferRenderer(
    val items: Array<Item>,
    val scale: Int,
): MinecraftShortcuts {
    val itemsPerDimension = ceil(sqrt(items.size.toDouble())).toInt()

    val framebuffer: Framebuffer = run {
        val fb = SimpleFramebuffer(
            NATIVE_ITEM_SIZE * scale * itemsPerDimension,
            NATIVE_ITEM_SIZE * scale * itemsPerDimension,
            true
        )

        fb.setClearColor(0.0f, 0.0f, 0.0f, 0.0f)

        fb
    }

    val itemPixelSizeOnFramebuffer = NATIVE_ITEM_SIZE * scale

    fun render(ctx: DrawContext): Map<Item, Pair<Vec2i, Vec2i>> {
        this.framebuffer.beginWrite(true)

        ctx.matrices.pushPose()

        ctx.matrices.setIdentity()

        ctx.matrices.scale(scale.toFloat(), scale.toFloat(), 1.0f)

        val projectionMatrix = RenderSystem.getProjectionMatrix()

        val matrix4f = Matrix4f().setOrtho(
            0.0f,
            this.framebuffer.textureWidth.toFloat(),
            this.framebuffer.textureHeight.toFloat(),
            0.0f,
            1000.0f,
            21000.0f
        )

        RenderSystem.setProjectionMatrix(matrix4f, ProjectionType.ORTHOGRAPHIC)
        GlobalFramebuffer.push(framebuffer)

        val map = this.items.mapIndexed { idx, item ->
            val from = Vec2i(
                (idx % this.itemsPerDimension) * NATIVE_ITEM_SIZE,
                (idx / this.itemsPerDimension) * NATIVE_ITEM_SIZE
            )

            ctx.drawItem(ItemStack(item), from.x, from.y)

            val fbFrom = Vec2i(from.x * this.scale, from.y * this.scale)
            val fbTo = Vec2i(
                fbFrom.x + this.itemPixelSizeOnFramebuffer,
                fbFrom.y + this.itemPixelSizeOnFramebuffer
            )

            item to (fbFrom to fbTo)
        }.toMap()

        ctx.matrices.popPose()

        GlobalFramebuffer.pop()
        mc.framebuffer.beginWrite(true)

        RenderSystem.setProjectionMatrix(projectionMatrix, ProjectionType.ORTHOGRAPHIC)

        return map
    }

    fun getImage(): NativeImage {
        return NativeImage(this.framebuffer.textureWidth, this.framebuffer.textureHeight, true)
    }

    fun deleteFramebuffer() {
        this.framebuffer.delete()
    }

}
