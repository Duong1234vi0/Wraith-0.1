package unique.core.features.modules.movement;

import java.awt.Color;
import java.util.Comparator;
import java.util.stream.StreamSupport;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1713;
import net.minecraft.class_1781;
import net.minecraft.class_1802;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2815;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_2828.class_2831;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.features.modules.combat.Aura;
import unique.core.setting.Setting;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.SearchInvResult;
import unique.core.utility.render.Render3DEngine;

public class ElytraTarget extends Module {
   public static class_243 auraPredict;
   public final Setting<Float> range = new Setting<>("Range", 64.0F, 1.0F, 128.0F);
   public final Setting<Float> predictTicks = new Setting<>("PredictTicks", 1.8F, 1.0F, 5.0F);
   public final Setting<Boolean> alwaysPredict = new Setting<>("AlwaysPredict", true);
   public final Setting<Integer> delay = new Setting<>("Delay", 15, 1, 128);
   public final Setting<Boolean> offhandFirework = new Setting<>("OffhandFirework", true);
   public final Setting<Float> minSpeed = new Setting<>("MinSpeed", 35.0F, 1.0F, 200.0F);
   public final Setting<Boolean> peekAssist = new Setting<>("PeekAssist", true);
   public final Setting<Boolean> lockTarget = new Setting<>("LockTarget", true);
   public final Setting<Boolean> boost = new Setting<>("Boost", true);
   public final Setting<Float> deg0 = new Setting<>("0 Degrees", 0.12F, 0.0F, 1.0F, v -> this.boost.getValue());
   public final Setting<Float> deg5 = new Setting<>("5 Degrees", 0.15F, 0.0F, 1.0F, v -> this.boost.getValue());
   public final Setting<Float> deg10 = new Setting<>("10 Degrees", 0.18F, 0.0F, 1.0F, v -> this.boost.getValue());
   public final Setting<Float> deg15 = new Setting<>("15 Degrees", 0.22F, 0.0F, 1.0F, v -> this.boost.getValue());
   public final Setting<Float> deg20 = new Setting<>("20 Degrees", 0.28F, 0.0F, 1.0F, v -> this.boost.getValue());
   public final Setting<Float> deg25 = new Setting<>("25 Degrees", 0.35F, 0.0F, 1.0F, v -> this.boost.getValue());
   public final Setting<Float> deg30 = new Setting<>("30 Degrees", 0.45F, 0.0F, 1.0F, v -> this.boost.getValue());
   public final Setting<Float> deg35 = new Setting<>("35 Degrees", 0.55F, 0.0F, 1.0F, v -> this.boost.getValue());
   public final Setting<Float> deg40 = new Setting<>("40 Degrees", 0.65F, 0.0F, 1.0F, v -> this.boost.getValue());
   public final Setting<Float> deg50 = new Setting<>("50 Degrees", 0.8F, 0.0F, 1.0F, v -> this.boost.getValue());
   private int rocketTimer;
   private int boostDelay;
   private class_238 predictedBox;
   private class_1297 currentTarget;

   public ElytraTarget() {
      super("ElytraTarget", Module.Category.MOVEMENT);
   }

   @EventHandler
   public void onUpdate(PlayerUpdateEvent event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (!this.alwaysPredict.getValue() && !mc.field_1724.method_6128()) {
            this.reset();
         } else {
            this.findTargetInRange();
            if (this.currentTarget instanceof class_1309 living) {
               float ticks = class_3532.method_15363(this.predictTicks.getValue(), 1.0F, mc.field_1724.method_6128() ? 2.6F : 2.0F);
               class_243 vel = this.currentTarget.method_18798();
               class_238 baseBox = this.currentTarget.method_5829();
               this.predictedBox = baseBox.method_997(vel.method_1021(ticks));
               class_243 center = this.predictedBox.method_1005();
               auraPredict = center;
               if (mc.field_1724.method_6128()) {
                  if (this.rocketTimer > 0) {
                     this.rocketTimer--;
                  }

                  if (this.boost.getValue() && this.boostDelay-- <= 0) {
                     this.applyBoost(center);
                     this.boostDelay = 2 + mc.field_1724.field_6012 % 2;
                  }

                  double speed = mc.field_1724.method_18798().method_1033() * 20.0;
                  if (this.rocketTimer <= 0 && speed < this.minSpeed.getValue() - mc.field_1724.field_6012 % 3 && this.useFirework()) {
                     this.rocketTimer = this.delay.getValue();
                  }

                  Aura.target = living;
               }
            } else {
               this.reset();
            }
         }
      }
   }

   private void applyBoost(class_243 target) {
      class_243 eyes = mc.field_1724.method_33571();
      class_243 dir = target.method_1020(eyes).method_1029();
      double speed = mc.field_1724.method_18798().method_1033();
      if (!(speed > 1.9)) {
         float pitch = Math.abs(mc.field_1724.method_36455());
         float factor = this.getBoostFactor(pitch);
         class_243 vel = mc.field_1724.method_18798();
         double lerp = 0.18;
         mc.field_1724
            .method_18800(
               class_3532.method_16436(lerp, vel.field_1352, vel.field_1352 + dir.field_1352 * factor),
               class_3532.method_16436(lerp, vel.field_1351, vel.field_1351 + dir.field_1351 * factor),
               class_3532.method_16436(lerp, vel.field_1350, vel.field_1350 + dir.field_1350 * factor)
            );
         float[] rot = this.getRotationsTo(target);
         float yaw = class_3532.method_17821(0.25F, mc.field_1724.method_36454(), rot[0]);
         float pitchRot = class_3532.method_16439(0.25F, mc.field_1724.method_36455(), rot[1]);
         mc.method_1562().method_52787(new class_2831(yaw, pitchRot, mc.field_1724.method_24828()));
      }
   }

   private float[] getRotationsTo(class_243 target) {
      class_243 eyes = mc.field_1724.method_33571();
      double x = target.field_1352 - eyes.field_1352;
      double y = target.field_1351 - eyes.field_1351;
      double z = target.field_1350 - eyes.field_1350;
      double dist = Math.sqrt(x * x + z * z);
      float yaw = (float)Math.toDegrees(Math.atan2(z, x)) - 90.0F;
      float pitch = (float)(-Math.toDegrees(Math.atan2(y, dist)));
      return new float[]{yaw, pitch};
   }

   private float getBoostFactor(float pitch) {
      if (pitch < 5.0F) {
         return this.deg0.getValue();
      } else if (pitch < 10.0F) {
         return this.deg5.getValue();
      } else if (pitch < 15.0F) {
         return this.deg10.getValue();
      } else if (pitch < 20.0F) {
         return this.deg15.getValue();
      } else if (pitch < 25.0F) {
         return this.deg20.getValue();
      } else if (pitch < 30.0F) {
         return this.deg25.getValue();
      } else if (pitch < 35.0F) {
         return this.deg30.getValue();
      } else if (pitch < 40.0F) {
         return this.deg35.getValue();
      } else {
         return pitch < 45.0F ? this.deg40.getValue() : this.deg50.getValue();
      }
   }

   private void findTargetInRange() {
      if (!this.lockTarget.getValue()
         || this.currentTarget == null
         || !this.currentTarget.method_5805()
         || !(mc.field_1724.method_5739(this.currentTarget) <= this.range.getValue())) {
         class_1297 aura = Aura.target;
         if (aura != null && aura.method_5805() && mc.field_1724.method_5739(aura) <= this.range.getValue()) {
            this.currentTarget = aura;
         } else {
            this.currentTarget = StreamSupport.<class_1297>stream(mc.field_1687.method_18112().spliterator(), false)
               .filter(e -> e instanceof class_1309 && e != mc.field_1724 && e.method_5805())
               .filter(e -> mc.field_1724.method_5739(e) <= this.range.getValue())
               .min(Comparator.comparingDouble(e -> mc.field_1724.method_5739(e)))
               .orElse(null);
         }
      }
   }

   private boolean useFirework() {
      if (this.offhandFirework.getValue() && mc.field_1724.method_6079().method_7909() instanceof class_1781) {
         mc.field_1761.method_2919(mc.field_1724, class_1268.field_5810);
         return true;
      } else {
         SearchInvResult hotbar = InventoryUtility.findItemInHotBar(class_1802.field_8639);
         SearchInvResult inv = InventoryUtility.findItemInInventory(class_1802.field_8639);
         if (hotbar.found()) {
            int old = mc.field_1724.method_31548().field_7545;
            mc.field_1724.method_31548().field_7545 = hotbar.slot();
            mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
            mc.field_1724.method_31548().field_7545 = old;
            return true;
         } else if (inv.found()) {
            int old = mc.field_1724.method_31548().field_7545;
            mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, inv.slot(), old, class_1713.field_7791, mc.field_1724);
            mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
            mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, inv.slot(), old, class_1713.field_7791, mc.field_1724);
            mc.method_1562().method_52787(new class_2815(mc.field_1724.field_7512.field_7763));
            return true;
         } else {
            return false;
         }
      }
   }

   private void reset() {
      this.predictedBox = null;
      auraPredict = null;
      this.currentTarget = null;
   }

   @Override
   public void onRender3D(class_4587 matrices) {
      if (this.predictedBox != null) {
         Color color = HudEditor.getColor(0);
         Render3DEngine.OUTLINE_QUEUE.add(new Render3DEngine.OutlineAction(this.predictedBox, color, 2.0F));
      }
   }
}
