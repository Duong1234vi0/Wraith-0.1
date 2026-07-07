package unique.core.injection;

import com.mojang.authlib.GameProfile;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_640;
import net.minecraft.class_742;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.features.modules.client.Capes;

@Mixin({class_640.class})
public class MixinPlayerListEntry {
   @Unique
   private boolean loadedCapeTexture;
   @Unique
   private class_2960 customCapeTexture;
   @Unique
   private String playerName;
   @Unique
   private static final class_2960 STARCAPE = class_2960.method_60655("unique", "textures/capes/starcape.png");

   @Inject(
      method = {"<init>(Lcom/mojang/authlib/GameProfile;Z)V"},
      at = {@At("TAIL")}
   )
   private void initHook(GameProfile profile, boolean secureChatEnforced, CallbackInfo ci) {
      this.playerName = profile.getName();
      this.getTexture(profile);
   }

   @Inject(
      method = {"getSkinTextures"},
      at = {@At("TAIL")},
      cancellable = true
   )
   private void getCapeTexture(CallbackInfoReturnable<class_8685> cir) {
      this.ensureCape();
      if (this.customCapeTexture != null) {
         class_8685 prev = (class_8685)cir.getReturnValue();
         class_8685 newTextures = new class_8685(
            prev.comp_1626(), prev.comp_1911(), this.customCapeTexture, this.customCapeTexture, prev.comp_1629(), prev.comp_1630()
         );
         cir.setReturnValue(newTextures);
      }
   }

   @Unique
   private void getTexture(GameProfile profile) {
      if (!this.loadedCapeTexture) {
         this.loadedCapeTexture = true;
         class_310 mc = class_310.method_1551();
         String name = profile.getName();
         Capes capesModule = Managers.MODULE.getModuleByClass(Capes.class);
         if (capesModule != null && capesModule.isEnabled()) {
            class_742 player = null;
            if (mc.field_1724 != null && mc.field_1687 != null) {
               for (class_742 p : mc.field_1687.method_18456()) {
                  if (p.method_5477().getString().equalsIgnoreCase(name)) {
                     player = p;
                     break;
                  }
               }
            }

            if (player != null) {
               class_2960 moduleCape = capesModule.getCapeTexture(player);
               if (moduleCape != null) {
                  this.customCapeTexture = moduleCape;
                  return;
               }
            }
         }

         if (this.isStarcapeTarget(name)) {
            this.customCapeTexture = STARCAPE;
         }
      }
   }

   @Unique
   private void ensureCape() {
      if (this.playerName != null && !this.playerName.isEmpty()) {
         Capes capesModule = Managers.MODULE.getModuleByClass(Capes.class);
         class_310 mc = class_310.method_1551();
         class_742 player = null;
         if (mc.field_1724 != null && mc.field_1687 != null) {
            for (class_742 p : mc.field_1687.method_18456()) {
               if (p.method_5477().getString().equalsIgnoreCase(this.playerName)) {
                  player = p;
                  break;
               }
            }
         }

         if (capesModule != null && capesModule.isEnabled() && capesModule.capeType.getValue() != Capes.CapeType.OFF) {
            if (player != null) {
               if (capesModule.onlySelf.getValue() && player != mc.field_1724 && (!capesModule.friends.getValue() || !Managers.FRIEND.isFriend(player))) {
                  this.customCapeTexture = null;
                  return;
               }

               class_2960 moduleCape = capesModule.getCapeTexture(player);
               this.customCapeTexture = moduleCape;
            }

            if (this.customCapeTexture == null && this.isStarcapeTarget(this.playerName)) {
               this.customCapeTexture = STARCAPE;
            }
         } else {
            this.customCapeTexture = null;
         }
      }
   }

   @Unique
   private boolean isStarcapeTarget(String name) {
      if (name != null && !name.isEmpty()) {
         if (UniqueCore.mc.field_1724 != null) {
            String selfName = UniqueCore.mc.field_1724.method_5477().getString();
            if (selfName.equalsIgnoreCase(name)) {
               return true;
            }
         }

         return Managers.FRIEND.isFriend(name);
      } else {
         return false;
      }
   }
}
