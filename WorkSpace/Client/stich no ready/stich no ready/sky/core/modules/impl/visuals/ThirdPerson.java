package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import org.joml.Vector2f;
import sky.core.SkyCore;
import sky.core.events.EventCancelThirdPerson;
import sky.core.events.EventKey;
import sky.core.events.EventThirdPersonDistance;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.LookHandler;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.movement.FreeCamera;
import sky.core.utils.component.impl.RotationComponent;

public class ThirdPerson extends Module {
   private ModeSetting povMode = new ModeSetting("Режим обзора", "Сзади", new String[]{"Спереди", "Сзади"});
   private SliderSetting distance = new SliderSetting("Дистанция", 4.0F, 1.0F, 10.0F, 0.1F);
   public BindSetting keySetting = new BindSetting("Бинд");
   private PointOfView prevPointOfView;
   private boolean pressed;
   private boolean keyDown;
   private Vector2f rotation;

   public ThirdPerson() {
      super("Third Person", "Позволяет осмотреться от третьего лица без отображения другим игрокам", Category.Visuals);
      this.prevPointOfView = PointOfView.FIRST_PERSON;
      this.rotation = new Vector2f(0.0F, 0.0F);
      this.addSettings(new Setting[]{this.povMode, this.keySetting, this.distance});
   }

   @EventTarget
   public void onKey(EventKey event) {
      if (!SkyCore.getInstance().getModuleManager().getModule(FreeCamera.class).isEnabled()) {
         if (event.getKey() == (Integer)this.keySetting.get()) {
            this.keyDown = event.isHold();
         }

      }
   }

   @EventTarget
   public void onKey(EventThirdPersonDistance eventThirdPersonRender) {
      if (this.pressed) {
         eventThirdPersonRender.setDistance((double)(Float)this.distance.get());
      }

   }

   @EventTarget
   private void onUpdate(EventUpdate e) {
      if (!this.pressed) {
         this.prevPointOfView = mc.gameSettings.getPointOfView();
         this.rotation.x = LookHandler.getFreeYaw();
         this.rotation.y = LookHandler.getFreePitch();
      }

      if (this.keyDown && mc.currentScreen == null) {
         Minecraft var10002 = mc;
         Minecraft var10003 = mc;
         RotationComponent.update(new Rotation(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch), 360.0F, 1, 1);
         if (this.povMode.is("Сзади")) {
            mc.gameSettings.setPointOfView(PointOfView.THIRD_PERSON_BACK);
         } else {
            mc.gameSettings.setPointOfView(PointOfView.THIRD_PERSON_FRONT);
         }

         this.pressed = true;
      } else if (this.pressed) {
         LookHandler.setFreeYaw(this.rotation.x);
         LookHandler.setFreePitch(this.rotation.y);
         mc.gameSettings.setPointOfView(this.prevPointOfView);
         this.pressed = false;
      }

   }

   @EventTarget
   public void onEvent(EventCancelThirdPerson eventThirdDistance) {
      if (this.keyDown) {
         eventThirdDistance.setCancelled(true);
      }

   }
}
