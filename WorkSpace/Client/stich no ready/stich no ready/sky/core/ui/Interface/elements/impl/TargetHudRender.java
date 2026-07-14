package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.modules.impl.miscellaneous.NameProtect;
import sky.core.modules.impl.miscellaneous.ScoreboardHealth;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.math.MathUtil;
import sky.core.utils.misc.ServerUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class TargetHudRender implements ElementRender {
   public static final ModeSetting hpbar = new ModeSetting("Цвет здоровья", "Клиентский", new String[]{"Клиентский", "Здоровье"});
   public static final BooleanSetting goldhealth = new BooleanSetting("Золотые сердца", true);
   public static final BooleanSetting particles2 = new BooleanSetting("Частицы", true);
   public static final BooleanSetting ontarget = new BooleanSetting("При наведении", false);
   public static final BooleanSetting armor = new BooleanSetting("Отображать броню", false);
   public static final BooleanSetting alphabg = new BooleanSetting("Прозрачный фон", false);
   private final Dragging dragging;
   private final DecimalFormat healthFormat;
   private final AnimationUtil healthAnimation;
   private final AnimationUtil secondaryHealthAnimation;
   private final AnimationUtil alphaAnimation;
   private final AnimationUtil armorScaleAnimation;
   private final AnimationUtil absorptionAnimation;
   private final AnimationUtil secondaryAbsorptionAnimation;
   private Entity lastTarget;
   private float lastHurtTime;
   private final CopyOnWriteArrayList particles;

   public void render(EventRender2D.Post event) {
      if (Interface.isOldHud()) {
         this.renderOld(event);
      } else {
         float posX = this.dragging.getX();
         float posY = this.dragging.getY();
         MatrixStack ms = event.getStack();
         SkyCore.getInstance().getModuleManager().getAttackAura();
         LivingEntity auraTarget = AttackAura.getTarget();
         Entity target = this.determineTarget(auraTarget);
         float targetAlpha = target != null ? 1.0F : 0.0F;
         Entity entityToUpdate = target != null ? target : this.lastTarget;
         if (entityToUpdate instanceof LivingEntity) {
            LivingEntity livingEntityToUpdate = (LivingEntity)entityToUpdate;
            float currentHP = this.getHealth(livingEntityToUpdate);
            float absorption = livingEntityToUpdate.getAbsorptionAmount();
            if (target != this.lastTarget && target != null) {
               this.healthAnimation.setValue(currentHP);
               this.secondaryHealthAnimation.setValue(currentHP);
               this.absorptionAnimation.setValue(absorption);
               this.secondaryAbsorptionAnimation.setValue(absorption);
            }

            this.healthAnimation.update(currentHP);
            this.secondaryHealthAnimation.update(currentHP);
            this.absorptionAnimation.update(absorption);
            this.secondaryAbsorptionAnimation.update(absorption);
         }

         this.updateAnimations(targetAlpha);
         if (this.alphaAnimation.getValue() <= 0.0F && !this.particles.isEmpty()) {
            this.particles.clear();
         }

         if (this.alphaAnimation.getValue() > 0.0F) {
            this.renderTargetHud(ms, target != null ? target : this.lastTarget, posX, posY);
         }

         this.lastTarget = target != null ? target : this.lastTarget;
         if (this.alphaAnimation.getValue() <= 0.01F) {
            this.dragging.setHeight(30.0F);
            this.dragging.setWidth(95.0F);
         }

      }
   }

   private void renderOld(EventRender2D.Post event) {
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      MatrixStack ms = event.getStack();
      SkyCore.getInstance().getModuleManager().getAttackAura();
      LivingEntity auraTarget = AttackAura.getTarget();
      Entity target = this.determineTarget(auraTarget);
      float targetAlpha = target != null ? 1.0F : 0.0F;
      Entity entityToUpdate = target != null ? target : this.lastTarget;
      if (entityToUpdate instanceof LivingEntity livingEntityToUpdate) {
         float currentHP = this.getHealth(livingEntityToUpdate);
         float absorption = livingEntityToUpdate.getAbsorptionAmount();
         if (target != this.lastTarget && target != null) {
            this.healthAnimation.setValue(currentHP);
            this.secondaryHealthAnimation.setValue(currentHP);
            this.absorptionAnimation.setValue(absorption);
            this.secondaryAbsorptionAnimation.setValue(absorption);
         }

         this.healthAnimation.update(currentHP);
         this.secondaryHealthAnimation.update(currentHP);
         this.absorptionAnimation.update(absorption);
         this.secondaryAbsorptionAnimation.update(absorption);
      }

      this.updateAnimations(targetAlpha);
      if (this.alphaAnimation.getValue() <= 0.0F && !this.particles.isEmpty()) {
         this.particles.clear();
      }

      if (this.alphaAnimation.getValue() > 0.0F) {
         this.renderTargetHudOld(ms, target != null ? target : this.lastTarget, posX, posY);
      }

      this.lastTarget = target != null ? target : this.lastTarget;
      this.dragging.setHeight(38.0F);
      this.dragging.setWidth(100.0F);
   }

   private Entity determineTarget(LivingEntity auraTarget) {
      Minecraft var4;
      if ((Boolean)ontarget.get()) {
         if (mc.currentScreen instanceof ChatScreen) {
            var4 = mc;
            return Minecraft.player;
         } else {
            RayTraceResult ray = mc.objectMouseOver;
            if (ray != null && ray.getType() == Type.ENTITY) {
               Entity ent = ((EntityRayTraceResult)ray).getEntity();
               return (Entity)(ent instanceof LivingEntity ? ent : auraTarget);
            } else {
               return auraTarget;
            }
         }
      } else {
         Object var10000;
         if (auraTarget == null && mc.currentScreen instanceof ChatScreen) {
            var4 = mc;
            var10000 = Minecraft.player;
         } else {
            var10000 = auraTarget;
         }

         return (Entity)var10000;
      }
   }

   private float getHealth(LivingEntity entity) {
      boolean useScoreboard = SkyCore.getInstance().getModuleManager().getModule(ScoreboardHealth.class).isEnabled();
      if (useScoreboard) {
         float scoreboardHealth = ServerUtil.getHealth(entity);
         float normalHealth = entity.getHealth() + entity.getAbsorptionAmount();
         return (double)scoreboardHealth == Math.floor((double)normalHealth) ? entity.getHealth() : Math.max(0.0F, scoreboardHealth);
      } else {
         return entity.getHealth();
      }
   }

   private void updateAnimations(float targetAlpha) {
      this.alphaAnimation.update(targetAlpha);
      this.armorScaleAnimation.update((Boolean)armor.get() && targetAlpha > 0.0F ? 1.0F : 0.0F);
   }

   private void renderTargetHud(MatrixStack ms, Entity renderTarget, float posX, float posY) {
      if (renderTarget instanceof LivingEntity livingTarget) {
         float alpha = this.alphaAnimation.getValue();
         float currentHurtTime = livingTarget.hurtTime > 0 ? Math.min(0.5F, (float)livingTarget.hurtTime / (float)livingTarget.maxHurtTime) : 0.0F;
         if ((Boolean)particles2.get() && currentHurtTime > this.lastHurtTime) {
            for(int i = 0; i < 16; ++i) {
               this.particles.add(new HeadParticle(new Vector3d(20.0, 20.0, 0.0)));
            }
         }

         this.lastHurtTime = currentHurtTime;
         this.getHealth(livingTarget);
         float absorption = livingTarget.getAbsorptionAmount();
         float maxHP = livingTarget.getMaxHealth();
         float padding = 5.0F;
         float gap = 5.0F;
         float avatarSize = 20.0F;
         float healthSize = 20.0F;
         float height = 30.0F;
         ITextComponent displayName = renderTarget.getDisplayName();
         String fullName = displayName.getString();
         String plainName = TextFormatting.getTextWithoutFormattingCodes(fullName);
         String cleanName;
         if (renderTarget instanceof PlayerEntity) {
            cleanName = ((PlayerEntity)renderTarget).getGameProfile().getName();
         } else {
            cleanName = plainName;
         }

         String privilege = this.resolvePrivilege(renderTarget, cleanName);
         cleanName = this.protectNameIfNeeded(cleanName);
         float nameWidth = Fonts.sfregular[12].getWidth(cleanName);
         float privilegeWidth = Fonts.sfregular[9].getWidth(privilege);
         float minContentWidth = avatarSize + gap + Math.max(nameWidth, privilegeWidth) + gap + healthSize + padding;
         float width = Math.max(95.0F, minContentWidth + padding);
         int bgColor = ColorUtil.darken(ThemeEditor.getColor(ThemeSettings.LOGO), 0.1F);
         RenderUtil.drawRoundedRectangle(posX, posY, width, height, 5.0F, ColorUtil.applyOpacity(bgColor, alpha));
         float avatarX = posX + padding;
         float avatarY = posY + padding;
         if (renderTarget instanceof PlayerEntity) {
            RenderUtil.drawRoundedHead(mc.getRenderManager().getRenderer(renderTarget).getEntityTexture((LivingEntity)renderTarget), (LivingEntity)renderTarget, avatarX, avatarY, avatarSize, avatarSize, 2.5F, alpha);
         } else {
            RenderUtil.drawRoundedRectangle(avatarX, avatarY, avatarSize, avatarSize, 2.5F, ColorUtil.applyOpacity(ColorUtil.darken(ThemeEditor.getColor(ThemeSettings.LOGO), 0.1F), alpha));
         }

         float infoX = avatarX + avatarSize + gap;
         float infoY = avatarY + 1.0F;
         Fonts.sfregular[9].drawString(ms, privilege, (double)infoX, (double)(infoY + 1.5F), ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.LOGO), alpha));
         Fonts.sfregular[12].drawString(ms, cleanName, (double)(infoX - 0.2F), (double)(infoY + 6.5F), ColorUtil.applyOpacity(-1, alpha));
         if ((Boolean)armor.get() && livingTarget instanceof PlayerEntity playerTarget) {
            this.renderArmorIcons(ms, playerTarget, infoX, infoY + 14.0F, alpha);
         }

         float healthX = posX + width - healthSize - padding + 2.0F;
         float healthY = posY + (height - healthSize) / 2.0F;
         float animatedHP = this.healthAnimation.getValue();
         float secondaryHP = this.secondaryHealthAnimation.getValue();
         float animatedAbsorption = this.absorptionAnimation.getValue();
         float secondaryAbsorption = this.secondaryAbsorptionAnimation.getValue();
         this.renderCircularHealth(ms, healthX, healthY, healthSize, animatedHP, secondaryHP, animatedAbsorption, secondaryAbsorption, maxHP, alpha);
         this.dragging.setWidth(width);
         this.dragging.setHeight(height);
      }
   }

   private void renderTargetHudOld(MatrixStack ms, Entity renderTarget, float posX, float posY) {
      if (renderTarget instanceof LivingEntity livingTarget) {
         float alpha = this.alphaAnimation.getValue();
         float currentHurtTime = livingTarget.hurtTime > 0 ? Math.min(0.5F, (float)livingTarget.hurtTime / (float)livingTarget.maxHurtTime) : 0.0F;
         if ((Boolean)particles2.get() && currentHurtTime > this.lastHurtTime) {
            for(int i = 0; i < 16; ++i) {
               this.particles.add(new HeadParticle(new Vector3d(20.0, 20.0, 0.0)));
            }
         }

         this.lastHurtTime = currentHurtTime;
         float currentHP = this.getHealth(livingTarget);
         float absorption = livingTarget.getAbsorptionAmount();
         float maxHP = livingTarget.getMaxHealth();
         float hpPercentage = Math.min(this.healthAnimation.getValue() / maxHP, 1.0F);
         float secondaryHpPercentage = Math.min(this.secondaryHealthAnimation.getValue() / maxHP, 1.0F);
         float hpBarWidth = 58.0F * hpPercentage;
         float secondaryHpBarWidth = 58.0F * secondaryHpPercentage;
         int textColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.WINDOW_TEXT), ThemeEditor.getAlpha(ThemeSettings.WINDOW_TEXT) / 255.0F * alpha);
         RenderUtil.drawBlurredRoundedRectangle(posX, posY, 100.0F, 38.0F, 6.5F, (Boolean)alphabg.get() ? ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.WINDOW_BG), 0) : ThemeEditor.getColor(ThemeSettings.WINDOW_BG), alpha);
         this.renderParticlesOld(posX, posY, alpha);
         this.renderEntityHeadOld(ms, renderTarget, posX, posY, textColor, alpha);
         if ((Boolean)armor.get()) {
            this.renderArmorOld(livingTarget, posX, posY);
         }

         this.renderHealthBarOld(posX, posY, hpBarWidth, secondaryHpBarWidth, currentHP, absorption, maxHP, alpha);
         this.renderTextOld(ms, renderTarget, posX, posY, currentHP, absorption, textColor);
      }

   }

   private void renderParticlesOld(float posX, float posY, float alpha) {
      this.particles.removeIf((particlex) -> {
         return System.currentTimeMillis() - particlex.time > particlex.lifetime;
      });
      Iterator var4 = this.particles.iterator();

      while(var4.hasNext()) {
         HeadParticle particle = (HeadParticle)var4.next();
         particle.update(posX, posY);
         float size = 1.0F - (float)(System.currentTimeMillis() - particle.time) / (float)particle.lifetime;
         float radius = 2.3F;
         RenderUtil.drawRoundedRectangle((float)particle.pos.x - 3.0F, (float)particle.pos.y - 3.0F, radius * 2.0F, radius * 2.0F, radius - 1.0F, ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), (int)(255.0F * particle.alpha * size * alpha)));
      }

   }

   private void renderEntityHeadOld(MatrixStack ms, Entity renderTarget, float posX, float posY, int textColor, float alpha) {
      if (renderTarget instanceof PlayerEntity) {
         RenderUtil.drawRoundedHead(mc.getRenderManager().getRenderer(renderTarget).getEntityTexture((LivingEntity)renderTarget), (LivingEntity)renderTarget, posX + 3.0F, posY + 3.0F, 32.0F, 32.0F, 4.0F, alpha);
      } else {
         Fonts.icons[40].drawString(ms, "N", (double)(posX + 10.0F), (double)(posY + 12.0F), textColor);
      }

   }

   private void renderArmorOld(LivingEntity livingTarget, float posX, float posY) {
      List armorStacks = new ArrayList();
      Iterator var5 = livingTarget.getArmorInventoryList().iterator();

      ItemStack offhand;
      while(var5.hasNext()) {
         offhand = (ItemStack)var5.next();
         if (!offhand.isEmpty()) {
            armorStacks.add(offhand);
         }
      }

      int armorCount = armorStacks.size();
      offhand = livingTarget.getHeldItemOffhand();
      ItemStack mainhand = livingTarget.getHeldItemMainhand();
      int totalItems = armorCount + (offhand.isEmpty() ? 0 : 1) + (mainhand.isEmpty() ? 0 : 1);
      if (totalItems > 0) {
         float panelWidth = this.dragging.getWidth();
         float usedWidth = 8.0F * (float)totalItems + (float)(totalItems - 1);
         float handX = posX + panelWidth - usedWidth - 3.5F;
         if (!offhand.isEmpty()) {
            RenderUtil.scaleStart(handX + 4.0F, posY - 6.0F, this.armorScaleAnimation.getValue());
            RenderUtil.drawStack(offhand, handX, posY - 10.0F, 0.5F);
            RenderUtil.scaleEnd();
            handX += 9.0F;
         }

         if (!mainhand.isEmpty()) {
            RenderUtil.scaleStart(handX + 4.0F, posY - 6.0F, this.armorScaleAnimation.getValue());
            RenderUtil.drawStack(mainhand, handX, posY - 10.0F, 0.5F);
            RenderUtil.scaleEnd();
            handX += 9.0F;
         }

         for(int i = 0; i < armorCount; ++i) {
            float itemX = handX + (float)i * 9.0F;
            RenderUtil.scaleStart(itemX + 4.0F, posY - 6.0F, this.armorScaleAnimation.getValue());
            RenderUtil.drawStack((ItemStack)armorStacks.get(i), itemX, posY - 10.0F, 0.5F);
            RenderUtil.scaleEnd();
         }
      }

   }

   private void renderHealthBarOld(float posX, float posY, float hpBarWidth, float secondaryHpBarWidth, float currentHP, float absorption, float maxHP, float alpha) {
      if (!(hpBarWidth <= 0.0F)) {
         float barX = posX + 37.0F;
         float barY = posY + 26.0F;
         float barWidth = 59.0F;
         float barHeight = 8.0F;
         float radius = 2.3F;
         float lagAbsorption;
         if (hpbar.is("Клиентский")) {
            int activeColor = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
            int inactiveColor = ColorUtil.darken(activeColor, 0.5F);
            lagAbsorption = ThemeEditor.getAlpha(ThemeSettings.MODULE_VISUAL) / 255.0F * alpha;
            RenderUtil.drawRoundedRectangleGradient(barX, barY, barWidth, barHeight, radius, inactiveColor, inactiveColor, activeColor, activeColor, lagAbsorption * 0.3F);
            RenderUtil.drawRoundedRectangleGradient(barX, barY, secondaryHpBarWidth, barHeight, radius, inactiveColor, inactiveColor, activeColor, activeColor, lagAbsorption * 0.75F);
            RenderUtil.drawRoundedRectangleGradient(barX, barY, hpBarWidth + 1.0F, barHeight, radius, inactiveColor, inactiveColor, activeColor, activeColor, lagAbsorption);
         } else {
            int[] colors = this.getHealthBarColorsOld(currentHP, maxHP);
            RenderUtil.drawRoundedRectangleGradient(barX, barY, barWidth, barHeight, radius, colors[0], colors[0], colors[1], colors[1], 0.3137255F * alpha);
            RenderUtil.drawRoundedRectangleGradient(barX, barY, secondaryHpBarWidth, barHeight, radius, colors[2], colors[2], colors[3], colors[3], 0.54901963F * alpha);
            RenderUtil.drawRoundedRectangleGradient(barX, barY, hpBarWidth + 1.0F, barHeight, radius, colors[2], colors[2], colors[4], colors[4], alpha);
         }

         if ((Boolean)goldhealth.get() && absorption > 0.0F) {
            float animatedAbsorption = this.absorptionAnimation.getValue();
            float absorptionBarWidth = 58.0F * Math.min(animatedAbsorption / maxHP, 1.0F);
            lagAbsorption = this.secondaryAbsorptionAnimation.getValue();
            float secondaryAbsorptionBarWidth = 58.0F * Math.min(lagAbsorption / maxHP, 1.0F);
            int goldTop = ColorUtil.getColor(255, 210, 0);
            int goldBottom = ColorUtil.darken(goldTop, 0.5F);
            RenderUtil.drawRoundedRectangleGradient(barX, barY, secondaryAbsorptionBarWidth, barHeight, radius, ColorUtil.darken(goldBottom, 0.6F), ColorUtil.darken(goldBottom, 0.6F), ColorUtil.darken(goldTop, 0.8F), ColorUtil.darken(goldTop, 0.8F), alpha * 0.6F);
            RenderUtil.drawRoundedRectangleGradient(barX, barY, absorptionBarWidth, barHeight, radius, goldBottom, goldBottom, goldTop, goldTop, alpha);
         }
      }

   }

   private int[] getHealthBarColorsOld(float currentHP, float maxHP) {
      if ((double)currentHP >= (double)maxHP * 0.7) {
         return new int[]{ColorUtil.getColor(0, 40, 8), ColorUtil.getColor(0, 80, 15), ColorUtil.getColor(0, 60, 12), ColorUtil.getColor(0, 160, 40), ColorUtil.getColor(0, 190, 45)};
      } else {
         return (double)currentHP >= (double)maxHP * 0.35 ? new int[]{ColorUtil.getColor(50, 55, 25), ColorUtil.getColor(85, 70, 50), ColorUtil.getColor(55, 50, 22), ColorUtil.getColor(140, 130, 60), ColorUtil.getColor(160, 150, 70)} : new int[]{ColorUtil.getColor(50, 35, 25), ColorUtil.getColor(70, 45, 40), ColorUtil.getColor(80, 42, 32), ColorUtil.getColor(160, 90, 70), ColorUtil.getColor(180, 100, 75)};
      }
   }

   private void renderTextOld(MatrixStack ms, Entity renderTarget, float posX, float posY, float currentHP, float absorption, int textColor) {
      String plainName = TextFormatting.getTextWithoutFormattingCodes(renderTarget.getName().getString());
      Fonts.sf_medium[18].drawSubString(ms, plainName, posX + 37.5F, posY + 7.3F, textColor, 47.0F);
      Fonts.sf_medium[14].drawString(ms, "HP: " + this.healthFormat.format((double)currentHP), (double)(posX + 37.8F), (double)(posY + 18.9F), textColor);
      if ((Boolean)goldhealth.get() && absorption > 0.0F) {
         String var10000 = this.healthFormat.format((double)currentHP);
         String hpText = "HP: " + var10000;
         var10000 = this.healthFormat.format((double)absorption);
         String goldText = "(" + var10000 + ")";
         float goldX = posX + 38.0F + Fonts.sf_medium[14].getWidth(hpText) + 1.5F;
         Fonts.sf_medium[14].drawString(ms, goldText, (double)goldX, (double)(posY + 18.5F), textColor);
      }

   }

   private String resolvePrivilege(Entity renderTarget, String playerName) {
      if (renderTarget instanceof PlayerEntity && mc.getConnection() != null) {
         NetworkPlayerInfo info = mc.getConnection().getPlayerInfo(renderTarget.getUniqueID());
         if (info == null && playerName != null) {
            info = mc.getConnection().getPlayerInfo(playerName);
         }

         if (info != null) {
            ScorePlayerTeam team = info.getPlayerTeam();
            String privilege = this.getPrivilege(team != null ? team.getPrefix().getString() : null, playerName);
            if (!this.isDefaultPrivilege(privilege)) {
               return privilege;
            }

            privilege = this.getPrivilege(team != null ? team.func_230427_d_(new StringTextComponent(playerName)).getString() : null, playerName);
            if (!this.isDefaultPrivilege(privilege)) {
               return privilege;
            }

            privilege = this.getPrivilege(info.getDisplayName() != null ? info.getDisplayName().getString() : null, playerName);
            if (!this.isDefaultPrivilege(privilege)) {
               return privilege;
            }
         }
      }

      if (renderTarget instanceof PlayerEntity && mc.world != null) {
         ScorePlayerTeam team = mc.world.getScoreboard().getPlayersTeam(playerName);
         String privilege = this.getPrivilege(team != null ? team.getPrefix().getString() : null, playerName);
         if (!this.isDefaultPrivilege(privilege)) {
            return privilege;
         }

         privilege = this.getPrivilege(team != null ? team.func_230427_d_(new StringTextComponent(playerName)).getString() : null, playerName);
         if (!this.isDefaultPrivilege(privilege)) {
            return privilege;
         }
      }

      return this.getPrivilege(renderTarget.getDisplayName().getString(), playerName);
   }

   private String getPrivilege(String rawText, String playerName) {
      String plainName = this.stripMinecraftFormatting(rawText);
      if (plainName != null && !plainName.isEmpty()) {
         String normalizedPlayerName = this.stripMinecraftFormatting(playerName);
         if (normalizedPlayerName != null && !normalizedPlayerName.isEmpty()) {
            plainName = this.removePlayerName(plainName, normalizedPlayerName);
         }

         int startBracket = plainName.indexOf(91);
         int endBracket = plainName.indexOf(93);
         if (startBracket != -1 && endBracket != -1 && endBracket > startBracket) {
            return this.normalizePrivilege(plainName.substring(startBracket + 1, endBracket));
         } else {
            int spaceIndex = plainName.indexOf(32);
            if (spaceIndex > 0 && spaceIndex < plainName.length() - 1) {
               String possiblePrefix = plainName.substring(0, spaceIndex).trim();
               String afterSpace = plainName.substring(spaceIndex + 1).trim();
               if (!possiblePrefix.isEmpty() && !afterSpace.isEmpty()) {
                  return this.normalizePrivilege(possiblePrefix);
               }
            }

            return this.normalizePrivilege(plainName);
         }
      } else {
         return "Player";
      }
   }

   private String stripMinecraftFormatting(String text) {
      if (text == null) {
         return "";
      } else {
         StringBuilder clean = new StringBuilder(text.length());

         for(int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (c == 167) {
               ++i;
            } else {
               clean.append(c);
            }
         }

         return clean.toString().replaceAll("\\s+", " ").trim();
      }
   }

   private String removePlayerName(String text, String playerName) {
      if (text != null && !text.isEmpty() && playerName != null && !playerName.isEmpty()) {
         String lowerText = text.toLowerCase(Locale.ROOT);
         String lowerName = playerName.toLowerCase(Locale.ROOT);
         int nameIndex = lowerText.indexOf(lowerName);
         if (nameIndex == -1) {
            return text;
         } else {
            String var10000 = text.substring(0, nameIndex);
            return (var10000 + text.substring(nameIndex + playerName.length())).replaceAll("\\s+", " ").trim();
         }
      } else {
         return text;
      }
   }

   private String normalizePrivilege(String privilege) {
      if (privilege == null) {
         return "Player";
      } else {
         String cleaned = privilege.replace("[", "").replace("]", "").replace("(", "").replace(")", "").replace("|", "").replace("»", "").replace("«", "").replace(">", "").replace("<", "").trim();
         cleaned = cleaned.replaceAll("\\s+", " ");
         if (cleaned.isEmpty()) {
            return "Player";
         } else {
            return cleaned.equalsIgnoreCase("player") ? "Player" : this.toTitleCase(cleaned);
         }
      }
   }

   private boolean isDefaultPrivilege(String privilege) {
      return privilege == null || privilege.isEmpty() || privilege.equalsIgnoreCase("Player");
   }

   private String toTitleCase(String text) {
      String lower = text.toLowerCase(Locale.ROOT);
      StringBuilder result = new StringBuilder(lower.length());
      boolean capitalizeNext = true;

      for(int i = 0; i < lower.length(); ++i) {
         char c = lower.charAt(i);
         if (Character.isLetterOrDigit(c)) {
            result.append(capitalizeNext ? Character.toUpperCase(c) : c);
            capitalizeNext = false;
         } else {
            result.append(c);
            capitalizeNext = true;
         }
      }

      return result.toString();
   }

   private void renderArmorIcons(MatrixStack ms, PlayerEntity player, float x, float y, float alpha) {
      float slotSize = 3.5F;
      float armorGap = 4.0F;
      ItemStack[] armorSlots = new ItemStack[]{(ItemStack)player.inventory.armorInventory.get(3), (ItemStack)player.inventory.armorInventory.get(2), (ItemStack)player.inventory.armorInventory.get(1), (ItemStack)player.inventory.armorInventory.get(0)};
      ItemStack offhand = player.getHeldItemOffhand();
      float currentX = x + 1.3F;
      float slotY = y + 1.0F;
      float crossWidth;
      float crossX;
      if (!offhand.isEmpty()) {
         RenderUtil.scaleStart(currentX + slotSize / 2.0F, slotY + slotSize / 2.0F, this.armorScaleAnimation.getValue());
         RenderUtil.drawStack(offhand, currentX - 2.5F, slotY - 3.0F, slotSize / 16.0F * 2.0F);
         RenderUtil.scaleEnd();
      } else {
         float crossWidth = Fonts.skycore[10].getWidth("k");
         crossWidth = currentX + (slotSize - crossWidth) / 2.0F + 0.5F;
         crossX = slotY + slotSize / 2.0F - 1.0F;
         Fonts.skycore[10].drawString(ms, "k", (double)(crossWidth - 1.2F), (double)crossX, ColorUtil.applyOpacity(-1, 0.48F * alpha));
      }

      currentX += slotSize + armorGap;

      for(int i = 0; i < 4; ++i) {
         if (armorSlots[i].isEmpty()) {
            crossWidth = Fonts.skycore[10].getWidth("k");
            crossX = currentX + (slotSize - crossWidth) / 2.0F + 0.5F;
            float crossY = slotY + slotSize / 2.0F - 1.0F;
            Fonts.skycore[10].drawString(ms, "k", (double)(crossX - 1.2F), (double)crossY, ColorUtil.applyOpacity(-1, 0.48F * alpha));
         } else {
            RenderUtil.scaleStart(currentX + slotSize / 2.0F, slotY + slotSize / 2.0F, this.armorScaleAnimation.getValue());
            RenderUtil.drawStack(armorSlots[i], currentX - 2.5F, slotY - 3.0F, slotSize / 16.0F * 2.0F);
            RenderUtil.scaleEnd();
         }

         currentX += slotSize + armorGap;
      }

   }

   private int getArmorColor(ItemStack stack) {
      if (stack.getItem() != Items.DIAMOND_HELMET && stack.getItem() != Items.DIAMOND_CHESTPLATE && stack.getItem() != Items.DIAMOND_LEGGINGS && stack.getItem() != Items.DIAMOND_BOOTS) {
         if (stack.getItem() != Items.IRON_HELMET && stack.getItem() != Items.IRON_CHESTPLATE && stack.getItem() != Items.IRON_LEGGINGS && stack.getItem() != Items.IRON_BOOTS) {
            if (stack.getItem() != Items.GOLDEN_HELMET && stack.getItem() != Items.GOLDEN_CHESTPLATE && stack.getItem() != Items.GOLDEN_LEGGINGS && stack.getItem() != Items.GOLDEN_BOOTS) {
               if (stack.getItem() != Items.LEATHER_HELMET && stack.getItem() != Items.LEATHER_CHESTPLATE && stack.getItem() != Items.LEATHER_LEGGINGS && stack.getItem() != Items.LEATHER_BOOTS) {
                  if (stack.getItem() != Items.NETHERITE_HELMET && stack.getItem() != Items.NETHERITE_CHESTPLATE && stack.getItem() != Items.NETHERITE_LEGGINGS && stack.getItem() != Items.NETHERITE_BOOTS) {
                     return stack.getItem() != Items.CHAINMAIL_HELMET && stack.getItem() != Items.CHAINMAIL_CHESTPLATE && stack.getItem() != Items.CHAINMAIL_LEGGINGS && stack.getItem() != Items.CHAINMAIL_BOOTS ? -11184811 : -9737365;
                  } else {
                     return -12763843;
                  }
               } else {
                  return -8697299;
               }
            } else {
               return -2842601;
            }
         } else {
            return -7697782;
         }
      } else {
         return -13847136;
      }
   }

   private String getArmorIcon(int slotIndex) {
      switch (slotIndex) {
         case 0:
            return "n";
         case 1:
            return "o";
         case 2:
            return "p";
         case 3:
            return "q";
         default:
            return "k";
      }
   }

   private int getItemColor(ItemStack stack) {
      String name = stack.getItem().toString().toLowerCase();
      if (name.contains("diamond")) {
         return -13847136;
      } else if (name.contains("iron")) {
         return -7697782;
      } else if (!name.contains("golden") && !name.contains("gold")) {
         if (name.contains("netherite")) {
            return -12763843;
         } else if (!name.contains("wood") && !name.contains("wooden")) {
            return name.contains("stone") ? -9737365 : -10850867;
         } else {
            return -8697299;
         }
      } else {
         return -2842601;
      }
   }

   private String getItemShortName(ItemStack stack) {
      String name = stack.getItem().toString().toLowerCase();
      if (name.contains("sword")) {
         return "l";
      } else if (name.contains("axe")) {
         return "l";
      } else if (name.contains("bow")) {
         return "l";
      } else if (name.contains("crossbow")) {
         return "l";
      } else if (name.contains("trident")) {
         return "l";
      } else if (name.contains("shield")) {
         return "l";
      } else {
         return name.contains("totem") ? "l" : "l";
      }
   }

   private void renderCircularHealth(MatrixStack ms, float x, float y, float size, float health, float secondaryHealth, float absorption, float secondaryAbsorption, float maxHealth, float alpha) {
      int playerColor = ThemeEditor.getColor(ThemeSettings.LOGO);
      int healthColor = ColorUtil.applyOpacity(playerColor, alpha);
      int secondaryColor = ColorUtil.applyOpacity(playerColor, 0.4F * alpha);
      int borderColor = ColorUtil.applyOpacity(-1, 0.08F * alpha);
      int absorptionColor = ColorUtil.getColor(255, 210, 0);
      int secondaryAbsorptionColor = ColorUtil.applyOpacity(ColorUtil.darken(absorptionColor, 0.4F), 0.65F * alpha);
      float centerX = x + size / 2.0F;
      float centerY = y + size / 2.0F;
      float radius = size / 2.0F - 2.0F;
      RenderUtil.drawCircle(centerX, centerY, 0.0F, 360.0F, radius, 1.0F, false, borderColor);
      float healthPercent;
      float secondaryAbsorptionPercent;
      float absorptionPercent;
      if (maxHealth > 0.0F) {
         float secondaryPercent = Math.min(secondaryHealth / maxHealth, 1.0F);
         if (secondaryPercent > 0.01F) {
            RenderUtil.drawCircle(centerX, centerY, 0.0F, 360.0F * secondaryPercent, radius, 1.0F, false, secondaryColor);
         }

         healthPercent = Math.min(health / maxHealth, 1.0F);
         if (healthPercent > 0.01F) {
            RenderUtil.drawCircle(centerX, centerY, 0.0F, 360.0F * healthPercent, radius, 1.0F, false, ColorUtil.applyOpacity(healthColor, alpha));
         }

         if ((Boolean)goldhealth.get() && absorption > 0.01F) {
            secondaryAbsorptionPercent = Math.min(secondaryAbsorption / maxHealth, 1.0F);
            if (secondaryAbsorptionPercent > 0.01F) {
               RenderUtil.drawCircle(centerX, centerY, 0.0F, 360.0F * secondaryAbsorptionPercent, radius, 1.0F, false, secondaryAbsorptionColor);
            }

            absorptionPercent = Math.min(absorption / maxHealth, 1.0F);
            if (absorptionPercent > 0.01F) {
               RenderUtil.drawCircle(centerX, centerY, 0.0F, 360.0F * absorptionPercent, radius, 1.0F, false, ColorUtil.applyOpacity(absorptionColor, alpha));
            }
         }
      }

      String healthText = String.valueOf((int)health);
      healthPercent = Fonts.sf_medium[12].getWidth("20");
      secondaryAbsorptionPercent = Fonts.sf_medium[12].getWidth(healthText);
      absorptionPercent = Fonts.sf_medium[12].getHeight();
      float blockX = centerX - healthPercent / 2.0F;
      float textX = blockX + (healthPercent - secondaryAbsorptionPercent) / 2.0F;
      float textY = centerY - absorptionPercent / 2.0F + 1.0F;
      Fonts.sf_medium[12].drawString(ms, healthText, (double)(textX + 0.2F), (double)(textY + 0.2F), ColorUtil.applyOpacity(-1, alpha));
   }

   private String protectNameIfNeeded(String name) {
      if (name != null && !name.isEmpty()) {
         if (SkyCore.getInstance() == null) {
            return name;
         } else {
            return !SkyCore.getInstance().getModuleManager().getModule(NameProtect.class).isEnabled() ? name : NameProtect.replaceName(name);
         }
      } else {
         return name;
      }
   }

   public TargetHudRender(Dragging dragging) {
      this.healthFormat = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.US));
      this.healthAnimation = new AnimationUtil(0.0F, 3.0F, Easings.CUBIC_OUT);
      this.secondaryHealthAnimation = new AnimationUtil(0.0F, 3.0F, Easings.LINEAR);
      this.alphaAnimation = new AnimationUtil(0.0F, 10.0F, Easings.CUBIC_OUT);
      this.armorScaleAnimation = new AnimationUtil(1.0F, 15.0F, Easings.CUBIC_OUT);
      this.absorptionAnimation = new AnimationUtil(0.0F, 3.0F, Easings.CUBIC_OUT);
      this.secondaryAbsorptionAnimation = new AnimationUtil(0.0F, 3.0F, Easings.LINEAR);
      this.lastTarget = null;
      this.lastHurtTime = 0.0F;
      this.particles = new CopyOnWriteArrayList();
      this.dragging = dragging;
   }

   public CopyOnWriteArrayList getParticles() {
      return this.particles;
   }

   public static class HeadParticle {
      private Vector3d offset;
      private Vector3d pos;
      private final Vector3d endOffset;
      private final long time;
      private float alpha;
      private final long lifetime;

      public HeadParticle(Vector3d offset) {
         this.offset = offset;
         this.pos = offset;
         this.endOffset = offset.add((double)(-ThreadLocalRandom.current().nextFloat(-75.0F, 75.0F)), (double)(-ThreadLocalRandom.current().nextFloat(-75.0F, 75.0F)), (double)(-ThreadLocalRandom.current().nextFloat(-75.0F, 75.0F)));
         this.time = System.currentTimeMillis();
         this.lifetime = 1250L + ThreadLocalRandom.current().nextLong(750L);
      }

      public void update(float hudX, float hudY) {
         this.alpha = MathUtil.lerp(this.alpha, 1.0F, 10.0F);
         this.offset = MathUtil.fast(this.offset, this.endOffset, 0.75F);
         this.pos = new Vector3d((double)hudX + this.offset.x, (double)hudY + this.offset.y, this.offset.z);
      }
   }
}
