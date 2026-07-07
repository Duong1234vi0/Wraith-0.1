package unique.core.utility.render;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_4587.class_4665;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import unique.core.UniqueCore;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;

public class Render3DEngine {
   public static List<Render3DEngine.FillAction> FILLED_QUEUE = new ArrayList<>();
   public static List<Render3DEngine.OutlineAction> OUTLINE_QUEUE = new ArrayList<>();
   public static List<Render3DEngine.FadeAction> FADE_QUEUE = new ArrayList<>();
   public static List<Render3DEngine.FillSideAction> FILLED_SIDE_QUEUE = new ArrayList<>();
   public static List<Render3DEngine.OutlineSideAction> OUTLINE_SIDE_QUEUE = new ArrayList<>();
   public static List<Render3DEngine.DebugLineAction> DEBUG_LINE_QUEUE = new ArrayList<>();
   public static List<Render3DEngine.LineAction> LINE_QUEUE = new ArrayList<>();
   public static final Matrix4f lastProjMat = new Matrix4f();
   public static final Matrix4f lastModMat = new Matrix4f();
   public static final Matrix4f lastWorldSpaceMatrix = new Matrix4f();
   private static float prevCircleStep;
   private static float circleStep;

   public static void onRender3D(class_4587 stack) {
      if (!FILLED_QUEUE.isEmpty() || !FADE_QUEUE.isEmpty() || !FILLED_SIDE_QUEUE.isEmpty()) {
         class_289 tessellator = class_289.method_1348();
         class_287 bufferBuilder = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
         RenderSystem.disableDepthTest();
         setupRender();
         RenderSystem.setShader(class_757::method_34540);
         FILLED_QUEUE.forEach(action -> setFilledBoxVertexes(bufferBuilder, stack.method_23760().method_23761(), action.box(), action.color()));
         FADE_QUEUE.forEach(action -> setFilledFadePoints(action.box(), bufferBuilder, stack.method_23760().method_23761(), action.color(), action.color2()));
         FILLED_SIDE_QUEUE.forEach(action -> setFilledSidePoints(bufferBuilder, stack.method_23760().method_23761(), action.box, action.color(), action.side()));
         Render2DEngine.endBuilding(bufferBuilder);
         endRender();
         RenderSystem.enableDepthTest();
         FADE_QUEUE.clear();
         FILLED_SIDE_QUEUE.clear();
         FILLED_QUEUE.clear();
      }

      if (!OUTLINE_QUEUE.isEmpty() || !OUTLINE_SIDE_QUEUE.isEmpty()) {
         setupRender();
         class_289 tessellator = class_289.method_1348();
         class_287 buffer = tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.setShader(class_757::method_34535);
         RenderSystem.lineWidth(2.0F);
         OUTLINE_QUEUE.forEach(
            action -> setOutlinePoints(
               action.box(), matrixFrom(action.box().field_1323, action.box().field_1322, action.box().field_1321), buffer, action.color()
            )
         );
         OUTLINE_SIDE_QUEUE.forEach(
            action -> setSideOutlinePoints(
               action.box, matrixFrom(action.box().field_1323, action.box().field_1322, action.box().field_1321), buffer, action.color(), action.side()
            )
         );
         Render2DEngine.endBuilding(buffer);
         RenderSystem.enableCull();
         RenderSystem.enableDepthTest();
         endRender();
         OUTLINE_QUEUE.clear();
         OUTLINE_SIDE_QUEUE.clear();
      }

      if (!DEBUG_LINE_QUEUE.isEmpty()) {
         setupRender();
         RenderSystem.disableDepthTest();
         class_289 tessellator = class_289.method_1348();
         class_287 buffer = tessellator.method_60827(class_5596.field_29344, class_290.field_29337);
         RenderSystem.disableCull();
         RenderSystem.setShader(class_757::method_34535);
         DEBUG_LINE_QUEUE.forEach(
            action -> {
               class_4587 matrices = matrixFrom(action.start.method_10216(), action.start.method_10214(), action.start.method_10215());
               vertexLine(
                  matrices,
                  buffer,
                  0.0F,
                  0.0F,
                  0.0F,
                  (float)(action.end.method_10216() - action.start.method_10216()),
                  (float)(action.end.method_10214() - action.start.method_10214()),
                  (float)(action.end.method_10215() - action.start.method_10215()),
                  action.color
               );
            }
         );
         Render2DEngine.endBuilding(buffer);
         RenderSystem.enableCull();
         RenderSystem.enableDepthTest();
         endRender();
         DEBUG_LINE_QUEUE.clear();
      }

      if (!LINE_QUEUE.isEmpty()) {
         setupRender();
         class_289 tessellator = class_289.method_1348();
         RenderSystem.disableCull();
         RenderSystem.setShader(class_757::method_34535);
         RenderSystem.lineWidth(2.0F);
         RenderSystem.disableDepthTest();
         class_287 buffer = tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
         LINE_QUEUE.forEach(
            action -> {
               class_4587 matrices = matrixFrom(action.start.method_10216(), action.start.method_10214(), action.start.method_10215());
               vertexLine(
                  matrices,
                  buffer,
                  0.0F,
                  0.0F,
                  0.0F,
                  (float)(action.end.method_10216() - action.start.method_10216()),
                  (float)(action.end.method_10214() - action.start.method_10214()),
                  (float)(action.end.method_10215() - action.start.method_10215()),
                  action.color
               );
            }
         );
         Render2DEngine.endBuilding(buffer);
         RenderSystem.enableCull();
         RenderSystem.lineWidth(1.0F);
         RenderSystem.enableDepthTest();
         endRender();
         LINE_QUEUE.clear();
      }
   }

   @Deprecated
   public static void drawFilledBox(class_4587 stack, class_238 box, Color c) {
      FILLED_QUEUE.add(new Render3DEngine.FillAction(box, c));
   }

   public static void setFilledBoxVertexes(@NotNull class_287 bufferBuilder, Matrix4f m, @NotNull class_238 box, @NotNull Color c) {
      float minX = (float)(box.field_1323 - Module.mc.method_1561().field_4686.method_19326().method_10216());
      float minY = (float)(box.field_1322 - Module.mc.method_1561().field_4686.method_19326().method_10214());
      float minZ = (float)(box.field_1321 - Module.mc.method_1561().field_4686.method_19326().method_10215());
      float maxX = (float)(box.field_1320 - Module.mc.method_1561().field_4686.method_19326().method_10216());
      float maxY = (float)(box.field_1325 - Module.mc.method_1561().field_4686.method_19326().method_10214());
      float maxZ = (float)(box.field_1324 - Module.mc.method_1561().field_4686.method_19326().method_10215());
      bufferBuilder.method_22918(m, minX, minY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, minY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, minY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, minY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, minY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, maxY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, maxY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, minY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, minY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, maxY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, maxY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, minY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, minY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, minY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, maxY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, maxY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, minY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, minY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, maxY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, maxY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, maxY, minZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, minX, maxY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, maxY, maxZ).method_39415(c.getRGB());
      bufferBuilder.method_22918(m, maxX, maxY, minZ).method_39415(c.getRGB());
   }

   @NotNull
   public static class_238 interpolateBox(@NotNull class_238 from, @NotNull class_238 to, float delta) {
      double X = Render2DEngine.interpolate(from.field_1320, to.field_1320, delta);
      double Y = Render2DEngine.interpolate(from.field_1325, to.field_1325, delta);
      double Z = Render2DEngine.interpolate(from.field_1324, to.field_1324, delta);
      double X1 = Render2DEngine.interpolate(from.field_1323, to.field_1323, delta);
      double Y1 = Render2DEngine.interpolate(from.field_1322, to.field_1322, delta);
      double Z1 = Render2DEngine.interpolate(from.field_1321, to.field_1321, delta);
      return new class_238(X1, Y1, Z1, X, Y, Z);
   }

   @Deprecated
   public static void drawFilledSide(class_4587 stack, @NotNull class_238 box, Color c, class_2350 dir) {
      FILLED_SIDE_QUEUE.add(new Render3DEngine.FillSideAction(box, c, dir));
   }

   public static void setFilledSidePoints(class_287 buffer, Matrix4f matrix, class_238 box, Color c, class_2350 dir) {
      float minX = (float)(box.field_1323 - Module.mc.method_1561().field_4686.method_19326().method_10216());
      float minY = (float)(box.field_1322 - Module.mc.method_1561().field_4686.method_19326().method_10214());
      float minZ = (float)(box.field_1321 - Module.mc.method_1561().field_4686.method_19326().method_10215());
      float maxX = (float)(box.field_1320 - Module.mc.method_1561().field_4686.method_19326().method_10216());
      float maxY = (float)(box.field_1325 - Module.mc.method_1561().field_4686.method_19326().method_10214());
      float maxZ = (float)(box.field_1324 - Module.mc.method_1561().field_4686.method_19326().method_10215());
      if (dir == class_2350.field_11033) {
         buffer.method_22918(matrix, minX, minY, minZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, minY, minZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, minY, maxZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, minX, minY, maxZ).method_39415(c.getRGB());
      }

      if (dir == class_2350.field_11043) {
         buffer.method_22918(matrix, minX, minY, minZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, minX, maxY, minZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, maxY, minZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, minY, minZ).method_39415(c.getRGB());
      }

      if (dir == class_2350.field_11034) {
         buffer.method_22918(matrix, maxX, minY, minZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, maxY, minZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, maxY, maxZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, minY, maxZ).method_39415(c.getRGB());
      }

      if (dir == class_2350.field_11035) {
         buffer.method_22918(matrix, minX, minY, maxZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, minY, maxZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, maxY, maxZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, minX, maxY, maxZ).method_39415(c.getRGB());
      }

      if (dir == class_2350.field_11039) {
         buffer.method_22918(matrix, minX, minY, minZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, minX, minY, maxZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, minX, maxY, maxZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, minX, maxY, minZ).method_39415(c.getRGB());
      }

      if (dir == class_2350.field_11036) {
         buffer.method_22918(matrix, minX, maxY, minZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, minX, maxY, maxZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, maxY, maxZ).method_39415(c.getRGB());
         buffer.method_22918(matrix, maxX, maxY, minZ).method_39415(c.getRGB());
      }
   }

   public static void drawTextIn3D(String text, @NotNull class_243 pos, double offX, double offY, double textOffset, @NotNull Color color) {
      class_4587 matrices = new class_4587();
      class_4184 camera = Module.mc.field_1773.method_19418();
      RenderSystem.disableDepthTest();
      RenderSystem.disableCull();
      matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
      matrices.method_22904(
         pos.method_10216() - camera.method_19326().field_1352,
         pos.method_10214() - camera.method_19326().field_1351,
         pos.method_10215() - camera.method_19326().field_1350
      );
      matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      setupRender();
      matrices.method_22904(offX, offY - 0.1, -0.01);
      matrices.method_22905(-0.025F, -0.025F, 0.0F);
      FontRenderers.sf_medium.drawCenteredString(matrices, text, textOffset, 0.0, color.getRGB());
      RenderSystem.enableCull();
      RenderSystem.enableDepthTest();
      endRender();
   }

   @NotNull
   public static class_243 worldSpaceToScreenSpace(@NotNull class_243 pos) {
      class_4184 camera = Module.mc.method_1561().field_4686;
      int displayHeight = Module.mc.method_22683().method_4507();
      int[] viewport = new int[4];
      GL11.glGetIntegerv(2978, viewport);
      Vector3f target = new Vector3f();
      double deltaX = pos.field_1352 - camera.method_19326().field_1352;
      double deltaY = pos.field_1351 - camera.method_19326().field_1351;
      double deltaZ = pos.field_1350 - camera.method_19326().field_1350;
      Vector4f transformedCoordinates = new Vector4f((float)deltaX, (float)deltaY, (float)deltaZ, 1.0F).mul(lastWorldSpaceMatrix);
      Matrix4f matrixProj = new Matrix4f(lastProjMat);
      Matrix4f matrixModel = new Matrix4f(lastModMat);
      matrixProj.mul(matrixModel).project(transformedCoordinates.x(), transformedCoordinates.y(), transformedCoordinates.z(), viewport, target);
      return new class_243(target.x / getScaleFactor(), (displayHeight - target.y) / getScaleFactor(), target.z);
   }

   public static double getScaleFactor() {
      return ClientSettings.scaleFactorFix.getValue() ? ClientSettings.scaleFactorFixValue.getValue().floatValue() : Module.mc.method_22683().method_4495();
   }

   @Deprecated
   public static void drawFilledFadeBox(@NotNull class_4587 stack, @NotNull class_238 box, @NotNull Color c, @NotNull Color c1) {
      FADE_QUEUE.add(new Render3DEngine.FadeAction(box, c, c1));
   }

   public static void setFilledFadePoints(class_238 box, class_287 buffer, Matrix4f posMatrix, Color c, Color c1) {
      float minX = (float)(box.field_1323 - Module.mc.method_1561().field_4686.method_19326().method_10216());
      float minY = (float)(box.field_1322 - Module.mc.method_1561().field_4686.method_19326().method_10214());
      float minZ = (float)(box.field_1321 - Module.mc.method_1561().field_4686.method_19326().method_10215());
      float maxX = (float)(box.field_1320 - Module.mc.method_1561().field_4686.method_19326().method_10216());
      float maxY = (float)(box.field_1325 - Module.mc.method_1561().field_4686.method_19326().method_10214());
      float maxZ = (float)(box.field_1324 - Module.mc.method_1561().field_4686.method_19326().method_10215());
      buffer.method_22918(posMatrix, minX, minY, minZ).method_39415(c.getRGB());
      buffer.method_22918(posMatrix, minX, maxY, minZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, maxX, maxY, minZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, maxX, minY, minZ).method_39415(c.getRGB());
      buffer.method_22918(posMatrix, maxX, minY, minZ).method_39415(c.getRGB());
      buffer.method_22918(posMatrix, maxX, maxY, minZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, maxX, maxY, maxZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, maxX, minY, maxZ).method_39415(c.getRGB());
      buffer.method_22918(posMatrix, minX, minY, maxZ).method_39415(c.getRGB());
      buffer.method_22918(posMatrix, maxX, minY, maxZ).method_39415(c.getRGB());
      buffer.method_22918(posMatrix, maxX, maxY, maxZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, minX, maxY, maxZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, minX, minY, minZ).method_39415(c.getRGB());
      buffer.method_22918(posMatrix, minX, minY, maxZ).method_39415(c.getRGB());
      buffer.method_22918(posMatrix, minX, maxY, maxZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, minX, maxY, minZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, minX, maxY, minZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, minX, maxY, maxZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, maxX, maxY, maxZ).method_39415(c1.getRGB());
      buffer.method_22918(posMatrix, maxX, maxY, minZ).method_39415(c1.getRGB());
   }

   public static void drawLine(@NotNull class_243 start, @NotNull class_243 end, @NotNull Color color) {
      LINE_QUEUE.add(new Render3DEngine.LineAction(start, end, color));
   }

   @Deprecated
   public static void drawBoxOutline(@NotNull class_238 box, Color color, float lineWidth) {
      OUTLINE_QUEUE.add(new Render3DEngine.OutlineAction(box, color, lineWidth));
   }

   public static void setOutlinePoints(class_238 box, class_4587 matrices, class_287 buffer, Color color) {
      box = box.method_997(new class_243(box.field_1323, box.field_1322, box.field_1321).method_22882());
      float x1 = (float)box.field_1323;
      float y1 = (float)box.field_1322;
      float z1 = (float)box.field_1321;
      float x2 = (float)box.field_1320;
      float y2 = (float)box.field_1325;
      float z2 = (float)box.field_1324;
      vertexLine(matrices, buffer, x1, y1, z1, x2, y1, z1, color);
      vertexLine(matrices, buffer, x2, y1, z1, x2, y1, z2, color);
      vertexLine(matrices, buffer, x2, y1, z2, x1, y1, z2, color);
      vertexLine(matrices, buffer, x1, y1, z2, x1, y1, z1, color);
      vertexLine(matrices, buffer, x1, y1, z2, x1, y2, z2, color);
      vertexLine(matrices, buffer, x1, y1, z1, x1, y2, z1, color);
      vertexLine(matrices, buffer, x2, y1, z2, x2, y2, z2, color);
      vertexLine(matrices, buffer, x2, y1, z1, x2, y2, z1, color);
      vertexLine(matrices, buffer, x1, y2, z1, x2, y2, z1, color);
      vertexLine(matrices, buffer, x2, y2, z1, x2, y2, z2, color);
      vertexLine(matrices, buffer, x2, y2, z2, x1, y2, z2, color);
      vertexLine(matrices, buffer, x1, y2, z2, x1, y2, z1, color);
   }

   @Deprecated
   public static void drawSideOutline(@NotNull class_238 box, Color color, float lineWidth, class_2350 dir) {
      OUTLINE_SIDE_QUEUE.add(new Render3DEngine.OutlineSideAction(box, color, lineWidth, dir));
   }

   public static void setSideOutlinePoints(class_238 box, class_4587 matrices, class_287 buffer, Color color, class_2350 dir) {
      box = box.method_997(new class_243(box.field_1323, box.field_1322, box.field_1321).method_22882());
      float x1 = (float)box.field_1323;
      float y1 = (float)box.field_1322;
      float z1 = (float)box.field_1321;
      float x2 = (float)box.field_1320;
      float y2 = (float)box.field_1325;
      float z2 = (float)box.field_1324;
      switch (dir) {
         case field_11036:
            vertexLine(matrices, buffer, x1, y2, z1, x2, y2, z1, color);
            vertexLine(matrices, buffer, x2, y2, z1, x2, y2, z2, color);
            vertexLine(matrices, buffer, x2, y2, z2, x1, y2, z2, color);
            vertexLine(matrices, buffer, x1, y2, z2, x1, y2, z1, color);
            break;
         case field_11033:
            vertexLine(matrices, buffer, x1, y1, z1, x2, y1, z1, color);
            vertexLine(matrices, buffer, x2, y1, z1, x2, y1, z2, color);
            vertexLine(matrices, buffer, x2, y1, z2, x1, y1, z2, color);
            vertexLine(matrices, buffer, x1, y1, z2, x1, y1, z1, color);
            break;
         case field_11034:
            vertexLine(matrices, buffer, x2, y1, z1, x2, y2, z1, color);
            vertexLine(matrices, buffer, x2, y1, z2, x2, y2, z2, color);
            vertexLine(matrices, buffer, x2, y2, z2, x2, y2, z1, color);
            vertexLine(matrices, buffer, x2, y1, z2, x2, y1, z1, color);
            break;
         case field_11039:
            vertexLine(matrices, buffer, x1, y1, z1, x1, y2, z1, color);
            vertexLine(matrices, buffer, x1, y1, z2, x1, y2, z2, color);
            vertexLine(matrices, buffer, x1, y2, z2, x1, y2, z1, color);
            vertexLine(matrices, buffer, x1, y1, z2, x1, y1, z1, color);
            break;
         case field_11043:
            vertexLine(matrices, buffer, x2, y1, z1, x2, y2, z1, color);
            vertexLine(matrices, buffer, x1, y1, z1, x1, y2, z1, color);
            vertexLine(matrices, buffer, x2, y1, z1, x1, y1, z1, color);
            vertexLine(matrices, buffer, x2, y2, z1, x1, y2, z1, color);
            break;
         case field_11035:
            vertexLine(matrices, buffer, x1, y1, z2, x1, y2, z2, color);
            vertexLine(matrices, buffer, x2, y1, z2, x2, y2, z2, color);
            vertexLine(matrices, buffer, x1, y1, z2, x2, y1, z2, color);
            vertexLine(matrices, buffer, x1, y2, z2, x2, y2, z2, color);
      }
   }

   public static void drawHoleOutline(@NotNull class_238 box, Color color, float lineWidth) {
      setupRender();
      class_4587 matrices = matrixFrom(box.field_1323, box.field_1322, box.field_1321);
      class_289 tessellator = class_289.method_1348();
      class_287 buffer = tessellator.method_60827(class_5596.field_27377, class_290.field_29337);
      RenderSystem.disableCull();
      RenderSystem.setShader(class_757::method_34535);
      RenderSystem.lineWidth(lineWidth);
      box = box.method_997(new class_243(box.field_1323, box.field_1322, box.field_1321).method_22882());
      float x1 = (float)box.field_1323;
      float y1 = (float)box.field_1322;
      float y2 = (float)box.field_1325;
      float z1 = (float)box.field_1321;
      float x2 = (float)box.field_1320;
      float z2 = (float)box.field_1324;
      vertexLine(matrices, buffer, x1, y1, z1, x2, y1, z1, color);
      vertexLine(matrices, buffer, x2, y1, z1, x2, y1, z2, color);
      vertexLine(matrices, buffer, x2, y1, z2, x1, y1, z2, color);
      vertexLine(matrices, buffer, x1, y1, z2, x1, y1, z1, color);
      vertexLine(matrices, buffer, x1, y1, z1, x1, y2, z1, color);
      vertexLine(matrices, buffer, x2, y1, z2, x2, y2, z2, color);
      vertexLine(matrices, buffer, x1, y1, z2, x1, y2, z2, color);
      vertexLine(matrices, buffer, x2, y1, z1, x2, y2, z1, color);
      Render2DEngine.endBuilding(buffer);
      RenderSystem.enableCull();
      endRender();
   }

   public static void vertexLine(
      @NotNull class_4587 matrices, @NotNull class_4588 buffer, float x1, float y1, float z1, float x2, float y2, float z2, @NotNull Color lineColor
   ) {
      Matrix4f model = matrices.method_23760().method_23761();
      class_4665 entry = matrices.method_23760();
      Vector3f normalVec = getNormal(x1, y1, z1, x2, y2, z2);
      buffer.method_22918(model, x1, y1, z1)
         .method_1336(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), lineColor.getAlpha())
         .method_60831(entry, normalVec.x(), normalVec.y(), normalVec.z());
      buffer.method_22918(model, x2, y2, z2)
         .method_1336(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), lineColor.getAlpha())
         .method_60831(entry, normalVec.x(), normalVec.y(), normalVec.z());
   }

   @NotNull
   public static Vector3f getNormal(float x1, float y1, float z1, float x2, float y2, float z2) {
      float xNormal = x2 - x1;
      float yNormal = y2 - y1;
      float zNormal = z2 - z1;
      float normalSqrt = class_3532.method_15355(xNormal * xNormal + yNormal * yNormal + zNormal * zNormal);
      return new Vector3f(xNormal / normalSqrt, yNormal / normalSqrt, zNormal / normalSqrt);
   }

   @NotNull
   public static class_4587 matrixFrom(double x, double y, double z) {
      class_4587 matrices = new class_4587();
      class_4184 camera = class_310.method_1551().field_1773.method_19418();
      matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
      matrices.method_22904(x - camera.method_19326().field_1352, y - camera.method_19326().field_1351, z - camera.method_19326().field_1350);
      return matrices;
   }

   public static void setupRender() {
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
   }

   public static void endRender() {
      RenderSystem.disableBlend();
   }

   public static void drawTargetEsp(class_4587 stack, @NotNull class_1297 target) {
      ArrayList<class_243> vecs = new ArrayList<>();
      ArrayList<class_243> vecs1 = new ArrayList<>();
      ArrayList<class_243> vecs2 = new ArrayList<>();
      double x = target.field_6014
         + (target.method_23317() - target.field_6014) * getTickDelta()
         - Module.mc.method_1561().field_4686.method_19326().method_10216();
      double y = target.field_6036
         + (target.method_23318() - target.field_6036) * getTickDelta()
         - Module.mc.method_1561().field_4686.method_19326().method_10214();
      double z = target.field_5969
         + (target.method_23321() - target.field_5969) * getTickDelta()
         - Module.mc.method_1561().field_4686.method_19326().method_10215();
      double height = target.method_17682();

      for (int i = 0; i <= 361; i++) {
         double v = Math.sin(Math.toRadians(i));
         double u = Math.cos(Math.toRadians(i));
         class_243 vec = new class_243((float)(u * 0.5), height, (float)(v * 0.5));
         vecs.add(vec);
         double v1 = Math.sin(Math.toRadians((i + 120) % 360));
         double u1 = Math.cos(Math.toRadians(i + 120) % 360.0);
         class_243 vec1 = new class_243((float)(u1 * 0.5), height, (float)(v1 * 0.5));
         vecs1.add(vec1);
         double v2 = Math.sin(Math.toRadians((i + 240) % 360));
         double u2 = Math.cos(Math.toRadians((i + 240) % 360));
         class_243 vec2 = new class_243((float)(u2 * 0.5), height, (float)(v2 * 0.5));
         vecs2.add(vec2);
         height -= 0.004F;
      }

      stack.method_22903();
      stack.method_22904(x, y, z);
      setupRender();
      RenderSystem.disableCull();
      RenderSystem.disableDepthTest();
      RenderSystem.setShader(class_757::method_34540);
      class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);
      Matrix4f matrix = stack.method_23760().method_23761();

      for (int j = 0; j < vecs.size() - 1; j++) {
         float alpha = 1.0F - (j + (float)(System.currentTimeMillis() - UniqueCore.initTime) / 5.0F) % 360.0F / 60.0F;
         bufferBuilder.method_22918(matrix, (float)vecs.get(j).field_1352, (float)vecs.get(j).field_1351, (float)vecs.get(j).field_1350)
            .method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
         bufferBuilder.method_22918(matrix, (float)vecs.get(j + 1).field_1352, (float)vecs.get(j + 1).field_1351 + 0.1F, (float)vecs.get(j + 1).field_1350)
            .method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
      }

      Render2DEngine.endBuilding(bufferBuilder);
      RenderSystem.setShader(class_757::method_34540);
      bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);

      for (int j = 0; j < vecs1.size() - 1; j++) {
         float alpha = 1.0F - (j + (float)(System.currentTimeMillis() - UniqueCore.initTime) / 5.0F) % 360.0F / 60.0F;
         bufferBuilder.method_22918(matrix, (float)vecs1.get(j).field_1352, (float)vecs1.get(j).field_1351, (float)vecs1.get(j).field_1350)
            .method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
         bufferBuilder.method_22918(matrix, (float)vecs1.get(j + 1).field_1352, (float)vecs1.get(j + 1).field_1351 + 0.1F, (float)vecs1.get(j + 1).field_1350)
            .method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
      }

      Render2DEngine.endBuilding(bufferBuilder);
      RenderSystem.setShader(class_757::method_34540);
      bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);

      for (int j = 0; j < vecs2.size() - 1; j++) {
         float alpha = 1.0F - (j + (float)(System.currentTimeMillis() - UniqueCore.initTime) / 5.0F) % 360.0F / 60.0F;
         bufferBuilder.method_22918(matrix, (float)vecs2.get(j).field_1352, (float)vecs2.get(j).field_1351, (float)vecs2.get(j).field_1350)
            .method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
         bufferBuilder.method_22918(matrix, (float)vecs2.get(j + 1).field_1352, (float)vecs2.get(j + 1).field_1351 + 0.1F, (float)vecs2.get(j + 1).field_1350)
            .method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
      }

      Render2DEngine.endBuilding(bufferBuilder);
      RenderSystem.enableCull();
      stack.method_22904(-x, -y, -z);
      endRender();
      RenderSystem.enableDepthTest();
      stack.method_22909();
   }

   public static void renderCrosses(@NotNull class_238 box, Color color, float lineWidth) {
      setupRender();
      class_4587 matrices = matrixFrom(box.field_1323, box.field_1322, box.field_1321);
      RenderSystem.disableCull();
      RenderSystem.setShader(class_757::method_34535);
      RenderSystem.lineWidth(lineWidth);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27377, class_290.field_29337);
      box = box.method_997(new class_243(box.field_1323, box.field_1322, box.field_1321).method_22882());
      vertexLine(
         matrices,
         buffer,
         (float)box.field_1320,
         (float)box.field_1322,
         (float)box.field_1321,
         (float)box.field_1323,
         (float)box.field_1322,
         (float)box.field_1324,
         color
      );
      vertexLine(
         matrices,
         buffer,
         (float)box.field_1323,
         (float)box.field_1322,
         (float)box.field_1321,
         (float)box.field_1320,
         (float)box.field_1322,
         (float)box.field_1324,
         color
      );
      Render2DEngine.endBuilding(buffer);
      RenderSystem.enableCull();
      endRender();
   }

   public static void drawSphere(class_4587 matrix, float radius, int slices, int stacks, int color) {
      float drho = (float) Math.PI / stacks;
      float dtheta = (float) (Math.PI * 2) / (slices - 1.0F);
      setupRender();

      for (int i = 1; i < stacks; i++) {
         float rho = i * drho;
         class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);
         RenderSystem.setShader(class_757::method_34540);

         for (int j = 0; j < slices; j++) {
            float theta = j * dtheta;
            float x = (float)(Math.cos(theta) * Math.sin(rho));
            float y = (float)(Math.sin(theta) * Math.sin(rho));
            float z = (float)Math.cos(rho);
            buffer.method_22918(matrix.method_23760().method_23761(), x * radius, y * radius, z * radius).method_39415(color);
         }

         Render2DEngine.endBuilding(buffer);
      }

      for (int j = 0; j < slices; j++) {
         float theta = j * dtheta;
         class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);
         RenderSystem.setShader(class_757::method_34540);

         for (int var20 = 0; var20 <= stacks; var20++) {
            float rho = var20 * drho;
            float x = (float)(Math.cos(theta) * Math.sin(rho));
            float y = (float)(Math.sin(theta) * Math.sin(rho));
            float z = (float)Math.cos(rho);
            buffer.method_22918(matrix.method_23760().method_23761(), x * radius, y * radius, z * radius).method_39415(color);
         }

         class_286.method_43433(buffer.method_60800());
      }

      endRender();
   }

   public static void drawCylinder(class_4587 stack, float radius, float height, int slices, int stacks, int color) {
      float da = (float)((Math.PI * 2) / slices);
      float dz = height / stacks;
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);
      RenderSystem.setShader(class_757::method_34540);
      float y = 0.0F;

      for (int j = 0; j <= stacks; j++) {
         for (int i = 0; i <= slices; i++) {
            float x = (float)Math.cos(i * da);
            float z = (float)Math.sin(i * da);
            buffer.method_22918(stack.method_23760().method_23761(), x * radius, y, z * radius).method_39415(color);
         }

         y += dz;
      }

      class_286.method_43433(buffer.method_60800());
      buffer = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);
      RenderSystem.setShader(class_757::method_34540);

      for (int i = 0; i <= slices; i++) {
         float x = (float)Math.cos(i * da);
         float z = (float)Math.sin(i * da);
         buffer.method_22918(stack.method_23760().method_23761(), x * radius, 0.0F, z * radius).method_39415(color);
         buffer.method_22918(stack.method_23760().method_23761(), x * radius, height, z * radius).method_39415(color);
      }

      class_286.method_43433(buffer.method_60800());
   }

   public static void drawCircle3D(class_4587 stack, class_1297 ent, float radius, int color, int points, boolean hudColor, int colorOffset) {
      setupRender();
      RenderSystem.setShader(class_757::method_34540);
      class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_29345, class_290.field_1576);
      double x = ent.field_6014 + (ent.method_23317() - ent.field_6014) * getTickDelta() - Module.mc.method_1561().field_4686.method_19326().method_10216();
      double y = ent.field_6036 + (ent.method_23318() - ent.field_6036) * getTickDelta() - Module.mc.method_1561().field_4686.method_19326().method_10214();
      double z = ent.field_5969 + (ent.method_23321() - ent.field_5969) * getTickDelta() - Module.mc.method_1561().field_4686.method_19326().method_10215();
      stack.method_22903();
      stack.method_22904(x, y, z);
      Matrix4f matrix = stack.method_23760().method_23761();

      for (int i = 0; i <= points; i++) {
         if (hudColor) {
            color = HudEditor.getColor(i * colorOffset).getRGB();
         }

         bufferBuilder.method_22918(matrix, (float)(radius * Math.cos(i * 6.28 / points)), 0.0F, (float)(radius * Math.sin(i * 6.28 / points)))
            .method_39415(color);
      }

      class_286.method_43433(bufferBuilder.method_60800());
      endRender();
      stack.method_22904(-x, -y, -z);
      stack.method_22909();
   }

   public static void drawOldTargetEsp(class_4587 stack, class_1297 target) {
      double cs = prevCircleStep + (circleStep - prevCircleStep) * getTickDelta();
      double prevSinAnim = absSinAnimation(cs - 0.45F);
      double sinAnim = absSinAnimation(cs);
      double x = target.field_6014
         + (target.method_23317() - target.field_6014) * getTickDelta()
         - Module.mc.method_1561().field_4686.method_19326().method_10216();
      double y = target.field_6036
         + (target.method_23318() - target.field_6036) * getTickDelta()
         - Module.mc.method_1561().field_4686.method_19326().method_10214()
         + prevSinAnim * target.method_17682();
      double z = target.field_5969
         + (target.method_23321() - target.field_5969) * getTickDelta()
         - Module.mc.method_1561().field_4686.method_19326().method_10215();
      double nextY = target.field_6036
         + (target.method_23318() - target.field_6036) * getTickDelta()
         - Module.mc.method_1561().field_4686.method_19326().method_10214()
         + sinAnim * target.method_17682();
      stack.method_22903();
      setupRender();
      RenderSystem.disableCull();
      RenderSystem.disableDepthTest();
      class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);
      RenderSystem.setShader(class_757::method_34540);

      for (int i = 0; i <= 30; i++) {
         float cos = (float)(x + Math.cos(i * 6.28 / 30.0) * target.method_17681() * 0.8);
         float sin = (float)(z + Math.sin(i * 6.28 / 30.0) * target.method_17681() * 0.8);
         bufferBuilder.method_22918(stack.method_23760().method_23761(), cos, (float)nextY, sin)
            .method_39415(Render2DEngine.injectAlpha(HudEditor.getColor(i), 170).getRGB());
         bufferBuilder.method_22918(stack.method_23760().method_23761(), cos, (float)y, sin)
            .method_39415(Render2DEngine.injectAlpha(HudEditor.getColor(i), 0).getRGB());
      }

      Render2DEngine.endBuilding(bufferBuilder);
      RenderSystem.enableCull();
      endRender();
      RenderSystem.enableDepthTest();
      stack.method_22909();
   }

   public static void renderGhosts(int espLength, int factor, float shaking, float amplitude, class_1297 target) {
      class_4184 camera = Module.mc.field_1773.method_19418();
      double tPosX = Render2DEngine.interpolate(target.field_6014, target.method_23317(), getTickDelta()) - camera.method_19326().field_1352;
      double tPosY = Render2DEngine.interpolate(target.field_6036, target.method_23318(), getTickDelta()) - camera.method_19326().field_1351;
      double tPosZ = Render2DEngine.interpolate(target.field_5969, target.method_23321(), getTickDelta()) - camera.method_19326().field_1350;
      float iAge = (float)Render2DEngine.interpolate(target.field_6012 - 1, target.field_6012, getTickDelta());
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      RenderSystem.setShaderTexture(0, TextureStorage.firefly);
      RenderSystem.setShader(class_757::method_34543);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1575);
      boolean canSee = Module.mc.field_1724.method_6057(target);
      if (canSee) {
         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(false);
      } else {
         RenderSystem.disableDepthTest();
      }

      for (int j = 0; j < 3; j++) {
         for (float i = 0.0F; i <= espLength; i += 0.23F) {
            double radians = Math.toRadians(((i / 2.0F + iAge * 1.5F) * factor + j * 120) % (factor * 360));
            double sinQuad = Math.sin(Math.toRadians(iAge * 2.5F + i * (j + 1)) * amplitude) / shaking;
            float offset = i / espLength;
            class_4587 matrices = new class_4587();
            matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
            matrices.method_22904(tPosX + Math.cos(radians) * target.method_17681(), tPosY + 1.0 + sinQuad, tPosZ + Math.sin(radians) * target.method_17681());
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            Matrix4f matrix = matrices.method_23760().method_23761();
            int color = Render2DEngine.applyOpacity(HudEditor.getColor((int)(180.0F * offset)), offset).getRGB();
            float scale = Math.max(0.24F * offset, 0.2F);
            buffer.method_22918(matrix, -scale, scale, 0.0F).method_22913(0.0F, 1.0F).method_39415(color);
            buffer.method_22918(matrix, scale, scale, 0.0F).method_22913(1.0F, 1.0F).method_39415(color);
            buffer.method_22918(matrix, scale, -scale, 0.0F).method_22913(1.0F, 0.0F).method_39415(color);
            buffer.method_22918(matrix, -scale, -scale, 0.0F).method_22913(0.0F, 0.0F).method_39415(color);
         }
      }

      class_286.method_43433(buffer.method_60800());
      RenderSystem.depthMask(true);
      RenderSystem.disableBlend();
   }

   public static void renderZenixC(int espLength, float scale, class_1297 target) {
      class_4184 camera = Module.mc.field_1773.method_19418();
      double tPosX = Render2DEngine.interpolate(target.field_6014, target.method_23317(), getTickDelta()) - camera.method_19326().field_1352;
      double tPosY = Render2DEngine.interpolate(target.field_6036, target.method_23318(), getTickDelta()) - camera.method_19326().field_1351;
      double tPosZ = Render2DEngine.interpolate(target.field_5969, target.method_23321(), getTickDelta()) - camera.method_19326().field_1350;
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      RenderSystem.setShaderTexture(0, TextureStorage.firefly);
      RenderSystem.setShader(class_757::method_34543);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      boolean canSee = Module.mc.field_1724.method_6057(target);
      if (canSee) {
         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(false);
      } else {
         RenderSystem.disableDepthTest();
      }

      long time = System.currentTimeMillis();
      float alphaFactor = 15.0F;

      for (int j = 0; j < 3; j++) {
         for (int i = 0; i <= espLength; i++) {
            double radius = 0.67;
            double distance = 10.0 + espLength * 0.2;
            double angle = 0.15F * (time - i * distance) / 30.0;
            double sin = Math.sin(angle) * radius;
            double cos = Math.cos(angle) * radius;
            double px = 0.0;
            double py = 0.0;
            double pz = 0.0;
            if (j == 0) {
               px = sin;
               py = cos;
               pz = -cos;
            } else if (j == 1) {
               px = -sin;
               py = -cos;
               pz = cos;
            } else if (j == 2) {
               px = -sin;
               py = sin;
               pz = -cos;
            }

            double gx = tPosX + px;
            double gy = tPosY + 0.75 + py;
            double gz = tPosZ + pz;
            int alpha = class_3532.method_15340(255 - i * (int)alphaFactor, 0, 255);
            int color = Render2DEngine.injectAlpha(HudEditor.getColor(i), alpha).getRGB();
            float progress = (float)i / espLength;
            float shaking = 0.27F;
            float ghostScale = shaking * scale * (float)Math.pow(1.0F - progress, 1.5);
            class_4587 matrices = new class_4587();
            matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
            matrices.method_22904(gx, gy, gz);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            Matrix4f matrix = matrices.method_23760().method_23761();
            buffer.method_22918(matrix, -ghostScale, ghostScale, 0.0F).method_22913(0.0F, 1.0F).method_39415(color);
            buffer.method_22918(matrix, ghostScale, ghostScale, 0.0F).method_22913(1.0F, 1.0F).method_39415(color);
            buffer.method_22918(matrix, ghostScale, -ghostScale, 0.0F).method_22913(1.0F, 0.0F).method_39415(color);
            buffer.method_22918(matrix, -ghostScale, -ghostScale, 0.0F).method_22913(0.0F, 0.0F).method_39415(color);
         }
      }

      class_286.method_43433(buffer.method_60800());
      if (canSee) {
         RenderSystem.depthMask(true);
         RenderSystem.disableDepthTest();
      } else {
         RenderSystem.enableDepthTest();
      }

      RenderSystem.disableBlend();
   }

   public static void renderCatlavan(int espLength, class_1297 target) {
      class_310 mc = class_310.method_1551();
      class_4184 camera = mc.field_1773.method_19418();
      double camX = camera.method_19326().field_1352;
      double camY = camera.method_19326().field_1351;
      double camZ = camera.method_19326().field_1350;
      double x = class_3532.method_16436(getTickDelta(), target.field_6014, target.method_23317());
      double y = class_3532.method_16436(getTickDelta(), target.field_6036, target.method_23318()) + target.method_17682() * 0.5 + 0.25;
      double z = class_3532.method_16436(getTickDelta(), target.field_5969, target.method_23321());
      double tPosX = x - camX;
      double tPosY = y - camY;
      double tPosZ = z - camZ;
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      RenderSystem.disableCull();
      RenderSystem.disableDepthTest();
      RenderSystem.setShader(class_757::method_34543);
      RenderSystem.setShaderTexture(0, TextureStorage.glow);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      long time = System.currentTimeMillis();
      double baseRadius = target.method_17681() * 0.9;
      double speed = 25.0;
      double distance = 15.0;
      float maxLen = espLength * 1.35F;

      for (int layer = 0; layer < 3; layer++) {
         double phaseOffset = layer * (Math.PI * 2.0 / 3.0);
         double radius = baseRadius + layer * 0.12;

         for (int i = 0; i < maxLen; i++) {
            double angle = 0.15 * (time - i * distance) / speed + phaseOffset;
            double s = Math.sin(angle) * radius;
            double c = Math.cos(angle) * radius;
            double px;
            double py;
            double pz;
            if (layer == 0) {
               px = tPosX + s;
               py = tPosY + c;
               pz = tPosZ - c;
            } else if (layer == 1) {
               px = tPosX - s;
               py = tPosY + s;
               pz = tPosZ - c;
            } else {
               px = tPosX - s;
               py = tPosY - s;
               pz = tPosZ + c;
            }

            class_4587 matrices = new class_4587();
            matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
            matrices.method_22904(px, py, pz);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
            Matrix4f mat = matrices.method_23760().method_23761();
            float offset = i / maxLen;
            float alpha = 1.0F;
            int color = Render2DEngine.applyOpacity(HudEditor.getColor((int)(180.0F * offset)), alpha).getRGB();
            float size = Math.max(0.28F * (1.0F - offset * 0.7F), 0.22F);
            buffer.method_22918(mat, -size, size, 0.0F).method_22913(0.0F, 1.0F).method_39415(color);
            buffer.method_22918(mat, size, size, 0.0F).method_22913(1.0F, 1.0F).method_39415(color);
            buffer.method_22918(mat, size, -size, 0.0F).method_22913(1.0F, 0.0F).method_39415(color);
            buffer.method_22918(mat, -size, -size, 0.0F).method_22913(0.0F, 0.0F).method_39415(color);
         }
      }

      class_286.method_43433(buffer.method_60800());
      RenderSystem.enableDepthTest();
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
   }

   public static void drawChain(class_4587 stack, class_1297 target, float radius, int links) {
      double x = class_3532.method_16436(Module.mc.method_60646().method_60637(true), target.field_6014, target.method_23317())
         - Module.mc.method_1561().field_4686.method_19326().field_1352;
      double y = class_3532.method_16436(Module.mc.method_60646().method_60637(true), target.field_6036, target.method_23318())
         + target.method_17682() * 0.6F
         - Module.mc.method_1561().field_4686.method_19326().field_1351;
      double z = class_3532.method_16436(Module.mc.method_60646().method_60637(true), target.field_5969, target.method_23321())
         - Module.mc.method_1561().field_4686.method_19326().field_1350;
      float movingValue = (float)(System.currentTimeMillis() - UniqueCore.initTime) / 10.0F % 360.0F;
      int baseColor = HudEditor.getColor(0).getRGB();
      float chainWidth = radius * 0.85F;
      float chainHeight = 0.55F;
      stack.method_22903();
      stack.method_22904(x, y, z);
      setupRender();
      RenderSystem.disableCull();
      RenderSystem.disableDepthTest();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShader(class_757::method_34543);
      RenderSystem.setShaderTexture(0, TextureStorage.chain);

      for (int chainIdx = 0; chainIdx < 3; chainIdx++) {
         stack.method_22903();
         float offset = chainIdx * 120.0F;
         float rotateSpeed = (movingValue + offset) % 360.0F;
         float gradusX = (float)(28.0 * Math.sin(Math.toRadians(rotateSpeed)));
         float gradusZ = (float)(28.0 * Math.cos(Math.toRadians(rotateSpeed)));
         stack.method_22907(class_7833.field_40718.rotationDegrees(gradusX));
         stack.method_22907(class_7833.field_40714.rotationDegrees(gradusZ));
         stack.method_46416(0.0F, chainIdx * 0.05F, 0.0F);
         Matrix4f matrix = stack.method_23760().method_23761();
         class_289 tess = class_289.method_1348();
         class_287 chainBuffer = tess.method_60827(class_5596.field_27382, class_290.field_1575);

         for (int i = 0; i < 360; i += 10) {
            float s1 = (float)Math.sin(Math.toRadians(i + rotateSpeed)) * chainWidth;
            float c1 = (float)Math.cos(Math.toRadians(i + rotateSpeed)) * chainWidth;
            float s2 = (float)Math.sin(Math.toRadians(i + 10 + rotateSpeed)) * chainWidth;
            float c2 = (float)Math.cos(Math.toRadians(i + 10 + rotateSpeed)) * chainWidth;
            float u1 = i / 360.0F * 5.0F;
            float u2 = (i + 10) / 360.0F * 5.0F;
            chainBuffer.method_22918(matrix, s1, -chainHeight / 2.0F, c1).method_22913(u1, 1.0F).method_39415(baseColor);
            chainBuffer.method_22918(matrix, s2, -chainHeight / 2.0F, c2).method_22913(u2, 1.0F).method_39415(baseColor);
            chainBuffer.method_22918(matrix, s2, chainHeight / 2.0F, c2).method_22913(u2, 0.0F).method_39415(baseColor);
            chainBuffer.method_22918(matrix, s1, chainHeight / 2.0F, c1).method_22913(u1, 0.0F).method_39415(baseColor);
         }

         class_286.method_43433(chainBuffer.method_60800());
         stack.method_22909();
      }

      RenderSystem.enableCull();
      RenderSystem.enableDepthTest();
      endRender();
      stack.method_22909();
   }

   public static void updateTargetESP() {
      prevCircleStep = circleStep;
      circleStep += 0.15F;
   }

   public static double absSinAnimation(double input) {
      return Math.abs(1.0 + Math.sin(input)) / 2.0;
   }

   public static class_243 interpolatePos(float prevposX, float prevposY, float prevposZ, float posX, float posY, float posZ) {
      double x = prevposX + (posX - prevposX) * getTickDelta() - Module.mc.method_1561().field_4686.method_19326().method_10216();
      double y = prevposY + (posY - prevposY) * getTickDelta() - Module.mc.method_1561().field_4686.method_19326().method_10214();
      double z = prevposZ + (posZ - prevposZ) * getTickDelta() - Module.mc.method_1561().field_4686.method_19326().method_10215();
      return new class_243(x, y, z);
   }

   public static void drawLineDebug(class_243 start, class_243 end, Color color) {
      DEBUG_LINE_QUEUE.add(new Render3DEngine.DebugLineAction(start, end, color));
   }

   public static void drawTexture(Vector4f vec, float size, class_243 cameraPos, float alpha) {
      class_289 tessellator = class_289.method_1348();
      class_287 bufferBuilder = tessellator.method_60827(class_5596.field_27382, class_290.field_1575);
      float x = (float)(vec.x() - cameraPos.field_1352);
      float y = (float)(vec.y() - cameraPos.field_1351);
      float z = (float)(vec.z() - cameraPos.field_1350);
      class_4587 matrices = new class_4587();
      matrices.method_46416(x, y, z);
      class_4184 camera = Module.mc.field_1773.method_19418();
      matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
      float halfSize = size / 2.0F;
      Matrix4f matrix = matrices.method_23760().method_23761();
      int color = Render2DEngine.injectAlpha(Color.WHITE, (int)(alpha * 255.0F)).getRGB();
      bufferBuilder.method_22918(matrix, -halfSize, -halfSize, 0.0F).method_22913(0.0F, 1.0F).method_39415(color);
      bufferBuilder.method_22918(matrix, halfSize, -halfSize, 0.0F).method_22913(1.0F, 1.0F).method_39415(color);
      bufferBuilder.method_22918(matrix, halfSize, halfSize, 0.0F).method_22913(1.0F, 0.0F).method_39415(color);
      bufferBuilder.method_22918(matrix, -halfSize, halfSize, 0.0F).method_22913(0.0F, 0.0F).method_39415(color);
      class_286.method_43433(bufferBuilder.method_60800());
   }

   public static float getTickDelta() {
      return Module.mc.method_60646().method_60637(true);
   }

   public record DebugLineAction(class_243 start, class_243 end, Color color) {
   }

   public record FadeAction(class_238 box, Color color, Color color2) {
   }

   public record FillAction(class_238 box, Color color) {
   }

   public record FillSideAction(class_238 box, Color color, class_2350 side) {
   }

   public record LineAction(class_243 start, class_243 end, Color color) {
   }

   public record OutlineAction(class_238 box, Color color, float lineWidth) {
   }

   public record OutlineSideAction(class_238 box, Color color, float lineWidth, class_2350 side) {
   }
}
