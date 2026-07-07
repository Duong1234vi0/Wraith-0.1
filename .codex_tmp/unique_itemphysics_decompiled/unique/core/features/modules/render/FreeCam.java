package unique.core.features.modules.render;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1309;
import net.minecraft.class_2828;
import unique.core.events.impl.EventAttack;
import unique.core.events.impl.EventKeyboardInput;
import unique.core.events.impl.EventMouse;
import unique.core.events.impl.EventMove;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.player.MovementUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class FreeCam extends Module {
   private final Setting<Float> speed = new Setting<>("HSpeed", 1.0F, 0.1F, 3.0F);
   private final Setting<Float> hspeed = new Setting<>("VSpeed", 0.42F, 0.1F, 3.0F);
   private final Setting<Boolean> freeze = new Setting<>("Freeze", false);
   public final Setting<Boolean> track = new Setting<>("Track", false);
   private float fakeYaw;
   private float fakePitch;
   private float prevFakeYaw;
   private float prevFakePitch;
   private float prevScroll;
   private double fakeX;
   private double fakeY;
   private double fakeZ;
   private double prevFakeX;
   private double prevFakeY;
   private double prevFakeZ;
   public class_1309 trackEntity;

   public FreeCam() {
      super("FreeCam", Module.Category.RENDER);
   }

   @Override
   public void onEnable() {
      mc.field_1730 = false;
      this.trackEntity = null;
      this.fakePitch = mc.field_1724.method_36455();
      this.fakeYaw = mc.field_1724.method_36454();
      this.prevFakePitch = this.fakePitch;
      this.prevFakeYaw = this.fakeYaw;
      this.fakeX = mc.field_1724.method_23317();
      this.fakeY = mc.field_1724.method_23318() + mc.field_1724.method_18381(mc.field_1724.method_18376());
      this.fakeZ = mc.field_1724.method_23321();
      this.prevFakeX = mc.field_1724.method_23317();
      this.prevFakeY = mc.field_1724.method_23318();
      this.prevFakeZ = mc.field_1724.method_23321();
   }

   @EventHandler
   public void onAttack(EventAttack e) {
      if (!e.isPre() && e.getEntity() instanceof class_1309 entity && this.track.getValue()) {
         this.trackEntity = entity;
      }
   }

   @Override
   public void onDisable() {
      if (!fullNullCheck()) {
         mc.field_1730 = true;
      }
   }

   @EventHandler(
      priority = 100
   )
   public void onSync(EventSync e) {
      this.prevFakeYaw = this.fakeYaw;
      this.prevFakePitch = this.fakePitch;
      if (this.isKeyPressed(256) || this.isKeyPressed(340) || this.isKeyPressed(344)) {
         this.trackEntity = null;
      }

      if (this.trackEntity != null) {
         this.fakeYaw = this.trackEntity.method_36454();
         this.fakePitch = this.trackEntity.method_36455();
         this.prevFakeX = this.fakeX;
         this.prevFakeY = this.fakeY;
         this.prevFakeZ = this.fakeZ;
         this.fakeX = this.trackEntity.method_23317();
         this.fakeY = this.trackEntity.method_23318() + this.trackEntity.method_18381(this.trackEntity.method_18376());
         this.fakeZ = this.trackEntity.method_23321();
      } else {
         this.fakeYaw = mc.field_1724.method_36454();
         this.fakePitch = mc.field_1724.method_36455();
      }
   }

   @EventHandler
   public void onKeyboardInput(EventKeyboardInput e) {
      if (mc.field_1724 != null) {
         if (this.trackEntity == null) {
            double[] motion = MovementUtility.forward(this.speed.getValue().floatValue());
            this.prevFakeX = this.fakeX;
            this.prevFakeY = this.fakeY;
            this.prevFakeZ = this.fakeZ;
            this.fakeX = this.fakeX + motion[0];
            this.fakeZ = this.fakeZ + motion[1];
            if (mc.field_1690.field_1903.method_1434()) {
               this.fakeY = this.fakeY + this.hspeed.getValue().floatValue();
            }

            if (mc.field_1690.field_1832.method_1434()) {
               this.fakeY = this.fakeY - this.hspeed.getValue().floatValue();
            }
         }

         mc.field_1724.field_3913.field_3905 = 0.0F;
         mc.field_1724.field_3913.field_3907 = 0.0F;
         mc.field_1724.field_3913.field_3904 = false;
         mc.field_1724.field_3913.field_3903 = false;
      }
   }

   @EventHandler(
      priority = -100
   )
   public void onMove(EventMove e) {
      if (this.freeze.getValue()) {
         e.setX(0.0);
         e.setY(0.0);
         e.setZ(0.0);
         e.cancel();
      }
   }

   @EventHandler
   public void onPacketSend(PacketEvent.Send e) {
      if (this.freeze.getValue() && e.getPacket() instanceof class_2828) {
         e.cancel();
      }
   }

   @EventHandler
   public void onScroll(EventMouse e) {
      if (e.getAction() == 2) {
         if (e.getButton() > 0) {
            this.speed.setValue(this.speed.getValue() + 0.05F);
         } else {
            this.speed.setValue(this.speed.getValue() - 0.05F);
         }

         this.prevScroll = e.getButton();
      }
   }

   public float getFakeYaw() {
      return (float)Render2DEngine.interpolate(this.prevFakeYaw, this.fakeYaw, Render3DEngine.getTickDelta());
   }

   public float getFakePitch() {
      return (float)Render2DEngine.interpolate(this.prevFakePitch, this.fakePitch, Render3DEngine.getTickDelta());
   }

   public double getFakeX() {
      return Render2DEngine.interpolate(this.prevFakeX, this.fakeX, Render3DEngine.getTickDelta());
   }

   public double getFakeY() {
      return Render2DEngine.interpolate(this.prevFakeY, this.fakeY, Render3DEngine.getTickDelta());
   }

   public double getFakeZ() {
      return Render2DEngine.interpolate(this.prevFakeZ, this.fakeZ, Render3DEngine.getTickDelta());
   }
}
