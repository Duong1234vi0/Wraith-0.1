package unique.core.gui.mainmenu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_332;
import net.minecraft.class_7833;
import unique.core.utility.render.TextureStorage;

public class MouseTrailParticle {
   public float x;
   public float y;
   public float size;
   public float alpha;
   public double motionX;
   public double motionY;
   public float rotation;
   public float rotationSpeed;
   public int life;
   public int maxLife;

   public MouseTrailParticle(float x, float y) {
      this.x = x;
      this.y = y;
      this.size = 2.0F + (float)Math.random() * 4.0F;
      this.maxLife = 15 + (int)(Math.random() * 15.0);
      this.life = this.maxLife;
      this.alpha = 1.0F;
      this.motionX = (Math.random() - 0.5) * 1.5;
      this.motionY = (Math.random() - 0.5) * 1.5;
      this.rotation = (float)(Math.random() * 360.0);
      this.rotationSpeed = (float)(Math.random() * 15.0 - 7.5);
   }

   public void update() {
      this.x = this.x + (float)this.motionX;
      this.y = this.y + (float)this.motionY;
      this.motionY += 0.05;
      this.motionX *= 0.94;
      this.motionY *= 0.94;
      this.rotation = this.rotation + this.rotationSpeed;
      this.life--;
      this.alpha = (float)this.life / this.maxLife;
   }

   public void render(class_332 context) {
      if (this.life > 0) {
         context.method_51448().method_22903();
         context.method_51448().method_46416(this.x, this.y, 0.0F);
         context.method_51448().method_22907(class_7833.field_40718.rotationDegrees(this.rotation));
         RenderSystem.enableBlend();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
         context.method_25290(
            TextureStorage.core, (int)(-this.size / 2.0F), (int)(-this.size / 2.0F), 0.0F, 0.0F, (int)this.size, (int)this.size, (int)this.size, (int)this.size
         );
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         context.method_51448().method_22909();
      }
   }

   public boolean isDead() {
      return this.life <= 0;
   }
}
