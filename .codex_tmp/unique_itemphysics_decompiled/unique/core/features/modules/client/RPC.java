package unique.core.features.modules.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
import net.minecraft.class_422;
import net.minecraft.class_442;
import net.minecraft.class_500;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.Timer;
import unique.core.utility.discord.DiscordEventHandlers;
import unique.core.utility.discord.DiscordRPC;
import unique.core.utility.discord.DiscordRichPresence;

public final class RPC extends Module {
   private static final DiscordRPC rpc = DiscordRPC.INSTANCE;
   public static Setting<RPC.Mode> mode = new Setting<>("Picture", RPC.Mode.CosmicDLC);
   public static Setting<Boolean> showIP = new Setting<>("ShowIP", true);
   public static Setting<RPC.sMode> smode = new Setting<>("StateMode", RPC.sMode.Stats);
   public static Setting<String> state = new Setting<>("State", "CosmicDLC 1.21");
   public static Setting<Boolean> nickname = new Setting<>("Nickname", true);
   public static DiscordRichPresence presence = new DiscordRichPresence();
   public static boolean started;
   static String String1 = "none";
   private final Timer timer_delay = new Timer();
   private static Thread thread;
   String slov;
   String[] rpc_perebor_en = new String[]{"Parkour", "Reporting Cheaters", "Touching Grass", "Asks How To Bind", "Reporting Bugs", "Watching Kilab"};
   String[] rpc_perebor_ru = new String[]{"Паркурит", "Репортит читеров", "Трогает траву", "Спрашивает как забиндить", "Репортит баги", "Смотрит Флюгера"};
   int randomInt;

   public RPC() {
      super("DiscordRPC", Module.Category.CLIENT);
   }

   public static void readFile() {
      try {
         File file = new File("ThunderHackRecode/misc/RPC.txt");
         if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
               while (reader.ready()) {
                  String1 = reader.readLine();
               }
            }
         }
      } catch (Exception var6) {
      }
   }

   public static void WriteFile(String url1, String url2) {
      File file = new File("ThunderHackRecode/misc/RPC.txt");

      try {
         file.createNewFile();

         try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(url1 + "SEPARATOR" + url2 + "\n");
         } catch (Exception var8) {
         }
      } catch (Exception var9) {
      }
   }

   @Override
   public void onDisable() {
      started = false;
      if (thread != null && !thread.isInterrupted()) {
         thread.interrupt();
      }

      rpc.Discord_Shutdown();
   }

   @Override
   public void onUpdate() {
      this.startRpc();
   }

   public void startRpc() {
      if (!this.isDisabled()) {
         if (!started) {
            started = true;
            DiscordEventHandlers handlers = new DiscordEventHandlers();
            rpc.Discord_Initialize("1447947000699162775", handlers, true, "");
            presence.startTimestamp = System.currentTimeMillis() / 1000L;
            presence.largeImageText = "Verison1.7b2407 [" + UniqueCore.GITHUB_HASH + "]";
            rpc.Discord_UpdatePresence(presence);
            thread = new Thread(() -> {
               while (!Thread.currentThread().isInterrupted()) {
                  rpc.Discord_RunCallbacks();
                  presence.details = this.getDetails();
                  switch ((RPC.sMode)smode.getValue()) {
                     case Custom:
                        presence.state = state.getValue();
                        break;
                     case Stats:
                        presence.state = "Hacks: " + Managers.MODULE.getEnabledModules().size() + " / " + Managers.MODULE.modules.size();
                        break;
                     case Version:
                        presence.state = "XD1.7b2407 For Mc 1.21";
                  }

                  if (nickname.getValue()) {
                     presence.smallImageText = "NickName - " + mc.method_1548().method_1676();
                     presence.smallImageKey = "https://minotar.net/helm/" + mc.method_1548().method_1676() + "/100.png";
                  } else {
                     presence.smallImageText = "";
                     presence.smallImageKey = "";
                  }

                  presence.button_label_1 = "Download";
                  presence.button_url_1 = "https://discord.gg/AEyetataPB";
                  switch ((RPC.Mode)mode.getValue()) {
                     case Custom:
                        readFile();
                        presence.largeImageKey = String1.split("SEPARATOR")[0];
                        if (!Objects.equals(String1.split("SEPARATOR")[1], "none")) {
                           presence.smallImageKey = String1.split("SEPARATOR")[1];
                        }
                        break;
                     case MegaCute:
                        presence.largeImageKey = "https://media.tenor.com/89QSIUPhqBkAAAAM/anime-girl-happy.gif";
                        break;
                     case CosmicDLC:
                        presence.largeImageKey = "https://media.tenor.com/ZWop2tkzsakAAAAM/magirevo-anis-shy.gif";
                  }

                  rpc.Discord_UpdatePresence(presence);

                  try {
                     Thread.sleep(2000L);
                  } catch (InterruptedException var2) {
                  }
               }
            }, "TH-RPC-Handler");
            thread.start();
         }
      }
   }

   private String getDetails() {
      String result = "";
      if (!(mc.field_1755 instanceof class_500) && !(mc.field_1755 instanceof class_422) && !(mc.field_1755 instanceof class_442)) {
         if (mc.method_1558() != null) {
            result = ClientSettings.isRu()
               ? (showIP.getValue() ? "Играет на " + mc.method_1558().field_3761 : "Играет на сервере")
               : (showIP.getValue() ? "Playing On " + mc.method_1558().field_3761 : "CosmicDLC Client");
         } else if (mc.method_1542()) {
            result = ClientSettings.isRu() ? "CosmicDLC" : "CosmicDLC -> AkiraEternal";
         }
      } else {
         if (this.timer_delay.passedMs(60000L)) {
            this.randomInt = (int)(Math.random() * 6.0 + 0.0);
            this.slov = ClientSettings.isRu() ? this.rpc_perebor_ru[this.randomInt] : this.rpc_perebor_en[this.randomInt];
            this.timer_delay.reset();
         }

         result = this.slov;
      }

      return result;
   }

   public static enum Mode {
      Custom,
      MegaCute,
      CosmicDLC;
   }

   public static enum sMode {
      Custom,
      Stats,
      Version;
   }
}
