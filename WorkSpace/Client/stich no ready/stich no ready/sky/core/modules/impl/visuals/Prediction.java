package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.text.DecimalFormat;
import java.util.Iterator;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;
import sky.core.events.EventRender2D;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.ProjectUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class Prediction extends Module implements Wrapper {
   private final MultiBooleanSetting projectiles = new MultiBooleanSetting("Снаряды", new BooleanSetting[]{new BooleanSetting("Эндер Пёрл", true), new BooleanSetting("Стрела", true), new BooleanSetting("Трезубец", true)});
   private static final DecimalFormat TIME_FORMAT = new DecimalFormat("0.0");

   public Prediction() {
      super("Prediction", "Предугадывает куда и за сколько времени упадет предмет", Category.Visuals);
      this.addSettings(new Setting[]{this.projectiles});
   }

   @EventTarget
   public void onEvent(EventRender2D event) {
      int rectBgColor = ColorUtil.getColor(0, 0, 0, 85);
      float rectHeight = 11.0F;
      float iconSize = 11.0F;
      float gap = 0.5F;
      float padding = 3.0F;
      float fontHeight = Fonts.sf_medium[13].getHeight();
      Iterator var8 = mc.world.getAllEntities().iterator();

      while(true) {
         Item item;
         int steps;
         Vector2f position;
         do {
            Entity entity;
            do {
               do {
                  if (!var8.hasNext()) {
                     return;
                  }

                  entity = (Entity)var8.next();
               } while(!this.validEntity(entity));
            } while(!this.noMove(entity));

            item = entity instanceof EnderPearlEntity ? Items.ENDER_PEARL : (entity instanceof ArrowEntity ? Items.ARROW : Items.TRIDENT);
            Vector3d pearlPosition = entity.getPositionVec();
            Vector3d pearlMotion = entity.getMotion();
            Vector3d lastPosition = pearlPosition;
            steps = 0;

            for(int i = 0; i <= 300; ++i) {
               ++steps;
               lastPosition = pearlPosition;
               pearlPosition = pearlPosition.add(pearlMotion);
               pearlMotion = this.updatePearlMotion(entity, pearlMotion, pearlPosition);
               if (this.shouldEntityHit(pearlPosition, lastPosition) || pearlPosition.y <= 0.0) {
                  break;
               }
            }

            position = ProjectUtil.project2D(lastPosition.x, lastPosition.y, lastPosition.z);
         } while(position.x == Float.MAX_VALUE && position.y == Float.MAX_VALUE);

         float timeInSeconds = (float)steps * 0.05F;
         String timeText = TIME_FORMAT.format((double)timeInSeconds) + " сек.";
         float timeBlockWidth = Fonts.sf_medium[13].getWidth(timeText) + 6.0F;
         float totalWidth = 11.5F + timeBlockWidth;
         float tagX = position.x - totalWidth / 2.0F;
         float tagY = position.y - 5.5F;
         RenderUtil.drawRoundedRectangle(tagX, tagY, 11.0F, 11.0F, 2.0F, rectBgColor);
         RenderUtil.drawStack(new ItemStack(item), tagX + 1.5F, tagY + 1.5F, 0.5F);
         float currentX = tagX + 11.5F;
         RenderUtil.drawRoundedRectangle(currentX, tagY, timeBlockWidth, 11.0F, 2.0F, rectBgColor);
         float timeTextX = currentX + 3.0F;
         float timeTextY = tagY + (11.0F - fontHeight) / 2.0F + 1.0F;
         Fonts.sf_medium[13].drawString(event.getStack(), timeText, (double)timeTextX, (double)timeTextY, -1);
      }
   }

   @EventTarget
   public void onEvent(EventRender3D event) {
      MatrixStack matrix = new MatrixStack();
      RenderSystem.pushMatrix();
      RenderSystem.multMatrix(matrix.getLast().getMatrix());
      RenderSystem.translated(-mc.getRenderManager().renderPosX(), -mc.getRenderManager().renderPosY(), -mc.getRenderManager().renderPosZ());
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableTexture();
      RenderSystem.disableDepthTest();
      GL11.glEnable(2848);
      RenderSystem.lineWidth(1.5F);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      BUFFER.begin(1, DefaultVertexFormats.POSITION_COLOR);
      Iterator var3 = mc.world.getAllEntities().iterator();

      while(var3.hasNext()) {
         Entity entity = (Entity)var3.next();
         if (this.validEntity(entity) && this.noMove(entity)) {
            this.renderLine(entity);
         }
      }

      TESSELLATOR.draw();
      RenderSystem.enableDepthTest();
      RenderSystem.enableTexture();
      RenderSystem.disableBlend();
      GL11.glDisable(2848);
      RenderSystem.translated(mc.getRenderManager().renderPosX(), mc.getRenderManager().renderPosY(), mc.getRenderManager().renderPosZ());
      RenderSystem.popMatrix();
   }

   private void renderLine(Entity pearl) {
      Vector3d pearlPosition = pearl.getPositionVec().add(0.0, 0.0, 0.0);
      Vector3d pearlMotion = pearl.getMotion();

      for(int i = 0; i <= 300; ++i) {
         Vector3d lastPosition = pearlPosition;
         pearlPosition = pearlPosition.add(pearlMotion);
         pearlMotion = this.updatePearlMotion(pearl, pearlMotion, lastPosition);
         if (this.shouldEntityHit(pearlPosition, lastPosition) || pearlPosition.y <= 0.0) {
            break;
         }

         int color = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
         BUFFER.pos(lastPosition.x, lastPosition.y, lastPosition.z).color(color).endVertex();
         BUFFER.pos(pearlPosition.x, pearlPosition.y, pearlPosition.z).color(color).endVertex();
      }

   }

   public Vector3d updatePearlMotion(Entity entity, Vector3d originalPearlMotion, Vector3d pearlPosition) {
      Vector3d pearlMotion;
      if ((entity.isInWater() || mc.world.getBlockState(new BlockPos(pearlPosition)).getBlock() == Blocks.WATER) && !(entity instanceof TridentEntity)) {
         float scale = entity instanceof EnderPearlEntity ? 0.8F : 0.6F;
         pearlMotion = originalPearlMotion.scale((double)scale);
      } else {
         pearlMotion = originalPearlMotion.scale(0.9900000095367432);
      }

      if (!entity.hasNoGravity()) {
         pearlMotion.y -= entity instanceof EnderPearlEntity ? 0.03 : 0.05;
      }

      return pearlMotion;
   }

   public boolean shouldEntityHit(Vector3d pearlPosition, Vector3d lastPosition) {
      Minecraft var10006 = mc;
      RayTraceContext rayTraceContext = new RayTraceContext(lastPosition, pearlPosition, BlockMode.COLLIDER, FluidMode.NONE, Minecraft.player);
      BlockRayTraceResult blockHitResult = mc.world.rayTraceBlocks(rayTraceContext);
      return blockHitResult.getType() == Type.BLOCK;
   }

   public static void drawItemStack(ItemStack stack, float x, float y, float width, float height) {
      RenderSystem.translated((double)x, (double)y, 0.0);
      float scaleX = width / 16.0F;
      float scaleY = height / 16.0F;
      RenderSystem.scaled((double)scaleX, (double)scaleY, 1.0);
      mc.getItemRenderer().renderItemAndEffectIntoGUI(stack, 0, 0);
      RenderSystem.scaled((double)(1.0F / scaleX), (double)(1.0F / scaleY), 1.0);
      RenderSystem.translated((double)(-x), (double)(-y), 0.0);
   }

   boolean noMove(Entity entity) {
      return entity.prevPosY != entity.getPosY() || entity.prevPosX != entity.getPosX() || entity.prevPosZ != entity.getPosZ();
   }

   boolean validEntity(Entity entity) {
      return entity instanceof EnderPearlEntity && this.projectiles.is("Эндер Пёрл") || entity instanceof ArrowEntity && this.projectiles.is("Стрела") || entity instanceof TridentEntity && this.projectiles.is("Трезубец");
   }
}
