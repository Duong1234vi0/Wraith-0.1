package unique.core.gui.mainmenu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_332;
import net.minecraft.class_7833;
import unique.core.utility.render.TextureStorage;

public class BackgroundSnowParticle {
   public float x;
   public float y;
   public float speed;
   public float size;
   public float alpha;
   public float rotation;
   public float drift;

   public BackgroundSnowParticle(float x, float y) {
      this.x = x;
      this.y = y;
      this.speed = 0.4F + (float)Math.random() * 0.8F;
      this.size = 3.0F + (float)Math.random() * 4.0F;
      this.alpha = 0.4F + (float)Math.random() * 0.5F;
      this.rotation = (float)(Math.random() * 360.0);
      this.drift = (float)(Math.random() * 10.0);
   }

   public void update(int width, int height) {
      this.y = this.y + this.speed;
      this.x = (float)(this.x + Math.sin(this.drift) * 0.3F);
      this.drift += 0.02F;
      this.rotation += 0.5F;
      if (this.y > height + 10) {
         this.y = -10.0F;
         this.x = (float)(Math.random() * width);
      }

      if (this.x > width) {
         this.x = (float)(Math.random() * width);
      }

      if (this.x < 0.0F) {
         this.x = width;
      }
   }

   public void render(class_332 context) {
      context.method_51448().method_22903();
      context.method_51448().method_46416(this.x, this.y, 0.0F);
      context.method_51448().method_22907(class_7833.field_40718.rotationDegrees(this.rotation));
      RenderSystem.enableBlend();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
      context.method_25290(
         TextureStorage.snowblast,
         (int)(-this.size / 2.0F),
         (int)(-this.size / 2.0F),
         0.0F,
         0.0F,
         (int)this.size,
         (int)this.size,
         (int)this.size,
         (int)this.size
      );
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      context.method_51448().method_22909();
   }
}
