package ru.metaculture.profile;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public enum Role {
   DEFAULT,
   USER,
   MEDIA,
   SUPPORT,
   MODERATOR,
   ADMIN,
   OWNER,
    PASTER;

   // $FF: synthetic method
   private static Role[] $values() {
      return new Role[]{DEFAULT, USER, MEDIA, SUPPORT, MODERATOR, ADMIN, OWNER};
   }
}
