package unique.core.utility.discord.callbacks;

import com.sun.jna.Callback;
import unique.core.utility.discord.DiscordUser;

public interface JoinRequestCallback extends Callback {
   void apply(DiscordUser var1);
}
