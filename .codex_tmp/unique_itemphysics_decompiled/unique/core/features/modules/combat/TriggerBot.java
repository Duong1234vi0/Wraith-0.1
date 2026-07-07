package unique.core.features.modules.combat;

import java.util.Random;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;

public final class TriggerBot extends Module {
   public final Setting<Float> attackRange = new Setting<>("Range", 3.0F, 1.0F, 7.0F);
   public final Setting<BooleanSettingGroup> smartCrit = new Setting<>("SmartCrit", new BooleanSettingGroup(true));
   public final Setting<Boolean> onlySpace = new Setting<>("OnlyCrit", false).addToGroup(this.smartCrit);
   public final Setting<Boolean> autoJump = new Setting<>("AutoJump", false).addToGroup(this.smartCrit);
   public final Setting<Boolean> ignoreWalls = new Setting<>("IgnoreWalls", false);
   public final Setting<Boolean> pauseEating = new Setting<>("PauseWhileEating", false);
   public final Setting<Integer> minDelay = new Setting<>("RandomDelayMin", 2, 0, 20);
   public final Setting<Integer> maxDelay = new Setting<>("RandomDelayMax", 13, 0, 20);
   private int delay;
   private final Random random = new Random();

   public TriggerBot() {
      super("TriggerBot", Module.Category.COMBAT);
   }

   @EventHandler
   public void onAttack(PlayerUpdateEvent e) {
      if (!mc.field_1724.method_6115() || !this.pauseEating.getValue()) {
         if (!mc.field_1690.field_1903.method_1434() && mc.field_1724.method_24828() && this.autoJump.getValue()) {
            mc.field_1724.method_6043();
         }

         if (!this.autoCrit() && this.delay > 0) {
            this.delay--;
         } else {
            class_1297 ent = Managers.PLAYER
               .getRtxTarget(mc.field_1724.method_36454(), mc.field_1724.method_36455(), this.attackRange.getValue(), this.ignoreWalls.getValue());
            if (ent != null && !Managers.FRIEND.isFriend(ent.method_5477().getString())) {
               mc.field_1761.method_2918(mc.field_1724, ent);
               mc.field_1724.method_6104(class_1268.field_5808);
               this.delay = this.random.nextInt(this.minDelay.getValue(), this.maxDelay.getValue() + 1);
            }
         }
      }
   }

   private boolean autoCrit() {
      boolean reasonForSkipCrit = !this.smartCrit.getValue().isEnabled()
         || mc.field_1724.method_31549().field_7479
         || mc.field_1724.method_6128()
         || ModuleManager.elytraPlus.isEnabled()
         || mc.field_1724.method_6059(class_1294.field_5919)
         || mc.field_1724.method_21754()
         || mc.field_1687.method_8320(class_2338.method_49638(mc.field_1724.method_19538())).method_26204() == class_2246.field_10343;
      if (mc.field_1724.field_6017 > 1.0F && mc.field_1724.field_6017 < 1.14) {
         return false;
      } else if (ModuleManager.aura.getAttackCooldown() < (mc.field_1724.method_24828() ? 1.0F : 0.9F)) {
         return false;
      } else {
         boolean mergeWithTargetStrafe = !ModuleManager.targetStrafe.isEnabled() || !ModuleManager.targetStrafe.jump.getValue();
         boolean mergeWithSpeed = !ModuleManager.speed.isEnabled() || mc.field_1724.method_24828();
         if (!mc.field_1690.field_1903.method_1434() && mergeWithTargetStrafe && mergeWithSpeed && !this.onlySpace.getValue() && !this.autoJump.getValue()) {
            return true;
         } else if (mc.field_1724.method_5771()) {
            return true;
         } else if (!mc.field_1690.field_1903.method_1434() && ModuleManager.aura.isAboveWater()) {
            return true;
         } else {
            return reasonForSkipCrit ? true : !mc.field_1724.method_24828() && mc.field_1724.field_6017 > 0.0F;
         }
      }
   }
}
