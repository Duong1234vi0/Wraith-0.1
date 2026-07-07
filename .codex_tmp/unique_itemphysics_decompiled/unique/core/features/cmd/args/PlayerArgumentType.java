package unique.core.features.cmd.args;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_2172;
import net.minecraft.class_2561;
import net.minecraft.class_640;
import unique.core.core.manager.IManager;
import unique.core.features.modules.client.ClientSettings;

public class PlayerArgumentType implements ArgumentType<class_640> {
   private static final Collection<String> EXAMPLES = List.of("pan4ur", "06ED");

   public static PlayerArgumentType create() {
      return new PlayerArgumentType();
   }

   public class_640 parse(StringReader reader) throws CommandSyntaxException {
      String name = reader.readString();
      class_640 player = IManager.mc.method_1562().method_2880().stream().filter(p -> name.equals(p.method_2966().getName())).findFirst().orElse(null);
      if (player == null) {
         throw new DynamicCommandExceptionType(
               nickname -> class_2561.method_43470(ClientSettings.isRu() ? "Игрок " + nickname + " не в сети" : "Player " + nickname + " offline")
            )
            .create(name);
      } else {
         return player;
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      return class_2172.method_9264(IManager.mc.method_1562().method_2880().stream().map(p -> p.method_2966().getName()), builder);
   }

   public Collection<String> getExamples() {
      return EXAMPLES;
   }
}
