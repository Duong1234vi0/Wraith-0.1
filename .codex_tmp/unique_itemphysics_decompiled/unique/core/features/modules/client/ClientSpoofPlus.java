package unique.core.features.modules.client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_2817;
import net.minecraft.class_2889;
import net.minecraft.class_8709;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class ClientSpoofPlus extends Module {
   private final Setting<ClientSpoofPlus.Mode> mode = new Setting<>("Mode", ClientSpoofPlus.Mode.Vanilla);
   private final Setting<String> custom = new Setting<>("Custom", "vanilla", v -> this.mode.getValue() == ClientSpoofPlus.Mode.Custom);
   private final Setting<ClientSpoofPlus.ProtocolVersion> protocolVersion = new Setting<>("Protocol", ClientSpoofPlus.ProtocolVersion.V1_20_1);
   private final Setting<String> customProtocol = new Setting<>(
      "CustomProtocol", "763", v -> this.protocolVersion.getValue() == ClientSpoofPlus.ProtocolVersion.Custom
   );
   private final Setting<Boolean> autoDetect = new Setting<>("AutoDetect", true);
   private final List<String> spoofHistory = new ArrayList<>();

   public ClientSpoofPlus() {
      super("ClientSpoof+", Module.Category.CLIENT);
   }

   @Override
   public void onEnable() {
      if (this.autoDetect.getValue()) {
         this.detectServerAndSetSpoof();
      }
   }

   private void detectServerAndSetSpoof() {
      if (mc.method_1558() != null) {
         String ip = mc.method_1558().field_3761.toLowerCase();
         if (ip.contains("hypixel")) {
            this.mode.setValue(ClientSpoofPlus.Mode.Lunar1_20_1);
            this.protocolVersion.setValue(ClientSpoofPlus.ProtocolVersion.V1_8_9);
         } else if (ip.contains("mineplex")) {
            this.mode.setValue(ClientSpoofPlus.Mode.CheatBreaker);
            this.protocolVersion.setValue(ClientSpoofPlus.ProtocolVersion.V1_7_10);
         } else if (ip.contains("cubecraft")) {
            this.mode.setValue(ClientSpoofPlus.Mode.Lunar1_20_1);
            this.protocolVersion.setValue(ClientSpoofPlus.ProtocolVersion.V1_20_1);
         } else if (!ip.contains("2b2t") && !ip.contains("constantiam")) {
            this.mode.setValue(ClientSpoofPlus.Mode.Vanilla);
            this.protocolVersion.setValue(ClientSpoofPlus.ProtocolVersion.V1_20_1);
         } else {
            this.mode.setValue(ClientSpoofPlus.Mode.Vanilla);
            this.protocolVersion.setValue(ClientSpoofPlus.ProtocolVersion.V1_12_2);
         }
      }
   }

   public String getClientName() {
      return switch ((ClientSpoofPlus.Mode)this.mode.getValue()) {
         case Vanilla -> "vanilla";
         case Forge -> "forge";
         case Lunar1_20_4 -> "lunarclient:1.20.4";
         case Lunar1_20_1 -> "lunarclient:1.20.1";
         case LabyMod -> "labymod:1.20.1";
         case Feather -> "feather:1.20.1";
         case CheatBreaker -> "CheatBreaker";
         case Sigma -> "Sigma";
         case Custom -> (String)this.custom.getValue();
         case Null -> "unknown";
      };
   }

   public int getSpoofedProtocol() {
      if (this.protocolVersion.getValue() == ClientSpoofPlus.ProtocolVersion.Custom) {
         try {
            return Integer.parseInt(this.customProtocol.getValue());
         } catch (NumberFormatException var2) {
            return 763;
         }
      } else {
         return this.protocolVersion.getValue().getProtocol();
      }
   }

   @EventHandler
   public void onPacketSend(PacketEvent.Send event) {
      if (event.getPacket() instanceof class_2817 packet && packet.comp_1647() instanceof class_8709) {
         event.cancel();
         mc.field_1724.field_3944.method_52787(new class_2817(new class_8709(this.getClientName())));
         this.spoofHistory.add(this.getClientName() + "@" + this.protocolVersion.getValue().getVersion());
      }
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive event) {
   }

   public void modifyHandshake(class_2889 packet) {
      if (packet != null) {
         try {
            Field brandField = class_2889.class.getDeclaredField("brand");
            brandField.setAccessible(true);
            brandField.set(packet, this.getClientName());
            Field protocolField = class_2889.class.getDeclaredField("protocolVersion");
            protocolField.setAccessible(true);
            protocolField.set(packet, this.getSpoofedProtocol());
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }
   }

   public List<String> getSpoofHistory() {
      return this.spoofHistory;
   }

   public static enum Mode {
      Vanilla,
      Forge,
      Lunar1_20_4,
      Lunar1_20_1,
      LabyMod,
      Feather,
      CheatBreaker,
      Sigma,
      Custom,
      Null;
   }

   public static enum ProtocolVersion {
      V1_21_4(769, "1.21.4"),
      V1_21_2(768, "1.21.2"),
      V1_21(767, "1.21"),
      V1_20_6(766, "1.20.6"),
      V1_20_5(766, "1.20.5"),
      V1_20_4(765, "1.20.4"),
      V1_20_3(764, "1.20.3"),
      V1_20_2(764, "1.20.2"),
      V1_20_1(763, "1.20.1"),
      V1_20(763, "1.20"),
      V1_19_4(762, "1.19.4"),
      V1_19_3(761, "1.19.3"),
      V1_19_2(760, "1.19.2"),
      V1_19_1(760, "1.19.1"),
      V1_19(759, "1.19"),
      V1_18_2(758, "1.18.2"),
      V1_18_1(757, "1.18.1"),
      V1_18(757, "1.18"),
      V1_17_1(756, "1.17.1"),
      V1_17(755, "1.17"),
      V1_16_5(754, "1.16.5"),
      V1_16_4(754, "1.16.4"),
      V1_16_3(753, "1.16.3"),
      V1_16_2(751, "1.16.2"),
      V1_16_1(736, "1.16.1"),
      V1_16(735, "1.16"),
      V1_15_2(578, "1.15.2"),
      V1_15_1(575, "1.15.1"),
      V1_15(573, "1.15"),
      V1_14_4(498, "1.14.4"),
      V1_14_3(490, "1.14.3"),
      V1_14_2(485, "1.14.2"),
      V1_14_1(480, "1.14.1"),
      V1_14(477, "1.14"),
      V1_13_2(404, "1.13.2"),
      V1_13_1(401, "1.13.1"),
      V1_13(393, "1.13"),
      V1_12_2(340, "1.12.2"),
      V1_12_1(338, "1.12.1"),
      V1_12(335, "1.12"),
      V1_11_2(316, "1.11.2"),
      V1_11_1(316, "1.11.1"),
      V1_11(315, "1.11"),
      V1_10_2(210, "1.10.2"),
      V1_10_1(210, "1.10.1"),
      V1_10(210, "1.10"),
      V1_9_4(110, "1.9.4"),
      V1_9_3(110, "1.9.3"),
      V1_9_2(109, "1.9.2"),
      V1_9_1(108, "1.9.1"),
      V1_9(107, "1.9"),
      V1_8_9(47, "1.8.9"),
      V1_8_8(47, "1.8.8"),
      V1_8_7(47, "1.8.7"),
      V1_8_6(47, "1.8.6"),
      V1_8_5(47, "1.8.5"),
      V1_8_4(47, "1.8.4"),
      V1_8_3(47, "1.8.3"),
      V1_8_2(47, "1.8.2"),
      V1_8_1(47, "1.8.1"),
      V1_8(47, "1.8"),
      V1_7_10(5, "1.7.10"),
      V1_7_9(5, "1.7.9"),
      V1_7_8(5, "1.7.8"),
      V1_7_7(5, "1.7.7"),
      V1_7_6(5, "1.7.6"),
      V1_7_5(4, "1.7.5"),
      V1_7_4(4, "1.7.4"),
      V1_7_2(4, "1.7.2"),
      Custom(-1, "Custom");

      private final int protocol;
      private final String version;

      private ProtocolVersion(int protocol, String version) {
         this.protocol = protocol;
         this.version = version;
      }

      public int getProtocol() {
         return this.protocol;
      }

      public String getVersion() {
         return this.version;
      }
   }
}
