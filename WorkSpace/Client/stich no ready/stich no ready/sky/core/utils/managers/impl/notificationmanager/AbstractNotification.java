package sky.core.utils.managers.impl.notificationmanager;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Objects;
import net.minecraft.util.text.ITextComponent;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.math.TimeUtil;

public abstract class AbstractNotification {
   protected final ITextComponent message;
   protected final long duration = 1500L;
   protected final TimeUtil timer = new TimeUtil();
   protected final AnimationUtil yAnimation = new AnimationUtil(0.0F, 20.0F);
   protected final AnimationUtil alphaAnimation = new AnimationUtil(0.0F, 10.0F);
   protected boolean forceExpire = false;
   protected boolean initializedY = false;

   public AbstractNotification(ITextComponent message) {
      this.message = message;
   }

   public abstract void render(float var1, float var2, MatrixStack var3);

   public boolean isExpired() {
      return this.forceExpire || this.timer.hasTimeElapsed(1500L);
   }

   public void expireNow() {
      this.forceExpire = true;
   }

   protected void initYIfNeeded(float y) {
      if (!this.initializedY) {
         this.yAnimation.setValue(y);
         this.initializedY = true;
      }

   }

   public ITextComponent getMessage() {
      return this.message;
   }

   public long getDuration() {
      Objects.requireNonNull(this);
      return 1500L;
   }

   public TimeUtil getTimer() {
      return this.timer;
   }

   public AnimationUtil getYAnimation() {
      return this.yAnimation;
   }

   public AnimationUtil getAlphaAnimation() {
      return this.alphaAnimation;
   }

   public boolean isForceExpire() {
      return this.forceExpire;
   }

   public boolean isInitializedY() {
      return this.initializedY;
   }
}
