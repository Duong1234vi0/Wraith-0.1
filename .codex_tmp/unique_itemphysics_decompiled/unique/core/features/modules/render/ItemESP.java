package unique.core.features.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_332;
import net.minecraft.class_757;
import net.minecraft.class_293.class_5596;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector4d;
import unique.core.features.modules.Module;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class ItemESP extends Module {
   private static final Color BACKGROUND_COLOR = new Color(10, 10, 10, 180);
   private final Setting<Boolean> showText = new Setting<>("ShowText", true);
   private final Setting<Integer> radius = new Setting<>("Blocks", 128, 1, 512);

   public ItemESP() {
      super("ItemESP", Module.Category.RENDER);
   }

   @Override
   public void onRender2D(class_332 context) {
      if (!fullNullCheck()) {
         Matrix4f matrix = context.method_51448().method_23760().method_23761();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableDepthTest();
         Render2DEngine.setupRender();
         RenderSystem.setShader(class_757::method_34540);
         class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);

         for (class_1297 ent : mc.field_1687.method_18112()) {
            if (ent instanceof class_1542 && !(mc.field_1724.method_5739(ent) > this.radius.getValue().intValue())) {
               Vector4d pos = this.getScreenPositions(ent);
               if (pos != null) {
                  this.drawRect(bufferBuilder, matrix, (float)pos.x, (float)pos.y, (float)pos.z, (float)pos.w);
               }
            }
         }

         Render2DEngine.endBuilding(bufferBuilder);
         Render2DEngine.endRender();

         for (class_1297 entx : mc.field_1687.method_18112()) {
            if (entx instanceof class_1542 item && !(mc.field_1724.method_5739(entx) > this.radius.getValue().intValue())) {
               Vector4d pos = this.getScreenPositions(entx);
               if (pos != null) {
                  float x1 = (float)pos.x;
                  float y1 = (float)pos.y;
                  float x2 = (float)pos.z;
                  float midX = x1 + (x2 - x1) / 2.0F;
                  float iconSize = 16.0F;
                  float padding = 4.0F;
                  float bgSize = iconSize + padding * 2.0F;
                  float bgX = midX - bgSize / 2.0F;
                  float bgY = y1 - bgSize - 6.0F;
                  Render2DEngine.drawRect(
                     context.method_51448(), bgX, bgY, bgSize, bgSize, 4.0F, 1.0F, BACKGROUND_COLOR, BACKGROUND_COLOR, BACKGROUND_COLOR, BACKGROUND_COLOR
                  );
                  class_1799 stack = item.method_6983();
                  if (!stack.method_7960()) {
                     context.method_51427(stack, (int)(bgX + padding), (int)(bgY + padding));
                  }

                  if (this.showText.getValue()) {
                     String name = stack.method_7964().getString();
                     float textWidth = FontRenderers.sf_bold_mini.getStringWidth(name);
                     FontRenderers.sf_bold_mini.drawString(context.method_51448(), name, midX - textWidth / 2.0F, bgY - 10.0F, -1);
                  }
               }
            }
         }

         RenderSystem.enableDepthTest();
         RenderSystem.disableBlend();
      }
   }

   private Vector4d getScreenPositions(class_1297 ent) {
      class_243[] points = getPoints(ent);
      float minX = Float.MAX_VALUE;
      float minY = Float.MAX_VALUE;
      float maxX = Float.MIN_VALUE;
      float maxY = Float.MIN_VALUE;
      boolean visible = false;

      for (class_243 p : points) {
         class_243 screen = Render3DEngine.worldSpaceToScreenSpace(p);
         if (!(screen.field_1350 < 0.0) && !(screen.field_1350 > 1.0)) {
            visible = true;
            minX = Math.min(minX, (float)screen.field_1352);
            minY = Math.min(minY, (float)screen.field_1351);
            maxX = Math.max(maxX, (float)screen.field_1352);
            maxY = Math.max(maxY, (float)screen.field_1351);
         }
      }

      return !visible ? null : new Vector4d(minX, minY, maxX, maxY);
   }

   private void drawRect(class_287 bufferBuilder, Matrix4f matrix, float x1, float y1, float x2, float y2) {
      Color c = Color.WHITE;
      float t = 0.6F;
      Render2DEngine.setRectPoints(bufferBuilder, matrix, x1, y1, x2, y1 + t, c, c, c, c);
      Render2DEngine.setRectPoints(bufferBuilder, matrix, x1, y2 - t, x2, y2, c, c, c, c);
      Render2DEngine.setRectPoints(bufferBuilder, matrix, x1, y1, x1 + t, y2, c, c, c, c);
      Render2DEngine.setRectPoints(bufferBuilder, matrix, x2 - t, y1, x2, y2, c, c, c, c);
   }

   @NotNull
   private static class_243[] getPoints(class_1297 ent) {
      class_238 bb = getBox(ent);
      return new class_243[]{
         new class_243(bb.field_1323, bb.field_1322, bb.field_1321),
         new class_243(bb.field_1323, bb.field_1325, bb.field_1321),
         new class_243(bb.field_1320, bb.field_1322, bb.field_1321),
         new class_243(bb.field_1320, bb.field_1325, bb.field_1321),
         new class_243(bb.field_1323, bb.field_1322, bb.field_1324),
         new class_243(bb.field_1323, bb.field_1325, bb.field_1324),
         new class_243(bb.field_1320, bb.field_1322, bb.field_1324),
         new class_243(bb.field_1320, bb.field_1325, bb.field_1324)
      };
   }

   @NotNull
   private static class_238 getBox(class_1297 ent) {
      double delta = Render3DEngine.getTickDelta();
      double x = ent.field_6014 + (ent.method_23317() - ent.field_6014) * delta;
      double y = ent.field_6036 + (ent.method_23318() - ent.field_6036) * delta;
      double z = ent.field_5969 + (ent.method_23321() - ent.field_5969) * delta;
      class_238 bb = ent.method_5829();
      return new class_238(
         bb.field_1323 - ent.method_23317() + x - 0.05,
         bb.field_1322 - ent.method_23318() + y,
         bb.field_1321 - ent.method_23321() + z - 0.05,
         bb.field_1320 - ent.method_23317() + x + 0.05,
         bb.field_1325 - ent.method_23318() + y + 0.25,
         bb.field_1324 - ent.method_23321() + z + 0.05
      );
   }
}
