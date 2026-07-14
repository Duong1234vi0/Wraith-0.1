package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import sky.core.SkyCore;
import sky.core.events.EventSwingAnimation;
import sky.core.events.EventSwingSpeed;
import sky.core.events.EventTransformSideFirstPerson;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.combat.AttackAura;

public class SwordAnimations extends Module {
   public final ModeSetting swordAnim = new ModeSetting("Мод", "Мод 1", new String[]{"Мод 1", "Мод 2", "Мод 3", "Мод 4", "Мод 5", "Мод 6", "Мод 7", "360", "Slant", "3D", "Fade"});
   public final SliderSetting angle = new SliderSetting("Угол", 100.0F, 0.0F, 360.0F, 1.0F, () -> {
      return this.swordAnim.is("Мод 2") || this.swordAnim.is("Мод 4");
   });
   public final SliderSetting swipePower = new SliderSetting("Сила взмаха", 8.0F, 1.0F, 10.0F, 1.0F, () -> {
      return this.swordAnim.is("Мод 1") || this.swordAnim.is("Мод 2") || this.swordAnim.is("Мод 3") || this.swordAnim.is("Мод 4") || this.swordAnim.is("Мод 6");
   });
   public final SliderSetting transform = new SliderSetting("Сила взмаха вниз", 0.0F, 0.0F, 1.0F, 0.1F);
   public final SliderSetting swipeSpeed = new SliderSetting("Плавность взмаха", 11.0F, 1.0F, 20.0F, 1.0F);
   public final BooleanSetting onlyaura = new BooleanSetting("Только с активной AttackAura", false);
   public final BooleanSetting newShowLeftArm = new BooleanSetting("Левая рука", false, () -> {
      return this.swordAnim.is("3D");
   });
   private boolean newSlashReversed;
   private boolean newSwingInProgress;

   public SwordAnimations() {
      super("Sword Animations", "Позволяет изменить анимацию удара", Category.Visuals);
      this.addSettings(new Setting[]{this.swordAnim, this.angle, this.swipePower, this.swipeSpeed, this.transform, this.newShowLeftArm, this.onlyaura});
   }

   private float easeInOutBack(float x) {
      float c1 = 1.70158F;
      float c2 = c1 * 1.525F;
      return x < 0.5F ? (float)(Math.pow((double)(2.0F * x), 2.0) * (double)((c2 + 1.0F) * 2.0F * x - c2) / 2.0) : (float)((Math.pow((double)(2.0F * x - 2.0F), 2.0) * (double)((c2 + 1.0F) * (x * 2.0F - 2.0F) + c2) + 2.0) / 2.0);
   }

   private void updateNewSwingState(float swingProgress) {
      boolean swinging = swingProgress > 0.01F;
      if (swinging && !this.newSwingInProgress) {
         this.newSlashReversed = !this.newSlashReversed;
      }

      this.newSwingInProgress = swinging;
   }

   private void applyNewMode(EventSwingAnimation event, boolean isLeft) {
      ItemStack stack = event.getPlayer().getHeldItem(event.getHand());
      if (!stack.isEmpty()) {
         this.updateNewSwingState(event.getSwingProgress());
         MatrixStack matrixStack = event.getMatrixStack();
         float direction = isLeft ? -1.0F : 1.0F;
         float swingProgress = event.getSwingProgress();
         float swingRot = swingProgress < 0.6F ? MathHelper.sin(MathHelper.clamp(swingProgress, 0.0F, 0.12506F) * 12.56F) : MathHelper.sin(MathHelper.clamp(swingProgress, 0.62532F, 0.75038F) * 12.56F);
         float swing = this.easeInOutBack(MathHelper.sin(swingProgress * 3.1415927F));
         boolean sword = stack.getItem() instanceof SwordItem;
         boolean axe = stack.getItem() instanceof AxeItem;
         boolean shovel = stack.getItem() instanceof ShovelItem;
         boolean tool = stack.getItem() instanceof ToolItem;
         boolean spear = stack.getUseAction() == UseAction.SPEAR;
         boolean block = stack.getUseAction() == UseAction.BLOCK;
         boolean primarySlash = this.newSlashReversed || axe || spear || block;
         if (primarySlash && !shovel) {
            if (!sword && !axe) {
               if (spear) {
                  matrixStack.translate(0.0, 0.0, 0.45 * (double)swingRot);
                  matrixStack.translate(-0.25 * (double)direction * (double)swing, -0.35 * (double)swingRot, -0.6 * (double)swing);
                  matrixStack.translate(0.0, 0.1 * (double)swing, 0.0);
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(15.0F * swingRot * direction));
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(30.0F * swingRot * direction));
               } else if (tool && !block) {
                  matrixStack.translate((double)(0.1F * direction * swingRot), (double)(0.1F * swingRot), (double)(-0.5F * swing));
                  matrixStack.rotate(Vector3f.XN.rotationDegrees(-30.0F * swingRot));
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(-20.0F * swingRot * direction));
                  matrixStack.rotate(Vector3f.XN.rotationDegrees(40.0F * swing));
               } else if (!block) {
                  matrixStack.translate((double)(0.1F * direction * swingRot), (double)(0.1F * swingRot), (double)(-0.1F * swing));
                  matrixStack.rotate(Vector3f.XN.rotationDegrees(-30.0F * swingRot));
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F * swingRot * direction));
                  matrixStack.rotate(Vector3f.XN.rotationDegrees(40.0F * swing));
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(10.0F * swing * direction));
               } else {
                  matrixStack.translate((double)(0.1F * direction * swingRot), (double)(0.1F * swingRot), (double)(-0.2F * swing));
                  matrixStack.rotate(Vector3f.XN.rotationDegrees(-10.0F * swingRot));
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F * swingRot * direction));
                  matrixStack.rotate(Vector3f.XN.rotationDegrees(20.0F * swing));
               }
            } else {
               matrixStack.translate((double)(0.8F * direction * swingRot), (double)(0.3F * swingRot), (double)(-0.5F * swing));
               matrixStack.rotate(Vector3f.YP.rotationDegrees(15.0F * swingRot * direction));
               matrixStack.rotate(Vector3f.XN.rotationDegrees(-20.0F * swingRot));
               matrixStack.rotate(Vector3f.ZP.rotationDegrees(-70.0F * swingRot * direction));
               matrixStack.rotate(Vector3f.XN.rotationDegrees((sword ? 40.0F : 30.0F) * swing));
            }
         } else if (shovel) {
            matrixStack.translate(0.0, 0.15 * (double)swingRot, -0.25 * (double)swingRot);
            matrixStack.translate(0.0, 0.0, -0.2 * (double)swing);
            matrixStack.rotate(Vector3f.YP.rotationDegrees(15.0F * swingRot));
            matrixStack.rotate(Vector3f.XP.rotationDegrees(-35.0F * swingRot));
            matrixStack.rotate(Vector3f.XP.rotationDegrees(30.0F * swing));
         } else if (sword) {
            matrixStack.translate((double)(-0.55F * direction * swingRot), (double)(-0.8F * swingRot), (double)(-0.77F * swing));
            matrixStack.rotate(Vector3f.YP.rotationDegrees(5.0F * swingRot * direction));
            matrixStack.rotate(Vector3f.XN.rotationDegrees(-30.0F * swingRot));
            matrixStack.rotate(Vector3f.ZP.rotationDegrees(70.0F * swingRot * direction));
            matrixStack.rotate(Vector3f.XN.rotationDegrees(50.0F * swing));
         } else if (tool) {
            matrixStack.translate((double)(0.1F * direction * swingRot), (double)(0.1F * swingRot), (double)(-0.5F * swing));
            matrixStack.rotate(Vector3f.XN.rotationDegrees(-30.0F * swingRot));
            matrixStack.rotate(Vector3f.ZP.rotationDegrees(-20.0F * swingRot * direction));
            matrixStack.rotate(Vector3f.XN.rotationDegrees(40.0F * swing));
         } else {
            matrixStack.translate((double)(0.1F * direction * swingRot), (double)(0.1F * swingRot), (double)(-0.1F * swing));
            matrixStack.rotate(Vector3f.XN.rotationDegrees(-30.0F * swingRot));
            matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F * swingRot * direction));
            matrixStack.rotate(Vector3f.XN.rotationDegrees(40.0F * swing));
            matrixStack.rotate(Vector3f.YP.rotationDegrees(10.0F * swing * direction));
         }
      }
   }

   @EventTarget
   public void onEvent(EventSwingAnimation event) {
      AttackAura aura = SkyCore.getInstance().getModuleManager().getAttackAura();
      if (!(Boolean)this.onlyaura.get() || AttackAura.target != null) {
         MatrixStack matrixStack = event.getMatrixStack();
         float anim = MathHelper.sin(MathHelper.sqrt(event.getSwingProgress()) * 3.1415927F);
         String mode = (String)this.swordAnim.get();
         if (event.getHand() == Hand.MAIN_HAND) {
            boolean isLeft = event.getPlayer().getPrimaryHand() == HandSide.LEFT;
            float rotate;
            float f;
            float f1;
            switch (mode) {
               case "Мод 1":
                  rotate = MathHelper.sqrt(event.getSwingProgress());
                  f = isLeft ? -1.0F : 1.0F;
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(f * (20.0F + MathHelper.sin(event.getSwingProgress() * event.getSwingProgress() * 3.1415927F) / 4.0F * -10.0F)));
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(f * MathHelper.sin(rotate * 3.1415927F) * -20.0F));
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(MathHelper.sin(rotate * 3.1415927F) * -(Float)this.swipePower.get() * 10.0F));
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(-f * 45.0F));
                  break;
               case "Мод 2":
                  matrixStack.translate(0.0, 0.15, -0.30000001192092896);
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(isLeft ? -90.0F : 90.0F));
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(isLeft ? 60.0F : -60.0F));
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(-(Float)this.angle.get() - (Float)this.swipePower.get() * 10.0F * anim));
                  break;
               case "Мод 3":
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(event.getSwingProgress() * 3.1415927F - (Float)this.swipePower.get() * 10.0F * anim));
                  break;
               case "Мод 4":
                  matrixStack.translate(0.0, 0.15, -0.30000001192092896);
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(isLeft ? -70.0F : 70.0F));
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(isLeft ? 30.0F : -30.0F));
                  matrixStack.scale(0.9F, 0.9F, 0.9F);
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(-(Float)this.angle.get() - (Float)this.swipePower.get() * 10.0F * anim));
                  break;
               case "Мод 5":
                  rotate = isLeft ? -1.0F : 1.0F;
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(rotate * 45.0F));
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(anim * -20.0F));
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(rotate * anim * -20.0F));
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(anim * -80.0F));
                  matrixStack.translate((double)(rotate * 0.4F), 0.20000000298023224, 0.20000000298023224);
                  matrixStack.translate((double)(rotate * -0.5F), 0.07999999821186066, 0.0);
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(rotate * 20.0F));
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(-80.0F));
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(rotate * 20.0F));
                  break;
               case "Мод 7":
                  rotate = isLeft ? -1.0F : 1.0F;
                  matrixStack.scale(1.0F, 1.0F, 1.0F);
                  matrixStack.translate((double)(rotate * (0.4F - anim * 0.3F)), 0.0, (double)(-0.0F - anim * 0.2F));
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(rotate * 90.0F));
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(rotate * -30.0F));
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(-70.0F - 30.0F * anim));
                  break;
               case "360":
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(-360.0F * event.getSwingProgress()));
                  break;
               case "Fade":
                  rotate = event.getSwingProgress();
                  f = isLeft ? -1.0F : 1.0F;
                  f1 = MathHelper.sin(rotate * rotate * 3.1415927F);
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(f * (45.0F + f1 * -5.0F)));
                  float f13 = MathHelper.sin(MathHelper.sqrt(rotate * rotate) * 3.1415927F);
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(f * f13 * -20.0F));
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(f13 * -((Float)this.swipePower.get() * 8.0F)));
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(f * -45.0F));
                  break;
               case "Мод 6":
                  rotate = isLeft ? -1.0F : 1.0F;
                  f = MathHelper.sin(event.getSwingProgress() * event.getSwingProgress() * 3.1415927F);
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(rotate * (45.0F + f * -20.0F)));
                  f1 = MathHelper.sin(MathHelper.sqrt(event.getSwingProgress()) * 3.1415927F);
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(rotate * f1 * -20.0F));
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(f1 * -(Float)this.swipePower.get() * 10.0F));
                  matrixStack.rotate(Vector3f.YP.rotationDegrees(rotate * -45.0F));
                  break;
               case "Slant":
                  rotate = 35.0F;
                  matrixStack.translate(0.0, 0.0, -0.3 * (double)anim);
                  matrixStack.rotate(Vector3f.XP.rotationDegrees(anim * -rotate));
                  matrixStack.rotate(Vector3f.ZP.rotationDegrees(anim * rotate));
                  break;
               case "3D":
                  this.applyNewMode(event, isLeft);
            }
         }

         event.setCancelled(true);
      }
   }

   @EventTarget
   public void onEvent(EventSwingSpeed event) {
      if (event.getHand() == Hand.MAIN_HAND) {
         event.setSwipeSpeed(((Float)this.swipeSpeed.get()).intValue());
         event.setCancelled(true);
      }
   }

   @EventTarget
   public void onEvent(EventTransformSideFirstPerson event) {
      HandSide var10000 = event.getHandSide();
      Minecraft var10001 = mc;
      if (var10000 == Minecraft.player.getPrimaryHand()) {
         event.setEquippedProg((Float)this.transform.get());
      }
   }
}
