package unique.core.features.modules.render;

import java.awt.Color;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2761;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.setting.impl.ColorSetting;

public class WorldTweaks extends Module {
   public static final Setting<BooleanSettingGroup> fogModify = new Setting<>("FogModify", new BooleanSettingGroup(true));
   public static final Setting<Integer> fogStart = new Setting<>("FogStart", 0, 0, 256).addToGroup(fogModify);
   public static final Setting<Integer> fogEnd = new Setting<>("FogEnd", 64, 10, 256).addToGroup(fogModify);
   public static final Setting<ColorSetting> fogColor = new Setting<>("FogColor", new ColorSetting(new Color(11075839))).addToGroup(fogModify);
   public final Setting<Boolean> ctime = new Setting<>("ChangeTime", false);
   public final Setting<Integer> ctimeVal = new Setting<>("Time", 21, 0, 23);
   long oldTime;

   public WorldTweaks() {
      super("WorldTweaks", Module.Category.RENDER);
   }

   @Override
   public void onEnable() {
      this.oldTime = mc.field_1687.method_8510();
   }

   @Override
   public void onDisable() {
      mc.field_1687.method_8435(this.oldTime);
   }

   @EventHandler
   private void onPacketReceive(PacketEvent.Receive event) {
      if (event.getPacket() instanceof class_2761 && this.ctime.getValue()) {
         this.oldTime = ((class_2761)event.getPacket()).method_11871();
         event.cancel();
      }
   }

   @Override
   public void onUpdate() {
      if (this.ctime.getValue()) {
         mc.field_1687.method_8435(this.ctimeVal.getValue() * 1000);
      }
   }
}
