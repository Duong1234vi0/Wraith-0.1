package dev.client.modules.impl.movement;

import dev.client.WildClient;
import dev.client.event.classes.TickEvent;
import dev.client.event.classes.UsingItemEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.event.interfaces.IUsingItem;
import dev.client.mixins.other.IClientPlayerInteractionManagerMixin;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.SequencedPacketCreator;
import net.minecraft.item.consume.UseAction;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.util.Hand;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class NoSlow extends Module implements IUsingItem, ITickable, IUtil {
   private ModeSetting mode = ((ModeSetting)(new ModeSetting()).name("Mode")).value("Grim Old").modes("Grim Old", "SpookyTime", "MetaHvH", "ReallyWorld");
   private int ticks = 0;

   public NoSlow() {
      super(new ModuleInfo("NoSlow", Category.MOVEMENT, "Убирает замедление от использования предметов"));
      this.addSetting(new Setting[]{this.mode});
   }

   @Compile
   public void onUsing(UsingItemEvent event) {
      Hand first = mc.player.getActiveHand();
      Hand second = first.equals(Hand.MAIN_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND;
      switch (event.getEventType()) {
         case ON:
            switch (this.mode.getValue()) {
               case "Grim Old":
                  if (mc.player.getOffHandStack().getUseAction().equals(UseAction.NONE) || mc.player.getMainHandStack().getUseAction().equals(UseAction.NONE)) {
                     interactItem(first);
                     interactItem(second);
                     event.cancel();
                  }
                  break;
               case "SpookyTime":
                  if ((float)this.ticks > 1.0F && mc.player.getItemUseTime() > 1) {
                     event.cancel();
                     this.ticks = 0;
                  }
                  break;
               case "ReallyWorld":
                  if ((float)this.ticks > 1.0F && mc.player.getItemUseTime() > 1) {
                     event.cancel();
                     this.ticks = 0;
                  }
                  break;
               case "MetaHvH":
                  event.cancel();
            }
         default:
      }
   }

   public static void sendSequencedPacket(SequencedPacketCreator packetCreator) {
      ((IClientPlayerInteractionManagerMixin)mc.interactionManager).invokeSendSequencedPacket(mc.world, packetCreator);
   }

   public static void interactItem(Hand hand) {
      sendSequencedPacket((i) -> new PlayerInteractItemC2SPacket(hand, i, WildClient.INSTANCE.getRotationManager().getYaw(), WildClient.INSTANCE.getRotationManager().getPitch()));
   }

   @Compile
   public void onTick(TickEvent event) {
      if (mc.player.getActiveHand() != Hand.MAIN_HAND && mc.player.getActiveHand() != Hand.OFF_HAND) {
         this.ticks = 0;
      } else {
         ++this.ticks;
      }

   }
}
