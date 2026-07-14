package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import sky.core.events.EventRender2D;
import sky.core.handlers.impl.StaffHandler;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.managers.impl.staffmanager.Staff;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;
import sky.core.utils.render.font.StyledFont;
import sky.core.utils.render.shader.ShaderUtil;

public class StaffListRender implements ElementRender {
   public static BooleanSetting skins = new BooleanSetting("Отображать скин", true);
   public static BooleanSetting alphabg = new BooleanSetting("Прозрачный фон", false);
   private static final ResourceLocation STEVE_SKIN = new ResourceLocation("textures/entity/steve.png");
   private static final ResourceLocation ALEX_SKIN = new ResourceLocation("textures/entity/alex.png");
   private final Dragging dragging;
   private final AnimationUtil alphaAnimation = new AnimationUtil(0.0F, 10.0F);
   private final Map itemAnimations = new HashMap();
   private final Map lastSeenStaff = new HashMap();
   private final Map ordinaryItemAnimations = new HashMap();
   private float ordinaryAnimatedWidth = 40.0F;
   private static final float PADDING_LEFT = 4.0F;
   private static final float PADDING_RIGHT = 4.0F;
   private static final float MIN_GAP_BEFORE_STATUS = 6.0F;
   private static final float PREFIX_NAME_GAP = 1.5F;

   public void render(EventRender2D.Post event) {
      if (Interface.isOldHud()) {
         this.renderOrdinary(event);
      } else {
         this.renderNursultan(event);
      }

   }

   private void renderOrdinary(EventRender2D.Post event) {
      MatrixStack ms = event.getStack();
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      List activeItems = StaffHandler.getInstance() != null ? StaffHandler.getInstance().getStaff() : new ArrayList();
      boolean hasActive = false;

      Iterator var7;
      for(var7 = ((List)activeItems).iterator(); var7.hasNext(); hasActive = true) {
         Staff staff = (Staff)var7.next();
         this.ordinaryItemAnimations.computeIfAbsent(staff.getName(), (k) -> {
            return new AnimationUtil(0.0F, 10.0F);
         });
      }

      var7 = this.ordinaryItemAnimations.entrySet().iterator();

      while(var7.hasNext()) {
         Map.Entry entry = (Map.Entry)var7.next();
         boolean exists = ((List)activeItems).stream().anyMatch((s) -> {
            return s.getName().equals(entry.getKey());
         });
         ((AnimationUtil)entry.getValue()).update(exists ? 1.0F : 0.0F);
      }

      boolean isChatOpen = mc.currentScreen instanceof ChatScreen;
      boolean show = isChatOpen || hasActive;
      this.alphaAnimation.update(show ? 1.0F : 0.0F);
      float globalAlpha = this.alphaAnimation.getValue();
      if (globalAlpha <= 0.01F) {
         this.ordinaryAnimatedWidth = 65.0F;
         this.dragging.setWidth(this.ordinaryAnimatedWidth);
         this.dragging.setHeight(18.5F);
      } else {
         float headerHeight = 12.3F;
         float itemHeight = 9.3F;
         float maxLeftPartWidth = 0.0F;
         float maxStatusWidth = 0.0F;
         List currentIds = new ArrayList();
         Iterator var15 = ((List)activeItems).iterator();

         while(true) {
            Staff staff;
            AnimationUtil anim;
            do {
               do {
                  if (!var15.hasNext()) {
                     this.ordinaryItemAnimations.entrySet().removeIf((e) -> {
                        return !currentIds.contains(e.getKey()) && ((AnimationUtil)e.getValue()).isDone() && ((AnimationUtil)e.getValue()).getValue() <= 0.01F;
                     });
                     float contentWidth = 4.0F + maxLeftPartWidth + 6.0F + maxStatusWidth + 4.0F;
                     float minWidth = 65.0F;
                     float targetWidth = show ? Math.max(minWidth, contentWidth) : minWidth;
                     this.ordinaryAnimatedWidth = targetWidth;
                     float contentHeight = 0.0F;
                     Iterator var45 = ((List)activeItems).iterator();

                     while(var45.hasNext()) {
                        Staff staff = (Staff)var45.next();
                        AnimationUtil anim = (AnimationUtil)this.ordinaryItemAnimations.get(staff.getName());
                        if (anim != null) {
                           contentHeight += itemHeight * anim.getValue();
                        }
                     }

                     float windowHeight = headerHeight + (contentHeight <= 0.01F ? 6.0F : contentHeight + 6.7F);
                     int headerColor = ColorUtil.applyOpacity(-1, globalAlpha);
                     int iconColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.LOGO), ThemeEditor.getAlpha(ThemeSettings.LOGO) / 255.0F * globalAlpha);
                     int windowBgColor = ThemeEditor.getColor(ThemeSettings.WINDOW_BG);
                     float topOffset = 3.8F;
                     RenderUtil.drawBlurredRoundedRectangle(posX, posY + topOffset, this.ordinaryAnimatedWidth, windowHeight - topOffset, 4.9F, (Boolean)alphabg.get() ? ColorUtil.applyOpacity(windowBgColor, 0) : windowBgColor, globalAlpha);
                     float renderY;
                     if (this.ordinaryAnimatedWidth > 35.0F) {
                        renderY = Fonts.lox[12].getWidth("f");
                        float iconX = posX + this.ordinaryAnimatedWidth - 4.0F - renderY;
                        Fonts.lox[12].drawString(ms, "f", (double)(iconX - 0.5F), (double)(posY + 9.74F), iconColor);
                        Fonts.sf_medium[15].drawString(ms, "Staffs", (double)(posX + 4.0F), (double)(posY + 8.55F), headerColor);
                     }

                     renderY = posY + headerHeight + 3.0F;
                     Iterator var52 = ((List)activeItems).iterator();

                     while(var52.hasNext()) {
                        Staff staff = (Staff)var52.next();
                        AnimationUtil itemAnim = (AnimationUtil)this.ordinaryItemAnimations.get(staff.getName());
                        if (itemAnim != null) {
                           float animVal = itemAnim.getValue();
                           if (!(animVal <= 0.01F)) {
                              float slideOffset = (1.0F - animVal) * 10.0F;
                              int itemTextColor = ColorUtil.applyOpacity(-1, animVal * globalAlpha);
                              String prefixPlain = TextFormatting.getTextWithoutFormattingCodes(staff.getPrefix().getString());
                              if (prefixPlain == null) {
                                 prefixPlain = "";
                              }

                              float currentX = posX + 4.0F - slideOffset;
                              Fonts.sf_medium[14].drawText(ms, staff.getPrefix(), (double)currentX, (double)(renderY + 4.2F), ColorUtil.applyOpacity(-1, globalAlpha * animVal));
                              currentX += Fonts.sf_medium[14].getWidth(prefixPlain);
                              StyledFont var10000 = Fonts.sf_medium[14];
                              String var10002 = staff.getName();
                              var10000.drawString(ms, " " + var10002, (double)currentX, (double)(renderY + 4.2F), itemTextColor);
                              String statusDisplay = staff.isVanished() ? "[V]" : "[Online]";
                              int statusColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.LOGO), animVal * globalAlpha);
                              float statusWidth = Fonts.sf_medium[14].getWidth(statusDisplay);
                              float statusX = posX + this.ordinaryAnimatedWidth - 4.0F - statusWidth + slideOffset;
                              Fonts.sf_medium[14].drawString(ms, statusDisplay, (double)statusX, (double)(renderY + 4.2F), statusColor);
                              renderY += itemHeight * animVal;
                           }
                        }
                     }

                     this.dragging.setHeight(windowHeight);
                     this.dragging.setWidth(this.ordinaryAnimatedWidth);
                     return;
                  }

                  staff = (Staff)var15.next();
                  currentIds.add(staff.getName());
                  anim = (AnimationUtil)this.ordinaryItemAnimations.get(staff.getName());
               } while(anim == null);
            } while(!(anim.getValue() > 0.01F) && !((List)activeItems).contains(staff));

            String prefixPlain = TextFormatting.getTextWithoutFormattingCodes(staff.getPrefix().getString());
            if (prefixPlain == null) {
               prefixPlain = "";
            }

            String statusText = staff.isVanished() ? "[V]" : "[Online]";
            float prefixWidth = Fonts.sf_medium[14].getWidth(prefixPlain);
            float nameWidth = Fonts.sf_medium[14].getWidth(" " + staff.getName());
            float statusWidth = Fonts.sf_medium[14].getWidth(statusText);
            maxLeftPartWidth = Math.max(maxLeftPartWidth, prefixWidth + nameWidth);
            maxStatusWidth = Math.max(maxStatusWidth, statusWidth);
         }
      }
   }

   private void renderNursultan(EventRender2D.Post event) {
      MatrixStack ms = event.getStack();
      float posX = this.dragging.getX();
      float posY = this.dragging.getY();
      List entries = StaffHandler.getInstance() != null ? StaffHandler.getInstance().getStaff() : new ArrayList();
      boolean hasActive = !((List)entries).isEmpty();
      boolean show = mc.currentScreen instanceof ChatScreen || hasActive;
      this.alphaAnimation.update(show ? 1.0F : 0.0F);
      float globalAlpha = this.alphaAnimation.getValue();
      if (globalAlpha <= 0.01F) {
         this.dragging.setWidth(90.0F);
         this.dragging.setHeight(25.0F);
      } else {
         float totalWidth = 90.0F;
         float paddingX = 6.0F;
         float paddingY = 7.5F;
         float gap = 7.5F;
         float itemGap = 6.0F;
         float borderRadius = 5.5F;
         float iconSize = 6.0F;
         float headerGap = 3.0F;
         float itemHeight = 7.0F;
         float headSize = 6.0F;
         float headGap = 4.0F;
         float maxItemWidth = 0.0F;
         List currentIds = new ArrayList();
         float headerHeight = 12.0F + gap;
         float contentHeight = 0.0F;
         Iterator var24 = ((List)entries).iterator();

         float currentX;
         while(var24.hasNext()) {
            Staff entry = (Staff)var24.next();
            currentIds.add(entry.getName());
            AnimationUtil[] anim = (AnimationUtil[])this.itemAnimations.computeIfAbsent(entry.getName(), (key) -> {
               return new AnimationUtil[]{new AnimationUtil(0.0F, 10.0F), new AnimationUtil(-5.0F, 10.0F), new AnimationUtil(0.0F, 10.0F)};
            });
            anim[0].update(1.0F);
            float animVal = anim[0].getValue();
            if (animVal > 0.01F) {
               String prefixPlain = TextFormatting.getTextWithoutFormattingCodes(entry.getPrefix().getString());
               if (prefixPlain == null) {
                  prefixPlain = "";
               }

               float prefixWidth = Fonts.sfregular[13].getWidth(prefixPlain);
               float nameWidth = Fonts.sfregular[13].getWidth(entry.getName());
               String statusText = entry.isVanished() ? "SPEC" : "ACTIVE";
               currentX = Fonts.sfregular[13].getWidth(statusText);
               float itemWidth = headSize + headGap + prefixWidth + 1.5F + nameWidth + itemGap + currentX;
               maxItemWidth = Math.max(maxItemWidth, itemWidth);
               contentHeight += (itemHeight + itemGap) * animVal;
            }
         }

         this.itemAnimations.entrySet().removeIf((e) -> {
            return !currentIds.contains(e.getKey()) && ((AnimationUtil[])e.getValue())[0].isDone() && ((AnimationUtil[])e.getValue())[0].getValue() <= 0.01F;
         });
         float targetWidth = Math.max(totalWidth, maxItemWidth + paddingX * 2.0F);
         float animatedHeight = paddingY + headerHeight + contentHeight + paddingY - 10.0F;
         int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
         int bgColor = ColorUtil.darken(logoColor, 0.1F);
         int textColor = -1;
         int statusColor = logoColor;
         RenderUtil.drawRoundedRectangle(posX, posY, targetWidth, animatedHeight, borderRadius, ColorUtil.applyOpacity(bgColor, (int)(255.0F * globalAlpha)));
         float currentY = posY + paddingY;
         currentX = posX + paddingX;
         Fonts.skycore[14].drawString(ms, "d", (double)currentX, (double)(currentY + 2.9F), ColorUtil.applyOpacity(logoColor, (int)(255.0F * globalAlpha)));
         currentX += iconSize + headerGap;
         Fonts.sfregular[13].drawString(ms, "Personal", (double)(currentX + 1.0F), (double)(currentY + 2.5F), ColorUtil.applyOpacity(textColor, (int)(255.0F * globalAlpha)));
         currentY += headerHeight;
         Iterator var50 = ((List)entries).iterator();

         while(var50.hasNext()) {
            Staff entry = (Staff)var50.next();
            AnimationUtil[] itemAnims = (AnimationUtil[])this.itemAnimations.get(entry.getName());
            if (itemAnims != null) {
               float animVal = itemAnims[0].getValue();
               if (!(animVal <= 0.01F)) {
                  currentX = posX + paddingX;
                  float slideOffset = (1.0F - animVal) * 10.0F;
                  float headY = currentY - 3.5F - slideOffset;
                  if ((Boolean)skins.get()) {
                     ResourceLocation skinLocation = this.getStaffSkin(entry.getName());
                     mc.getTextureManager().bindTexture(skinLocation);
                     ShaderUtil.rounded_head_texture.attach();
                     ShaderUtil.rounded_head_texture.setUniformf("size", headSize, headSize);
                     ShaderUtil.rounded_head_texture.setUniformf("radius", headSize / 2.0F);
                     ShaderUtil.rounded_head_texture.setUniformf("roundType", 0.0F);
                     ShaderUtil.rounded_head_texture.setUniformf("hurt_time", 0.0F);
                     ShaderUtil.rounded_head_texture.setUniformf("alpha", animVal * globalAlpha);
                     ShaderUtil.rounded_head_texture.setUniformf("texXSize", 64.0F);
                     ShaderUtil.rounded_head_texture.setUniformf("texYSize", 64.0F);
                     RenderUtil.beginRectBatch(false, true);
                     RenderUtil.drawQuads((double)currentX, (double)headY, (double)headSize, (double)headSize);
                     RenderUtil.endRectBatch();
                     ShaderUtil.rounded_head_texture.detach();
                     currentX += headSize + headGap;
                  }

                  String prefixPlain = TextFormatting.getTextWithoutFormattingCodes(entry.getPrefix().getString());
                  if (prefixPlain == null) {
                     prefixPlain = "";
                  }

                  Fonts.sfregular[13].drawText(ms, entry.getPrefix(), (double)currentX, (double)(currentY - 2.0F - slideOffset), ColorUtil.applyOpacity(-1, (int)(255.0F * animVal * globalAlpha)));
                  currentX += Fonts.sfregular[13].getWidth(prefixPlain) + 1.5F;
                  Fonts.sfregular[13].drawString(ms, entry.getName(), (double)currentX, (double)(currentY - 2.0F - slideOffset), ColorUtil.applyOpacity(textColor, (int)(255.0F * animVal * globalAlpha)));
                  String statusText = entry.isVanished() ? "SPEC" : "ACTIVE";
                  float statusWidth = Fonts.sfregular[13].getWidth(statusText);
                  float statusX = posX + targetWidth - paddingX - statusWidth;
                  Fonts.sfregular[13].drawString(ms, statusText, (double)(statusX + slideOffset), (double)(currentY - 2.0F - slideOffset), ColorUtil.applyOpacity(statusColor, (int)(255.0F * animVal * globalAlpha)));
                  currentY += (itemHeight + itemGap) * animVal;
               }
            }
         }

         this.dragging.setWidth(targetWidth);
         this.dragging.setHeight(animatedHeight);
      }
   }

   private ResourceLocation getStaffSkin(String name) {
      Iterator var2;
      ResourceLocation skin;
      if (mc.getConnection() != null) {
         var2 = mc.getConnection().getPlayerInfoMap().iterator();

         while(var2.hasNext()) {
            NetworkPlayerInfo info = (NetworkPlayerInfo)var2.next();
            if (info != null && info.getGameProfile() != null && name.equalsIgnoreCase(info.getGameProfile().getName())) {
               try {
                  skin = info.getLocationSkin();
                  if (skin != null && !skin.equals(STEVE_SKIN) && !skin.equals(ALEX_SKIN) && !skin.getPath().contains("steve") && !skin.getPath().contains("alex")) {
                     return skin;
                  }
               } catch (Exception var5) {
               }
            }
         }
      }

      if (mc.world != null) {
         var2 = mc.world.getPlayers().iterator();

         while(var2.hasNext()) {
            PlayerEntity player = (PlayerEntity)var2.next();
            if (name.equalsIgnoreCase(player.getGameProfile().getName())) {
               skin = mc.getRenderManager().getRenderer(player).getEntityTexture(player);
               if (skin != null) {
                  return skin;
               }
            }
         }
      }

      UUID offlineUuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8));
      return (offlineUuid.hashCode() & 1) == 0 ? STEVE_SKIN : ALEX_SKIN;
   }

   private float[] calculateDimensions(List dimsItems) {
      float maxTextWidth = 0.0F;

      float prefixWidth;
      float nameWidth;
      for(Iterator var3 = dimsItems.iterator(); var3.hasNext(); maxTextWidth = Math.max(maxTextWidth, prefixWidth + nameWidth)) {
         Staff item = (Staff)var3.next();
         String plainName = TextFormatting.getTextWithoutFormattingCodes(item.getPrefix().getString());
         prefixWidth = Fonts.sf_medium[12].getWidth(plainName);
         nameWidth = Fonts.sf_medium[12].getWidth(item.getName());
      }

      float avatarBlock = (Boolean)skins.get() ? 9.5F : 0.0F;
      float width = Math.max(68.0F, avatarBlock + maxTextWidth + 21.0F);
      return new float[]{width};
   }

   public StaffListRender(Dragging dragging) {
      this.dragging = dragging;
   }
}
