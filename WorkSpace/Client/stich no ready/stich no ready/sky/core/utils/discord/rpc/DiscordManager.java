package sky.core.utils.discord.rpc;

import com.adl.nativeprotect.Native;
import com.adl.nativeprotect.User;
import sky.core.utils.discord.rpc.utils.DiscordEventHandlers;
import sky.core.utils.discord.rpc.utils.DiscordRPC;
import sky.core.utils.discord.rpc.utils.DiscordRichPresence;
import sky.core.utils.discord.rpc.utils.RPCButton;

public class DiscordManager {
   private DiscordDaemonThread discordDaemonThread;
   private long applicationId;
   private boolean running;
   private String image;
   private String discord;
   private String telegram;

   @Native
   private void initDefaults() {
      this.discordDaemonThread = new DiscordDaemonThread();
      this.applicationId = 1522260340761427998L;
      this.running = true;
      this.image = "stich";
      this.discord = "https://discord.gg/XB2MNBbnu9";
      this.telegram = "https://t.me/+zzzo73D5QE5hZGNi";
   }

   @Native
   public void init() {
      this.initDefaults();
      DiscordRichPresence.Builder builder = new DiscordRichPresence.Builder();
      DiscordEventHandlers handlers = (new DiscordEventHandlers.Builder()).build();
      DiscordRPC.INSTANCE.Discord_Initialize(String.valueOf(this.applicationId), handlers, true, "");
      builder.setStartTimestamp(System.currentTimeMillis() / 1000L);
      String user = User.getInstance().profile("username");
      String uid = User.getInstance().profile("uid");
      builder.setDetails("User: " + user);
      builder.setState("UID: " + uid);
      builder.setLargeImage(this.image, "https://stichdlc.ru");
      builder.setButtons(RPCButton.create("Download", this.discord), RPCButton.create("News", this.telegram));
      DiscordRPC.INSTANCE.Discord_UpdatePresence(builder.build());
      this.discordDaemonThread.start();
   }

   @Native
   public DiscordManager start() {
      try {
         this.init();
      } catch (Throwable var2) {
         this.running = false;
      }

      return this;
   }

   @Native
   public void stopRPC() {
      try {
         this.running = false;
         DiscordRPC.INSTANCE.Discord_Shutdown();
         if (this.discordDaemonThread != null && this.discordDaemonThread.isAlive()) {
            this.discordDaemonThread.interrupt();
         }
      } catch (Throwable var2) {
      }

   }

   public DiscordDaemonThread getDiscordDaemonThread() {
      return this.discordDaemonThread;
   }

   public long getApplicationId() {
      return this.applicationId;
   }

   public boolean isRunning() {
      return this.running;
   }

   public String getImage() {
      return this.image;
   }

   public String getDiscord() {
      return this.discord;
   }

   public String getTelegram() {
      return this.telegram;
   }

   private class DiscordDaemonThread extends Thread {
      public void run() {
         this.setName("Discord-RPC");

         try {
            while(DiscordManager.this.running) {
               DiscordRPC.INSTANCE.Discord_RunCallbacks();
               sleep(15000L);
            }
         } catch (Throwable var2) {
            DiscordManager.this.running = false;
         }

      }
   }
}
