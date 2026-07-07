package unique.core.features.modules.base;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1511;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2824;
import net.minecraft.class_2879;
import net.minecraft.class_1297.class_5529;
import org.jetbrains.annotations.NotNull;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.setting.impl.ColorSetting;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.Timer;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.PlayerUtility;
import unique.core.utility.player.SearchInvResult;
import unique.core.utility.render.BlockAnimationUtility;

public abstract class PlaceModule extends Module {
   protected final Setting<Float> range = new Setting<>("Range", 5.0F, 0.0F, 7.0F);
   protected final Setting<InteractionUtility.Interact> interact = new Setting<>("Interact", InteractionUtility.Interact.Strict);
   protected final Setting<PlaceModule.InteractMode> placeMode = new Setting<>("Place Mode", PlaceModule.InteractMode.Normal);
   protected final Setting<InteractionUtility.Rotate> rotate = new Setting<>("Rotate", InteractionUtility.Rotate.None);
   protected final Setting<Boolean> swing = new Setting<>("Swing", false);
   protected final Setting<BooleanSettingGroup> crystalBreaker = new Setting<>("Crystal Breaker", new BooleanSettingGroup(false));
   protected final Setting<Integer> crystalAge = new Setting<>("CrystalAge", 0, 0, 20).addToGroup(this.crystalBreaker);
   protected final Setting<Integer> breakDelay = new Setting<>("Break Delay", 100, 1, 1000).addToGroup(this.crystalBreaker);
   protected final Setting<Boolean> remove = new Setting<>("Remove", false).addToGroup(this.crystalBreaker);
   protected final Setting<PlaceModule.InteractMode> breakCrystalMode = new Setting<>("Break Mode", PlaceModule.InteractMode.Normal)
      .addToGroup(this.crystalBreaker);
   protected final Setting<Boolean> antiWeakness = new Setting<>("Anti Weakness", false).addToGroup(this.crystalBreaker);
   protected final Setting<SettingGroup> blocks = new Setting<>("Blocks", new SettingGroup(false, 0));
   protected final Setting<Boolean> obsidian = new Setting<>("Obsidian", true).addToGroup(this.blocks);
   protected final Setting<Boolean> anchor = new Setting<>("Anchor", false).addToGroup(this.blocks);
   protected final Setting<Boolean> enderChest = new Setting<>("EnderChest", true).addToGroup(this.blocks);
   protected final Setting<Boolean> netherite = new Setting<>("Netherite", false).addToGroup(this.blocks);
   protected final Setting<Boolean> cryingObsidian = new Setting<>("Crying Obsidian", true).addToGroup(this.blocks);
   protected final Setting<Boolean> dirt = new Setting<>("Dirt", false).addToGroup(this.blocks);
   protected final Setting<Boolean> oakPlanks = new Setting<>("OakPlanks", false).addToGroup(this.blocks);
   protected final Setting<SettingGroup> pause = new Setting<>("Pause", new SettingGroup(false, 0));
   protected final Setting<Boolean> eatPause = new Setting<>("On Eat", false).addToGroup(this.pause);
   protected final Setting<Boolean> breakPause = new Setting<>("On Break", false).addToGroup(this.pause);
   protected final Setting<BooleanSettingGroup> render = new Setting<>("Render", new BooleanSettingGroup(true));
   protected final Setting<BlockAnimationUtility.BlockRenderMode> renderMode = new Setting<>("Render Mode", BlockAnimationUtility.BlockRenderMode.All)
      .addToGroup(this.render);
   protected final Setting<BlockAnimationUtility.BlockAnimationMode> animationMode = new Setting<>(
         "Animation Mode", BlockAnimationUtility.BlockAnimationMode.Fade
      )
      .addToGroup(this.render);
   protected final Setting<ColorSetting> renderFillColor = new Setting<>("Fill Color", new ColorSetting(HudEditor.getColor(0))).addToGroup(this.render);
   protected final Setting<ColorSetting> renderLineColor = new Setting<>("Line Color", new ColorSetting(HudEditor.getColor(0))).addToGroup(this.render);
   protected final Setting<Integer> renderLineWidth = new Setting<>("Line Width", 2, 1, 5).addToGroup(this.render);
   public final Timer inactivityTimer = new Timer();
   public final Timer pauseTimer = new Timer();
   protected final Timer attackTimer = new Timer();

   public PlaceModule(@NotNull String name, @NotNull Module.Category category) {
      super(name, category);
   }

   protected boolean shouldPause() {
      return this.eatPause.getValue() && PlayerUtility.isEating() || this.breakPause.getValue() && PlayerUtility.isMining() || !this.pauseTimer.passedMs(350L);
   }

   protected boolean placeBlock(class_2338 pos) {
      return this.placeBlock(pos, this.crystalBreaker.getValue().isEnabled(), this.placeMode.getValue(), this.rotate.getValue());
   }

   protected boolean placeBlock(class_2338 pos, boolean ignoreEntities) {
      return this.placeBlock(pos, ignoreEntities, this.placeMode.getValue(), this.rotate.getValue());
   }

   protected boolean placeBlock(class_2338 pos, InteractionUtility.Rotate rotate) {
      return this.placeBlock(pos, this.crystalBreaker.getValue().isEnabled(), this.placeMode.getValue(), rotate);
   }

   protected boolean placeBlock(class_2338 pos, PlaceModule.InteractMode mode) {
      return this.placeBlock(pos, this.crystalBreaker.getValue().isEnabled(), mode, this.rotate.getValue());
   }

   protected boolean placeBlock(class_2338 pos, boolean ignoreEntities, PlaceModule.InteractMode mode, InteractionUtility.Rotate rotate) {
      if (this.shouldPause()) {
         return false;
      } else {
         boolean validInteraction = false;
         SearchInvResult result = this.getBlockResult();
         if (!result.found()) {
            return false;
         } else {
            if (this.crystalBreaker.getValue().isEnabled() && mc.field_1687 != null && this.attackTimer.passedMs(this.breakDelay.getValue().intValue())) {
               mc.field_1687.method_18467(class_1511.class, new class_238(pos)).stream().findFirst().ifPresent(this::breakCrystal);
            }

            if (mode == PlaceModule.InteractMode.Packet) {
               validInteraction = InteractionUtility.placeBlock(
                  pos, rotate, this.interact.getValue(), InteractionUtility.PlaceMode.Packet, result.slot(), true, ignoreEntities
               );
            }

            if (mode == PlaceModule.InteractMode.Normal) {
               validInteraction = InteractionUtility.placeBlock(
                  pos, rotate, this.interact.getValue(), InteractionUtility.PlaceMode.Normal, result.slot(), true, ignoreEntities
               );
            }

            if (validInteraction && mc.field_1724 != null) {
               if (this.render.getValue().isEnabled()) {
                  this.renderBlock(pos);
               }

               if (this.swing.getValue()) {
                  mc.field_1724.method_6104(class_1268.field_5808);
               }
            }

            return validInteraction;
         }
      }
   }

   protected void breakCrystal(class_1511 entity) {
      if (mc.field_1724 != null
         && mc.field_1687 != null
         && mc.field_1761 != null
         && !this.shouldPause()
         && this.attackTimer.passedMs(this.breakDelay.getValue().intValue())
         && !(mc.field_1724.method_5858(entity) > this.range.getPow2Value())
         && entity.field_6012 >= this.crystalAge.getValue()) {
         int preSlot = mc.field_1724.method_31548().field_7545;
         if (this.antiWeakness.getValue() && mc.field_1724.method_6059(class_1294.field_5911)) {
            SearchInvResult result = InventoryUtility.getAntiWeaknessItem();
            if (!result.found()) {
               return;
            }

            result.switchTo();
         }

         if (this.breakCrystalMode.getValue() == PlaceModule.InteractMode.Packet) {
            this.sendPacket(class_2824.method_34206(entity, mc.field_1724.method_5715()));
         }

         if (this.breakCrystalMode.getValue() == PlaceModule.InteractMode.Normal) {
            mc.field_1761.method_2918(mc.field_1724, entity);
         }

         this.sendPacket(new class_2879(class_1268.field_5808));
         this.attackTimer.reset();
         if (this.remove.getValue()) {
            entity.method_5768();
            entity.method_31745(class_5529.field_26998);
            entity.method_36209();
         }

         if (this.antiWeakness.getValue() && mc.field_1724.method_6059(class_1294.field_5911)) {
            InventoryUtility.switchTo(preSlot);
         }
      }
   }

   protected boolean canPlaceBlock(class_2338 pos, boolean ignoreEntities) {
      return InteractionUtility.canPlaceBlock(pos, this.interact.getValue(), ignoreEntities);
   }

   protected void renderBlock(class_2338 pos) {
      BlockAnimationUtility.renderBlock(
         pos,
         this.renderLineColor.getValue().getColorObject(),
         this.renderLineWidth.getValue(),
         this.renderFillColor.getValue().getColorObject(),
         this.animationMode.getValue(),
         this.renderMode.getValue()
      );
   }

   protected SearchInvResult getBlockResult() {
      List<class_2248> canUseBlocks = new ArrayList<>();
      if (mc.field_1724 == null) {
         return SearchInvResult.notFound();
      } else {
         if (this.obsidian.getValue()) {
            canUseBlocks.add(class_2246.field_10540);
         }

         if (this.enderChest.getValue()) {
            canUseBlocks.add(class_2246.field_10443);
         }

         if (this.cryingObsidian.getValue()) {
            canUseBlocks.add(class_2246.field_22423);
         }

         if (this.netherite.getValue()) {
            canUseBlocks.add(class_2246.field_22108);
         }

         if (this.anchor.getValue()) {
            canUseBlocks.add(class_2246.field_23152);
         }

         if (this.dirt.getValue()) {
            canUseBlocks.addAll(List.of(class_2246.field_10566, class_2246.field_10219, class_2246.field_10520));
         }

         if (this.oakPlanks.getValue()) {
            canUseBlocks.addAll(List.of(class_2246.field_10161, class_2246.field_10148, class_2246.field_10075));
         }

         class_1799 mainHandStack = mc.field_1724.method_6047();
         if (mainHandStack != class_1799.field_8037 && mainHandStack.method_7909() instanceof class_1747) {
            class_2248 blockFromMainHandItem = ((class_1747)mainHandStack.method_7909()).method_7711();
            if (canUseBlocks.contains(blockFromMainHandItem)) {
               return new SearchInvResult(mc.field_1724.method_31548().field_7545, true, mainHandStack);
            }
         }

         return InventoryUtility.findBlockInHotBar(canUseBlocks);
      }
   }

   public void pause() {
      this.pauseTimer.reset();
   }

   protected static enum InteractMode {
      Packet,
      Normal;
   }
}
