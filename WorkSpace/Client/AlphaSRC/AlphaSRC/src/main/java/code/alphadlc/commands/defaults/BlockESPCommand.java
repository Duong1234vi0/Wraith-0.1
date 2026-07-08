/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.AlphaDLC;
import code.alphadlc.features.impl.render.BlockESP;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_7923;

public class BlockESPCommand
extends Command {
    public BlockESPCommand() {
        super("blockesp", "\u0423\u043f\u0440\u0430\u0432\u043b\u044f\u0435\u0442 \u0444\u0443\u043d\u043a\u0446\u0438\u0435\u0439 Block ESP", "blockesp");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String subCommand;
        if (!args.hasAny()) {
            this.sendUsage();
            return;
        }
        BlockESP blockESP = AlphaDLC.getInstance().getModuleRepository().modules().stream().filter(module -> module instanceof BlockESP).findFirst().orElse(null);
        if (blockESP == null) {
            ChatMessage.brandmessage("\u041c\u043e\u0434\u0443\u043b\u044c Block ESP \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d");
            return;
        }
        switch (subCommand = args.getString().toLowerCase()) {
            case "add": {
                if (args.hasAny()) {
                    String blockToAdd = args.getString();
                    class_2248 block = (class_2248)class_7923.field_41175.method_63535(class_2960.method_12829((String)blockToAdd));
                    if (block != null && block != class_2246.field_10124) {
                        String registryName = class_7923.field_41175.method_10221((Object)block).toString();
                        if (blockESP.getBlocksToHighlight().add(registryName)) {
                            ChatMessage.brandmessage("\u0414\u043e\u0431\u0430\u0432\u043b\u0435\u043d \u0431\u043b\u043e\u043a " + registryName + " \u0432 Block ESP");
                            break;
                        }
                        ChatMessage.brandmessage("\u0411\u043b\u043e\u043a " + registryName + " \u0443\u0436\u0435 \u0435\u0441\u0442\u044c \u0432 Block ESP");
                        break;
                    }
                    ChatMessage.brandmessage("\u0411\u043b\u043e\u043a " + blockToAdd + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d");
                    break;
                }
                ChatMessage.brandmessage("\u0423\u043a\u0430\u0436\u0438\u0442\u0435 \u0431\u043b\u043e\u043a \u0434\u043b\u044f \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0438\u044f: .blockesp add <block>");
                break;
            }
            case "remove": {
                if (args.hasAny()) {
                    String blockToRemove = args.getString();
                    class_2248 block = (class_2248)class_7923.field_41175.method_63535(class_2960.method_12829((String)blockToRemove));
                    if (block != null && block != class_2246.field_10124) {
                        String registryName = class_7923.field_41175.method_10221((Object)block).toString();
                        if (blockESP.getBlocksToHighlight().remove(registryName)) {
                            ChatMessage.brandmessage("\u0423\u0434\u0430\u043b\u0451\u043d \u0431\u043b\u043e\u043a " + registryName + " \u0438\u0437 Block ESP");
                            break;
                        }
                        ChatMessage.brandmessage("\u0411\u043b\u043e\u043a " + blockToRemove + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 Block ESP");
                        break;
                    }
                    ChatMessage.brandmessage("\u0411\u043b\u043e\u043a " + blockToRemove + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d");
                    break;
                }
                ChatMessage.brandmessage("\u0423\u043a\u0430\u0436\u0438\u0442\u0435 \u0431\u043b\u043e\u043a \u0434\u043b\u044f \u0443\u0434\u0430\u043b\u0435\u043d\u0438\u044f: .blockesp remove <block>");
                break;
            }
            case "clear": {
                blockESP.getBlocksToHighlight().clear();
                ChatMessage.brandmessage("\u0421\u043f\u0438\u0441\u043e\u043a Block ESP \u043e\u0447\u0438\u0449\u0435\u043d");
                break;
            }
            case "list": {
                ChatMessage.brandmessage("\u0421\u043f\u0438\u0441\u043e\u043a \u0432\u0441\u0435\u0445 \u0431\u043b\u043e\u043a\u043e\u0432 \u0432 Minecraft:");
                class_7923.field_41175.method_10235().stream().map(class_2960::toString).sorted().forEach(blockName -> ChatMessage.brandmessage("- " + blockName));
                break;
            }
            default: {
                this.sendUsage();
            }
        }
    }

    public void sendUsage() {
        ChatMessage.brandmessage("\u041f\u0440\u0438\u043c\u0435\u0440 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u044f \u043a\u043e\u043c\u0430\u043d\u0434\u044b .blockesp:");
        ChatMessage.brandmessage(".blockesp add <block> - \u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u0431\u043b\u043e\u043a \u0432 Block ESP (\u043d\u0430\u043f\u0440\u0438\u043c\u0435\u0440: .blockesp add minecraft:diamond_ore)");
        ChatMessage.brandmessage(".blockesp remove <block> - \u0423\u0434\u0430\u043b\u0438\u0442\u044c \u0431\u043b\u043e\u043a \u0438\u0437 Block ESP (\u043d\u0430\u043f\u0440\u0438\u043c\u0435\u0440: .blockesp remove minecraft:diamond_ore)");
        ChatMessage.brandmessage(".blockesp clear - \u041e\u0447\u0438\u0441\u0442\u0438\u0442\u044c \u0441\u043f\u0438\u0441\u043e\u043a Block ESP");
        ChatMessage.brandmessage(".blockesp list - \u041f\u043e\u043a\u0430\u0437\u0430\u0442\u044c \u0432\u0441\u0435 \u0431\u043b\u043e\u043a\u0438 \u0432 Minecraft");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (!args.hasAny()) {
            return Stream.of("add", "remove", "clear", "list");
        }
        if (args.hasExactlyOne()) {
            String partial = args.peekString().toLowerCase();
            return Stream.of("add", "remove", "clear", "list").filter(cmd -> cmd.startsWith(partial));
        }
        String subCommand = args.getString().toLowerCase();
        if (("add".equals(subCommand) || "remove".equals(subCommand)) && args.hasExactlyOne()) {
            String partial = args.peekString().toLowerCase();
            return class_7923.field_41175.method_10235().stream().map(class_2960::toString).filter(blockName -> blockName.toLowerCase().startsWith(partial));
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u0423\u043f\u0440\u0430\u0432\u043b\u044f\u0435\u0442 \u0444\u0443\u043d\u043a\u0446\u0438\u0435\u0439 Block ESP.";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u0423\u043f\u0440\u0430\u0432\u043b\u044f\u0435\u0442 \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u043e\u0439 \u0431\u043b\u043e\u043a\u043e\u0432 \u0432 \u043c\u043e\u0434\u0443\u043b\u0435 Block ESP.", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", ".blockesp add <block> - \u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u0431\u043b\u043e\u043a \u0432 \u0441\u043f\u0438\u0441\u043e\u043a \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0438.", ".blockesp remove <block> - \u0423\u0434\u0430\u043b\u0438\u0442\u044c \u0431\u043b\u043e\u043a \u0438\u0437 \u0441\u043f\u0438\u0441\u043a\u0430 \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0438.", ".blockesp clear - \u041e\u0447\u0438\u0441\u0442\u0438\u0442\u044c \u0441\u043f\u0438\u0441\u043e\u043a \u043f\u043e\u0434\u0441\u0432\u0435\u0447\u0438\u0432\u0430\u0435\u043c\u044b\u0445 \u0431\u043b\u043e\u043a\u043e\u0432.", ".blockesp list - \u041f\u043e\u043a\u0430\u0437\u0430\u0442\u044c \u0432\u0441\u0435 \u0434\u043e\u0441\u0442\u0443\u043f\u043d\u044b\u0435 \u0431\u043b\u043e\u043a\u0438.");
    }
}
