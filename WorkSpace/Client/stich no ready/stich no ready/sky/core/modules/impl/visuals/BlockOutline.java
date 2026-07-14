package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.optifine.util.TextureUtils;
import org.lwjgl.opengl.GL11;
import sky.core.events.EventRender2D;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.math.AnimationTest;
import sky.core.utils.math.Easing;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.shader.CustomFramebuffer;
import sky.core.utils.render.shader.ShaderUtil;

public class BlockOutline extends Module {
   private static final float WAVE_SPEED = 1.2F;
   private static final float WAVE_FREQUENCY = 1.0F;
   private static final float OUTLINE_WIDTH = 0.1F;
   private static final float GLOW_STRENGTH = 10.0F;
   private static final float FILL_STRENGTH = 0.6F;
   private static final float ALPHA_VALUE = 1.0F;
   private final AnimationTest stretchAnim;
   private BlockPos lastpensibBlock;
   private AxisAlignedBB mirHitb;
   private AxisAlignedBB toWorldBB;
   private final Minecraft mc;
   private CustomFramebuffer maskFramebuffer;

   public BlockOutline() {
      super("Block Overlay", "Шейдерная обводка блока.", Category.Visuals);
      this.stretchAnim = new AnimationTest(Easing.EASE_OUT_CUBIC, 170L);
      this.mc = Minecraft.getInstance();
      this.maskFramebuffer = new CustomFramebuffer(true);
   }

   @EventTarget
   private void renderMask(EventRender3D event) {
      if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.getType() == Type.BLOCK) {
         if (this.mc.objectMouseOver.getType() == Type.BLOCK) {
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult)this.mc.objectMouseOver;
            BlockPos blockPos = blockRayTraceResult.getPos();
            if (blockPos != null && !this.mc.world.isAirBlock(blockPos)) {
               BlockState blockState = this.mc.world.getBlockState(blockPos);
               VoxelShape voxelShape = blockState.getShape(this.mc.world, blockPos);
               AxisAlignedBB currentWorldBB;
               if (voxelShape.isEmpty()) {
                  currentWorldBB = new AxisAlignedBB(blockPos);
               } else {
                  currentWorldBB = voxelShape.getBoundingBox().offset(blockPos);
               }

               float t;
               AxisAlignedBB currentInterp;
               if (this.lastpensibBlock != null && this.mirHitb != null && this.toWorldBB != null) {
                  if (!blockPos.equals(this.lastpensibBlock)) {
                     t = (float)this.stretchAnim.getValue();
                     currentInterp = lerpAABB(this.mirHitb, this.toWorldBB, t);
                     this.lastpensibBlock = blockPos;
                     this.mirHitb = currentInterp;
                     this.toWorldBB = currentWorldBB;
                     this.stretchAnim.setValue(0.0);
                     this.stretchAnim.setDestinationValue(1.0);
                     this.stretchAnim.reset();
                  } else {
                     this.toWorldBB = currentWorldBB;
                  }
               } else {
                  this.lastpensibBlock = blockPos;
                  this.mirHitb = currentWorldBB;
                  this.toWorldBB = currentWorldBB;
                  this.stretchAnim.setValue(1.0);
                  this.stretchAnim.setDestinationValue(1.0);
               }

               this.stretchAnim.run(1.0);
               t = (float)this.stretchAnim.getValue();
               currentInterp = lerpAABB(this.mirHitb, this.toWorldBB, t);
               Vector3d viewPos = this.mc.getRenderManager().info.getProjectedView();
               AxisAlignedBB bb = currentInterp.offset(-viewPos.x, -viewPos.y, -viewPos.z);
               this.maskFramebuffer = CustomFramebuffer.createFramebuffer(this.maskFramebuffer);
               this.maskFramebuffer.setup(true);
               this.drawMaskBox(bb);
               this.maskFramebuffer.unbindFramebuffer();
               this.mc.getFramebuffer().bindFramebuffer(true);
            } else {
               this.lastpensibBlock = null;
               this.mirHitb = null;
               this.toWorldBB = null;
            }
         }

      } else {
         this.lastpensibBlock = null;
         this.mirHitb = null;
         this.toWorldBB = null;
      }
   }

   @EventTarget
   private void renderShader(EventRender2D event) {
      if (this.mc.world != null) {
         if (this.lastpensibBlock != null && this.mirHitb != null && this.toWorldBB != null) {
            if (this.maskFramebuffer != null) {
               int theme = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
               float[] rgb1 = ColorUtil.getColor(theme);
               float[] rgb2 = ColorUtil.getColor(ColorUtil.darken(theme, 0.65F));
               RenderSystem.pushMatrix();
               GlStateManager.enableAlphaTest();
               GlStateManager.alphaFunc(516, 0.0F);
               GlStateManager.enableBlend();
               GlStateManager.blendFuncSeparate(770, 1, 1, 0);
               GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
               RenderSystem.shadeModel(7425);
               ShaderUtil.blockout.useProgram();
               ShaderUtil.blockout.setUniform("texture", 0);
               ShaderUtil.blockout.setUniform("texelSize", 1.0F / (float)this.mc.getMainWindow().getFramebufferWidth(), 1.0F / (float)this.mc.getMainWindow().getFramebufferHeight());
               ShaderUtil.blockout.setUniform("color", rgb1[0], rgb1[1], rgb1[2]);
               ShaderUtil.blockout.setUniform("color2", rgb2[0], rgb2[1], rgb2[2]);
               ShaderUtil.blockout.setUniform("time", (float)(System.currentTimeMillis() % 100000L) / 1000.0F);
               ShaderUtil.blockout.setUniform("speed", 1.2F);
               ShaderUtil.blockout.setUniform("scale", 1.0F);
               ShaderUtil.blockout.setUniform("outline", 0.1F);
               ShaderUtil.blockout.setUniform("glow", 10.0F);
               ShaderUtil.blockout.setUniform("fill", 0.6F);
               ShaderUtil.blockout.setUniform("alpha", 2.0F);
               TextureUtils.bindTexture(this.maskFramebuffer.framebufferTexture);
               CustomFramebuffer.drawQuads();
               ShaderUtil.blockout.unloadProgram();
               RenderSystem.shadeModel(7424);
               GlStateManager.disableBlend();
               GlStateManager.disableAlphaTest();
               RenderSystem.popMatrix();
            }
         }
      }
   }

   private static AxisAlignedBB lerpAABB(AxisAlignedBB a, AxisAlignedBB b, float t) {
      return new AxisAlignedBB(a.minX + (b.minX - a.minX) * (double)t, a.minY + (b.minY - a.minY) * (double)t, a.minZ + (b.minZ - a.minZ) * (double)t, a.maxX + (b.maxX - a.maxX) * (double)t, a.maxY + (b.maxY - a.maxY) * (double)t, a.maxZ + (b.maxZ - a.maxZ) * (double)t);
   }

   private void drawMaskBox(AxisAlignedBB bb) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3553);
      GL11.glDisable(2884);
      GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(255, 255, 255, 255).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(255, 255, 255, 255).endVertex();
      tessellator.draw();
      GL11.glEnable(2884);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }
}
