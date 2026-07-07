package unique.core.utility.discord;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
import unique.core.utility.discord.callbacks.DisconnectedCallback;
import unique.core.utility.discord.callbacks.ErroredCallback;
import unique.core.utility.discord.callbacks.JoinGameCallback;
import unique.core.utility.discord.callbacks.JoinRequestCallback;
import unique.core.utility.discord.callbacks.ReadyCallback;
import unique.core.utility.discord.callbacks.SpectateGameCallback;

public class DiscordEventHandlers extends Structure {
   public DisconnectedCallback disconnected;
   public JoinRequestCallback joinRequest;
   public SpectateGameCallback spectateGame;
   public ReadyCallback ready;
   public ErroredCallback errored;
   public JoinGameCallback joinGame;

   protected List<String> getFieldOrder() {
      return Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest");
   }
}
