package unique.core.features.modules.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4587;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import unique.core.core.Managers;
import unique.core.events.impl.EventTick;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.Timer;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public final class AutoWeb extends Module {
   private final Setting<Integer> range = new Setting<>("Range", 5, 1, 7);
   private final Setting<Integer> placeWallRange = new Setting<>("WallRange", 5, 1, 7);
   private final Setting<AutoWeb.PlaceTiming> placeTiming = new Setting<>("PlaceTiming", AutoWeb.PlaceTiming.Default);
   private final Setting<Integer> blocksPerTick = new Setting<>("Block/Tick", 8, 1, 12, v -> this.placeTiming.getValue() == AutoWeb.PlaceTiming.Default);
   private final Setting<Integer> placeDelay = new Setting<>("Delay/Place", 3, 0, 10);
   private final Setting<InteractionUtility.Interact> interact = new Setting<>("Interact", InteractionUtility.Interact.Strict);
   private final Setting<InteractionUtility.PlaceMode> placeMode = new Setting<>("PlaceMode", InteractionUtility.PlaceMode.Normal);
   private final Setting<InteractionUtility.Rotate> rotate = new Setting<>("Rotate", InteractionUtility.Rotate.None);
   private final Setting<SettingGroup> selection = new Setting<>("Selection", new SettingGroup(false, 0));
   private final Setting<Boolean> head = new Setting<>("Head", true).addToGroup(this.selection);
   private final Setting<Boolean> leggs = new Setting<>("Leggs", true).addToGroup(this.selection);
   private final Setting<Boolean> surround = new Setting<>("Surround", true).addToGroup(this.selection);
   private final Setting<Boolean> upperSurround = new Setting<>("UpperSurround", false).addToGroup(this.selection);
   private final Setting<SettingGroup> renderCategory = new Setting<>("Render", new SettingGroup(false, 0));
   private final Setting<AutoWeb.RenderMode> renderMode = new Setting<>("Render Mode", AutoWeb.RenderMode.Fade).addToGroup(this.renderCategory);
   private final Setting<ColorSetting> renderFillColor = new Setting<>("Render Fill Color", new ColorSetting(HudEditor.getColor(0)))
      .addToGroup(this.renderCategory);
   private final Setting<ColorSetting> renderLineColor = new Setting<>("Render Line Color", new ColorSetting(HudEditor.getColor(0)))
      .addToGroup(this.renderCategory);
   private final Setting<Integer> renderLineWidth = new Setting<>("Render Line Width", 2, 1, 5).addToGroup(this.renderCategory);
   private final Setting<Integer> effectDurationMs = new Setting<>("Effect Duration (MS)", 500, 0, 10000).addToGroup(this.renderCategory);
   private final ArrayList<class_2338> sequentialBlocks = new ArrayList<>();
   public static Timer inactivityTimer = new Timer();
   private final Map<class_2338, Long> renderPoses = new ConcurrentHashMap<>();
   private int delay = 0;

   public AutoWeb() {
      super("AutoWeb", Module.Category.COMBAT);
   }

   @Override
   public void onRender3D(class_4587 stack) {
      this.renderPoses
         .forEach(
            (pos, time) -> {
               if (System.currentTimeMillis() - time > this.effectDurationMs.getValue().intValue()) {
                  this.renderPoses.remove(pos);
               } else {
                  switch ((AutoWeb.RenderMode)this.renderMode.getValue()) {
                     case Fade:
                        Render3DEngine.drawFilledBox(
                           stack,
                           new class_238(pos),
                           Render2DEngine.injectAlpha(
                              this.renderFillColor.getValue().getColorObject(), (int)(100.0F * (1.0F - (float)(System.currentTimeMillis() - time) / 500.0F))
                           )
                        );
                        Render3DEngine.drawBoxOutline(
                           new class_238(pos),
                           Render2DEngine.injectAlpha(
                              this.renderLineColor.getValue().getColorObject(), (int)(100.0F * (1.0F - (float)(System.currentTimeMillis() - time) / 500.0F))
                           ),
                           this.renderLineWidth.getValue().intValue()
                        );
                        break;
                     case Decrease:
                        float scale = 1.0F - (float)(System.currentTimeMillis() - time) / 500.0F;
                        class_238 box = new class_238(
                           pos.method_10263(), pos.method_10264(), pos.method_10260(), pos.method_10263(), pos.method_10264(), pos.method_10260()
                        );
                        Render3DEngine.drawFilledBox(
                           stack,
                           box.method_1002(scale, scale, scale).method_989(0.5 + scale * 0.5, 0.5 + scale * 0.5, 0.5 + scale * 0.5),
                           Render2DEngine.injectAlpha(
                              this.renderFillColor.getValue().getColorObject(), (int)(100.0F * (1.0F - (float)(System.currentTimeMillis() - time) / 500.0F))
                           )
                        );
                        Render3DEngine.drawBoxOutline(
                           box.method_1002(scale, scale, scale).method_989(0.5 + scale * 0.5, 0.5 + scale * 0.5, 0.5 + scale * 0.5),
                           this.renderLineColor.getValue().getColorObject(),
                           this.renderLineWidth.getValue().intValue()
                        );
                  }
               }
            }
         );
   }

   @Override
   public void onEnable() {
      this.sequentialBlocks.clear();
      this.renderPoses.clear();
   }

   @EventHandler
   public void onTick(EventTick e) {
      class_2338 targetBlock1 = this.getSequentialPos();
      if (targetBlock1 != null) {
         if (this.delay > 0) {
            this.delay--;
         } else {
            InventoryUtility.saveSlot();
            if (this.placeTiming.getValue() == AutoWeb.PlaceTiming.Default) {
               int placed = 0;

               while (placed < this.blocksPerTick.getValue()) {
                  class_2338 targetBlock = this.getSequentialPos();
                  if (targetBlock == null
                     || !InteractionUtility.placeBlock(
                        targetBlock, this.rotate.getValue(), this.interact.getValue(), this.placeMode.getValue(), this.getSlot(), false, true
                     )) {
                     break;
                  }

                  placed++;
                  this.renderPoses.put(targetBlock, System.currentTimeMillis());
                  this.delay = this.placeDelay.getValue();
                  inactivityTimer.reset();
               }
            } else if (this.placeTiming.getValue() == AutoWeb.PlaceTiming.Vanilla) {
               class_2338 targetBlock = this.getSequentialPos();
               if (targetBlock == null) {
                  return;
               }

               if (InteractionUtility.placeBlock(
                  targetBlock, this.rotate.getValue(), this.interact.getValue(), this.placeMode.getValue(), this.getSlot(), false, true
               )) {
                  this.sequentialBlocks.add(targetBlock);
                  this.renderPoses.put(targetBlock, System.currentTimeMillis());
                  this.delay = this.placeDelay.getValue();
                  inactivityTimer.reset();
               }
            }

            InventoryUtility.returnSlot();
         }
      }
   }

   private class_2338 getSequentialPos() {
      class_1657 target = Managers.COMBAT.getNearestTarget(this.range.getValue().intValue());
      if (target != null) {
         class_2338 targetBp = class_2338.method_49638(target.method_19538());
         ArrayList<class_2338> positions = new ArrayList<>();
         if (this.leggs.getValue()) {
            positions.add(targetBp);
         }

         if (this.head.getValue()) {
            positions.add(targetBp.method_10084());
         }

         if (this.surround.getValue()) {
            positions.add(targetBp.method_10078());
            positions.add(targetBp.method_10067());
            positions.add(targetBp.method_10072());
            positions.add(targetBp.method_10095());
         }

         if (this.upperSurround.getValue()) {
            positions.add(targetBp.method_10078().method_10084());
            positions.add(targetBp.method_10067().method_10084());
            positions.add(targetBp.method_10072().method_10084());
            positions.add(targetBp.method_10095().method_10084());
         }

         for (class_2338 bp : positions) {
            class_3965 wallCheck = mc.field_1687
               .method_17742(
                  new class_3959(
                     InteractionUtility.getEyesPos(mc.field_1724),
                     bp.method_46558().method_43206(class_2350.field_11036, 0.5),
                     class_3960.field_17558,
                     class_242.field_1348,
                     mc.field_1724
                  )
               );
            if ((
                  wallCheck == null
                     || wallCheck.method_17783() != class_240.field_1332
                     || wallCheck.method_17777() == bp
                     || !(InteractionUtility.squaredDistanceFromEyes(bp.method_46558()) > this.placeWallRange.getPow2Value())
               )
               && InteractionUtility.canPlaceBlock(bp, this.interact.getValue(), true)
               && mc.field_1687.method_8320(bp).method_45474()) {
               return bp;
            }
         }
      }

      return null;
   }

   private int getSlot() {
      List<class_2248> canUseBlocks = new ArrayList<>();
      canUseBlocks.add(class_2246.field_10343);
      int slot = -1;
      class_1799 mainhandStack = mc.field_1724.method_6047();
      if (mainhandStack != class_1799.field_8037 && mainhandStack.method_7909() instanceof class_1747) {
         class_2248 blockFromMainhandItem = ((class_1747)mainhandStack.method_7909()).method_7711();
         if (canUseBlocks.contains(blockFromMainhandItem)) {
            slot = mc.field_1724.method_31548().field_7545;
         }
      }

      if (slot == -1) {
         for (int i = 0; i < 9; i++) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (stack != class_1799.field_8037 && stack.method_7909() instanceof class_1747) {
               class_2248 blockFromItem = ((class_1747)stack.method_7909()).method_7711();
               if (canUseBlocks.contains(blockFromItem)) {
                  slot = i;
                  break;
               }
            }
         }
      }

      return slot;
   }

   private static enum PlaceTiming {
      Default,
      Vanilla;
   }

   private static enum RenderMode {
      Fade,
      Decrease;
   }
}
