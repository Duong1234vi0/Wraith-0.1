package sky.core.modules.api.elements;

import sky.core.ui.gui.Panel;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.render.font.StyledFont;

public class Element implements IBuilder {
   private float x;
   private float y;
   private float width;
   private float height;
   private Panel panel;
   private final AnimationUtil visibilityAnimation = new AnimationUtil(0.0F, 15.0F);
   private final StyledFont.TextScrollState scrollState = new StyledFont.TextScrollState();

   public boolean isHovered(float mouseX, float mouseY) {
      return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
   }

   public boolean isHovered(float mouseX, float mouseY, float height) {
      return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + height;
   }

   public boolean isVisible() {
      return true;
   }

   public float getVisibilityAnimation() {
      this.visibilityAnimation.update(this.isVisible() ? 1.0F : 0.0F);
      return this.visibilityAnimation.getValue();
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public float getWidth() {
      return this.width;
   }

   public float getHeight() {
      return this.height;
   }

   public Panel getPanel() {
      return this.panel;
   }

   public StyledFont.TextScrollState getScrollState() {
      return this.scrollState;
   }

   public void setX(float x) {
      this.x = x;
   }

   public void setY(float y) {
      this.y = y;
   }

   public void setWidth(float width) {
      this.width = width;
   }

   public void setHeight(float height) {
      this.height = height;
   }

   public void setPanel(Panel panel) {
      this.panel = panel;
   }
}
