package unique.core.utility.interfaces;

import unique.core.features.modules.combat.Aura;

public interface IOtherClientPlayerEntity {
   void resolve(Aura.Resolver var1);

   void releaseResolver();
}
