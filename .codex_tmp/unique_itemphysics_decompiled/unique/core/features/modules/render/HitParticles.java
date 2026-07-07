package unique.core.features.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_2189;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.gui.font.FontRenderers;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.math.MathUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;
import unique.core.utility.render.animation.AnimationUtility;

public class HitParticles extends Module {
   private final Setting<HitParticles.Mode> mode = new Setting<>("Mode", HitParticles.Mode.Stars);
   private final Setting<HitParticles.Physics> physics = new Setting<>("Physics", HitParticles.Physics.Fall);
   private final Setting<ColorSetting> colorrr = new Setting<>("Color", new ColorSetting(-2013200640));
   private final Setting<Boolean> onlySelf = new Setting<>("Self", false);
   private final Setting<Integer> amount = new Setting<>("Amount", 2, 1, 5);
   private final Setting<Integer> lifeTime = new Setting<>("LifeTime", 2, 1, 10);
   private final Setting<Integer> speed = new Setting<>("Speed", 2, 1, 20);
   private final Setting<Float> starsScale = new Setting<>("Scale", 3.0F, 1.0F, 10.0F, v -> this.mode.getValue() != HitParticles.Mode.Orbiz);
   private final Setting<HitParticles.ColorMode> colorMode = new Setting<>("ColorMode", HitParticles.ColorMode.Sync);
   private final Setting<ColorSetting> colorH = new Setting<>("HealColor", new ColorSetting(3142544), v -> this.mode.is(HitParticles.Mode.Text));
   private final Setting<ColorSetting> colorD = new Setting<>("DamageColor", new ColorSetting(15811379), v -> this.mode.is(HitParticles.Mode.Text));
   private final HashMap<Integer, Float> healthMap = new HashMap<>();
   private final CopyOnWriteArrayList<HitParticles.Particle> particles = new CopyOnWriteArrayList<>();

   public HitParticles() {
      super("HitParticles", Module.Category.RENDER);
   }

   @Override
   public void onUpdate() {
      this.particles.removeIf(HitParticles.Particle::update);
      if (this.mode.is(HitParticles.Mode.Text)) {
         for (class_1297 entity : mc.field_1687.method_18112()) {
            if (entity != null && !(mc.field_1724.method_5858(entity) > 256.0) && entity.method_5805() && entity instanceof class_1309 lent) {
               Color c = this.colorMode.getValue() == HitParticles.ColorMode.Sync
                  ? HudEditor.getColor((int)MathUtility.random(1.0F, 228.0F))
                  : this.colorrr.getValue().getColorObject();
               float health = lent.method_6032() + lent.method_6067();
               float lastHealth = this.healthMap.getOrDefault(entity.method_5628(), health);
               this.healthMap.put(entity.method_5628(), health);
               if (lastHealth != health) {
                  this.particles
                     .add(
                        new HitParticles.Particle(
                           (float)lent.method_23317(),
                           MathUtility.random((float)(lent.method_23318() + lent.method_17682()), (float)lent.method_23318()),
                           (float)lent.method_23321(),
                           c,
                           MathUtility.random(0.0F, 180.0F),
                           MathUtility.random(10.0F, 60.0F),
                           health - lastHealth
                        )
                     );
               }
            }
         }
      } else {
         for (class_1657 player : mc.field_1687.method_18456()) {
            if ((!this.onlySelf.getValue() || player == mc.field_1724) && player.field_6235 > 0) {
               Color c = this.colorMode.getValue() == HitParticles.ColorMode.Sync
                  ? HudEditor.getColor((int)MathUtility.random(1.0F, 228.0F))
                  : this.colorrr.getValue().getColorObject();

               for (int i = 0; i < this.amount.getValue(); i++) {
                  this.particles
                     .add(
                        new HitParticles.Particle(
                           (float)player.method_23317(),
                           MathUtility.random((float)(player.method_23318() + player.method_17682()), (float)player.method_23318()),
                           (float)player.method_23321(),
                           c,
                           MathUtility.random(0.0F, 180.0F),
                           MathUtility.random(10.0F, 60.0F),
                           0.0F
                        )
                     );
               }
            }
         }
      }
   }

   @Override
   public void onRender3D(class_4587 stack) {
      RenderSystem.disableDepthTest();
      if (mc.field_1724 != null && mc.field_1687 != null) {
         for (HitParticles.Particle particle : this.particles) {
            particle.render(stack);
         }
      }

      RenderSystem.enableDepthTest();
   }

   public static enum ColorMode {
      Custom,
      Sync;
   }

   public static enum Mode {
      Orbiz,
      Stars,
      Hearts,
      Bloom,
      Text;
   }

   public class Particle {
      float x;
      float y;
      float z;
      float px;
      float py;
      float pz;
      float motionX;
      float motionY;
      float motionZ;
      float rotationAngle;
      float rotationSpeed;
      float health;
      long time;
      Color color;

      public Particle(float x, float y, float z, Color color, float rotationAngle, float rotationSpeed, float health) {
         this.x = x;
         this.y = y;
         this.z = z;
         this.px = x;
         this.py = y;
         this.pz = z;
         this.motionX = MathUtility.random(-HitParticles.this.speed.getValue().intValue() / 50.0F, HitParticles.this.speed.getValue().intValue() / 50.0F);
         this.motionY = MathUtility.random(-HitParticles.this.speed.getValue().intValue() / 50.0F, HitParticles.this.speed.getValue().intValue() / 50.0F);
         this.motionZ = MathUtility.random(-HitParticles.this.speed.getValue().intValue() / 50.0F, HitParticles.this.speed.getValue().intValue() / 50.0F);
         this.time = System.currentTimeMillis();
         this.color = color;
         this.rotationAngle = rotationAngle;
         this.rotationSpeed = rotationSpeed;
         this.health = health;
      }

      public long getTime() {
         return this.time;
      }

      public boolean update() {
         double sp = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.px = this.x;
         this.py = this.y;
         this.pz = this.z;
         this.x = this.x + this.motionX;
         this.y = this.y + this.motionY;
         this.z = this.z + this.motionZ;
         if (this.posBlock(this.x, this.y - HitParticles.this.starsScale.getValue() / 10.0F, this.z)) {
            this.motionY = -this.motionY / 1.1F;
            this.motionX /= 1.1F;
            this.motionZ /= 1.1F;
         } else if (this.posBlock(this.x - sp, this.y, this.z - sp)
            || this.posBlock(this.x + sp, this.y, this.z + sp)
            || this.posBlock(this.x + sp, this.y, this.z - sp)
            || this.posBlock(this.x - sp, this.y, this.z + sp)
            || this.posBlock(this.x + sp, this.y, this.z)
            || this.posBlock(this.x - sp, this.y, this.z)
            || this.posBlock(this.x, this.y, this.z + sp)
            || this.posBlock(this.x, this.y, this.z - sp)) {
            this.motionX = -this.motionX;
            this.motionZ = -this.motionZ;
         }

         if (HitParticles.this.physics.getValue() == HitParticles.Physics.Fall) {
            this.motionY -= 0.035F;
         }

         this.motionX /= 1.005F;
         this.motionZ /= 1.005F;
         this.motionY /= 1.005F;
         return System.currentTimeMillis() - this.getTime() > HitParticles.this.lifeTime.getValue() * 1000;
      }

      public void render(class_4587 matrixStack) {
         float size = HitParticles.this.starsScale.getValue();
         float scale = HitParticles.this.mode.is(HitParticles.Mode.Text) ? 0.025F * size : 0.07F;
         double posX = Render2DEngine.interpolate(this.px, this.x, Render3DEngine.getTickDelta())
            - Module.mc.method_1561().field_4686.method_19326().method_10216();
         double posY = Render2DEngine.interpolate(this.py, this.y, Render3DEngine.getTickDelta())
            + 0.1
            - Module.mc.method_1561().field_4686.method_19326().method_10214();
         double posZ = Render2DEngine.interpolate(this.pz, this.z, Render3DEngine.getTickDelta())
            - Module.mc.method_1561().field_4686.method_19326().method_10215();
         matrixStack.method_22903();
         matrixStack.method_22904(posX, posY, posZ);
         matrixStack.method_22905(scale, scale, scale);
         matrixStack.method_46416(size / 2.0F, size / 2.0F, size / 2.0F);
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees(-Module.mc.field_1773.method_19418().method_19330()));
         matrixStack.method_22907(class_7833.field_40714.rotationDegrees(Module.mc.field_1773.method_19418().method_19329()));
         if (HitParticles.this.mode.is(HitParticles.Mode.Text)) {
            matrixStack.method_22907(class_7833.field_40718.rotationDegrees(180.0F));
         } else {
            matrixStack.method_22907(
               class_7833.field_40718.rotationDegrees(this.rotationAngle = this.rotationAngle + AnimationUtility.deltaTime() * this.rotationSpeed)
            );
         }

         matrixStack.method_46416(-size / 2.0F, -size / 2.0F, -size / 2.0F);
         switch ((HitParticles.Mode)HitParticles.this.mode.getValue()) {
            case Orbiz:
               Render2DEngine.drawOrbiz(matrixStack, 0.0F, 0.3, this.color);
               Render2DEngine.drawOrbiz(matrixStack, -0.1F, 0.5, this.color);
               Render2DEngine.drawOrbiz(matrixStack, -0.2F, 0.7, this.color);
               break;
            case Stars:
               Render2DEngine.drawStar(matrixStack, this.color, size);
               break;
            case Hearts:
               Render2DEngine.drawHeart(matrixStack, this.color, size);
               break;
            case Bloom:
               Render2DEngine.drawBloom(matrixStack, this.color, size);
               break;
            case Text:
               FontRenderers.sf_medium
                  .drawCenteredString(
                     matrixStack,
                     MathUtility.round2(this.health) + " ",
                     0.0,
                     0.0,
                     (this.health > 0.0F ? HitParticles.this.colorH.getValue() : HitParticles.this.colorD.getValue()).getColorObject()
                  );
         }

         matrixStack.method_22905(0.8F, 0.8F, 0.8F);
         matrixStack.method_22909();
      }

      private boolean posBlock(double x, double y, double z) {
         class_2248 b = Module.mc.field_1687.method_8320(class_2338.method_49637(x, y, z)).method_26204();
         return !(b instanceof class_2189) && b != class_2246.field_10382 && b != class_2246.field_10164;
      }
   }

   public static enum Physics {
      Fall,
      Fly;
   }
}
