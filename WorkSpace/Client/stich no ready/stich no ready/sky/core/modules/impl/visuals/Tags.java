package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import org.joml.Vector2f;
import other.bot.Bot;
import other.bot.BotManager;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.impl.miscellaneous.ScoreboardHealth;
import sky.core.utils.math.MathUtil;
import sky.core.utils.math.NumberUtil;
import sky.core.utils.misc.ServerUtil;
import sky.core.utils.misc.TargetUtil;
import sky.core.utils.player.PotionUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.ProjectUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;
import sky.core.utils.render.font.StyledFont;

public class Tags extends Module {
   private static final ResourceLocation STEVE_SKIN = new ResourceLocation("textures/entity/steve.png");
   public MultiBooleanSetting rendering = new MultiBooleanSetting("Отображать", new BooleanSetting[]{new BooleanSetting("Игроков", true), new BooleanSetting("Монстров", false), new BooleanSetting("Друзей", false), new BooleanSetting("Животных", false), new BooleanSetting("Себя", false), new BooleanSetting("Жителей", false), new BooleanSetting("Голых", false), new BooleanSetting("Предметы", true)});
   public BooleanSetting viewhead = new BooleanSetting("Отображать голову", true);
   public BooleanSetting vieweffects = new BooleanSetting("Отображать эффекты", false);
   public BooleanSetting viewarmor = new BooleanSetting("Отображать броню", false);
   public BooleanSetting viewenchants = new BooleanSetting("Отображать зачарования", false);
   public BooleanSetting viewitem = new BooleanSetting("Отображать левую руку", false);
   public BooleanSetting viewdisplayname = new BooleanSetting("Отображать визуальное имя предмета", false, () -> {
      return (Boolean)this.viewitem.get();
   });
   public BooleanSetting viewitemicon = new BooleanSetting("Отображать иконку предмета", true);

   public Tags() {
      super("Tags", "Отображает имя и информацию об существах", Category.Visuals);
      this.addSettings(new Setting[]{this.rendering, this.viewhead, this.viewitemicon, this.vieweffects, this.viewarmor, this.viewenchants, this.viewitem, this.viewdisplayname});
   }

   @EventTarget
   public void onRender(EventRender2D event) {
      float partialTicks = mc.getRenderPartialTicks();
      StyledFont fontNameCached = Fonts.sf_medium[13];
      int rectBgColor = ColorUtil.getColor(0, 0, 0, 85);
      Bot bot = null;
      Iterator var6 = BotManager.allBots.iterator();

      while(var6.hasNext()) {
         Bot bot1 = (Bot)var6.next();
         if (mc.renderViewEntity == bot1.connection.bot) {
            bot = bot1;
         }
      }

      var6 = (bot != null ? bot.connection.getWorld().getAllEntities() : mc.world.getAllEntities()).iterator();

      while(true) {
         while(true) {
            Entity baseEntity;
            do {
               if (!var6.hasNext()) {
                  return;
               }

               baseEntity = (Entity)var6.next();
            } while(!ProjectUtil.isInView(baseEntity));

            if (TargetUtil.isItemTarget(baseEntity, this.rendering) && baseEntity instanceof ItemEntity itemEntity) {
               Vector3d interpolatedItem = MathUtil.interpolate(itemEntity, partialTicks);
               AxisAlignedBB itemAabb = ProjectUtil.getEntityBox(itemEntity, interpolatedItem);
               Vector4f bounds = this.computeScreenBounds(itemAabb);
               this.renderItemTag(event, itemEntity, bounds, fontNameCached, rectBgColor);
            } else if (baseEntity instanceof LivingEntity entity) {
               if (entity.isAlive()) {
                  boolean isTarget = this.isEntityTarget(entity);
                  if (isTarget) {
                     Vector3d interpolated = MathUtil.interpolate(entity, partialTicks);
                     AxisAlignedBB aabb = ProjectUtil.getEntityBox(entity, interpolated);
                     Vector4f bounds = this.computeScreenBounds(aabb);
                     this.renderEntityTag(event, entity, bounds, fontNameCached, rectBgColor);
                  }
               }
            }
         }
      }
   }

   private boolean isEntityTarget(LivingEntity entity) {
      return TargetUtil.isSelfTarget(entity, this.rendering) || TargetUtil.isPlayerTarget(entity, this.rendering, true) || TargetUtil.isVillagerTarget(entity, this.rendering) || TargetUtil.isAnimalTarget(entity, this.rendering) || TargetUtil.isMonsterTarget(entity, this.rendering);
   }

   private Vector4f computeScreenBounds(AxisAlignedBB aabb) {
      Vector3d[] corners = ProjectUtil.getCorners(aabb);
      Vector2f min = new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
      Vector2f max = new Vector2f(Float.MIN_VALUE, Float.MIN_VALUE);
      Vector3d[] var5 = corners;
      int var6 = corners.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Vector3d corner = var5[var7];
         Vector2f projected = ProjectUtil.project2D(corner);
         if (projected.x < min.x) {
            min.x = projected.x;
         }

         if (projected.y < min.y) {
            min.y = projected.y;
         }

         if (projected.x > max.x) {
            max.x = projected.x;
         }

         if (projected.y > max.y) {
            max.y = projected.y;
         }
      }

      return new Vector4f(min.x, min.y, max.x, max.y);
   }

   private ResourceLocation getSkinTexture(PlayerEntity player) {
      return player instanceof AbstractClientPlayerEntity ? ((AbstractClientPlayerEntity)player).getLocationSkin() : STEVE_SKIN;
   }

   private void renderItemTag(EventRender2D event, ItemEntity itemEntity, Vector4f b, StyledFont fontName, int rectColor) {
      ItemStack stack = itemEntity.getItem();
      ITextComponent nameComp = (Boolean)this.viewdisplayname.get() ? stack.getDisplayName() : stack.getItem().getDisplayName(stack);
      int count = stack.getCount();
      Rarity rarity = stack.getRarity();
      String plainName = nameComp.getString();
      IFormattableTextComponent nameBlockComponent = (new StringTextComponent("")).append(nameComp).mergeStyle(rarity.color);
      float fontHeight = fontName.getHeight();
      float centerX = (b.getX() + b.getZ()) / 2.0F;
      float rectHeight = 11.0F;
      float gap = 1.0F;
      float padding = 3.0F;
      float nameBlockWidth = fontName.getWidth(plainName) + padding * 2.0F;
      boolean showCount = count > 1;
      String countText = "x" + count;
      float countBlockWidth = showCount ? fontName.getWidth(countText) + padding * 2.0F : 0.0F;
      int countColor = ColorUtil.rgb(255, 68, 68);
      boolean showIcon = (Boolean)this.viewitemicon.get();
      float totalWidth = (showIcon ? rectHeight + gap : 0.0F) + nameBlockWidth + (showCount ? gap + countBlockWidth : 0.0F);
      float startX = centerX - totalWidth / 2.0F;
      float tagY = b.getY() - rectHeight - 3.0F;
      float currentX = startX;
      if (showIcon) {
         RenderUtil.drawRoundedRectangle(startX, tagY, rectHeight, rectHeight, 2.0F, rectColor);
         RenderUtil.drawStack(stack, startX + 1.5F, tagY + 1.5F, 0.5F);
         currentX = startX + rectHeight + gap;
      }

      RenderUtil.drawRoundedRectangle(currentX, tagY, nameBlockWidth, rectHeight, 2.0F, rectColor);
      float nameTextX = currentX + padding;
      float nameTextY = tagY + (rectHeight - fontHeight) / 2.0F + 1.0F;
      fontName.drawText(event.getStack(), nameBlockComponent, (double)nameTextX, (double)nameTextY, -1);
      currentX += nameBlockWidth;
      if (showCount) {
         currentX += gap;
         RenderUtil.drawRoundedRectangle(currentX, tagY, countBlockWidth, rectHeight, 2.0F, rectColor);
         float countTextX = currentX + padding;
         float countTextY = tagY + (rectHeight - fontHeight) / 2.0F + 1.0F;
         fontName.drawString(event.getStack(), countText, (double)countTextX, (double)countTextY, countColor);
      }

   }

   private void renderEntityTag(EventRender2D event, LivingEntity entity, Vector4f b, StyledFont fontName, int rectColor) {
      ITextComponent nameComponent = entity.getDisplayName();
      String plainName = TextFormatting.getTextWithoutFormattingCodes(nameComponent.getString());
      boolean isFriend = entity instanceof PlayerEntity && SkyCore.getInstance().getFriendManager().isFriend(((PlayerEntity)entity).getGameProfile().getName());
      boolean useScoreboard = SkyCore.getInstance().getModuleManager().getModule(ScoreboardHealth.class).isEnabled();
      float hp = useScoreboard ? ServerUtil.getHealth(entity) : entity.getHealth();
      int healthValue = Math.max(0, Math.round(hp));
      int finalRectColor = isFriend ? ColorUtil.getColor(0, 120, 0, 120) : rectColor;
      float centerX = (b.getX() + b.getZ()) / 2.0F;
      float fontHeight = fontName.getHeight();
      float rectHeight = 11.0F;
      float gap = 0.5F;
      float padding = 3.0F;
      String strippedName = TextFormatting.getTextWithoutFormattingCodes(nameComponent.getString());
      String actualName = "";
      if (entity instanceof PlayerEntity playerEntity) {
         actualName = playerEntity.getGameProfile().getName();
      }

      IFormattableTextComponent nameBlockComponent = (new StringTextComponent("")).append(nameComponent);
      String nameBlockText = strippedName;
      if (isFriend) {
         nameBlockComponent.append((new StringTextComponent(" [")).mergeStyle(TextFormatting.GRAY)).append((new StringTextComponent("F")).mergeStyle(TextFormatting.GREEN)).append((new StringTextComponent("]")).mergeStyle(TextFormatting.GRAY));
         nameBlockText = strippedName + " [F]";
      }

      float nameBlockWidth = fontName.getWidth(nameBlockText) + padding * 2.0F;
      String hpText = String.valueOf(healthValue);
      float hpBlockWidth = fontName.getWidth(hpText) + padding * 2.0F;
      int hpColor = ColorUtil.rgb(255, 68, 68);
      boolean showHead = (Boolean)this.viewhead.get() && entity instanceof PlayerEntity;
      float totalWidth = (showHead ? rectHeight + gap : 0.0F) + nameBlockWidth + gap + hpBlockWidth;
      float startX = centerX - totalWidth / 2.0F;
      float tagY = b.getY() - rectHeight - 3.0F;
      float currentX = startX;
      if (showHead) {
         RenderUtil.drawRoundedRectangle(startX, tagY, rectHeight, rectHeight, 2.0F, finalRectColor);
         ResourceLocation skin = this.getSkinTexture((PlayerEntity)entity);
         RenderUtil.drawRoundedHead(skin, entity, startX + 1.5F, tagY + 1.5F, rectHeight - 3.0F, rectHeight - 3.0F, 1.0F, 1.0F);
         currentX = startX + rectHeight + gap;
      }

      RenderUtil.drawRoundedRectangle(currentX, tagY, nameBlockWidth, rectHeight, 2.0F, finalRectColor);
      float nameTextX = currentX + padding;
      float nameTextY = tagY + (rectHeight - fontHeight) / 2.0F + 1.0F;
      fontName.drawText(event.getStack(), nameBlockComponent, (double)nameTextX, (double)nameTextY, -1);
      currentX += nameBlockWidth + gap;
      RenderUtil.drawRoundedRectangle(currentX, tagY, hpBlockWidth, rectHeight, 2.0F, finalRectColor);
      float hpTextX = currentX + padding;
      float hpTextY = tagY + (rectHeight - fontHeight) / 2.0F + 1.0F;
      fontName.drawString(event.getStack(), hpText, (double)hpTextX, (double)hpTextY, hpColor);
      if ((Boolean)this.viewarmor.get()) {
         this.drawArmorRow(entity, centerX, tagY);
      }

      float effectsBaseY = b.getW() + 6.0F;
      if ((Boolean)this.viewitem.get()) {
         effectsBaseY = this.drawHeldItems(event, entity, centerX, effectsBaseY, finalRectColor);
      }

      if ((Boolean)this.vieweffects.get()) {
         this.drawEffects(event, entity, centerX, effectsBaseY, Fonts.sf_regular[11]);
      }

   }

   private int getHealthColor(int health, float maxHealth) {
      float ratio = Math.min(1.0F, (float)health / Math.max(1.0F, maxHealth));
      float t;
      int g;
      if (ratio > 0.66F) {
         t = (ratio - 0.66F) / 0.34F;
         g = (int)lerp(255.0F, 0.0F, t);
         int g = 255;
         return ColorUtil.rgb(g, g, 0);
      } else if (ratio > 0.33F) {
         t = (ratio - 0.33F) / 0.33F;
         g = (int)lerp(100.0F, 255.0F, t);
         return ColorUtil.rgb(255, g, 0);
      } else {
         t = ratio / 0.33F;
         g = (int)lerp(0.0F, 100.0F, t);
         return ColorUtil.rgb(255, g, 0);
      }
   }

   private static float lerp(float from, float to, float t) {
      return from + (to - from) * Math.max(0.0F, Math.min(1.0F, t));
   }

   private void drawArmorRow(LivingEntity entity, float centerX, float baseY) {
      List armor = new ArrayList();
      Iterable var10000 = entity.getArmorInventoryList();
      Objects.requireNonNull(armor);
      var10000.forEach(armor::add);
      ItemStack mainHand = entity.getHeldItemMainhand();
      ItemStack offHand = entity.getHeldItemOffhand();
      float y = baseY - 12.0F;
      float totalWidth = 0.0F;

      for(int i = 3; i >= 0; --i) {
         if (!((ItemStack)armor.get(i)).isEmpty()) {
            totalWidth += 9.5F;
         }
      }

      if (!mainHand.isEmpty()) {
         totalWidth += 9.5F;
      }

      if (!offHand.isEmpty()) {
         totalWidth += 9.5F;
      }

      float offsetX = -totalWidth / 2.0F + 3.0F;

      for(int i = 0; i < 6; ++i) {
         ItemStack stack = i == 0 ? offHand : (i == 5 ? mainHand : (ItemStack)armor.get(4 - i));
         if (!stack.isEmpty()) {
            if ((Boolean)this.viewenchants.get()) {
               List enchantments = this.getEnchantments(stack);
               if (!enchantments.isEmpty()) {
                  float enchantY = y - 4.0F;

                  for(Iterator var14 = enchantments.iterator(); var14.hasNext(); enchantY -= 6.0F) {
                     String enchantment = (String)var14.next();
                     String plainText = TextFormatting.getTextWithoutFormattingCodes(enchantment);
                     float enchantWidth = Fonts.sf_medium[10].getWidth(plainText);
                     Fonts.sf_medium[10].drawString(new MatrixStack(), plainText, (double)(centerX + offsetX - enchantWidth / 2.0F + 5.0F), (double)enchantY, -1);
                  }
               }
            }

            RenderUtil.drawStack(stack, centerX + offsetX, y, 0.5F);
            offsetX += 10.5F;
         }
      }

   }

   private float drawHeldItems(EventRender2D event, LivingEntity entity, float centerX, float startY, int rectColor) {
      float lineHeight = 10.0F;
      float y = startY;
      ItemStack main = entity.getHeldItemMainhand();
      float w;
      if (!main.isEmpty()) {
         ITextComponent comp = (Boolean)this.viewdisplayname.get() ? main.getDisplayName() : main.getItem().getDisplayName(main);
         String text = comp.getString();
         float w = Fonts.sf_medium[12].getWidth(text);
         w = centerX - w / 2.0F;
         RenderUtil.drawMinecraftRectangle(event.getStack(), w - 1.5F, startY - 4.0F, w + 3.0F, 10.0F, rectColor);
         Fonts.sf_medium[12].drawText(event.getStack(), comp, (double)w, (double)startY, -1);
         y = startY + lineHeight;
      }

      ItemStack off = entity.getHeldItemOffhand();
      if (!off.isEmpty()) {
         ITextComponent comp = (Boolean)this.viewdisplayname.get() ? off.getDisplayName() : off.getItem().getDisplayName(off);
         String text = comp.getString();
         w = Fonts.sf_medium[12].getWidth(text);
         float x = centerX - w / 2.0F;
         RenderUtil.drawMinecraftRectangle(event.getStack(), x - 1.5F, y - 3.0F, w + 3.0F, 10.0F, rectColor);
         Fonts.sf_medium[12].drawText(event.getStack(), comp, (double)x, (double)(y + 1.0F), -1);
         y += lineHeight;
      }

      return y;
   }

   private List getEnchantments(ItemStack stack) {
      List enchantments = new ArrayList();
      Map enchantmentMap = EnchantmentHelper.getEnchantments(stack);
      Iterator var4 = enchantmentMap.entrySet().iterator();

      while(true) {
         while(var4.hasNext()) {
            Map.Entry entry = (Map.Entry)var4.next();
            Enchantment enchantment = (Enchantment)entry.getKey();
            String enchantName = I18n.format(enchantment.getName(), new Object[0]);
            String shortName = enchantName.length() >= 2 ? enchantName.substring(0, 2) : enchantName;
            TextFormatting color = TextFormatting.WHITE;
            if (enchantment.getMaxLevel() == 1 && (Integer)entry.getValue() == 1) {
               String var10001 = String.valueOf(color);
               enchantments.add(var10001 + shortName);
            } else {
               enchantments.add(String.valueOf(color) + shortName + String.valueOf(entry.getValue()));
            }
         }

         return enchantments;
      }
   }

   private void drawEffects(EventRender2D event, LivingEntity entity, float centerX, float startY, StyledFont fontEff) {
      Collection activeEffects = entity.getActivePotionEffects();
      if (!activeEffects.isEmpty()) {
         List lines = new ArrayList(activeEffects.size());
         Iterator var8 = activeEffects.iterator();

         while(var8.hasNext()) {
            EffectInstance effectInstance = (EffectInstance)var8.next();
            int durationTicks = effectInstance.getDuration();
            if (durationTicks > 20) {
               String key = Registry.EFFECTS.getKey(effectInstance.getPotion()).getPath();
               TextFormatting tf = PotionUtil.getEffectColor(key);
               Integer rgb = tf.getColor();
               int color = rgb != null ? -16777216 | rgb : -1;
               String name = effectInstance.getPotion().getDisplayName().getString();
               int amp = effectInstance.getAmplifier() + 1;
               StringBuilder sb = new StringBuilder(name);
               if (amp > 1) {
                  sb.append(' ').append(NumberUtil.toRomanNumeral(amp));
               }

               sb.append(' ').append(PotionUtil.formatDuration(durationTicks));
               String text = sb.toString();
               float width = fontEff.getWidth(text);
               lines.add(new EffectLine(text, width, color));
            }
         }

         if (lines.size() > 1) {
            lines.sort(Comparator.comparingDouble((e) -> {
               return (double)e.width;
            }));
         }

         float lineStep = fontEff.getHeight() + 2.0F;

         for(int i = 0; i < lines.size(); ++i) {
            EffectLine line = (EffectLine)lines.get(i);
            float drawX = centerX - line.width / 2.0F;
            float drawY = startY + (float)i * lineStep;
            fontEff.drawString(event.getStack(), line.text, (double)drawX, (double)drawY, line.color);
         }

      }
   }
}
