package unique.core.features.cmd.args;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_1792;
import net.minecraft.class_2172;
import net.minecraft.class_2248;
import net.minecraft.class_2561;
import net.minecraft.class_7923;
import org.jetbrains.annotations.NotNull;
import unique.core.features.modules.client.ClientSettings;

public class ChestStealerArgumentType implements ArgumentType<String> {
   private static final List<String> EXAMPLES = getRegistered().stream().limit(5L).toList();

   public static ChestStealerArgumentType create() {
      return new ChestStealerArgumentType();
   }

   public String parse(@NotNull StringReader reader) throws CommandSyntaxException {
      String blockName = reader.readString();
      if (!getRegistered().contains(blockName)) {
         throw new DynamicCommandExceptionType(name -> class_2561.method_43470(ClientSettings.isRu() ? "Такого предмета нет!" : "There is no such item!"))
            .create(blockName);
      } else {
         return blockName;
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      return class_2172.method_9265(getRegistered(), builder);
   }

   public Collection<String> getExamples() {
      return EXAMPLES;
   }

   @NotNull
   private static List<String> getRegistered() {
      List<String> result = new ArrayList<>();

      for (class_2248 block : class_7923.field_41175) {
         result.add(block.method_9539().replace("block.minecraft.", ""));
      }

      for (class_1792 item : class_7923.field_41178) {
         result.add(item.method_7876().replace("item.minecraft.", ""));
      }

      return result;
   }
}
