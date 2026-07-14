package dev.client.modules.impl.player;

import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.interfaces.IReceivePacketable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;

@Environment(EnvType.CLIENT)
public class NoPush extends Module implements IReceivePacketable, IUtil {
   public final MultiBoxSetting options = ((MultiBoxSetting)(new MultiBoxSetting()).name("Options")).booleanSettings(((BooleanSetting)(new BooleanSetting()).name("Water")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Players")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Blocks")).value(false), ((BooleanSetting)(new BooleanSetting()).name("FishingHook")).value(false));

   public NoPush() {
      super(new ModuleInfo("NoPush", Category.PLAYER, "Убирает отталкивание игрока от выбранных объектов"));
      this.addSetting(new Setting[]{this.options});
   }

   public void onReceivePacket(ReceivePacketEvent receivePacketEvent) {
      Packet var4 = receivePacketEvent.getPacket();
      if (var4 instanceof EntityStatusS2CPacket pac) {
         if (pac.getStatus() == 31) {
            Entity var5 = pac.getEntity(mc.world);
            if (var5 instanceof FishingBobberEntity) {
               FishingBobberEntity hook = (FishingBobberEntity)var5;
               if (this.options.getValueByName("FishingHook") && hook.getHookedEntity() == mc.player) {
                  receivePacketEvent.cancel();
               }
            }
         }
      }

   }
}
