package unique.core.injection;

import net.minecraft.class_2561;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_500;
import net.minecraft.class_4185.class_7840;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unique.core.core.manager.IManager;
import unique.core.core.manager.client.ModuleManager;
import unique.core.gui.windows.WindowsScreen;
import unique.core.gui.windows.impl.ConfigWindow;
import unique.core.gui.windows.impl.FriendsWindow;
import unique.core.gui.windows.impl.MacroWindow;
import unique.core.gui.windows.impl.ProxyWindow;
import unique.core.gui.windows.impl.WaypointWindow;

@Mixin({class_500.class})
public abstract class MixinMultiplayerScreen extends class_437 {
   public MixinMultiplayerScreen(class_2561 title) {
      super(title);
   }

   @Inject(
      method = {"init"},
      at = {@At("RETURN")}
   )
   private void initHook(CallbackInfo ci) {
      class_7840 builder = class_4185.method_46430(
            class_2561.method_43470("⚡"),
            button -> IManager.mc
               .method_1507(
                  new WindowsScreen(
                     MacroWindow.get(
                        ModuleManager.windows.macroPos.getValue().getX() * IManager.mc.method_22683().method_4486(),
                        ModuleManager.windows.macroPos.getValue().getY() * IManager.mc.method_22683().method_4502(),
                        ModuleManager.windows.macroPos
                     ),
                     ConfigWindow.get(
                        ModuleManager.windows.configPos.getValue().getX() * IManager.mc.method_22683().method_4486(),
                        ModuleManager.windows.configPos.getValue().getY() * IManager.mc.method_22683().method_4502(),
                        ModuleManager.windows.configPos
                     ),
                     FriendsWindow.get(
                        ModuleManager.windows.friendPos.getValue().getX() * IManager.mc.method_22683().method_4486(),
                        ModuleManager.windows.friendPos.getValue().getY() * IManager.mc.method_22683().method_4502(),
                        ModuleManager.windows.friendPos
                     ),
                     WaypointWindow.get(
                        ModuleManager.windows.waypointPos.getValue().getX() * IManager.mc.method_22683().method_4486(),
                        ModuleManager.windows.waypointPos.getValue().getY() * IManager.mc.method_22683().method_4502(),
                        ModuleManager.windows.waypointPos
                     ),
                     ProxyWindow.get(
                        ModuleManager.windows.proxyPos.getValue().getX() * IManager.mc.method_22683().method_4486(),
                        ModuleManager.windows.proxyPos.getValue().getY() * IManager.mc.method_22683().method_4502(),
                        ModuleManager.windows.proxyPos
                     )
                  )
               )
         )
         .method_46437(60, 20);
      if (!ModuleManager.unHook.isEnabled()) {
         this.method_37063(builder.method_46433(this.field_22789 - 65, this.field_22790 - 25).method_46431());
      }
   }
}
