package unique.core.features.modules.render;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1764;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2879;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import org.jetbrains.annotations.NotNull;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.combat.Aura;
import unique.core.injection.accesors.IHeldItemRenderer;
import unique.core.setting.Setting;

public class Animations extends Module {
   private final Setting<Boolean> onlyaura = new Setting<>("OnlyAura", false);
   public Setting<Boolean> oldAnimationsM = new Setting<>("DisableSwapMain", true);
   public Setting<Boolean> oldAnimationsOff = new Setting<>("DisableSwapOff", true);
   private final Setting<Boolean> disableOffHand = new Setting<>("DisableOffHand", false);
   private final Setting<Animations.Mode> mode = new Setting<>("Mode", Animations.Mode.Default);
   private final Setting<Float> hitStrength = new Setting<>("HitStrength", 1.0F, 0.1F, 2.0F);
   public static Setting<Boolean> slowAnimation = new Setting<>("SlowAnimation", true);
   public static Setting<Integer> slowAnimationVal = new Setting<>("SlowValue", 12, 1, 50);
   public final Setting<Boolean> disableSlowSmooth = new Setting<>("DisableSlowSmooth", true, v -> this.mode.is(Animations.Mode.Smooth));
   public boolean flip;

   public Animations() {
      super("Animations", Module.Category.RENDER);
   }

   public boolean shouldAnimate() {
      return this.isEnabled()
         && (!this.onlyaura.getValue() || ModuleManager.aura.isEnabled() && Aura.target != null)
         && this.mode.getValue() != Animations.Mode.Normal;
   }

   public boolean shouldChangeAnimationDuration() {
      if (!this.isEnabled()) {
         return false;
      } else {
         return this.mode.getValue() == Animations.Mode.Smooth && this.disableSlowSmooth.getValue()
            ? false
            : !this.onlyaura.getValue() || ModuleManager.aura.isEnabled() && Aura.target != null;
      }
   }

   @Override
   public void onUpdate() {
      if (!fullNullCheck()) {
         if (this.oldAnimationsM.getValue() && ((IHeldItemRenderer)mc.method_1561().method_43336()).getEquippedProgressMainHand() <= 1.0F) {
            ((IHeldItemRenderer)mc.method_1561().method_43336()).setEquippedProgressMainHand(1.0F);
            ((IHeldItemRenderer)mc.method_1561().method_43336()).setItemStackMainHand(mc.field_1724.method_6047());
         }

         if (this.oldAnimationsOff.getValue() && ((IHeldItemRenderer)mc.method_1561().method_43336()).getEquippedProgressOffHand() <= 1.0F) {
            ((IHeldItemRenderer)mc.method_1561().method_43336()).setEquippedProgressOffHand(1.0F);
            ((IHeldItemRenderer)mc.method_1561().method_43336()).setItemStackOffHand(mc.field_1724.method_6079());
         }
      }
   }

   @EventHandler
   public void onPacketSend(PacketEvent.Send e) {
      if (e.getPacket() instanceof class_2879) {
         this.flip = !this.flip;
      }
   }

   private void renderSwordAnimation(class_4587 matrices, float f, float swingProgress, float equipProgress, class_1306 arm) {
      if (arm != class_1306.field_6182
         || this.mode.getValue() != Animations.Mode.One && this.mode.getValue() != Animations.Mode.Two && this.mode.getValue() != Animations.Mode.Three) {
         int i = arm == class_1306.field_6183 ? 1 : -1;
         switch ((Animations.Mode)this.mode.getValue()) {
            case Default:
               this.applyEquipOffset(matrices, arm, equipProgress);
               this.translateToViewModelOff(matrices);
               this.applySwingOffset(matrices, arm, swingProgress);
               this.translateBacklOff(matrices);
               break;
            case One: {
               float g = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) Math.PI);
               this.applyEquipOffset(matrices, arm, 0.0F);
               this.translateToViewModel(matrices);
               matrices.method_22907(class_7833.field_40714.rotationDegrees(50.0F));
               matrices.method_22907(class_7833.field_40716.rotationDegrees(-30.0F * (1.0F - g) - 30.0F));
               matrices.method_22907(class_7833.field_40718.rotationDegrees(110.0F));
               this.translateBack(matrices);
               break;
            }
            case Two: {
               float g = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) Math.PI);
               this.applyEquipOffset(matrices, arm, 0.0F);
               this.translateToViewModel(matrices);
               matrices.method_22907(class_7833.field_40714.rotationDegrees(50.0F));
               matrices.method_22907(class_7833.field_40716.rotationDegrees(-60.0F * g - 50.0F));
               matrices.method_22907(class_7833.field_40718.rotationDegrees(110.0F));
               this.translateBack(matrices);
               break;
            }
            case Three:
               if (swingProgress > 0.0F) {
                  float gx = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) Math.PI);
                  matrices.method_46416(0.56F, equipProgress * -0.2F - 0.5F, -0.7F);
                  this.translateToViewModel(matrices);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0F));
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(gx * -85.0F));
                  float scale = ModuleManager.viewModel.isEnabled() ? ModuleManager.viewModel.scaleMain.getValue() : 1.0F;
                  matrices.method_46416(-0.1F * scale, 0.28F * scale, 0.2F * scale);
                  matrices.method_22907(class_7833.field_40714.rotationDegrees(-85.0F));
                  this.translateBack(matrices);
               } else {
                  float n = -0.4F * class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) Math.PI);
                  float m = 0.2F * class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) (Math.PI * 2));
                  float f1 = -0.2F * class_3532.method_15374(swingProgress * (float) Math.PI);
                  matrices.method_46416(n, m, f1);
                  this.applyEquipOffset(matrices, arm, equipProgress);
                  this.applySwingOffset(matrices, arm, swingProgress);
               }
               break;
            case Down:
               this.applyEquipOffset(matrices, arm, 0.0F);
               matrices.method_46416(i * 0.56F, -0.32F, -0.72F);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(76 * i));
               float sinSqrt = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) Math.PI);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(sinSqrt * -5.0F * this.hitStrength.getValue()));
               matrices.method_22907(class_7833.field_40713.rotationDegrees(sinSqrt * -100.0F * this.hitStrength.getValue()));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(sinSqrt * -155.0F * this.hitStrength.getValue()));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(-100.0F));
               break;
            case Feast:
               this.applyEquipOffset(matrices, arm, 0.0F);
               matrices.method_46416(i * 0.56F, -0.32F, -0.72F);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(30 * i));
               float sinSqrtFeast = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) Math.PI);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(sinSqrtFeast * 75.0F * i * this.hitStrength.getValue()));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(sinSqrtFeast * -45.0F * this.hitStrength.getValue()));
               matrices.method_22907(class_7833.field_40716.rotationDegrees(30 * i));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(-80.0F));
               matrices.method_22907(class_7833.field_40716.rotationDegrees(35 * i));
               break;
            case Smooth:
               float sin2 = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) Math.PI);
               matrices.method_46416(i * 0.56F, -0.42F, -0.72F);
               matrices.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -80.0F * this.hitStrength.getValue()));
               matrices.method_46416(0.0F, -0.1F, 0.0F);
         }
      } else {
         this.applyEquipOffset(matrices, arm, equipProgress);
         matrices.method_46416(
            -ModuleManager.viewModel.positionMainX.getValue(),
            ModuleManager.viewModel.positionMainY.getValue(),
            ModuleManager.viewModel.positionMainZ.getValue()
         );
         this.applySwingOffset(matrices, arm, swingProgress);
         matrices.method_46416(
            ModuleManager.viewModel.positionMainX.getValue(),
            -ModuleManager.viewModel.positionMainY.getValue(),
            -ModuleManager.viewModel.positionMainZ.getValue()
         );
      }
   }

   public void renderFirstPersonItemCustom(
      class_742 player,
      float tickDelta,
      float pitch,
      class_1268 hand,
      float swingProgress,
      class_1799 item,
      float equipProgress,
      class_4587 matrices,
      class_4597 vertexConsumers,
      int light
   ) {
      if (!player.method_31550()) {
         boolean bl = hand == class_1268.field_5808;
         class_1306 arm = bl ? player.method_6068() : player.method_6068().method_5928();
         matrices.method_22903();
         if (item.method_31574(class_1802.field_8399)) {
            boolean bl2 = class_1764.method_7781(item);
            boolean bl3 = arm == class_1306.field_6183;
            int i = bl3 ? 1 : -1;
            if (player.method_6115() && player.method_6014() > 0 && player.method_6058() == hand) {
               this.applyEquipOffset(matrices, arm, equipProgress);
               matrices.method_46416(i * -0.4785682F, -0.094387F, 0.05731531F);
               matrices.method_22907(class_7833.field_40714.rotationDegrees(-11.935F));
               matrices.method_22907(class_7833.field_40716.rotationDegrees(i * 65.3F));
               matrices.method_22907(class_7833.field_40718.rotationDegrees(i * -9.785F));
               float f = item.method_7935(mc.field_1724) - (mc.field_1724.method_6014() - tickDelta + 1.0F);
               float g = f / class_1764.method_7775(item, mc.field_1724);
               if (g > 1.0F) {
                  g = 1.0F;
               }

               if (g > 0.1F) {
                  float h = class_3532.method_15374((f - 0.1F) * 1.3F);
                  float j = g - 0.1F;
                  float k = h * j;
                  matrices.method_46416(k * 0.0F, k * 0.004F, k * 0.0F);
               }

               matrices.method_46416(g * 0.0F, g * 0.0F, g * 0.04F);
               matrices.method_22905(1.0F, 1.0F, 1.0F + g * 0.2F);
               matrices.method_22907(class_7833.field_40715.rotationDegrees(i * 45.0F));
            } else {
               float fx = -0.4F * class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) Math.PI);
               float gx = 0.2F * class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) (Math.PI * 2));
               float h = -0.2F * class_3532.method_15374(swingProgress * (float) Math.PI);
               matrices.method_46416(i * fx, gx, h);
               this.applyEquipOffset(matrices, arm, equipProgress);
               this.applySwingOffset(matrices, arm, swingProgress);
               if (bl2 && swingProgress < 0.001F && bl) {
                  matrices.method_46416(i * -0.641864F, 0.0F, 0.0F);
                  matrices.method_22907(class_7833.field_40716.rotationDegrees(i * 10.0F));
               }
            }

            this.renderItem(player, item, bl3 ? class_811.field_4322 : class_811.field_4321, !bl3, matrices, vertexConsumers, light);
         } else {
            boolean bl2 = arm == class_1306.field_6183;
            if (player.method_6115() && player.method_6014() > 0 && player.method_6058() == hand) {
               int l = bl2 ? 1 : -1;
               switch (item.method_7976()) {
                  case field_8952:
                  case field_8949:
                     this.applyEquipOffset(matrices, arm, equipProgress);
                     break;
                  case field_8950:
                  case field_8946:
                     this.applyEatOrDrinkTransformationCustom(matrices, tickDelta, arm, item);
                     this.applyEquipOffset(matrices, arm, equipProgress);
                     break;
                  case field_8953:
                     this.applyEquipOffset(matrices, arm, equipProgress);
                     matrices.method_46416(l * -0.2785682F, 0.18344387F, 0.15731531F);
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(-13.935F));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(l * 35.3F));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees(l * -9.785F));
                     float mx = item.method_7935(mc.field_1724) - (mc.field_1724.method_6014() - tickDelta + 1.0F);
                     float fxx = mx / 20.0F;
                     fxx = (fxx * fxx + fxx * 2.0F) / 3.0F;
                     if (fxx > 1.0F) {
                        fxx = 1.0F;
                     }

                     if (fxx > 0.1F) {
                        float gx = class_3532.method_15374((mx - 0.1F) * 1.3F);
                        float h = fxx - 0.1F;
                        float j = gx * h;
                        matrices.method_46416(j * 0.0F, j * 0.004F, j * 0.0F);
                     }

                     matrices.method_46416(fxx * 0.0F, fxx * 0.0F, fxx * 0.04F);
                     matrices.method_22905(1.0F, 1.0F, 1.0F + fxx * 0.2F);
                     matrices.method_22907(class_7833.field_40715.rotationDegrees(l * 45.0F));
                     break;
                  case field_8951:
                     this.applyEquipOffset(matrices, arm, equipProgress);
                     matrices.method_46416(l * -0.5F, 0.7F, 0.1F);
                     matrices.method_22907(class_7833.field_40714.rotationDegrees(-55.0F));
                     matrices.method_22907(class_7833.field_40716.rotationDegrees(l * 35.3F));
                     matrices.method_22907(class_7833.field_40718.rotationDegrees(l * -9.785F));
                     float m = item.method_7935(mc.field_1724) - (mc.field_1724.method_6014() - tickDelta + 1.0F);
                     float fx = m / 10.0F;
                     if (fx > 1.0F) {
                        fx = 1.0F;
                     }

                     if (fx > 0.1F) {
                        float gx = class_3532.method_15374((m - 0.1F) * 1.3F);
                        float h = fx - 0.1F;
                        float j = gx * h;
                        matrices.method_46416(j * 0.0F, j * 0.004F, j * 0.0F);
                     }

                     matrices.method_46416(0.0F, 0.0F, fx * 0.2F);
                     matrices.method_22905(1.0F, 1.0F, 1.0F + fx * 0.2F);
                     matrices.method_22907(class_7833.field_40715.rotationDegrees(l * 45.0F));
                     break;
                  case field_42717:
                     this.applyBrushTransformation(matrices, tickDelta, arm, item, equipProgress);
               }
            } else if (player.method_6123()) {
               this.applyEquipOffset(matrices, arm, equipProgress);
               int l = bl2 ? 1 : -1;
               matrices.method_46416(l * -0.4F, 0.8F, 0.3F);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(l * 65.0F));
               matrices.method_22907(class_7833.field_40718.rotationDegrees(l * -85.0F));
            } else if (this.disableOffHand.getValue() && hand == class_1268.field_5810) {
               this.applyEquipOffset(matrices, arm, equipProgress);
               this.applySwingOffset(matrices, arm, swingProgress);
            } else {
               this.renderSwordAnimation(matrices, 0.0F, swingProgress, equipProgress, arm);
            }

            this.renderItem(player, item, bl2 ? class_811.field_4322 : class_811.field_4321, !bl2, matrices, vertexConsumers, light);
         }

         matrices.method_22909();
      }
   }

   private void applyBrushTransformation(class_4587 matrices, float tickDelta, class_1306 arm, @NotNull class_1799 stack, float equipProgress) {
      this.applyEquipOffset(matrices, arm, equipProgress);
      float f = mc.field_1724.method_6014() - tickDelta + 1.0F;
      float g = 1.0F - f / stack.method_7935(mc.field_1724);
      float m = -15.0F + 75.0F * class_3532.method_15362(g * 45.0F * (float) Math.PI);
      if (arm != class_1306.field_6183) {
         matrices.method_22904(0.1, 0.83, 0.35);
         matrices.method_22907(class_7833.field_40714.rotationDegrees(-80.0F));
         matrices.method_22907(class_7833.field_40716.rotationDegrees(-90.0F));
         matrices.method_22907(class_7833.field_40714.rotationDegrees(m));
         matrices.method_22904(-0.3, 0.22, 0.35);
      } else {
         matrices.method_22904(-0.25, 0.22, 0.35);
         matrices.method_22907(class_7833.field_40714.rotationDegrees(-80.0F));
         matrices.method_22907(class_7833.field_40716.rotationDegrees(90.0F));
         matrices.method_22907(class_7833.field_40718.rotationDegrees(0.0F));
         matrices.method_22907(class_7833.field_40714.rotationDegrees(m));
      }
   }

   private void applyEquipOffset(@NotNull class_4587 matrices, class_1306 arm, float equipProgress) {
      int i = arm == class_1306.field_6183 ? 1 : -1;
      matrices.method_46416(i * 0.56F, -0.52F + equipProgress * -0.6F, -0.72F);
   }

   private void applySwingOffset(@NotNull class_4587 matrices, class_1306 arm, float swingProgress) {
      int i = arm == class_1306.field_6183 ? 1 : -1;
      float f = class_3532.method_15374(swingProgress * swingProgress * (float) Math.PI);
      matrices.method_22907(class_7833.field_40716.rotationDegrees(i * (45.0F + f * -20.0F)));
      float g = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) Math.PI);
      matrices.method_22907(class_7833.field_40718.rotationDegrees(i * g * -20.0F));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(g * -80.0F));
      matrices.method_22907(class_7833.field_40716.rotationDegrees(i * -45.0F));
   }

   public void renderItem(
      class_1309 entity, class_1799 stack, class_811 renderMode, boolean leftHanded, class_4587 matrices, class_4597 vertexConsumers, int light
   ) {
      if (!stack.method_7960()) {
         mc.method_1480()
            .method_23177(
               entity, stack, renderMode, leftHanded, matrices, vertexConsumers, entity.method_37908(), light, class_4608.field_21444, entity.method_5628()
            );
      }
   }

   private void applyEatOrDrinkTransformationCustom(class_4587 matrices, float tickDelta, class_1306 arm, class_1799 stack) {
      float f = mc.field_1724.method_6014() - tickDelta + 1.0F;
      float g = f / stack.method_7935(mc.field_1724);
      float i = 1.0F - (float)Math.pow(g, 27.0);
      int j = arm == class_1306.field_6183 ? 1 : -1;
      matrices.method_46416(i * 0.6F * j * ModuleManager.viewModel.eatX.getValue(), i * -0.5F * ModuleManager.viewModel.eatY.getValue(), 0.0F);
      matrices.method_22907(class_7833.field_40716.rotationDegrees(j * i * 90.0F));
      matrices.method_22907(class_7833.field_40714.rotationDegrees(i * 10.0F));
      matrices.method_22907(class_7833.field_40718.rotationDegrees(j * i * 30.0F));
   }

   private void translateToViewModel(class_4587 matrices) {
      if (ModuleManager.viewModel.isEnabled()) {
         matrices.method_46416(
            ModuleManager.viewModel.positionMainX.getValue(),
            ModuleManager.viewModel.positionMainY.getValue(),
            ModuleManager.viewModel.positionMainZ.getValue()
         );
      }
   }

   private void translateBack(class_4587 matrices) {
      if (ModuleManager.viewModel.isEnabled()) {
         matrices.method_46416(
            -ModuleManager.viewModel.positionMainX.getValue(),
            -ModuleManager.viewModel.positionMainY.getValue(),
            -ModuleManager.viewModel.positionMainZ.getValue()
         );
      }
   }

   private void translateToViewModelOff(class_4587 matrices) {
      if (ModuleManager.viewModel.isEnabled()) {
         matrices.method_46416(
            ModuleManager.viewModel.positionOffX.getValue(), ModuleManager.viewModel.positionOffY.getValue(), ModuleManager.viewModel.positionOffZ.getValue()
         );
      }
   }

   private void translateBacklOff(class_4587 matrices) {
      if (ModuleManager.viewModel.isEnabled()) {
         matrices.method_46416(
            -ModuleManager.viewModel.positionOffX.getValue(),
            -ModuleManager.viewModel.positionOffY.getValue(),
            -ModuleManager.viewModel.positionOffZ.getValue()
         );
      }
   }

   private static enum Mode {
      Normal,
      Default,
      One,
      Two,
      Three,
      Down,
      Feast,
      Smooth;
   }
}
