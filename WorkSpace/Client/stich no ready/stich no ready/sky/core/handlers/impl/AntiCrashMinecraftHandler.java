package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.network.play.server.SEntityTeleportPacket;
import net.minecraft.network.play.server.SExplosionPacket;
import net.minecraft.network.play.server.SPlayerPositionLookPacket;
import net.minecraft.network.play.server.SSpawnParticlePacket;
import sky.core.events.EventPacket;
import sky.core.utils.Wrapper;

public class AntiCrashMinecraftHandler {
   @EventTarget
   public void onPacket(EventPacket e) {
      Minecraft var10000 = Wrapper.mc;
      if (Minecraft.player != null && Wrapper.mc.world != null) {
         Object p = e.getPacket();
         if (p instanceof SExplosionPacket) {
            SExplosionPacket exp = (SExplosionPacket)p;
            if (Math.abs(exp.getX()) > 1.0E9 || Math.abs(exp.getY()) > 1.0E9 || Math.abs(exp.getZ()) > 1.0E9 || (double)Math.abs(exp.getStrength()) > 1.0E9) {
               e.setCancelled(true);
            }
         } else if (p instanceof SSpawnParticlePacket) {
            SSpawnParticlePacket part = (SSpawnParticlePacket)p;
            if (Math.abs(part.getXCoordinate()) > 1.0E9 || Math.abs(part.getYCoordinate()) > 1.0E9 || Math.abs(part.getZCoordinate()) > 1.0E9 || (double)Math.abs(part.getXOffset()) > 1.0E9 || (double)Math.abs(part.getYOffset()) > 1.0E9 || (double)Math.abs(part.getZOffset()) > 1.0E9 || (double)Math.abs(part.getParticleSpeed()) > 1.0E9) {
               e.setCancelled(true);
            }
         } else if (p instanceof SPlayerPositionLookPacket) {
            SPlayerPositionLookPacket pos = (SPlayerPositionLookPacket)p;
            if (Math.abs(pos.getX()) > 1.0E9 || Math.abs(pos.getY()) > 1.0E9 || Math.abs(pos.getZ()) > 1.0E9 || (double)Math.abs(pos.getYaw()) > 1.0E9 || (double)Math.abs(pos.getPitch()) > 1.0E9) {
               e.setCancelled(true);
            }
         } else if (p instanceof SChangeGameStatePacket) {
            SChangeGameStatePacket change = (SChangeGameStatePacket)p;
            int id = change.func_241776_b_().field_241778_b_;
            if (id == 5) {
               e.setCancelled(true);
            }
         } else if (p instanceof SEntityTeleportPacket) {
            SEntityTeleportPacket entity = (SEntityTeleportPacket)p;
            if (Math.abs(entity.getX()) > 1000000.0 || Math.abs(entity.getY()) > 1000000.0 || Math.abs(entity.getZ()) > 1000000.0) {
               e.setCancelled(true);
            }
         }

      }
   }
}
