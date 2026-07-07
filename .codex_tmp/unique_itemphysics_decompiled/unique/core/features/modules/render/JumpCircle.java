package unique.core.features.modules.render;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.setting.Setting;
import unique.core.utility.Timer;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.TextureStorage;

public class JumpCircle extends Module {
   private final Setting<JumpCircle.Mode> mode = new Setting<>("Mode", JumpCircle.Mode.Default);
   private final Setting<Boolean> easeOut = new Setting<>("EaseOut", true);
   private final Setting<Float> rotateSpeed = new Setting<>("RotateSpeed", 2.0F, 0.5F, 5.0F);
   private final Setting<Float> circleScale = new Setting<>("CircleScale", 1.0F, 0.5F, 5.0F);
   private final Setting<Boolean> onlySelf = new Setting<>("OnlySelf", false);
   private final List<JumpCircle.Circle> circles = new ArrayList<>();
   private final List<class_1657> cache = new CopyOnWriteArrayList<>();

   public JumpCircle() {
      super("JumpCircle", Module.Category.RENDER);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onUpdate() {
      for (class_1657 pl : mc.field_1687.method_18456()) {
         if (!this.cache.contains(pl) && pl.method_24828() && (mc.field_1724 == pl || !this.onlySelf.getValue())) {
            this.cache.add(pl);
         }
      }

      this.cache
         .forEach(
            plx -> {
               if (plx != null && !plx.method_24828()) {
                  this.circles
                     .add(
                        new JumpCircle.Circle(new class_243(plx.method_23317(), (int)Math.floor(plx.method_23318()) + 0.001F, plx.method_23321()), new Timer())
                     );
                  this.cache.remove(plx);
               }
            }
         );
      this.circles.removeIf(c -> c.timer.passedMs(this.easeOut.getValue() ? 5000L : 6000L));
   }

   @Override
   public void onRender3D(class_4587 stack) {
      Collections.reverse(this.circles);
      RenderSystem.disableDepthTest();
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      switch ((JumpCircle.Mode)this.mode.getValue()) {
         case Default:
            RenderSystem.setShaderTexture(0, TextureStorage.default_circle);
            break;
         case VenusWare:
            RenderSystem.setShaderTexture(0, TextureStorage.venuswarebetter);
            break;
         case Nursultan:
            RenderSystem.setShaderTexture(0, TextureStorage.nursultan);
      }

      RenderSystem.setShader(class_757::method_34543);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);

      for (JumpCircle.Circle c : this.circles) {
         float colorAnim = (float)c.timer.getPassedTimeMs() / 6000.0F;
         float sizeAnim = this.circleScale.getValue()
            - (float)Math.pow(1.0F - (float)c.timer.getPassedTimeMs() * (this.easeOut.getValue() ? 2.0F : 1.0F) / 5000.0F, 4.0);
         stack.method_22903();
         stack.method_22904(
            c.pos().field_1352 - mc.method_1561().field_4686.method_19326().method_10216(),
            c.pos().field_1351 - mc.method_1561().field_4686.method_19326().method_10214(),
            c.pos().field_1350 - mc.method_1561().field_4686.method_19326().method_10215()
         );
         stack.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
         stack.method_22907(class_7833.field_40718.rotationDegrees(sizeAnim * this.rotateSpeed.getValue() * 1000.0F));
         float scale = sizeAnim * 2.0F;
         Matrix4f matrix = stack.method_23760().method_23761();
         buffer.method_22918(matrix, -sizeAnim, -sizeAnim + scale, 0.0F)
            .method_22913(0.0F, 1.0F)
            .method_39415(Render2DEngine.applyOpacity(HudEditor.getColor(270), 1.0F - colorAnim).getRGB());
         buffer.method_22918(matrix, -sizeAnim + scale, -sizeAnim + scale, 0.0F)
            .method_22913(1.0F, 1.0F)
            .method_39415(Render2DEngine.applyOpacity(HudEditor.getColor(0), 1.0F - colorAnim).getRGB());
         buffer.method_22918(matrix, -sizeAnim + scale, -sizeAnim, 0.0F)
            .method_22913(1.0F, 0.0F)
            .method_39415(Render2DEngine.applyOpacity(HudEditor.getColor(180), 1.0F - colorAnim).getRGB());
         buffer.method_22918(matrix, -sizeAnim, -sizeAnim, 0.0F)
            .method_22913(0.0F, 0.0F)
            .method_39415(Render2DEngine.applyOpacity(HudEditor.getColor(90), 1.0F - colorAnim).getRGB());
         stack.method_22909();
      }

      Render2DEngine.endBuilding(buffer);
      RenderSystem.disableBlend();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableDepthTest();
      Collections.reverse(this.circles);
   }

   public record Circle(class_243 pos, Timer timer) {
   }

   public static enum Mode {
      Default,
      VenusWare,
      Nursultan;
   }
}
