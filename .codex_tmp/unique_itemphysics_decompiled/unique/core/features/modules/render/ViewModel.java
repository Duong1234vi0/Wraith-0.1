package unique.core.features.modules.render;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_1839;
import net.minecraft.class_7833;
import unique.core.events.impl.EventHeldItemRenderer;
import unique.core.events.impl.EventSetting;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class ViewModel extends Module {
   public final Setting<Boolean> syncHands = new Setting<>("SyncHands", true);
   public final Setting<SettingGroup> mainHand = new Setting<>("MainHand", new SettingGroup(false, 0));
   public final Setting<Float> positionMainX = new Setting<>("MainX", 0.0F, -3.0F, 3.0F).addToGroup(this.mainHand);
   public final Setting<Float> positionMainY = new Setting<>("MainY", 0.0F, -3.0F, 3.0F).addToGroup(this.mainHand);
   public final Setting<Float> positionMainZ = new Setting<>("MainZ", 0.0F, -3.0F, 3.0F).addToGroup(this.mainHand);
   public final Setting<Float> scaleMain = new Setting<>("ScaleMain", 1.0F, 0.1F, 1.5F).addToGroup(this.mainHand);
   public final Setting<SettingGroup> rotationMain = new Setting<>("RotationMain", new SettingGroup(false, 1)).addToGroup(this.mainHand);
   public final Setting<Float> rotationMainX = new Setting<>("RotMainX", 0.0F, -180.0F, 180.0F).addToGroup(this.rotationMain);
   public final Setting<Float> rotationMainY = new Setting<>("RotMainY", 0.0F, -180.0F, 180.0F).addToGroup(this.rotationMain);
   public final Setting<Float> rotationMainZ = new Setting<>("RotMainZ", 0.0F, -180.0F, 180.0F).addToGroup(this.rotationMain);
   public final Setting<SettingGroup> animateMain = new Setting<>("AnimateMain", new SettingGroup(false, 2)).addToGroup(this.mainHand);
   public final Setting<Boolean> animateMainX = new Setting<>("AnimMainX", false).addToGroup(this.animateMain);
   public final Setting<Boolean> animateMainY = new Setting<>("AnimMainY", false).addToGroup(this.animateMain);
   public final Setting<Boolean> animateMainZ = new Setting<>("AnimMainZ", false).addToGroup(this.animateMain);
   public final Setting<Float> speedAnimateMain = new Setting<>("SpeedMain", 1.0F, 0.1F, 5.0F).addToGroup(this.animateMain);
   public final Setting<SettingGroup> offHand = new Setting<>("OffHand", new SettingGroup(false, 0));
   public final Setting<Float> positionOffX = new Setting<>("OffX", 0.0F, -3.0F, 3.0F).addToGroup(this.offHand);
   public final Setting<Float> positionOffY = new Setting<>("OffY", 0.0F, -3.0F, 3.0F).addToGroup(this.offHand);
   public final Setting<Float> positionOffZ = new Setting<>("OffZ", 0.0F, -3.0F, 3.0F).addToGroup(this.offHand);
   public final Setting<Float> scaleOff = new Setting<>("ScaleOff", 1.0F, 0.1F, 1.5F).addToGroup(this.offHand);
   public final Setting<SettingGroup> rotationOff = new Setting<>("RotationOff", new SettingGroup(false, 1)).addToGroup(this.offHand);
   public final Setting<Float> rotationOffX = new Setting<>("RotOffX", 0.0F, -180.0F, 180.0F).addToGroup(this.rotationOff);
   public final Setting<Float> rotationOffY = new Setting<>("RotOffY", 0.0F, -180.0F, 180.0F).addToGroup(this.rotationOff);
   public final Setting<Float> rotationOffZ = new Setting<>("RotOffZ", 0.0F, -180.0F, 180.0F).addToGroup(this.rotationOff);
   public final Setting<SettingGroup> animateOff = new Setting<>("AnimateOff", new SettingGroup(false, 2)).addToGroup(this.offHand);
   public final Setting<Boolean> animateOffX = new Setting<>("AnimOffX", false).addToGroup(this.animateOff);
   public final Setting<Boolean> animateOffY = new Setting<>("AnimOffY", false).addToGroup(this.animateOff);
   public final Setting<Boolean> animateOffZ = new Setting<>("AnimOffZ", false).addToGroup(this.animateOff);
   public final Setting<Float> speedAnimateOff = new Setting<>("SpeedOff", 1.0F, 0.1F, 5.0F).addToGroup(this.animateOff);
   public final Setting<SettingGroup> eatMod = new Setting<>("Eat", new SettingGroup(false, 0));
   public final Setting<Float> eatX = new Setting<>("EatX", 1.0F, -2.0F, 2.0F).addToGroup(this.eatMod);
   public final Setting<Float> eatY = new Setting<>("EatY", 1.0F, -2.0F, 2.0F).addToGroup(this.eatMod);
   public final Setting<Boolean> eatShake = new Setting<>("EatShake", true).addToGroup(this.eatMod);
   private float prevMainX;
   private float prevMainY;
   private float prevMainZ;
   private float prevOffX;
   private float prevOffY;
   private float prevOffZ;

   public ViewModel() {
      super("ViewModel", Module.Category.RENDER);
   }

   private float rotate(float value, float speed) {
      float next = value - speed;
      return next < -180.0F ? 180.0F : next;
   }

   @EventHandler
   public void onSettingChange(EventSetting e) {
      if (this.syncHands.getValue()) {
         if (e.getSetting() == this.positionMainX) {
            this.positionOffX.setValueSilent(this.positionMainX.getValue());
         }

         if (e.getSetting() == this.positionMainY) {
            this.positionOffY.setValueSilent(this.positionMainY.getValue());
         }

         if (e.getSetting() == this.positionMainZ) {
            this.positionOffZ.setValueSilent(this.positionMainZ.getValue());
         }

         if (e.getSetting() == this.scaleMain) {
            this.scaleOff.setValueSilent(this.scaleMain.getValue());
         }

         if (e.getSetting() == this.positionOffX) {
            this.positionMainX.setValueSilent(this.positionOffX.getValue());
         }

         if (e.getSetting() == this.positionOffY) {
            this.positionMainY.setValueSilent(this.positionOffY.getValue());
         }

         if (e.getSetting() == this.positionOffZ) {
            this.positionMainZ.setValueSilent(this.positionOffZ.getValue());
         }

         if (e.getSetting() == this.scaleOff) {
            this.scaleMain.setValueSilent(this.scaleOff.getValue());
         }
      }
   }

   @Override
   public void onUpdate() {
      this.prevMainX = this.rotationMainX.getValue();
      this.prevMainY = this.rotationMainY.getValue();
      this.prevMainZ = this.rotationMainZ.getValue();
      this.prevOffX = this.rotationOffX.getValue();
      this.prevOffY = this.rotationOffY.getValue();
      this.prevOffZ = this.rotationOffZ.getValue();
      if (this.animateMainX.getValue()) {
         this.rotationMainX.setValue(this.rotate(this.rotationMainX.getValue(), this.speedAnimateMain.getValue()));
      }

      if (this.animateMainY.getValue()) {
         this.rotationMainY.setValue(this.rotate(this.rotationMainY.getValue(), this.speedAnimateMain.getValue()));
      }

      if (this.animateMainZ.getValue()) {
         this.rotationMainZ.setValue(this.rotate(this.rotationMainZ.getValue(), this.speedAnimateMain.getValue()));
      }

      if (this.animateOffX.getValue()) {
         this.rotationOffX.setValue(this.rotate(this.rotationOffX.getValue(), this.speedAnimateOff.getValue()));
      }

      if (this.animateOffY.getValue()) {
         this.rotationOffY.setValue(this.rotate(this.rotationOffY.getValue(), this.speedAnimateOff.getValue()));
      }

      if (this.animateOffZ.getValue()) {
         this.rotationOffZ.setValue(this.rotate(this.rotationOffZ.getValue(), this.speedAnimateOff.getValue()));
      }
   }

   private float getEatShake(class_1799 stack) {
      if (this.eatShake.getValue() && mc.field_1724 != null && mc.field_1724.method_6115()) {
         if (mc.field_1724.method_6030() != stack) {
            return 0.0F;
         } else {
            class_1839 action = stack.method_7976();
            if (action != class_1839.field_8950 && action != class_1839.field_8946) {
               return 0.0F;
            } else {
               float t = (mc.field_1724.field_6012 + Render3DEngine.getTickDelta()) * 0.65F;
               return (float)Math.sin(t) * 0.12F;
            }
         }
      } else {
         return 0.0F;
      }
   }

   @EventHandler
   private void onHeldItemRender(EventHeldItemRenderer e) {
      float shake = this.getEatShake(e.getItem());
      if (e.getHand() == class_1268.field_5808) {
         e.getStack()
            .method_46416(
               this.positionMainX.getValue() + shake * this.eatX.getValue(),
               this.positionMainY.getValue() + shake * this.eatY.getValue(),
               this.positionMainZ.getValue()
            );
         e.getStack().method_22905(this.scaleMain.getValue(), this.scaleMain.getValue(), this.scaleMain.getValue());
         e.getStack()
            .method_22907(
               class_7833.field_40714
                  .rotationDegrees(Render2DEngine.interpolateFloat(this.prevMainX, this.rotationMainX.getValue(), Render3DEngine.getTickDelta()))
            );
         e.getStack()
            .method_22907(
               class_7833.field_40716
                  .rotationDegrees(Render2DEngine.interpolateFloat(this.prevMainY, this.rotationMainY.getValue(), Render3DEngine.getTickDelta()))
            );
         e.getStack()
            .method_22907(
               class_7833.field_40718
                  .rotationDegrees(Render2DEngine.interpolateFloat(this.prevMainZ, this.rotationMainZ.getValue(), Render3DEngine.getTickDelta()))
            );
      } else {
         e.getStack()
            .method_46416(
               -this.positionOffX.getValue() - shake * this.eatX.getValue(),
               this.positionOffY.getValue() + shake * this.eatY.getValue(),
               this.positionOffZ.getValue()
            );
         e.getStack().method_22905(this.scaleOff.getValue(), this.scaleOff.getValue(), this.scaleOff.getValue());
         e.getStack()
            .method_22907(
               class_7833.field_40714
                  .rotationDegrees(Render2DEngine.interpolateFloat(this.prevOffX, this.rotationOffX.getValue(), Render3DEngine.getTickDelta()))
            );
         e.getStack()
            .method_22907(
               class_7833.field_40716
                  .rotationDegrees(Render2DEngine.interpolateFloat(this.prevOffY, this.rotationOffY.getValue(), Render3DEngine.getTickDelta()))
            );
         e.getStack()
            .method_22907(
               class_7833.field_40718
                  .rotationDegrees(Render2DEngine.interpolateFloat(this.prevOffZ, this.rotationOffZ.getValue(), Render3DEngine.getTickDelta()))
            );
      }
   }
}
