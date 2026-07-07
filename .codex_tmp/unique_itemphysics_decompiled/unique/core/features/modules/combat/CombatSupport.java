package unique.core.features.modules.combat;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1667;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2824;
import net.minecraft.class_2846;
import net.minecraft.class_2885;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_1297.class_5529;
import net.minecraft.class_239.class_240;
import net.minecraft.class_2846.class_2847;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.jetbrains.annotations.NotNull;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.utility.Timer;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.PlayerUtility;
import unique.core.utility.player.SearchInvResult;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class CombatSupport extends Module {
   private final Setting<BooleanSettingGroup> autoCart = new Setting<>("AutoCart", new BooleanSettingGroup(true));
   private final Setting<Float> maxDistance = new Setting<>("MaxDistance", 4.0F, 2.0F, 6.0F).addToGroup(this.autoCart);
   private final Setting<BooleanSettingGroup> crystalOptimizer = new Setting<>("CrystalOptimizer", new BooleanSettingGroup(true));
   private final Timer timer = new Timer();
   private class_243 rotateVec = null;

   public CombatSupport() {
      super("HelperPVP", Module.Category.PLAYER);
   }

   @EventHandler
   public void onPacketSendPost(@NotNull PacketEvent.SendPost event) {
      if (event.getPacket() instanceof class_2846 action) {
         if (action.method_12363() == class_2847.field_12974) {
            if (this.autoCart.getValue().isEnabled()) {
               if (mc.field_1724.method_6047().method_7909() == class_1802.field_8102) {
                  class_2338 pos = this.calcTrajectory(mc.field_1724.method_36454());
                  if (pos != null) {
                     double dist = PlayerUtility.squaredDistanceFromEyes(pos.method_46558());
                     if (!(dist > this.maxDistance.getPow2Value()) && !(dist < 3.0)) {
                        SearchInvResult rail = InventoryUtility.findItemInHotBar(
                           class_1802.field_8129, class_1802.field_8655, class_1802.field_8211, class_1802.field_8848
                        );
                        SearchInvResult cart = InventoryUtility.findItemInHotBar(class_1802.field_8069);
                        if (rail.found() && cart.found()) {
                           InventoryUtility.saveSlot();
                           rail.switchTo();
                           this.sendSequencedPacket(
                              s -> new class_2885(
                                 class_1268.field_5808,
                                 new class_3965(
                                    new class_243(pos.method_10263() + 0.5, pos.method_10084().method_10264(), pos.method_10260() + 0.5),
                                    class_2350.field_11036,
                                    pos,
                                    false
                                 ),
                                 s
                              )
                           );
                           this.rotateVec = pos.method_10084().method_46558();
                           cart.switchTo();
                           this.sendSequencedPacket(
                              s -> new class_2885(
                                 class_1268.field_5808,
                                 new class_3965(
                                    new class_243(pos.method_10263() + 0.5, pos.method_10084().method_10264() + 0.125, pos.method_10260() + 0.5),
                                    class_2350.field_11036,
                                    pos.method_10084(),
                                    false
                                 ),
                                 s
                              )
                           );
                           InventoryUtility.returnSlot();
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onPacketSend(@NotNull PacketEvent.Send event) {
      if (this.crystalOptimizer.getValue().isEnabled()) {
         if (event.getPacket() instanceof class_2824) {
            if (Criticals.getInteractType(event.getPacket()) == Criticals.InteractType.ATTACK) {
               if (Criticals.getEntity(event.getPacket()) instanceof class_1511 c) {
                  if (!ModuleManager.autoCrystal.isEnabled()) {
                     c.method_5768();
                     c.method_31745(class_5529.field_26998);
                     c.method_36209();
                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onSync(EventSync e) {
      if (this.rotateVec != null) {
         float[] angle = InteractionUtility.calculateAngle(this.rotateVec);
         mc.field_1724.method_36456(angle[0]);
         mc.field_1724.method_36457(angle[1]);
         this.rotateVec = null;
      }
   }

   private class_2338 calcTrajectory(float yaw) {
      double x = Render2DEngine.interpolate(mc.field_1724.field_6014, mc.field_1724.method_23317(), Render3DEngine.getTickDelta());
      double y = Render2DEngine.interpolate(mc.field_1724.field_6036, mc.field_1724.method_23318(), Render3DEngine.getTickDelta());
      double z = Render2DEngine.interpolate(mc.field_1724.field_5969, mc.field_1724.method_23321(), Render3DEngine.getTickDelta());
      y += mc.field_1724.method_18381(mc.field_1724.method_18376()) - 0.1;
      double mx = -class_3532.method_15374(yaw * (float) (Math.PI / 180.0)) * class_3532.method_15362(mc.field_1724.method_36455() * (float) (Math.PI / 180.0));
      double my = -class_3532.method_15374(mc.field_1724.method_36455() * (float) (Math.PI / 180.0));
      double mz = class_3532.method_15362(yaw * (float) (Math.PI / 180.0)) * class_3532.method_15362(mc.field_1724.method_36455() * (float) (Math.PI / 180.0));
      float power = mc.field_1724.method_6048() / 20.0F;
      power = (power * power + power * 2.0F) / 3.0F;
      if (power > 1.0F) {
         power = 1.0F;
      }

      double norm = Math.sqrt(mx * mx + my * my + mz * mz);
      mx = mx / norm * power * 3.0;
      my = my / norm * power * 3.0;
      mz = mz / norm * power * 3.0;
      if (!mc.field_1724.method_24828()) {
         my += mc.field_1724.method_18798().field_1351;
      }

      for (int i = 0; i < 300; i++) {
         class_243 last = new class_243(x, y, z);
         x += mx;
         y += my;
         z += mz;
         mx *= 0.99;
         my *= 0.99;
         mz *= 0.99;
         my -= 0.05;
         class_243 pos = new class_243(x, y, z);

         for (class_1297 ent : mc.field_1687.method_18112()) {
            if (!(ent instanceof class_1667)
               && ent != mc.field_1724
               && ent.method_5829().method_994(new class_238(x - 0.3, y - 0.3, z - 0.3, x + 0.3, y + 0.3, z + 0.3))) {
               return null;
            }
         }

         class_3965 bhr = mc.field_1687.method_17742(new class_3959(last, pos, class_3960.field_17559, class_242.field_1348, mc.field_1724));
         if (bhr.method_17783() == class_240.field_1332) {
            return bhr.method_17777();
         }

         if (y < -64.0) {
            break;
         }
      }

      return null;
   }
}
