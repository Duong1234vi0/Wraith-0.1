package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.datafixers.util.Pair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.network.play.server.SEntityEquipmentPacket;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.event.HoverEvent.Action;
import sky.core.events.EventPacket;
import sky.core.events.EventPopTotem;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.managers.impl.notificationmanager.NotificationManager;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.misc.ChatUtil;

public class UseTracker extends Module {
   private final BooleanSetting totem = new BooleanSetting("Отслеживать снесенные тотемы", true);
   private static final Map enchanted = new HashMap();
   private final Set dropSet = new HashSet();
   private final TimeUtil timerEat = new TimeUtil();

   public UseTracker() {
      super("Use Tracker", "Оповещает об использовании еды/зелий другими игроками", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.totem});
   }

   @EventTarget
   public void onThrowPotion(EventPopTotem event) {
      Entity var3 = event.getEntity();
      if (var3 instanceof PlayerEntity player) {
         if ((Boolean)this.totem.get()) {
            ItemStack totemStack = player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING ? player.getHeldItemOffhand() : (player.getHeldItemMainhand().getItem() == Items.TOTEM_OF_UNDYING ? player.getHeldItemMainhand() : new ItemStack(Items.TOTEM_OF_UNDYING));
            boolean isEnchanted = totemStack.isEnchanted();
            enchanted.put(player.getUniqueID(), isEnchanted);
            IFormattableTextComponent itemDisplayName = (IFormattableTextComponent)totemStack.getDisplayName();
            List tooltipLines = totemStack.getTooltip(player, TooltipFlags.NORMAL);
            IFormattableTextComponent hoverText = new StringTextComponent("");

            for(int i = 0; i < tooltipLines.size(); ++i) {
               ITextComponent line = (ITextComponent)tooltipLines.get(i);
               if (line instanceof IFormattableTextComponent) {
                  hoverText.append(line);
               } else {
                  hoverText.append(new StringTextComponent(line.getString()));
               }

               if (i < tooltipLines.size() - 1) {
                  hoverText.appendString("\n");
               }
            }

            HoverEvent hoverEvent = new HoverEvent(Action.SHOW_TEXT, hoverText);
            IFormattableTextComponent message = (new StringTextComponent("")).append((new StringTextComponent(player.getName().getString())).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.WHITE))).append((new StringTextComponent(" потерял ")).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.GRAY))).append(itemDisplayName).append((new StringTextComponent(", зачарован: ")).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.GRAY))).append((new StringTextComponent("●")).setStyle((new StringTextComponent("")).getStyle().applyFormatting(isEnchanted ? TextFormatting.GREEN : TextFormatting.RED)));
            message.setStyle(message.getStyle().setHoverEvent(hoverEvent));
            ChatUtil.addText(message);
            NotificationManager.addNotification((ResourceLocation)(new ResourceLocation("minecraft", "textures/item/totem_of_undying.png")), (ITextComponent)message, isEnchanted ? '\uff00' : 16711680);
            return;
         }
      }

   }

   @EventTarget
   public void onPacket(EventPacket e) {
      if (e.isReceive()) {
         Object packet = e.getPacket();
         if (packet instanceof SAnimateHandPacket) {
            SAnimateHandPacket sAnimateHandPacket = (SAnimateHandPacket)packet;
            if (sAnimateHandPacket.getAnimationType() == 0) {
               this.dropSet.add(sAnimateHandPacket.getEntityID());
               this.timerEat.reset();
            }
         }

         if (packet instanceof SEntityEquipmentPacket) {
            SEntityEquipmentPacket sEntityEquipmentPacket = (SEntityEquipmentPacket)packet;
            int entityId = sEntityEquipmentPacket.getEntityID();
            if (this.dropSet.contains(entityId)) {
               if (this.timerEat.getTimePassed() < 300L) {
                  return;
               }

               this.dropSet.remove(entityId);
            }

            Entity raw = mc.world.getEntityByID(entityId);
            if (!(raw instanceof LivingEntity)) {
               return;
            }

            LivingEntity living = (LivingEntity)raw;
            ItemStack before = living.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            Iterator var8 = sEntityEquipmentPacket.func_241790_c_().iterator();

            while(var8.hasNext()) {
               Pair entry = (Pair)var8.next();
               if (entry.getFirst() == EquipmentSlotType.MAINHAND) {
                  ItemStack after = (ItemStack)entry.getSecond();
                  if (!after.isEmpty() && this.isConsumeEvent(before, after, living)) {
                     IFormattableTextComponent itemDisplayName = (IFormattableTextComponent)after.getDisplayName();
                     List tooltipLines = after.getTooltip(living instanceof PlayerEntity ? (PlayerEntity)living : null, TooltipFlags.NORMAL);
                     IFormattableTextComponent hoverText = new StringTextComponent("");

                     for(int i = 0; i < tooltipLines.size(); ++i) {
                        ITextComponent line = (ITextComponent)tooltipLines.get(i);
                        if (line instanceof IFormattableTextComponent) {
                           hoverText.append(line);
                        } else {
                           hoverText.append(new StringTextComponent(line.getString()));
                        }

                        if (i < tooltipLines.size() - 1) {
                           hoverText.appendString("\n");
                        }
                     }

                     HoverEvent hoverEvent = new HoverEvent(Action.SHOW_TEXT, hoverText);
                     IFormattableTextComponent message = (new StringTextComponent("")).append((new StringTextComponent(living.getName().getString())).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.WHITE))).append((new StringTextComponent(" использовал ")).setStyle((new StringTextComponent("")).getStyle().applyFormatting(TextFormatting.GRAY))).append(itemDisplayName);
                     message.setStyle(message.getStyle().setHoverEvent(hoverEvent));
                     ResourceLocation texture;
                     if (after.getItem() != Items.GOLDEN_APPLE && after.getItem() != Items.ENCHANTED_GOLDEN_APPLE) {
                        texture = new ResourceLocation("minecraft", "textures/item/" + String.valueOf(after.getItem()) + ".png");
                     } else {
                        texture = new ResourceLocation("minecraft", "textures/item/golden_apple.png");
                     }

                     int potionColor = PotionUtils.getColor(after);
                     ChatUtil.addText(message);
                     NotificationManager.addNotification((ResourceLocation)texture, (ITextComponent)message, potionColor);
                  }
                  break;
               }
            }
         }

      }
   }

   private boolean isConsumeEvent(ItemStack before, ItemStack after, LivingEntity living) {
      return ItemStack.areItemsEqual(before, after) && after.getCount() < before.getCount() && living.isHandActive();
   }
}
