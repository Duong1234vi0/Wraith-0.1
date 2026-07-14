package sky.core.utils.managers.impl.dragmanager;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import sky.core.SkyCore;
import sky.core.events.EventDragging;
import sky.core.events.EventMouseClicked;
import sky.core.events.EventMouseReleased;
import sky.core.events.EventRender2D;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.elements.Element;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.managers.impl.dragmanager.elements.impl.DraggingBooleanElement;
import sky.core.utils.managers.impl.dragmanager.elements.impl.DraggingModeElement;
import sky.core.utils.math.MathUtil;
import sky.core.utils.misc.OtherUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class Dragging implements Wrapper {
   private String name;
   private float x;
   private float y;
   private float width;
   private float height;
   private final float initialX;
   private final float initialY;
   private boolean isDragging;
   private boolean isHovered;
   private boolean settingsVisible;
   private float offsetX;
   private float offsetY;
   private float settingsX;
   private float settingsY;
   private float settingsOffsetX;
   private float settingsOffsetY;
   private final MultiBooleanSetting elements;
   private final AnimationUtil textAlphaAnimation;
   private final AnimationUtil scaleAnimation;
   private final AnimationUtil dragScaleAnimation;
   private static final float DRAG_SCALE = 1.045F;
   private static final float MAX_TILT_DEGREES = 16.0F;
   private static final float TILT_VELOCITY_FACTOR = 4.4F;
   private static final float POS_LERP_DRAG = 0.1F;
   private static final float POS_LERP_IDLE = 0.18F;
   private static final float TILT_LERP_DRAG = 0.12F;
   private static final float TILT_LERP_IDLE = 0.08F;
   private static final float VELOCITY_SMOOTH = 0.1F;
   private float renderX;
   private float renderY;
   private float renderTilt;
   private float prevTargetX;
   private float prevTargetY;
   private float smoothedVelocityX;
   private boolean renderTransformPushed;
   private boolean renderScalePushed;
   private static Dragging currentlyDragging = null;
   private final List settings;
   private final List settingElements;
   private final DraggingManager draggingManager;
   private final boolean draggable;

   public Dragging(String name, float x, float y, MultiBooleanSetting elements) {
      this(name, x, y, elements, true);
   }

   public Dragging(String name, float x, float y, MultiBooleanSetting elements, boolean draggable) {
      this.textAlphaAnimation = new AnimationUtil(0.0F, 15.0F, Easings.LINEAR);
      this.scaleAnimation = new AnimationUtil(0.0F, 10.0F, Easings.LINEAR);
      this.dragScaleAnimation = new AnimationUtil(1.0F, 12.0F, Easings.QUAD_OUT);
      this.settings = new ArrayList();
      this.settingElements = new ArrayList();
      this.name = name;
      this.x = x;
      this.y = y;
      this.initialX = x;
      this.initialY = y;
      this.elements = elements;
      this.draggable = draggable;
      this.renderX = x;
      this.renderY = y;
      this.prevTargetX = x;
      this.prevTargetY = y;
      this.draggingManager = SkyCore.getInstance().getDraggingManager();
      this.draggingManager.addDraggable(this);
      EventManager.register(this);
   }

   public void addSettings(Setting... settings) {
      Setting[] var2 = settings;
      int var3 = settings.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Setting s = var2[var4];
         this.settings.add(s);
         if (s instanceof BooleanSetting bs) {
            this.settingElements.add(new DraggingBooleanElement(bs));
         }

         if (s instanceof ModeSetting bs) {
            this.settingElements.add(new DraggingModeElement(bs));
         }
      }

      this.draggingManager.loadSettingsFor(this);
   }

   @EventTarget
   public void onMouseClicked(EventMouseClicked event) {
      if (mc.currentScreen instanceof ChatScreen) {
         if (!mc.gameSettings.showDebugInfo) {
            float mx = event.getMouseX();
            float my = event.getMouseY();
            if (this.settingsVisible && event.getKey() == 0) {
               float elemW = 75.0F;
               float offset = 24.0F;
               Iterator var6 = this.settingElements.iterator();

               while(var6.hasNext()) {
                  Element element = (Element)var6.next();
                  float textWidth;
                  if (element instanceof DraggingBooleanElement) {
                     DraggingBooleanElement dbe = (DraggingBooleanElement)element;
                     textWidth = Fonts.sf_medium[12].getWidth(dbe.getSetting().getName());
                     elemW = Math.max(elemW, textWidth + offset);
                  } else if (element instanceof DraggingModeElement) {
                     DraggingModeElement dme = (DraggingModeElement)element;
                     textWidth = Fonts.sf_medium[12].getWidth(dme.getSetting().getName());
                     elemW = Math.max(elemW, textWidth + offset);
                  }
               }

               float totalHeight = 2.0F;

               Iterator var14;
               Element element;
               for(var14 = this.settingElements.iterator(); var14.hasNext(); totalHeight += element.getHeight()) {
                  element = (Element)var14.next();
               }

               if (!MathUtil.isHovered(mx, my, this.settingsX, this.settingsY, elemW, totalHeight)) {
                  this.settingsVisible = false;
                  event.setCancelled(true);
                  return;
               }

               var14 = this.settingElements.iterator();

               while(var14.hasNext()) {
                  element = (Element)var14.next();
                  if (element.isHovered(mx, my)) {
                     element.mouseClicked(mx, my, event.getKey());
                     event.setCancelled(true);
                     return;
                  }
               }
            }

            if (!this.isAnySettingsOpen() || this.settingsVisible || event.getKey() == 1) {
               Iterator var11;
               Dragging d;
               if (event.getKey() == 1 && this.isInside(mx, my)) {
                  if (this.isTopmostUnderMouse(mx, my)) {
                     var11 = this.draggingManager.getRenderOrder().iterator();

                     while(var11.hasNext()) {
                        d = (Dragging)var11.next();
                        if (d != this && d.settingsVisible) {
                           d.settingsVisible = false;
                        }
                     }

                     if (!this.settingsVisible) {
                        this.settingsOffsetX = mx - this.x;
                        this.settingsOffsetY = my - this.y;
                        this.settingsX = this.x + this.settingsOffsetX;
                        this.settingsY = this.y + this.settingsOffsetY;
                        this.scaleAnimation.setValue(0.0F);
                     }

                     this.settingsVisible = !this.settingsVisible;
                     event.setCancelled(true);
                  }
               } else {
                  if (this.draggable && event.getKey() == 0 && currentlyDragging == null) {
                     var11 = this.draggingManager.getRenderOrder().iterator();

                     while(var11.hasNext()) {
                        d = (Dragging)var11.next();
                        if (d.isVisible() && d.isInside(mx, my) && d == this) {
                           this.settingsVisible = false;
                           this.isDragging = true;
                           currentlyDragging = this;
                           this.offsetX = mx - this.x;
                           this.offsetY = my - this.y;
                           this.draggingManager.bringToFront(this);
                           event.setCancelled(true);
                           return;
                        }
                     }
                  }

                  if (this.draggable && event.getKey() == 2 && this.isInside(mx, my)) {
                     this.x = this.initialX;
                     this.y = this.initialY;
                     this.syncSmoothPosition();
                     this.renderTilt = 0.0F;
                     this.smoothedVelocityX = 0.0F;
                     this.draggingManager.save();
                     event.setCancelled(true);
                  }

               }
            }
         }
      }
   }

   @EventTarget
   public void onMouseReleased(EventMouseReleased event) {
      if (this.draggable && mc.currentScreen instanceof ChatScreen && !mc.gameSettings.showDebugInfo && event.getKey() == 0 && this.isDragging) {
         this.isDragging = false;
         currentlyDragging = null;
         this.draggingManager.save();
         event.setCancelled(true);
      }

   }

   private boolean isInside(float mouseX, float mouseY) {
      return MathUtil.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
   }

   public void syncSmoothPosition() {
      this.renderX = this.x;
      this.renderY = this.y;
      this.dragScaleAnimation.setValue(1.0F);
   }

   public static void prepareHudDragFrame(float mouseX, float mouseY) {
      if (currentlyDragging != null) {
         currentlyDragging.updatePosition(mouseX, mouseY);
      }

      Iterator var2 = SkyCore.getInstance().getDraggingManager().getRenderOrder().iterator();

      while(var2.hasNext()) {
         Dragging drag = (Dragging)var2.next();
         if (drag != null) {
            drag.tickSmoothing();
         }
      }

   }

   public static void tickAllSmoothing() {
      Iterator var0 = SkyCore.getInstance().getDraggingManager().getRenderOrder().iterator();

      while(var0.hasNext()) {
         Dragging drag = (Dragging)var0.next();
         if (drag != null) {
            drag.tickSmoothing();
         }
      }

   }

   private boolean isVisible() {
      if (this.elements == null) {
         return true;
      } else {
         Boolean value = this.elements.is(this.name);
         return value == null || value;
      }
   }

   public float getTargetX() {
      return this.x;
   }

   public float getTargetY() {
      return this.y;
   }

   public float getX() {
      return this.renderX;
   }

   public float getY() {
      return this.renderY;
   }

   public void tickSmoothing() {
      float dx = this.x - this.prevTargetX;
      this.prevTargetX = this.x;
      this.prevTargetY = this.y;
      this.dragScaleAnimation.update(this.draggable && this.isDragging ? 1.045F : 1.0F);
      if (this.draggable && this.isDragging) {
         this.renderX = MathHelper.lerp(0.1F, this.renderX, this.x);
         this.renderY = MathHelper.lerp(0.1F, this.renderY, this.y);
         this.smoothedVelocityX = MathHelper.lerp(0.1F, this.smoothedVelocityX, dx);
         float targetTilt = MathHelper.clamp(this.smoothedVelocityX * 4.4F, -16.0F, 16.0F);
         this.renderTilt = MathHelper.lerp(0.12F, this.renderTilt, targetTilt);
      } else {
         this.smoothedVelocityX = MathHelper.lerp(0.32F, this.smoothedVelocityX, 0.0F);
         this.renderX = MathHelper.lerp(0.18F, this.renderX, this.x);
         this.renderY = MathHelper.lerp(0.18F, this.renderY, this.y);
         this.renderTilt = MathHelper.lerp(0.08F, this.renderTilt, 0.0F);
      }

   }

   public void pushRenderTransform() {
      float centerX = this.getX() + this.width / 2.0F;
      float centerY = this.getY() + this.height / 2.0F;
      float tilt = this.renderTilt;
      float scale = this.dragScaleAnimation.getValue();
      if (!(Math.abs(tilt) < 0.01F) || !(Math.abs(scale - 1.0F) < 0.001F)) {
         if (Math.abs(scale - 1.0F) >= 0.001F) {
            RenderUtil.scaleStart(centerX, centerY, scale);
            this.renderScalePushed = true;
         }

         if (Math.abs(tilt) >= 0.01F) {
            RenderUtil.rotateStart(centerX, centerY, tilt);
            this.renderTransformPushed = true;
         }

      }
   }

   public void popRenderTransform() {
      if (this.renderTransformPushed) {
         RenderUtil.rotateEnd();
         this.renderTransformPushed = false;
      }

      if (this.renderScalePushed) {
         RenderUtil.scaleEnd();
         this.renderScalePushed = false;
      }

   }

   public void updatePosition(float mouseX, float mouseY) {
      if (this.draggable) {
         if (this.isDragging) {
            int screenWidth = mc.getMainWindow().getScaledWidth();
            int screenHeight = mc.getMainWindow().getScaledHeight();
            float maxX = Math.max(0.0F, (float)screenWidth - this.width);
            float maxY = Math.max(0.0F, (float)screenHeight - this.height);
            this.x = MathHelper.clamp(mouseX - this.offsetX, 0.0F, maxX);
            this.y = MathHelper.clamp(mouseY - this.offsetY, 0.0F, maxY);
            if (this.scaleAnimation.getValue() > 0.0F) {
               this.settingsX = this.x + this.settingsOffsetX;
               this.settingsY = this.y + this.settingsOffsetY;
            }

         }
      }
   }

   @EventTarget
   public void onEvent(EventDragging e) {
      if (currentlyDragging == this) {
         this.updatePosition((float)e.getMouseX(), (float)e.getMouseY());
      }

   }

   @EventTarget
   public void onEvent(EventRender2D.Send e) {
      Vector2f mouse = OtherUtil.getMouse((int)mc.mouseHelper.getMouseX(), (int)mc.mouseHelper.getMouseY());
      float mx = mouse.x / 2.0F;
      float my = mouse.y / 2.0F;
      if (!this.draggable && this.name.equals("Уведомления")) {
         float previewWidth = 19.0F + Fonts.sfregular[12].getWidth("Это пример уведомления") + 8.0F;
         this.x = ((float)mc.getMainWindow().getScaledWidth() - previewWidth) / 2.0F;
         this.y = (float)mc.getMainWindow().getScaledHeight() / 2.0F + 13.0F;
         this.width = previewWidth;
         this.height = 19.0F;
         this.renderX = this.x;
         this.renderY = this.y;
      }

      boolean chatOpen = mc.currentScreen instanceof ChatScreen && SkyCore.getInstance().getModuleManager().getModule(Interface.class).isEnabled() && !mc.gameSettings.showDebugInfo;
      if (!chatOpen) {
         this.settingsVisible = false;
         if (this.isDragging) {
            this.isDragging = false;
            currentlyDragging = null;
            this.draggingManager.save();
         }
      }

      Dragging topmostHovered = null;
      if (chatOpen && !this.isDragging) {
         Iterator var7 = this.draggingManager.getRenderOrder().iterator();

         while(var7.hasNext()) {
            Dragging d = (Dragging)var7.next();
            if (d != null) {
               boolean dVisible = d.isVisible();
               if (dVisible && MathUtil.isHovered(mx, my, d.x, d.y, d.width, d.height)) {
                  topmostHovered = d;
               }
            }
         }
      }

      this.isHovered = chatOpen && !this.isDragging && topmostHovered == this && !this.isAnySettingsOpen() && !this.settingsVisible;
      this.textAlphaAnimation.update(this.isHovered ? 1.0F : 0.0F);
      if (this.textAlphaAnimation.getValue() > 0.0F && this.isVisible()) {
         this.renderTooltip(e);
      }

      this.scaleAnimation.update(this.settingsVisible ? 1.0F : 0.0F);
      if (this.scaleAnimation.getValue() > 0.0F && !this.settingElements.isEmpty()) {
         this.renderSettingsElements(e, mx, my);
      }

   }

   private void renderTooltip(EventRender2D.Send e) {
      float alpha = ThemeEditor.getAlpha(ThemeSettings.TOOLTIP) / 255.0F * this.textAlphaAnimation.getValue();
      if (!(alpha <= 0.01F)) {
         int color = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.TOOLTIP), alpha);
         int outlineColor = ColorUtil.applyOpacity(ColorUtil.getColor(0, 0, 0, 255), alpha * 0.75F);
         float screenWidth = (float)mc.getMainWindow().getScaledWidth();
         float screenHeight = (float)mc.getMainWindow().getScaledHeight();
         float elementX = this.getX();
         float elementY = this.getY();
         float elementCenterX = elementX + this.width / 2.0F;
         float textHeight = Fonts.sf_medium[11].getHeight();
         float lineGap = 2.5F;
         String settingsText;
         if (!this.draggable) {
            settingsText = "ПКМ - Дополнительные настройки";
            float textY = this.getTooltipY(elementY, this.height, textHeight, screenHeight);
            this.drawCenteredTooltipLine(e, settingsText, elementCenterX, textY, screenWidth, color, outlineColor);
         } else {
            settingsText = "СКМ - Сбросить расположение";
            String settingsText = "ПКМ - Дополнительные настройки";
            float blockHeight = textHeight * 2.0F + lineGap;
            float textY = this.getTooltipY(elementY, this.height, blockHeight, screenHeight);
            boolean below = textY > elementY;
            float resetY = below ? textY : textY + textHeight + lineGap;
            float settingsY = below ? textY + textHeight + lineGap : textY;
            float slide = (1.0F - this.textAlphaAnimation.getValue()) * (below ? -textHeight - lineGap : textHeight + lineGap);
            this.drawCenteredTooltipLine(e, settingsText, elementCenterX, resetY + slide, screenWidth, color, outlineColor);
            this.drawCenteredTooltipLine(e, settingsText, elementCenterX, settingsY - slide, screenWidth, color, outlineColor);
         }

      }
   }

   private float getTooltipY(float elementY, float elementHeight, float tooltipHeight, float screenHeight) {
      float belowY = elementY + elementHeight + 3.0F;
      float aboveY = elementY - tooltipHeight - 2.5F;
      float preferredY = elementY + elementHeight < screenHeight / 2.0F ? belowY : aboveY;
      return MathHelper.clamp(preferredY, 1.0F, screenHeight - tooltipHeight - 1.0F);
   }

   private void drawCenteredTooltipLine(EventRender2D.Send e, String text, float centerX, float y, float screenWidth, int color, int outlineColor) {
      float textWidth = Fonts.sf_medium[11].getWidth(text);
      float textX = MathHelper.clamp(centerX - textWidth / 2.0F, 1.0F, screenWidth - textWidth - 1.0F);
      Fonts.sfregular[11].drawOutlineString(e.getStack(), text, (double)textX, (double)y, color, true, true, true, true, outlineColor);
   }

   private void renderSettingsElements(EventRender2D.Send e, float mouseX, float mouseY) {
      float elemW = 75.0F;
      float offset = 24.0F;
      Iterator var6 = this.settingElements.iterator();

      float currentY;
      while(var6.hasNext()) {
         Element element = (Element)var6.next();
         if (element instanceof DraggingBooleanElement dbe) {
            currentY = Fonts.sf_medium[12].getWidth(dbe.getSetting().getName());
            elemW = Math.max(elemW, currentY + offset);
         } else if (element instanceof DraggingModeElement dme) {
            currentY = Fonts.sf_medium[12].getWidth(dme.getSetting().getName());
            elemW = Math.max(elemW, currentY + offset);
         }
      }

      float totalHeight = 4.0F;

      Element element;
      for(Iterator var17 = this.settingElements.iterator(); var17.hasNext(); totalHeight += element.getHeight()) {
         element = (Element)var17.next();
      }

      RenderUtil.scaleStart(this.settingsX + elemW / 2.0F, this.settingsY + totalHeight / 2.0F, this.scaleAnimation.getValue());
      RenderUtil.beginNoLiquidGlass();

      try {
         int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
         int bgColor = ColorUtil.darken(logoColor, 0.1F);
         int bgWithAlpha = ColorUtil.applyOpacity(bgColor, ThemeEditor.getAlpha(ThemeSettings.LOGO) / 255.0F * this.scaleAnimation.getValue());
         RenderUtil.drawRoundedRectangle(this.settingsX - 3.0F, this.settingsY - 1.0F, elemW + 6.0F, totalHeight + 2.0F, 5.0F, bgWithAlpha);
         currentY = this.settingsY + 1.0F;

         Element element;
         for(Iterator var11 = this.settingElements.iterator(); var11.hasNext(); currentY += element.getHeight()) {
            element = (Element)var11.next();
            element.setX(this.settingsX);
            element.setY(currentY);
            element.setWidth(elemW);
            element.render(e.getStack(), mouseX, mouseY, 1.0F);
         }
      } finally {
         RenderUtil.endNoLiquidGlass();
         RenderUtil.scaleEnd();
      }

   }

   private boolean isAnySettingsOpen() {
      Iterator var1 = this.draggingManager.getRenderOrder().iterator();

      Dragging d;
      do {
         if (!var1.hasNext()) {
            return false;
         }

         d = (Dragging)var1.next();
      } while(d == null || !d.settingsVisible);

      return true;
   }

   private boolean isTopmostUnderMouse(float mouseX, float mouseY) {
      Dragging candidate = null;
      Iterator var4 = this.draggingManager.getRenderOrder().iterator();

      while(var4.hasNext()) {
         Dragging d = (Dragging)var4.next();
         if (d != null) {
            boolean dVisible = d.isVisible();
            if (dVisible && MathUtil.isHovered(mouseX, mouseY, d.x, d.y, d.width, d.height)) {
               candidate = d;
            }
         }
      }

      return candidate == this;
   }

   public void setName(String name) {
      this.name = name;
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

   public void setDragging(boolean isDragging) {
      this.isDragging = isDragging;
   }

   public void setHovered(boolean isHovered) {
      this.isHovered = isHovered;
   }

   public void setSettingsVisible(boolean settingsVisible) {
      this.settingsVisible = settingsVisible;
   }

   public void setOffsetX(float offsetX) {
      this.offsetX = offsetX;
   }

   public void setOffsetY(float offsetY) {
      this.offsetY = offsetY;
   }

   public void setSettingsX(float settingsX) {
      this.settingsX = settingsX;
   }

   public void setSettingsY(float settingsY) {
      this.settingsY = settingsY;
   }

   public void setSettingsOffsetX(float settingsOffsetX) {
      this.settingsOffsetX = settingsOffsetX;
   }

   public void setSettingsOffsetY(float settingsOffsetY) {
      this.settingsOffsetY = settingsOffsetY;
   }

   public void setRenderX(float renderX) {
      this.renderX = renderX;
   }

   public void setRenderY(float renderY) {
      this.renderY = renderY;
   }

   public void setRenderTilt(float renderTilt) {
      this.renderTilt = renderTilt;
   }

   public void setPrevTargetX(float prevTargetX) {
      this.prevTargetX = prevTargetX;
   }

   public void setPrevTargetY(float prevTargetY) {
      this.prevTargetY = prevTargetY;
   }

   public void setSmoothedVelocityX(float smoothedVelocityX) {
      this.smoothedVelocityX = smoothedVelocityX;
   }

   public void setRenderTransformPushed(boolean renderTransformPushed) {
      this.renderTransformPushed = renderTransformPushed;
   }

   public void setRenderScalePushed(boolean renderScalePushed) {
      this.renderScalePushed = renderScalePushed;
   }

   public String getName() {
      return this.name;
   }

   public float getWidth() {
      return this.width;
   }

   public float getHeight() {
      return this.height;
   }

   public float getInitialX() {
      return this.initialX;
   }

   public float getInitialY() {
      return this.initialY;
   }

   public boolean isDragging() {
      return this.isDragging;
   }

   public boolean isHovered() {
      return this.isHovered;
   }

   public boolean isSettingsVisible() {
      return this.settingsVisible;
   }

   public float getOffsetX() {
      return this.offsetX;
   }

   public float getOffsetY() {
      return this.offsetY;
   }

   public float getSettingsX() {
      return this.settingsX;
   }

   public float getSettingsY() {
      return this.settingsY;
   }

   public float getSettingsOffsetX() {
      return this.settingsOffsetX;
   }

   public float getSettingsOffsetY() {
      return this.settingsOffsetY;
   }

   public MultiBooleanSetting getElements() {
      return this.elements;
   }

   public AnimationUtil getTextAlphaAnimation() {
      return this.textAlphaAnimation;
   }

   public AnimationUtil getScaleAnimation() {
      return this.scaleAnimation;
   }

   public AnimationUtil getDragScaleAnimation() {
      return this.dragScaleAnimation;
   }

   public float getRenderX() {
      return this.renderX;
   }

   public float getRenderY() {
      return this.renderY;
   }

   public float getRenderTilt() {
      return this.renderTilt;
   }

   public float getPrevTargetX() {
      return this.prevTargetX;
   }

   public float getPrevTargetY() {
      return this.prevTargetY;
   }

   public float getSmoothedVelocityX() {
      return this.smoothedVelocityX;
   }

   public boolean isRenderTransformPushed() {
      return this.renderTransformPushed;
   }

   public boolean isRenderScalePushed() {
      return this.renderScalePushed;
   }

   public List getSettings() {
      return this.settings;
   }

   public List getSettingElements() {
      return this.settingElements;
   }

   public DraggingManager getDraggingManager() {
      return this.draggingManager;
   }

   public boolean isDraggable() {
      return this.draggable;
   }
}
