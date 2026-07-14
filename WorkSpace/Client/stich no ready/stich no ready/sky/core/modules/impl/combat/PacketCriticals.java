package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.network.play.client.CUseEntityPacket;
import net.minecraft.network.play.client.CUseEntityPacket.Action;
import sky.core.events.EventPacket;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.utils.math.MathUtil;

public class PacketCriticals extends Module {
   public final ModeSetting mode = new ModeSetting("Режим", "ReallyWorld", new String[]{"ReallyWorld", "Grim 1.17+"});
   public static boolean cancelCrit;

   public PacketCriticals() {
      super("PacketCriticals", "Всегда даёт критический удар", Category.Combat);
      this.addSettings(new Setting[]{this.mode});
   }

   public void onEnable() {
      super.onEnable();
      cancelCrit = false;
   }

   public void onDisable() {
      super.onDisable();
      cancelCrit = false;
   }

   @EventTarget
   public void onPacket(EventPacket e) {
      if (e.isSend()) {
         IPacket var3 = e.getPacket();
         if (var3 instanceof CUseEntityPacket) {
            CUseEntityPacket packet = (CUseEntityPacket)var3;
            if (packet.getAction() == Action.ATTACK) {
               Minecraft var10000 = mc;
               if (Minecraft.player != null && mc.world != null) {
                  var10000 = mc;
                  if (!Minecraft.player.isElytraFlying()) {
                     Entity entity = packet.getEntityFromWorld(mc.world);
                     if (entity != null && !(entity instanceof EnderCrystalEntity) && !cancelCrit) {
                        double y;
                        ClientPlayNetHandler var7;
                        double var8;
                        double var9;
                        Minecraft var10003;
                        Minecraft var10004;
                        Minecraft var10005;
                        if (this.mode.is("ReallyWorld")) {
                           var10000 = mc;
                           if (!Minecraft.player.isInWeb()) {
                              return;
                           }

                           var10000 = mc;
                           if (Minecraft.player.isOnGround()) {
                              return;
                           }

                           var10000 = mc;
                           y = Minecraft.player.getPosY();
                           if (y == (double)((int)y)) {
                              return;
                           }

                           var10000 = mc;
                           Minecraft.player.fallDistance = 0.001F;
                           var10000 = mc;
                           var7 = Minecraft.player.connection;
                           var10003 = mc;
                           var8 = Minecraft.player.getPosX();
                           var10004 = mc;
                           var9 = Minecraft.player.getPosY();
                           var10005 = mc;
                           var9 += (double)(-(Minecraft.player.fallDistance = MathUtil.randomizeFloat(1.0E-7F, 1.0E-6F)));
                           var10005 = mc;
                           var7.sendPacket(new CPlayerPacket.PositionRotationPacket(var8, var9, Minecraft.player.getPosZ(), AttackAura.rotate.x, AttackAura.rotate.y, false));
                        } else if (this.mode.is("Grim 1.17+")) {
                           var10000 = mc;
                           if (Minecraft.player.isOnGround()) {
                              return;
                           }

                           var10000 = mc;
                           y = Minecraft.player.getPosY();
                           if (y == (double)((int)y)) {
                              return;
                           }

                           var10000 = mc;
                           Minecraft.player.fallDistance = 0.001F;
                           var10000 = mc;
                           var7 = Minecraft.player.connection;
                           var10003 = mc;
                           var8 = Minecraft.player.getPosX();
                           var10004 = mc;
                           var9 = Minecraft.player.getPosY();
                           var10005 = mc;
                           var9 += (double)(-(Minecraft.player.fallDistance = MathUtil.randomizeFloat(1.0E-7F, 1.0E-6F)));
                           var10005 = mc;
                           var7.sendPacket(new CPlayerPacket.PositionRotationPacket(var8, var9, Minecraft.player.getPosZ(), AttackAura.rotate.x, AttackAura.rotate.y, false));
                        }

                     }
                  }
               }
            }
         }
      }
   }
}
