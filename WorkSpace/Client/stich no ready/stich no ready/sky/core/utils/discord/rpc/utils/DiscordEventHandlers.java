package sky.core.utils.discord.rpc.utils;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
import sky.core.utils.discord.rpc.callbacks.DisconnectedCallback;
import sky.core.utils.discord.rpc.callbacks.ErroredCallback;
import sky.core.utils.discord.rpc.callbacks.JoinGameCallback;
import sky.core.utils.discord.rpc.callbacks.JoinRequestCallback;
import sky.core.utils.discord.rpc.callbacks.ReadyCallback;
import sky.core.utils.discord.rpc.callbacks.SpectateGameCallback;

public class DiscordEventHandlers extends Structure {
   public DisconnectedCallback disconnected;
   public JoinRequestCallback joinRequest;
   public SpectateGameCallback spectateGame;
   public ReadyCallback ready;
   public ErroredCallback errored;
   public JoinGameCallback joinGame;

   protected List getFieldOrder() {
      return Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest");
   }

   public static class Builder {
      private final DiscordEventHandlers handlers = new DiscordEventHandlers();

      public DiscordEventHandlers build() {
         return this.handlers;
      }
   }
}
