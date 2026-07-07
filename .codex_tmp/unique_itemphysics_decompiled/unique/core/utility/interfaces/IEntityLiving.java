package unique.core.utility.interfaces;

import java.util.List;
import unique.core.features.modules.combat.Aura;

public interface IEntityLiving {
   double getPrevServerX();

   double getPrevServerY();

   double getPrevServerZ();

   List<Aura.Position> getPositionHistory();
}
