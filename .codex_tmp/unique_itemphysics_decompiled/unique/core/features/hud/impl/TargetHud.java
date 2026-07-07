package unique.core.features.hud.impl;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_266;
import net.minecraft.class_269;
import net.minecraft.class_332;
import net.minecraft.class_408;
import net.minecraft.class_4587;
import net.minecraft.class_742;
import net.minecraft.class_757;
import net.minecraft.class_8646;
import net.minecraft.class_9015;
import unique.core.features.hud.HudElement;
import unique.core.features.modules.client.HudEditor;
import unique.core.features.modules.combat.Aura;
import unique.core.features.modules.combat.AutoAnchor;
import unique.core.features.modules.combat.AutoCrystal;
import unique.core.features.modules.misc.NameProtect;
import unique.core.gui.font.FontRenderers;
import unique.core.gui.hud.HudEditorGui;
import unique.core.setting.Setting;
import unique.core.utility.Timer;
import unique.core.utility.math.MathUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;
import unique.core.utility.render.animation.EaseOutBack;
import unique.core.utility.render.animation.EaseOutCirc;

public class TargetHud extends HudElement {
   private final Setting<Boolean> fixHP = new Setting<>("FixHP", true);
   public EaseOutBack animation = new EaseOutBack();
   public static EaseOutCirc healthAnimation = new EaseOutCirc();
   private class_1309 target;
   private boolean direction;
   private final Timer timer = new Timer();

   public TargetHud() {
      super("TargetHud", 150, 50);
   }

   @Override
   public void onUpdate() {
      this.animation.update(this.direction);
      healthAnimation.update();
   }

   @Override
   public void onRender2D(class_332 context) {
      super.onRender2D(context);
      this.getTarget();
      if (this.target != null) {
         float health = this.getHealth();
         float maxHealth = this.target.method_6063();
         if (this.fixHP.getValue() && this.getScoreboardHealth(this.target) != -1.0F) {
            maxHealth = 20.0F;
         }

         context.method_51448().method_22903();
         if (!HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
            sizeAnimation(context.method_51448(), this.getPosX() + 75.0F, this.getPosY() + 25.0F, this.animation.getAnimationd());
         }

         if (this.animation.getAnimationd() > 0.0) {
            float anim = (float)MathUtility.clamp(this.animation.getAnimationd(), 0.0, 1.0);
            this.renderNurik(context, health, maxHealth, anim);
         }

         context.method_51448().method_22909();
      }
   }

   private void getTarget() {
      if (AutoCrystal.target != null) {
         this.target = AutoCrystal.target;
         this.direction = true;
         if (this.target.method_29504()) {
            AutoCrystal.target = null;
            this.target = null;
         }
      } else if (Aura.target instanceof class_1309) {
         this.target = (class_1309)Aura.target;
         this.direction = true;
      } else if (AutoAnchor.target != null) {
         this.target = AutoAnchor.target;
         this.direction = true;
         if (this.target.method_29504()) {
            AutoAnchor.target = null;
            this.target = null;
         }
      } else if (!(mc.field_1755 instanceof class_408) && !(mc.field_1755 instanceof HudEditorGui)) {
         this.direction = false;
         if (this.animation.getAnimationd() < 0.02) {
            this.target = null;
         }
      } else {
         this.target = mc.field_1724;
         this.direction = true;
      }
   }

   private float getScoreboardHealth(class_1309 entity) {
      if (mc.field_1687 != null && entity != null) {
         class_269 scoreboard = mc.field_1687.method_8428();
         class_266 objective = scoreboard.method_1189(class_8646.field_45158);
         if (objective == null) {
            return -1.0F;
         } else {
            class_9015 scoreHolder = class_9015.method_55422(entity.method_5820());
            return scoreboard.method_1180(scoreHolder, objective).method_55409();
         }
      } else {
         return -1.0F;
      }
   }

   private void renderNurik(class_332 context, float health, float maxHealth, float animationFactor) {
      float hurtPercent = Render2DEngine.interpolateFloat(
            MathUtility.clamp(this.target.field_6235 == 0 ? 0 : this.target.field_6235 + 1, 0, 10), this.target.field_6235, Render3DEngine.getTickDelta()
         )
         / 8.0F;
      healthAnimation.setValue(health);
      float animatedHealth = (float)healthAnimation.getAnimationD();
      Render2DEngine.drawHudBase2(
         context.method_51448(),
         this.getPosX(),
         this.getPosY(),
         137.0F,
         47.5F,
         9.0F,
         HudEditor.blurStrength.getValue(),
         HudEditor.blurOpacity.getValue(),
         animationFactor
      );
      this.setBounds(this.getPosX(), this.getPosY(), 137.0F, 47.5F);
      if (this.target instanceof class_1657) {
         RenderSystem.setShaderTexture(0, ((class_742)this.target).method_52814().comp_1626());
      } else {
         RenderSystem.setShaderTexture(0, mc.method_1561().method_3953(this.target).method_3931(this.target));
      }

      context.method_51448().method_22903();
      context.method_51448().method_46416(this.getPosX() + 23.5F, this.getPosY() + 23.5F, 0.0F);
      context.method_51448().method_22905(1.0F - hurtPercent / 15.0F, 1.0F - hurtPercent / 15.0F, 1.0F);
      context.method_51448().method_46416(-(this.getPosX() + 23.5F), -(this.getPosY() + 23.5F), 0.0F);
      RenderSystem.enableBlend();
      RenderSystem.colorMask(false, false, false, true);
      RenderSystem.clear(16384, false);
      RenderSystem.colorMask(true, true, true, true);
      RenderSystem.setShader(class_757::method_34540);
      Render2DEngine.renderRoundedQuadInternal(
         context.method_51448().method_23760().method_23761(),
         animationFactor,
         animationFactor,
         animationFactor,
         animationFactor,
         this.getPosX() + 3.5F,
         this.getPosY() + 3.5F,
         this.getPosX() + 43.5F,
         this.getPosY() + 43.5F,
         7.0,
         10.0
      );
      RenderSystem.blendFunc(772, 773);
      RenderSystem.setShaderColor(animationFactor, animationFactor - hurtPercent / 2.0F, animationFactor - hurtPercent / 2.0F, animationFactor);
      Render2DEngine.renderTexture(context.method_51448(), this.getPosX() + 3.5F, this.getPosY() + 3.5F, 40.0, 40.0, 8.0F, 8.0F, 8.0, 8.0, 64.0, 64.0);
      Render2DEngine.renderTexture(context.method_51448(), this.getPosX() + 3.5F, this.getPosY() + 3.5F, 40.0, 40.0, 40.0F, 8.0F, 8.0, 8.0, 64.0, 64.0);
      RenderSystem.defaultBlendFunc();
      context.method_51448().method_22909();
      this.renderItems(context);
      Render2DEngine.drawGradientRound(
         context.method_51448(),
         this.getPosX() + 48.0F,
         this.getPosY() + 32.0F,
         85.0F,
         11.0F,
         4.0F,
         HudEditor.getColor(0).darker(),
         HudEditor.getColor(0).darker().darker(),
         HudEditor.getColor(0).darker().darker(),
         HudEditor.getColor(0).darker().darker()
      );
      Render2DEngine.renderRoundedGradientRect(
         context.method_51448(),
         HudEditor.getColor(270),
         HudEditor.getColor(0),
         HudEditor.getColor(0),
         HudEditor.getColor(270),
         this.getPosX() + 48.0F,
         this.getPosY() + 32.0F,
         (int)MathUtility.clamp(85.0F * (animatedHealth / maxHealth), 8.0F, 85.0F),
         11.0F,
         4.0F
      );
      FontRenderers.sf_bold
         .drawCenteredString(
            context.method_51448(),
            String.valueOf(Math.round(health * 10.0F) / 10.0F),
            this.getPosX() + 92.0F,
            this.getPosY() + 35.0F,
            Render2DEngine.applyOpacity(-1, animationFactor)
         );
      FontRenderers.sf_bold
         .drawString(
            context.method_51448(),
            NameProtect.getCustomName(this.target.method_5477().getString()),
            this.getPosX() + 48.0F,
            this.getPosY() + 7.0F,
            Render2DEngine.applyOpacity(-1, animationFactor)
         );
   }

   private void renderItems(class_332 context) {
      if (this.target instanceof class_1657 player) {
         class_1799[] var13 = new class_1799[]{
            player.method_6047(),
            player.method_31548().method_7372(3),
            player.method_31548().method_7372(2),
            player.method_31548().method_7372(1),
            player.method_31548().method_7372(0),
            player.method_6079()
         };
         float scale = 0.7F;
         int baseX = (int)(this.getPosX() + 48.0F);
         int baseY = (int)(this.getPosY() + 17.0F);
         context.method_51448().method_22903();
         context.method_51448().method_22905(scale, scale, 1.0F);
         int x = (int)(baseX / scale);
         int y = (int)(baseY / scale);

         for (class_1799 stack : var13) {
            if (!stack.method_7960()) {
               context.method_51427(stack, x, y);
               context.method_51431(mc.field_1772, stack, x, y);
            }

            x += 18;
         }

         context.method_51448().method_22909();
      }
   }

   public float getHealth() {
      if (this.fixHP.getValue()) {
         float sb = this.getScoreboardHealth(this.target);
         if (sb >= 0.0F) {
            return sb;
         }
      }

      return Math.max(this.target.method_6032(), 0.0F);
   }

   public static void sizeAnimation(class_4587 ms, double x, double y, double a) {
      ms.method_22904(x, y, 0.0);
      ms.method_22905((float)a, (float)a, 1.0F);
      ms.method_22904(-x, -y, 0.0);
   }
}
