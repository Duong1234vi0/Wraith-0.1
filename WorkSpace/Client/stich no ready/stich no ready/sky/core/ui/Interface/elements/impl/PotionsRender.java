package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.renderer.texture.PotionSpriteUploader;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import sky.core.events.EventRender2D;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class PotionsRender implements ElementRender {
   private final Dragging dragging;
   private final AnimationUtil alphaAnimation = new AnimationUtil(0.0F, 10.0F);
   private final AnimationUtil ordinaryAlphaAnimation = new AnimationUtil(0.0F, 10.0F);
   private float ordinaryAnimatedWidth = 40.0F;
   private final Map ordinaryEffectAnimations = new HashMap();
   private final Map itemAnimations = new HashMap();
   private final Map lastDurationTexts = new HashMap();
   private final Map lastDurationTicks = new HashMap();
   private static final int DURATION_HIDE_TICKS = 72000;
   public static BooleanSetting alphabg = new BooleanSetting("Прозрачный фон", false);
   public static BooleanSetting badeffects = new BooleanSetting("Отображать плохие эффекты", true);
   private int currentEffectIndex = 0;
   private long lastEffectSwitchTime = 0L;
   private static final long EFFECT_SWITCH_INTERVAL = 1000L;
   private static final Map EFFECT_TRANSLATIONS = new HashMap();
   private static final Map EFFECT_ICON_COLORS;

   public void render(EventRender2D.Post event) {
      if (Interface.isNewHud()) {
         this.renderOrdinary(event);
      } else {
         this.renderNursultan(event);
      }

   }

   private String getEffectNameTranslated(Effect effect, int amplifier) {
      String key = effect.getName();
      String name = (String)EFFECT_TRANSLATIONS.getOrDefault(key, I18n.format(key, new Object[0]));
      if (amplifier > 0) {
         name = name + " " + (amplifier + 1);
      }

      return name;
   }

   private void renderOrdinary(EventRender2D.Post event) {
      MatrixStack ms = event.getStack();
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      List activeEffects = new ArrayList();
      boolean hasActive = false;
      Minecraft var10000 = mc;
      Iterator var7 = Minecraft.player.getActivePotionEffects().iterator();

      while(true) {
         EffectInstance effectInstance;
         Effect effect;
         do {
            if (!var7.hasNext()) {
               var7 = this.ordinaryEffectAnimations.entrySet().iterator();

               while(var7.hasNext()) {
                  Map.Entry entry = (Map.Entry)var7.next();
                  var10000 = mc;
                  boolean hasEffect = Minecraft.player.getActivePotionEffects().stream().anyMatch((e) -> {
                     return e.getPotion() == entry.getKey() && ((Boolean)badeffects.get() || e.getPotion().getEffectType() != EffectType.HARMFUL);
                  });
                  if (!hasEffect) {
                     ((AnimationUtil)entry.getValue()).update(0.0F);
                  }
               }

               boolean isChatOpen = mc.currentScreen instanceof ChatScreen;
               boolean show = isChatOpen || hasActive;
               this.ordinaryAlphaAnimation.update(show ? 1.0F : 0.0F);
               float globalAlpha = this.ordinaryAlphaAnimation.getValue();
               if (globalAlpha <= 0.01F) {
                  this.dragging.setWidth(51.5F);
                  this.dragging.setHeight(11.0F);
                  return;
               }

               List currentIds = new ArrayList();
               Iterator var11 = activeEffects.iterator();

               while(var11.hasNext()) {
                  EffectInstance effectInstance = (EffectInstance)var11.next();
                  Effect effect = effectInstance.getPotion();
                  currentIds.add(effect.getName());
               }

               this.ordinaryEffectAnimations.entrySet().removeIf((e) -> {
                  return !currentIds.contains(e.getKey()) && ((AnimationUtil)e.getValue()).isDone() && ((AnimationUtil)e.getValue()).getValue() <= 0.01F;
               });
               if (isChatOpen && activeEffects.isEmpty()) {
                  this.renderPreviewMode(event, activeEffects, globalAlpha);
                  return;
               }

               float baseCardWidth = 51.5F;
               float cardHeight = 22.0F;
               float cardRadius = 5.0F;
               float cardPaddingX = 6.0F;
               float cardPaddingYTop = 3.5F;
               float cardPaddingYBottom = 4.0F;
               float cardGap = 5.0F;
               int mainColor = ThemeEditor.getColor(ThemeSettings.LOGO);
               int cardBgColor = ColorUtil.darken(mainColor, 0.1F);
               float iconSize = 13.0F;
               float iconTextGap = 3.0F;
               float currentY = posY;
               float maxCardWidth = baseCardWidth;
               Iterator var24 = activeEffects.iterator();

               while(var24.hasNext()) {
                  EffectInstance effectInstance = (EffectInstance)var24.next();
                  Effect effect = effectInstance.getPotion();
                  AnimationUtil itemAnim = (AnimationUtil)this.ordinaryEffectAnimations.get(effect);
                  if (itemAnim != null) {
                     float animVal = itemAnim.getValue();
                     if (!(animVal <= 0.01F)) {
                        float itemAlpha = animVal * globalAlpha;
                        int nameColor = ColorUtil.rgba(255, 255, 255, (int)(255.0F * itemAlpha));
                        int timeColor = ColorUtil.rgba(255, 255, 255, (int)(64.0F * itemAlpha));
                        String effectName = this.getEffectNameTranslated(effect, effectInstance.getAmplifier());
                        String durationText = EffectUtils.getPotionDurationString(effectInstance, 1.0F);
                        float nameWidth = Fonts.sfregular[12].getWidth(effectName);
                        float timeWidth = Fonts.sfregular[12].getWidth(durationText);
                        float textWidth = Math.max(nameWidth, timeWidth);
                        float cardWidth = Math.max(baseCardWidth, cardPaddingX + iconSize + iconTextGap + textWidth + cardPaddingX);
                        maxCardWidth = Math.max(maxCardWidth, cardWidth);
                        int bgWithAlpha = ColorUtil.applyOpacity(cardBgColor, itemAlpha);
                        RenderUtil.drawRoundedRectangle(posX, currentY, cardWidth, cardHeight, cardRadius, bgWithAlpha);
                        PotionSpriteUploader potionSpriteUploader = mc.getPotionSpriteUploader();
                        TextureAtlasSprite sprite = potionSpriteUploader.getSprite(effect);
                        float iconX = posX + cardPaddingX;
                        float iconY = currentY + cardPaddingYTop + (cardHeight - cardPaddingYTop - cardPaddingYBottom - iconSize) / 2.0F;
                        mc.getTextureManager().bindTexture(sprite.getAtlasTexture().getTextureLocation());
                        RenderSystem.enableBlend();
                        RenderSystem.defaultBlendFunc();
                        RenderSystem.color4f(1.0F, 1.0F, 1.0F, itemAlpha);
                        AbstractGui.blit(ms, (int)iconX, (int)iconY, 0, (int)iconSize, (int)iconSize, sprite);
                        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                        float textX = iconX + iconSize + iconTextGap;
                        float textY = currentY + cardPaddingYTop + 2.5F;
                        float lineHeight = Fonts.sf_medium[12].getFontHeight();
                        Fonts.sfregular[12].drawString(ms, effectName, (double)textX, (double)textY, nameColor);
                        Fonts.sfregular[12].drawString(ms, durationText, (double)textX, (double)(textY + lineHeight + 0.5F), timeColor);
                        currentY += (cardHeight + cardGap) * animVal;
                     }
                  }
               }

               float totalHeight = (float)activeEffects.size() * (cardHeight + cardGap) - cardGap;
               if (totalHeight < 0.0F) {
                  totalHeight = cardHeight;
               }

               this.dragging.setWidth(maxCardWidth);
               this.dragging.setHeight(totalHeight);
               return;
            }

            effectInstance = (EffectInstance)var7.next();
            effect = effectInstance.getPotion();
         } while(!(Boolean)badeffects.get() && effect.getEffectType() == EffectType.HARMFUL);

         AnimationUtil anim = (AnimationUtil)this.ordinaryEffectAnimations.computeIfAbsent(effect, (k) -> {
            return new AnimationUtil(0.0F, 10.0F);
         });
         anim.update(1.0F);
         activeEffects.add(effectInstance);
         hasActive = true;
      }
   }

   private void renderPreviewMode(EventRender2D.Post event, List activeEffects, float globalAlpha) {
      MatrixStack ms = event.getStack();
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      List effectKeys = new ArrayList(EFFECT_TRANSLATIONS.keySet());
      if (!effectKeys.isEmpty()) {
         long currentTime = System.currentTimeMillis();
         if (currentTime - this.lastEffectSwitchTime >= 1000L) {
            this.lastEffectSwitchTime = currentTime;
            this.currentEffectIndex = (this.currentEffectIndex + 1) % effectKeys.size();
         }

         String currentEffectKey = (String)effectKeys.get(this.currentEffectIndex % effectKeys.size());
         String effectName = (String)EFFECT_TRANSLATIONS.getOrDefault(currentEffectKey, "Эффект");
         effectName = effectName + " 2";
         float baseCardWidth = 51.5F;
         float cardHeight = 22.0F;
         float cardRadius = 5.0F;
         float cardPaddingX = 6.0F;
         float cardPaddingYTop = 3.5F;
         float cardPaddingYBottom = 4.0F;
         int mainColor = ThemeEditor.getColor(ThemeSettings.LOGO);
         int cardBgColor = ColorUtil.darken(mainColor, 0.1F);
         float iconSize = 13.0F;
         float iconTextGap = 3.0F;
         String durationText = "1:23";
         float nameWidth = Fonts.sfregular[12].getWidth(effectName);
         float timeWidth = Fonts.sfregular[12].getWidth(durationText);
         float textWidth = Math.max(nameWidth, timeWidth);
         float cardWidth = Math.max(baseCardWidth, cardPaddingX + iconSize + iconTextGap + textWidth + cardPaddingX);
         int nameColor = ColorUtil.rgba(255, 255, 255, (int)(255.0F * globalAlpha));
         int timeColor = ColorUtil.rgba(255, 255, 255, (int)(64.0F * globalAlpha));
         int bgColor = ColorUtil.darken(mainColor, 0.1F);
         int bgWithAlpha = ColorUtil.applyOpacity(bgColor, globalAlpha);
         RenderUtil.drawRoundedRectangle(posX, posY, cardWidth, cardHeight, cardRadius, bgWithAlpha);
         float iconX = posX + cardPaddingX;
         float iconY = posY + cardPaddingYTop + (cardHeight - cardPaddingYTop - cardPaddingYBottom - iconSize) / 2.0F;
         String effectId = currentEffectKey.replace("effect.minecraft.", "minecraft:");
         Effect previewEffect = (Effect)Registry.EFFECTS.getOrDefault(new ResourceLocation(effectId));
         if (previewEffect != null) {
            PotionSpriteUploader potionSpriteUploader = mc.getPotionSpriteUploader();
            TextureAtlasSprite sprite = potionSpriteUploader.getSprite(previewEffect);
            mc.getTextureManager().bindTexture(sprite.getAtlasTexture().getTextureLocation());
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, globalAlpha);
            AbstractGui.blit(ms, (int)iconX, (int)iconY, 0, (int)iconSize, (int)iconSize, sprite);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
         } else {
            int iconColor = (Integer)EFFECT_ICON_COLORS.getOrDefault(currentEffectKey, 9079434);
            int iconWithAlpha = ColorUtil.applyOpacity(iconColor, globalAlpha);
            RenderUtil.drawRoundedRectangle(iconX, iconY, iconSize, iconSize, 2.0F, iconWithAlpha);
         }

         float textX = iconX + iconSize + iconTextGap;
         float textY = posY + cardPaddingYTop + 2.5F;
         float lineHeight = Fonts.sf_medium[12].getFontHeight();
         Fonts.sfregular[12].drawString(ms, effectName, (double)textX, (double)textY, nameColor);
         Fonts.sfregular[12].drawString(ms, durationText, (double)textX, (double)(textY + lineHeight + 0.5F), timeColor);
         this.dragging.setWidth(cardWidth);
         this.dragging.setHeight(cardHeight);
      }
   }

   private void renderNursultan(EventRender2D.Post event) {
      MatrixStack ms = event.getStack();
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      List activeEffects = new ArrayList();
      boolean hasActive = false;
      Minecraft var10000 = mc;
      Iterator var7 = Minecraft.player.getActivePotionEffects().iterator();

      while(true) {
         EffectInstance effectInstance;
         Effect effect;
         do {
            if (!var7.hasNext()) {
               var7 = this.ordinaryEffectAnimations.entrySet().iterator();

               while(var7.hasNext()) {
                  Map.Entry entry = (Map.Entry)var7.next();
                  var10000 = mc;
                  boolean hasEffect = Minecraft.player.getActivePotionEffects().stream().anyMatch((e) -> {
                     return e.getPotion() == entry.getKey() && ((Boolean)badeffects.get() || e.getPotion().getEffectType() != EffectType.HARMFUL);
                  });
                  if (!hasEffect) {
                     ((AnimationUtil)entry.getValue()).update(0.0F);
                  }
               }

               boolean isChatOpen = mc.currentScreen instanceof ChatScreen;
               boolean show = isChatOpen || hasActive;
               this.ordinaryAlphaAnimation.update(show ? 1.0F : 0.0F);
               float globalAlpha = this.ordinaryAlphaAnimation.getValue();
               if (globalAlpha <= 0.01F) {
                  this.dragging.setWidth(51.5F);
                  this.dragging.setHeight(11.0F);
                  return;
               }

               List currentIds = new ArrayList();
               Iterator var11 = activeEffects.iterator();

               while(var11.hasNext()) {
                  EffectInstance effectInstance = (EffectInstance)var11.next();
                  Effect effect = effectInstance.getPotion();
                  currentIds.add(effect.getName());
               }

               this.ordinaryEffectAnimations.entrySet().removeIf((e) -> {
                  return !currentIds.contains(((Effect)e.getKey()).getName()) && ((AnimationUtil)e.getValue()).isDone() && ((AnimationUtil)e.getValue()).getValue() <= 0.01F;
               });
               float headerHeight = 12.3F;
               float itemHeight = 9.6F;
               float topOffset = 3.8F;
               float maxCombinedWidthCalc = 0.0F;
               Iterator var15 = activeEffects.iterator();

               while(true) {
                  EffectInstance effectInstance;
                  AnimationUtil anim;
                  do {
                     do {
                        if (!var15.hasNext()) {
                           float width = Math.max(65.0F, maxCombinedWidthCalc);
                           float contentHeight = 0.0F;
                           Iterator var56 = activeEffects.iterator();

                           while(var56.hasNext()) {
                              EffectInstance effectInstance = (EffectInstance)var56.next();
                              Effect effect = effectInstance.getPotion();
                              AnimationUtil anim = (AnimationUtil)this.ordinaryEffectAnimations.get(effect);
                              if (anim != null) {
                                 contentHeight += itemHeight * anim.getValue();
                              }
                           }

                           float windowHeight = headerHeight + (contentHeight <= 0.01F ? 6.0F : contentHeight + 6.7F);
                           int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
                           int iconColor = ColorUtil.applyOpacity(logoColor, ThemeEditor.getAlpha(ThemeSettings.LOGO) / 255.0F * globalAlpha);
                           int textColor = ColorUtil.applyOpacity(-1, globalAlpha);
                           RenderUtil.drawBlurredRoundedRectangle(posX, posY + topOffset, width, windowHeight - topOffset, 4.9F, (Boolean)alphabg.get() ? ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.WINDOW_BG), 0) : ThemeEditor.getColor(ThemeSettings.WINDOW_BG), globalAlpha);
                           float renderY;
                           if (width > 35.0F) {
                              renderY = posX + 54.0F + (width - 65.0F);
                              Fonts.skycore[13].drawString(ms, "e", (double)(renderY + 0.5F), (double)(posY + 10.0F), iconColor);
                              Fonts.sf_medium[15].drawString(ms, "Potions", (double)(posX + 4.0F), (double)(posY + 9.0F), textColor);
                           }

                           renderY = posY + headerHeight + 3.0F;
                           Iterator var65 = activeEffects.iterator();

                           while(var65.hasNext()) {
                              EffectInstance effectInstance = (EffectInstance)var65.next();
                              Effect effect = effectInstance.getPotion();
                              AnimationUtil itemAnim = (AnimationUtil)this.ordinaryEffectAnimations.get(effect);
                              if (itemAnim != null) {
                                 float animVal = itemAnim.getValue();
                                 if (!(animVal <= 0.01F)) {
                                    float slideOffset = (1.0F - animVal) * 10.0F;
                                    float itemAlpha = animVal * globalAlpha;
                                    int itemTextColor = ColorUtil.applyOpacity(-1, itemAlpha);
                                    String effectName = I18n.format(effectInstance.getEffectName(), new Object[0]);
                                    String amplifierText = I18n.format("enchantment.level." + (effectInstance.getAmplifier() + 1), new Object[0]);
                                    String durationText = EffectUtils.getPotionDurationString(effectInstance, 1.0F);
                                    PotionSpriteUploader potionSpriteUploader = mc.getPotionSpriteUploader();
                                    TextureAtlasSprite sprite = potionSpriteUploader.getSprite(effect);
                                    float iconSize = 7.0F;
                                    float iconX = posX + 3.0F - slideOffset;
                                    float iconY = renderY + (itemHeight - iconSize) / 2.0F + 1.9F;
                                    mc.getTextureManager().bindTexture(sprite.getAtlasTexture().getTextureLocation());
                                    RenderSystem.enableBlend();
                                    RenderSystem.defaultBlendFunc();
                                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, itemAlpha);
                                    AbstractGui.blit(ms, (int)iconX, (int)iconY, 0, (int)iconSize, (int)iconSize, sprite);
                                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                                    float textY = renderY + 4.05F;
                                    float nameX = iconX + iconSize + 4.0F;
                                    Fonts.sf_medium[14].drawString(ms, effectName, (double)(nameX - 1.0F), (double)textY, itemTextColor);
                                    float nameWidth = Fonts.sf_medium[14].getWidth(effectName);
                                    Fonts.sf_medium[14].drawString(ms, amplifierText, (double)(nameX + nameWidth + 2.0F), (double)textY, itemTextColor);
                                    float durationWidth = Fonts.sf_medium[14].getWidth(durationText);
                                    float dx = posX + width - durationWidth - 3.0F + slideOffset;
                                    if (width > durationWidth + 12.0F) {
                                       Fonts.sf_medium[14].drawString(ms, durationText, (double)dx, (double)textY, itemTextColor);
                                    }

                                    renderY += itemHeight * animVal;
                                 }
                              }
                           }

                           this.dragging.setHeight(windowHeight);
                           this.dragging.setWidth(width);
                           return;
                        }

                        effectInstance = (EffectInstance)var15.next();
                        Effect effect = effectInstance.getPotion();
                        anim = (AnimationUtil)this.ordinaryEffectAnimations.get(effect);
                     } while(anim == null);
                  } while(!(anim.getValue() > 0.01F) && !activeEffects.contains(effectInstance));

                  String effectName = I18n.format(effectInstance.getEffectName(), new Object[0]);
                  String amplifierText = I18n.format("enchantment.level." + (effectInstance.getAmplifier() + 1), new Object[0]);
                  String durationText = EffectUtils.getPotionDurationString(effectInstance, 1.0F);
                  float combined = Fonts.sf_medium[14].getWidth(effectName) + Fonts.sf_medium[14].getWidth(amplifierText) + Fonts.sf_medium[14].getWidth(durationText) + 22.0F;
                  maxCombinedWidthCalc = Math.max(maxCombinedWidthCalc, combined);
               }
            }

            effectInstance = (EffectInstance)var7.next();
            effect = effectInstance.getPotion();
         } while(!(Boolean)badeffects.get() && effect.getEffectType() == EffectType.HARMFUL);

         AnimationUtil anim = (AnimationUtil)this.ordinaryEffectAnimations.computeIfAbsent(effect, (k) -> {
            return new AnimationUtil(0.0F, 10.0F);
         });
         anim.update(1.0F);
         activeEffects.add(effectInstance);
         hasActive = true;
      }
   }

   private List getActiveItems() {
      List activeItems = new ArrayList();
      Minecraft var10000 = mc;
      Iterator var2 = Minecraft.player.getActivePotionEffects().iterator();

      while(true) {
         EffectInstance instance;
         Effect effect;
         do {
            if (!var2.hasNext()) {
               return activeItems;
            }

            instance = (EffectInstance)var2.next();
            effect = instance.getPotion();
         } while(!(Boolean)badeffects.get() && effect.getEffectType() == EffectType.HARMFUL);

         activeItems.add(new PotionItem(effect, instance.getAmplifier()));
      }
   }

   private float[] calculateDimensions(List dimsItems, Map effectInstances) {
      float maxNameWidth = 0.0F;
      float maxDurationWidth = 0.0F;

      String duration;
      for(Iterator var5 = dimsItems.iterator(); var5.hasNext(); maxDurationWidth = Math.max(maxDurationWidth, Fonts.sf_medium[12].getWidth(duration))) {
         PotionItem item = (PotionItem)var5.next();
         maxNameWidth = Math.max(maxNameWidth, Fonts.sf_medium[12].getWidth(item.getEffectName()));
         if (effectInstances.containsKey(item)) {
            duration = ((EffectInstance)effectInstances.get(item)).getDuration() > 72000 ? "**:**" : EffectUtils.getPotionDurationString((EffectInstance)effectInstances.get(item), 1.0F);
         } else {
            duration = (Integer)this.lastDurationTicks.getOrDefault(item, 0) > 72000 ? "**:**" : (String)this.lastDurationTexts.getOrDefault(item, "");
         }
      }

      float width = Math.max(90.0F, 3.5F + maxNameWidth + 3.5F + maxDurationWidth + 3.5F + 0.5F + 2.5F + 7.0F + 3.5F);
      return new float[]{width};
   }

   public PotionsRender(Dragging dragging) {
      this.dragging = dragging;
   }

   static {
      EFFECT_TRANSLATIONS.put("effect.minecraft.speed", "Скорость");
      EFFECT_TRANSLATIONS.put("effect.minecraft.slowness", "Медлительность");
      EFFECT_TRANSLATIONS.put("effect.minecraft.haste", "Спешка");
      EFFECT_TRANSLATIONS.put("effect.minecraft.mining_fatigue", "Усталость");
      EFFECT_TRANSLATIONS.put("effect.minecraft.strength", "Сила");
      EFFECT_TRANSLATIONS.put("effect.minecraft.instant_health", "Моментальное лечение");
      EFFECT_TRANSLATIONS.put("effect.minecraft.instant_damage", "Моментальный урон");
      EFFECT_TRANSLATIONS.put("effect.minecraft.jump_boost", "Прыгучесть");
      EFFECT_TRANSLATIONS.put("effect.minecraft.nausea", "Тошнота");
      EFFECT_TRANSLATIONS.put("effect.minecraft.regeneration", "Регенерация");
      EFFECT_TRANSLATIONS.put("effect.minecraft.resistance", "Сопротивление");
      EFFECT_TRANSLATIONS.put("effect.minecraft.fire_resistance", "Огнестойкость");
      EFFECT_TRANSLATIONS.put("effect.minecraft.water_breathing", "Подводное дыхание");
      EFFECT_TRANSLATIONS.put("effect.minecraft.invisibility", "Невидимость");
      EFFECT_TRANSLATIONS.put("effect.minecraft.blindness", "Слепота");
      EFFECT_TRANSLATIONS.put("effect.minecraft.night_vision", "Ночное зрение");
      EFFECT_TRANSLATIONS.put("effect.minecraft.hunger", "Голод");
      EFFECT_TRANSLATIONS.put("effect.minecraft.weakness", "Слабость");
      EFFECT_TRANSLATIONS.put("effect.minecraft.poison", "Отравление");
      EFFECT_TRANSLATIONS.put("effect.minecraft.wither", "Иссушение");
      EFFECT_TRANSLATIONS.put("effect.minecraft.health_boost", "Прилив здоровья");
      EFFECT_TRANSLATIONS.put("effect.minecraft.absorption", "Поглощение");
      EFFECT_TRANSLATIONS.put("effect.minecraft.saturation", "Насыщение");
      EFFECT_TRANSLATIONS.put("effect.minecraft.glowing", "Свечение");
      EFFECT_TRANSLATIONS.put("effect.minecraft.levitation", "Левитация");
      EFFECT_TRANSLATIONS.put("effect.minecraft.luck", "Удача");
      EFFECT_TRANSLATIONS.put("effect.minecraft.unluck", "Невезение");
      EFFECT_TRANSLATIONS.put("effect.minecraft.slow_falling", "Плавное падение");
      EFFECT_TRANSLATIONS.put("effect.minecraft.conduit_power", "Мощь источника");
      EFFECT_TRANSLATIONS.put("effect.minecraft.dolphins_grace", "Грация дельфина");
      EFFECT_TRANSLATIONS.put("effect.minecraft.bad_omen", "Дурное знамение");
      EFFECT_TRANSLATIONS.put("effect.minecraft.hero_of_the_village", "Герой деревни");
      EFFECT_ICON_COLORS = new HashMap();
      EFFECT_ICON_COLORS.put("effect.minecraft.speed", 8169727);
      EFFECT_ICON_COLORS.put("effect.minecraft.slowness", 9079434);
      EFFECT_ICON_COLORS.put("effect.minecraft.haste", 14270531);
      EFFECT_ICON_COLORS.put("effect.minecraft.mining_fatigue", 4868682);
      EFFECT_ICON_COLORS.put("effect.minecraft.strength", 16739179);
      EFFECT_ICON_COLORS.put("effect.minecraft.instant_health", 16747403);
      EFFECT_ICON_COLORS.put("effect.minecraft.instant_damage", 6041134);
      EFFECT_ICON_COLORS.put("effect.minecraft.jump_boost", 10485628);
      EFFECT_ICON_COLORS.put("effect.minecraft.nausea", 5613653);
      EFFECT_ICON_COLORS.put("effect.minecraft.regeneration", 16739229);
      EFFECT_ICON_COLORS.put("effect.minecraft.resistance", 9079434);
      EFFECT_ICON_COLORS.put("effect.minecraft.fire_resistance", 16747325);
      EFFECT_ICON_COLORS.put("effect.minecraft.water_breathing", 8169727);
      EFFECT_ICON_COLORS.put("effect.minecraft.invisibility", 15790320);
      EFFECT_ICON_COLORS.put("effect.minecraft.blindness", 1710618);
      EFFECT_ICON_COLORS.put("effect.minecraft.night_vision", 6041228);
      EFFECT_ICON_COLORS.put("effect.minecraft.hunger", 6044206);
      EFFECT_ICON_COLORS.put("effect.minecraft.weakness", 9071452);
      EFFECT_ICON_COLORS.put("effect.minecraft.poison", 4885038);
      EFFECT_ICON_COLORS.put("effect.minecraft.wither", 2763306);
      EFFECT_ICON_COLORS.put("effect.minecraft.health_boost", 16733525);
      EFFECT_ICON_COLORS.put("effect.minecraft.absorption", 16767395);
      EFFECT_ICON_COLORS.put("effect.minecraft.saturation", 14263147);
      EFFECT_ICON_COLORS.put("effect.minecraft.glowing", 16777099);
      EFFECT_ICON_COLORS.put("effect.minecraft.levitation", 10724259);
      EFFECT_ICON_COLORS.put("effect.minecraft.luck", 5626197);
      EFFECT_ICON_COLORS.put("effect.minecraft.unluck", 9061444);
      EFFECT_ICON_COLORS.put("effect.minecraft.slow_falling", 10738175);
      EFFECT_ICON_COLORS.put("effect.minecraft.conduit_power", 6064803);
      EFFECT_ICON_COLORS.put("effect.minecraft.dolphins_grace", 8182271);
      EFFECT_ICON_COLORS.put("effect.minecraft.bad_omen", 2759194);
      EFFECT_ICON_COLORS.put("effect.minecraft.hero_of_the_village", 16747325);
   }

   private static class PotionItem {
      private final Effect effect;
      private final int amplifier;

      public PotionItem(Effect effect, int amplifier) {
         this.effect = effect;
         this.amplifier = amplifier;
      }

      public String getEffectName() {
         String var10000 = I18n.format(this.effect.getName(), new Object[0]);
         return var10000 + (this.amplifier > 0 ? " " + (this.amplifier + 1) : "");
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            PotionItem that = (PotionItem)o;
            return this.amplifier == that.amplifier && this.effect.equals(that.effect);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return 31 * this.effect.hashCode() + this.amplifier;
      }

      public Effect getEffect() {
         return this.effect;
      }

      public int getAmplifier() {
         return this.amplifier;
      }
   }
}
