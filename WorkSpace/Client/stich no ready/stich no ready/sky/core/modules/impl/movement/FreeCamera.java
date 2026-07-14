package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.glfw.GLFW;
import sky.core.events.EventCancelThirdPerson;
import sky.core.events.EventInput;
import sky.core.events.EventRotation;
import sky.core.events.EventSwapWorld;
import sky.core.events.EventThirdPersonDistance;
import sky.core.events.EventThirdPersonRender;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class FreeCamera extends Module {
   private final SliderSetting speedXZ = new SliderSetting("Скорость XZ", 1.5F, 0.1F, 5.0F, 0.1F);
   private final SliderSetting speedY = new SliderSetting("Скорость Y", 1.0F, 0.1F, 5.0F, 0.1F);
   private Vector3d cameraPosition;
   private Vector3d prevCameraPosition;
   private Vector2f rotation;
   private float inputForward;
   private float inputStrafe;
   private boolean inputJump;
   private boolean inputSneak;
   private PointOfView prevPointOfView;

   public FreeCamera() {
      super("Free Camera", "Позволяет перемещаться в свободной камере", Category.Movement);
      this.prevPointOfView = PointOfView.FIRST_PERSON;
      this.addSettings(new Setting[]{this.speedXZ, this.speedY});
   }

   public void onEnable() {
      super.onEnable();
      Minecraft var10001 = mc;
      this.cameraPosition = Minecraft.player.getEyePosition(mc.getRenderPartialTicks());
      this.prevCameraPosition = this.cameraPosition;
      this.prevPointOfView = mc.gameSettings.getPointOfView();
   }

   public Vector3d getInterpolatedCameraPosition(float partialTicks) {
      if (this.cameraPosition == null) {
         return null;
      } else if (this.prevCameraPosition == null) {
         return this.cameraPosition;
      } else {
         double x = this.prevCameraPosition.x + (this.cameraPosition.x - this.prevCameraPosition.x) * (double)partialTicks;
         double y = this.prevCameraPosition.y + (this.cameraPosition.y - this.prevCameraPosition.y) * (double)partialTicks;
         double z = this.prevCameraPosition.z + (this.cameraPosition.z - this.prevCameraPosition.z) * (double)partialTicks;
         return new Vector3d(x, y, z);
      }
   }

   public void onDisable() {
      super.onDisable();
      this.cameraPosition = null;
      this.prevCameraPosition = null;
      this.inputForward = this.inputStrafe = 0.0F;
      this.inputJump = this.inputSneak = false;
      mc.gameSettings.setPointOfView(this.prevPointOfView);
   }

   @EventTarget
   public void onEvent(EventSwapWorld eventSwapWorld) {
      this.toggle();
   }

   @EventTarget
   public void onEvent(EventThirdPersonRender eventThirdDistance) {
      eventThirdDistance.setThirdperson(true);
   }

   @EventTarget
   public void onEvent(EventCancelThirdPerson eventThirdDistance) {
      eventThirdDistance.setCancelled(true);
   }

   @EventTarget
   public void onEvent(EventThirdPersonDistance eventThirdDistance) {
      eventThirdDistance.setDistance(0.0);
   }

   @EventTarget
   public void onEvent(EventRotation eventRotation) {
      this.rotation = eventRotation.getRotation();
      Vector3d renderPos = this.prevCameraPosition == null ? this.cameraPosition : this.prevCameraPosition.add(this.cameraPosition.subtract(this.prevCameraPosition).scale((double)mc.getRenderPartialTicks()));
      eventRotation.setPosition(renderPos);
      eventRotation.setCancelled(true);
   }

   @EventTarget
   public void onEvent(EventUpdate event) {
      this.prevCameraPosition = this.cameraPosition;
      mc.gameSettings.setPointOfView(PointOfView.THIRD_PERSON_BACK);
      if (this.inputForward != 0.0F || this.inputStrafe != 0.0F || this.inputJump || this.inputSneak) {
         double speedHorizontal = (double)(Float)this.speedXZ.get();
         double speedVertical = (double)(Float)this.speedY.get();
         double yawRad = Math.toRadians((double)this.rotation.x);
         double sin = Math.sin(yawRad);
         double cos = Math.cos(yawRad);
         double dx = ((double)this.inputForward * -sin + (double)this.inputStrafe * cos) * speedHorizontal;
         double dz = ((double)this.inputForward * cos + (double)this.inputStrafe * sin) * speedHorizontal;
         double dy = (this.inputJump ? speedVertical : 0.0) - (this.inputSneak ? speedVertical : 0.0);
         this.cameraPosition = this.cameraPosition.add(dx, dy, dz);
      }

   }

   @EventTarget
   public void onEvent(EventInput eventInput) {
      if (this.cameraPosition != null) {
         this.inputForward = eventInput.getForward();
         this.inputStrafe = eventInput.getStrafe();
         this.inputJump = eventInput.isJump();
         this.inputSneak = eventInput.isSneak();
         long windowHandle = mc.getMainWindow().getHandle();
         boolean up = GLFW.glfwGetKey(windowHandle, 265) == 1;
         boolean down = GLFW.glfwGetKey(windowHandle, 264) == 1;
         boolean left = GLFW.glfwGetKey(windowHandle, 263) == 1;
         boolean right = GLFW.glfwGetKey(windowHandle, 262) == 1;
         float playerForward = 0.0F;
         if (up) {
            ++playerForward;
         }

         if (down) {
            --playerForward;
         }

         float playerStrafe = 0.0F;
         if (left) {
            ++playerStrafe;
         }

         if (right) {
            --playerStrafe;
         }

         eventInput.setForward(playerForward);
         eventInput.setStrafe(playerStrafe);
         eventInput.setJump(false);
         eventInput.setSneak(false);
      }
   }
}
