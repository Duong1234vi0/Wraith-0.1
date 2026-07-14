package sky.core.cmd.impl;

import com.adl.nativeprotect.Native;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.ResourceLocationArgument;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import sky.core.SkyCore;
import sky.core.cmd.Command;
import sky.core.utils.Wrapper;
import sky.core.utils.managers.impl.BlockESPManager;
import sky.core.utils.misc.ChatUtil;

public class BlockESPCommand extends Command implements ArgumentType, Wrapper {
   private static final Map COLOR_NAMES = Map.of("white", 16777215, "red", 16711680, "blue", 255, "green", 65280, "yellow", 16776960, "cyan", 65535, "magenta", 16711935, "black", 0, "gray", 8421504);
   private static final String[] SHULKER_TYPES = new String[]{"white_shulker_box", "orange_shulker_box", "magenta_shulker_box", "light_blue_shulker_box", "yellow_shulker_box", "lime_shulker_box", "pink_shulker_box", "gray_shulker_box", "light_gray_shulker_box", "cyan_shulker_box", "purple_shulker_box", "blue_shulker_box", "brown_shulker_box", "green_shulker_box", "red_shulker_box", "black_shulker_box"};

   public BlockESPCommand() {
      super("blockesp");
   }

   @Native
   public void run(LiteralArgumentBuilder builder) {
      builder.executes((x$0) -> {
         return this.invalidArgs(x$0);
      });
      builder.then(((LiteralArgumentBuilder)literal("add").then(literal("shulkers").then(args("color", this).suggests(this::listColorSuggestions).executes(this::handleAddShulkers)))).then(args("block", ResourceLocationArgument.resourceLocation()).suggests(this::suggestBlocks).then(args("color", this).suggests(this::listColorSuggestions).executes(this::handleAddBlock))));
      builder.then(literal("remove").then(args("block", ResourceLocationArgument.resourceLocation()).suggests(this::suggestAddedBlocks).executes(this::handleRemove)));
      builder.then(literal("clear").executes((ctx) -> {
         this.getManager().clear();
         IFormattableTextComponent message = new StringTextComponent("Список блоков очищен!");
         message.setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
         ChatUtil.addText(message);
         return 1;
      }));
      builder.then(literal("list").executes((ctx) -> {
         List list = this.getManager().getBlocks();
         StringTextComponent headerMessage;
         if (list.isEmpty()) {
            headerMessage = new StringTextComponent("Список пуст!");
            headerMessage.setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(headerMessage);
            return 1;
         } else {
            headerMessage = new StringTextComponent("Список блоков:");
            headerMessage.setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(headerMessage);
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
               BlockESPManager.BlockEntry entry = (BlockESPManager.BlockEntry)var4.next();
               IFormattableTextComponent blockMessage = new StringTextComponent(entry.getBlock());
               blockMessage.setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE));
               ChatUtil.addText(blockMessage);
            }

            IFormattableTextComponent totalMessage = new StringTextComponent("Всего: " + list.size());
            totalMessage.setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY));
            ChatUtil.addText(totalMessage);
            return 1;
         }
      }));
   }

   @Native
   private int handleAddBlock(CommandContext ctx) {
      ResourceLocation id = (ResourceLocation)ctx.getArgument("block", ResourceLocation.class);
      String colorArg = (String)ctx.getArgument("color", String.class);
      int color = this.parseColor(colorArg);
      this.getManager().addBlock(id.toString(), color);
      IFormattableTextComponent message = new StringTextComponent("");
      message.append((new StringTextComponent("Блок с именем '")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
      message.append((new StringTextComponent(id.toString())).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE)));
      message.append((new StringTextComponent("' добавлен!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
      ChatUtil.addText(message);
      return 1;
   }

   @Native
   private int handleAddShulkers(CommandContext ctx) {
      String colorArg = (String)ctx.getArgument("color", String.class);
      int color = this.parseColor(colorArg);
      String[] var4 = SHULKER_TYPES;
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String type = var4[var6];
         this.getManager().addBlock(type, color);
      }

      IFormattableTextComponent message = new StringTextComponent("");
      message.append((new StringTextComponent("Все ")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
      message.append((new StringTextComponent("шалкеры")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE)));
      message.append((new StringTextComponent(" добавлены!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
      ChatUtil.addText(message);
      return 1;
   }

   @Native
   private int handleRemove(CommandContext ctx) {
      ResourceLocation id = (ResourceLocation)ctx.getArgument("block", ResourceLocation.class);
      boolean removed = this.getManager().removeBlock(id.toString());
      if (removed) {
         IFormattableTextComponent message = new StringTextComponent("");
         message.append((new StringTextComponent("Блок с именем '")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
         message.append((new StringTextComponent(id.toString())).setStyle(Style.EMPTY.applyFormatting(TextFormatting.WHITE)));
         message.append((new StringTextComponent("' удалён!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
         ChatUtil.addText(message);
      } else {
         ChatUtil.addText("BlockESP entry not found: " + String.valueOf(id));
      }

      return 1;
   }

   @Native
   private CompletableFuture listColorSuggestions(CommandContext ctx, SuggestionsBuilder builder) {
      return ISuggestionProvider.suggest(COLOR_NAMES.keySet(), builder);
   }

   @Native
   private CompletableFuture suggestBlocks(CommandContext ctx, SuggestionsBuilder builder) {
      List suggestions = (List)Stream.concat(Registry.BLOCK.keySet().stream().map(ResourceLocation::toString), Stream.of("shulkers")).collect(Collectors.toList());
      return ISuggestionProvider.suggest(suggestions, builder);
   }

   @Native
   private CompletableFuture suggestAddedBlocks(CommandContext ctx, SuggestionsBuilder builder) {
      List suggestions = (List)this.getManager().getBlocks().stream().map(BlockESPManager.BlockEntry::getBlock).collect(Collectors.toList());
      return ISuggestionProvider.suggest(suggestions, builder);
   }

   @Native
   private int parseColor(String input) {
      if (input.startsWith("#")) {
         try {
            return Integer.parseInt(input.substring(1), 16);
         } catch (NumberFormatException var3) {
            return (Integer)COLOR_NAMES.getOrDefault(input.toLowerCase(), 16777215);
         }
      } else {
         return (Integer)COLOR_NAMES.getOrDefault(input.toLowerCase(), 16777215);
      }
   }

   @Native
   public String parse(StringReader reader) throws CommandSyntaxException {
      return reader.readString();
   }

   private BlockESPManager getManager() {
      return SkyCore.getInstance().getBlockESPManager();
   }
}
