package dev.client.modules.impl.util;

import com.mojang.authlib.GameProfile;
import dev.client.WildClient;
import dev.client.modules.Category;
import dev.client.modules.IDisableable;
import dev.client.modules.IEnableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.impl.player.NameProtect;
import dev.client.util.IUtil;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity.RemovalReason;

@Environment(EnvType.CLIENT)
public class FakePlayer extends Module implements IDisableable, IEnableable, IUtil {
   private OtherClientPlayerEntity fakeModel;

   public FakePlayer() {
      super(new ModuleInfo("FakePlayer", Category.UTIL, "Создает противника, видного только игроку"));
   }

   public void onDisable() {
      mc.world.removeEntity(1448, RemovalReason.DISCARDED);
   }

   public void onEnable() {
      if (mc.world != null && mc.player != null) {
         NameProtect nameProtect = (NameProtect)WildClient.INSTANCE.getModuleManager().getByClass(NameProtect.class);
         this.fakeModel = new OtherClientPlayerEntity(mc.world, new GameProfile(UUID.randomUUID(), nameProtect.replace(mc.player.getNameForScoreboard())));
         this.fakeModel.copyFrom(mc.player);
         this.fakeModel.headYaw = mc.player.headYaw;
         this.fakeModel.bodyYaw = mc.player.bodyYaw;
         this.fakeModel.setId(1448);
         mc.world.addEntity(this.fakeModel);
      }
   }
}
