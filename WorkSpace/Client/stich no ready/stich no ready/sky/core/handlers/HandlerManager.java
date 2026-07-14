package sky.core.handlers;

import com.darkmagician6.eventapi.EventManager;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import other.party.PartyHandler;
import sky.core.handlers.impl.AlertHandler;
import sky.core.handlers.impl.AntiCrashMinecraftHandler;
import sky.core.handlers.impl.GpsRenderHandler;
import sky.core.handlers.impl.HolyWorldJoinHandler;
import sky.core.handlers.impl.InventoryHandler;
import sky.core.handlers.impl.MacroHandler;
import sky.core.handlers.impl.PickItemFixHandler;
import sky.core.handlers.impl.ReallyWorldJoinHandler;
import sky.core.handlers.impl.RotationHandler;
import sky.core.handlers.impl.StaffHandler;
import sky.core.handlers.impl.TPSHandler;
import sky.core.handlers.impl.TargetESPHandler;
import sky.core.handlers.impl.WaypointRenderHandler;
import sky.core.utils.managers.impl.notificationmanager.NotificationManager;

public class HandlerManager {
   private final List handlers = new CopyOnWriteArrayList();

   public void init() {
      this.add(new TargetESPHandler());
      this.add(new InventoryHandler());
      this.add(new RotationHandler());
      this.add(new NotificationManager());
      this.add(new TPSHandler());
      this.add(new StaffHandler());
      this.add(new MacroHandler());
      this.add(new AlertHandler());
      this.add(new PartyHandler());
      this.add(new ReallyWorldJoinHandler());
      this.add(new GpsRenderHandler());
      this.add(new WaypointRenderHandler());
      this.add(new AntiCrashMinecraftHandler());
      this.add(new PickItemFixHandler());
      this.add(new HolyWorldJoinHandler());
   }

   public void add(Object handler) {
      this.handlers.add(handler);
      EventManager.register(handler);
   }
}
