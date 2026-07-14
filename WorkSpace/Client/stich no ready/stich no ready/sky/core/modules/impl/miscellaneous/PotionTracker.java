package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.event.HoverEvent.Action;
import sky.core.events.EventThrowPotion;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.managers.impl.notificationmanager.NotificationManager;
import sky.core.utils.math.NumberUtil;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.render.ColorUtil;

public class PotionTracker extends Module {
   private final BooleanSetting ignoreself = new BooleanSetting("Игнорировать себя", false);

   public PotionTracker() {
      super("Potion Tracker", "У данного модуля нет описания!", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.ignoreself});
   }

   @EventTarget
   public void onThrowPotion(EventThrowPotion event) {
      if ((Boolean)this.ignoreself.get() && event.getEntity() instanceof PlayerEntity) {
         LivingEntity var10000 = event.getEntity();
         Minecraft var10001 = mc;
         if (var10000.equals(Minecraft.player)) {
            return;
         }
      }

      int totalReceivedDuration = 0;
      int totalMaxDuration = 0;

      EffectInstance effect;
      for(Iterator var4 = event.getEffects().iterator(); var4.hasNext(); totalMaxDuration += effect.getDuration()) {
         effect = (EffectInstance)var4.next();
         totalReceivedDuration += (int)((double)effect.getDuration() * event.getDistanceFactor());
      }

      int receivedSeconds = totalReceivedDuration / 20;
      int maxSeconds = totalMaxDuration / 20;
      int successPercentage = maxSeconds > 0 ? receivedSeconds * 100 / maxSeconds : 100;
      StringBuilder effectsList = new StringBuilder();
      Iterator var8 = event.getEffects().iterator();

      int i;
      while(var8.hasNext()) {
         EffectInstance effect = (EffectInstance)var8.next();
         String effectName = effect.getPotion().getDisplayName().getString();
         i = (int)((double)effect.getDuration() * event.getDistanceFactor());
         int minutes = i / 20 / 60;
         int seconds = i / 20 % 60;
         String romanAmplifier = NumberUtil.toRomanNumeral(effect.getAmplifier() + 1);
         effectsList.append(TextFormatting.GRAY).append("● ").append(TextFormatting.RED).append(effectName).append(" ").append(romanAmplifier).append(TextFormatting.GRAY).append(" ").append(String.format("%d:%02d", minutes, seconds)).append("\n");
      }

      if (effectsList.length() > 1) {
         effectsList.setLength(effectsList.length() - 1);
      }

      ItemStack stack = event.getItemStack();
      List tooltipLines = new ArrayList();
      tooltipLines.add(stack.getDisplayName().deepCopy());
      PotionUtils.addPotionTooltip(stack, tooltipLines, 1.0F);
      IFormattableTextComponent hoverText = new StringTextComponent("");

      for(i = 0; i < tooltipLines.size(); ++i) {
         hoverText.append((ITextComponent)tooltipLines.get(i));
         if (i < tooltipLines.size() - 1) {
            hoverText.appendString("\n");
         }
      }

      HoverEvent hoverEvent = new HoverEvent(Action.SHOW_TEXT, hoverText);
      IFormattableTextComponent chatMessage = (new StringTextComponent("")).append((new StringTextComponent(event.getEntity().getName().getString())).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.GRAY))).append((new StringTextComponent(" получил эффекты из ")).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.GRAY))).append(event.getItemStack().getDisplayName()).append((new StringTextComponent("\n")).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.GRAY))).append((new StringTextComponent("● Успешность ")).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.GRAY))).append((new StringTextComponent("" + successPercentage + "%")).setStyle((new StringTextComponent("")).getStyle().setColor(Color.fromInt(ColorUtil.interpolateSuccessColor(successPercentage))))).append((new StringTextComponent("\n" + String.valueOf(effectsList))).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.GRAY)));
      chatMessage.setStyle(chatMessage.getStyle().setHoverEvent(hoverEvent));
      ChatUtil.addText(chatMessage);
      IFormattableTextComponent notificationMessage = (new StringTextComponent("")).append((new StringTextComponent(event.getEntity().getName().getString())).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.WHITE))).append((new StringTextComponent(" получил эффекты из '")).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.WHITE))).append(((IFormattableTextComponent)stack.getDisplayName()).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.WHITE))).append((new StringTextComponent("'\n")).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.RESET))).append((new StringTextComponent(" " + successPercentage + "%")).setStyle((new StringTextComponent("")).getStyle().setColor(Color.fromInt(ColorUtil.interpolateSuccessColor(successPercentage)))));
      int potionColor = PotionUtils.getColor(stack);
      NotificationManager.addNotification((ResourceLocation)(new ResourceLocation("minecraft", "textures/item/splash_potion.png")), (ITextComponent)notificationMessage, potionColor);
   }
}
