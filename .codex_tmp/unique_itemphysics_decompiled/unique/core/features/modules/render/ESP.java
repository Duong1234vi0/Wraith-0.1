package unique.core.features.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_1295;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1541;
import net.minecraft.class_1657;
import net.minecraft.class_1684;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2580;
import net.minecraft.class_2586;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_332;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_9381;
import net.minecraft.class_293.class_5596;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector4d;
import unique.core.core.Managers;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;
import unique.core.injection.accesors.IAreaEffectCloudEntity;
import unique.core.injection.accesors.IBeaconBlockEntity;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;
import unique.core.utility.render.animation.AnimationUtility;

public class ESP extends Module {
   private final Setting<Boolean> lingeringPotions = new Setting<>("LingeringPotions", false);
   private final Setting<Boolean> tntFuse = new Setting<>("TNTFuse", false);
   private final Setting<Float> tntrange = new Setting<>("TNTRange", 8.0F, 0.0F, 8.0F);
   private final Setting<ColorSetting> tntFuseText = new Setting<>("TNTFuseText", new ColorSetting(new Color(-1)), v -> this.tntFuse.getValue());
   private final Setting<Boolean> tntRadius = new Setting<>("TNTRadius", false);
   private final Setting<ColorSetting> tntRadiusColor = new Setting<>("TNTSphereColor", new ColorSetting(new Color(-1)), v -> this.tntRadius.getValue());
   private final Setting<Boolean> beaconRadius = new Setting<>("BeaconRadius", false);
   private final Setting<Boolean> keepY = new Setting<>("KeepY", false, v -> this.beaconRadius.getValue());
   private final Setting<ColorSetting> sphereColor = new Setting<>("SphereColor", new ColorSetting(new Color(-1)), v -> this.beaconRadius.getValue());
   private final Setting<ColorSetting> beakonColor = new Setting<>("BeakonColor", new ColorSetting(new Color(-1)), v -> this.beaconRadius.getValue());
   private final Setting<Boolean> burrow = new Setting<>("Burrow", false);
   private final Setting<ColorSetting> burrowTextColor = new Setting<>("BurrowTextColor", new ColorSetting(new Color(-1)), v -> this.burrow.getValue());
   private final Setting<ColorSetting> burrowColor = new Setting<>("BurrowColor", new ColorSetting(new Color(-1)), v -> this.burrow.getValue());
   private final Setting<Boolean> pearls = new Setting<>("Pearls", false);
   private final Setting<Boolean> dizorentRadius = new Setting<>("DizorentRadius", true);
   private final Setting<ColorSetting> dizorentColor = new Setting<>(
      "DizorentColor", new ColorSetting(new Color(-1291783732, true)), v -> this.dizorentRadius.getValue()
   );
   private final Setting<SettingGroup> boxEsp = new Setting<>("Box", new SettingGroup(false, 0));
   private final Setting<Boolean> players = new Setting<>("Players", true).addToGroup(this.boxEsp);
   private final Setting<Boolean> friends = new Setting<>("Friends", true).addToGroup(this.boxEsp);
   private final Setting<Boolean> crystals = new Setting<>("Crystals", true).addToGroup(this.boxEsp);
   private final Setting<Boolean> creatures = new Setting<>("Creatures", false).addToGroup(this.boxEsp);
   private final Setting<Boolean> monsters = new Setting<>("Monsters", false).addToGroup(this.boxEsp);
   private final Setting<Boolean> ambients = new Setting<>("Ambients", false).addToGroup(this.boxEsp);
   private final Setting<Boolean> others = new Setting<>("Others", false).addToGroup(this.boxEsp);
   private final Setting<Boolean> outline = new Setting<>("Outline", true).addToGroup(this.boxEsp);
   private final Setting<ESP.Colors> colorMode = new Setting<>("ColorMode", ESP.Colors.SyncColor).addToGroup(this.boxEsp);
   private final Setting<Boolean> renderHealth = new Setting<>("renderHealth", true).addToGroup(this.boxEsp);
   private final Setting<SettingGroup> boxColors = new Setting<>("BoxColors", new SettingGroup(false, 0));
   private final Setting<ColorSetting> playersC = new Setting<>("PlayersC", new ColorSetting(new Color(16749056))).addToGroup(this.boxColors);
   private final Setting<ColorSetting> friendsC = new Setting<>("FriendsC", new ColorSetting(new Color(3211008))).addToGroup(this.boxColors);
   private final Setting<ColorSetting> crystalsC = new Setting<>("CrystalsC", new ColorSetting(new Color(48127))).addToGroup(this.boxColors);
   private final Setting<ColorSetting> creaturesC = new Setting<>("CreaturesC", new ColorSetting(new Color(10527910))).addToGroup(this.boxColors);
   private final Setting<ColorSetting> monstersC = new Setting<>("MonstersC", new ColorSetting(new Color(16711680))).addToGroup(this.boxColors);
   private final Setting<ColorSetting> ambientsC = new Setting<>("AmbientsC", new ColorSetting(new Color(8061183))).addToGroup(this.boxColors);
   private final Setting<ColorSetting> othersC = new Setting<>("OthersC", new ColorSetting(new Color(16711778))).addToGroup(this.boxColors);
   public final Setting<ColorSetting> healthB = new Setting<>("healthB", new ColorSetting(new Color(16716032))).addToGroup(this.boxColors);
   public final Setting<ColorSetting> healthU = new Setting<>("healthU", new ColorSetting(new Color(3145472))).addToGroup(this.boxColors);
   private float dizorentAnimation = 0.0F;

   public ESP() {
      super("ESP", Module.Category.RENDER);
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (!mc.field_1690.field_1842) {
         if (this.lingeringPotions.getValue()) {
            for (class_1297 ent : mc.field_1687.method_18112()) {
               if (ent instanceof class_1295 aece) {
                  double x = aece.method_23317() - mc.method_1561().field_4686.method_19326().method_10216();
                  double y = aece.method_23318() - mc.method_1561().field_4686.method_19326().method_10214();
                  double z = aece.method_23321() - mc.method_1561().field_4686.method_19326().method_10215();
                  float middle = aece.method_5599();
                  stack.method_22903();
                  stack.method_22904(x, y, z);
                  Render3DEngine.setupRender();
                  RenderSystem.disableDepthTest();
                  RenderSystem.setShader(class_757::method_34540);
                  class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);

                  for (int i = 0; i <= 360; i += 6) {
                     double v = Math.sin(Math.toRadians(i));
                     double u = Math.cos(Math.toRadians(i));
                     bufferBuilder.method_22918(stack.method_23760().method_23761(), (float)u * middle, 0.0F, (float)v * middle)
                        .method_39415(Render2DEngine.injectAlpha(new Color(this.getAreaCloudColor(aece)), 100).getRGB());
                     bufferBuilder.method_22918(stack.method_23760().method_23761(), 0.0F, 0.0F, 0.0F)
                        .method_39415(Render2DEngine.injectAlpha(new Color(this.getAreaCloudColor(aece)), 0).getRGB());
                  }

                  Render2DEngine.endBuilding(bufferBuilder);
                  RenderSystem.setShader(class_757::method_34540);
                  bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);

                  for (int i = 0; i <= 360; i += 6) {
                     double v = Math.sin(Math.toRadians(i));
                     double u = Math.cos(Math.toRadians(i));
                     bufferBuilder.method_22918(stack.method_23760().method_23761(), (float)u * middle, 0.0F, (float)v * middle)
                        .method_39415(Render2DEngine.injectAlpha(new Color(this.getAreaCloudColor(aece)), 255).getRGB());
                     bufferBuilder.method_22918(stack.method_23760().method_23761(), (float)u * (middle - 0.04F), 0.0F, (float)v * (middle - 0.04F))
                        .method_39415(Render2DEngine.injectAlpha(new Color(this.getAreaCloudColor(aece)), 255).getRGB());
                  }

                  Render2DEngine.endBuilding(bufferBuilder);
                  Render3DEngine.endRender();
                  RenderSystem.enableDepthTest();
                  stack.method_22904(-x, -y, -z);
                  stack.method_22909();
                  RenderSystem.disableDepthTest();
                  class_4587 matrices = new class_4587();
                  class_4184 camera = mc.field_1773.method_19418();
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
                  matrices.method_22904(x, y, z);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
                  RenderSystem.enableBlend();
                  RenderSystem.defaultBlendFunc();
                  matrices.method_46416(0.0F, 0.0F, 0.0F);
                  matrices.method_22905(-0.05F, -0.05F, 0.0F);
                  FontRenderers.modules.drawCenteredString(matrices, String.format("%.1f", aece.method_5599() * 10.0F - 5.0F), 0.0, -10.0, -1);
                  RenderSystem.disableBlend();
                  RenderSystem.enableDepthTest();
               }
            }
         }

         if (this.dizorentRadius.getValue()) {
            this.dizorentAnimation = AnimationUtility.fast(
               this.dizorentAnimation, mc.field_1724.method_6047().method_7909() == class_1802.field_8449 ? 10.0F : 0.0F, 15.0F
            );
            if (mc.field_1724.method_6047().method_7909() == class_1802.field_8449) {
               double x = Render2DEngine.interpolate(mc.field_1724.field_6014, mc.field_1724.method_23317(), Render3DEngine.getTickDelta())
                  - mc.method_1561().field_4686.method_19326().method_10216();
               double y = Render2DEngine.interpolate(mc.field_1724.field_6036, mc.field_1724.method_23318(), Render3DEngine.getTickDelta())
                  - mc.method_1561().field_4686.method_19326().method_10214();
               double z = Render2DEngine.interpolate(mc.field_1724.field_5969, mc.field_1724.method_23321(), Render3DEngine.getTickDelta())
                  - mc.method_1561().field_4686.method_19326().method_10215();
               stack.method_22903();
               stack.method_22904(x, y, z);
               Render3DEngine.setupRender();
               RenderSystem.disableDepthTest();
               RenderSystem.setShader(class_757::method_34540);
               class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);

               for (int i = 0; i <= 360; i += 6) {
                  double v = Math.sin(Math.toRadians(i));
                  double u = Math.cos(Math.toRadians(i));
                  bufferBuilder.method_22918(stack.method_23760().method_23761(), (float)u * this.dizorentAnimation, 0.0F, (float)v * this.dizorentAnimation)
                     .method_39415(Render2DEngine.injectAlpha(new Color(this.dizorentColor.getValue().getColor()), 100).getRGB());
                  bufferBuilder.method_22918(stack.method_23760().method_23761(), 0.0F, 0.0F, 0.0F)
                     .method_39415(Render2DEngine.injectAlpha(new Color(this.dizorentColor.getValue().getColor()), 0).getRGB());
               }

               Render2DEngine.endBuilding(bufferBuilder);
               RenderSystem.setShader(class_757::method_34540);
               bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);

               for (int i = 0; i <= 360; i += 6) {
                  double v = Math.sin(Math.toRadians(i));
                  double u = Math.cos(Math.toRadians(i));
                  bufferBuilder.method_22918(stack.method_23760().method_23761(), (float)u * this.dizorentAnimation, 0.0F, (float)v * this.dizorentAnimation)
                     .method_39415(Render2DEngine.injectAlpha(new Color(this.dizorentColor.getValue().getColor()), 255).getRGB());
                  bufferBuilder.method_22918(
                        stack.method_23760().method_23761(), (float)u * (this.dizorentAnimation - 0.04F), 0.0F, (float)v * (this.dizorentAnimation - 0.04F)
                     )
                     .method_39415(Render2DEngine.injectAlpha(new Color(this.dizorentColor.getValue().getColor()), 255).getRGB());
               }

               Render2DEngine.endBuilding(bufferBuilder);
               Render3DEngine.endRender();
               RenderSystem.enableDepthTest();
               stack.method_22904(-x, -y, -z);
               stack.method_22909();

               for (class_1657 pl : Managers.ASYNC.getAsyncPlayers()) {
                  if (!(mc.field_1724.method_5707(pl.method_19538()) > 100.0) && pl != mc.field_1724) {
                     Render3DEngine.drawTargetEsp(stack, pl);
                  }
               }
            }
         }

         if (this.beaconRadius.getValue()) {
            for (class_2586 be : StorageEsp.getBlockEntities()) {
               if (be instanceof class_2580 bbe) {
                  double x = be.method_11016().method_10263() - mc.method_1561().field_4686.method_19326().method_10216();
                  double y = be.method_11016().method_10264() - mc.method_1561().field_4686.method_19326().method_10214();
                  double z = be.method_11016().method_10260() - mc.method_1561().field_4686.method_19326().method_10215();
                  Render3DEngine.drawBoxOutline(new class_238(be.method_11016()), this.beakonColor.getValue().getColorObject(), 2.0F);
                  float range = ((IBeaconBlockEntity)bbe).getLevel() * 10 + 11;
                  boolean ky = this.keepY.getValue();
                  stack.method_22903();
                  stack.method_22904(x, ky ? -10.0 : y, z);
                  Render3DEngine.drawCylinder(stack, range, ky ? 20.0F : 256.0F, 20, ky ? 5 : 20, this.sphereColor.getValue().getColor());
                  stack.method_22904(-x, ky ? 10.0 : y, -z);
                  stack.method_22909();
               }
            }
         }

         if (this.burrow.getValue()) {
            for (class_1657 plx : mc.field_1687.method_18456()) {
               class_2338 blockPos = class_2338.method_49638(plx.method_19538().method_1031(0.0, 0.15F, 0.0));
               class_2248 block = mc.field_1687.method_8320(blockPos).method_26204();
               double x = blockPos.method_10263() - mc.method_1561().field_4686.method_19326().method_10216();
               double y = blockPos.method_10264() - mc.method_1561().field_4686.method_19326().method_10214();
               double z = blockPos.method_10260() - mc.method_1561().field_4686.method_19326().method_10215();
               if (block == class_2246.field_10540
                  || block == class_2246.field_22423
                  || block == class_2246.field_10535
                  || block == class_2246.field_10432
                  || block == class_2246.field_10481
                  || block == class_2246.field_10177) {
                  Render3DEngine.drawBoxOutline(new class_238(blockPos), this.burrowColor.getValue().getColorObject(), 2.0F);
                  RenderSystem.disableDepthTest();
                  class_4587 matrices = new class_4587();
                  class_4184 camera = mc.field_1773.method_19418();
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
                  matrices.method_22904(x + 0.5, y + 0.5, z + 0.5);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
                  RenderSystem.enableBlend();
                  RenderSystem.defaultBlendFunc();
                  matrices.method_46416(0.0F, 0.0F, 0.0F);
                  matrices.method_22905(-0.025F, -0.025F, 0.0F);
                  FontRenderers.modules.drawCenteredString(matrices, "BURROW", 0.0, -5.0, this.burrowTextColor.getValue().getColor());
                  RenderSystem.disableBlend();
                  RenderSystem.enableDepthTest();
               }
            }
         }

         if (this.tntFuse.getValue() || this.tntRadius.getValue()) {
            for (class_1297 entx : mc.field_1687.method_18112()) {
               if (entx instanceof class_1541 tnt) {
                  double x = tnt.field_6014
                     + (tnt.method_19538().method_10216() - tnt.field_6014) * Render3DEngine.getTickDelta()
                     - mc.method_1561().field_4686.method_19326().method_10216();
                  double y = tnt.field_6036
                     + (tnt.method_19538().method_10214() - tnt.field_6036) * Render3DEngine.getTickDelta()
                     - mc.method_1561().field_4686.method_19326().method_10214();
                  double z = tnt.field_5969
                     + (tnt.method_19538().method_10215() - tnt.field_5969) * Render3DEngine.getTickDelta()
                     - mc.method_1561().field_4686.method_19326().method_10215();
                  if (this.tntFuse.getValue()) {
                     RenderSystem.disableDepthTest();
                     class_4587 matrices = new class_4587();
                     class_4184 camera = mc.field_1773.method_19418();
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
                     matrices.method_22904(x, y + 0.5, z);
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
                     RenderSystem.enableBlend();
                     RenderSystem.defaultBlendFunc();
                     matrices.method_46416(0.0F, 0.0F, 0.0F);
                     matrices.method_22905(-0.025F, -0.025F, 0.0F);
                     FontRenderers.modules
                        .drawCenteredString(matrices, String.format("%.1f", tnt.method_6969() / 20.0F) + "s", 0.0, -5.0, this.tntFuseText.getValue().getColor());
                     RenderSystem.disableBlend();
                     RenderSystem.enableDepthTest();
                  }

                  if (this.tntRadius.getValue()) {
                     stack.method_22903();
                     stack.method_22904(x, y, z);
                     Render3DEngine.drawSphere(stack, this.tntrange.getValue(), 20, 20, this.tntRadiusColor.getValue().getColor());
                     stack.method_22904(-x, -y, -z);
                     stack.method_22909();
                  }
               }
            }
         }
      }
   }

   @Override
   public void onRender2D(class_332 context) {
      if (!mc.field_1690.field_1842) {
         if (this.pearls.getValue()) {
            for (class_1297 ent : mc.field_1687.method_18112()) {
               if (ent instanceof class_1684 pearl) {
                  float xOffset = mc.method_22683().method_4486() / 2.0F;
                  float yOffset = mc.method_22683().method_4502() / 2.0F;
                  float xPos = (float)(pearl.field_6014 + (pearl.method_19538().method_10216() - pearl.field_6014) * Render3DEngine.getTickDelta());
                  float zPos = (float)(pearl.field_5969 + (pearl.method_19538().method_10215() - pearl.field_5969) * Render3DEngine.getTickDelta());
                  float yaw = getRotations(new class_241(xPos, zPos)) - mc.field_1724.method_36454();
                  context.method_51448().method_46416(xOffset, yOffset, 0.0F);
                  context.method_51448().method_22907(class_7833.field_40718.rotationDegrees(yaw));
                  context.method_51448().method_46416(-xOffset, -yOffset, 0.0F);
                  Render2DEngine.drawTracerPointer(
                     context.method_51448(), xOffset, yOffset - 50.0F, 12.5F, 0.5F, 3.63F, true, true, HudEditor.getColor(1).getRGB()
                  );
                  context.method_51448().method_46416(xOffset, yOffset, 0.0F);
                  context.method_51448().method_22907(class_7833.field_40718.rotationDegrees(-yaw));
                  context.method_51448().method_46416(-xOffset, -yOffset, 0.0F);
                  RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                  FontRenderers.modules
                     .drawCenteredString(
                        context.method_51448(),
                        String.format("%.1f", mc.field_1724.method_5739(pearl)) + "m",
                        (float)(Math.sin(Math.toRadians(yaw)) * 50.0) + xOffset,
                        (float)(yOffset - Math.cos(Math.toRadians(yaw)) * 50.0) - 20.0F,
                        -1
                     );
               }
            }
         }

         Matrix4f matrix = context.method_51448().method_23760().method_23761();
         Render2DEngine.setupRender();
         RenderSystem.setShader(class_757::method_34540);
         class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);

         for (class_1297 entx : mc.field_1687.method_18112()) {
            if (this.shouldRender(entx)) {
               this.drawBox(bufferBuilder, entx, matrix);
            }
         }

         Render2DEngine.endBuilding(bufferBuilder);
         Render2DEngine.endRender();
      }
   }

   public boolean shouldRender(class_1297 entity) {
      if (entity == null) {
         return false;
      } else if (mc.field_1724 == null) {
         return false;
      } else if (entity instanceof class_1657) {
         if (entity == mc.field_1724) {
            return false;
         } else {
            return Managers.FRIEND.isFriend((class_1657)entity) ? this.friends.getValue() : this.players.getValue();
         }
      } else if (entity instanceof class_1511) {
         return this.crystals.getValue();
      } else {
         return switch (entity.method_5864().method_5891()) {
            case field_6294, field_6300 -> this.creatures.getValue();
            case field_6302 -> this.monsters.getValue();
            case field_6303, field_24460 -> this.ambients.getValue();
            default -> this.others.getValue();
         };
      }
   }

   public Color getEntityColor(class_1297 entity) {
      if (entity == null) {
         return new Color(-1);
      } else if (entity instanceof class_1657) {
         return Managers.FRIEND.isFriend((class_1657)entity) ? this.friendsC.getValue().getColorObject() : this.playersC.getValue().getColorObject();
      } else if (entity instanceof class_1511) {
         return this.crystalsC.getValue().getColorObject();
      } else {
         return switch (entity.method_5864().method_5891()) {
            case field_6294, field_6300 -> this.creaturesC.getValue().getColorObject();
            case field_6302 -> this.monstersC.getValue().getColorObject();
            case field_6303, field_24460 -> this.ambientsC.getValue().getColorObject();
            default -> this.othersC.getValue().getColorObject();
         };
      }
   }

   public void drawBox(class_287 bufferBuilder, @NotNull class_1297 ent, Matrix4f matrix) {
      class_243[] vectors = getVectors(ent);
      Color col = this.getEntityColor(ent);
      Vector4d position = null;

      for (class_243 vector : vectors) {
         vector = Render3DEngine.worldSpaceToScreenSpace(new class_243(vector.field_1352, vector.field_1351, vector.field_1350));
         if (vector.field_1350 > 0.0 && vector.field_1350 < 1.0) {
            if (position == null) {
               position = new Vector4d(vector.field_1352, vector.field_1351, vector.field_1350, 0.0);
            }

            position.x = Math.min(vector.field_1352, position.x);
            position.y = Math.min(vector.field_1351, position.y);
            position.z = Math.max(vector.field_1352, position.z);
            position.w = Math.max(vector.field_1351, position.w);
         }
      }

      if (position != null) {
         double posX = position.x;
         double posY = position.y;
         double endPosX = position.z;
         double endPosY = position.w;
         if (this.outline.getValue()) {
            Render2DEngine.setRectPoints(
               bufferBuilder,
               matrix,
               (float)(posX - 1.0),
               (float)posY,
               (float)(posX + 0.5),
               (float)(endPosY + 0.5),
               Color.BLACK,
               Color.BLACK,
               Color.BLACK,
               Color.BLACK
            );
            Render2DEngine.setRectPoints(
               bufferBuilder,
               matrix,
               (float)(posX - 1.0),
               (float)(posY - 0.5),
               (float)(endPosX + 0.5),
               (float)(posY + 0.5 + 0.5),
               Color.BLACK,
               Color.BLACK,
               Color.BLACK,
               Color.BLACK
            );
            Render2DEngine.setRectPoints(
               bufferBuilder,
               matrix,
               (float)(endPosX - 0.5 - 0.5),
               (float)posY,
               (float)(endPosX + 0.5),
               (float)(endPosY + 0.5),
               Color.BLACK,
               Color.BLACK,
               Color.BLACK,
               Color.BLACK
            );
            Render2DEngine.setRectPoints(
               bufferBuilder,
               matrix,
               (float)(posX - 1.0),
               (float)(endPosY - 0.5 - 0.5),
               (float)(endPosX + 0.5),
               (float)(endPosY + 0.5),
               Color.BLACK,
               Color.BLACK,
               Color.BLACK,
               Color.BLACK
            );
         }

         switch ((ESP.Colors)this.colorMode.getValue()) {
            case SyncColor:
               Render2DEngine.setRectPoints(
                  bufferBuilder,
                  matrix,
                  (float)(posX - 0.5),
                  (float)posY,
                  (float)(posX + 0.5 - 0.5),
                  (float)endPosY,
                  HudEditor.getColor(270),
                  HudEditor.getColor(0),
                  HudEditor.getColor(0),
                  HudEditor.getColor(270)
               );
               Render2DEngine.setRectPoints(
                  bufferBuilder,
                  matrix,
                  (float)posX,
                  (float)(endPosY - 0.5),
                  (float)endPosX,
                  (float)endPosY,
                  HudEditor.getColor(0),
                  HudEditor.getColor(180),
                  HudEditor.getColor(180),
                  HudEditor.getColor(0)
               );
               Render2DEngine.setRectPoints(
                  bufferBuilder,
                  matrix,
                  (float)(posX - 0.5),
                  (float)posY,
                  (float)endPosX,
                  (float)(posY + 0.5),
                  HudEditor.getColor(180),
                  HudEditor.getColor(90),
                  HudEditor.getColor(90),
                  HudEditor.getColor(180)
               );
               Render2DEngine.setRectPoints(
                  bufferBuilder,
                  matrix,
                  (float)(endPosX - 0.5),
                  (float)posY,
                  (float)endPosX,
                  (float)endPosY,
                  HudEditor.getColor(90),
                  HudEditor.getColor(270),
                  HudEditor.getColor(270),
                  HudEditor.getColor(90)
               );
               break;
            case Custom:
               Render2DEngine.setRectPoints(
                  bufferBuilder, matrix, (float)(posX - 0.5), (float)posY, (float)(posX + 0.5 - 0.5), (float)endPosY, col, col, col, col
               );
               Render2DEngine.setRectPoints(bufferBuilder, matrix, (float)posX, (float)(endPosY - 0.5), (float)endPosX, (float)endPosY, col, col, col, col);
               Render2DEngine.setRectPoints(bufferBuilder, matrix, (float)(posX - 0.5), (float)posY, (float)endPosX, (float)(posY + 0.5), col, col, col, col);
               Render2DEngine.setRectPoints(bufferBuilder, matrix, (float)(endPosX - 0.5), (float)posY, (float)endPosX, (float)endPosY, col, col, col, col);
         }

         if (ent instanceof class_1309 lent && lent.method_6032() != 0.0F && this.renderHealth.getValue()) {
            Render2DEngine.setRectPoints(
               bufferBuilder, matrix, (float)(posX - 5.0), (float)posY, (float)posX - 3.0F, (float)endPosY, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK
            );
            switch ((ESP.Colors)this.colorMode.getValue()) {
               case SyncColor:
                  Render2DEngine.setRectPoints(
                     bufferBuilder,
                     matrix,
                     (float)(posX - 5.0),
                     (float)(endPosY + (posY - endPosY) * lent.method_6032() / lent.method_6063()),
                     (float)posX - 3.0F,
                     (float)endPosY,
                     HudEditor.getColor(90),
                     HudEditor.getColor(90),
                     HudEditor.getColor(270),
                     HudEditor.getColor(270)
                  );
                  break;
               case Custom:
                  Render2DEngine.setRectPoints(
                     bufferBuilder,
                     matrix,
                     (float)(posX - 5.0),
                     (float)(endPosY + (posY - endPosY) * lent.method_6032() / lent.method_6063()),
                     (float)posX - 3.0F,
                     (float)endPosY,
                     this.healthB.getValue().getColorObject(),
                     this.healthB.getValue().getColorObject(),
                     this.healthU.getValue().getColorObject(),
                     this.healthU.getValue().getColorObject()
                  );
            }
         }
      }
   }

   @NotNull
   private static class_243[] getVectors(@NotNull class_1297 ent) {
      double x = ent.field_6014 + (ent.method_23317() - ent.field_6014) * Render3DEngine.getTickDelta();
      double y = ent.field_6036 + (ent.method_23318() - ent.field_6036) * Render3DEngine.getTickDelta();
      double z = ent.field_5969 + (ent.method_23321() - ent.field_5969) * Render3DEngine.getTickDelta();
      class_238 axisAlignedBB2 = ent.method_5829();
      class_238 axisAlignedBB = new class_238(
         axisAlignedBB2.field_1323 - ent.method_23317() + x - 0.05,
         axisAlignedBB2.field_1322 - ent.method_23318() + y,
         axisAlignedBB2.field_1321 - ent.method_23321() + z - 0.05,
         axisAlignedBB2.field_1320 - ent.method_23317() + x + 0.05,
         axisAlignedBB2.field_1325 - ent.method_23318() + y + 0.15,
         axisAlignedBB2.field_1324 - ent.method_23321() + z + 0.05
      );
      return new class_243[]{
         new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1322, axisAlignedBB.field_1321),
         new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1325, axisAlignedBB.field_1321),
         new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1322, axisAlignedBB.field_1321),
         new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1325, axisAlignedBB.field_1321),
         new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1322, axisAlignedBB.field_1324),
         new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1325, axisAlignedBB.field_1324),
         new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1322, axisAlignedBB.field_1324),
         new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1325, axisAlignedBB.field_1324)
      };
   }

   private int getAreaCloudColor(class_1295 ent) {
      return ent.method_5600() instanceof class_9381 effect ? ((IAreaEffectCloudEntity)ent).getPotionContentsComponent().method_8064() : -1;
   }

   public static float getRotations(class_241 vec) {
      if (mc.field_1724 == null) {
         return 0.0F;
      } else {
         double x = vec.field_1343 - mc.field_1724.method_19538().field_1352;
         double z = vec.field_1342 - mc.field_1724.method_19538().field_1350;
         return (float)(-(Math.atan2(x, z) * (180.0 / Math.PI)));
      }
   }

   public static enum Colors {
      SyncColor,
      Custom;
   }
}
