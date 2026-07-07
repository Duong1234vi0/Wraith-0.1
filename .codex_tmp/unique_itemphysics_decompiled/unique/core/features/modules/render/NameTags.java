package unique.core.features.modules.render;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_1934;
import net.minecraft.class_243;
import net.minecraft.class_266;
import net.minecraft.class_308;
import net.minecraft.class_332;
import net.minecraft.class_5250;
import net.minecraft.class_5321;
import net.minecraft.class_640;
import net.minecraft.class_8646;
import net.minecraft.class_9013;
import net.minecraft.class_9025;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector4d;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.features.modules.misc.NameProtect;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;
import unique.core.utility.render.TextureStorage;

public class NameTags extends Module {
   private final Map<class_5321<class_1887>, String> encMap = new HashMap<>();
   private final Setting<Boolean> self = new Setting<>("Self", false);
   private final Setting<Float> scale = new Setting<>("Scale", 1.0F, 0.1F, 10.0F);
   private final Setting<Boolean> gamemode = new Setting<>("Gamemode", false);
   private final Setting<Boolean> ping = new Setting<>("Ping", false);
   private final Setting<Boolean> hp = new Setting<>("HP", true);
   private final Setting<Boolean> distance = new Setting<>("Distance", true);
   private final Setting<Boolean> funtimeHp = new Setting<>("FunTimeHp", false);
   private final Setting<Boolean> ignoreBots = new Setting<>("IgnoreBots", false);
   private final Setting<NameTags.OutlineColor> outline = new Setting<>("OutlineType", NameTags.OutlineColor.New);
   private final Setting<ColorSetting> outlineColor = new Setting<>("OutlineColor", new ColorSetting(Integer.MIN_VALUE));
   private final Setting<ColorSetting> fillColorA = new Setting<>("Fill", new ColorSetting(Integer.MIN_VALUE));

   public NameTags() {
      super("NameTags", Module.Category.RENDER);
      this.encMap.put(class_1893.field_9107, "B");
      this.encMap.put(class_1893.field_9111, "P");
      this.encMap.put(class_1893.field_9118, "S");
      this.encMap.put(class_1893.field_9131, "E");
      this.encMap.put(class_1893.field_9119, "U");
      this.encMap.put(class_1893.field_9103, "PO");
      this.encMap.put(class_1893.field_9097, "T");
   }

   @Override
   public void onRender2D(class_332 context) {
      if (!mc.field_1690.field_1842) {
         for (class_1657 ent : mc.field_1687.method_18456()) {
            if ((ent != mc.field_1724 || !mc.field_1690.method_31044().method_31034() && this.self.getValue())
               && (getEntityPing(ent) > 0 || !this.ignoreBots.getValue())) {
               double x = ent.field_6014 + (ent.method_23317() - ent.field_6014) * Render3DEngine.getTickDelta();
               double y = ent.field_6036 + (ent.method_23318() - ent.field_6036) * Render3DEngine.getTickDelta();
               double z = ent.field_5969 + (ent.method_23321() - ent.field_5969) * Render3DEngine.getTickDelta();
               float scale = this.scale.getValue();
               class_243 vector = new class_243(x, y + 2.0, z);
               Vector4d position = null;
               vector = Render3DEngine.worldSpaceToScreenSpace(vector);
               if (vector.field_1350 > 0.0 && vector.field_1350 < 1.0) {
                  position = new Vector4d(vector.field_1352, vector.field_1351, vector.field_1350, 0.0);
                  position.x = Math.min(vector.field_1352, position.x);
                  position.y = Math.min(vector.field_1351, position.y);
                  position.z = Math.max(vector.field_1352, position.z);
               }

               String final_string = "";
               if (this.ping.getValue()) {
                  final_string = final_string + this.getPingColor(getEntityPing(ent)) + getEntityPing(ent) + "ms " + class_124.field_1068;
               }

               if (this.gamemode.getValue()) {
                  final_string = final_string + this.translateGamemode(getEntityGamemode(ent)) + " ";
               }

               if (ModuleManager.nameProtect.isEnabled() && NameProtect.hideFriends.getValue() && Managers.FRIEND.isFriend(ent.method_5476().getString())) {
                  final_string = final_string + NameProtect.getCustomName(ent.method_5476().getString()) + " ";
               } else {
                  final_string = final_string + NameProtect.getCustomName(ent.method_5476().getString()) + " ";
               }

               if (this.hp.getValue()) {
                  final_string = final_string + this.getHealthColor(this.getHealth(ent)) + round2(this.getHealth(ent)) + " ";
               }

               if (this.distance.getValue()) {
                  final_string = final_string + String.format("%.1f", mc.field_1724.method_5739(ent)) + "M ";
               }

               if (position != null) {
                  double posX = position.x;
                  double posY = position.y;
                  double endPosX = position.z;
                  double maxEnchantY = 0.0;
                  float diff = (float)(endPosX - posX) / 2.0F;
                  float textWidth = FontRenderers.sf_bold.getStringWidth(final_string);
                  float tagX = (float)((posX + diff - textWidth / 2.0F) * 1.0);
                  ArrayList<class_1799> stacks = new ArrayList<>();
                  stacks.add(ent.method_6079());
                  stacks.add((class_1799)ent.method_31548().field_7548.get(0));
                  stacks.add((class_1799)ent.method_31548().field_7548.get(1));
                  stacks.add((class_1799)ent.method_31548().field_7548.get(2));
                  stacks.add((class_1799)ent.method_31548().field_7548.get(3));
                  stacks.add(ent.method_6047());
                  context.method_51448().method_22903();
                  context.method_51448().method_46416(tagX - 2.0F + (textWidth + 4.0F) / 2.0F, (float)(posY - 13.0) + 6.5F, 0.0F);
                  context.method_51448().method_22905(scale, scale, 1.0F);
                  context.method_51448().method_46416(-(tagX - 2.0F + (textWidth + 4.0F) / 2.0F), -((float)(posY - 13.0 + 6.5)), 0.0F);
                  float item_offset = 0.0F;

                  for (class_1799 armorComponent : stacks) {
                     if (!armorComponent.method_7960()) {
                        context.method_51448().method_22903();
                        context.method_51448().method_22904(posX - 55.0 + item_offset, (float)(posY - 33.0), 0.0);
                        context.method_51448().method_22905(1.1F, 1.1F, 1.1F);
                        class_308.method_24210();
                        context.method_51427(armorComponent, 0, 0);
                        context.method_51431(mc.field_1772, armorComponent, 0, 0);
                        context.method_51448().method_22909();
                     }

                     item_offset += 18.0F;
                  }

                  Color color = this.fillColorA.getValue().getColorObject();
                  NameTags.OutlineColor cl = this.outline.getValue();
                  if (cl == NameTags.OutlineColor.New) {
                     Render2DEngine.drawRectWithOutline(
                        context.method_51448(),
                        tagX - 2.0F,
                        (float)(posY - 13.0),
                        textWidth + 4.0F,
                        11.0F,
                        color,
                        this.outlineColor.getValue().getColorObject()
                     );
                  } else {
                     Render2DEngine.drawRect(context.method_51448(), tagX - 2.0F, (float)(posY - 13.0), textWidth + 4.0F, 11.0F, color);
                  }

                  FontRenderers.sf_bold.drawString(context.method_51448(), final_string, tagX, (float)(posY - 10.0), -1);
                  if (Managers.TELEMETRY.getOnlinePlayers().contains(ent.method_7334().getName())) {
                     Render2DEngine.drawRect(context.method_51448(), tagX - 14.0F, (float)(posY - 13.0), 12.0F, 11.0F, color.brighter().brighter());
                     RenderSystem.enableBlend();
                     RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
                     Color lColor = HudEditor.getColor(0);
                     RenderSystem.setShaderColor(lColor.getRed() / 255.0F, lColor.getGreen() / 255.0F, lColor.getBlue() / 255.0F, 1.0F);
                     RenderSystem.setShaderTexture(0, TextureStorage.miniLogo);
                     Render2DEngine.renderTexture(
                        context.method_51448(), tagX - 13.0F, (float)(posY - 12.5), 10.0, 10.0, 0.0F, 0.0F, 256.0, 256.0, 256.0, 256.0
                     );
                     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                     RenderSystem.disableBlend();
                  }

                  switch (cl) {
                     case Sync:
                        Render2DEngine.drawRect(context.method_51448(), tagX - 3.0F, (float)(posY - 14.0), textWidth + 6.0F, 1.0F, HudEditor.getColor(270));
                        Render2DEngine.drawRect(context.method_51448(), tagX - 3.0F, (float)(posY - 3.0), textWidth + 6.0F, 1.0F, HudEditor.getColor(0));
                        Render2DEngine.drawRect(context.method_51448(), tagX - 3.0F, (float)(posY - 14.0), 1.0F, 11.0F, HudEditor.getColor(180));
                        Render2DEngine.drawRect(context.method_51448(), tagX + textWidth + 2.0F, (float)(posY - 14.0), 1.0F, 11.0F, HudEditor.getColor(90));
                        break;
                     case Custom:
                        Color oc = this.outlineColor.getValue().getColorObject();
                        Render2DEngine.drawRect(context.method_51448(), tagX - 3.0F, (float)(posY - 14.0), textWidth + 6.0F, 1.0F, oc);
                        Render2DEngine.drawRect(context.method_51448(), tagX - 3.0F, (float)(posY - 3.0), textWidth + 6.0F, 1.0F, oc);
                        Render2DEngine.drawRect(context.method_51448(), tagX - 3.0F, (float)(posY - 14.0), 1.0F, 11.0F, oc);
                        Render2DEngine.drawRect(context.method_51448(), tagX + textWidth + 2.0F, (float)(posY - 14.0), 1.0F, 11.0F, oc);
                     case None:
                     case New:
                  }

                  context.method_51448().method_22909();
               }
            }
         }
      }
   }

   public static int getEntityPing(class_1657 entity) {
      if (mc.method_1562() == null) {
         return 0;
      } else {
         class_640 playerListEntry = mc.method_1562().method_2871(entity.method_5667());
         return playerListEntry == null ? 0 : playerListEntry.method_2959();
      }
   }

   public static class_1934 getEntityGamemode(class_1657 entity) {
      if (entity == null) {
         return null;
      } else {
         class_640 playerListEntry = mc.method_1562().method_2871(entity.method_5667());
         return playerListEntry == null ? null : playerListEntry.method_2958();
      }
   }

   private String translateGamemode(class_1934 gamemode) {
      if (gamemode == null) {
         return "[BOT]";
      } else {
         return switch (gamemode) {
            case field_9215 -> "[S]";
            case field_9220 -> "[C]";
            case field_9219 -> "[SP]";
            case field_9216 -> "[A]";
            default -> throw new MatchException(null, null);
         };
      }
   }

   public float getHealth(class_1657 ent) {
      if ((mc.method_1562() == null || mc.method_1562().method_45734() == null || !mc.method_1562().method_45734().field_3761.contains("funtime"))
         && !this.funtimeHp.getValue()) {
         return ent.method_6032() + ent.method_6067();
      } else {
         class_266 scoreBoard = null;
         String resolvedHp = "";
         if (ent.method_7327().method_1189(class_8646.field_45158) != null) {
            scoreBoard = ent.method_7327().method_1189(class_8646.field_45158);
            if (scoreBoard != null) {
               class_9013 readableScoreboardScore = ent.method_7327().method_55430(ent, scoreBoard);
               class_5250 text2 = class_9013.method_55398(readableScoreboardScore, scoreBoard.method_55380(class_9025.field_47566));
               resolvedHp = text2.getString();
            }
         }

         float numValue = 0.0F;

         try {
            numValue = Float.parseFloat(resolvedHp);
         } catch (NumberFormatException var6) {
         }

         return numValue;
      }
   }

   @NotNull
   public String getHealthColor(float health) {
      if (health <= 15.0F && health > 7.0F) {
         return class_124.field_1054 + "";
      } else {
         return health > 15.0F ? class_124.field_1060 + "" : class_124.field_1061 + "";
      }
   }

   @NotNull
   public String getPingColor(int ping) {
      if (ping <= 60) {
         return class_124.field_1060 + "";
      } else {
         return ping > 60 && ping < 120 ? class_124.field_1054 + "" : class_124.field_1061 + "";
      }
   }

   public static float round2(float value) {
      if (!Float.isNaN(value) && !Float.isInfinite(value)) {
         BigDecimal bd = new BigDecimal((double)value);
         bd = bd.setScale(1, RoundingMode.HALF_UP);
         return bd.floatValue();
      } else {
         return 1.0F;
      }
   }

   private void drawBackground(class_332 context, int x, int y, float[] colors) {
      RenderSystem.disableBlend();
      RenderSystem.setShaderColor(colors[0], colors[1], colors[2], 1.0F);
      RenderSystem.texParameter(3553, 10240, 9729);
      RenderSystem.texParameter(3553, 10241, 9987);
      context.method_25290(TextureStorage.container, x, y, 0.0F, 0.0F, 176, 67, 176, 67);
      RenderSystem.enableBlend();
   }

   private static enum OutlineColor {
      Sync,
      Custom,
      None,
      New;
   }
}
