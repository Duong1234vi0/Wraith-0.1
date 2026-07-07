package unique.core.features.modules.client;

import unique.core.features.modules.Module;
import unique.core.gui.windows.WindowsScreen;
import unique.core.gui.windows.impl.ConfigWindow;
import unique.core.gui.windows.impl.FriendsWindow;
import unique.core.gui.windows.impl.MacroWindow;
import unique.core.gui.windows.impl.ProxyWindow;
import unique.core.gui.windows.impl.WaypointWindow;
import unique.core.setting.Setting;
import unique.core.setting.impl.PositionSetting;

public class Windows extends Module {
   public final Setting<PositionSetting> macroPos = new Setting<>("macroPos", new PositionSetting(0.3F, 0.3F), v -> false);
   public final Setting<PositionSetting> configPos = new Setting<>("configPos", new PositionSetting(0.35F, 0.35F), v -> false);
   public final Setting<PositionSetting> friendPos = new Setting<>("friendPos", new PositionSetting(0.4F, 0.4F), v -> false);
   public final Setting<PositionSetting> waypointPos = new Setting<>("waypointPos", new PositionSetting(0.45F, 0.45F), v -> false);
   public final Setting<PositionSetting> proxyPos = new Setting<>("proxyPos", new PositionSetting(0.5F, 0.5F), v -> false);

   public Windows() {
      super("Windows", Module.Category.CLIENT);
   }

   @Override
   public void onEnable() {
      mc.method_1507(
         new WindowsScreen(
            MacroWindow.get(
               this.macroPos.getValue().getX() * mc.method_22683().method_4486(),
               this.macroPos.getValue().getY() * mc.method_22683().method_4502(),
               this.macroPos
            ),
            ConfigWindow.get(
               this.configPos.getValue().getX() * mc.method_22683().method_4486(),
               this.configPos.getValue().getY() * mc.method_22683().method_4502(),
               this.configPos
            ),
            FriendsWindow.get(
               this.friendPos.getValue().getX() * mc.method_22683().method_4486(),
               this.friendPos.getValue().getY() * mc.method_22683().method_4502(),
               this.friendPos
            ),
            WaypointWindow.get(
               this.waypointPos.getValue().getX() * mc.method_22683().method_4486(),
               this.waypointPos.getValue().getY() * mc.method_22683().method_4502(),
               this.waypointPos
            ),
            ProxyWindow.get(
               this.proxyPos.getValue().getX() * mc.method_22683().method_4486(),
               this.proxyPos.getValue().getY() * mc.method_22683().method_4502(),
               this.proxyPos
            )
         )
      );
      this.disable();
   }
}
