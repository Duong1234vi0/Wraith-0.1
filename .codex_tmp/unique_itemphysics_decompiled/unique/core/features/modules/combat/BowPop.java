package unique.core.features.modules.combat;

import java.util.Random;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_2846;
import net.minecraft.class_2848;
import net.minecraft.class_2886;
import net.minecraft.class_2828.class_2829;
import net.minecraft.class_2828.class_2830;
import net.minecraft.class_2846.class_2847;
import net.minecraft.class_2848.class_2849;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.Timer;

public final class BowPop extends Module {
   public Setting<Boolean> rotation = new Setting<>("Rotation", false);
   public Setting<BowPop.ModeEn> Mode = new Setting<>("Mode", BowPop.ModeEn.Maximum);
   public Setting<Float> factor = new Setting<>("Factor", 1.0F, 1.0F, 20.0F);
   public Setting<BowPop.exploitEn> exploit = new Setting<>("Exploit", BowPop.exploitEn.Strong);
   public Setting<Float> scale = new Setting<>("Scale", 0.01F, 0.01F, 0.4F);
   public Setting<Boolean> minimize = new Setting<>("Minimize", false);
   public Setting<Float> delay = new Setting<>("Delay", 5.0F, 0.0F, 10.0F);
   public final Setting<SettingGroup> selection = new Setting<>("Selection", new SettingGroup(false, 0));
   public final Setting<Boolean> bow = new Setting<>("Bows", true).addToGroup(this.selection);
   public final Setting<Boolean> pearls = new Setting<>("EPearls", true).addToGroup(this.selection);
   public final Setting<Boolean> xp = new Setting<>("XP", true).addToGroup(this.selection);
   public final Setting<Boolean> eggs = new Setting<>("Eggs", true).addToGroup(this.selection);
   public final Setting<Boolean> potions = new Setting<>("SplashPotions", true).addToGroup(this.selection);
   public final Setting<Boolean> snowballs = new Setting<>("Snowballs", true).addToGroup(this.selection);
   public static Timer delayTimer = new Timer();
   private final Random rnd = new Random();

   public BowPop() {
      super("BowPop", Module.Category.COMBAT);
   }

   @EventHandler
   private void onPacketSend(PacketEvent.Send event) {
      if (!fullNullCheck() && delayTimer.passedMs((long)(this.delay.getValue() * 1000.0F))) {
         if (event.getPacket() instanceof class_2846
               && ((class_2846)event.getPacket()).method_12363() == class_2847.field_12974
               && mc.field_1724.method_6030().method_7909() == class_1802.field_8102
               && this.bow.getValue()
            || event.getPacket() instanceof class_2886
               && ((class_2886)event.getPacket()).method_12551() == class_1268.field_5808
               && (
                  mc.field_1724.method_6047().method_7909() == class_1802.field_8634 && this.pearls.getValue()
                     || mc.field_1724.method_6047().method_7909() == class_1802.field_8287 && this.xp.getValue()
                     || mc.field_1724.method_6047().method_7909() == class_1802.field_8803 && this.eggs.getValue()
                     || mc.field_1724.method_6047().method_7909() == class_1802.field_8436 && this.potions.getValue()
                     || mc.field_1724.method_6047().method_7909() == class_1802.field_8543 && this.snowballs.getValue()
               )) {
            mc.field_1724.field_3944.method_52787(new class_2848(mc.field_1724, class_2849.field_12981));
            double[] strict_direction = new double[]{
               100.0 * -Math.sin(Math.toRadians(mc.field_1724.method_36454())), 100.0 * Math.cos(Math.toRadians(mc.field_1724.method_36454()))
            };
            if (this.exploit.getValue() == BowPop.exploitEn.Fast) {
               for (int i = 0; i < this.getRuns(); i++) {
                  this.spoof(
                     mc.field_1724.method_23317(),
                     this.minimize.getValue() ? mc.field_1724.method_23318() : mc.field_1724.method_23318() - 1.0E-10,
                     mc.field_1724.method_23321(),
                     true
                  );
                  this.spoof(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.0E-10, mc.field_1724.method_23321(), false);
               }
            }

            if (this.exploit.getValue() == BowPop.exploitEn.Strong) {
               for (int i = 0; i < this.getRuns(); i++) {
                  this.spoof(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.0E-10, mc.field_1724.method_23321(), false);
                  this.spoof(
                     mc.field_1724.method_23317(),
                     this.minimize.getValue() ? mc.field_1724.method_23318() : mc.field_1724.method_23318() - 1.0E-10,
                     mc.field_1724.method_23321(),
                     true
                  );
               }
            }

            if (this.exploit.getValue() == BowPop.exploitEn.Phobos) {
               for (int i = 0; i < this.getRuns(); i++) {
                  this.spoof(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.3E-13, mc.field_1724.method_23321(), true);
                  this.spoof(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 2.7E-13, mc.field_1724.method_23321(), false);
               }
            }

            if (this.exploit.getValue() == BowPop.exploitEn.Strict) {
               for (int i = 0; i < this.getRuns(); i++) {
                  if (this.rnd.nextBoolean()) {
                     this.spoof(
                        mc.field_1724.method_23317() - strict_direction[0],
                        mc.field_1724.method_23318(),
                        mc.field_1724.method_23321() - strict_direction[1],
                        false
                     );
                  } else {
                     this.spoof(
                        mc.field_1724.method_23317() + strict_direction[0],
                        mc.field_1724.method_23318(),
                        mc.field_1724.method_23321() + strict_direction[1],
                        true
                     );
                  }
               }
            }

            if (this.exploit.getValue() == BowPop.exploitEn.WB) {
               for (int ix = 0; ix < this.getRuns(); ix++) {
                  this.spoof(
                     mc.field_1724.method_23317() + this.getWorldBorderRnd(),
                     mc.field_1724.method_23318(),
                     mc.field_1724.method_23321() + this.getWorldBorderRnd(),
                     false
                  );
               }
            }

            delayTimer.reset();
         }
      }
   }

   private void spoof(double x, double y, double z, boolean ground) {
      if (this.rotation.getValue()) {
         this.sendPacket(new class_2830(x, y, z, mc.field_1724.method_36454(), mc.field_1724.method_36455(), ground));
      } else {
         this.sendPacket(new class_2829(x, y, z, ground));
      }
   }

   private int getRuns() {
      return switch ((BowPop.ModeEn)this.Mode.getValue()) {
         case Normal -> (int)Math.floor(this.factor.getValue().floatValue());
         case Maximum -> (int)(30.0F * this.factor.getValue());
         case Factorised -> 10 + (int)(this.factor.getValue() - 1.0F);
      };
   }

   private int getWorldBorderRnd() {
      if (mc.method_1542()) {
         return 1;
      } else {
         int n = this.rnd.nextInt(29000000);
         return this.rnd.nextBoolean() ? n : -n;
      }
   }

   private static enum ModeEn {
      Normal,
      Maximum,
      Factorised;
   }

   private static enum exploitEn {
      Strong,
      Fast,
      Strict,
      Phobos,
      WB;
   }
}
