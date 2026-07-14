package dev.client.modules.impl.util;

import dev.client.WildClient;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class AutoLeave extends Module implements ITickable, IUtil {
   private final FloatSetting distanceSetting = ((FloatSetting)(new FloatSetting()).name("Distance")).minValue(5.0F).maxValue(40.0F).incriment(1.0F).value(10.0F);

   public AutoLeave() {
      super(new ModuleInfo("AutoLeave", Category.UTIL, "Выходит с сервера при появлении противника рядом с игроком"));
   }

   public void leave(Text text) {
      mc.getNetworkHandler().sendChatCommand("hub");
      this.setEnabled(false);
   }

   @Compile
   public void onTick(TickEvent event) {
      mc.world.getPlayers().stream().filter((p) -> mc.player.distanceTo(p) < this.distanceSetting.getValue() && mc.player != p && !WildClient.INSTANCE.getFriendManager().isFriend(p.getNameForScoreboard())).findFirst().ifPresent((p) -> this.leave(p.getName().copy().append(" - Появился рядом " + mc.player.distanceTo(p) + "м")));
   }
}
