package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventJump;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;

public class JumpCircle extends Module {
   private final SliderSetting radius = new SliderSetting("Радиус", 1.5F, 1.0F, 2.0F, 0.1F);
   private final CopyOnWriteArrayList circles = new CopyOnWriteArrayList();

   public JumpCircle() {
      super("Jump Circle", "Добавляет эффект при прыжке", Category.Visuals);
      this.addSettings(new Setting[]{this.radius});
   }

   @EventTarget
   private void onJump(EventJump event) {
      Minecraft var10002 = mc;
      double var3 = Minecraft.player.getPosX();
      Minecraft var10003 = mc;
      double var4 = Minecraft.player.getPosY();
      Minecraft var10004 = mc;
      Vector3d pos = new Vector3d(var3, var4, Minecraft.player.getPosZ());
      this.circles.add(new Circle(pos));
      this.circles.add(new Circle(pos));
   }

   @EventTarget
   private void onRender3D(EventRender3D event) {
      if (!this.circles.isEmpty()) {
         float spin = ((float)mc.world.getGameTime() + event.getPartialTicks()) * 20.0F;
         float angleStep = 3.0F;
         int themeColor = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
         int darkerColor = ColorUtil.darken(themeColor, 0.6F);
         int boost = 45;
         Iterator var7 = this.circles.iterator();

         while(true) {
            while(var7.hasNext()) {
               Circle circle = (Circle)var7.next();
               if (!circle.hasExpanded) {
                  circle.scaleAnim.update(1.1F);
                  if (circle.alphaAnim.getTarget() != 1.0F || !circle.alphaAnim.isDone()) {
                     circle.alphaAnim.update(1.0F);
                  }

                  if (circle.scaleAnim.isDone()) {
                     circle.hasExpanded = true;
                     circle.scaleAnim.setValue(1.1F);
                     circle.scaleAnim.update(1.0F);
                  }
               } else {
                  circle.scaleAnim.update(1.0F);
                  if (!circle.fading) {
                     if (circle.alphaAnim.getTarget() != 1.0F || !circle.alphaAnim.isDone()) {
                        circle.alphaAnim.update(1.0F);
                     }

                     if (circle.alphaAnim.isDone()) {
                        circle.fading = true;
                        circle.alphaAnim.setSpeed(0.25F);
                        circle.alphaAnim.update(0.0F);
                     }
                  } else {
                     if (circle.alphaAnim.getTarget() != 0.0F || !circle.alphaAnim.isDone()) {
                        circle.alphaAnim.update(0.0F);
                     }

                     if (circle.scaleAnim.isDone() && !circle.speedSwitched) {
                        circle.speedSwitched = true;
                        circle.alphaAnim.setSpeed(3.0F);
                        circle.alphaAnim.setValue(circle.alphaAnim.getValue());
                        circle.alphaAnim.update(0.0F);
                     }

                     if (circle.alphaAnim.isDone()) {
                        this.circles.remove(circle);
                        continue;
                     }
                  }
               }

               float worldX = (float)(circle.vector3d.x - mc.getRenderManager().info.getProjectedView().x);
               float worldY = (float)(circle.vector3d.y - mc.getRenderManager().info.getProjectedView().y) + 0.05F;
               float worldZ = (float)(circle.vector3d.z - mc.getRenderManager().info.getProjectedView().z);
               float circleRadius = ((Float)this.radius.get() + 1.0F) * circle.scaleAnim.getValue() * 0.5F;
               float innerRadius = Math.max(0.001F, circleRadius * 0.02F);
               float alpha = circle.alphaAnim.getValue();
               int colorInner = (int)(alpha * 25.5F) << 24 | Math.min(255, ColorUtil.red(themeColor) + boost) << 16 | Math.min(255, ColorUtil.green(themeColor) + boost) << 8 | Math.min(255, ColorUtil.blue(themeColor) + boost);

               for(int i = 0; i < 120; ++i) {
                  float rad1 = (float)Math.toRadians((double)(spin + (float)i * angleStep));
                  float rad2 = (float)Math.toRadians((double)(spin + (float)(i + 1) * angleStep));
                  float cos1 = (float)Math.cos((double)rad1);
                  float sin1 = (float)Math.sin((double)rad1);
                  float cos2 = (float)Math.cos((double)rad2);
                  float sin2 = (float)Math.sin((double)rad2);
                  float p1 = (float)i * angleStep % 360.0F;
                  p1 = p1 >= 180.0F ? 360.0F - p1 : p1;
                  float p2 = (float)(i + 1) * angleStep % 360.0F;
                  p2 = p2 >= 180.0F ? 360.0F - p2 : p2;
                  float easedP1 = (float)Easings.SINE_IN_OUT.ease((double)(p1 / 180.0F));
                  float easedP2 = (float)Easings.SINE_IN_OUT.ease((double)(p2 / 180.0F));
                  int col1 = ColorUtil.overCol(ColorUtil.applyOpacity(themeColor, alpha), ColorUtil.applyOpacity(darkerColor, alpha), easedP1);
                  int col2 = ColorUtil.overCol(ColorUtil.applyOpacity(themeColor, alpha), ColorUtil.applyOpacity(darkerColor, alpha), easedP2);
                  int c0 = ColorUtil.alpha(col1) << 24 | Math.min(255, ColorUtil.red(col1) + boost) << 16 | Math.min(255, ColorUtil.green(col1) + boost) << 8 | Math.min(255, ColorUtil.blue(col1) + boost);
                  int c1 = ColorUtil.alpha(col2) << 24 | Math.min(255, ColorUtil.red(col2) + boost) << 16 | Math.min(255, ColorUtil.green(col2) + boost) << 8 | Math.min(255, ColorUtil.blue(col2) + boost);
                  RenderUtil.drawImage3DQuadInternal(new ResourceLocation("SkyCore/icons/world_render/jump.png"), worldX + cos1 * circleRadius, worldY, worldZ + sin1 * circleRadius, worldX + cos2 * circleRadius, worldY, worldZ + sin2 * circleRadius, worldX + cos2 * innerRadius, worldY, worldZ + sin2 * innerRadius, worldX + cos1 * innerRadius, worldY, worldZ + sin1 * innerRadius, c0, c1, colorInner, colorInner, cos1 * 0.5F + 0.5F, sin1 * 0.5F + 0.5F, cos2 * 0.5F + 0.5F, sin2 * 0.5F + 0.5F, 0.5F, 0.5F, 0.5F, 0.5F);
               }
            }

            RenderUtil.flushImage3DBatch();
            return;
         }
      }
   }

   private static class Circle {
      private final Vector3d vector3d;
      private boolean hasExpanded = false;
      private boolean fading = false;
      private boolean speedSwitched = false;
      private final AnimationUtil scaleAnim;
      private final AnimationUtil alphaAnim;

      public Circle(Vector3d vector3d) {
         this.scaleAnim = new AnimationUtil(0.1F, 3.0F, Easings.SINE_OUT);
         this.alphaAnim = new AnimationUtil(0.0F, 3.0F, Easings.SINE_OUT);
         this.vector3d = vector3d;
         this.scaleAnim.update(1.1F);
         this.alphaAnim.update(1.0F);
      }
   }
}
