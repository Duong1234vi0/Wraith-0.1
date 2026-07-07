package unique.core.features.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_2172;
import net.minecraft.class_640;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.features.cmd.Command;
import unique.core.features.cmd.args.FriendArgumentType;
import unique.core.features.cmd.args.PlayerArgumentType;

public class FriendCommand extends Command {
   public FriendCommand() {
      super("friend", "friends");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.then(literal("reset").executes(context -> {
         Managers.FRIEND.clear();
         sendMessage("Friends got reset.");
         return 1;
      }));
      builder.then(literal("add").then(arg("player", PlayerArgumentType.create()).executes(context -> {
         class_640 player = (class_640)context.getArgument("player", class_640.class);
         Managers.FRIEND.addFriend(player.method_2966().getName());
         sendMessage(player.method_2966().getName() + " has been friended");
         return 1;
      })));
      builder.then(literal("remove").then(arg("player", FriendArgumentType.create()).executes(context -> {
         String nickname = (String)context.getArgument("player", String.class);
         Managers.FRIEND.removeFriend(nickname);
         sendMessage(nickname + " has been unfriended");
         return 1;
      })));
      builder.then(literal("is").then(arg("player", PlayerArgumentType.create()).executes(context -> {
         class_640 player = (class_640)context.getArgument("player", class_640.class);
         sendMessage(player.method_2966().getName() + (Managers.FRIEND.isFriend(player.method_2966().getName()) ? " is friended." : " isn't friended."));
         return 1;
      })));
      builder.executes(context -> {
         if (Managers.FRIEND.getFriends().isEmpty()) {
            sendMessage("Friend list empty D:");
         } else {
            StringBuilder f = new StringBuilder("Friends: ");

            for (String friend : Managers.FRIEND.getFriends()) {
               try {
                  f.append(friend).append(", ");
               } catch (Exception var5) {
               }
            }

            sendMessage(f.toString());
         }

         return 1;
      });
   }
}
